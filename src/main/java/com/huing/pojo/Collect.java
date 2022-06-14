package com.huing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Collect {
    private Integer cid;
    private Integer uid;
    private Integer pid;

    private List<ProductInfo> productInfoList;
}
