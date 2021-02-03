package com.crm.service;

import com.crm.domin.Product;
import com.crm.result.PageBean;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name ProductService
 * @date 2021/1/28 12:25
 */
public interface ProductService {

    PageBean<Product> findAllProduct(int nowPage, int pageSize) throws  Exception;
    void saveProduct(Product product) throws  Exception;
    void updateProduct(Product product) throws Exception;
    Product findProductById(String id) throws  Exception;
    void delProductById(String id) throws  Exception;
}
