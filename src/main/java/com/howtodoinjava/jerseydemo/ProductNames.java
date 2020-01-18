package com.howtodoinjava.jerseydemo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "productNames")
public class ProductNames {
  
    @XmlElement(name="productName")
    private ArrayList<ProductName> productNames;
  
    public ArrayList<ProductName> getProductNames() {
        return productNames;
    }
  
    public void setProductNames(ArrayList<ProductName> productNames) {
        this.productNames = productNames;
    }
}
