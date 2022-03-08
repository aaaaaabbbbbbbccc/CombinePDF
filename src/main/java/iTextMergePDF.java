import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSmartCopy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class iTextMergePDF {
    private static final String PATH_TO_FOLDER = "C:\\Users\\shiva\\IdeaProjects\\CombinePDF\\src\\main\\resources";
    private static final String MERGED_FILE_NAME = "mergedFile.pdf";

    static public void main(String[] args) throws DocumentException, IOException {
        mergePDF(PATH_TO_FOLDER, MERGED_FILE_NAME);
    }
    public static void mergePDF(String directory, String targetFile) throws DocumentException, IOException {
        File dir = new File(directory);
        File[] filesToMerge = dir.listFiles((file, fileName) -> fileName.endsWith(".pdf"));

        Document document = new Document();
        FileOutputStream outputStream = new FileOutputStream(PATH_TO_FOLDER + "\\" + targetFile);
        PdfCopy copy = new PdfSmartCopy(document, outputStream);
        document.open();

        for (File inFile : filesToMerge) {
            System.out.println(inFile.getCanonicalPath());
            PdfReader reader = new PdfReader(inFile.getCanonicalPath());
            copy.addDocument(reader);
            reader.close();
        }
        document.close();
    }
}