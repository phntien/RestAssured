package examples;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.BeforeClass;


public class BasePage {
    protected static RequestSpecification requestSpec;
    protected static ResponseSpecification responseSpecification;
    protected String test;
    public BasePage(){

    }
    @Before
    public void setUp(){
         requestSpec = new RequestSpecBuilder().setBaseUri("http://api.zippopotam.us").build();
    }

    @BeforeClass
    public static void setresponseSpec() {
        responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    }

}
