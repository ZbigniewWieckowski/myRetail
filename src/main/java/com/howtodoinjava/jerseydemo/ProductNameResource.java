package com.howtodoinjava.jerseydemo;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "productNames")
@Path("/productNames")
public class ProductNameResource
{
    private static Map<Integer, ProductName> DB = new HashMap<>();
     
    @GET
    @Produces("application/json")
    public ProductNames getAllProducts() {
        ProductNames productNames = new ProductNames();
        productNames.setProductNames(new ArrayList<>(DB.values()));
        return productNames;
    }
     
    @POST
    @Consumes("application/json")
    public Response createProductName(ProductName productName) throws URISyntaxException
    {
        if(productName.getName() == null) {
            return Response.status(400).entity("Please provide all mandatory inputs").build();
        }
        productName.setId(DB.values().size()+1);
        productName.setUri("/productName-management/"+ productName.getId());
        DB.put(productName.getId(), productName);
        return Response.status(201).contentLocation(new URI(productName.getUri())).build();
    }
 
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getProductNameById(@PathParam("id") int id) throws URISyntaxException
    {
        ProductName productName = DB.get(id);
        if(productName == null) {
            return Response.status(404).build();
        }
        return Response
                .status(200)
                .entity(productName)
                .contentLocation(new URI("/productName-management/"+id)).build();
    }
 
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateProductName(@PathParam("id") int id, ProductName productName) throws URISyntaxException
    {
        ProductName temp = DB.get(id);
        if(productName == null) {
            return Response.status(404).build();
        }
        temp.setName(productName.getName());
        DB.put(temp.getId(), temp);
        return Response.status(200).entity(temp).build();
    }
 
    @DELETE
    @Path("/{id}")
    public Response deleteProductName(@PathParam("id") int id) throws URISyntaxException {
        ProductName productName = DB.get(id);
        if(productName != null) {
            DB.remove(productName.getId());
            return Response.status(200).build();
        }
        return Response.status(404).build();
    }
     
    static
    {
        ProductName productName1 = new ProductName();
        productName1.setId(15197729);
        productName1.setName("The Big Lebowski (Blue-ray) (Widescreen)");
        productName1.setUri("/productName-management/15197729");
 
        ProductName productName2 = new ProductName();
        productName2.setId(16483589);
        productName2.setName("Modern Problems (DVD) (Widescreen)");
        productName2.setUri("/productName-management/16483589");
         
        DB.put(productName1.getId(), productName1);
        DB.put(productName2.getId(), productName2);
    }
}
