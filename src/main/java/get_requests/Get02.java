package get_requests;

import base_urls.ReqresBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get02 extends ReqresBaseUrl {
     /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body should be empty
        And
            Response body does not contain "TechProEd"
        And
            Server is "cloudflare"
     */


    @Test
    public void get02() {

        spec.pathParams("first", "users","second",23);
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        Assert.assertEquals(404,response.getStatusCode());
        Assert.assertEquals("HTTP/1.1 404 Not Found",response.getStatusLine());
        Assert.assertEquals(0, response.asString().replaceAll("[^A-Za-z0-9]", "").length());
        Assert.assertFalse(response.asString().contains("TechPro"));
        Assert.assertEquals("cloudflare",response.getHeader("Server"));

    }
}