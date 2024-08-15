package day2;

import day1.LoginPojo;
import groovyjarjarantlr4.v4.codegen.model.SrcOp;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DateFormat;

import static io.restassured.RestAssured.given;

public class TC001 {
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
    public void addContact(){
        RestAssured.baseURI=baseUrl;
        RestAssured.basePath="/contacts";

        AddContact addContact = new AddContact();
        addContact.setEmail(DataGen.email());
        addContact.setCity(DataGen.city());
        addContact.setBirthdate(DataGen.dateofBirth());
        addContact.setPhone(DataGen.phonenumber());
        addContact.setCountry(DataGen.country());
        addContact.setStreet1(DataGen.street());
        addContact.setStreet2(DataGen.street());
        addContact.setPostalCode(DataGen.postalCode());
        addContact.setStateProvince(DataGen.stateProvince());
        addContact.setFirstName(DataGen.firstName());
        addContact.setLastName(DataGen.lastName());

        Response response = given().contentType(ContentType.JSON).header("Authorization","Bearer "+token).log().all().body(addContact).post();

        response.prettyPrint();
        System.out.println(response.statusCode());

        Assert.assertEquals(response.statusCode(),201);

    }

}
