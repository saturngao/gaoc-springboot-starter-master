package com.imooc.service;

import com.github.pagehelper.PageInfo;
import com.imooc.pojo.SysCountRecord;
import com.imooc.pojo.SysDoorCount;

import java.util.List;
import java.util.Map;

public interface SysDoorCountService {

    List<Map<String, String>> getCodeList();

    List<SysDoorCount> getAllList();

    SysDoorCount getSysDoorCount(String id);

    void saveSysCount(SysCountRecord sysCountRecord);

    PageInfo<SysCountRecord> getAllRecordByCon(Map<String, String> params);
}
