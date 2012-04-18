/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ludens.applet.restful;


import co.edu.udea.ludens.applet.util.ElementEvent;
import co.edu.udea.ludens.applet.listeners.ElementListener;
import co.edu.udea.ludens.applet.util.LudensConstants;
import co.edu.udea.ludens.domain.MessageEvent;
import co.edu.udea.ludens.enums.EnumDataType;
import co.edu.udea.ludens.enums.EnumMsgType;

import co.edu.udea.ludens.util.MessageListener;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juanga
 */
public class UtopiaRestClient  {

    private String login;
    private String urlBase;
    private List<ElementListener> elementListeners = new ArrayList<ElementListener>();
    private List<MessageListener> messageListeners = new ArrayList<MessageListener>();

    
    public static final String PATH_BASE = "/restapi/elements";
    // http://localhost:8080/Utopo/restapi/elements/materials/{login}
    public static final String URL_FORMAT_FETCH_DATA = "%s/restapi/elements%s/%s";

    // http://localhost:8080/Utopo/restapi/elements/upLevel/{elementName}/{login}"
    public static final String URL_FORMAT_ACTION = "%s/restapi/elements%s/%s/%s";

    public final static String PATH_POPULATION = "/population";
    public final static String PATH_FACTORS = "/factors";
    public final static String PATH_MATERIALS = "/materials";
    public final static String PATH_NOTIFICATIONS = "/notifications";

    public static final String ACTION_UP_LEVEL = "/upLevel";

    public final static String METHOD_GET = "GET";
    public final static String METHOD_POST = "POST";
    public final static String METHOD_PUT = "PUT";
    public final static String METHOD_DELETE = "DELETE";
    


    public UtopiaRestClient(String login, String urlBase) {

        this.login = login;
        this.urlBase = urlBase;
       


    }

    public synchronized void fetchAllElements() {

        fetchDataByType(EnumDataType.FACTOR);
        fetchDataByType(EnumDataType.MATERIAL);
        fetchDataByType(EnumDataType.POPULATION);
        fetchDataByType(EnumDataType.NOTIFICATIONS);

    }

    public void fetchDataByType(EnumDataType dataType) {

        List<Object> results = null;
        ElementEvent event;
        String msg = null;
        String requestedPath = null;

        if (EnumDataType.FACTOR == dataType) {
            requestedPath = PATH_FACTORS;

        } else if (EnumDataType.MATERIAL == dataType) {
            requestedPath = PATH_MATERIALS;

        } else if (EnumDataType.POPULATION == dataType) {
            requestedPath = PATH_POPULATION;

        } else if (EnumDataType.NOTIFICATIONS == dataType) {
            requestedPath = PATH_NOTIFICATIONS;
        }

        if (requestedPath == null) {
            return;
        }



        try {

         // http://localhost:8080/Utopo/restapi/elements/materials/{login}
            //"%s/restapi/elements%s/%s";
            String url = String.format(URL_FORMAT_FETCH_DATA,urlBase,requestedPath,login);
            Logger.getLogger("Rest").info("Callin url " + url);

            String response = sendRequest(url, METHOD_GET);

            XMLParser parser= XMLParserFactory.getInstance(dataType);

            results = parser.parseXML(response);
            Logger.getLogger("Rest").info("url fetch element" + url);
            Logger.getLogger("Rest").info("elements " + results.size());
            
            msg = LudensConstants.FETCHED_ELEMENTS;



        } catch (Exception ex) {
            Logger.getLogger(UtopiaRestClient.class.getName()).log(Level.SEVERE, null, ex);
            msg = LudensConstants.DONT_FETCH_ELEMENTS + ex;
        }

        event = new ElementEvent(this, results, dataType, msg);
        notifyElementEventListeners(event);


    }

    public void upLevel(String elementName) {

    
        MessageEvent event = null;
        String msg = null;

        try {

            elementName = URLEncoder.encode(elementName, "ISO-8859-1");

          // http://localhost:8080/Utopo/restapi/elements/upLevel/{elementName}/{login}"
          // "%s/restapi/elements%s/%s/%s";
            String url = String.format(URL_FORMAT_ACTION, urlBase,ACTION_UP_LEVEL,elementName,login);

            Logger.getLogger("Rest").info("url action " + url);
            String response = sendRequest(url, METHOD_GET);            
         

        } catch (Exception ex) {
            Logger.getLogger(UtopiaRestClient.class.getName()).log(Level.SEVERE, null, ex);
            msg = LudensConstants.DONT_FETCH_ELEMENTS + ex;
            event = new MessageEvent(this);
            event.setMsgType(EnumMsgType.ERROR);
            event.setMsg(msg);
        }

        fetchAllElements();
        notifyMessageListeners(event);
       
      

    }

    private String sendRequest(String rqAddr, String method) throws java.net.MalformedURLException, java.io.IOException, Exception {
        java.net.HttpURLConnection foo = null;
        try {

            java.net.URL rqURL = new java.net.URL(rqAddr);
            java.net.HttpURLConnection con = (java.net.HttpURLConnection) rqURL.openConnection();
            foo = con;

            if (METHOD_POST.equalsIgnoreCase(method) || METHOD_PUT.equalsIgnoreCase(method) || METHOD_DELETE.equalsIgnoreCase(method)) {
                con.setRequestMethod(METHOD_POST);
            } else {
                con.setRequestMethod(METHOD_GET);

            }

            con.setFollowRedirects(true);
            con.setAllowUserInteraction(false);
            con.setDoOutput(true);



            con.connect();


            java.io.InputStream is = con.getInputStream();
            StringBuilder sb = new StringBuilder();

            java.io.InputStreamReader reader = new java.io.InputStreamReader(is);
            String charSet = reader.getEncoding();
            int nbytes;
            byte[] buf = new byte[4096];

            do {
                nbytes = is.read(buf);
                if (nbytes > 0) {
                    sb.append(new String(buf, 0, nbytes, charSet));
                }
            } while (nbytes >= 0);

            return sb.toString();

        } catch (java.io.IOException e) {

            java.io.InputStream is = foo.getErrorStream();
            StringBuilder sb = new StringBuilder();
            java.io.InputStreamReader reader = new java.io.InputStreamReader(is);
            String charSet = reader.getEncoding();
            int nbytes;
            byte[] buf = new byte[4096];
            do {
                nbytes = is.read(buf);
                if (nbytes > 0) {
                    sb.append(new String(buf, 0, nbytes, charSet));
                }
            } while (nbytes >= 0);

            throw new Exception(sb.toString(), e);
        }
    }

    private void notifyElementEventListeners(ElementEvent event) {

        for (ElementListener listener : elementListeners) {
            listener.changeElements(event);
        }

    }


    private void notifyMessageListeners(MessageEvent event) {

        for (MessageListener listener : messageListeners) {
            listener.notifyMsg(event);
        }

    }

 


    public void addElementListener(ElementListener listener) {
        elementListeners.add(listener);

    }

    public void renoveElementListener(ElementListener listener) {
        elementListeners.remove(listener);

    }


    public void addMessageListener(MessageListener listener) {
        messageListeners.add(listener);

    }

    public void renoveMessageListener(MessageListener listener) {
         messageListeners.add(listener);

    }




}
