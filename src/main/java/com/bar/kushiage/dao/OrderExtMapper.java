package com.bar.kushiage.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface OrderExtMapper {

    /**
     * 根据日期获取最大订单号
     * @param day
     * @return
     */
    @Select({"SELECT order_num as orderNum, create_time FROM `ORDER` WHERE create_time > #{day} ORDER BY create_time LIMIT 1"})
    public Map<String,Object> queryMaxOrderNumByDayTime(@Param("day") Long day);
}
