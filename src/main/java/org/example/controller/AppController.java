package org.example.controller;

import org.example.model.ImageProcessor;
import org.example.view.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class AppController {
    private MainFrame view;
    private ImageProcessor model;

    public AppController(MainFrame view) {
        this.view = view;
        this.model = new ImageProcessor();

        view.getLoadButton().addActionListener(new LoadImageListener());
        view.getApplyButton().addActionListener(new ApplyOperationListener());
        view.getResetButton().addActionListener(new ResetListener()); // Новый слушатель
    }

    class LoadImageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(view);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    BufferedImage image = ImageIO.read(selectedFile);
                    model.setOriginalImage(image);
                    view.displayOriginalImage(new ImageIcon(image));
                    view.resetProcessedImage(); // Сбрасываем обработанное изображение
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Ошибка при загрузке изображения.");
                    ex.printStackTrace();
                }
            }
        }
    }

    class ApplyOperationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (model.getOriginalImage() == null) {
                JOptionPane.showMessageDialog(view, "Пожалуйста, сначала загрузите изображение.");
                return;
            }

            String operation = view.getSelectedOperation();
            int kernelSize = view.getSelectedKernelSize();

            model.processImage(operation, kernelSize);
            view.displayProcessedImage(new ImageIcon(model.getProcessedImage()));
        }
    }

    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (model.getOriginalImage() == null) {
                JOptionPane.showMessageDialog(view, "Нет загруженного изображения.");
                return;
            }

            view.resetProcessedImage();
        }
    }
}