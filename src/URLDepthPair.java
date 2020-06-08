import java.util.Objects;

public class URLDepthPair {

    private String url;
    private int depth;

    public URLDepthPair(String host, int depth) {
        url = host;
        this.depth = depth;
    }

    public String getURL() {
        return url;
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof URLDepthPair) {
            URLDepthPair o = (URLDepthPair)obj;
            return this.url.equals(o.getURL());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }
}
