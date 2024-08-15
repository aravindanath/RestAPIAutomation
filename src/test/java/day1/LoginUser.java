package day1;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginUser {

    @Test
    public void test(){

        RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
        RestAssured.basePath="/users/login";

        JSONObject json = new JSONObject();
        json.put("email","112test1@fake.com");
        json.put("password","myPassword");

        Response response = given().contentType(ContentType.JSON).log().all().body(json).post();

        response.prettyPrint();
        System.out.println(response.statusCode());

      String token =   response.then().extract().path("token");

      System.err.println("Token: " +token);

    }
}
