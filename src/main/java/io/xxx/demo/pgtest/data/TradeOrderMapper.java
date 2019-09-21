package io.xxx.demo.pgtest.data;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.xxx.demo.pgtest.entity.TradeOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TradeOrderMapper extends BaseMapper<TradeOrder> {
}
