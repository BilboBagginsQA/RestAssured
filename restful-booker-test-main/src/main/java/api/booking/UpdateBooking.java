package api.booking;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;

import api.BaseAPI;
import env.ApplicationProperties;
import env.Environment;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import pojo.Booking;
import utilities.FrameworkConstants;

/**
* 
* Implementation of UpdateBooking PUT Service
*/

public class UpdateBooking extends BaseAPI {
	ApplicationProperties appProps = Environment.INSTANCE.getApplicationProperties();
	String apiPath = "/booking";
	int bookingId;
	String authToken;
	Booking requestBody;

	public UpdateBooking(String baseURI) {
		super(baseURI);
	}

	public void setBookingId(int bookingId) {
		this.apiPath = String.format("%s/%s", this.apiPath, bookingId);
	}
	
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
	
	public void setRequestBody(Booking booking) {
		this.requestBody = booking;
	}
	
	@Override
	protected void createRequest() {
		JSONObject requestBody = new JSONObject(this.requestBody);
		requestSpecBuilder.setBaseUri(baseURI);
		requestSpecBuilder.setBasePath(apiPath);
		requestSpecBuilder.addParam("id", bookingId); //setting of path parameter is not working, so apiPath has formed using string.format()
		PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();
	    auth.setUserName(appProps.getUsername());
	    auth.setPassword(appProps.getPassword());
		requestSpecBuilder.setAuth(auth);
		requestSpecBuilder.setContentType(ContentType.JSON);
		requestSpecBuilder.setBody(requestBody.toString());
		requestSpecification = requestSpecBuilder.build();
	}

	@Override
	protected void executeRequest() {
		apiResponse = given().spec(requestSpecification).put();
	}

	@Override
	protected void validateResponse() {
		responseSpecBuilder.expectStatusCode(expectedStatusCode);
		responseSpecification = responseSpecBuilder.build();
		apiResponse.then().spec(responseSpecification);
	}
}