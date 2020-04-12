package com.bar.kushiage.dao;

import com.bar.kushiage.model.dto.OrderPayLog;
import com.bar.kushiage.model.dto.OrderPayLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderPayLogMapper {
    long countByExample(OrderPayLogExample example);

    int deleteByExample(OrderPayLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderPayLog record);

    int insertSelective(OrderPayLog record);

    List<OrderPayLog> selectByExample(OrderPayLogExample example);

    OrderPayLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderPayLog record, @Param("example") OrderPayLogExample example);

    int updateByExample(@Param("record") OrderPayLog record, @Param("example") OrderPayLogExample example);

    int updateByPrimaryKeySelective(OrderPayLog record);

    int updateByPrimaryKey(OrderPayLog record);
}