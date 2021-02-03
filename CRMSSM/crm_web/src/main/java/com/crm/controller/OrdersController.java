package com.crm.controller;

import com.crm.domin.Orders;
import com.crm.result.PageBean;
import com.crm.service.OrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name OrdersController
 * @date 2021/1/30 10:35
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Resource
    private OrdersService ordersService;

    @RequestMapping("/showOrdersList")
    public ModelAndView showOrdersList(@RequestParam(defaultValue = "1") int nowPage, @RequestParam(defaultValue = "2")int pageSize){
        PageBean pageBean = null;
        try {
            pageBean = ordersService.findAllOrders(nowPage, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("pageBean" ,pageBean);
        mv.setViewName("order-list");
        return mv;
    }

    @RequestMapping("/showOrders")
    public ModelAndView showOrdersInfo(String id){
        Orders orders = null;
        try {
            orders = ordersService.findOrdersInfoById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("orders" ,orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
