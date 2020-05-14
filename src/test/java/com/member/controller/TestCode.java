package com.member.controller;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javafx.scene.control.Cell;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class TestCode {

    @Test
    void testc() throws Exception {
        //创建pdf
        PdfReader reader = null;
        Document document = new Document();
        FileOutputStream fos = new FileOutputStream(new File("D:\\yan\\11.pdf"));
        PdfWriter pdfWriter = PdfWriter.getInstance(document,fos);

        //设置文档属性
      //  createAttr(document);
        //设置页眉页脚
//        createHeaderFooter(document);
        /*Chunk：块(Chunk)是能被添加到文档的文本的最小单位。

        Phrase：短句（Phrase）是一系列以特定间距（两行之间的距离）作为参数的块。

        Paragraph ：段落是一系列块和（或）短句。同短句一样，段落有确定的间距。用户还可以指定缩排；在边和（或）右边保留一定空白，段落可以左对齐、右对齐和居中对齐。添加到文档中的每一个段落将自动另起一行。*/
        document.open();
        document.add(new Paragraph("Table"));
        document.add(createColorText("小燕子",18,BaseColor.RED,0));
//        PdfPTable table = createTable(4, Element.ALIGN_CENTER, 2);


        //创建单元格并设置样式，看自身情况可使用循环
       /* table.addCell("11111");
        table.addCell("11111");
        table.addCell("11111");
        table.addCell("11111");
        table.addCell("11111");
        table.addCell("11111");*/


       /* document.add(table);*/
/*

        Paragraph parag = new Paragraph("  ----------------------这里是分割线---------------------    ");
        parag.setAlignment(Element.ALIGN_CENTER);
        document.add(parag);
*/

    /*    Chunk chunk = new Chunk("PdfTable 样例");
//		chunk.setHorizontalScaling(0.5f);  //水平缩放
        document.add(chunk);*/

        //创建表格及设置表格样式
//        PdfPTable pTable = createPdfTabel();
        //创建单元格并设置样式，看自身情况可使用循环
       /* pTable.addCell(createPdfCell("姓名"));
        pTable.addCell(createPdfCell("性别"));
        pTable.addCell(createPdfCell("籍贯"));
        pTable.addCell(createPdfCell("生日"));
        pTable.addCell(createPdfCell("英语"));
        pTable.addCell(createPdfCell("数学"));
        pTable.addCell(createPdfCellWithSpan("行列合并", 2, 1));
        pTable.addCell(createPdfCell("物理"));
        pTable.addCell(createPdfCell("化学"));
        pTable.addCell(createPdfCell("生物"));
//		pTable.addCell(createPdfCell("地理"));
        document.add(pTable);*/
       String str = "\"这里是段落内容，这里是段落内容，这里是段落内容，这里是段落内容，\"\n" +
               "                + \"这里是段落内容，这里是段落内容，这里是段落内容，这里是段落内容，这里是段落内容，这里是段落内容，\"\n" +
               "                + \"这里是段落内容\"";
        document.add(createColorText(str,18,BaseColor.RED,0));

        //生成图片
        document.add(imgToPdf("D:\\yan\\banner.jpg"));

        // 新建第二页，页眉页脚会继承，页码会递增
        document.newPage();
//		document.setPageSize(PageSize.A4);  //默认是 A4 大小
        document.add(new Paragraph("第二页"));

        document.close();
      //  textWaterImg(pdfWriter);
//        文档关闭状态执行 ,包括水印、页眉、页脚

       picWaterImg("D:\\yan\\11.pdf","D:\\yan\\pic.pdf","D:\\yan\\banner.jpg");


        System.out.println("over");

    }
    private void createAttr(Document document){
        document.addAuthor("小燕子");
        document.addHeader("name","content");
        document.addSubject("subj");
        document.addKeywords("keywords  keyword");
        document.addTitle("title");
        document.addCreator("creator");
        //	document.addProducer();
        //	document.addCreationDate();

    }
    public void createHeaderFooter(Document document, Font font){


    }
    private static PdfPTable createTable(int colNum, int align, int padding) {
        PdfPTable table = null;
        table = new PdfPTable(colNum);
        /*
         * 外边框一直有
         * 0 单元格无边框；1 单元格有横边框；4的倍数 单元格有竖边框；5 横竖边框均有
         * 默认有横竖边框
         */
//		table.getDefaultCell().setBorder(Cell.NO_BORDER);
//        table.setAlignment(align);
//        table.setPadding(padding);
        return table;
    }
    private static PdfPTable createPdfTabel() {
        PdfPTable pTable = new PdfPTable(4);
        pTable.getDefaultCell().setBorder(1);
        pTable.setHorizontalAlignment(Element.ALIGN_CENTER);
        return pTable;
    }
    private static PdfPCell createPdfCellWithSpan(String content, int rowSpan, int colSpan) {
        PdfPCell pCell = new PdfPCell();
        pCell.setColspan(rowSpan);
        pCell.setRowspan(colSpan);
        pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pCell.setPhrase(new Phrase(content));
        return pCell;
    }
    private static PdfPCell createPdfCell(String content) {
        PdfPCell pCell = new PdfPCell();
        pCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pCell.setPadding(5);
        pCell.setPhrase(new Phrase(content));
        return pCell;
    }
    private static Image imgToPdf(String imgPath) {
        // 读取一个图片
        Image image = null;
        try {
            image = Image.getInstance(imgPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        image.setAlignment(Image.ALIGN_CENTER);

        // 设置图片的绝对位置
        // 第一个参数表示距页面左下角的横向宽度，第二个参数表示距页面左下角的纵向高度
//		image.setAbsolutePosition(0, 200);//不写这行则默认在内容下一行

        // 等比例放大或缩小图片
/*		float imageHeight = image.getScaledHeight();
		float imageWidth = image.getScaledWidth();
		int i = 0;
		while (imageHeight > 500 || imageWidth > 500) {
			image.scalePercent(100 - i);   //减 是缩小， 加 是放大
			i++;
			imageHeight = image.getScaledHeight();
			imageWidth = image.getScaledWidth();
			System.out.println("imageHeight->" + imageHeight);
			System.out.println("imageWidth->" + imageWidth);
		}
*/
        image.scaleAbsolute(50, 60);	//参数代表宽高，默认居中

        return image;
    }

    //设置有颜色的字体
    private Paragraph createColorText(String text,float fontSize,BaseColor color,int align){
        FontSelector selector = new FontSelector();
        //非汉字字体颜色
//        Font f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);
        Font f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);
        f1.setColor(color);
//        f1.setColor(BaseColor.RED);
        //汉字字体颜色
        Font f2 = FontFactory.getFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//        f2.setColor(BaseColor.RED);
        f2.setColor(color);

        selector.addFont(f1);
        selector.addFont(f2);

        Phrase ph = selector.process(text);
        Paragraph p = new Paragraph(ph);
//        p.setAlignment(1);
        p.setAlignment(0);
       return p;
    }
    //生成W文字水印

    public static void textWaterImg(PdfWriter writer) throws Exception {
        String outPdfPath="D:\\yan\\1133.pdf";
        File tempfile = new File(outPdfPath);
        if(tempfile.exists()) {
            tempfile.delete();
        }
        /// 待加水印的文件
                PdfReader reader = new PdfReader("D:\\yan\\11.pdf");
                // 加完水印的文件
                 PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outPdfPath));
                int total = reader.getNumberOfPages() + 1;
                PdfContentByte content;
               // 设置字体
                BaseFont font = BaseFont.createFont();
                // 循环对每页插入水印
                for (int i = 1; i < total; i++)
                     {
                        // 水印的起始
                         content = stamper.getUnderContent(i);
                        // 开始
                        content.beginText();
                        // 设置颜色 默认为蓝色
                        content.setColorFill(BaseColor.BLUE);
                        // content.setColorFill(Color.GRAY);
                        // 设置字体及字号
                       content.setFontAndSize(font, 38);
                         // 设置起始位置
                        content.setTextMatrix(200, 880);
//                        content.setTextMatrix(textWidth, textHeight);
                        // 开始写入水印
//                        content.showTextAligned(Element.ALIGN_LEFT, text, textWidth,
//                                        textHeight, 45);
                        content.showTextAligned(Element.ALIGN_LEFT, new String("123".getBytes()), 200,
                                        600, 45);
                        content.endText();
                    }
                stamper.close();
    }

    /**
     *
     * @param inPdfFile  需要加水印的pdf文件路径
     * @param outPdfFile   输出的pdf文件路径
     * @param picPath   需要加水印的路径路径
     * @throws Exception
     */
    public boolean  picWaterImg(String inPdfFile,String outPdfFile,String picPath) throws Exception {
        File tempfile = new File(outPdfFile);
        if (tempfile.exists()) {
            tempfile.delete();
        }
        if (StringUtils.isBlank(inPdfFile) || StringUtils.isBlank(outPdfFile) || StringUtils.isBlank(picPath)) {
            System.out.println("路径不能为空");
            return false;
        }
        // 判断是否为输入和输出是否为pdf结尾的
        if (!(inPdfFile.endsWith("pdf") || inPdfFile.endsWith("PDF")) || !(outPdfFile.endsWith("pdf") || outPdfFile.endsWith("PDF"))) {
            System.out.println("文件名不对");
            return false;
        }
        if ((!(picPath.endsWith("jpg") || picPath.endsWith("JPG")))) {
            System.out.println("图片文件名不对");
            return false;
        }

        PdfReader reader = new PdfReader(inPdfFile);
        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(outPdfFile));

        //开始签名插入水印
        Image img = Image.getInstance(picPath);// 水印图片路径
        img.setAbsolutePosition(50, 10);//设置水印图片的位置
        //设置图片旋转
