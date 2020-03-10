package com.bar.kushiage.service;

import com.bar.kushiage.model.vo.FoodTypeVo;
import com.bar.kushiage.model.vo.FoodVo;

import java.util.List;

/**
 * 菜品服务
 */
public interface FoodService {

    /**
     * 查询菜品类型
     *
     * @param traceId
     * @return
     */
    public List<FoodTypeVo> findFoodType(String traceId);

    /**
     * 查询根据菜品类型/助记码查询菜品信息(名称，菜品ID，规格，价钱)
     *
     * @param typeId
     * @param traceId
     * @return
     */
    public List<FoodVo> findFoodByType(Long typeId, String traceId);
}
