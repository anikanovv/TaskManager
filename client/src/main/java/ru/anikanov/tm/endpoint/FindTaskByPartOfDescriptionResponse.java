
package ru.anikanov.tm.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for findTaskByPartOfDescriptionResponse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="findTaskByPartOfDescriptionResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://endpoint.tm.anikanov.ru/}taskDto" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findTaskByPartOfDescriptionResponse", propOrder = {
        "_return"
})
public class FindTaskByPartOfDescriptionResponse {

    @XmlElement(name = "return")
    protected TaskDto _return;

    /**
     * Gets the value of the return property.
     *
     * @return possible object is
     * {@link TaskDto }
     */
    public TaskDto getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     *
     * @param value allowed object is
     *              {@link TaskDto }
     */
    public void setReturn(TaskDto value) {
        this._return = value;
    }

}
