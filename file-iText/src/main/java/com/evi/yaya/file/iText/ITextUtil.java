package com.evi.yaya.file.iText;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.attach.impl.layout.HtmlPageBreak;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.font.FontProvider;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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
            String fonts = ITextUtil.class.getResource("/fonts").getPath();
            FontProvider fontProvider = new DefaultFontProvider();
            fontProvider.addDirectory(fonts);
            ConverterProperties properties = new ConverterProperties();
            properties.setFontProvider(fontProvider);
            properties.setCharset("UTF-8");
            List<IElement> elements = HtmlConverter.convertToElements(new FileInputStream(htmlPath), properties);
            PdfDocument pdf = new PdfDocument(new PdfWriter(pdfPath));
            Document document = new Document(pdf, PageSize.A4, false);
            for (IElement element : elements) {
                // 分页符
                if (element instanceof HtmlPageBreak) {
                    document.add((HtmlPageBreak) element);

                    //普通块级元素
                } else {
                    document.add((IBlockElement) element);
                }
            }
            document.close();
        } catch (FileNotFoundException e) {
            log.error("parseXHtml FileNotFoundException e={}", e.getMessage());
            return Boolean.FALSE;
        } catch (IOException e) {
            log.error("parseXHtml IOException e={}", e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

//    public static Boolean parseXHtml(String htmlPath, String pdfPath) {
//        try {
//            Document document = new Document();
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
//            document.open();
//            // todo 页面html标签需指定中文字体，否则中文显示异常
//            // todo html文件中需设定纸张大小，如A4纸张，否则显示异常
//            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(htmlPath), Charset.forName("UTF-8"));
//            document.close();
//        } catch (DocumentException e) {
//            log.error("parseXHtml DocumentException e={}", e.getMessage());
//            return Boolean.FALSE;
//        } catch (FileNotFoundException e) {
//            log.error("parseXHtml FileNotFoundException e={}", e.getMessage());
//            return Boolean.FALSE;
//        } catch (IOException e) {
//            log.error("parseXHtml IOException e={}", e.getMessage());
//            return Boolean.FALSE;
//        }
//        return Boolean.TRUE;
//    }

    public static void main(String args[]) {
        parseXHtml("E:\\data\\pdf\\html\\freeMarker01.html", "E:\\data\\pdf\\result\\freeMarker01.pdf");
    }
}

