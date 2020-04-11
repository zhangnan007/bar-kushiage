package com.bar.kushiage.controller;

import com.bar.kushiage.common.Util;
import com.bar.kushiage.model.vo.order.OrderVo;
import com.bar.kushiage.model.vo.order.QueryBillParamVo;
import com.bar.kushiage.model.vo.order.QueryBillTableVo;
import com.bar.kushiage.service.FoodService;
import com.bar.kushiage.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;

/**
 * 订单controller
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    Logger logger = LoggerFactory.getLogger(FoodController.class);

    @Autowired
    FoodService foodService;
    @Autowired
    OrderService orderService;

    /**
     * 提交订单
     *
     * @return
     */
    @RequestMapping(value = "/submit")
    @ResponseBody
    public Boolean submit(@RequestBody OrderVo orderVo) {
        // 构造结果集
        try {
            logger.info("order controller submit start");
            Double sumPrice = foodService.calFoodPrice(orderVo.bulidFoodIds());
            Double totalPrice = Util.parsePrice(orderVo.getTotalMoney());
            if(sumPrice - totalPrice != 0){
                logger.error("order controller submit error,price disaccord,service price: " + sumPrice + ", client price: " + totalPrice);
                return Boolean.FALSE;
            }
            boolean result = orderService.create(orderVo);
            return  result;
        } catch (Exception e) {
            logger.error("order controller submit error", e);
            return Boolean.FALSE;
        }
    }

    /**
     * 根据参数查询订单数据
     * @param queryBillParamVo
     * @return
     */
    @RequestMapping(value = "/queryBillByParam")
    @ResponseBody
    public QueryBillTableVo queryBillByParam(QueryBillParamVo queryBillParamVo) {
        // 构造结果集
        try {
            logger.info("order controller queryBillByParam start");
            if(queryBillParamVo != null){
                // 补全时间秒
                if(StringUtils.isNotBlank(queryBillParamVo.getStimeStr())){
                    queryBillParamVo.setStartTime(Timestamp.valueOf(queryBillParamVo.getStimeStr() + ":00"));
                }
                if(StringUtils.isNotBlank(queryBillParamVo.getEtimeStr())){
                    queryBillParamVo.setEndTime(Timestamp.valueOf(queryBillParamVo.getEtimeStr() + ":59"));
                }
            }
            QueryBillTableVo result = orderService.queryByParam(queryBillParamVo);
            return result;
        } catch (Exception e) {
            logger.error("order controller queryBillByParam error", e);
        }
        return  null;
    }

    /**
     * 根据订单ID获取订单详情数据
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/showDetails")
    public String showDetails(Long orderId, Model model) {
        // 构造结果集
        try {
            logger.info("order controller showDetails start");
            model.addAttribute("orderId",orderId);
            return "orderDetails";
        } catch (Exception e) {
            logger.error("order controller showDetails error", e);
        }
            return  null;
    }
}
