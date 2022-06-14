package com.huing.mapper;

import com.huing.pojo.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huing
 * @create 2022-06-07 16:01
 */
public interface AdminMapper {

    Admin findByName(String name);


}