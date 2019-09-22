package io.xxx.demo.pgtest.data;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.xxx.demo.pgtest.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
