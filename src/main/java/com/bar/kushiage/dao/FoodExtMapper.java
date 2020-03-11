package com.bar.kushiage.dao;

import com.bar.kushiage.model.vo.FoodVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FoodExtMapper {

    List<FoodVo> selectFoodByTypeId(@Param("typeId") Long typeId, @Param("foodCode") String foodCode);

}
