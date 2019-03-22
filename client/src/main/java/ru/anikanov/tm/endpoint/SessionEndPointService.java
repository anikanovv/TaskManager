package ru.anikanov.tm.endpoint;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-03-22T18:04:54.550+03:00
 * Generated source version: 3.2.7
 *
 */
@WebServiceClient(name = "SessionEndPointService",
        wsdlLocation = "http://localhost:8080/SessionEndpoint?wsdl",
        targetNamespace = "http://endpoint.tm.anikanov.ru/")
public class SessionEndPointService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://endpoint.tm.anikanov.ru/", "SessionEndPointService");
    public final static QName SessionEndPointPort = new QName("http://endpoint.tm.anikanov.ru/", "SessionEndPointPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/SessionEndpoint?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(SessionEndPointService.class.getName())
                    .log(java.util.logging.Level.INFO,
                            "Can not initialize the default wsdl from {0}", "http://localhost:8080/SessionEndpoint?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public SessionEndPointService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SessionEndPointService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SessionEndPointService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public SessionEndPointService(WebServiceFeature... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public SessionEndPointService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public SessionEndPointService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }


    /**
     *
     * @return
     *     returns SessionEndPoint
     */
    @WebEndpoint(name = "SessionEndPointPort")
    public SessionEndPoint getSessionEndPointPort() {
        return super.getPort(SessionEndPointPort, SessionEndPoint.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SessionEndPoint
     */
    @WebEndpoint(name = "SessionEndPointPort")
    public SessionEndPoint getSessionEndPointPort(WebServiceFeature... features) {
        return super.getPort(SessionEndPointPort, SessionEndPoint.class, features);
    }

}
