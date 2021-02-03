package com.crm.dao;

import com.crm.domin.Product;

import java.util.List;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name ProductMapper
 * @date 2021/1/28 12:20
 */
public interface ProductMapper {
    List<Product> findAllProduct() throws Exception;
    void addProduct(Product product) throws Exception;
    void updateProduct(Product product) throws Exception;
    Product findProductById(String id) throws Exception;
    void delProductById(String id) throws  Exception;
}
