package org.yun.controller;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

public class TestMy {
    private static final Logger log = LoggerFactory.getLogger(TestMy.class);

    public static void main(String[] args) {
        // 路径 这里写一个路径进去
        String srcPath = "E:\\image\\232";
//		String srcPath = "E:\\image\\23";
        String dirPath = "E:\\image";
        String testPath = "E:\\image2";
        // 调用方法
//		 getFilesName(srcPath);

        //全部删除 迭代的那种
//		 deleteFile(srcPath, true);
        deleteDir(srcPath);

        /*
         * 有图片的处理 QueryViolationImageResponse response = new
         * QueryViolationImageResponse();
         *
         * List<String> imageNamesList = getFilesName(srcPath, "23");
         *
         * if (imageNamesList != null && !imageNamesList.isEmpty()) {// 有图片
         *
         * QueryViolationImageResponse response1 = cachedProcess(
         * imageNamesList, response, srcPath);
         *
         * log.info("返回内容--->response1 ：" + response1); } else { // TODO 没图片 }
         */

        // 获取文件夹最后一次修改时间[此处，不管是因为，  修改时间即是创建时间---业务需求]
//		 String ctime = getLastUpdateTime(srcPath);
//		 System.out.println(ctime);


//		System.out.println(getCreateTime1(srcPath));

        // 获取所有子文件夹名称和创建事件
        /**
         *  key：23  value：2020-01-21
         key：24  value：2020-01-31
         */
//		 getFileDirNameAndCreateTime(dirPath);

        // 深度删除
        // deleteFile(testPath,true);
        // deleteDir(testPath);

        // 绝对路径
        // File file = new File(testPath);
        // File[] files = file.listFiles();//listFiles是获取该目录下所有文件和目录的绝对路径
        // for (int i = 0; i < files.length; i++) {
        // System.out.println(files[i]);
        // }

        //异步编程
//		System.out.println("date1:" + new Date());
//		String s = add();
//		System.out.println("date2:" + new Date());
//		System.out.println(s);

        //测试枚举
//		 System.out.println(Color.getName(1));


    }

    public enum Color {
        RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
        // 成员变量
        private String name;
        private int index;

        // 构造方法
        private Color(String name, int index) {
            this.name = name;
            this.index = index;
        }

        // 普通方法
        public static String getName(int index) {
            for (Color c : Color.values()) {
                if (c.getIndex() == index) {
                    return c.name;
                }
            }
            return null;
        }

