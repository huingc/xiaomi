package com.huing.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * kyf
 * 2022/6/10 15:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillVo {
    private Integer bid;
    private Integer uid;
    //设置页码
    private Integer page;
}
