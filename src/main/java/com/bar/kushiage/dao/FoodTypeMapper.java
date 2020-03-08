package com.bar.kushiage.dao;

import com.bar.kushiage.model.dto.FoodType;
import com.bar.kushiage.model.dto.FoodTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FoodTypeMapper {
    long countByExample(FoodTypeExample example);

    int deleteByExample(FoodTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FoodType record);

    int insertSelective(FoodType record);

    List<FoodType> selectByExample(FoodTypeExample example);

    FoodType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FoodType record, @Param("example") FoodTypeExample example);

    int updateByExample(@Param("record") FoodType record, @Param("example") FoodTypeExample example);

    int updateByPrimaryKeySelective(FoodType record);

    int updateByPrimaryKey(FoodType record);
}