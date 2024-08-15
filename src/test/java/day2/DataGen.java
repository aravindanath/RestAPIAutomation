package day2;

import com.github.javafaker.Faker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataGen
{



    public static String email(){
        Faker faker = new Faker(new Locale("en-IND"));
       return faker.name().firstName()+"@testmail.com";
    }

    public static String lastName(){
        Faker faker = new Faker(new Locale("en-IND"));
        return faker.name().lastName();
    }


    public static String dateofBirth(){
        Date date = new Date();
        String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
        return modifiedDate;
    }

    public static String phonenumber(){
        Faker faker = new Faker(new Locale("en-IND"));
        return faker.number().digits(10);
    }

    public static String street(){
        Faker faker = new Faker(new Locale("en-IND"));
        return faker.address().streetAddress();
    }

    public static String city(){
        Faker faker = new Faker(new Locale("en-IND"));
        return faker.address().city();
    }

    public static String stateProvince(){
        Faker faker = new Faker(new Locale("en-IND"));
        return faker.address().state();
    }
    public static String postalCode(){
        Faker faker = new Faker(new Locale("en-IND"));
        return faker.address().zipCode();
    }

    public static String country(){
        Faker faker = new Faker(new Locale("en-IND"));
        return faker.address().country();
    }

    public static String firstName() {
        Faker faker = new Faker(new Locale("en-IND"));
        return faker.name().firstName();
    }
}
