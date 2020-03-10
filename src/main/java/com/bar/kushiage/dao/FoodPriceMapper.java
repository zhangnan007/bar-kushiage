package com.bar.kushiage.dao;

import com.bar.kushiage.model.dto.FoodPrice;
import com.bar.kushiage.model.dto.FoodPriceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FoodPriceMapper {
    long countByExample(FoodPriceExample example);

    int deleteByExample(FoodPriceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FoodPrice record);

    int insertSelective(FoodPrice record);

    List<FoodPrice> selectByExample(FoodPriceExample example);

    FoodPrice selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FoodPrice record, @Param("example") FoodPriceExample example);

    int updateByExample(@Param("record") FoodPrice record, @Param("example") FoodPriceExample example);

    int updateByPrimaryKeySelective(FoodPrice record);

    int updateByPrimaryKey(FoodPrice record);
}