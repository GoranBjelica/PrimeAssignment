package prime.holding.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String fullName;
    
    @Column
    private String email;
   
    @Column
    private String phoneNumber;
    
    @Column
    private LocalDate dateOfBirth;
    
    @Column
    private double salary;
    
    @OnDelete(action = OnDeleteAction.CASCADE) 
    @OneToMany(mappedBy = "employee")
    private List<Task> tasks;
    
    @ManyToOne
    private WorkPlace workPlace;
    
    
    public Employee() {
		super();
    }

	public Employee(Long id, String fullName, String email, String phoneNumber, LocalDate dateOfBirth, double salary,
			List<Task> tasks) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.salary = salary;
		this.tasks = tasks;
	}

	public Employee(String fullName, String email, String phoneNumber, LocalDate dateOfBirth, double salary,
			List<Task> tasks) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.salary = salary;
		this.tasks = tasks;
	}

	public Employee(Long id, String fullName, String email, String phoneNumber, LocalDate dateOfBirth, double salary,
			List<Task> tasks, WorkPlace workPlace) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.salary = salary;
		this.tasks = tasks;
		this.workPlace = workPlace;
	}


	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Long getId() {
		return id;
	}
	
	public WorkPlace getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(WorkPlace workPlace) {
		this.workPlace = workPlace;
	}

	public long tasksInPasMonthForEmployee() {
		LocalDate today = LocalDate.now();
		long countTasksInLastMonthPerEmployee;

		if(today.getMonthValue() ==1) {
			countTasksInLastMonthPerEmployee = tasks.stream().
					filter(task -> (task.getDueDate().getMonthValue() == 12 && task.getDueDate().getYear() == (today.getYear()-1))).
					count();	
			return countTasksInLastMonthPerEmployee;
		}

		countTasksInLastMonthPerEmployee = tasks.stream().
				filter(task -> (task.getDueDate().getMonth() == today.getMonth().minus(1) && task.getDueDate().getYear() == today.getYear())).
				count();	
		return countTasksInLastMonthPerEmployee;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", fullName=" + fullName + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", dateOfBirth=" + dateOfBirth + ", salary=" + salary  + "]";
	} 
    
}