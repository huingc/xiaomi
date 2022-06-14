package com.huing.mapper;

import com.huing.pojo.Inform;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * @author tzy
 * @create 2022-06-13 15:27
 */
public interface InformMapper {

    List<Inform> selectAll();

    List<Inform> informList(String iName);

    int addInform(Inform inform);

    Inform selectById(int iId);

    int updateInform(Inform inform);

    int deleteInform(int iId);

    Inform getInformId(int iId);

    Inform newInform();
}
