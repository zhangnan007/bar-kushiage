package com.bar.kushiage.dao;

import com.bar.kushiage.model.dto.OrderFoodLog;
import com.bar.kushiage.model.dto.OrderFoodLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderFoodLogMapper {
    long countByExample(OrderFoodLogExample example);

    int deleteByExample(OrderFoodLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderFoodLog record);

    int insertSelective(OrderFoodLog record);

    List<OrderFoodLog> selectByExample(OrderFoodLogExample example);

    OrderFoodLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderFoodLog record, @Param("example") OrderFoodLogExample example);

    int updateByExample(@Param("record") OrderFoodLog record, @Param("example") OrderFoodLogExample example);

    int updateByPrimaryKeySelective(OrderFoodLog record);

    int updateByPrimaryKey(OrderFoodLog record);
}