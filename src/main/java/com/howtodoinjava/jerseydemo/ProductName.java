package com.howtodoinjava.jerseydemo;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "productName")
public class ProductName implements Serializable {
  
    private static final long serialVersionUID = 1L;
  
    @XmlAttribute(name = "id")
    private int id;
  
    @XmlAttribute(name="uri")
    private String uri;
  
    @XmlElement(name = "name")
    private String name;
  
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
    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
}
