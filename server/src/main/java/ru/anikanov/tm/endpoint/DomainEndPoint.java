package ru.anikanov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anikanov.tm.api.service.IDomainService;
import ru.anikanov.tm.entity.Domain;
import ru.anikanov.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@Service
@WebService
@NoArgsConstructor
public class DomainEndPoint {
    @Autowired
    private IDomainService domainService;


    @WebMethod
    public Domain fasterJsonSerialize(@WebParam @NotNull final Session session) {
        domainService.fasterJson(session);
        return null;
    }

    @WebMethod
    public void fasterXmlSerialize(@WebParam @NotNull final Session session) {
        domainService.fasterXml(session);
    }

    @WebMethod
    public void jaxbJsonSerialize(@WebParam @NotNull final Session session) {
        domainService.jaxbJson(session);
    }

    @WebMethod
    public void jaxbXmlSerialize(@WebParam @NotNull final Session session) {
        domainService.jaxbXml(session);
    }

    @WebMethod
    public void standartSerialize(@WebParam @NotNull final Session session) {
        domainService.standartSerialize(session);
    }
}
