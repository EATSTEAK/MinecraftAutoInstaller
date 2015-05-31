/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.itstake.minecraftautoinstaller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/**
 *
 * @author itstake
 */
public class InstallHandling {
    public void InstallProfile(Properties profile, String path) {
        String unzippath = path + "\\" +  profile.getProperty("unzippath");
        String zippath = profile.getProperty("zippath");
        boolean checked;
        boolean change;
        boolean deletemode;
        String deletepath;
        boolean nounzip;
        boolean backupmode;
        String backupfrompath;
        String backuptopath;
        if(profile.getProperty("checked").equals("false")) {
            checked = false;
        } else {
            checked = true;
        }
        if(profile.getProperty("change").equals("false")) {
            change = false;
        } else {
            change = true;
        }
        if(profile.getProperty("deletemode").equals("true")) {
            deletemode = true;
        } else {
            deletemode = false;
        }
        if(profile.getProperty("deletepath").equals("")) {
            deletepath = path;
        } else {
            deletepath = path + "\\" + profile.getProperty("deletepath");
        }
        if(profile.getProperty("nounzip").equals("true")) {
            nounzip = true;
        } else {
            nounzip = false;
        }
        if(profile.getProperty("backupmode").equals("true")) {
            backupmode = true;
        } else {
            backupmode = false;
        }
        if(profile.getProperty("backupfrompath").equals("")) {
            backupfrompath = path;
        } else {
            backupfrompath = path + "\\" + profile.getProperty("backupfrompath");
        }
        if(profile.getProperty("backuptopath").equals("")) {
            backuptopath = path + "backup";
        } else {
            backuptopath = path + "\\" + profile.getProperty("backuptopath");
        }
        if(backupmode) {
            File fromfile = new File(backupfrompath);
            File tofile = new File(backuptopath);
            try {
                copyDirectory(fromfile, tofile);
            } catch (IOException ex) {
                Logger.getLogger(InstallHandling.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(deletemode) {
            File deletefile = new File(deletepath);
            if(deletefile.isDirectory()) {
                deleteDirectory(deletefile);
            } else {
                deletefile.delete();
            }
        }
        if(!nounzip) {
            try {
                unzip(zippath, unzippath);
            } catch (IOException ex) {
                Logger.getLogger(InstallHandling.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(InstallHandling.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ZipException ex) {
                Logger.getLogger(InstallHandling.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static boolean deleteDirectory(File path) {
        if(!path.exists()) {
            return false;
        }
         
        File[] files = path.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                deleteDirectory(file);
            } else {
                file.delete();
            }
        }
         
        return path.delete();
    }
    
    public static void copyDirectory(File sourcelocation , File targetdirectory) throws IOException {
        if (sourcelocation.isDirectory()) {               
            if (!targetdirectory.exists()) {
                targetdirectory.mkdir();
            }
                
            String[] children = sourcelocation.list();
            for (int i=0; i<children.length; i++) {
                copyDirectory(new File(sourcelocation, children[i]),
                    new File(targetdirectory, children[i]));
            }
        } else {
            InputStream in = new FileInputStream(sourcelocation);                
            OutputStream out = new FileOutputStream(targetdirectory);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
    }
    
    public final void unzip(String zipfile, String extractTo) throws IOException, URISyntaxException, ZipException {
        File file = new File(zipfile);
        
      InputStream inputStream = this.getClass().getResourceAsStream("/zip/" + zipfile);

      OutputStream outStream = new FileOutputStream(file);

      // 읽어들일 버퍼크기를 메모리에 생성
      byte[] buf = new byte[4096];

      int len = 0;
      // 끝까지 읽어들이면서 File 객체에 내용들을 쓴다

      while ((len = inputStream.read(buf)) > 0){

         outStream.write(buf, 0, len);

      }
      // Stream 객체를 모두 닫는다.

      outStream.close();

      inputStream.close();
        ZipFile zipFile;
        zipFile = new ZipFile(file);
        zipFile.extractAll(extractTo);
        file.delete();
    }
    
}
