package prime.holding.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.springframework.stereotype.Component;

@Component
public class TaskDto {

    private Long id;
    
    @NotBlank
    private String title;
    
    @NotBlank
    private String description;
    
    private String employeeName;
    
    @Positive
    private long employeeId;
    
    @NotBlank
    @Pattern(regexp = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$",
    		message = "date and time must be in format yyyy-mm-dd")	
    private String dueDate;
    
   
	public TaskDto() {
		super();
	}


	public TaskDto(Long id, String title, String description, String employeeName, long employeeId, String dueDate) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.employeeName = employeeName;
		this.employeeId = employeeId;
		this.dueDate = dueDate;
	}


	public TaskDto(String title, String description, String employeeName, long employeeId, String dueDate) {
		super();
		this.title = title;
		this.description = description;
		this.employeeName = employeeName;
		this.employeeId = employeeId;
		this.dueDate = dueDate;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public long getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return "TaskDto [id=" + id + ", title=" + title + ", description=" + description + ", employeeName="
				+ employeeName + ", employeeId=" + employeeId + ", dueDate=" + dueDate + "]";
	}
    
}
