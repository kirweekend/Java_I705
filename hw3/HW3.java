package HW3;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class HW3 {

    static Document doc;

    static {
        try {
            doc = Jsoup.connect("http://www.washingtonpost.com").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        String filename = "headlines.txt";
        int c = 0;
        PrintWriter outputStream = new PrintWriter(new FileOutputStream(new File(filename),
                true /*check if file exist and append*/));

        String content = new String(Files.readAllBytes(Paths.get("headlines.txt")));
        System.out.println(content);

        outputStream.println("        News       " + new Date());
        outputStream.println("-----------------------------------");

        for (Element headline : doc.select("div.headline > a[href]")) {
            c++;
            outputStream.println(c + " " + headline.text());
            outputStream.println(" > " + headline.attr("href"));
            outputStream.println();
            if (c == 10) {
                break;
            }
        }
        outputStream.close();

    }
}