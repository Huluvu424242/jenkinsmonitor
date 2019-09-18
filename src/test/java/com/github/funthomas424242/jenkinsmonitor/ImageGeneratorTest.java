package com.github.funthomas424242.jenkinsmonitor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ImageGeneratorTest {


    @Test
    @DisplayName("Initialisierung mit null generiert ein graues Icon 100x100")
    void initNullCreateGrayIcon100x100() {
        final ImageGenerator generator = new ImageGenerator(null);
        final BufferedImage image = generator.getImage(100,100);
        assertEquals(100,image.getHeight());
        assertEquals(100,image.getWidth());
    }

    @Test
    @DisplayName("Initialisierung ohne Jobs generiert ein graues Icon 100x100")
    void initEmptyCreateGrayIcon100x100() {
        final JobStatus[] jobsStatus = new JobStatus[0];
        final ImageGenerator generator = new ImageGenerator(jobsStatus);
        final BufferedImage image = generator.getImage(100,100);
        assertEquals(100,image.getHeight());
        assertEquals(100,image.getWidth());
    }


}
