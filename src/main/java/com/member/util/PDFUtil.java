package com.member.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.member.entity.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PDFUtil {
    // 创建pdf文件
    public static String createPdf(Student student,String outputDir,String outputFileName,String picPath) throws Exception {
        //创建pdf
        PdfReader reader = null;
        Document document = new Document();

        //如果文件夹不存在那么就创建文件夹
        File rootDir = new File(outputDir);
        if (!rootDir.exists()){
            rootDir.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(new File(outputDir+outputFileName));
        PdfWriter pdfWriter = PdfWriter.getInstance(document,fos);

        document.open();

        document.add(createColorText("党校结业证书",25,BaseColor.RED,1));

       String str = "  亲爱的"+student.getStuName()+"同学,恭喜你正式加入党员";
       document.add(createColorText(str,20,BaseColor.BLACK,1));

        document.close();
        //  textWaterImg(pdfWriter);
//        文档关闭状态执行 ,包括水印、页眉、页脚

       String docPath = picWaterImg(outputDir+outputFileName,outputDir+student.getStuName()+".pdf",picPath,"SCHOOL");
       return docPath;
    }

    //设置有颜色的字体
    private static Paragraph createColorText(String text,float fontSize,BaseColor color,int align){
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
        p.setAlignment(align);
        return p;
    }

    /**
     *
     * @param inPdfFile  需要加水印的pdf文件路径
     * @param outPdfFile   输出的pdf文件路径
     * @param picPath   需要加水印的路径路径
     * @throws Exception
     */
    public static String  picWaterImg(String inPdfFile,String outPdfFile,String picPath,String warterText) throws Exception {
        File tempfile = new File(outPdfFile);
        if (tempfile.exists()) {
            tempfile.delete();
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
            under.setFontAndSize(BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED), 70);
            // 设置水印对齐方式 水印内容 X坐标 Y坐标 旋转角度，不支持中文
            under.showTextAligned(Element.ALIGN_CENTER, warterText, 300, 500, 45);
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
        return outPdfFile;
    }
}
