package com.imooc.mapper;

import com.imooc.pojo.SysCountRecord;

import java.util.List;
import java.util.Map;

public interface SysCountRecordMapper {

    /**
     * 保存用户计算记录
     * @param sysCountRecord
     * @return
     */
    Long saveSysCountRecord(SysCountRecord sysCountRecord);

    /**
     * 条件查询查询用户计算记录
     * @param params
     * @return
     */
    List<SysCountRecord> getUserRecords(Map<String,String> params);
}
