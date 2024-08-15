package day1;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginUser_usingPojo {

    @Test
    public void test(){

        RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
        RestAssured.basePath="/users/login";

        LoginPojo lp = new LoginPojo();
        lp.setEmail("112test1@fake.com");
        lp.setPassword("myPassword");


        Response response = given().contentType(ContentType.JSON).log().all().body(lp).post();

        response.prettyPrint();
        System.out.println(response.statusCode());

      String token =   response.then().extract().path("token");

      System.err.println("Token: " +token);

    }
}
