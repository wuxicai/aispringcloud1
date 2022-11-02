package com.southwind.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户实体类")
public class User {
    public User(String name, String password, int age) {
        this.name = name;
        this.password = password;
        this.age = age;
    }

    @ApiModelProperty("姓名")
    public String name;
    @ApiModelProperty("密码")
    public String password;
    @ApiModelProperty("年龄")
    public int age;
}
