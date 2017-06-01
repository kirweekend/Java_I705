package hw3;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

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
    }
}





