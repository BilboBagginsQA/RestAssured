package api.tests;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import api.booking.CreateMultipleBookings;
import api.booking.GetBookingIds;
import env.ApplicationProperties;
import env.Environment;
import io.restassured.response.Response;
import pojo.Booking;
import pojo.BookingDetail;

// This test will create N number of bookings on line 24>>> obj.createMultipleBookings(appProps.getBaseURL(), 5);
// After creation find them using getBookingIds and validate for the bookingID to be present
public class MultipleBookingTests  extends BaseTest {
	ApplicationProperties appProps = Environment.INSTANCE.getApplicationProperties();
	Booking requestBody;
	
	
	@Test(description = "Get All Bookings Created test")
	public void getBooking() throws Exception {
		
		CreateMultipleBookings obj=new CreateMultipleBookings();
		List<BookingDetail> bookingList=obj.createMultipleBookings(appProps.getBaseURL(), 5);

		GetBookingIds getBookingRequest = new GetBookingIds(appProps.getBaseURL());
		getBookingRequest.setExpectedStatusCode(200);
		getBookingRequest.perform();
		Response resp=getBookingRequest.getApiResponse();
		for (BookingDetail bookingDetail : bookingList) 
		{
			Assert.assertTrue(resp.asPrettyString().contains(String.valueOf(bookingDetail.getBookingid())));
		}
		

	}

	
}
