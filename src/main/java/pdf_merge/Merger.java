package pdf_merge;

import java.io.IOException;

public interface Merger {

    void merge(String directory, String filename) throws IOException;
}
