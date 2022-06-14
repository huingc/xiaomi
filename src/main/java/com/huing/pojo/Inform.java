package com.huing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author tzy
 * @create 2022-06-13 15:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Inform {
    private Integer iId;

    private String iName;

    private String iContent;

//    private String iPic;

    private Date updateTime;
}
