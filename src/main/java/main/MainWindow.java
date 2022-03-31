package main;

import pdf_merge.PDFMergeUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import static main.CombinePDF.DEFAULT_FILENAME;

public class MainWindow extends JPanel {

    JLabel folderTitle;
    JTextField chosenFolder;
    JButton browseFolder;
    JFileChooser folderChooser;
    JLabel outputFilenameTitle;
    JTextField outputFilename;
    JButton goButton;
    JRadioButton iTextMerge;
    JRadioButton pdfBoxMerge;
    ButtonGroup pdfMergeOptions;

    public MainWindow() {
        folderTitle = new JLabel("Choose the folder - all .pdf files inside will be combined");
        add(folderTitle);

        chosenFolder = new JTextField(CombinePDF.DEFAULT_FOLDER);
        chosenFolder.setPreferredSize(new Dimension(250, 30));
        add(chosenFolder);

        browseFolder = new JButton("Browse..");
        browseFolder.addActionListener(e -> {
            folderChooser = new JFileChooser();
            folderChooser.setCurrentDirectory(new java.io.File(CombinePDF.DEFAULT_FOLDER));
            folderChooser.setDialogTitle("Choose folder");
            folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            // disable the "All files" option.
            folderChooser.setAcceptAllFileFilterUsed(false);
            if (folderChooser.showOpenDialog(MainWindow.this) == JFileChooser.APPROVE_OPTION) {
                chosenFolder.setText(folderChooser.getSelectedFile().toString());
            }
            else {
                System.out.println("No Selection ");
            }
        });
        add(browseFolder);

        JPanel outputFilenameTitlePanel = new JPanel();
        outputFilenameTitlePanel.setPreferredSize(new Dimension(300, 30));
        outputFilenameTitle = new JLabel("Output filename: ");
        outputFilenameTitlePanel.add(outputFilenameTitle);
        add(outputFilenameTitlePanel);

        outputFilename = new JTextField(DEFAULT_FILENAME);
        outputFilename.setPreferredSize(new Dimension(300, 30));
        add(outputFilename);

        goButton = new JButton("Go");
        goButton.addActionListener(e -> {
            try {
                File targetFile = new File(chosenFolder.getText() + "\\" + outputFilename.getText());
                if (targetFile.exists()) {
                    JOptionPane.showMessageDialog(this, "The file already exists: " + targetFile.getCanonicalPath());
                    return;
                }
                if (pdfMergeOptions.getSelection() != null) {
                    String merger = GroupButtonUtils.getSelectedButtonText(pdfMergeOptions);
                    PDFMergeUtil.mergePdf(merger, chosenFolder.getText(), outputFilename.getText());
                    JOptionPane.showMessageDialog(this, "Success!");
                } else {
                    JOptionPane.showMessageDialog(this, "Please choose one of the merging options.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        add(goButton);

        iTextMerge = new JRadioButton("iText");
        pdfBoxMerge = new JRadioButton("PDFBox");
        pdfMergeOptions = new ButtonGroup();
        pdfMergeOptions.add(iTextMerge);
        pdfMergeOptions.add(pdfBoxMerge);
        add(iTextMerge);
        add(pdfBoxMerge);
    }

    public Dimension getPreferredSize(){
        return new Dimension(400, 210);
    }
}