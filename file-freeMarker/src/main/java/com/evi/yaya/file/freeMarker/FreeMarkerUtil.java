package com.evi.yaya.file.freeMarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Map;

/**
 * 说明：TODO
 *
 * @Title: FreeMarkerUtil
 * @Package com.evi.yaya.file.freeMarker
 * @See: [] </br>
 * @Copyright: Copyright (c) 2017 </br>
 * @Company: sany huax witsight team by product</br>
 * @author: wangzy
 * @date: 2019/8/14 13:15
 * @version: V1.0
 */
@Slf4j
public class FreeMarkerUtil {

    private static Configuration configuration = null;

    static {
        configuration = new Configuration(Configuration.getVersion());
        //设置模板所在的目录 - test.ftl
        try {
            configuration.setDirectoryForTemplateLoading(new File("E:\\data\\pdf\\template\\"));
        } catch (IOException e) {
            log.error("init configuration IOException e={}", e.getMessage());
        }
        //设置字符集
        configuration.setDefaultEncoding("utf-8");
    }

    public static Boolean processTemplate(Map<String, Object> parmas, String templateName, String filePath) {
        Template template = null;
        Writer writer = null;
        try {
            template = configuration.getTemplate(templateName);
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
            //调用模板方法，完成合并
            template.process(parmas, writer);
        } catch (TemplateException e) {
            log.error("getTemplate TemplateException e={}", e.getMessage());
            return Boolean.FALSE;
        } catch (IOException e) {
            log.error("getTemplate IOException e={}", e.getMessage());
            return Boolean.FALSE;
        } finally {
            if (null != writer) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Boolean.TRUE;
    }
}
