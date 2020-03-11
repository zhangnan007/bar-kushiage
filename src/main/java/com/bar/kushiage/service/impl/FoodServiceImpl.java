package com.bar.kushiage.service.impl;

import com.bar.kushiage.common.constant.FoodConstantEnum;
import com.bar.kushiage.dao.FoodExtMapper;
import com.bar.kushiage.dao.FoodMapper;
import com.bar.kushiage.dao.FoodTypeMapper;
import com.bar.kushiage.model.dto.FoodType;
import com.bar.kushiage.model.dto.FoodTypeExample;
import com.bar.kushiage.model.vo.FoodTypeVo;
import com.bar.kushiage.model.vo.FoodVo;
import com.bar.kushiage.service.FoodService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("foodService")
public class FoodServiceImpl implements FoodService {
    Logger logger = LoggerFactory.getLogger(FoodServiceImpl.class);

    @Autowired
    FoodTypeMapper foodTypeMapper;
    @Autowired
    FoodMapper foodMapper;
    @Autowired
    FoodExtMapper foodExtMapper;

    @Override
    public List<FoodTypeVo> findFoodType(String traceId) {
        try {
            FoodTypeExample foodTypeExample = new FoodTypeExample();
            foodTypeExample.createCriteria()
                    .andStatusEqualTo(FoodConstantEnum.FOOD_STATUS_NORMAL.getCode());
            // 获取正在生效的菜品类型
            List<FoodType> types = foodTypeMapper.selectByExample(foodTypeExample);
            List<FoodTypeVo> vos = new ArrayList<FoodTypeVo>();
            if (CollectionUtils.isNotEmpty(types)) {
                types.forEach(type -> {
                    FoodTypeVo vo = new FoodTypeVo();
                    vo.setId(type.getId());
                    vo.setName(type.getName());
                    vos.add(vo);
                });
            }
            return vos;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("FoodServiceImpl findFoodType error", e);
            throw e;
        }
    }

    @Override
    public List<FoodVo> findFoodByTypeAndCode(Long typeId, String foodCode, String traceId) {
        try {
            List<FoodVo> foodVos = foodExtMapper.selectFoodByTypeId(typeId,foodCode);
            return foodVos;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("FoodServiceImpl findFoodByType error", e);
            throw e;
        }
    }
}
