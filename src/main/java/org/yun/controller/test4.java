package org.yun.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName test4
 * @Author 芸
 * @Date 2020/3/20 17:15
 * @Description TODO
 **/
public class test4 {

    private static String srcPath;

    @Test
    public void test() {
//        System.out.println("删除开始");
//        srcPath = "E:\\image\\232";
//        deleteDir(srcPath);
//        System.out.println("删除结束");
        System.out.println(test4.class);
        System.out.println(this.getClass());
        System.out.println("getClass() = " + getClass());
    }

    /**
     * 迭代删除文件夹
     *
     * @param dirPath 文件夹路径
     * @return
     */
    public static void deleteDir(String dirPath) {

        File file = new File(dirPath);
        if (file.isFile()) {
            file.delete();
        } else {
            File[] files = file.listFiles();// listFiles是获取该目录下所有文件和目录的绝对路径
            if (files.length == 0) {
                file.delete();
            } else {
                for (int i = 0; i < files.length; i++) {
                    deleteDir(files[i].getAbsolutePath());
                }
                file.delete();
            }
        }
    }

    public static void main(String[] args) {
        //获取当前时间
        DateTime date = DateUtil.date();
        //当前时间
        DateTime date1 = DateUtil.date(Calendar.getInstance());
        //当前时间
        Date date3 = DateUtil.date(System.currentTimeMillis());
        //当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();
        //当前日期字符串，格式：yyyy-MM-dd
        String today = DateUtil.today();

         /*date = 2020-03-27 11:02:54
        date1 = 2020-03-27 11:02:54
        date3 = 2020-03-27 11:02:54
        now = 2020-03-27 11:02:54*/
        System.out.println("date = " + date);
        System.out.println("date1 = " + date1);
        System.out.println("date3 = " + date3);
        System.out.println("now = " + now);


        String s = DateUtil.formatDateTime(new Date());
        System.out.println("s = " + s);

        //yyyy-MM-dd HH:mm:ss
        String format = DateUtil.format(new Date(), "yyyy-MM-dd");
        System.out.println("format = " + format);

        //解析
        DateTime dateTime = DateUtil.parse(format);
        System.out.println("dateTime = " + dateTime);


    }

    //和一开始的一样
//    public static void main(String[] args) {
//        //用户输入路径
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入路径:");
//        //创建File对象接收路径
//        File file = new File(scanner.nextLine());
//        delete(file);
//    }
//
//    public static void delete(File file) {
//        //判断是否存在此文件
//        if (file.exists()) {
//            //判断是否是文件夹
//            if (file.isDirectory()) {
//                File[] files = file.listFiles();
//                //判断文件夹里是否有文件
//                if (files.length >= 1) {
//                    //遍历文件夹里所有子文件
//                    for (File file1 : files) {
//                        //是文件，直接删除
//                        if (file1.isFile()) {
//                            file1.delete();
//                            System.out.println("成功删除：" + file1.getAbsolutePath());
//                        } else {
//                            //是文件夹，递归
//                            delete(file1);
//                        }
//                    }
//                    //file此时已经是空文件夹
//                    file.delete();
//                    System.out.println("成功删除：" + file.getAbsolutePath());
//                } else {
//                    //是空文件夹，直接删除
//                    file.delete();
//                    System.out.println("成功删除：" + file.getAbsolutePath());
//                }
//            } else {
//                //是文件，直接删除
//                file.delete();
//                System.out.println("成功删除：" + file.getAbsolutePath());
//            }
//        } else {
//            System.out.println("文件不存在");
//        }
//    }


//    //只是会删除,该文件夹里面的所有文件.但是子文件夹还是存在的
//    public static void main(String[] args) {
//        String fileRoot = "E:\\image4";
//        delFolder(fileRoot);
//        System.out.println("deleted");
//    }
//
//    //	// 删除完文件后删除文件夹
////	// param folderPath 文件夹完整绝对路径
//    public static void delFolder(String folderPath) {
//        try {
//            delAllFile(folderPath); // 删除完里面所有内容
//            //不想删除文佳夹隐藏下面
////			String filePath = folderPath;
////			filePath = filePath.toString();
////			java.io.File myFilePath = new java.io.File(filePath);
////			myFilePath.delete(); // 删除空文件夹
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // 删除指定文件夹下所有文件
//    // param path 文件夹完整绝对路径
//    public static boolean delAllFile(String path) {
//        boolean flag = false;
//        File file = new File(path);
//        if (!file.exists()) {
//            return flag;
//        }
//        if (!file.isDirectory()) {
//            return flag;
//        }
//        String[] tempList = file.list();
//        File temp = null;
//        for (int i = 0; i < tempList.length; i++) {
//            if (path.endsWith(File.separator)) {
//                temp = new File(path + tempList[i]);
//            } else {
//                temp = new File(path + File.separator + tempList[i]);
//            }
//            if (temp.isFile()) {
//                temp.delete();
//            }
//            if (temp.isDirectory()) {
//                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
////				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
//                flag = true;
//            }
//        }
//        return flag;
//    }


}
