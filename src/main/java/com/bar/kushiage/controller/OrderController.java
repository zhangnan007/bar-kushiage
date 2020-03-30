package com.bar.kushiage.controller;

import com.bar.kushiage.model.vo.order.OrderVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 订单controller
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    Logger logger = LoggerFactory.getLogger(FoodController.class);

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

            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("order controller submit error", e);
            return Boolean.FALSE;
        }
    }
}
