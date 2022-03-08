import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.IOException;

public class PDFBoxMergePDF {
    private static final String PATH_TO_FOLDER = "C:\\XX\\PDF";
    private static final String MERGED_FILE_NAME = "mergedFile.pdf";

    public static void main(String[] args) throws IOException {
        File dir = new File(PATH_TO_FOLDER);
        File[] filesToMerge = dir.listFiles((file, fileName) -> fileName.endsWith(".pdf"));

        PDFMergerUtility PDFmerger = new PDFMergerUtility();
        for (File file : filesToMerge) {
            System.out.println(file.getCanonicalPath());
            PDFmerger.addSource(file);
        }

        PDFmerger.setDestinationFileName(PATH_TO_FOLDER + "\\" + MERGED_FILE_NAME);

        PDFmerger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
        System.out.println("Documents merged");
    }
}