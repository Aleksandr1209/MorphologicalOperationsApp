package org.example.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private ImagePanel originalImagePanel;
    private ImagePanel processedImagePanel;
    private JComboBox<String> operationComboBox;
    private JComboBox<String> kernelSizeComboBox;
    private JButton loadButton;
    private JButton applyButton;
    private JButton resetButton; // Новая кнопка

    public MainFrame() {
        setTitle("Морфологические операции с изображениями");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        loadButton = new JButton("Загрузить изображение");
        applyButton = new JButton("Применить операцию");
        resetButton = new JButton("Сбросить"); // Новая кнопка

        String[] operations = {"Эрозия", "Дилатация", "Открытие", "Закрытие", "Градиент"};
        operationComboBox = new JComboBox<>(operations);

        String[] kernelSizes = {"3x3", "5x5", "7x7"};
        kernelSizeComboBox = new JComboBox<>(kernelSizes);

        controlPanel.add(loadButton);
        controlPanel.add(new JLabel("Операция:"));
        controlPanel.add(operationComboBox);
        controlPanel.add(new JLabel("Размер ядра:"));
        controlPanel.add(kernelSizeComboBox);
        controlPanel.add(applyButton);
        controlPanel.add(resetButton); // Добавляем кнопку в панель

        add(controlPanel, BorderLayout.NORTH);

        JPanel imagePanel = new JPanel(new GridLayout(1, 2));
        originalImagePanel = new ImagePanel("Оригинальное изображение");
        processedImagePanel = new ImagePanel("Результат обработки");

        imagePanel.add(originalImagePanel);
        imagePanel.add(processedImagePanel);

        add(imagePanel, BorderLayout.CENTER);
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public JButton getApplyButton() {
        return applyButton;
    }

    public JButton getResetButton() {
        return resetButton; // Новый геттер
    }

    public String getSelectedOperation() {
        return (String) operationComboBox.getSelectedItem();
    }

    public int getSelectedKernelSize() {
        String size = (String) kernelSizeComboBox.getSelectedItem();
        return Integer.parseInt(size.substring(0, 1));
    }

    public void displayOriginalImage(ImageIcon icon) {
        originalImagePanel.setImage(icon);
    }

    public void displayProcessedImage(ImageIcon icon) {
        processedImagePanel.setImage(icon);
    }

    public void resetProcessedImage() {
        processedImagePanel.setImage(null); // Очищаем обработанное изображение
    }
}