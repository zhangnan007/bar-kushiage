package com.bar.kushiage.controller;

import com.bar.kushiage.model.vo.FoodTypeVo;
import com.bar.kushiage.model.vo.FoodVo;
import com.bar.kushiage.service.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        // 构造结果集
        try {
            logger.info("food controller findFoodType start, traceId: " + traceId);
            List<FoodTypeVo> types = foodService.findFoodType(traceId);
            return types;
        } catch (Exception e) {
            e.printStackTrace();
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
    @RequestMapping(value = "/queryFoods")
    public String queryFoods(String traceId, Long foodTypeId, String foodCode, Model model) {
        logger.info("food controller findFoodType start, traceId: " + traceId);
        try {
            List<FoodVo> foods = foodService.findFoodByTypeAndCode(foodTypeId, foodCode, traceId);
            model.addAttribute("foods", foods);
            return "foodList";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("food controller queryFoods error,traceId: " + traceId, e);
        }
        return null;
    }

}
