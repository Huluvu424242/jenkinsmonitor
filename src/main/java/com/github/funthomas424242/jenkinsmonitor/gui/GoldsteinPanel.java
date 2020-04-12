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
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GoldsteinPanel extends JPanel {
    public static final Logger LOGGER = LoggerFactory.getLogger(GoldsteinPanel.class);

    String imgPath;

    public GoldsteinPanel() {

        this.setLayout( new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add( new JLabel("Wer den Jenkins kontrolliert, kontrolliert die Gegenwart !!!"));
        try {
            final InputStream inputStream = getClass().getClassLoader().getResourceAsStream("1984EmmanuelGoldstein.jpg");
            final BufferedImage myPicture = ImageIO.read(inputStream);
            final JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            this.add(picLabel);
            inputStream.close();
        } catch (IOException e) {
            LOGGER.warn("Goldstein Panel nicht erzeugt: " + e);
        }

        this.add( new JLabel("Wer die Vergangenheit kontrolliert, kontrolliert die Zukunft."));
        this.add( new JLabel("Wer die Gegenwart kontrolliert, kontrolliert die Vergangenheit."));
        this.add( new JLabel("— Georg Orwell (Author des Buches \"1984\")"));

    }

    public String getImgPath() {
        return imgPath;
    }
}
