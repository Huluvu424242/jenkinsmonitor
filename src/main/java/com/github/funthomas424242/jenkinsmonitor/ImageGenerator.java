package com.github.funthomas424242.jenkinsmonitor;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageGenerator {

    public ImageGenerator(Object o) {

    }

    public BufferedImage getImage( final int width, final int height) {
        final BufferedImage image = new BufferedImage(width, height,
            BufferedImage.TYPE_BYTE_INDEXED);
        final Graphics g = image.createGraphics();
        g.setColor(Color.gray);
        g.fillRect(0, 0, width, height);
        g.dispose();

        return image;
    }
}
