package com.howtodoinjava.jerseydemo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
  
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "product")
public class Product implements Serializable {
  
    private static final long serialVersionUID = 1L;
  
    @XmlAttribute(name = "id")
    private int id;
  
    @XmlAttribute(name="uri")
    private String uri;
  
    @XmlElement(name = "name")
    private String name;
  
    @XmlElement(name = "currentPrice")
    private Price currentPrice;
  
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Price getCurrentPrice() {
        return currentPrice;
    }
    public void setCurrentPrice(Price currentPrice) {
        this.currentPrice = currentPrice;
    }
    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
}
