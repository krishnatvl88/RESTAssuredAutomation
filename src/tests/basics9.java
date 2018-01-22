package tests;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class basics9 {
	String ConsumerKey="GWmge66uB5wE2uqtxEeIbizuV";
	String ConsumerSecret="xW8mHvoZcIP7vRSpLHiGzazAJc9YDlMZRJZkaO6I1ncTkGmzUy";
	String Token="946735276889554945-PmBtQVd7Cj6vfLFXoTWLwM0ZntjC4ip";
	String TokenSecret="vN6gtuCzBwlxw7gvHXNxEIeWYEIAJAt7qVM4BQ7v0fHZO";
	
	@Test
	public void getLatestTweet(){
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
		Response res = given().auth().oauth(ConsumerKey,ConsumerSecret,Token,TokenSecret).
		queryParam("count","1").
		when().get("/home_timeline.json").then().extract().response(); 
		
		String response = res.asString();
		JsonPath js = new JsonPath(response);
		System.out.println(js.get("text"));
		System.out.println(js.get("id"));
	}
	
	@Test
	public void createTweet(){
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
		Response res = given().auth().oauth(ConsumerKey,ConsumerSecret,Token,TokenSecret).
		queryParam("status","test tweet two qith automation").
		when().post("/update.json").then().extract().response(); 
		
		String response = res.asString();
		JsonPath js = new JsonPath(response);
		//System.out.println(js.get("text"));
		String id=js.get("id").toString();
	}
	
	@Test
	public void deleteTweet(){
		createTweet();
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
		Response res = given().auth().oauth(ConsumerKey,ConsumerSecret,Token,TokenSecret).
		when().post("/destroy/"+id+".json").then().extract().response(); 
		
		String response = res.asString();
		JsonPath js = new JsonPath(response);
		System.out.println(js.get("Tweet which got deleted is below"));
		System.out.println(js.get("text"));
		
		System.out.println(js.get("truncated"));
	}
}
