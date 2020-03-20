package org.yun.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yun.entity.User;
import sun.nio.ch.IOUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * @ClassName Test4
 * @Author 芸
 * @Date 2020/3/13 11:01
 * @Description TODO
 **/
@RequestMapping("/export")
@ResponseBody
public class export {

    public void expord(HttpServletResponse response) throws Exception {

//        Timestamp timestamp = new Timestamp(new Date().getTime());//.var
//        System.out.println("timestamp = " + timestamp);//timestamp.soutf

        ArrayList<User> userArrayList = new ArrayList<User>();
        Collections.addAll(userArrayList, new User("张飞", 12, "男", new Date(),new Timestamp(new Date().getTime())),
                new User("关羽", 12, "男", new Date(),new Timestamp(new Date().getTime())),
                new User("刘备", 12, "男", new Date(),new Timestamp(new Date().getTime())),
                new User("曹操", 12, "男", new Date(),new Timestamp(new Date().getTime())),
                new User("夏侯惇", 12, "男", new Date(),new Timestamp(new Date().getTime())),
                new User("夏侯渊", 12, "男", new Date(),new Timestamp(new Date().getTime())),
                new User("郭嘉", 12, "男", new Date(),new Timestamp(new Date().getTime())),
                new User("司马懿", 12, "男", new Date(),new Timestamp(new Date().getTime())),
                new User("诸葛亮", 12, "男", new Date(),new Timestamp(new Date().getTime())),
                new User("荀彧", 12, "男", new Date(),new Timestamp(new Date().getTime())),
                new User("典韦", 12, "男", new Date(),new Timestamp(new Date().getTime())),
                new User("许褚", 12, "男", new Date(),new Timestamp(new Date().getTime())),
                new User("曹仁", 12, "男", DateUtil.date(new Date()),new Timestamp(new Date().getTime()))
        );

        /*通过工具类创建writer,默认创建xls格式*/
        ExcelWriter writer = new ExcelWriter();

        /*自定义标题提名*/
        writer.addHeaderAlias("name","姓名");
        writer.addHeaderAlias("age","年龄");
        writer.addHeaderAlias("sex","性别");
        writer.addHeaderAlias("date","时间");
        writer.addHeaderAlias("date1","时间戳long");

        /*合并单元格后的标题行,使用默认标题行*/
        writer.merge(1,"登记人员信息");

        /*一次性写出内容,使用默认样式,强制输出标题*/
        writer.write(userArrayList,true);

        /*response为HttpServletResponse对象*/
        response.setContentType( "application/vnd.ms-excel;charset=utf-8" );

        /* test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码 */
        //生成文件名
        String name = StringToUTF8( "登记信息" );

        response.setHeader( "Content-Disposition", "attachment;filename=" + name + ".xls" );

        /*out为OutputStream需要写出到的目标流*/
        ServletOutputStream out = null;

        try {
            out = response.getOutputStream();
            writer.flush(out,true);
        } catch ( IOException e ) {
            e.printStackTrace();
        }finally {
            //无论如何都会到这里执行
            //关闭writer  释放内存
            writer.close();
        }
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }

    // 对get方式过来的中文参数的乱码进行转码
    public static String StringToUTF8(String param) throws Exception {
        return new String(param.getBytes("ISO-8859-1"), "UTF-8");
    }
}
