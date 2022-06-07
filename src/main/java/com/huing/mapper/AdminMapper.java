package com.huing.mapper;

import com.huing.pojo.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    Admin findByName(String name);
}