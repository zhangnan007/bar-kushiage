package com.bar.kushiage.dao;

import com.bar.kushiage.model.dto.Food;
import com.bar.kushiage.model.dto.FoodExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FoodMapper {
    long countByExample(FoodExample example);

    int deleteByExample(FoodExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Food record);

    int insertSelective(Food record);

    List<Food> selectByExample(FoodExample example);

    Food selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Food record, @Param("example") FoodExample example);

    int updateByExample(@Param("record") Food record, @Param("example") FoodExample example);

    int updateByPrimaryKeySelective(Food record);

    int updateByPrimaryKey(Food record);
}