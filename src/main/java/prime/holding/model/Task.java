package prime.holding.model;

import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Task {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String title;
    
    @Column
    private String description;
    
    @OnDelete(action = OnDeleteAction.CASCADE) 
    @ManyToOne
    private Employee employee;
    
    @Column
    private LocalDate dueDate;

	public Task() {
		super();
	}

	public Task(Long id, String title, String description, Employee employee, LocalDate dueDate) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.employee = employee;
		this.dueDate = dueDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", employee=" + employee.getFullName()
				+ ", dueDate=" + dueDate + "]";
	}
    
}
