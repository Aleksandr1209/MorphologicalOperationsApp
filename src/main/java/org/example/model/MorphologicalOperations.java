package org.example.model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MorphologicalOperations {
    public BufferedImage erode(BufferedImage image, int kernelSize) {
        // Реализация эрозии
        return applyOperation(image, kernelSize, true);
    }

    public BufferedImage dilate(BufferedImage image, int kernelSize) {
        // Реализация дилатации
        return applyOperation(image, kernelSize, false);
    }

    public BufferedImage open(BufferedImage image, int kernelSize) {
        // Открытие = эрозия + дилатация
        BufferedImage eroded = erode(image, kernelSize);
        return dilate(eroded, kernelSize);
    }

    public BufferedImage close(BufferedImage image, int kernelSize) {
        // Закрытие = дилатация + эрозия
        BufferedImage dilated = dilate(image, kernelSize);
        return erode(dilated, kernelSize);
    }

    public BufferedImage gradient(BufferedImage image, int kernelSize) {
        // Градиент = дилатация - эрозия
        BufferedImage dilated = dilate(image, kernelSize);
        BufferedImage eroded = erode(image, kernelSize);
        return subtractImages(dilated, eroded);
    }

    private BufferedImage applyOperation(BufferedImage image, int kernelSize, boolean isErosion) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        int radius = kernelSize / 2;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int extremeValue = isErosion ? 255 : 0;
                int targetValue = isErosion ? 0 : 255;

                for (int ky = -radius; ky <= radius; ky++) {
                    for (int kx = -radius; kx <= radius; kx++) {
                        int px = x + kx;
                        int py = y + ky;

                        if (px >= 0 && px < image.getWidth() && py >= 0 && py < image.getHeight()) {
                            int pixel = new Color(image.getRGB(px, py)).getRed();
                            if (isErosion) {
                                extremeValue = Math.min(extremeValue, pixel);
                            } else {
                                extremeValue = Math.max(extremeValue, pixel);
                            }
                        }
                    }
                }

                int newPixel = new Color(extremeValue, extremeValue, extremeValue).getRGB();
                result.setRGB(x, y, newPixel);
            }
        }
        return result;
    }

    private BufferedImage subtractImages(BufferedImage img1, BufferedImage img2) {
        BufferedImage result = new BufferedImage(img1.getWidth(), img1.getHeight(), img1.getType());

        for (int y = 0; y < img1.getHeight(); y++) {
            for (int x = 0; x < img1.getWidth(); x++) {
                int rgb1 = new Color(img1.getRGB(x, y)).getRed();
                int rgb2 = new Color(img2.getRGB(x, y)).getRed();
                int diff = Math.abs(rgb1 - rgb2);
                int newPixel = new Color(diff, diff, diff).getRGB();
                result.setRGB(x, y, newPixel);
            }
        }
        return result;
    }
}