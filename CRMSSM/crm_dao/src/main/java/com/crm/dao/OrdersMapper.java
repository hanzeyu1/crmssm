package com.crm.dao;

import com.crm.domin.Orders;

import java.util.List;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name OrdersMapper
 * @date 2021/1/30 10:14
 */
public interface OrdersMapper {
    List<Orders> finAllOrders() throws Exception;
    Orders findOrdersById(String id);
}
