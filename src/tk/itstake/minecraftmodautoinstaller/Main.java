/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.itstake.minecraftmodautoinstaller;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Properties;
import javax.swing.UIManager;
import tk.itstake.minecraftmodautoinstaller.MainFrame;

/**
 *
 * @author itstake
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static Font font;
    public static void main(String[] args) throws IOException, FontFormatException {
        //AA On
        System.setProperty("awt.useSystemAAFontSettings","on");
        System.setProperty("swing.aatext", "true");
        Main main = new Main();
        main.setLookandFeel();
        SettingHandling sh = new SettingHandling();
        Properties settings = sh.getSettingFile();
        MainFrame gui = new MainFrame();
        if(!settings.getProperty("versionlink").equals("") && !settings.getProperty("versionlocal").equals("")) {
            String webversion = main.getURLString(new URL(settings.getProperty("versionlink")));
            String localversion = settings.getProperty("versionlocal");
            if(!webversion.equals(localversion)) {
                UpdateDialog ud = new UpdateDialog();
                ud.main();
            } else {
                gui.main();
            }
        } else {
            gui.main();
        }
        
        
        
    }
    
    public String getURLString(URL url) {
        try {
            URLConnection urlConn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));            
        	String str;
        	while ((str = in.readLine()) != null) {
        		return str;
        	}
        	in.close();
        }catch (UnknownHostException ue){
        	System.out.println("주소가 잘못되었습니다. 다시 입력하세요..");
        }catch (IOException ie) {
        }
        return null;
    }
    
    public void setLookandFeel() throws FontFormatException, IOException {
        Main.font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/fonts/NanumBarunGothic.ttf")).deriveFont(Font.PLAIN, 0);
        UIManager.put("OptionPane.messageFont", font.deriveFont(Font.PLAIN, 14));
        UIManager.put("OptionPane.buttonFont", font.deriveFont(Font.PLAIN, 14));
        UIManager.put("FileChooser.listFont", font.deriveFont(Font.PLAIN, 14));
        UIManager.put("Button.select", new javax.swing.plaf.ColorUIResource(Color.LIGHT_GRAY));
        UIManager.put("ToggleButton.select", new javax.swing.plaf.ColorUIResource(Color.LIGHT_GRAY));
        UIManager.put("Button.background", new java.awt.Color(238, 238, 238));
        UIManager.put("ToggleButton.background", new java.awt.Color(238, 238, 238));
        UIManager.put("Button.font", font.deriveFont(Font.PLAIN, 14));
        UIManager.put("Label.font", font.deriveFont(Font.PLAIN, 14));
        UIManager.put("ScrollPane.font", font.deriveFont(Font.PLAIN, 14));
        UIManager.put("ComboBox.font", font.deriveFont(Font.PLAIN, 14));
        UIManager.put("Button.focus", new javax.swing.plaf.ColorUIResource(Color.LIGHT_GRAY));
        UIManager.put("Slider.focus", new java.awt.Color(238, 238, 238));
        UIManager.put("Slider.altTrackColor", new javax.swing.plaf.ColorUIResource(Color.GRAY));
        UIManager.put("Slider.paintThumbArrowShape", true);
        UIManager.put("Slider.highlight", new java.awt.Color(238, 238, 238));
        UIManager.put("Button.border", new javax.swing.border.LineBorder(new java.awt.Color(238, 238, 238), 4, true));
        UIManager.put("ToggleButton.border", new javax.swing.border.LineBorder(new java.awt.Color(238, 238, 238), 4, true));
        UIManager.put("OptionPane.background", new javax.swing.plaf.ColorUIResource(Color.WHITE));
        UIManager.put("Label.background", new javax.swing.plaf.ColorUIResource(Color.WHITE));
        UIManager.put("Panel.background", new javax.swing.plaf.ColorUIResource(Color.WHITE));
    }
    
}
