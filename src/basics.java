import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class basics {

	@Test
	public void getPlaceAPI() {
		// TODO Auto-generated method stub
		
		//BaseURL or Host
		RestAssured.baseURI="https://maps.googleapis.com";
		given().
		param("location","51.503186,-0.126446").
		param("radius","500").
		param("type","restaurants").
		param("key","AIzaSyCnmpy9LRIzUAIqFb0o5RJks3zOo5n8GSM").log().all().
			when().
					get("/maps/api/place/radarsearch/json").
					then().assertThat().statusCode(200).and().
					contentType(ContentType.JSON).and().
					body("results[0].place_id",equalTo("ChIJc9zIO88EdkgRtiF_0UhDLZk")).and().
					header("server","pablo").log().body();
				

	}

}
