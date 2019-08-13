package com.evi.yaya.file.freeMarker;

import com.evi.yaya.file.freeMarker.bean.Customer;
import com.evi.yaya.file.freeMarker.bean.Machine;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
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

    public static void main(String args[]) {
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
            Template template = cfg.getTemplate("freeMarker01.html");
            //指定生成html文件的位置
            Writer writer = null;
            File file2 = new File("E:\\data\\pdf\\html\\freeMarker01.html");
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2)));
            //调用模板方法，完成合并
            template.process(root, writer);
            writer.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
