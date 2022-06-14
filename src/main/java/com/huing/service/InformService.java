package com.huing.service;


import com.huing.pojo.Inform;

import java.util.Date;
import java.util.List;

/**
 * @author tzy
 * @create 2022-06-13 16:01
 */
public interface InformService {
    List<Inform> selectAll();

    List<Inform> informList(String iName);

    int addInform(Inform inform);

    Inform selectInform(int iId);

    int updateInform(Inform inform);

    int deleteInform(int iId);

    Inform getInformId(Integer iId);

    Inform newInform();
}
