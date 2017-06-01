package hw1;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

public class Hw1 {

    public static void main(String[] args) throws IOException {

        int width = 100;
        int height = 30;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 24));

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Scanner scanner = new Scanner(System.in);
        System.out.println("input:");
        String message = scanner.nextLine();
        graphics.drawString(message, 10, 20);
        scanner.close();

        for (int y = 1; y < image.getHeight(); y++) {
            for (int x = 1; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                image.getGraphics().fillRect(x, y, 1, 1);
                if(rgb == -1){
                    System.out.print(' ');
                }else{
                    System.out.print('#');
                }
            }
            System.out.println("");
        }
    }
}
