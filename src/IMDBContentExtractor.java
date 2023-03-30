import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IMDBContentExtractor implements ContentExtractor {
    public List<Content> extract(String jsonData) {
        JsonParser parser = new JsonParser();
        List<Map<String, String>> parsedJsonData = parser.parse(jsonData);
        List<Content> contentList = new ArrayList<>();

        for (Map<String, String> data : parsedJsonData) {
            String title = data.get("title");
            String urlImage = data.get("image");
            var content = new Content(title, urlImage);
            contentList.add(content);
        }

        return contentList;
    }
}
