//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);
            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf(")", openParen);

            //fix 3: break loop if [, ], ( or ) is not found
            //(fix 1 removed because redundant)
            if(openBracket == -1 || closeBracket == -1 ||
                openParen == -1 || closeParen == -1) {
                    break;
            }

            //fix 2: skip image links
            if(markdown.substring(currentIndex, openBracket).contains("![")){
                currentIndex = closeParen + 1;
                continue;
            }     
            
            //fix 4: skip when bracket and parenthesis are far apart
            if(openParen != closeBracket + 1){
                currentIndex = closeBracket + 1;
                continue;
            }

            //fix 5: skip backticks
            toReturn.add(markdown.substring(openParen + 1, closeParen)
                    .replaceAll("`", ""));
            currentIndex = closeParen + 1;
        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
        // System.out.println("--- Lab 4 Part 2 File Seperator ---");
    }
}
