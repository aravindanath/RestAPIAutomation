package day2;

import day1.LoginPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;

public class TC002 {
    String token = null;
    String baseUrl = "https://thinking-tester-contact-list.herokuapp.com";
    String userEmail ="112test1@fake.com";

    @Test(priority = 1)
    public void login(){
        RestAssured.baseURI=baseUrl;
        RestAssured.basePath="/users/login";

        LoginPojo lp = new LoginPojo();
        lp.setEmail("112test1@fake.com");
        lp.setPassword("myPassword");

        Response response = given().contentType(ContentType.JSON).log().all().body(lp).post();

        response.prettyPrint();
        System.out.println(response.statusCode());

         token =   response.then().extract().path("token");

        System.err.println("Token: " +token);

    }

    @Test(priority = 2)
    public void getUserProfile(){
        RestAssured.baseURI=baseUrl;
        RestAssured.basePath="/users/me";


        Response response = given().header("Authorization","Bearer "+token).log().all().get();

        response.prettyPrint();
        System.out.println(response.statusCode());

        String email =   response.then().extract().path("email");

        System.err.println("email: " +email);
        Assert.assertEquals(userEmail,email);

    }

    @Test(priority = 3)
    public void addContact() throws FileNotFoundException {
        RestAssured.baseURI=baseUrl;
        RestAssured.basePath="/contacts";

        String path = System.getProperty("user.dir")+ File.separator+"addContact.json";
        FileInputStream fis = new FileInputStream(path);

        Response response = given().contentType(ContentType.JSON).header("Authorization","Bearer "+token).log().all().body(fis).post();

        response.prettyPrint();
        System.out.println(response.statusCode());

        Assert.assertEquals(response.statusCode(),201);

    }

}
