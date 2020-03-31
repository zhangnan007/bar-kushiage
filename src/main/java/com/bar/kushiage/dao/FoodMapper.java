package com.bar.kushiage.dao;

import com.bar.kushiage.model.dto.FoodType;
import com.bar.kushiage.model.vo.food.FoodVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FoodMapper {

    /**
     * 获取生效的菜品类型
     * @param status
     * @return
     */
    @Results({
            @Result(property = "id", column = "id",id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "loseReason", column = "lose_reason"),
            @Result(property = "status", column = "status"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "modifyTime", column = "modify_time")
    })
    @Select("select * from food_type where status = #{status}")
    List<FoodType> selectFoodTypeByStatus(@Param("status") Integer status);

    /**
     * 根据ID或助记码查询菜品信息
     *
     * @param typeId
     * @param foodCode
     * @return
     */
    List<FoodVo> selectByTypeId(@Param("typeId") Long typeId, @Param("foodCode") String foodCode);

    /**
     * 根据菜品ID和规格计算菜品总价格
     *
     * @return
     */
    Double selectSumPrice(@Param("foodId") Long foodId, @Param("foodSpecs") String foodSpecs);

}
