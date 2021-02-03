package com.crm.service.impl;

import com.crm.dao.OrdersMapper;
import com.crm.domin.Orders;
import com.crm.domin.Product;
import com.crm.result.PageBean;
import com.crm.service.OrdersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name OrdersServiceImpl
 * @date 2021/1/30 10:28
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Resource
    private OrdersMapper ordersMapper;

    public PageBean findAllOrders(int nowPage, int pageSize) throws  Exception{

        PageHelper.startPage(nowPage,pageSize);

        List<Orders> ordersList = ordersMapper.finAllOrders();

        PageInfo<Orders> pageInfo = new PageInfo<Orders>(ordersList);

        PageBean pageBean = new PageBean();
        pageBean.setNowPage(nowPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount((int)pageInfo.getTotal());
        pageBean.setTotalPage(pageInfo.getPages());
        pageBean.setList(ordersList);
        pageBean.cal();

        return pageBean;
    }

    public Orders findOrdersInfoById(String id) throws Exception {
        Orders orders = ordersMapper.findOrdersById(id);
        return orders;
    }
}
