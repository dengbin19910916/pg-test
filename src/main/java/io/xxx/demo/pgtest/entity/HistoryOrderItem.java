package io.xxx.demo.pgtest.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("history_order_item")
public class HistoryOrderItem {

    private Long id;
    private Long orderId;
    private String orderChannel;
    private Integer serial;
    private String skuId;
    private String name;
    private Integer quantity;
    private BigDecimal tagPrice;
    private BigDecimal purchasePrice;
    private Boolean given;
    private LocalDateTime orderCreatedTime;
    private String orderStatus;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
