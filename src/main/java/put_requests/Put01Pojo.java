package put_requests;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.ReqresUsersPojo;
import utils.JsonUtils;

import static io.restassured.RestAssured.given;

public class Put01Pojo extends ReqresBaseUrl {
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
    public void put01Pojo(){

        spec.pathParams("first","users","second",2);
        ReqresUsersPojo expectedData = new ReqresUsersPojo("morpheus","zion president");
        System.out.println("expectedData = " + expectedData);

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();

        ReqresUsersPojo actualData = response.as(ReqresUsersPojo.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.getName(),actualData.getName());
        Assert.assertEquals(expectedData.getJob(),actualData.getJob());

    }

    @Test
    public void put01PojoObjectMapper(){
        String jsonBody = "{\n" +
                "                \"name\": \"morpheus\",\n" +
                "                \"job\": \"zion president\"\n" +
                "                }";

        spec.pathParams("first","users","second",2);
        ReqresUsersPojo expectedData = new ReqresUsersPojo("morpheus","zion president");
        System.out.println("expectedData = " + expectedData);

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();

        //ReqresUsersPojo actualData = response.as(ReqresUsersPojo.class); //==>Gson

        ReqresUsersPojo actualData =  JsonUtils.convertJsonToJava(jsonBody,ReqresUsersPojo.class);//Object Mapper
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.getName(),actualData.getName());
        Assert.assertEquals(expectedData.getJob(),actualData.getJob());

    }
}
