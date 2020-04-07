package com.bar.kushiage.controller;

import com.bar.kushiage.common.Util;
import com.bar.kushiage.model.vo.order.OrderVo;
import com.bar.kushiage.model.vo.order.QueryBillParamVo;
import com.bar.kushiage.model.vo.order.QueryBillTableVo;
import com.bar.kushiage.service.FoodService;
import com.bar.kushiage.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
            e.printStackTrace();
            logger.error("order controller submit error", e);
            return Boolean.FALSE;
        }
    }

    @RequestMapping(value = "/queryBillByParam")
    @ResponseBody
    public QueryBillTableVo queryBillByParam(QueryBillParamVo queryBillParamVo) {
        // 构造结果集
        try {
            logger.info("order controller queryBillByParam start");
            return orderService.queryByParam(queryBillParamVo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("order controller queryBillByParam error", e);
        }
        return  null;
    }

    @RequestMapping(value = "/queryBillById")
    @ResponseBody
    public OrderVo queryBillById(@RequestBody Long OrderId) {
        // 构造结果集
        try {
            logger.info("order controller queryBillById start");

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("order controller queryBillById error", e);
        }
            return  null;
    }
}
