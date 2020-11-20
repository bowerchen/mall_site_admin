package com.javaee.mallsite.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @version 1.0.0
 * Create by bowerchen
 * @time 2020/11/20 15:22
 */
@Data
public class UserLogin {

    // @NotEmpty  用于集合
    // @NotNull
    // @NotBlank  用于 String 判断空格
    @NotBlank()
    private String username;

    @NotBlank
    private String password;

}
