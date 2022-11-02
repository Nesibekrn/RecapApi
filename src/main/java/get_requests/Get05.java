package get_requests;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class Get05 extends ReqresBaseUrl {
   /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}
      */

    @Test
    public void get05(){
        spec.pathParams("first","unknown","second",3);
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.statusCode(),200,"Status code did not match==>");
        softAssert.assertEquals(response.contentType(),"application/json; charset=utf-8","Content Type did not match==>");
        softAssert.assertEquals(jsonPath.getString("data.name"),"true red","name did not match==>");
        softAssert.assertEquals(jsonPath.getInt("data.year"),2002,"year did not match==>");
        softAssert.assertEquals(jsonPath.getString("data.color"),"#BF1932","color did not match==>");
        softAssert.assertEquals(jsonPath.getString("data.pantone_value"),"19-1664","pantone_value did not match==>");
        softAssert.assertEquals(jsonPath.getString("support.url"),"https://reqres.in/#support-heading","url did not match==>");
        softAssert.assertEquals(jsonPath.getString("support.text"),"To keep ReqRes free, contributions towards server costs are appreciated!","text did not match==>");

        softAssert.assertAll();

    }
}