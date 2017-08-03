package com.zhuzi.util_word;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Encoder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class DocumentHandler {
	private static Configuration configuration = null;

    static{
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
    }
    
    /**
     * 生成doc文档
     * @param dataMap
     * @param fileName
     * @throws UnsupportedEncodingException
     */
    public static void createDoc(Map<String, Object> dataMap, String fileName)
            throws UnsupportedEncodingException {
        configuration.setClassForTemplateLoading(DocumentHandler.class, "ftl");
        Template t = null;
        try {
            t = configuration.getTemplate("Demo.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File outFile = new File(fileName);
        Writer out = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outFile);
            OutputStreamWriter oWriter = new OutputStreamWriter(fos, "UTF-8");
            out = new BufferedWriter(oWriter);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        try {
            t.process(dataMap, out);
            out.close();
            fos.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * 生成doc文档(网页版)
     * @param dataMap
     * @param fileName
     * @throws IOException 
     */
    public static void createDocByHtml(Map<String, Object> dataMap,String fileName,HttpServletResponse resp)
            throws IOException {
        configuration.setClassForTemplateLoading(DocumentHandler.class, "ftl");
        Template t = null;
        try {
            t = configuration.getTemplate("Demo.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String name = fileName + ".doc";  
        File f = new File(name);  
        try {  
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开  
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");  
            t.process(dataMap, w);  
            w.close();  
        } catch (Exception ex) {  
            ex.printStackTrace();  
            throw new RuntimeException(ex);  
        }  
        
		InputStream fin = null;
		ServletOutputStream out = null;
		try {
			
			fin = new FileInputStream(f);

			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/msword");
			
			resp.addHeader("Content-Disposition",
					"attachment;filename="+fileName+".doc");

			out = resp.getOutputStream();
			byte[] buffer = new byte[512]; // 缓冲区
			int bytesToRead = -1;
			// 通过循环将读入的Word文件的内容输出到浏览器中
			while ((bytesToRead = fin.read(buffer)) != -1) {
				out.write(buffer, 0, bytesToRead);
			}
		} catch (UnsupportedEncodingException e) {
			logger.debug("异常:"+e);
		} catch (FileNotFoundException e) {
			logger.debug("异常:"+e);
		} catch (IOException e) {
			logger.debug("异常:"+e);
		} finally {
			if(fin != null) fin.close();  
            if(out != null) out.close();  
            if(f != null) f.delete(); // 删除临时文件
		}
    }
    /**
     * 读取图片转换成base64字符串
     * @param imagePath
     * @return
     * @throws IOException 
     */
    public static String getImageStr(String imagePath) throws IOException {
        InputStream in = null;
        byte[] data = null;
          in = new FileInputStream(imagePath);
          data = new byte[in.available()];
          in.read(data);
          in.close();
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
      }
    /**
     * 读取多个图片转换成base64的集合
     * @param imagePath
     * @return
     * @throws IOException 
     */
    public static List<String> getImageStrs(List<String> imagePaths) throws IOException{
        List<String> images = new ArrayList<String>();
        for(String imagePath : imagePaths){
            images.add(getImageStr(imagePath));
        }
        return images;
    }
    private static Logger logger = LoggerFactory.getLogger(DocumentHandler.class);
}
