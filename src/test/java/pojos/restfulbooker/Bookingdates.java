package pojos.restfulbooker;

public class Bookingdates{
	private String checkin;
	private String checkout;

	public void setCheckin(String checkin){
		this.checkin = checkin;
	}

	public String getCheckin(){
		return checkin;
	}

	public void setCheckout(String checkout){
		this.checkout = checkout;
	}

	public String getCheckout(){
		return checkout;
	}

	@Override
 	public String toString(){
		return 
			"Bookingdates{" + 
			"checkin = '" + checkin + '\'' + 
			",checkout = '" + checkout + '\'' + 
			"}";
		}
}
