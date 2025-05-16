package org.example.model;

import java.awt.image.BufferedImage;

public class ImageProcessor {
    private BufferedImage originalImage;
    private BufferedImage processedImage;

    public void setOriginalImage(BufferedImage image) {
        this.originalImage = image;
    }

    public BufferedImage getOriginalImage() {
        return originalImage;
    }

    public BufferedImage getProcessedImage() {
        return processedImage;
    }

    public void processImage(String operation, int kernelSize) {
        if (originalImage == null) return;

        MorphologicalOperations operations = new MorphologicalOperations();
        switch (operation) {
            case "Эрозия":
                processedImage = operations.erode(originalImage, kernelSize);
                break;
            case "Дилатация":
                processedImage = operations.dilate(originalImage, kernelSize);
                break;
            case "Открытие":
                processedImage = operations.open(originalImage, kernelSize);
                break;
            case "Закрытие":
                processedImage = operations.close(originalImage, kernelSize);
                break;
            case "Градиент":
                processedImage = operations.gradient(originalImage, kernelSize);
                break;
        }
    }
}
