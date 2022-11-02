package get_requests;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Get06 extends ReqresBaseUrl {
       /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then
            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */

    @Test
    public void get06(){

        spec.pathParam("first","unknown");
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

//        1)Status code is 200
        Assert.assertEquals(200,response.statusCode());

//        2)Print all pantone_values
        JsonPath jsonPath = response.jsonPath();
        List<String> pantone_values =  jsonPath.getList("data.pantone_value");
        System.out.println("pantone_values = " + pantone_values);

//        3)Print all ids greater than 3 on the console
        List<Integer> ids = jsonPath.getList("data.findAll{it.id>3}.id");//Groovy Language
        System.out.println("ids greater than 3 = " + ids);

//        Assert that there are 3 ids greater than 3
        Assert.assertEquals(3,ids.size());

//        4)Print all names whose ids are less than 3 on the console
        List<String> names = jsonPath.getList("data.findAll{it.id<3}.name");
        System.out.println("names whose ids are less than 3 = " + names);

//        Assert that the number of names whose ids are less than 3 is 2
        Assert.assertEquals(2,names.size());

    }

}