package com.crm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name A
 * @date 2021/1/29 20:23
 */
public class A {

    public static void main(String[] args) {
        Date date = new Date("Wed Oct 10 10:10:00 CST 2018");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        System.out.println(format);
    }
}
