package org.example.view;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private JLabel imageLabel;

    public ImagePanel(String title) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(title));

        imageLabel = new JLabel("", SwingConstants.CENTER);
        add(imageLabel, BorderLayout.CENTER);
    }

    public void setImage(ImageIcon icon) {
        if (icon != null) {
            // Масштабирование изображения для отображения
            Image image = icon.getImage();
            Image scaledImage = image.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            imageLabel.setIcon(null);
        }
    }
}