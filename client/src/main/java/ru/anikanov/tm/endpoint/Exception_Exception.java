
package ru.anikanov.tm.endpoint;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-03-22T13:51:09.110+03:00
 * Generated source version: 3.2.7
 */

@WebFault(name = "Exception", targetNamespace = "http://endpoint.tm.anikanov.ru/")
public class Exception_Exception extends java.lang.Exception {

    private ru.anikanov.tm.endpoint.Exception exception;

    public Exception_Exception() {
        super();
    }

    public Exception_Exception(String message) {
        super(message);
    }

    public Exception_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public Exception_Exception(String message, ru.anikanov.tm.endpoint.Exception exception) {
        super(message);
        this.exception = exception;
    }

    public Exception_Exception(String message, ru.anikanov.tm.endpoint.Exception exception, java.lang.Throwable cause) {
        super(message, cause);
        this.exception = exception;
    }

    public ru.anikanov.tm.endpoint.Exception getFaultInfo() {
        return this.exception;
    }
}
