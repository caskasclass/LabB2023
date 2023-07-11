package intefaces;

import java.io.IOException;

public interface RequestElaboration_withClose extends RequestElaboration {

    public void close() throws IOException;
}