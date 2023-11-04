/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


public class ModelFile {
    public boolean compressTo(String pathSrc, String fileZipName, String pathCompress) {
        try {
            FileOutputStream fos = new FileOutputStream(new File(pathCompress, fileZipName));
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            File fileToZip = new File(pathSrc);

            zipFile(fileToZip, fileToZip.getName(), zipOut);
            zipOut.close();
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
     public void unzip(String sourceFile, String destinationFolder) {
        try {
            File destDir = new File(destinationFolder);
            if (!destDir.exists() || !destDir.isDirectory()) {
                System.out.println("Destination folder does not exist or is not a directory.");
                return;
            }

            FileInputStream fis = new FileInputStream(sourceFile);
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));

            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                String entryName = zipEntry.getName();
                File entryFile = new File(destinationFolder, entryName);

                if (zipEntry.isDirectory()) {
                    entryFile.mkdirs();
                } else {
                    byte[] buffer = new byte[1024];
                    int length;
                    FileOutputStream fos = new FileOutputStream(entryFile);
                    while ((length = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }
                    fos.close();
                    System.out.println("Extracted file: " + entryName); // In ra tên tệp đã giải nén
                }
            }

            zis.close();
            fis.close();

            System.out.println("Extraction successful.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Extraction failed.");
        }

    }
     private void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
            }
            zipOut.closeEntry();
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }


}
