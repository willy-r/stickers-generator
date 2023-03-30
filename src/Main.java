import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI uri = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        JsonParser parser = new JsonParser();
        List<Map<String, String>> moviesList = parser.parse(body);

        for (Map<String, String> movie : moviesList) {
            String movieImageUrl = movie.get("image");
            double movieRating = Double.parseDouble(movie.get("imDbRating"));
            String movieTitle = movie.get("title");

            System.out.println("Título: " + "\u001b[1m" + movieTitle + "\u001b[m");
            System.out.println("Poster: " + "\u001b[1m" + movieImageUrl + "\u001b[m");
            System.out.println("\u001b[30;1m\u001b[45;1mClassificação: " + movieRating + "\u001b[m");

            String starEmoji = "\uD83C\uDF1F";
            int movieRatingInt = (int) movieRating;
            String stars = "";
            for (int i = 0; i < movieRatingInt; i++) {
                stars += starEmoji;
            }
            System.out.println(stars);

            InputStream inputStream = new URL(movieImageUrl).openStream();
            String destFileName = movieTitle.replace(" ", "_") + ".png";
            var stickerGenerator = new StickersGenerator();
            stickerGenerator.generate(inputStream, destFileName);
        }
    }
}
