package com.video.util;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class Base64ImageDecoder {
    public static String saveImage(String base64Image, String outputDirectory) {
        try {
            // 解码Base64格式的图片数据
//            byte[] imageBytes = Base64.getDecoder().decode(base64Image.split(",")[1]);
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);

            // 将字节数组转换为BufferedImage
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));

            // 获取目标目录
            File directory = new File(outputDirectory);
            if (!directory.exists()) {
                directory.mkdirs(); // 如果目录不存在，则创建它
            }

            // 获取目录下已存在的文件数量
            int fileCount = directory.listFiles((dir, name) -> name.startsWith("image")).length;

            // 创建输出文件路径
            String outputFilePath = outputDirectory + "image" + (fileCount + 1) + ".png";
            File outputFile = new File(outputFilePath);

            // 将BufferedImage保存为文件
            ImageIO.write(bufferedImage, "png", outputFile);

            System.out.println("图片保存成功：" + outputFilePath);

            // 返回保存的文件名
            return outputFile.getName();
        } catch (IOException e) {
            System.err.println("图片保存失败：" + e.getMessage());
            return null;
        }
    }
    }

