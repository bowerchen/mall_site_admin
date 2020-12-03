package com.javaee.mallsite.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaee.mallsite.dao.OrderItemMapper;
import com.javaee.mallsite.dao.OrderMapper;
import com.javaee.mallsite.dao.ProductMapper;
import com.javaee.mallsite.dao.ShippingMapper;
import com.javaee.mallsite.enums.ProductStatusEnum;
import com.javaee.mallsite.enums.ResponseEnum;
import com.javaee.mallsite.pojo.*;
import com.javaee.mallsite.service.ICartService;
import com.javaee.mallsite.service.IOrderService;
import com.javaee.mallsite.vo.OrderItemVo;
import com.javaee.mallsite.vo.OrderVo;
import com.javaee.mallsite.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.javaee.mallsite.enums.OrderStatusEnum.CANCELED;
import static com.javaee.mallsite.enums.OrderStatusEnum.NO_PAY;
import static com.javaee.mallsite.enums.PaymentTypeEnum.PAY_ONLINE;
import static com.javaee.mallsite.enums.ResponseEnum.*;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/23 13:57
 */
@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private ShippingMapper shippingMapper;

    @Autowired
    private ICartService cartService;

    @Autowired
    private ProductMapper  productMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public ResponseVo<OrderVo> create(Integer uid, Integer shippingId) {
        // 收货地址校验
        final Shipping shipping = shippingMapper.selectByUidAndShippingId(uid, shippingId);
        log.info("shipping={}", shipping);
        if (shipping == null) {
            return ResponseVo.error(SHIPPING_NOT_EXIST);
        }

        // 购物车校验(是否有商品、库存)
        final List<Cart> cartList = cartService.listForCart(uid).stream()
                .filter(Cart::getProductSelected)
                .collect(Collectors.toList());
        log.info("cartList={}", cartList);
        if (CollectionUtils.isEmpty(cartList)) {
            return ResponseVo.error(CART_SELECTED_IS_EMPTY);
        }
        
            // 获取cartList的productIds
        final Set<Integer> productIdSet = cartList.stream()
                .map(Cart::getProductId)
                .collect(Collectors.toSet());
        log.info("productList={}", productIdSet);
        final List<Product> productList = productMapper.selectByProductIdSet(productIdSet);
        Map<Integer, Product> map = productList.stream()
                .collect(Collectors.toMap(Product::getId, product -> product));

        List<OrderItem> orderItemList = new ArrayList<>();
        Long orderNo = generateOrderNo();
        for (Cart cart : cartList) {
            // 根据productId查数据库
            final Product product = map.get(cart.getProductId());
            if (product == null) {
                return ResponseVo.error(PRODUCT_NOT_EXIST, "商品不存在, productId =" + cart.getProductId());
            }

            // 商品上下架状态
            if (!ProductStatusEnum.ON_SALE.getCode().equals(product.getStatus())) {
                return ResponseVo.error(PRODUCT_OFF_SALE_OR_DELETE,
                        "商品已下架, " + product.getName());
            }

            // 库存是否充足
            if (product.getStock() < cart.getQuantity()) {
                return ResponseVo.error(PRODUCT_STOCK_ERROR,
                        "库存不正确, " + product.getName());
            }
            final OrderItem orderItem = buildOrderItem(uid, orderNo, cart.getQuantity(), product);
            orderItemList.add(orderItem);

            // 减库存
            product.setStock(product.getStock()-cart.getQuantity());
            int row = productMapper.updateByPrimaryKeySelective(product);
            if (row <= 0) {
                return ResponseVo.error(ResponseEnum.ERROR);
            }
        }

        // 计算总价，只计算被选中的商品
        // 生成订单，入库：order 和 order_item  使用 事务 同时写入
        final Order order = buildOrder(uid, orderNo, shippingId, orderItemList);

        final int rowForOrder = orderMapper.insertSelective(order);
        if (rowForOrder <= 0) {
            return ResponseVo.error(ERROR);
        }

        int rowForOrderItem = orderItemMapper.batchInsert(orderItemList);
        if (rowForOrderItem <= 0) {
            return ResponseVo.error(ERROR);
        }
        // 更新购物车 (删掉选中的商品)
        for (Cart cart : cartList) {
            cartService.delete(uid, cart.getProductId());
        }

        // 构造orderVo
        final OrderVo orderVo = buildOrderVo(order, orderItemList, shipping);
        return ResponseVo.success(orderVo);
    }

    @Override
    public ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        final List<Order> orderList = orderMapper.selectByUid(uid);

        final Set<Long> orderNoSet = orderList.stream().map(Order::getOrderNo).collect(Collectors.toSet());
        final List<OrderItem> orderItemList = orderItemMapper.selectByOrderNoSet(orderNoSet);
        Map<Long, List<OrderItem>> orderItemMap = orderItemList.stream()
                .collect(Collectors.groupingBy(OrderItem::getOrderNo));

        final Set<Integer> shippingIdSet = orderList.stream().map(Order::getShippingId).collect(Collectors.toSet());
        final List<Shipping> shippingList = shippingMapper.selectByIdSet(shippingIdSet);
        Map<Integer, Shipping> shippingMap = shippingList.stream()
                .collect(Collectors.toMap(Shipping::getId, shipping -> shipping));

        List<OrderVo> orderVoList = new ArrayList<>();
        for (Order order : orderList) {
            buildOrderVo(order, orderItemMap.get(order.getOrderNo()), shippingMap.get(order.getShippingId()));
        }
        PageInfo pageInfo = new PageInfo(orderList);
        pageInfo.setList(orderVoList);

        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<OrderVo> detail(Integer uid, Long orderNo) {
        final Order order = orderMapper.selectByOrderNo(orderNo);
        if (order == null || !order.getUserId().equals(uid)) {
            return ResponseVo.error(ORDER_NOT_EXIST);
        }
        Set orderNoSet = new HashSet();
        orderNoSet.add(order.getOrderNo());
        List<OrderItem> orderItemList = orderItemMapper.selectByOrderNoSet(orderNoSet);

        final Shipping shipping = shippingMapper.selectByPrimaryKey(order.getShippingId());

        final OrderVo orderVo = buildOrderVo(order, orderItemList, shipping);
        return ResponseVo.success(orderVo);
    }

    @Override
    public ResponseVo cancel(Integer uid, Long orderNo) {

        final Order order = orderMapper.selectByOrderNo(orderNo);
        if (order == null || !order.getUserId().equals(uid)) {
            return ResponseVo.error(ORDER_NOT_EXIST);
        }

        // 只有/未付款/订单可以取消
        if (!order.getStatus().equals(NO_PAY.getCode())) {
            return ResponseVo.error(ORDER_STATUS_ERROR);
        }

        order.setStatus(CANCELED.getCode());
        order.setCloseTime(new Date());
        final int row = orderMapper.updateByPrimaryKeySelective(order);

        if (row <= 0) {
            return ResponseVo.error(ERROR);
        }
        return ResponseVo.success();
    }

    private OrderVo buildOrderVo(Order order, List<OrderItem> orderItemList, Shipping shipping) {
        final OrderVo orderVo = new OrderVo();
        BeanUtils.copyProperties(order, orderVo);

        final List<OrderItemVo> orderItemVoList = orderItemList.stream().map(e -> {
            OrderItemVo orderItemVo = new OrderItemVo();
            BeanUtils.copyProperties(e, orderItemVo);
            return orderItemVo;
        }).collect(Collectors.toList());
        orderVo.setOrderItemVoList(orderItemVoList);

        if (shipping != null) {
            orderVo.setShippingId(shipping.getId());
            orderVo.setShippingVo(shipping);
        }

        return orderVo;
    }

    private Order buildOrder(Integer uid,
                             Long orderNo,
                             Integer shippingId,
                             List<OrderItem> orderItemList
                             ) {
        final BigDecimal payment = orderItemList.stream().map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setUserId(uid);
        order.setShippingId(shippingId);
        order.setPayment(payment);
        order.setPaymentType(PAY_ONLINE.getCode());
        order.setPostage(0);
        order.setStatus(NO_PAY.getCode());
        return order;
    }

    /**
     * 企业级： 分布式唯一id
     * @return
     */
    private Long generateOrderNo() {
        return System.currentTimeMillis() + new Random().nextInt(999);
    }


    private OrderItem buildOrderItem(Integer uid, Long orderNo,Integer quantity, Product product) {
        OrderItem item = new OrderItem();
        item.setUserId(uid);
        item.setOrderNo(orderNo);
        item.setProductId(product.getId());
        item.setProductName(product.getName());
        item.setProductImage(product.getMainImage());
        item.setCurrentUnitPrice(product.getPrice());
        item.setQuantity(quantity);
        item.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        return item;
    }
}
