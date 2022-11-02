package patch_requests;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import test_data.ReqresTestData;
import utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Patch01 extends ReqresBaseUrl {
    /*
     Given
         1) https://reqres.in/api/users/2
         2) {
              "name": "neo"
             }
     When
          I send PATCH Request to the Url
     Then
           Status code is 200
           And response body is like   {
                                              "name": "neo",
                                              "updatedAt": "2022-10-02T12:53:21.675Z"
                                       }
  */
    @Test
    public void patch01() {

        String json = "{\n" +
                "              \"name\": \"Neo\"\n" +
                "             }";
        spec.pathParams("first", "users", "second", 2);
        ReqresTestData reqresTestData = new ReqresTestData();
        Map<String, String> expectedData = reqresTestData.reqresUsersSetUpWithMissingKey("Neo", null);
        System.out.println("expectedData = " + expectedData);

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}");
        response.prettyPrint();

        Map<String, String> actualData = JsonUtils.convertJsonToJava(json, HashMap.class);//Object Mapper
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals(expectedData.get("name"), actualData.get("name"));

    }
}
