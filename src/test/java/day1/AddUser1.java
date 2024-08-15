package day1;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddUser1 {

    @Test
    public void test(){

        RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
        RestAssured.basePath="/users";

        Response response = given().contentType(ContentType.JSON).log().all().body("\n" +
                "{\n" +
                "    \"firstName\": \"Testt\",\n" +
                "    \"lastName\": \"Useer\",\n" +
                "    \"email\": \"112test1@fake.com\",\n" +
                "    \"password\": \"myPassword\"\n" +
                "}").post();

        response.prettyPrint();
        System.out.println(response.statusCode());

    }
}
