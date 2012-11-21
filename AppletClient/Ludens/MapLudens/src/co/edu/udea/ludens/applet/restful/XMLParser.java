package co.edu.udea.ludens.applet.restful;

import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public interface XMLParser {

    public List<Object> parseXML(String response) throws ParserConfigurationException,
            SAXException, IOException;
}