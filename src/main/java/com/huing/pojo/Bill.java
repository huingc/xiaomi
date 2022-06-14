package com.huing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * kyf
 * 2022/6/9 21:46
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    private Integer bid;
    private Integer uid;
    private Integer count;
    private Double total;
    private Date date;
    private List<BillInfo> billInfo;
}
