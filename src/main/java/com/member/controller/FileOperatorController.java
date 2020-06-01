package com.member.controller;

import com.member.entity.Student;
import com.member.service.StudentServiceImp;
import com.member.util.PDFUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@RestController
public class FileOperatorController {


    @Autowired
    private StudentServiceImp studentServiceImp;
    @GetMapping("/tempList/{fileDir}")
    public ResponseEntity getdownList(@PathVariable(name = "fileDir",required = false) String fileDir){
        String path = Class.class.getClass().getResource("/").getPath() + "/static/"+fileDir;  //获得路径
        //判断路径是否存在
        File fileRoot = new File(path);
        if (!fileRoot.isDirectory()){
            //如果不是文件夹那么，则返回
            String msg = "此路径不是一个文件夹";
           return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        List list = new ArrayList();
        //列出里面的文件
        File[] files = fileRoot.listFiles();
        if (files!=null && files.length>0){
            for (File file:files){
                String disName = file.getName();
                String downName=disName;
                //这里的[[]]里面的内容是生成uuid的内容，对其进行替换
                if (disName.contains("-suuid") && disName.contains("-euuid")){
                    String rep = disName.substring(disName.indexOf("-suuid"),disName.indexOf("-euuid")+"-euuid".length());
                    disName = disName.replace(rep,"");
                }
                    Map map = new HashMap();
                //如果是下载模板
                    map.put("disName",disName); //如果不是上传文件夹的下载，那么键和值都是一样
                    map.put("downName",downName); //如果不是上传文件夹的下载，那么键和值都是一样
                 list.add(map);
            }
        }
        return ResponseEntity.ok(list);
    }

    //文件上传
    @PostMapping("/upload")
    public ModelAndView upload(MultipartFile fileUpload,ModelAndView modelAndView){
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();  //fileupload对象不可能为null
        modelAndView.setViewName("upload");
        if (StringUtils.isEmpty(fileName)){
            return modelAndView;
        }
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //获得文件名
        String orName = fileName.substring(0,fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = orName+"-suuid"+UUID.randomUUID()+"-euuid"+suffixName;
        //指定本地文件夹存储图片
        String filePath = Class.class.getClass().getResource("/").getPath() + "/static/upload/";  //获得路径
        // 如果该路径不存在，则需要创建
        File file = new File(filePath);
        if (!file.exists()){
            file.mkdirs();
        }
        try {
            fileUpload.transferTo(new File(filePath+fileName));
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            return modelAndView;
        }
    }

    @GetMapping("/pdfDown")
    public ResponseEntity pdfDown(Student stu, HttpServletResponse response) throws Exception {
        //查找学生
        Student student = studentServiceImp.updateEntity(stu);
        String path = Class.class.getClass().getResource("/").getPath();
         path = PDFUtil.createPdf(student,path,student.getStuNo()+".pdf",path+"/static/pic/banner.jpg");
        String downname = student.getStuName()+".pdf";
        downloadTool(response,path,downname);
        return ResponseEntity.ok("");
    }

    //下载上传的文件
    @GetMapping("/udownload")
    public ResponseEntity getUDownload(@RequestParam("name") String filename,HttpServletResponse response) throws UnsupportedEncodingException {
        //根据上传的文件进行下载
        String filePath = Class.class.getClass().getResource("/").getPath() + "/static/upload/";  //获得路径

        if (StringUtils.isEmpty(filename)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        String path = filePath+filename;
        String rep = filename.substring(filename.indexOf("-suuid"),filename.indexOf("-euuid")+"-euuid".length());
        filename = filename.replace(rep,"");
        downloadTool(response,path,filename);
        return ResponseEntity.ok("");
    }

    @GetMapping("/student_file/download")
    public ResponseEntity getStuFile(@RequestParam(value = "name",required = false) String studentName,HttpServletResponse response) throws UnsupportedEncodingException {
        File file = new File(Class.class.getClass().getResource("/").getPath() + "/static/upload/11[[73958f91-9492-47d5-a808-0f4828077046]].pdf");
        byte[] body = null;
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            body = new byte[is.available()];
            is.read(body);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + (studentName==null?"1.pdf":studentName+".pdf"));
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
        return entity;
    }


    public void  downloadTool(HttpServletResponse response,String path,String fileName) throws UnsupportedEncodingException {
        File file = new File(path);
        if (file.exists()) {
            //重置response
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.addHeader("Content-Type","application/force-download");//4
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("gb2312"),"ISO8859-1"));
            //设置文件长度
            int fileLength = (int)file.length();
            response.setContentLength(fileLength);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("success");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
