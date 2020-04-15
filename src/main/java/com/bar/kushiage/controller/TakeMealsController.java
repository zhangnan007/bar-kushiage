package com.bar.kushiage.controller;

import com.bar.kushiage.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 取餐号controller
 */
@Controller
@RequestMapping("/takeMeal")
public class TakeMealsController {
    Logger logger = LoggerFactory.getLogger(TakeMealsController.class);

    /**
     * 叫号取餐
     *
     * @return
     */
    @RequestMapping(value = "/callMealNum")
    @ResponseBody
    public Boolean callMealNum(Long mealNum) {
        try {
            logger.info("takeMeal controller callMealNum start");
            if (mealNum != null) {
                String speechStr = "请第" + mealNum + "号取餐啦";
                Util.textToSpeech(speechStr);
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            logger.error("takeMeal controller callMealNum error", e);
        }
        return Boolean.FALSE;
    }
}
