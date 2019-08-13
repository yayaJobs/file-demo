package com.evi.yaya.file.freeMarker;

import com.evi.yaya.file.freeMarker.bean.Customer;
import com.evi.yaya.file.freeMarker.bean.Machine;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 说明：TODO
 *
 * @Title: FreeMarkerService
 * @Package com.evi.yaya.file.freeMarker
 * @See: [] </br>
 * @Copyright: Copyright (c) 2017 </br>
 * @Company: sany huax witsight team by product</br>
 * @author: wangzy
 * @date: 2019/8/13 13:15
 * @version: V1.0
 */
public class FreeMarkerService {

    public static void main(String args[]){
        Configuration cfg = new Configuration(Configuration.getVersion());
        Map<String, Object> root = new HashMap<>();
        Customer customer = new Customer();
        customer.setName("Test");
        Machine machine = new Machine();
        machine.setSerialNo("85541256ASDDAS4545");
        machine.setTypeName("第五型号");
        root.put("customer", customer);
        root.put("machine", machine);
        try {
            //2.设置模板所在的目录 - test.ftl
            cfg.setDirectoryForTemplateLoading(new File("E:\\data\\pdf\\template\\"));
            //3.设置字符集
            cfg.setDefaultEncoding("utf-8");
            Template temp = cfg.getTemplate("freeMarker01.html");
            /* Merge data-model with template */
            Writer out = new OutputStreamWriter(System.out);
            temp.process(root, out);
            out.close();
        } catch (TemplateException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
