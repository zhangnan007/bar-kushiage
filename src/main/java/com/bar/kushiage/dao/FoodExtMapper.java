package com.bar.kushiage.dao;

import com.bar.kushiage.model.vo.food.FoodVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FoodExtMapper {

    /**
     * 根据ID或助记码查询菜品信息
     *
     * @param typeId
     * @param foodCode
     * @return
     */
    List<FoodVo> selectFoodByTypeId(@Param("typeId") Long typeId, @Param("foodCode") String foodCode);

    /**
     * 根据菜品ID和规格计算菜品总价格
     *
     * @return
     */
    Double selectSumPrice(@Param("foodId") Long foodId, @Param("foodSpecs") String foodSpecs);

}
