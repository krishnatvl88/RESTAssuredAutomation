package tests;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.omg.Messaging.SyncScopeHelper;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class basics8 {
	
	Properties prop = new Properties();
	@BeforeTest
	public void getData() throws IOException{
		FileInputStream fis = new FileInputStream("C:\\Users\\Kris M\\workspace\\RestAssuredAutomation\\src\\files\\env.properties");
		prop.load(fis);
	}

	@Test
	public void JiraAPIUpdate(){
		
		//Comment on issue created in basics6
		RestAssured.baseURI="http://localhost:8081";
		Response res = given().header("Content-Type","application/json").
		header("Cookie","JSESSIONID="+ReusableMethods.getSessionKey()+"").
		pathParams("commentid","10001").
		body("{"+
   "\"body\": \"Update Comment from API automation\","+
    "\"visibility\": {"+
        "\"type\": \"role\","+
        "\"value\": \"Administrators\""+
    "}"+
"}").when().
		put("/rest/api/2/issue/10003/comment/{commentid}").then().statusCode(200).extract().response();
		
		
	}
}
