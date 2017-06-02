package HW3;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
                true /* append = true */));

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

        FileReader fileReader = new FileReader(filename);
        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        c = 0;
        String fileT = "";
        while ((line = bufferedReader.readLine()) != null) {
            String file = line;
            // System.out.println(file);
            fileT += file;
        }

        for (Element headline : doc.select("div.headline > a[href]")) {
            c++;
            if (!fileT.toLowerCase().contains(headline.text().toLowerCase())){

                System.out.println("New headline: " + headline.text());
            } else {

                System.out.println("Old Headline: " + headline.text());
            }

            if (c == 10) {
                break;
            }

           
        }

        outputStream.close();
        // Always close files.
        bufferedReader.close();

    }
}