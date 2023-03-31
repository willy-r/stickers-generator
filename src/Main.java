import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD-JamesWebbSpaceTelescope.json";
        String url = "http://localhost:8080/api/languages";

        HTTPClient client = new HTTPClient();
        String jsonData = client.getData(url);

        ProgrammingLanguagesContentExtractor extractor = new ProgrammingLanguagesContentExtractor();
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
            String phrase;

            if (contentTitle.equals("Java")) {
                phrase = "DESGRAÇA!";
            } else {
                List<String> phrasesList = Arrays.asList("TOPZERA!", "QUE DAORA!", "CONTROLLAH");
                Random rand = new Random();
                phrase = phrasesList.get(rand.nextInt(phrasesList.size()));
            }

            stickersGenerator.generate(inputStream, destFileName, phrase);
        }
    }
}
