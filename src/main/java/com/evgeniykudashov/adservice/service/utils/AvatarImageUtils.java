package com.evgeniykudashov.adservice.service.utils;

import lombok.experimental.UtilityClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;


@UtilityClass
public class AvatarImageUtils {

    public static void generate(Path directory, String letter) {
        int width = 256; // Image width
        int height = 256; // Image height

        // Create a BufferedImage object with the specified width and height
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Get the Graphics object from the BufferedImage
        Graphics graphics = image.getGraphics();

        // Set the background color to green
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(0, 0, width, height);

        // Set the font and color for the text
        Font font = new Font("Arial", Font.BOLD, 128);
        graphics.setFont(font);
        graphics.setColor(Color.WHITE);

        String text = letter; // Text to be displayed

        // Get the FontMetrics to calculate the position of the text
        FontMetrics fontMetrics = graphics.getFontMetrics(font);
        int textWidth = fontMetrics.stringWidth(text);
        int textHeight = fontMetrics.getHeight();

        // Calculate the position to center the text horizontally and vertically
        int x = (width - textWidth) / 2;
        int y = (height - textHeight) / 2 + fontMetrics.getAscent();

        // Draw the text in the middle of the image
        graphics.drawString(text, x, y);

        // Dispose the Graphics object
        graphics.dispose();

        // Save the generated image to a file
        try {

            String extension = "png";

            String filename = String.format("%dx%d.%s", height, width, extension);

            File output = directory.resolve(filename).toFile();

            ImageIO.write(image, extension, output);

        } catch (IOException e) {

            System.out.println("Error while saving the image: " + e.getMessage());

        }
    }
}
