package post_requests;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import test_data.ReqresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Post01 extends ReqresBaseUrl {
       /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
     */

    @Test
    public void post01(){
        spec.pathParam("first","users");
        ReqresTestData reqresTestData = new ReqresTestData();
        Map<String,String > expectedData = reqresTestData.reqresUsersSetUp("morpheus","leader");
        System.out.println("expectedData = " + expectedData);

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        Map<String, String> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(201,response.statusCode());
        Assert.assertEquals(expectedData.get("name"),actualData.get("name"));
        Assert.assertEquals(expectedData.get("job"),actualData.get("job"));

    }
}
