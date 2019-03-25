package ru.anikanov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
@NoArgsConstructor
public class DomainEndPoint {
    private ServiceLocator serviceLocator;

    public DomainEndPoint(@WebParam @NotNull final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @WebMethod
    public void fasterJsonSerialize(@WebParam @NotNull final Session session) {
        serviceLocator.getDomainService().fasterJson(session);
    }

    @WebMethod
    public void fasterXmlSerialize(@WebParam @NotNull final Session session) {
        serviceLocator.getDomainService().fasterXml(session);
    }

    @WebMethod
    public void jaxbJsonSerialize(@WebParam @NotNull final Session session) {
        serviceLocator.getDomainService().jaxbJson(session);
    }

    @WebMethod
    public void jaxbXmlSerialize(@WebParam @NotNull final Session session) {
        serviceLocator.getDomainService().jaxbXml(session);
    }

    @WebMethod
    public void standartSerialize(@WebParam @NotNull final Session session) {
        serviceLocator.getDomainService().standartSerialize(session);
    }
}
