/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.webUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import jakarta.inject.Named;


/**
 *
 * @author FACULTY
 */

@Named
public class ConvertBlobToMedia implements Serializable{

    public static File convertBlobToMultiMediaFile(byte[] imageBytes, String filePath) {
        File mediaFile = null;
        try {
            mediaFile = new File(filePath);
            FileOutputStream fos = new FileOutputStream(mediaFile);
            fos.write(imageBytes);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mediaFile;
    }

    public static byte[] convertMultiMediaFileToBlob(File file) {
        byte[] blob = new byte[(int) file.length()];
        try {
            FileInputStream fis = new FileInputStream(file);
            fis.read(blob);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blob;
    }

}
