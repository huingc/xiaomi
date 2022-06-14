package com.huing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * kyf
 * 2022/6/9 21:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillInfo {
    private Integer bid;
    private Integer p_id;
    private Integer num;
    private ProductInfo productInfo;
}
