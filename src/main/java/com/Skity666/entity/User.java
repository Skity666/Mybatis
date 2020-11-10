package com.Skity666.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName User
 * @Description TODO
 * @Author Skity666
 * @Date 2020/11/9 0009 19:16
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private Date birth;
    private BigDecimal salary;
}
