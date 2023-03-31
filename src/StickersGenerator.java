import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StickersGenerator {
    public void generate(InputStream inputStream, String destFileName, String phrase) throws Exception {
        BufferedImage image = ImageIO.read(inputStream);

        int width = image.getWidth();
        int height = image.getHeight();
        int plusHeight = 200;
        int newHeight = height + plusHeight; // pixels
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(image, 0, 0, null);

        int fontSize = 64;
        var font = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(font);

        int x = 0;
        int y = (newHeight - plusHeight / 2) + fontSize / 2;
        graphics.drawString(phrase, x, y);

        String destDirName = "stickers";
        String finalDestName = destDirName + File.separator + destFileName;

        ImageIO.write(newImage, "png", new File(finalDestName));
    }
}
