import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class StickersGenerator {
    public void generate(InputStream inputStream, String destFileName) throws Exception {
        // Leitura da imagem.
        BufferedImage image = ImageIO.read(inputStream);

        // Cria nova imagem em memória com transparência e com tamanho novo.
        int width = image.getWidth();
        int height = image.getHeight();
        int plusHeight = 200;
        int newHeight = height + plusHeight; // pixels
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // Copiar a imagem original para nova imagem (em memória).
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(image, 0, 0, null);

        // Configurar a fonte.
        int fontSize = 64;
        var font = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(font);

        // Escrever uma frase na nova imagem.
        String phrase = "TOPZERA";
        int x = 0; // Almost the center.
        int y = (newHeight - plusHeight / 2) + fontSize / 2;
        graphics.drawString(phrase, x, y);

        // Escrever a nova imagem em um arquivo.
        String destDirName = "stickers";
        ImageIO.write(newImage, "png", new File(destDirName + File.separator + destFileName));
    }
}
