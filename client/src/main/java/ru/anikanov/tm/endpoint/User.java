
package ru.anikanov.tm.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for user complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="user"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://endpoint.tm.anikanov.ru/}abstractEntity"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="hashPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="role" type="{http://endpoint.tm.anikanov.ru/}role" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user", propOrder = {
        "hashPassword",
        "name",
        "role"
})
public class User
        extends AbstractEntity {

    protected String hashPassword;
    protected String name;
    @XmlSchemaType(name = "string")
    protected Role role;

    /**
     * Gets the value of the hashPassword property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getHashPassword() {
        return hashPassword;
    }

    /**
     * Sets the value of the hashPassword property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setHashPassword(String value) {
        this.hashPassword = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the role property.
     *
     * @return possible object is
     * {@link Role }
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     *
     * @param value allowed object is
     *              {@link Role }
     */
    public void setRole(Role value) {
        this.role = value;
    }

}
