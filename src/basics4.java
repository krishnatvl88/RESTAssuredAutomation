import org.testng.annotations.Test;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

public class basics4 {
	
	@Test
	public void postDataXML() throws IOException{
		String postData = GenerateStringFromResource("C:\\Users\\Kris M\\Downloads\\data.xml");
		RestAssured.baseURI="https://maps.googleapis.com";
		
		Response res = given().
		queryParam("key","AIzaSyCnmpy9LRIzUAIqFb0o5RJks3zOo5n8GSM").
		body(postData).
		when().
		post("/maps/api/place/add/xml").
		then().assertThat().statusCode(200).and().
		contentType(ContentType.XML).extract().response();
		
		XmlPath x = ReusableMethods.rawToXML(res);
		String place = x.get("PlaceAddResponse.place_id");
		System.out.println(place);
	}
	
	public static String GenerateStringFromResource(String path) throws IOException{
		return new String(Files.readAllBytes(Paths.get(path)));
	}

}
