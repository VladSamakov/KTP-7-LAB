import java.io.IOException;

public class ScannerApp {
    public static void main(String args[]) throws IOException {
        // first program arguments is a link, second argument is depth of the search
        Crawler crawler = new Crawler(args[0], Integer.parseInt(args[1]));
        crawler.Scan();
        System.out.println("Depth: " + Integer.parseInt(args[1]));
        crawler.getSites();
    }
}
