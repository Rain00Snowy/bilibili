package com.video.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class Base64Util {
    public static String imageToBase64(String imagePath) {
        try {
            String basePath = "src/main/resources";
            Path videoPath2 = Paths.get(basePath, imagePath);
            System.out.println(videoPath2);
            File imageFile = new File(videoPath2.toUri());
            byte[] imageData = Files.readAllBytes(imageFile.toPath());
            return Base64.getEncoder().encodeToString(imageData);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String videoToBase64(String videoPath) {
        try {
            String basePath = "src/main/resources";
            Path videoPath2 = Paths.get(basePath, videoPath);
            File videoFile = new File(videoPath2.toUri());
            System.out.println(videoPath2);
            byte[] videoData = Files.readAllBytes(videoFile.toPath());
            return Base64.getEncoder().encodeToString(videoData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void base64ToImage(String base64Data, String outputImagePath) {
        try {
            byte[] imageData = Base64.getDecoder().decode(base64Data);
            try (OutputStream stream = new FileOutputStream(outputImagePath)) {
                stream.write(imageData);
            }
            System.out.println("成功将Base64编码的数据转换为图像：" + outputImagePath);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("转换Base64编码的数据到图像时发生错误：" + e.getMessage());
        }
    }

    public static void base64ToVideo(String base64Data, String outputVideoPath) {
        try {
            byte[] videoData = Base64.getDecoder().decode(base64Data);
            try (OutputStream stream = new FileOutputStream(outputVideoPath)) {
                stream.write(videoData);
            }
            System.out.println("成功将Base64编码的数据转换为视频：" + outputVideoPath);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("转换Base64编码的数据到视频时发生错误：" + e.getMessage());
        }
    }

    public static void main(String[] args) {
//         示例用法:
//        String imagePath = "/video/getVideoImage/80840748_cover.jpg";
//        String imageBase64 = imageToBase64(imagePath);
//        System.out.println(imageBase64);
//        base64ToImage(imageBase64, "src/main/resources/static/img/test02.jpg");
//
        String videoFileName = "/static/video/20210526152900.mp4";
        String videoBase64 = videoToBase64(videoFileName);
        System.out.println("视频Base64: " + videoBase64);
        base64ToVideo(videoBase64,"src/main/resources/static/video/testtt.mp4");
    }
}
