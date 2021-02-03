package com.crm.controller;

import com.crm.domin.Product;
import com.crm.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name PageController
 * @date 2021/1/28 23:17
 */
@Controller
public class PageController {

    @Resource
    private ProductService productService;

    @RequestMapping("/{page}")
    public String toMain(@PathVariable(name = "page") String page){
        return page;
    }

}
