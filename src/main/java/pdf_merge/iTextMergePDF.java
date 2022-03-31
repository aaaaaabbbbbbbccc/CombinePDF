package pdf_merge;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSmartCopy;

import java.io.File;
import java.io.FileOutputStream;

public class iTextMergePDF implements Merger {

    public void merge(String directory, String filename) {
        File dir = new File(directory);
        File[] filesToMerge = dir.listFiles((file, fileName) -> fileName.endsWith(".pdf"));

        try {
            Document document = new Document();
            FileOutputStream outputStream = new FileOutputStream(dir.getCanonicalPath() + "\\" + filename);
            PdfCopy copy = new PdfSmartCopy(document, outputStream);
            document.open();

            for (File inFile : filesToMerge) {
                System.out.println(inFile.getCanonicalPath());
                PdfReader reader = new PdfReader(inFile.getCanonicalPath());
                copy.addDocument(reader);
                reader.close();
            }
            document.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}