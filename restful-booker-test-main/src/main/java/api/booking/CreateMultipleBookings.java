package api.booking;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import groovyjarjarantlr4.runtime.IntStream;
import pojo.Booking;
import pojo.BookingDetail;

public class CreateMultipleBookings {

	
	public List<BookingDetail> createMultipleBookings(String baseUrl, int numberOfBookings) throws IOException {
		List<BookingDetail> myList = new ArrayList<BookingDetail>();
		while(numberOfBookings>0)
		{
		Booking requestBody = new Booking();
		CreateBooking createBookingRequest = new CreateBooking(baseUrl);
		createBookingRequest.setExpectedStatusCode(200);
		createBookingRequest.setRequestBody(requestBody);
		createBookingRequest.perform();
		BookingDetail createBookingResponse = createBookingRequest.getAPIResponseAsPOJO(BookingDetail.class);
		myList.add(createBookingResponse);
		numberOfBookings--;
		}
		return myList;
	}
		
}
