package in.pickmyclick.modal;

import java.io.Serializable;
import java.util.Date;

public class UserDto implements Serializable{

	private static final long serialVersionUID = 5914173139515956840L;
	private String userId;
	private String firstName;
	private String lastName;
	private String password;
	private String confirmPassword;
	private String email;
	private String mobileNumber;
	private int enabled;
	private Date createdOn;
	private Date updatedOn;
	
	private ShippingAddress shippingAddress;
	
	
	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}



	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}



	public UserDto() {
		super();
	}
	
	
	
	public UserDto(String firstName, String lastName, String password, String confirmPassword, String email,
			String mobileNumber, int enabled, Date createdOn, Date updatedOn) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.enabled = enabled;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}



	@Override
	public String toString() {
		return "UserDto [firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", confirmPassword=" + confirmPassword + ", email=" + email + ", mobileNumber=" + mobileNumber
				+ ", enabled=" + enabled + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + "]";
	}


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	
	
	
}