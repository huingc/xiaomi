package com.huing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * kyf
 * 2022/6/11 14:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Integer uid;
    private Integer p_id;
    private Integer num;
    private ProductInfo productInfo;
}
