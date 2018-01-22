import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.payLoad;
import files.resources;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class basics3 {
	
	Properties prop = new Properties();
	@BeforeTest
	public void getData() throws IOException{
		FileInputStream fis = new FileInputStream("C:\\Users\\Kris M\\workspace\\RestAssuredAutomation\\src\\files\\env.properties");
		prop.load(fis);
	}
	
	@Test
	public void getAndDeletePlace(){
		
	
	
	//Task-1
	
	RestAssured.baseURI=prop.getProperty("HOST");
	Response res = given().
	queryParam("key",prop.getProperty("KEY")).
	body(payLoad.getPostData()).
	when().
	post(resources.placePostData()).
	then().assertThat().statusCode(200).and().
	contentType(ContentType.JSON).and().
	body("status",equalTo("OK")).
	extract().response();
	
	//Task-2 Grab place is from response
	String response = res.asString();
	System.out.println(response);
	
	JsonPath js = new JsonPath(response);
	String place = js.get("place_id");
	System.out.println(place);
	
	
	//Tesk 3 - use place id and delete request
	given().
	queryParam("key","AIzaSyCnmpy9LRIzUAIqFb0o5RJks3zOo5n8GSM").
	body("{"+
  "\"place_id\": \""+place+"\""+
"}").
	when().
	post("/maps/api/place/delete/json").
	then().assertThat().statusCode(200).and().
	contentType(ContentType.JSON).and().
	body("status",equalTo("OK"));
	
	
	}
}
