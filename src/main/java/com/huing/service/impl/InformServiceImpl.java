package com.huing.service.impl;

import com.huing.mapper.InformMapper;
import com.huing.pojo.Inform;
import com.huing.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author tzy
 * @create 2022-06-13 17:35
 */
@Service
public class InformServiceImpl implements InformService {
    @Autowired
    private InformMapper informMapper;
    @Override
    public List<Inform> selectAll() {
        return informMapper.selectAll();
    }
    @Override
    public List<Inform> informList(String iName) {
        return informMapper.informList(iName);
    }

    @Override
    public int addInform(Inform info) {
        info.setUpdateTime(new Date());
        return informMapper.addInform(info);
    }

    @Override
    public Inform selectInform(int iId) {
        return informMapper.selectById(iId);
    }

    @Override
    public int updateInform(Inform inform) {
        return informMapper.updateInform(inform);
    }

    @Override
    public int deleteInform(int iId) {
        return informMapper.deleteInform(iId);
    }

    @Override
    public Inform getInformId(Integer iId) {
        return informMapper.getInformId(iId);
    }

    @Override
    public Inform newInform(){
        return informMapper.newInform();
    }

}
