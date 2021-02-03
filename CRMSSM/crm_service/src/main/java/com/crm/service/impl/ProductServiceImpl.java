package com.crm.service.impl;

import com.crm.dao.ProductMapper;
import com.crm.domin.Product;
import com.crm.result.PageBean;
import com.crm.service.ProductService;
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
 * @name ProductServiceImpl
 * @date 2021/1/28 12:26
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;

    /**
     * 分页查询
     * @param nowPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    public PageBean<Product> findAllProduct(int nowPage, int pageSize) throws  Exception{
        PageHelper.startPage(nowPage,pageSize);
        List<Product> products = productMapper.findAllProduct();
        PageInfo pageInfo = new PageInfo(products);

        PageBean<Product> pageBean = new PageBean();
        pageBean.setNowPage(nowPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount((int)pageInfo.getTotal());
        pageBean.setTotalPage(pageInfo.getPages());
        pageBean.setList(products);
        pageBean.cal();

        return pageBean;
    }

    /**
     * 新建
     * @param product
     * @throws Exception
     */
    public void saveProduct(Product product) throws Exception {
        productMapper.addProduct(product);
    }

    /**
     * 修改
     * @param product
     * @throws Exception
     */
    public void updateProduct(Product product) throws Exception {
        if (product.getProductDesc() == null || product.getProductDesc() == "") {
            product.setProductDesc(findProductById(product.getId()).getProductDesc());
        }
        productMapper.updateProduct(product);
    }

    /**
     * 根据ID查询product
     * @param id
     * @return
     */
    public Product findProductById(String id) throws Exception{
        return productMapper.findProductById(id);
    }

    /**
     * 删除
     * @param id
     * @throws Exception
     */
    public void delProductById(String id) throws Exception {
        productMapper.delProductById(id);
    }
}
