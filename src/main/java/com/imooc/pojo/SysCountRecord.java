package com.imooc.pojo;

import lombok.Data;

@Data
public class SysCountRecord {
    private String id;
    private String userId;
    private String totalFee;
    private String totalCount;
    private String createTime;
}
