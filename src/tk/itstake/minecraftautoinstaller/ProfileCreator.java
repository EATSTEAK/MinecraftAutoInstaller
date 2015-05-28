/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.itstake.minecraftautoinstaller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author itstake
 */
public class ProfileCreator {
    private static String OS = System.getProperty("os.name").toLowerCase();
    public void addProfile() throws UnsupportedEncodingException, IOException {
        JSONParser parser = new JSONParser();
        SettingHandling sh = new SettingHandling();
        Properties settings = sh.getSettingFile();
        String profilename = new String(settings.getProperty("profilename").getBytes("ISO-8859-1"), "UTF-8");
        String profileversion = new String(settings.getProperty("profileverison").getBytes("ISO-8859-1"), "UTF-8");
        String path = null;
        if(isWindows()) {
            path = System.getenv("APPDATA") + "\\.minecraft\\";
        } else if(isMac()) {
            path = System.getenv("APPDATA") + "\\minecraft\\";
        } else {
            path = System.getenv("APPDATA") + "\\minecraft\\";
        }
        Object obj = null;
        try {
            obj = parser.parse(new FileReader(path + "\\launcher_profiles.json"));
        } catch (Exception ex) {
            Logger.getLogger(ProfileCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject jsonObject = (JSONObject) obj;
        Object profiles = jsonObject.get("profiles");
        JSONObject profile = new JSONObject();
        profile.put("name", profilename);
        profile.put("lastVersionId", profileversion);
        JSONObject jprofiles = (JSONObject) profiles;
        jprofiles.put(profilename, profile);
        jsonObject.put("profiles", jprofiles);
        jsonObject.put("selectedProfile", profilename);
        try {
 
            FileWriter file = new FileWriter(path + "\\launcher_profiles.json");
            file.write(jsonObject.toJSONString());
            file.flush();
            file.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean isWindows() {
 
	return (OS.indexOf("win") >= 0);
 
	}
 
    public static boolean isMac() {
 
	return (OS.indexOf("mac") >= 0);
 
    }
    
}
