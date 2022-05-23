import org.junit.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void snippet1() throws IOException {
        String contents = Files.readString(Path.of("snippet-1.md"));
        List<String> expect = List.of("`google.com",
                "google.com", "ucsd.edu");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }
    @Test
    public void snippet2() throws IOException {
        String contents = Files.readString(Path.of("snippet-2.md"));
        List<String> expect = List.of("a.com", "a.com(())", "example.com");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }
    @Test
    public void snippet3() throws IOException {
        String contents = Files.readString(Path.of("snippet-3.md"));
        List<String> expect = List.of("https://www.twitter.com", 
                "https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule",
                "https://cse.ucsd.edu/");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }
}