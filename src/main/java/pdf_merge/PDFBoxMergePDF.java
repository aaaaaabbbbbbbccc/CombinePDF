package pdf_merge;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.IOException;

public class PDFBoxMergePDF implements Merger {

    public void merge(String directory, String targetFile) throws IOException {
        File dir = new File(directory);
        File[] filesToMerge = dir.listFiles((file, fileName) -> fileName.endsWith(".pdf"));

        PDFMergerUtility PDFmerger = new PDFMergerUtility();
        for (File file : filesToMerge) {
            PDFmerger.addSource(file);
        }
        PDFmerger.setDestinationFileName(dir.getCanonicalPath() + "\\" + targetFile);
        PDFmerger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());

    }
}