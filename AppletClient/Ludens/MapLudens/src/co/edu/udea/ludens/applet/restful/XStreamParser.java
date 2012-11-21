package co.edu.udea.ludens.applet.restful;

import com.thoughtworks.xstream.XStream;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class XStreamParser implements XMLParser {

    private XStream xstream;

    public XStreamParser() {
        xstream = new XStream();
    }

    @Override
    public List<Object> parseXML(String response) throws ParserConfigurationException,
        SAXException, IOException {

        return ((List<Object>) xstream.fromXML(response));
    }
}