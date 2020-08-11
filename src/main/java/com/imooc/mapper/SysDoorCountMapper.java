package com.imooc.mapper;

import com.imooc.pojo.SysDoorCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysDoorCountMapper {


    List<Map<String, String>> getCodeList();

    List<SysDoorCount> getAllList();

    /**
     * 通过ID查找
     * @param id
     * @return
     */
    SysDoorCount getById(@Param("id") String id);
}
