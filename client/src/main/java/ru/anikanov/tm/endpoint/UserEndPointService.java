package ru.anikanov.tm.endpoint;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-04-16T14:02:57.718+03:00
 * Generated source version: 3.2.7
 */
@Component
@WebServiceClient(name = "UserEndPointService",
        wsdlLocation = "http://localhost:8080/UserEndpoint?wsdl",
        targetNamespace = "http://endpoint.tm.anikanov.ru/")
public class UserEndPointService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://endpoint.tm.anikanov.ru/", "UserEndPointService");
    public final static QName UserEndPointPort = new QName("http://endpoint.tm.anikanov.ru/", "UserEndPointPort");

    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/UserEndpoint?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(UserEndPointService.class.getName())
                    .log(java.util.logging.Level.INFO,
                            "Can not initialize the default wsdl from {0}", "http://localhost:8080/UserEndpoint?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public UserEndPointService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public UserEndPointService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public UserEndPointService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public UserEndPointService(WebServiceFeature... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public UserEndPointService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public UserEndPointService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }


    /**
     * @return returns UserEndPoint
     */
    @Bean
    @WebEndpoint(name = "UserEndPointPort")
    public UserEndPoint getUserEndPointPort() {
        return super.getPort(UserEndPointPort, UserEndPoint.class);
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns UserEndPoint
     */
    @WebEndpoint(name = "UserEndPointPort")
    public UserEndPoint getUserEndPointPort(WebServiceFeature... features) {
        return super.getPort(UserEndPointPort, UserEndPoint.class, features);
    }

}
