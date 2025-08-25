package com.tiv.card.audit.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.zip.*;

/**
 * zip工具类
 */
@Slf4j
@Component
public class ZipUtil {

    static final int BUFFER_SIZE = 4 << 10;

    /**
     * 压缩指定目录下的所有文件
     *
     * @param dir
     * @param zipPath
     * @param destZipName
     * @throws IOException
     */
    public void zip(String dir, String zipPath, String destZipName) throws IOException {
        File file = new File(dir);
        // 1.为zip文件创建目录
        String path = file.getParentFile().getAbsolutePath();
        if (StringUtils.isEmpty(destZipName)) {
            destZipName = file.getName() + ".zip";
        }
        if (StringUtils.isEmpty(zipPath)) {
            zipPath = path;
        }
        String zipOut = zipPath + File.separator + destZipName;
        File zipOutFile = new File(zipOut);
        if (!zipOutFile.getParentFile().exists()) {
            zipOutFile.getParentFile().mkdirs();
        }

        // 为zip文件创建目录
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipOut));) {
            if (!file.isDirectory()) zipOutputStream.close();
            File[] subFiles = file.listFiles();
            if (subFiles != null) {
                for (File subFile : subFiles) {
                    buildZipDir(zipOutputStream, subFile, null);
                }
            }
        } catch (IOException e) {
            throw new IOException(String.format("压缩文件失败,错误信息:%s", e.getMessage()));
        }
    }

    private void buildZipDir(ZipOutputStream zipOut, File file, String baseDir) throws IOException {
        if (file.isFile()) {
            String zipEntryName = baseDir == null ? file.getName() : baseDir + File.separator + file.getName();
            zipOut.putNextEntry(new ZipEntry(zipEntryName));
            byte[] buffer = new byte[BUFFER_SIZE];
            try (InputStream inputStream = new FileInputStream(file)) {
                var len = -1;
                while ((len = inputStream.read(buffer)) != -1) {
                    zipOut.write(buffer, 0, len);
                }
                zipOut.flush();
                zipOut.closeEntry();
            }
        } else {
            // 文件是dir,继续递归找文件
            File[] subFiles = file.listFiles();
            if (subFiles.length == 0) {
                // 处理空文件夹
                String zipName = baseDir == null ? file.getName() : baseDir + File.separator + file.getName();
                zipOut.putNextEntry(new ZipEntry(zipName));
                zipOut.flush();
                zipOut.closeEntry();
            } else {
                for (File subFile : subFiles) {
                    String subBaseDir = baseDir == null ? file.getName() : baseDir + File.separator + file.getName();
                    buildZipDir(zipOut, subFile, subBaseDir);
                }
            }
        }
    }

    /**
     * 解压zip文件
     *
     * @param zipPath
     * @param unzipPath
     * @throws ZipException
     * @throws IOException
     */
    public void unZip(String zipPath, String unzipPath) throws ZipException, IOException {
        File file = new File(zipPath);
        try (
                ZipFile zipFile = new ZipFile(file);
                ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));) {
            if (StringUtils.isEmpty(unzipPath)) {
                unzipPath = zipPath.replace(".zip", "");
            }
            ZipEntry nextEntry;
            while ((nextEntry = zipInputStream.getNextEntry()) != null) {
                String name = nextEntry.getName();
                String fileOutPath = unzipPath + File.separator + name;
                File fileOut = new File(fileOutPath);
                if (!fileOut.getParentFile().exists()) {
                    fileOut.getParentFile().mkdirs();
                }
                if (!fileOut.exists()) {
                    fileOut.createNewFile();
                }
                try (OutputStream output = new FileOutputStream(fileOutPath);
                     InputStream inputStream = zipFile.getInputStream(nextEntry)) {
                    var read = -1;
                    byte[] buffer = new byte[BUFFER_SIZE];
                    while ((read = inputStream.read(buffer)) != -1) {
                        output.write(buffer, 0, read);
                    }
                } catch (IOException e) {
                    throw new IOException(String.format("解压zip文件失败,流处理异常,错误信息:%s", e.getMessage()));
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(String.format("解压zip文件失败,找不到指定文件,错误信息:%s", e.getMessage()));
        } catch (ZipException e) {
            throw new ZipException(String.format("解压zip文件失败,错误信息:%s", e.getMessage()));
        }
    }

}