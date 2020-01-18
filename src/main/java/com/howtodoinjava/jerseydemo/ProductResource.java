package com.howtodoinjava.jerseydemo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "products")
@Path("/products")
public class ProductResource
{
    private static Map<Integer, Product> DB = new HashMap<>();

    @GET
    @Produces("application/json")
    public Products getAllProducts() {
        Products products = new Products();
        products.setProducts(new ArrayList<>(DB.values()));
        return products;
    }
     
    @POST
    @Consumes("application/json")
    public Response createProduct(Product product) throws URISyntaxException
    {
        if(product.getName() == null || product.getCurrentPrice() == null) {
            return Response.status(400).entity("Please provide all mandatory inputs").build();
        }
        product.setId(DB.values().size()+1);
        product.setUri("/product-management/"+ product.getId());
        DB.put(product.getId(), product);
        return Response.status(201).contentLocation(new URI(product.getUri())).build();
    }
 
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getProductById(@PathParam("id") int id) throws URISyntaxException
    {
        Product product = DB.get(id);
        if(product == null) {
            return Response.status(404).build();
        }
        return Response
                .status(200)
                .entity(product)
                .contentLocation(new URI("/product-management/"+id)).build();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateProduct(@PathParam("id") int id, Product product) throws URISyntaxException
    {
        Product temp = DB.get(id);
        if(product == null) {
            return Response.status(404).build();
        }
        temp.setName(product.getName());
        temp.setCurrentPrice(product.getCurrentPrice());
        DB.put(temp.getId(), temp);
        return Response.status(200).entity(temp).build();
    }
 
    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") int id) throws URISyntaxException {
        Product product = DB.get(id);
        if(product != null) {
            DB.remove(product.getId());
            return Response.status(200).build();
        }
        return Response.status(404).build();
    }

     public static String getProductName(int id) throws ParseException {

         final String uri = "http://localhost:8080/productNames/" + id;

         String result = JerseydemoApplication.restTemplate.getForObject(uri, String.class);

         JSONParser parser = new JSONParser();
         JSONObject json = (JSONObject) parser.parse(result);

         return (String)json.get("name");
     }

     
    static
    {
        try {

            Product product1 = new Product();
            int id1 = 15197729;
            product1.setId(id1);
            product1.setName(getProductName(id1));
            Query searchPriceQuery1 = new Query(Criteria.where("id").is(id1));
            product1.setCurrentPrice(JerseydemoApplication.mongoOperation.findOne(searchPriceQuery1, Price.class));
            product1.setUri("/product-management/15197729");

            Product product2 = new Product();
            int id2 = 16483589;
            product2.setId(id2);
            product2.setName(getProductName(id2));
            Query searchPriceQuery2 = new Query(Criteria.where("id").is(id2));
            product2.setCurrentPrice(JerseydemoApplication.mongoOperation.findOne(searchPriceQuery2, Price.class));
            product2.setUri("/product-management/16483589");

            DB.put(product1.getId(), product1);
            DB.put(product2.getId(), product2);

        } catch (Throwable th) {
            Throwable cause = th.getCause();
            System.err.println("Error initializing application: " + th.getClass().getName() + " - " + th.getMessage() + " - " + (null==cause ? "null" : cause.getClass().getName()));
            th.printStackTrace(System.err);
        }
    }
}
