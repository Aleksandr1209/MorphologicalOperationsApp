package org.example;

import org.example.controller.AppController;
import org.example.view.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            new AppController(mainFrame);
            mainFrame.setVisible(true);
        });
    }
}