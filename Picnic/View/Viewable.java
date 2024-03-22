package View;

import java.io.IOException;

public interface Viewable {
    void run() throws IOException;
    String prompt(String message);
}
