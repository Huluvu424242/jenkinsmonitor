package com.github.funthomas424242.jenkinsmonitor;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TrayImage {

    public static boolean  isImageOfColor(BufferedImage image, Color color) {
        boolean isOfColor = true;
        final int width = image.getWidth();
        final int height = image.getHeight();
        for (int breite = 0; breite < width; breite++) {
            for (int hoehe = 0; hoehe < height; hoehe++) {
                isOfColor = isOfColor && image.getRGB(breite, hoehe) == color.getRGB();
            }
        }
        return isOfColor;
    }
}
