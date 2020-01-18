package com.howtodoinjava.jerseydemo;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
  
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "products")
public class Products {
  
    @XmlElement(name="product")
    private ArrayList<Product> products;
  
    public ArrayList<Product> getProducts() {
        return products;
    }
  
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
