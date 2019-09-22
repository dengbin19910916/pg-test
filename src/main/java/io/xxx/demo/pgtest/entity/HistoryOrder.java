package io.xxx.demo.pgtest.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("history_order")
public class HistoryOrder {

    private Long id;
    private String channel;
    private String outId;
    private String shopCode;
    private String shopName;
    private LocalDateTime paidTime;
    private String buyerName;
    private String buyerMemo;
    private String receiverName;
    private String receiverMobile;
    private String receiverCountryCode;
    private String receiverCityCode;
    private String receiverProvinceCode;
    private String receiverAreaCode;
    private String receiverAddress;
    private String receiverZip;
    private Boolean includesGift;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
