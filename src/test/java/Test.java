import examples.BasePage;

import static io.restassured.RestAssured.given;

public class Test extends BasePage{
    BasePage basePage = new BasePage();

    public BasePage getBasePage() {
        return basePage;
    }

    public void setBasePage(BasePage basePage) {
        this.basePage = basePage;
    }
    public void requestUsZipCode90210_checkStatusCode_expectHttp200() {

        given().spec(requestSpec).
                when().
                get("/us/90210").
                then().spec(responseSpecification).and().
                assertThat().
                statusCode(200);
    }
}
