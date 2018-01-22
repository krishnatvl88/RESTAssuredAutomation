import org.testng.annotations.Test;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class basics5 {

	@Test
	public void extractingNamesAPI() {
		// TODO Auto-generated method stub
		
		//BaseURL or Host
		RestAssured.baseURI="https://maps.googleapis.com";
		Response res = given().
		param("location","51.503186,-0.126446").
		param("radius","500").
		param("type","museum").
		param("key","AIzaSyCnmpy9LRIzUAIqFb0o5RJks3zOo5n8GSM").
			when().
					get("/maps/api/place/radarsearch/json").
					then().assertThat().statusCode(200).and().
					contentType(ContentType.JSON).and().
					body("results[0].place_id",equalTo("ChIJq4lX1doEdkgR5JXPstgQjc0")).and().
					header("server","pablo").extract().response();
		
		JsonPath js = ReusableMethods.rawToJson(res);
		
		int count = js.get("results.size()");
		System.out.println(count);
		/*for(int i=0;i<count;i++){
			String name = js.get("results["+i+"].name");
			System.out.println(name);
		}
		*/		

	}

}
