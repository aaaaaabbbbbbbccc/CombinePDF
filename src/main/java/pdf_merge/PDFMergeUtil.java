package pdf_merge;

import java.io.IOException;

public class PDFMergeUtil {

    public static void mergePdf(String merger, String directory, String filename){
        Merger pdfMerger;
        if (merger.equals("iText")) {
            pdfMerger = new iTextMergePDF();
        } else if (merger.equals("PDFBox")) {
            pdfMerger = new PDFBoxMergePDF();
        } else {
            pdfMerger = new iTextMergePDF();
        }
        try {
            pdfMerger.merge(directory, filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
