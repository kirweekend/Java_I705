package HW3;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by kirweekend on 4/25/17.
 */
public class HW3 {
    public static void main(String[] args) throws IOException {
        String filename = "headlines.txt";
        Document doc;
        doc = Jsoup.connect("http://www.washingtonpost.com").get();
        int c = 0;
        try {
            PrintWriter outputStream = new PrintWriter(filename);
            outputStream.println("        News       ");
            outputStream.println("-------------------");

            for (Element headline : doc.select("div.headline > a[href]")){
                c ++;
                outputStream.println(c + " " + headline.text());
                outputStream.println(" > " + headline.attr("href"));
                outputStream.println();
                if (c == 10) {
                    break;
                }
            }

            outputStream.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = new String(Files.readAllBytes(Paths.get("headlines.txt")));
        Path path = Paths.get("headlines.txt");
        File news = new File("headlines.txt");

        if (news.exists()){
            java.util.List<String> rlines = Files.readAllLines(path);
            rlines.add(0, content);
            Files.write(path, rlines);
        } else {
            Files.write(path, content.getBytes(), StandardOpenOption.CREATE);
            //CREATE = create new file if not exist; APPEND = add new data to same file when program is run
        }
    }

}





