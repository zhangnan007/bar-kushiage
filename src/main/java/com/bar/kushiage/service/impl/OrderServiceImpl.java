package com.bar.kushiage.service.impl;

import com.bar.kushiage.common.Util;
import com.bar.kushiage.common.constant.ConstantEnum;
import com.bar.kushiage.common.constant.PayTypeConstantEnum;
import com.bar.kushiage.dao.OrderFoodLogMapper;
import com.bar.kushiage.dao.OrderMapper;
import com.bar.kushiage.dao.OrderPayLogMapper;
import com.bar.kushiage.model.dto.*;
import com.bar.kushiage.model.vo.order.*;
import com.bar.kushiage.service.OrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    // 订单号自增序列
    private static AtomicInteger orderNumHolder = new AtomicInteger(0);

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderFoodLogMapper orderFoodLogMapper;
    @Autowired
    OrderPayLogMapper orderPayLogMapper;

    @Transactional
    @Override
    public Boolean create(OrderVo orderVo) throws ParseException {
        // 获取当天时间0点时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String begin = sdf.format(new Date()).split(" ")[0].trim() + " 00:00:00";
        Date day = sdf.parse(begin);
        // 获取订单号
        int num = generateOrderNum(day);
        String tempOrderNum = "000" + num;
        tempOrderNum = tempOrderNum.substring(tempOrderNum.length() - 4);
        String orderNum = begin.split(" ")[0].replace("-", "").trim() + tempOrderNum;
        Order order = bulidOrder(orderVo, orderNum);
        orderMapper.insert(order);
        Date now = new Date();
        orderVo.getFoodList().forEach(item -> {
            OrderFoodLog foodLog = new OrderFoodLog();
            foodLog.setOrderId(order.getId());
            foodLog.setFoodName(item.getName());
            foodLog.setFoodPrice(item.getPrice());
            foodLog.setFoodSpecs(item.getSpecs());
            foodLog.setNum(item.getNum());
            foodLog.setStatus(ConstantEnum.DB_STATUS_NORMAL.getCode());
            foodLog.setCreateTime(now);
            foodLog.setModifyTime(now);
            orderMapper.insertOrderFood(foodLog);
        });
        orderVo.getPayInfo().forEach(item -> {
            OrderPayLog payLog = new OrderPayLog();
            payLog.setOrderId(order.getId());
            payLog.setPayType(item.getPayVal());
            payLog.setPayPrice(Util.parsePrice(item.getPayPrice()));
            payLog.setStatus(ConstantEnum.DB_STATUS_NORMAL.getCode());
            payLog.setCreateTime(now);
            payLog.setModifyTime(now);
            orderMapper.insertOrderPay(payLog);
        });
        return Boolean.TRUE;

    }

    @Override
    public QueryBillTableVo queryByParam(QueryBillParamVo queryBillParamVo) {
        // 分页插件: 查询第1页，每页10行
        Page<Order> page = PageHelper.startPage(queryBillParamVo.getPageNum(), queryBillParamVo.getPageSize());
        List<Order> orderInfo = orderMapper.selectByParam(queryBillParamVo);
        QueryBillTableVo result = new QueryBillTableVo();
        List<BillTableRowsVo> rows = new ArrayList<BillTableRowsVo>();
        if (CollectionUtils.isNotEmpty(orderInfo)) {
            orderInfo.forEach(order -> {
                BillTableRowsVo vo = new BillTableRowsVo();
                vo.setId(order.getId());
                vo.setOrderNum(order.getOrderNum());
                vo.setMealNum(order.getMealNum());
                vo.setTotalPrice(order.getConsumePrice());
                vo.setCreateTime(Util.parseTimestampToString(order.getCreateTime()));
                rows.add(vo);
            });
            result.setTotal(page.getTotal());
        }
        result.setRows(rows);
        return result;
    }

    @Override
    public OrderVo queryDetailsById(Long orderId) {
        // 根据订单id查询订单信息
        Order order = orderMapper.selectById(orderId);
        // 构造订单vo
        OrderVo result = new OrderVo();
        if (order != null) {
            result.setId(order.getId());
            result.setOrderNum(Long.parseLong(order.getOrderNum()));
            result.setMealNum(order.getMealNum());
            result.setTotalMoney("￥ " + order.getConsumePrice());
            result.setPaidMoney("￥ " + order.getRealityPrice());
            result.setChangeMoney("￥ " + order.getChargePrice());
            result.setCreate_time(Util.parseTimestampToString(order.getCreateTime()));
            result.setModify_time(Util.parseTimestampToString(order.getModifyTime()));
        }
        return result;
    }

    @Override
    public Map<String, Object> queryOrderFoodByOrderId(Long orderId) {
        OrderFoodLogExample orderFoodLogExample = new OrderFoodLogExample();
        orderFoodLogExample.createCriteria().andOrderIdEqualTo(orderId).andStatusEqualTo(ConstantEnum.DB_STATUS_NORMAL.getCode());
        // 获取有效的订单菜品明细
        List<OrderFoodLog> orderFoods = orderFoodLogMapper.selectByExample(orderFoodLogExample);
        // 订单菜品明细dto转vo
        List<OrderFoodVo> rows = new ArrayList<OrderFoodVo>();
        if (CollectionUtils.isNotEmpty(orderFoods)) {
            orderFoods.forEach(of -> {
                OrderFoodVo vo = new OrderFoodVo();
                vo.setId(of.getId() + "");
                vo.setName(of.getFoodName());
                vo.setSpecs(of.getFoodSpecs());
                vo.setPrice(of.getFoodPrice());
                vo.setNum(of.getNum());
                float temp = of.getFoodPrice().floatValue();
                BigDecimal price = new BigDecimal(Double.toString((temp + 1)));
                BigDecimal num = new BigDecimal(Double.toString(of.getNum()));
                double totalPrice = price.multiply(num).doubleValue();// 相乘结果
                vo.setTotalPrice(totalPrice);
                rows.add(vo);
            });
        }
        // 构造前台使用的结果集
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", rows.size());
        result.put("rows", rows);
        return result;
    }

    @Override
    public Map<String, Object> queryOrderPayLogByOrderId(Long orderId) {
        OrderPayLogExample orderPayLogExample = new OrderPayLogExample();
        orderPayLogExample.createCriteria().andOrderIdEqualTo(orderId).andStatusEqualTo(ConstantEnum.DB_STATUS_NORMAL.getCode());
        // 获取有效的订单支付明细
        List<OrderPayLog> payLog = orderPayLogMapper.selectByExample(orderPayLogExample);
        // 订单支付明细dto转vo
        List<OrderPayLogVo> rows = new ArrayList<OrderPayLogVo>();
        if (CollectionUtils.isNotEmpty(payLog)) {
            payLog.forEach(pay -> {
                OrderPayLogVo vo = new OrderPayLogVo();
                vo.setId(pay.getId() + "");
                vo.setPayTypeText(PayTypeConstantEnum.getValueOf(pay.getPayType()).getNote());
                vo.setPayPrice("￥ " + pay.getPayPrice());
                rows.add(vo);
            });
        }
        // 构造前台使用的结果集
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", rows.size());
        result.put("rows", rows);
        return result;
    }


    /**
     * 构造order对象
     *
     * @param orderVo
     * @return
     */
    private Order bulidOrder(OrderVo orderVo, String orderNum) {
        Order order = new Order();
        order.setMealNum(orderVo.getMealNum());
        order.setOrderNum(orderNum);
        // 找零金额
        order.setChargePrice(Util.parsePrice(orderVo.getChangeMoney()));
        // 消费金额
        order.setConsumePrice(Util.parsePrice(orderVo.getTotalMoney()));
        // 实收金额
        order.setRealityPrice(Util.parsePrice(orderVo.getPaidMoney()));
        order.setStatus(ConstantEnum.DB_STATUS_NORMAL.getCode());
        String now = Util.parseDateToStr(new Date());
        order.setCreateTime(Timestamp.valueOf(now));
        order.setModifyTime(order.getCreateTime());
        return order;

    }

    /**
     * 生成订单序列号
     *
     * @param currDay
     * @return
     */
    private synchronized int generateOrderNum(Date currDay) {
        int num = orderNumHolder.incrementAndGet();
        if (num > 1) {
            return num;
        } else {
            // 进行初始化
            // 查询数据库当天最大订单号
            Map<String, Object> map = orderMapper.queryMaxOrderNumByDayTime(currDay);
            // 判断如果没有则直接加1并返回
            if (map == null || map.size() <= 0) {
                return num;
            } else {
                // 否则获取最大数加一再返回
                Integer orderNum = (Integer) map.get("orderNum");
                orderNum = orderNum + 1;
                orderNumHolder.set(orderNum);
                return orderNum;
            }
        }
    }
}