        // get set 方法
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    public static String add() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Future future1 = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return select1();
            }
        });
        Future future2 = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return select2();
            }
        });
        String s1 = (String) future1.get();
        String s2 = (String) future2.get();
        String s = s1 + s2;
        return s;
    }

    public static String select1() throws InterruptedException {
        Date date = new Date();
        System.out.println("s1kaishi");
        System.out.println("date:" + date);
//		Thread.currentThread().sleep(20000);
        return "select1";
    }

    public static String select2() throws InterruptedException {
        Date date = new Date();
        System.out.println("s2kaishi");
        System.out.println("date:" + date);
//		Thread.currentThread().sleep(10000);
        return "select2";
    }

    /**
     * @Description: 获取所有某目录下--文件夹名称 和 该文件夹创建时间
     * @author Lucky
     * @date 2020年1月31日 String srcPath = "E:\\image\\23";
     * // key为文件夹名字(String类型)，value为创建时间    eg:   key：23  value：2020-01-21
     */
    public static Map getFileDirNameAndCreateTime(String srcPath) {
        Map<String, String> map = new HashMap<String, String>();
        File dirFile = new File(srcPath);
        if (dirFile.exists()) {
            File[] files = dirFile.listFiles();
            if (files != null) {
                for (File fileChildDir : files) {
                    // 输出文件夹名
                    if (fileChildDir.isDirectory()) {
                        // System.out.println(fileChildDir + " :  此为目录名" );
                        // System.out.println(getCreateTime(fileChildDir.getAbsolutePath())
                        // + "创建时间");
                        // System.out.println(fileChildDir.getName());
                        map.put(fileChildDir.getName(),
                                getLastUpdateTime(fileChildDir.getAbsolutePath()));// key为文件夹名字(String类型)，value为创建时间
                        System.out.println("key：" + fileChildDir.getName()
                                + "  value：" + map.get(fileChildDir.getName()));
                    }
                }
            }
        } else {
            System.out.println("你想查找的文件不存在");
        }
        return map;
    }

    /**
     * @Description: 获取 某文件夹（ 绝对路径）的创建时间[其实是，修改时间]
     * 获取文件夹最后一次修改时间[此处，不管是因为，  修改时间即是创建时间---业务需求]
     * @author Lucky
     * @date 2020年1月31日 String srcPath = "E:\\image\\23";
     */
    public static String getLastUpdateTime(String srcPath) {
        File file = new File(srcPath);
        long time = file.lastModified();// 返回文件最后修改时间，是以个long型毫秒数

//		System.out.println("--试验：" + new Date(time).toLocaleString());//--试验：2020-2-4 10:39:47

        // String ctime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new
        // Date(time));
        String ctime = new SimpleDateFormat("yyyy-MM-dd")
                .format(new Date(time));
        return ctime;
    }


    /**
     * 读取文件创建时间
     * 看情况使用，两种都可以
     * 此法，是读取，文件夹下   文件的创建时间
     */
    public static String getCreateTime1(String srcPath) {
        String strTime = null;
        try {
            Process p = Runtime.getRuntime().exec("cmd /C dir "
                    + srcPath
                    + "/tc");
            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.endsWith(".jpg")) {
//                    strTime = line.substring(0,17);
                    strTime = line.substring(0, 10);
                    return strTime;
//                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("创建时间    " + strTime);  //输出：创建时间  2009-08-17        2009-08-17  10:21

        return strTime;
    }


    /**
     * @param srcPath 绝对路径 isForceDelete是否强制删除子文件夹（Java --但凡，该文件夹有子文件 不允许删除）
     * @Description: 删除该文件夹（深度删除）
     * @author Lucky
     * @date 2020年1月31日
     */
    public static void deleteFile(String srcPath, boolean isForceDelete) {
        File file = new File(srcPath);
        if (file.exists()) {
            file.delete();
        }
        if (file.exists()) {
            if (!isForceDelete) {
                return;
            }
            String[] paths = file.list();// //获取该目录下的所有文件和目录的文件名
            for (String str : paths) {
                // System.out.println("str》》》》》》》》》" + str);
                deleteFile(srcPath + "\\" + str, isForceDelete);
            }
            file.delete();
            paths = null; // lets gc do its works
        }
        file = null; // lets gc do its works
    }

    /**
     * 迭代删除文件夹
     *
     * @param dirPath 文件夹路径
     */
    public static void deleteDir(String dirPath) {
        File file = new File(dirPath);
        if (file.isFile()) {
            file.delete();
        } else {
            File[] files = file.listFiles();// listFiles是获取该目录下所有文件和目录的绝对路径
            if (files == null) {
                file.delete();
            } else {
                for (int i = 0; i < files.length; i++) {
                    deleteDir(files[i].getAbsolutePath());
                }
                file.delete();
            }
        }
    }

    @Test
    public void testEasy() throws Exception {
        Timestamp startTime = new Timestamp(new Date().getTime());
        // Date date = new Date();
        System.err.println("startTime--->" + startTime);

        long startTime2 = startTime.getTime();// 将时间戳转换为毫秒数
        // long nowTime = new Date().getTime();//获取当前系统时间的毫秒数
        System.out.println("startTime2---->" + startTime2);

        long endTime = startTime2 + (7 * 24 * 60 * 60 * 1000L);// L 防溢出
        // long endTime = startTime2 - (7 * 24 * 60 * 60 * 1000L);//L 防溢出
        System.out.println("endTime 加上7天--->" + endTime);
        System.out.println(new Date(endTime));
    }

    @Test
    public void getOldTime() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd "); // 日期格式

        Date now = new Date();
        System.out.println("当前时间：" + now);
        System.out.println("当前时间戳：" + now.getTime());
        // 使用Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        Date oldDate = calendar.getTime();
        System.out.println("7天前：" + oldDate);
        long oldMillis = calendar.getTimeInMillis();
        System.out.println("7天前时间戳：" + oldMillis);
        // 使用时间戳减毫秒数---第二种方式
        long oldMillis2 = now.getTime() - 7 * 24 * 60 * 60 * 1000L;
        System.out.println("7天前时间戳：" + oldMillis2);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");// 年与日
        // SimpleDateFormat format = new
        // SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//年月日,时分秒
        String format1 = format.format(oldMillis2);
        Date date = format.parse(format1);// 转换
        System.out.println("7天前日期-年月日:" + date);

        System.out.println("格式化后--->" + dateFormat.format(date));// 返回时间日期

    }


    /***
     * url 链接 获取返回值 通过url 读取文件 为byte字节
     *
     * @param srcPath
     *            --文件绝对路径
     * @param size
     *            byte缓存大小
     * @return url请求结果
     */
    public static byte[] bufferStreamForByte(String srcPath, int size) {
        byte[] content = null;
        try {
            BufferedInputStream bis = null;
            ByteArrayOutputStream out = null;
            try {
                FileInputStream input = new FileInputStream(srcPath);
                bis = new BufferedInputStream(input, size);
                byte[] bytes = new byte[size];
                int len;
                out = new ByteArrayOutputStream();
                while ((len = bis.read(bytes)) > 0) {
                    out.write(bytes, 0, len);
                }

                bis.close();
                // bos.flush();
                // bos.close();
                content = out.toByteArray();
            } finally {
                if (bis != null)
                    bis.close();
                if (out != null)
                    out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("获取图片错误");
            log.error("获取图片错误" + e.getMessage());
        }
        return content;

    }

    /**
     * 读取二进制文件并且写入数组里
     *
     * @param filePath
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static byte[] getBytes4File(String filePath) throws IOException {

        InputStream in = null;
        BufferedInputStream buffer = null;
        DataInputStream dataIn = null;
        ByteArrayOutputStream bos = null;
        DataOutputStream dos = null;
        byte[] bArray = null;
        try {
            in = new FileInputStream(filePath);
            buffer = new BufferedInputStream(in);
            dataIn = new DataInputStream(buffer);
            bos = new ByteArrayOutputStream();
            dos = new DataOutputStream(bos);
            byte[] buf = new byte[1024];
            while (true) {
                int len = dataIn.read(buf);
                if (len < 0)
                    break;
                dos.write(buf, 0, len);
            }
            bArray = bos.toByteArray();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("读取二进制文件并且写入数组里--出错");
        } finally {
            if (in != null)
                in.close();
            if (dataIn != null)
                dataIn.close();
            if (buffer != null)
                buffer.close();
            if (bos != null)
                bos.close();
            if (dos != null)
                dos.close();
        }
        return bArray;
    }

    /**
     * 获取某目录下所有文件名 不递归，该目录下的子文件夹
     */

    public static void getFilesName(String path) {
        File file = new File(path);
        // 如果这个路径是文件夹
        if (file.isDirectory()) {
            // 获取路径下的所有文件
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                // 如果还是文件夹 递归获取里面的文件 文件夹
                // if (files[i].isDirectory()) {
                // System.out.println("目录：" + files[i].getPath());
                // getFiles(files[i].getPath());
                // } else {
                String fileName = files[i].getPath();
                String fileNameNow = fileName.substring(fileName
                        .lastIndexOf("\\") + 1);
                System.out.println(fileNameNow);
                // System.out.println("文件名：" + files[i].getName() + ",文件路径："+
                // files[i].getPath());
                // System.out.println("文件：" + files[i].getPath());// }

            }
        } else {// 不是文件夹
            String fileName = file.getPath();
            String fileNameNow = fileName
                    .substring(fileName.lastIndexOf("\\") + 1);
            System.out.println(fileNameNow);

            System.out.println("文件名：" + file.getName() + ",文件路径："
                    + file.getPath());
        }
    }

    /**
     * 获取某目录下所有文件名 不递归，该目录下的子文件夹
     */

    public static List<String> getFilesName(String srcPath, String xh) {
        File file = new File(srcPath);
        List<String> list = new ArrayList<String>();

        // 如果这个路径是文件夹
        if (file.isDirectory()) {
            // 获取路径下的所有文件
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                log.info("文件名：" + files[i].getName() + ",文件路径："
                        + files[i].getPath());
                // System.out.println("文件名：" + files[i].getName() + ",文件路径："
                // + files[i].getPath());;
                if (files[i].getName() != null) {
                    String[] chachedXh = files[i].getPath().split("\\\\");// 名字
                    // 切割
                    // \
                    // 转义字符
                    // \\
                    // 转义
                    // \
                    // \\为/
                    // x.jpg

                    while (StringUtils.isEmpty(chachedXh[0])) {
                        continue;
                    }

                    if (xh.equals(chachedXh[2])) {

                        list.add(files[i].getName());
                        System.out.println("list：---->" + list.get(i));
                    }
                } else {
                    System.out.println("图片名字为null :" + i);
                }
            }
        } else {// 不是文件夹
            log.error("路径不对，不是文件夹");
        }
        return list;
    }

    /**
     * 处理Base64解码并写图片到指定位置
     *
     * @param base64 图片Base64数据
     * @param path   图片保存路径
     * @return
     */
    public static boolean base64ToImageFile(String base64, String path)
            throws IOException {// 对字节数组字符串进行Base64解码并生成图片
        // 生成jpeg图片
        try {
            OutputStream out = new FileOutputStream(path);
            return base64ToImageOutput(base64, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 处理Base64解码并输出流
     *
     * @param base64
     * @param out
     * @return
     */
    public static boolean base64ToImageOutput(String base64, OutputStream out)
            throws IOException {
        if (base64 == null) { // 图像数据为空
            return false;
        }
        try {
            // Base64解码
            byte[] bytes = Base64.decodeBase64(base64);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            out.write(bytes);
            out.flush();
            return true;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    /**
//     * @Description: 压缩图片到指定大小
//     * @Param: bytes 源文件 desFileSize：大小，accuracy：每次缩小几倍
//     * @return: String
//     * @Author:
//     * @Date: 2019/9/29
//     */
//    public static String commpressPicCycle(byte[] bytes, long desFileSize,
//                                           double accuracy) throws IOException {
//        long srcFileSizeJPG = bytes.length;
//        System.out.println(srcFileSizeJPG);
//        // 2、判断大小，如果小于，不压缩；如果大于等于，压缩
//        if (srcFileSizeJPG <= desFileSize * 1024) {
//            File file = new File("D://img/abc.png");
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            fileOutputStream.write(bytes);
//            return ImageToBase64(bytes.toString());
//        }
//        // 计算宽高
//        BufferedImage bim = ImageIO.read(new ByteArrayInputStream(bytes));
//        int srcWdith = bim.getWidth();
//        int srcHeigth = bim.getHeight();
//        int desWidth = new BigDecimal(srcWdith).multiply(
//                new BigDecimal(accuracy)).intValue();
//        int desHeight = new BigDecimal(srcHeigth).multiply(
//                new BigDecimal(accuracy)).intValue();
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream(); // 字节输出流（写入到内存）
//        Thumbnails.of(new ByteArrayInputStream(bytes))//将Jar包放入
//                .size(desWidth, desHeight).outputQuality(accuracy)
//                .toOutputStream(baos);
//        byte[] bytes1 = baos.toByteArray();
//        baos.close();
//        return commpressPicCycle(bytes1, desFileSize, accuracy);
//    }

    // 图片转化成base64字符串
    public static String ImageToBase64(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

}
