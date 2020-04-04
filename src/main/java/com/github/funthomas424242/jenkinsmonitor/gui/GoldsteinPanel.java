package com.github.funthomas424242.jenkinsmonitor.gui;

/*-
 * #%L
 * Jenkins Monitor
 * %%
 * Copyright (C) 2019 - 2020 PIUG
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GoldsteinPanel extends JPanel {
    public static final Logger LOGGER = LoggerFactory.getLogger(GoldsteinPanel.class);

    String imgPath;

    public GoldsteinPanel() {
        Path path = Paths.get("1984EmmanuelGoldstein.jpg");
        imgPath = "1984EmmanuelGoldstein.jpg";
        if (!path.toFile().exists()) {
            path = Paths.get("src/main/resources/img/1984EmmanuelGoldstein.jpg");
            imgPath = "src/main/resources/img/1984EmmanuelGoldstein.jpg";
        }

        try {
            final BufferedImage myPicture = ImageIO.read(path.toFile());
            final JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            this.add(picLabel);
        } catch (IOException e) {
            LOGGER.error("Goldstein Panel nicht erzeugt: " + e);
        }
    }

    public String getImgPath() {
        return imgPath;
    }
}
