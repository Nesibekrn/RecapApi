package put_requests;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import test_data.ReqresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Put01 extends ReqresBaseUrl {

        /*
        Given
            1) https://reqres.in/api/users/2
            2) {
                "name": "morpheus",
                "job": "zion president"
                }
        When
            I send Put Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "zion president",
                                                "updatedAt": "2022-10-02T11:35:05.693Z"
                                            }
     */

    @Test
    public void put01(){
        spec.pathParams("first","users","second",2);
        ReqresTestData reqresTestData = new ReqresTestData();
        Map<String,String> expectedData = reqresTestData.reqresUsersSetUp("morpheus","zion president");
        System.out.println("expectedData = " + expectedData);

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();

        Map<String ,String > actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.get("name"),actualData.get("name"));
        Assert.assertEquals(expectedData.get("job"),actualData.get("job"));

    }
}