//        使用setRotation(final float r)或者setRotationDegrees(final float deg)方法来实现图片的旋转效果。
        img.setRotation(60f);
//        img.setRotationDegrees(60);
        //水印图片尺寸与pdf尺寸不一致时，可以直接设置插入水印的图片大小，

        //不用事先对水印图片进行缩放，插入后比较清晰
        img.scaleAbsolute(400, 400);

        int total = reader.getNumberOfPages() + 1;
        PdfContentByte under = null;
        PdfGState gs = new PdfGState();
        // 设置图片透明度为0.2f
        gs.setFillOpacity(0.2f);
        // 设置笔触字体不透明度为0.4f
        gs.setStrokeOpacity(0.4f);
        for (int i = 1; i < total; i++) {
            under = stamp.getUnderContent(i);//获取pdf当前页面
            // under = stamp.getOverContent(1);
            // 开始水印处理
            under.beginText();
            // 设置透明度
            under.setGState(gs);
            // 设置水印字体参数及大小
            under.setFontAndSize(BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED), 60);
            // 设置水印对齐方式 水印内容 X坐标 Y坐标 旋转角度，不支持中文
            under.showTextAligned(Element.ALIGN_CENTER, "not", 200, 400, 45);
            // 设置水印颜色
            under.setColorFill(BaseColor.GRAY);
            under.addImage(img);
            // 完成水印添加
            under.endText();
            // stroke
            under.stroke();
        }
        stamp.close();//签名完毕，关闭流输出
        reader.close();
        return true;
    }
    @Test
    public void filetest(){
        String fileName = "11[[2da4da03-0e8e-4d41-a3d1-88ec757aafd0]].pdf";
       if (fileName.contains("[[") && fileName.contains("]]")){
           String tt = fileName.substring(fileName.indexOf("[["),fileName.indexOf("]]")+2);
           fileName = fileName.replace(tt,"");
           System.out.println(fileName);
       }

    }
}