package com.howtodoinjava.jerseydemo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "prices")
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "price")
public class Price implements Serializable {

    private static final long serialVersionUID = 1L;

    Price(int id, double value, String currencyCode) {
        this.id = id;
        this.value = value;
        this.currencyCode = currencyCode;
    }

    @Id
    public int id;

    @XmlAttribute(name = "value")
    private double value;

    @XmlElement(name = "currencyCode")
    private String currencyCode;

    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
    public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, value='%f', currencyCode='%s']",
                id, value, currencyCode);
    }

}
