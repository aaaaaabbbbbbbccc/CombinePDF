package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CombinePDF {

    public final static String DEFAULT_FOLDER = "C:\\Users\\shiva\\Desktop\\PDFs";
    public final static String DEFAULT_FILENAME = "example.pdf";

    public static void main(String[] args) {
        JFrame frame = new JFrame("Combine PDF");
        MainWindow mainWindow = new MainWindow();
        frame.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
        frame.getContentPane().add(mainWindow,"Center");
        frame.setLayout(new FlowLayout());
        frame.setSize(mainWindow.getPreferredSize());
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
