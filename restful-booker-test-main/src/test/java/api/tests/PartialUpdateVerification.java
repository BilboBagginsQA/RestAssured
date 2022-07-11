package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.booking.CreateBooking;
import api.booking.GetBooking;
import api.booking.UpdateBooking;
import env.ApplicationProperties;
import env.Environment;
import pojo.Booking;
import pojo.BookingDetail;

// Here you update only the parameters you wish using the parameterized constructor of Booking Pojo
public class PartialUpdateVerification  extends BaseTest {
	ApplicationProperties appProps = Environment.INSTANCE.getApplicationProperties();
	Booking requestBody;

	@Test(description = "Get All Bookings Created test")
	public void updateBooking() throws Exception {
		requestBody = new Booking();
		CreateBooking createBookingRequest = new CreateBooking(appProps.getBaseURL());
		createBookingRequest.setExpectedStatusCode(200);
		createBookingRequest.setRequestBody(requestBody);
		System.out.println("UPDATE BOOKING REQUEST:"+requestBody);
		createBookingRequest.perform();

		BookingDetail createBookingResponse = createBookingRequest.getAPIResponseAsPOJO(BookingDetail.class);
		Assert.assertEquals(requestBody, createBookingResponse.getBooking());

		UpdateBooking updateBookingRequest = new UpdateBooking(appProps.getBaseURL());
		Booking newReqquestBody = new Booking("Sunit","Kole",0,false,null,null);
		updateBookingRequest.setBookingId(createBookingResponse.getBookingid());
		System.out.println(createBookingResponse.getBookingid());
		updateBookingRequest.setRequestBody(newReqquestBody);
		updateBookingRequest.setExpectedStatusCode(200);
		updateBookingRequest.perform();

		Booking updateBookingResponse = updateBookingRequest.getAPIResponseAsPOJO(Booking.class);

		GetBooking getBookingRequest = new GetBooking(appProps.getBaseURL());
		getBookingRequest.setBookingId(createBookingResponse.getBookingid());
		getBookingRequest.setExpectedStatusCode(200);
		getBookingRequest.perform();

		Booking getBookingResponse = getBookingRequest.getAPIResponseAsPOJO(Booking.class);
		Assert.assertEquals(updateBookingResponse, getBookingResponse);

	}

}
