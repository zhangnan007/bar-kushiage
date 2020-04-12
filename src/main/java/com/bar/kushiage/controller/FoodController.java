package com.bar.kushiage.controller;

import com.bar.kushiage.model.vo.food.FoodTypeVo;
import com.bar.kushiage.model.vo.food.FoodVo;
import com.bar.kushiage.service.FoodService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/food")
public class FoodController {
    Logger logger = LoggerFactory.getLogger(FoodController.class);

    @Autowired
    FoodService foodService;

    /**
     * 获取菜品类型
     *
     * @param traceId
     * @return
     */
    @RequestMapping(value = "/queryFoodTypes")
    @ResponseBody
    public List<FoodTypeVo> queryFoodTypes(String traceId) {
        try {
            logger.info("food controller findFoodType start, traceId: " + traceId);
            List<FoodTypeVo> types = foodService.findFoodType(traceId);
            return types;
        } catch (Exception e) {
            logger.error("food controller findFoodType error,traceId: " + traceId, e);
        }
        return null;
    }

    /**
     * 获取菜品信息
     *
     * @param foodTypeId
     * @param model
     * @return
     */
    @RequestMapping(value = "/queryFoodsByTypeAndCode")
    public String queryFoodsByTypeAndCode(String traceId, Long foodTypeId, String foodCode, Model model) {
        logger.info("food controller findFoodType start, traceId: " + traceId);
        try {
            List<FoodVo> foods = foodService.findFoodByTypeAndCode(foodTypeId, foodCode, traceId);
            model.addAttribute("foods", foods);
        } catch (Exception e) {
            logger.error("food controller queryFoods error,traceId: " + traceId, e);
        }
        return "foods";
    }

    /**
     * 跳转支付窗口页面
     *
     * @param traceId
     * @param model
     * @return
     */
    @RequestMapping(value = "/toPay")
    public String toPay(String foodIds, String traceId, Model model) {
        try {
            logger.info("food controller toPay start");
            Double sumPrice = 0.00;
            // 根据foodId查询菜品总价格
            sumPrice = foodService.calFoodPrice(foodIds);
            if (StringUtils.isBlank(foodIds)) {
                sumPrice = 0.00;
            }
            model.addAttribute("totalMoney", sumPrice);
            BigDecimal value = new BigDecimal(sumPrice.toString());
            BigDecimal noZeros = value.stripTrailingZeros();
            String initInMoney = noZeros.toPlainString();
            model.addAttribute("inMoney", initInMoney);
            return "pay";
        } catch (Exception e) {
            logger.error("food controller toPay error", e);
            return null;
        }
    }

}
