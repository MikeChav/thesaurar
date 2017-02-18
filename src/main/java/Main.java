import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static spark.Spark.*;

public class Main {



    public static void main(String[] args) {
        staticFileLocation("/static");
        get("/", (req, res) -> (new Main()).renderContent());
        get("/convert", "application/json", (request, response)-> Rewriter.convert(request.queryParams("text")));
    }


    private String renderContent() {
        try {
            URL url = getClass().getResource("index.html");
            Path path = Paths.get(url.toURI());
            return new String(Files.readAllBytes(path), Charset.defaultCharset());
        } catch (IOException | URISyntaxException e) {
            return e.getMessage();

        }
    }




}