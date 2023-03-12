package prime.holding.web.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

@Component
public class WorkPlaceDto {
	
	private Long id;
	
	@NotBlank
	private String country;
	
	@NotBlank
	private String city;
	

	public WorkPlaceDto() {
		super();
	}	

	public WorkPlaceDto(String country, String city) {
		super();
		this.country = country;
		this.city = city;
	}

	public WorkPlaceDto(Long id, String country, String city) {
		super();
		this.id = id;
		this.country = country;
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "WokrPlaceDto [id=" + id + ", country=" + country + ", city=" + city + "]";
	}
	
}
