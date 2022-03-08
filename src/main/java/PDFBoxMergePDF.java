import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.IOException;

public class PDFBoxMergePDF {
    private static final String PATH_TO_FOLDER = "C:\\Users\\shiva\\IdeaProjects\\CombinePDF\\src\\main\\resources";
    private static final String MERGED_FILE_NAME = "mergedFile.pdf";

    public static void main(String[] args) throws IOException {
        mergePDF(PATH_TO_FOLDER, MERGED_FILE_NAME);
    }

    public static void mergePDF(String directory, String targetFile) throws IOException {
        File dir = new File(directory);
        File[] filesToMerge = dir.listFiles((file, fileName) -> fileName.endsWith(".pdf"));

        PDFMergerUtility PDFmerger = new PDFMergerUtility();
        for (File file : filesToMerge) {
            PDFmerger.addSource(file);
        }

        PDFmerger.setDestinationFileName(directory + "\\" + targetFile);
        PDFmerger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
    }
}