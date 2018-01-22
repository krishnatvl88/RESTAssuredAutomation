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

public class basics6 {
	
	Properties prop = new Properties();
	@BeforeTest
	public void getData() throws IOException{
		FileInputStream fis = new FileInputStream("C:\\Users\\Kris M\\workspace\\RestAssuredAutomation\\src\\files\\env.properties");
		prop.load(fis);
	}

	@Test
	public void JiraAPI(){
		
		//creating isseue/defect
		RestAssured.baseURI="http://localhost:8081";
		Response res = given().header("Content-Type","application/json").
		header("Cookie","JSESSIONID="+ReusableMethods.getSessionKey()+"").
		body("{"+
    "\"fields\": {"+
        "\"project\": {"+
            "\"key\": \"RES\""+
        "},"+
        "\"summary\": \"Create Defect TWO\","+
        "\"description\": \"create sample issue TWO\","+
        "\"issuetype\": {"+
            "\"name\": \"Bug\""+
        "}"+
    "}"+
"}").when().
		post("/rest/api/2/issue/").then().statusCode(201).extract().response();
		JsonPath js = ReusableMethods.rawToJson(res);
		String issueID = js.get("id");
		System.out.println(issueID);
		
	}
}
