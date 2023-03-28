import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        // Fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        URI uri = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        // Extrair só os dados que interessam (título, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> moviesList = parser.parse(body);

        // Exibir e manipular os dados
        for (Map<String, String> movie : moviesList) {
            System.out.println("Título: " + "\u001b[1m" + movie.get("title") + "\u001b[m");
            System.out.println("Poster: " + "\u001b[1m" + movie.get("image") + "\u001b[m");
            System.out.println("\u001b[30;1m\u001b[45;1mClassificação: " + movie.get("imDbRating") + "\u001b[m");
            String starEmoji = "\uD83C\uDF1F";
            double movieRating = Double.parseDouble(movie.get("imDbRating"));
            int movieRatingInt = (int) movieRating;
            String stars = "";
            for (int i = 0; i < movieRatingInt; i++) {
                stars += starEmoji;
            }
            System.out.println(stars);
        }
    }
}
