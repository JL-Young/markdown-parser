import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.beans.Transient;
import java.nio.file.Files;
import java.util.ArrayList;

import org.junit.*;
public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void getLinksTest() throws IOException {
        boolean result = false;
        String content = Files.readString(Path.of("test-file.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);

        if (links.contains("https://something.com") && 
            links.contains("some-thing.html")) {
                result = true;
            }

        assertTrue(result);
    }

    @Test
    public void testFile() throws IOException {
        boolean result = false;
        Path fileName = Path.of("test-file.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);

        if (links.contains("https://something.com") && 
            links.contains("some-thing.html")) {
                result = true;
            }

        assertTrue(result);
    }
}