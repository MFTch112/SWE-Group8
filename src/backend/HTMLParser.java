package backend;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;


public class HTMLParser {

    private int mTimeout;

    public HTMLParser(int t){
        mTimeout = t;
    }

    public String getBinaryString() throws IOException {
        Document doc = Jsoup.connect("http://qrng.anu.edu.au/RawBin.php").timeout(mTimeout).get();
        Element table = doc.select("table.rng").first();
        Element text = table.getElementsByTag("td").first();
        return text.ownText();
    }

    public String getHexString() throws IOException {
        Document doc = Jsoup.connect("http://qrng.anu.edu.au/RawHex.php").timeout(mTimeout).get();
        Element table = doc.select("table.rng").first();
        Element text = table.getElementsByTag("td").first();
        return text.ownText();
    }
}
