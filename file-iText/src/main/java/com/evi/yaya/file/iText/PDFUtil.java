package com.evi.yaya.file.iText;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.*;
import java.nio.charset.Charset;

import static com.itextpdf.tool.xml.html.HTML.Tag.FONT;

/**
 * 说明：TODO
 *
 * @Title: PDFUtil
 * @Package com.evi.yaya.file.iText
 * @See: [] </br>
 * @Copyright: Copyright (c) 2017 </br>
 * @Company: sany huax witsight team by product</br>
 * @author: wangzy
 * @date: 2019/8/13 15:10
 * @version: V1.0
 */
public class PDFUtil {

    public static void main(String args[]) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E:\\data\\pdf\\result\\freeMarker01.pdf"));
        // step 3
        document.open();
        // step 4
        // todo 页面html标签需指定中文字体，否则中文显示异常
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream("E:\\data\\pdf\\html\\freeMarker01.html"), Charset.forName("UTF-8"));
        // step 5
        document.close();

    }
}

