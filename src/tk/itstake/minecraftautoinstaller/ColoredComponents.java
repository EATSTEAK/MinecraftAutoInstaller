/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.itstake.minecraftautoinstaller;

import java.awt.Color;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author itstake
 */
public class ColoredComponents {
    public Color getComponentColor() throws IOException {
        SettingHandling sh = new SettingHandling();
        Properties settings = sh.getSettingFile();
        String str = settings.getProperty("color");
        switch (str) {
            case "0":
                return new Color(158, 158, 158);
            case "1":
                return new Color(244,67,54);
            case "2":
                return new Color(233,30,99);
            case "3":
                return new Color(156,39,176);
            case "4":
                return new Color(103,58,183);
            case "5":
                return new Color(63,81,181);
            case "6":
                return new Color(33,150,243);
            case "7":
                return new Color(3,169,244);
            case "8":
                return new Color(0,188,212);
            case "9":
                return new Color(0,150,136);
            case "10":
                return new Color(76,175,80);
            case "11":
                return new Color(255,87,34);
            case "12":
                return new Color(121,85,72);
            case "13":
                return new Color(96,125,139);
            case "14":
                return new Color(0,0,0);
            default:
                return new Color(158, 158, 158);
        }
    }
}
