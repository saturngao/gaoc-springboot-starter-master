package com.imooc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mapper.SysCountRecordMapper;
import com.imooc.mapper.SysDoorCountMapper;
import com.imooc.pojo.SysCountRecord;
import com.imooc.pojo.SysDoorCount;
import com.imooc.service.SysDoorCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysDoorCountServiceImpl implements SysDoorCountService {

    @Autowired
    private SysDoorCountMapper sysDoorCountMapper;
    @Autowired
    private SysCountRecordMapper sysCountRecordMapper;

    @Override
    public List<Map<String, String>> getCodeList() {
        return sysDoorCountMapper.getCodeList();
    }

    @Override
    public List<SysDoorCount> getAllList() {
        return sysDoorCountMapper.getAllList();
    }
    @Override
    public PageInfo<SysCountRecord> getAllRecordByCon(Map<String, String> params) {
        PageHelper.startPage(1, 10);
        List<SysCountRecord> list;
        if(params != null){
            list = sysCountRecordMapper.getUserRecords(params);
        } else {
            list = sysCountRecordMapper.getUserRecords(params);
        }
        PageInfo<SysCountRecord> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    @Override
    public SysDoorCount getSysDoorCount(String id) {
        return sysDoorCountMapper.getById(id);
    }

    @Override
    public void saveSysCount(SysCountRecord sysCountRecord) {
        sysCountRecordMapper.saveSysCountRecord(sysCountRecord);
    }
}
