package org.yun.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lucky
 * @ClassName: CacheUtils
 * @Description: 缓存以及图片处理
 * @date 2020年1月19日
 */
public class CacheUtils {

    /**
     * @Description: 获取所有某目录下--文件夹名称 和 该文件夹创建时间
     * @author Lucky
     * @date 2020年1月31日 String srcPath = "E:\\image\\23"; key为文件夹名字(String类型)，value为创建时间 eg: key：23
     * value：2020-01-21
     */
    public static Map<String, String> getFileDirNameAndCreateTime(String srcPath) {
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
                        map.put(fileChildDir.getName(), getLastUpdateTime(fileChildDir.getAbsolutePath()));// key为文件夹名字(String类型)，value为创建时间
                        System.out.println("key：" + fileChildDir.getName() + "  value："
                                + map.get(fileChildDir.getName()));
                    }
                }
            }
        } else {
            System.out.println("你想查找的文件不存在");
        }
        return map;
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

    /**
     * @Description: 获取 某文件夹（ 绝对路径）的创建时间[其实是，修改时间] 获取文件夹最后一次修改时间[此处，不管是因为， 修改时间即是创建时间---业务需求]
     * @author Lucky
     * @date 2020年1月31日 String srcPath = "E:\\image\\23";
     */
    public static String getLastUpdateTime(String srcPath) {
        File file = new File(srcPath);
        long time = file.lastModified();// 返回文件最后修改时间，是以个long型毫秒数

        // System.out.println("--试验：" + new Date(time).toLocaleString());//--试验：2020-2-4 10:39:47

        // String ctime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new
        // Date(time));
        String ctime = new SimpleDateFormat("yyyy-MM-dd").format(new Date(time));
        return ctime;
    }

    /**
     * 将图片压缩到指定大小以内
     *
     * @param srcImgData 源图片数据
     * @param maxSize    目的图片大小
     * @return 压缩后的图片数据
     */
    public static byte[] compressUnderSize(byte[] srcImgData, long maxSize) {
        double scale = 0.9;
        byte[] imgData = Arrays.copyOf(srcImgData, srcImgData.length);

        if (imgData.length > maxSize) {
            do {
                try {
                    imgData = compress(imgData, scale);

                } catch (IOException e) {
                    throw new IllegalStateException("压缩图片过程中出错，请及时联系管理员！", e);
                }

            } while (imgData.length > maxSize);
        }

        return imgData;
    }

    /**
     * 按照 宽高 比例压缩
     *
     * @param srcImgData 待压缩图片输入流
     * @param scale      压缩刻度
     * @return 压缩后图片数据
     * @throws IOException 压缩图片过程中出错
     */
    public static byte[] compress(byte[] srcImgData, double scale) throws IOException {
        BufferedImage bi = ImageIO.read(new ByteArrayInputStream(srcImgData));
        int width = (int) (bi.getWidth() * scale); // 源图宽度
        int height = (int) (bi.getHeight() * scale); // 源图高度

        Image image = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = tag.getGraphics();
        g.setColor(Color.RED);
        g.drawImage(image, 0, 0, null); // 绘制处理后的图
        g.dispose();

        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        ImageIO.write(tag, "JPEG", bOut);

        return bOut.toByteArray();
    }

    /**
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @param path 图片路径
     * @return base64字符串
     */
    public static String imageToBase64(String path) throws IOException {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        InputStream in = null;
        try {
            in = new FileInputStream(path);
            data = new byte[in.available()];
            in.read(data);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return org.apache.commons.codec.binary.Base64.encodeBase64String(data);// 返回Base64编码过的字节数组字符串
    }

    /**
     * 处理Base64解码并写图片到指定位置
     *
     * @param base64 图片Base64数据
     * @param path   图片保存路径
     * @return
     */
    public static boolean base64ToImageFile(String base64, String path) throws IOException {// 对字节数组字符串进行Base64解码并生成图片
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
    public static boolean base64ToImageOutput(String base64, OutputStream out) throws IOException {
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

    /**
     * 长高等比例缩小图片
     *
     * @param srcImagePath 读取图片路径
     * @param toImagePath  写入图片路径
     * @param ratio        缩小比例
     * @throws IOException
     */
    public static void reduceImageEqualProportion(String srcImagePath, String toImagePath, int ratio)
            throws IOException {
        FileOutputStream out = null;
        try {
            // 读入文件
            File file = new File(srcImagePath);
            // 构造Image对象
            BufferedImage src = javax.imageio.ImageIO.read(file);
            int width = src.getWidth();
            int height = src.getHeight();
            // 缩小边长
            BufferedImage tag = new BufferedImage(width / ratio, height / ratio,
                    BufferedImage.TYPE_INT_RGB);
            // 绘制 缩小 后的图片
            tag.getGraphics().drawImage(src, 0, 0, width / ratio, height / ratio, null);
            out = new FileOutputStream(toImagePath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }


    /***
     * url 链接 获取返回值 通过url 读取文件 为byte字节
     *
     * @param srcPath
     *          --文件绝对路径
     * @param size
     *          byte缓存大小
     * @return url请求结果
     * @throws
     */
    private byte[] bufferStreamForByte(String srcPath, int size) {
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
        }
        return content;

    }

    /**
     * 解压缩tar.gz文件
     *
     * @param backupFile
     * @param path
     */
    public static void unTarGz(String backupFile, String path) throws IOException {
        String commMand = "tar -zxvf ${backupFile} -C ${path}";
        Runtime rt = Runtime.getRuntime();
        Process proc = rt.exec(commMand);
        InputStream inputStream = proc.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            String line = reader.readLine();
            if (line == null)
                break;
        }
    }


//    /**
//     * 获取某目录下所有文件
//     */
//    private List<File> getFilesName(String srcPath) {
//
//        logger.info("保存文件名：" + srcPath);
//        File file = new File(srcPath);
//        List<File> list = new ArrayList<File>();
//
//        if (!file.exists()) { // 判断文件目录是否存在
//
//            file.mkdirs();
//            return list;
//        }
//
//        // 如果这个路径是文件夹
//        if (file.isDirectory()) {
//
//            // 获取路径下的所有文件
//            File[] files = file.listFiles();
//
//            list.addAll(Arrays.asList(files));
//        }
//        return list;
//    }
//
//    private void processCachedImg(List<File> imageFileList, QueryViolationImageResponse response)
//            throws TransServiceException {
//
//        for (File imageFile : imageFileList) {
//
//            try {
//                String absolutePath = imageFile.getAbsolutePath();
//
//            } catch (Exception e) {
//
//                throw new Exception(e.getMessage());
//            }
//        }
//    }


}
