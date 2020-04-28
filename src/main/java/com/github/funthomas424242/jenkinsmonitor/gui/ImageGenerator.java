package com.github.funthomas424242.jenkinsmonitor.gui;

/*-
 * #%L
 * Jenkins Monitor
 * %%
 * Copyright (C) 2019 PIUG
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

import com.github.funthomas424242.jenkinsmonitor.jenkins.AbstractJobBeschreibung;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatus;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class StartXHolder {
    public int startX = 0;
}

public class ImageGenerator {

    public static final Logger LOGGER = LoggerFactory.getLogger(ImageGenerator.class);

    protected final JobStatusBeschreibungen jobsStatusBeschreibungen;

    protected ImageGenerator(final JobStatusBeschreibungen jobsStatusBeschreibungen) {
        this.jobsStatusBeschreibungen = jobsStatusBeschreibungen;
    }

    protected BufferedImage createImage(final int width, final int height) {
        return drawImage(new BufferedImage(width, height, BufferedImage.TYPE_BYTE_INDEXED), width, height);
    }

    protected BufferedImage drawImage(final BufferedImage image, final int width, final int height) {
        // rename in fillAndDrawPartImage
        drawPartImage(image, 0, width, height, JobStatus.OTHER);

        if (jobsStatusBeschreibungen == null || jobsStatusBeschreibungen.size() < 1) {
            return image;
        }

        final JobStatusBeschreibungen tmpJobStatusBeschreibungen = new JobStatusBeschreibungen(jobsStatusBeschreibungen.getCloneOfDataModel());


        final int jobCount = tmpJobStatusBeschreibungen.size();
        final int partImageWidth = width / jobCount;
        final StartXHolder startXHolder = new StartXHolder();
        AbstractJobBeschreibung.sortedKeyStreamOf(tmpJobStatusBeschreibungen)
                .forEach(primaryKey -> {
                    drawPartImage(image, startXHolder.startX, partImageWidth, height, tmpJobStatusBeschreibungen.get(primaryKey).getJobStatus());
                    startXHolder.startX += partImageWidth;
                });
        return image;
    }

    protected BufferedImage drawPartImage(BufferedImage image,
                                          final int x,
                                          final int width,
                                          final int height,
                                          final JobStatus jobStatus) {

        final Graphics g = image.createGraphics();
        g.setColor(jobStatus.getColor());
        g.fillRect(x, 0, width, height);
        g.dispose();

        return image;
    }

    protected static ImageIcon getGoldSteinImageIcon() {
        try {
            final InputStream inputStream = Versionsinfofenster.class.getClassLoader().getResourceAsStream("1984EmmanuelGoldstein.jpg");
            assert inputStream != null;
            final BufferedImage myPicture = ImageIO.read(inputStream);
            inputStream.close();
            return new ImageIcon(myPicture);
        } catch (IOException e) {
            LOGGER.warn(MessageFormat.format("Goldstein Logo nicht erzeugt: {0}", e));
        }
        return null;
    }

    protected static ImageIcon getMinervaLogo() {
        try {
            final InputStream inputStream = Versionsinfofenster.class.getClassLoader().getResourceAsStream("MinervaV-64x64.png");
            assert inputStream != null;
            final BufferedImage myPicture = ImageIO.read(inputStream);
            inputStream.close();
            return new ImageIcon(myPicture);
        } catch (IOException e) {
            LOGGER.warn(MessageFormat.format("Minerva Logo nicht erzeugt: {0}", e));
        }
        return null;
    }

    public static Image convertIconToImage(Icon icon) {
        if (icon instanceof ImageIcon) {
            return ((ImageIcon) icon).getImage();
        } else {
            int width = icon.getIconWidth();
            int height = icon.getIconHeight();
            final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//            Graphics2D g = (Graphics2D) image.getGraphics();
//            icon.paintIcon(null, g, 0, 0);
            return image;
        }
    }

}
