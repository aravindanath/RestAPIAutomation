package day2;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

public class ReadYml
{


    public static String getJsonStringFromYmal(String key){
        String path = System.getProperty("user.dir")+ File.separator+"Data.yaml";
        Map<String,Object> jsonMap = null;
        try {
            Yaml yaml = new Yaml();
            Reader reader = new FileReader(path);
             jsonMap = yaml.load(reader);
        }catch (IOException e){
            e.printStackTrace();
        }
        return jsonMap.get(key).toString();
    }


    public static String setBodyValue(String body, String key, String value){
        DocumentContext docContext = com.jayway.jsonpath.JsonPath.using(Configuration.builder().build()).parse(body);
        body = docContext.set(key, value).jsonString();
        return body;
    }



}
