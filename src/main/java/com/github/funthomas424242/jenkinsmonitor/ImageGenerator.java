package com.github.funthomas424242.jenkinsmonitor;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageGenerator {

    public ImageGenerator(Object o) {

    }

    public BufferedImage getImage() {
        int with = 100;
        int hight = 100;

        final BufferedImage image = new BufferedImage(with, hight,
            BufferedImage.TYPE_BYTE_INDEXED);
        final Graphics g = image.createGraphics();
        g.setColor(Color.gray);
        g.fillRect(0, 0, with, hight);
        g.dispose();

        return image;
    }
}
