import java.io.*;
import java.net.*;
import java.util.LinkedList;


public class Crawler {

    // just alias for depth which is ignored in URLDepthPair.equals()
    final static int AnyDepth = 0;


    private LinkedList<URLDepthPair> visited = new LinkedList<URLDepthPair>();
    private LinkedList<URLDepthPair> notVisited = new LinkedList<URLDepthPair>();

    private int depth;
    private String startHost;
    // prefix has no slash to support https too
    private String prefix = "http";

    public Crawler(String host, int depth) {
        startHost = host;
        this.depth = depth;
        notVisited.add(new URLDepthPair(startHost, this.depth));
    }
    
    public void Scan() throws IOException {

       while (notVisited.size() > 0) {
           Process(notVisited.removeFirst());
           }
       }

    public void getSites() {
        // printing the links
        for (URLDepthPair elem : visited)
            System.out.println(elem.getURL());
        System.out.println("Links visited: " + visited.size());
    }

    public void Process(URLDepthPair pair) throws IOException{
        // set up a connection and follow the redirect
        URL url = new URL(pair.getURL());
        URLConnection connection = url.openConnection();
        String redirect = connection.getHeaderField("Location");
        if (redirect != null) {
            connection = new URL(redirect).openConnection();
        }
        visited.add(pair);
        if (pair.getDepth() == 0) return;

        // reading references
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String input;
        while ((input = reader.readLine()) != null) {
            while (input.contains("a href=\"" + prefix)) {
                input = input.substring(input.indexOf("a href=\"" + prefix) + 8);
                String link = input.substring(0, input.indexOf('\"'));
                if(link.contains(" "))
                    link = link.replace(" ", "%20");
                // avoid multiple visiting of the same link
                if (notVisited.contains(new URLDepthPair(link, AnyDepth)) ||
                        visited.contains(new URLDepthPair(link, AnyDepth))) continue;
                notVisited.add(new URLDepthPair(link, pair.getDepth() - 1));
            }
        }
        // close the connection
        reader.close();

    }




}
