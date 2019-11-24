package api.restassured;

import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * Unit test for simple App.
 */
public class WeatherTest extends MasterTest {

	@Test
	public void getAllWeatherHeaderData()
	{
		Response response = null;
		response = RestAssured.given().get("/Hyderabad");
		methods.info(MarkupHelper.createCodeBlock(response.getHeaders().toString()));
		for(Header header : response.getHeaders())
		{
			Report.info(header.getName() + " : " + header.getValue());
		}
	}
	
	@Test
	public void verifyWeatherStatus()
	{
		Response response = null; 
		response = RestAssured.given().get("/Hyderabad");
		Report.info("Status Code : " + response.getStatusCode());
		methods.info(MarkupHelper.createLabel("Status Code : " + response.getStatusCode(), ExtentColor.BLUE));
	}
	
	@Test
	public void verifyWeatherStatusLine()
	{
		Response response = null; 
		response = RestAssured.given().get("/Hyderabad");
		Report.info("Status Code : " + response.getStatusLine());
		methods.info(MarkupHelper.createLabel("Status Line : " + response.getStatusLine(), ExtentColor.BLUE));
	}
	
	@Test
	public void verifyWeatherJsonPath()
	{
		Response response = null; 
		JsonPath jsonPath = null;
		response = RestAssured.given().get("/Hyderabad");
		jsonPath = response.jsonPath();
		Report.info("City : " + jsonPath.get("City"));
		methods.info(MarkupHelper.createLabel("City : " + jsonPath.get("City"), ExtentColor.BLUE));
	}
	
	@Test
	public void getWeatherResponseBody()
	{
		Response response = null; 
		response = RestAssured.given().get("/Hyderabad");
		Report.info("Response : " + response.getBody().asString());
		methods.info(MarkupHelper.createCodeBlock("Response : " + response.getBody().asString()));
	}

}
