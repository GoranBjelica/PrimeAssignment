package prime.holding.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDTO {
	
	private Long id;
	
	@NotBlank(message = "You have not entered a name")
	private String fullName;
	
	@NotBlank (message = "you have not entered a eMail")
	private String eMail;
	
	@NotBlank
	private String phoneNumber;
	
	@NotBlank
    @Pattern(regexp = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$",
    		message = "date and time must be in format yyyy-mm-dd")	
	private String dateOfBirth;
	
	@Positive
	private double salary;
	
	@Positive
	private Long addressId;
	
	private String country;
	
	private String city;

	public EmployeeDTO() {
		super();
	}

	public EmployeeDTO(String fullName, String eMail, String phoneNumber, String dateOfBirth, double solary) {
		super();
		this.fullName = fullName;
		this.eMail = eMail;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.salary = solary;
	}

	public EmployeeDTO(long id, String fullName, String eMail, String phoneNumber, String dateOfBirth, double solary) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.eMail = eMail;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.salary = solary;
	}

	public EmployeeDTO(Long id, String fullName, String eMail, String phoneNumber, String dateOfBirth, double solary,
			Long addressId) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.eMail = eMail;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.salary = solary;
		this.addressId = addressId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public double getSolary() {
		return salary;
	}

	public void setSolary(double solary) {
		this.salary = solary;
	}	

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [id=" + id + ", fullName=" + fullName + ", eMail=" + eMail + ", phoneNumber=" + phoneNumber
				+ ", dateOfBirth=" + dateOfBirth + ", solary=" + salary + "]";
	}

}
