package pojos.restfulbooker;

public class Booking{
	private String firstname;
	private String additionalneeds;
	private Bookingdates bookingdates;
	private Integer totalprice;
	private Boolean depositpaid;
	private String lastname;

	public void setFirstname(String firstname){
		this.firstname = firstname;
	}

	public String getFirstname(){
		return firstname;
	}

	public void setAdditionalneeds(String additionalneeds){
		this.additionalneeds = additionalneeds;
	}

	public String getAdditionalneeds(){
		return additionalneeds;
	}

	public void setBookingdates(Bookingdates bookingdates){
		this.bookingdates = bookingdates;
	}

	public Bookingdates getBookingdates(){
		return bookingdates;
	}

	public void setTotalprice(Integer totalprice){
		this.totalprice = totalprice;
	}

	public Integer getTotalprice(){
		return totalprice;
	}

	public void setDepositpaid(Boolean depositpaid){
		this.depositpaid = depositpaid;
	}

	public Boolean isDepositpaid(){
		return depositpaid;
	}

	public void setLastname(String lastname){
		this.lastname = lastname;
	}

	public String getLastname(){
		return lastname;
	}

	@Override
 	public String toString(){
		return 
			"Booking{" + 
			"firstname = '" + firstname + '\'' + 
			",additionalneeds = '" + additionalneeds + '\'' + 
			",bookingdates = '" + bookingdates + '\'' + 
			",totalprice = '" + totalprice + '\'' + 
			",depositpaid = '" + depositpaid + '\'' + 
			",lastname = '" + lastname + '\'' + 
			"}";
		}
}
