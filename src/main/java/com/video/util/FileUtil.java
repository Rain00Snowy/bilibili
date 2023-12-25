package com.video.util;

import java.io.File;

public class FileUtil {

    // 删除指定目录下的所有文件和目录。
    public void deleteAllFilesOfDir(File file) {
        if (null != file) {
            // 检查文件或目录是否存在，如果不存在则直接返回。
            if (!file.exists())
                return;

            // 如果是文件，尝试删除它。
            if (file.isFile()) {
                boolean result = file.delete();
                int tryCount = 0;

                // 如果删除失败，尝试多次删除，最多10次。
                while (!result && tryCount++ < 10) {
                    System.gc(); // 调用垃圾回收器，试图释放文件锁定。
                    result = file.delete();
                }
            }

            // 如果是目录，递归删除其中的所有文件。
            File[] files = file.listFiles();
            if (null != files) {
                for (int i = 0; i < files.length; i++) {
                    deleteAllFilesOfDir(files[i]);
                }
            }

            // 删除目录本身。
            file.delete();
        }
    }

    // 根据给定的路径创建一个File对象。
    public File getFile(String path) {
        return new File(path);
    }
}
