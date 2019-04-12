package ru.anikanov.tm.endpoint;

import javax.enterprise.inject.Produces;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-04-08T17:46:06.450+03:00
 * Generated source version: 3.2.7
 */
@WebServiceClient(name = "DomainEndPointService",
        wsdlLocation = "http://localhost:8080/DomainEndpoint?wsdl",
        targetNamespace = "http://endpoint.tm.anikanov.ru/")
public class DomainEndPointService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://endpoint.tm.anikanov.ru/", "DomainEndPointService");
    public final static QName DomainEndPointPort = new QName("http://endpoint.tm.anikanov.ru/", "DomainEndPointPort");

    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/DomainEndpoint?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(DomainEndPointService.class.getName())
                    .log(java.util.logging.Level.INFO,
                            "Can not initialize the default wsdl from {0}", "http://localhost:8080/DomainEndpoint?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public DomainEndPointService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public DomainEndPointService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DomainEndPointService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public DomainEndPointService(WebServiceFeature... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public DomainEndPointService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public DomainEndPointService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }


    /**
     * @return returns DomainEndPoint
     */
    @Produces
    @WebEndpoint(name = "DomainEndPointPort")
    public DomainEndPoint getDomainEndPointPort() {
        return super.getPort(DomainEndPointPort, DomainEndPoint.class);
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns DomainEndPoint
     */
    @WebEndpoint(name = "DomainEndPointPort")
    public DomainEndPoint getDomainEndPointPort(WebServiceFeature... features) {
        return super.getPort(DomainEndPointPort, DomainEndPoint.class, features);
    }

}
