package paises;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class ConfigXml {
    private final String rutaXml;
    String ruta;
    int color1;
    int color2;
    int fons;


    public ConfigXml(String rutaXml) {
        this.rutaXml = rutaXml;
    }


    // text hex -> int
    static int hexToColor(String hex) {
        return Integer.parseInt(hex.replace("#", ""), 16);
    }


    static String ansiBg(int rgb) {
        return "\u001B[48;2;" + ((rgb >> 16) & 0xFF) + ";" + ((rgb >> 8) & 0xFF) + ";" + (rgb & 0xFF) + "m";
    }


    // Només text
    static String ansiFg(int rgb) {
        return "\u001B[38;2;" + ((rgb >> 16) & 0xFF) + ";" + ((rgb >> 8) & 0xFF) + ";" + (rgb & 0xFF) + "m";
    }
    public String load() throws Exception {
        Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new File(rutaXml));
        Node nodeRuta = doc.getElementsByTagName("ruta").item(0);
        Node nodeColor1 = doc.getElementsByTagName("color1").item(0);
        Node nodeColor2 = doc.getElementsByTagName("color2").item(0);
        Node nodeFons = doc.getElementsByTagName("fons").item(0);

        this.ruta = nodeRuta.getTextContent();
        this.color1 = hexToColor(nodeColor1.getTextContent());
        this.color2 = hexToColor(nodeColor2.getTextContent());
        this.fons = hexToColor(nodeFons.getTextContent());
        
        return this.ruta;
    }


}
