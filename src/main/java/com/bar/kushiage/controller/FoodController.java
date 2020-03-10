package com.bar.kushiage.controller;

import com.bar.kushiage.model.vo.FoodTypeVo;
import com.bar.kushiage.model.vo.FoodVo;
import com.bar.kushiage.service.FoodService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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
    @RequestMapping(value = "/findFoodType")
    public List<FoodTypeVo> getShopAndFoodInfo(String traceId,Model model) {
        try {
            logger.info("food controller findFoodType start, traceId: " + traceId);
            List<FoodTypeVo> types = foodService.findFoodType(traceId);
            return types;
        } catch (Exception e) {
            e.printStackTrace();

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
    @RequestMapping(value = "/findListByType")
    public String getShopAndFoodInfo(Integer foodTypeId, Model model) {
        List<FoodVo> list = new ArrayList<FoodVo>();
        FoodVo vo1 = new FoodVo();
        FoodVo vo2 = new FoodVo();
        FoodVo vo3 = new FoodVo();
        vo1.setName("菜品菜品菜品菜品菜品菜品1");
        vo1.setPrice(30.10);
        vo1.setSpecs("超大份超大份");
        vo2.setName("菜品菜品菜品菜品菜品菜品2");
        vo2.setPrice(30.20);
        vo2.setSpecs("大份大份大份");
        vo3.setName("菜品菜品菜品菜品菜品菜品3");
        vo3.setPrice(30.30);
        vo3.setSpecs("小份小份小份");
        list.add(vo1);
        list.add(vo2);
        list.add(vo3);
        model.addAttribute("foodInfos", list);
        return "foodList";
    }

}
