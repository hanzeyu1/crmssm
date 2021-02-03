package com.crm.service;

import com.crm.domin.Orders;
import com.crm.result.PageBean;

import java.util.List;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name OrdersService
 * @date 2021/1/30 10:28
 */
public interface OrdersService {
    PageBean findAllOrders(int nowPage, int pageSize) throws Exception;
    Orders findOrdersInfoById(String id) throws  Exception;
}
