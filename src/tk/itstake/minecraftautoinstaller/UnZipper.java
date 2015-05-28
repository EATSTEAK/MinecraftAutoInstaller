/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.itstake.minecraftautoinstaller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/**
 *
 * @author itstake
 */
public class UnZipper {
    
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
