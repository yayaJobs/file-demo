package com.evi.yaya.file.iText;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;

import static com.itextpdf.tool.xml.html.HTML.Tag.FONT;

/**
 * 说明：TODO
 *
 * @Title: ITextUtil
 * @Package com.evi.yaya.file.iText
 * @See: [] </br>
 * @Copyright: Copyright (c) 2017 </br>
 * @Company: sany huax witsight team by product</br>
 * @author: wangzy
 * @date: 2019/8/13 15:10
 * @version: V1.0
 */
@Slf4j
public class ITextUtil {

    public static Boolean parseXHtml(String htmlPath, String pdfPath) {
        try {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
            document.open();
            // todo 页面html标签需指定中文字体，否则中文显示异常
            // todo html文件中需设定纸张大小，如A4纸张，否则显示异常
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(htmlPath), Charset.forName("UTF-8"));
            document.close();
        } catch (DocumentException e) {
            log.error("parseXHtml DocumentException e={}", e.getMessage());
            return Boolean.FALSE;
        } catch (FileNotFoundException e) {
            log.error("parseXHtml FileNotFoundException e={}", e.getMessage());
            return Boolean.FALSE;
        } catch (IOException e) {
            log.error("parseXHtml IOException e={}", e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public static void main(String args[]) {
        parseXHtml("E:\\data\\pdf\\html\\freeMarker01.html", "E:\\data\\pdf\\result\\freeMarker01.pdf");
    }
}

