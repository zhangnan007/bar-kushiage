package com.bar.kushiage.dao;

import com.bar.kushiage.model.vo.FoodVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FoodExtMapper {


    /**
     * 获取指定类型的菜品数据，typeId为空时获取全部
     *
     * @param typeId
     * @return
     */
    List<FoodVo> selectFoodByTypeId(@Param("typeId") Long typeId);

}