package pojos.restfulbooker;

public class BookerResponse {
	private Booking booking;
	private Integer bookingid;

	public void setBooking(Booking booking){
		this.booking = booking;
	}

	public Booking getBooking(){
		return booking;
	}

	public void setBookingid(Integer bookingid){
		this.bookingid = bookingid;
	}

	public Integer getBookingid(){
		return bookingid;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"booking = '" + booking + '\'' + 
			",bookingid = '" + bookingid + '\'' + 
			"}";
		}
}
