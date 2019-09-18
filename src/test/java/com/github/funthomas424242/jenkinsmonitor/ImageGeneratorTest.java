package com.github.funthomas424242.jenkinsmonitor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ImageGeneratorTest {


    @Test
    @DisplayName("Generiert ein graues Icon 100x100")
    void createGrayIcon100x100() {
//        final JobStatus[] jobsStatus = new JobStatus[0];
        final ImageGenerator generator = new ImageGenerator(null);
        final BufferedImage image = generator.getImage();
        assertEquals(100,image.getHeight());
        assertEquals(100,image.getWidth());
    }


}
