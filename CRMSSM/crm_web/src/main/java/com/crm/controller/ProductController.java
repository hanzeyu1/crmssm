package com.crm.controller;

import com.crm.domin.Product;
import com.crm.result.PageBean;
import com.crm.service.ProductService;
import com.crm.utils.DateUtil;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name ProductController
 * @date 2021/1/28 12:34
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    @RequestMapping("/findAll")
    public ModelAndView showProductList(@RequestParam(defaultValue = "1") int nowPage,
                                        @RequestParam(defaultValue = "2") int pageSize){
        ModelAndView mv = new ModelAndView();
        PageBean<Product> pageBean = null;


        try {
            pageBean = productService.findAllProduct(nowPage, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.addObject("pageBean",pageBean);
        mv.setViewName("product-list");
        return mv;
    }

    @RequestMapping("/toAdd")
    public ModelAndView toAdd(String id){

        Product product = null;
        if (id != null && id != "") {
            try {
                product = productService.findProductById(id);
                product.setDepartureTimeStr(DateUtil.date2String(new Date(product.getDepartureTime()+""),"MM-dd-yyyy - HH:mm"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//10-11-2018 - 00:10
        ModelAndView modelAndView = new ModelAndView();
        if (product != null) {
            modelAndView.addObject("product",product);
        }
        modelAndView.setViewName("product-add");
        return modelAndView;
    }

    @RequestMapping(value = "/add&update",method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TEST')")
    public String addOrUpdate(Product product){

        product.setDepartureTime(DateUtil.string2Date(product.getDepartureTimeStr(),"MM-dd-yyyy - HH:mm"));
        if (product.getDepartureTime() == null) {
            product.setDepartureTime(DateUtil.string2Date(product.getDepartureTimeStr(),"yyyy-MM-dd HH:mm"));
        }
        if (product.getId() != null && product.getId() != "") {
            //修改
            try {
                productService.updateProduct(product);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            //新增
            try {
                productService.saveProduct(product);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/product/findAll.do";
    }

    @RequestMapping("/delProduct")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TEST')")
    public String delProduct(String id){
        try {
            productService.delProductById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/product/findAll.do";
    }

    @RequestMapping("/showProduct")
    public ModelAndView showProduct(String id){

        Product product = null;

        try {
            product = productService.findProductById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("product",product);
        mv.setViewName("product-dataform");
        return mv;
    }
}
