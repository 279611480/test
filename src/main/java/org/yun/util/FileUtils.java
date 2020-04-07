//package org.yun.util;
//
//import java.io.*;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//
///**
// * @ClassName FileUtils
// * @Author 芸
// * @Date 2020/3/20 10:24
// * @Description TODO
// **/
//public class FileUtils {
//
//    private static final int CHECK_TIMEOUT = 8;
//
//    private static String basePath;
//    private static String wechatImagePath;
//    private static String aliImagePath;
//    private static String violationImagePath;
//    private static FileUtils ImageFileUtils;
//
//    static {
//        basePath = getBaseDir() + File.separator + "qrcode";
//        wechatImagePath = basePath + File.separator + "wechatImage";
//        aliImagePath = basePath + File.separator + "aliImage";
//        violationImagePath = getBaseDir() + File.separator + "image";
//    }
//
//    private static String getBaseDir() {
//        File directory = new File("");
//        String baseDir = "";
//        try {
//            baseDir = directory.getCanonicalPath();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        System.out.println("当前项目路径：" + baseDir);
//        return baseDir;
//    }
//
//    public static String getCurrentWechatImagePath() {
//        return wechatImagePath + File.separator + getNowDate2YYYYMMDD();
//    }
//
//    public static String getCurrentAliImagePath() {
//        return aliImagePath + File.separator + getNowDate2YYYYMMDD();
//    }
//
//    public static String getViolationImagePath() {
//        return violationImagePath;
//    }
//
//    public static String saveWechatImage(String fileName, String imageData) {
//        File dir = new File(getCurrentWechatImagePath());
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//        File imageFile = new File(dir, fileName);
//        return writeFile(imageFile, imageData);
//    }
//
//    public static String saveAliImage(String fileName, String imageData) {
//        File dir = new File(getCurrentAliImagePath());
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//        File imageFile = new File(dir, fileName);
//        return writeFile(imageFile, imageData);
//    }
//
//    private static String writeFile(File file, String data) {
//        BufferedWriter bw = null;
//
//        try {
//            bw = new BufferedWriter(new FileWriter(file));
//            bw.write(data);
//            bw.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                bw.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return file.getAbsolutePath();
//    }
//
//    public static boolean isWechatImageExist(String jdsbh) {
//        File dir = new File(getCurrentWechatImagePath());
//        if (!dir.exists()) {
//            return false;
//        }
//        return new File(dir, jdsbh).exists();
//    }
//
//    public static boolean isAliImageExist(String jdsbh) {
//        File dir = new File(getCurrentAliImagePath());
//        if (!dir.exists()) {
//            return false;
//        }
//        return new File(dir, jdsbh).exists();
//    }
//
//    public static String getWechatImageData(String jdsbh) {
//        File imageFile = new File(getCurrentWechatImagePath() + File.separator + jdsbh);
//        if (checkImageTimeOut(imageFile)) {
//            return null;
//        }
//        return readFile(imageFile);
//    }
//
//    public static String getAliImageData(String jdsbh) {
//        File imageFile = new File(getCurrentAliImagePath() + File.separator + jdsbh);
//        if (checkImageTimeOut(imageFile)) {
//            return null;
//        }
//        return readFile(imageFile);
//    }
//
//    private static boolean checkImageTimeOut(File imageFile) {
//        return System.currentTimeMillis() - imageFile.lastModified() > CHECK_TIMEOUT * 60 * 1000;
//    }
//
//    private static String readFile(File file) {
//        String fileData = "";
//        BufferedReader br = null;
//        try {
//            br = new BufferedReader(new FileReader(file));
//            String line = null;
//            while ((line = br.readLine()) != null) {
//                fileData += line;
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                br.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return fileData;
//    }
//
//    /**
//     * 获取系统当前日期 格式: yyyyMMdd
//     */
//    public static String getNowDate2YYYYMMDD() {
//        return new SimpleDateFormat("yyyyMMdd").format(new Date());
//    }
//
//    /**
//     * @return the basePath
//     */
//    public static String getBasePath() {
//        return basePath;
//    }
//
//    /**
//     * @param basePath the basePath to set
//     */
//    public static void setBasePath(String basePath) {
//        ImageFileUtils.basePath = basePath;
//    }
//
//    /**
//     * @return the wechatImagePath
//     */
//    public static String getWechatImagePath() {
//        return wechatImagePath;
//    }
//
//    /**
//     * @param wechatImagePath the wechatImagePath to set
//     */
//    public static void setWechatImagePath(String wechatImagePath) {
//        ImageFileUtils.wechatImagePath = wechatImagePath;
//    }
//
//    /**
//     * @return the aliImageData
//     */
//    public static String getAliImagePath() {
//        return aliImagePath;
//    }
//
//    /**
//     * @param
//     */
//    public static void setAliImagePath(String aliImagePath) {
//        ImageFileUtils.aliImagePath = aliImagePath;
//    }
//
//    public static void main(String[] args) {
//        System.out.println(ImageFileUtils.getBasePath());
//        System.out.println(ImageFileUtils.getWechatImagePath());
//        System.out.println(ImageFileUtils.getAliImagePath());
//        System.out.println(ImageFileUtils.getCurrentWechatImagePath());
//        System.out.println(ImageFileUtils.getCurrentAliImagePath());
////        System.out.println(ImageFileUtils.saveWechatImage("123456", "EJNBGFT"));
////        System.out.println(ImageFileUtils.saveWechatImage("1234567.txt", "ASDFGHJKL"));
////        System.out.println(ImageFileUtils.isWechatImageExist("123456"));
////        System.out.println(ImageFileUtils.getWechatImageData("1234567.txt"));
////        System.out.println(ImageFileUtils.saveWechatImage("53201905008", new Date().toString()));
//        long startTime = System.currentTimeMillis();
//        System.out.println(ImageFileUtils.isWechatImageExist("53201905008"));
//        long endTime = System.currentTimeMillis();
//        System.out.println("耗时" + (endTime - startTime));
//
//        long startTime2 = System.currentTimeMillis();
//        System.out.println(ImageFileUtils.isWechatImageExist("53201905009"));
//        long endTime2 = System.currentTimeMillis();
//        System.out.println("耗时" + (endTime - startTime));
//
//        long startTime3 = System.currentTimeMillis();
//        File imageFile = new File(getCurrentWechatImagePath() + File.separator + "53201905009");
//        System.out.println(ImageFileUtils.checkImageTimeOut(imageFile));
//        long endTime3 = System.currentTimeMillis();
//        System.out.println("耗时" + (endTime - startTime));
////        System.out.println(ImageFileUtils.getWechatImageData("53201905008"));
//    }
//
//}
//
//
