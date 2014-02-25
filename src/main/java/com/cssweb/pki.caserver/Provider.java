package com.cssweb.pki.caserver;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Provider {
 
    public static void main(String[] args) throws Exception {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"provider.xml"});
        

FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(new String[]{"e:/pki/caserver/src/main/resources/provider.xml"});

        context.start();
 
        System.in.read(); // 按任意键退出
    }
 
}