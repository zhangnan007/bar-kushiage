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
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Map;

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
        try {
            logger.info("order controller submit start");
            Double sumPrice = foodService.calFoodPrice(orderVo.bulidFoodIds());
            Double totalPrice = Util.parsePrice(orderVo.getTotalMoney());
            if (sumPrice - totalPrice != 0) {
                logger.error("order controller submit error,price disaccord,service price: " + sumPrice + ", client price: " + totalPrice);
                return Boolean.FALSE;
            }
            boolean result = orderService.create(orderVo);
            return result;
        } catch (Exception e) {
            logger.error("order controller submit error", e);
            return Boolean.FALSE;
        }
    }

    /**
     * 根据参数查询订单数据
     *
     * @param queryBillParamVo
     * @return
     */
    @RequestMapping(value = "/queryBillByParam")
    @ResponseBody
    public QueryBillTableVo queryBillByParam(QueryBillParamVo queryBillParamVo) {
        try {
            logger.info("order controller queryBillByParam start");
            if (queryBillParamVo != null) {
                // 补全时间秒
                if (StringUtils.isNotBlank(queryBillParamVo.getStimeStr())) {
                    queryBillParamVo.setStartTime(Timestamp.valueOf(queryBillParamVo.getStimeStr() + ":00"));
                }
                if (StringUtils.isNotBlank(queryBillParamVo.getEtimeStr())) {
                    queryBillParamVo.setEndTime(Timestamp.valueOf(queryBillParamVo.getEtimeStr() + ":59"));
                }
            }
            QueryBillTableVo result = orderService.queryByParam(queryBillParamVo);
            return result;
        } catch (Exception e) {
            logger.error("order controller queryBillByParam error", e);
        }
        return null;
    }

    /**
     * 根据订单ID获取订单详情数据
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/showDetails")
    public String showDetails(Long orderId, Model model) {
        try {
            logger.info("order controller showDetails start");
            // 获取指定订单数据
            OrderVo orderVo = orderService.queryDetailsById(orderId);
            model.addAttribute("order", orderVo);
            return "orderDetails";
        } catch (Exception e) {
            logger.error("order controller showDetails error", e);
        }
        return null;
    }

    /**
     * 获取订单已选菜品信息
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/queryOrderFoodByOrderId")
    @ResponseBody
    public Map<String, Object> queryOrderFoodByOrderId(Long orderId) {
        try {
            logger.info("order controller queryOrderFoodByOrderId start");
            if (orderId != null) {
                Map<String, Object> result = orderService.queryOrderFoodByOrderId(orderId);
                return result;
            }
        } catch (Exception e) {
            logger.error("order controller queryOrderFoodByOrderId error", e);
        }
        return null;
    }

    /**
     * 获取订单支付明细
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/queryOrderPayLogByOrderId")
    @ResponseBody
    public Map<String, Object> queryOrderPayLogByOrderId(Long orderId) {
        try {
            logger.info("order controller queryOrderPayLogByOrderId start");
            if (orderId != null) {
                Map<String, Object> result = orderService.queryOrderPayLogByOrderId(orderId);
                return result;
            }
        } catch (Exception e) {
            logger.error("order controller queryOrderPayLogByOrderId error", e);
        }
        return null;
    }

}
