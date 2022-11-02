package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get04 extends HerOkuAppBaseUrl {

    /*
       Given
           https://restful-booker.herokuapp.com/booking?firstname=Guoqiang&lastname=Dominguez
       When
           User sends get request to the URL
       Then
           Status code is 200
         And
           Among the data there should be someone whose firstname is "Guoqiang" and lastname is "Dominguez"
    */
    @Test
    public void get04() {

        spec.pathParam("first", "booking").
                queryParams("firstname", "Guoqiang", "lastname", "Dominguez");
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        Assert.assertEquals(200,response.statusCode());
        Assert.assertTrue(response.asString().contains("bookingid"));

    }
}