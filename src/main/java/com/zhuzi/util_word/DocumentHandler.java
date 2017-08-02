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
}
