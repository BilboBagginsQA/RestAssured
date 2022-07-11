package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.booking.CreateBooking;
import api.booking.DeleteBooking;
import api.booking.GetBooking;
import io.restassured.response.Response;
import pojo.Booking;
import pojo.BookingDetail;

public class NegativeTests extends BaseTest {
	// PASS - with appropriate error code
		
	// FAIL CASE -- as allows blank and negative values
	@Test(description = "CreateBooking with Blank name, negative amount")
	public void createBooking() throws Exception {
		Booking requestBody = new Booking("","",-1,false,null,"");
		CreateBooking createBookingRequest = new CreateBooking(appProps.getBaseURL());
		createBookingRequest.setRequestBody(requestBody);
		
		createBookingRequest.setExpectedStatusCode(400);
		createBookingRequest.perform();
		Response resp=createBookingRequest.getApiResponse();
		
		Assert.assertEquals(resp.getStatusCode(), 400);
		BookingDetail createBookingResponse = createBookingRequest.getAPIResponseAsPOJO(BookingDetail.class);
		Assert.assertEquals(requestBody, createBookingResponse.getBooking());
		
	}

	
	@Test(description = "GetBooking for ID not existing")
	public void getBooking() throws Exception {
		
		GetBooking getBookingRequest = new GetBooking(appProps.getBaseURL());
		getBookingRequest.setBookingId(0);
		getBookingRequest.setExpectedStatusCode(404);
		getBookingRequest.perform();
		Response apiResponse=getBookingRequest.getApiResponse();
		Assert.assertEquals(apiResponse.statusCode(), 404);
		//retrieving data to verify against booking request body sent
		
		

	}
}
