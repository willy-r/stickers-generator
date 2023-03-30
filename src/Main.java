import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD-JamesWebbSpaceTelescope.json";

        HTTPClient client = new HTTPClient();
        String jsonData = client.getData(url);

        NASAContentExtractor extractor = new NASAContentExtractor();
        List<Content> contentList = extractor.extract(jsonData);

        var stickersGenerator = new StickersGenerator();

        for (Content content : contentList) {
            String contentImageUrl = content.getImageUrl();
            String contentTitle = content.getTitle();

            System.out.println("Título: " + "\u001b[1m" + contentTitle + "\u001b[m");
            System.out.println("Imagem: " + "\u001b[1m" + contentImageUrl + "\u001b[m");
            System.out.println();

            InputStream inputStream = new URL(contentImageUrl).openStream();
            String destFileName = contentTitle.replace(" ", "_") + ".png";

            stickersGenerator.generate(inputStream, destFileName);
        }
    }
}
