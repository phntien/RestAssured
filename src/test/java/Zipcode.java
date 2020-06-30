import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import examples.BasePage;
import examples.Location;
import io.restassured.http.ContentType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.testng.Assert;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import com.github.*;

@RunWith(DataProviderRunner.class)
public class Zipcode extends BasePage {
//    private static RequestSpecification requestSpec;
//    @BeforeClass
//    public static void createRequestSpecification(){
//        requestSpec = new RequestSpecBuilder().setBaseUri("http://api.zippopotam.us").build();
//    }

    //@Rule
    //public WireMockRule wireMockRule = new WireMockRule(options().port(90210));

    @Test
    public void testZipcode(){
        Location location = given().log().all().when().get("http://api.zippopotam.us/us/90210").as(Location.class);

        Assert.assertEquals(
                "Beverly Hills",
                location.getPlace().get(0).getPlaceName()
        );
    }
    @DataProvider
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][]{
                {"us", "90210", "Beverly Hills"},
                {"us", "12345", "Schenectady"},
                {"ca", "B2R", "Waverley"},
                {"nl", "1001", "Amsterdam"}
        };
    }

    @Test
    @UseDataProvider("zipCodesAndPlaces")
    public void requestZipCodesFromCollection(String countryCode, String zipCode, String expectedPlaceName) {
        given().spec(requestSpec).pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
                when().
                get("/{countryCode}/{zipCode}").
                then().spec(responseSpecification).and().
                assertThat().
                body("places[0].'place name'", equalTo(expectedPlaceName));
    }

    @Test
    public void requestUsZipCode90210(){
        Location location =
                given().
                        when().get("http://api.zippopotam.us/us/90210").as(Location.class);
        Assert.assertEquals("Beverly Hills", location.getPlace().get(0).getPlaceName());
    }
    @Test
    public void requestUsZipCode90210_checkStatusCode_expectHttp200() {

        given().spec(requestSpec).
                when().
                get("/us/90210").
                then().spec(responseSpecification).and().
                assertThat().
                statusCode(200);
    }

    @Test
    public void requestUsZipCode90210_checkContentType_expectApplicationJson() {

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                contentType(ContentType.JSON);
    }

    @Test
    public void requestUsZipCode90210_logRequestAndResponseDetails() {

        given().
                log().all().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                log().body();
    }

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills() {

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }

    @Test
    public void requestUsZipCode90210_checkStateNameInResponseBody_expectCalifornia() {

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places[0].state", equalTo("California"));
    }

    @Test
    public void requestUsZipCode90210_checkListOfPlaceNamesInResponseBody_expectContainsBeverlyHills() {

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places.'place name'", hasItem("Beverly Hills"));
    }

    @Test
    public void requestUsZipCode90210_checkNumberOfPlaceNamesInResponseBody_expectOne() {

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places.'place name'", hasSize(1));
    }
}