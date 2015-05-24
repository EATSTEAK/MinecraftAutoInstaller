/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.itstake.minecraftmodautoinstaller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

/**
 *
 * @author itstake
 */
public class SettingHandling {
    
    public Properties getSettingFile() throws IOException {
        Properties settings = new Properties();
        try {
            InputStream fis = this.getClass().getResourceAsStream("/settings/settings.properties");
            settings.load(fis);
        } catch (FileNotFoundException e) {
            System.err.println("설정 파일이 없습니다! 설치기를 종료합니다.");
            System.exit(1);
        }
        return settings;
    }
}
