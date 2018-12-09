
package ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "SayHelloImplService", targetNamespace = "http://ws/", wsdlLocation = "http://localhost:8888/ws/hello?wsdl")
public class SayHelloImplService
    extends Service
{

    private final static URL SAYHELLOIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException SAYHELLOIMPLSERVICE_EXCEPTION;
    private final static QName SAYHELLOIMPLSERVICE_QNAME = new QName("http://ws/", "SayHelloImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8888/ws/hello?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SAYHELLOIMPLSERVICE_WSDL_LOCATION = url;
        SAYHELLOIMPLSERVICE_EXCEPTION = e;
    }

    public SayHelloImplService() {
        super(__getWsdlLocation(), SAYHELLOIMPLSERVICE_QNAME);
    }

    public SayHelloImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SAYHELLOIMPLSERVICE_QNAME, features);
    }

    public SayHelloImplService(URL wsdlLocation) {
        super(wsdlLocation, SAYHELLOIMPLSERVICE_QNAME);
    }

    public SayHelloImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SAYHELLOIMPLSERVICE_QNAME, features);
    }

    public SayHelloImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SayHelloImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SayHelloImpl
     */
    @WebEndpoint(name = "SayHelloImplPort")
    public SayHelloImpl getSayHelloImplPort() {
        return super.getPort(new QName("http://ws/", "SayHelloImplPort"), SayHelloImpl.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SayHelloImpl
     */
    @WebEndpoint(name = "SayHelloImplPort")
    public SayHelloImpl getSayHelloImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws/", "SayHelloImplPort"), SayHelloImpl.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SAYHELLOIMPLSERVICE_EXCEPTION!= null) {
            throw SAYHELLOIMPLSERVICE_EXCEPTION;
        }
        return SAYHELLOIMPLSERVICE_WSDL_LOCATION;
    }

}
