package prime.holding.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import prime.holding.enumeration.Country;

@Entity
public class WorkPlace {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private Country country;
	
	@Column
	private String city;
		
	@OneToMany(mappedBy = "workPlace", 
			cascade = CascadeType.ALL,
		    orphanRemoval = true)
	private List<Employee> employees;
	
	public WorkPlace() {
		super();
	}

	public WorkPlace(Long id, Country country, String city, List<Employee> employees) {
		super();
		this.id = id;
		this.country = country;
		this.city = city;
		this.employees = employees;
	}

	public WorkPlace(Country country, String city, List<Employee> employees) {
		super();
		this.country = country;
		this.city = city;
		this.employees = employees;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Long getId() {
		return id;
	}

	public long thisMonthTasksByCity() {
		LocalDate today = LocalDate.now();
		long countTasksInThisMonth = employees.stream().flatMap(e -> e.getTasks().stream()
				.filter(t -> t.getDueDate().getMonth() == today.getMonth() && t.getDueDate().getYear() == today.getYear())).count();
		return countTasksInThisMonth;													   
	}

	@Override
	public String toString() {
		return "WorkPlace [id=" + id + ", country=" + country + ", city=" + city  + "]";
	}
	
}
