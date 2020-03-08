package com.bar.kushiage.controller;

import com.bar.kushiage.model.vo.FoodVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/food")
public class FoodController {

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
        model.addAttribute("foodInfos",list);
        return "foodList";
    }

}
