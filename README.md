# PrimeAssignment
Assignment for Prime Holding internship 2023.1
Documentation
I did this assignment with web platform using Spring Boot framework, as I learned in the course, and database as storage place. I also used Eclipse for writing the code.

Steps to start application:

You can just Import existing Maven project into IDE, and open the src folder, and top Package, "prime.holding", there is the main class called "FinalTestApplication.java", right click, and Run as Java Application.

In resources folder you can find data.sql file with initial data for database, and when application is started, the SQL schema is created in MySQL.
I divided files in classic Spring Boot structure, there is packages for: Model, Controller, Service, JPA Service, Repository, DTO, Support, Enum, and on the top there is class  ‘FinalTestApplication.java’  with the Main method.

I have three entities WorkPlace, Employee and Task.

I tested all endpoints with Postman, and here is links to test the application:

Get method: http://localhost:8080/api/employees to get a list of all employees

Get method: http://localhost:8080/api/employees/{id} to get one employee

Post method: http://localhost:8080/api/employees and employeeDto body, to create new employee

Put method: http://localhost:8080/api/employees/{id} and employeeDto to update chosen employee

Delete method: http://localhost:8080/api/employees/{id} to delete chosen employee

Get method: http://localhost:8080/api/tasks to get a list of all tasks

Get method: http://localhost:8080/api/workplaces to get a list of all Workplaces

CRUD methods for ‘tasks’ and ‘workplaces’ are same as for employees.

There is no method for updating the workplace because I think there is no sense to change City or Country name.

Workplace can be deleted only if there are no more employees in the workplace.

Third mandatory functionality – Display 5 employees who completed the largest number of tasks in the past month, you can see with Get method and endpoint:  http://localhost:8080/api/employees/best5

I created a new method in class 'Employee' which count the employee tasks in past month.

In 'EmployeeService' interface and ‘JPAEmployeeService’  I have created a method that returns a list of 5 employees who completed the most tasks. In that method I used stream API to sort (compare) and limit to top 5 employees.

In ‘WorkPlaceController’ I created two extra methods (endpoints):

1.	 Get method:  http://localhost:8080/api/workplaces/employeesPerCity/{id}   - 
In {id} you should enter the ID of the city you want to know how many employees are in the ‘Workplace’
This method returns the String message: which city (workplace) you have chosen and how many employees there are in that city.
This functionality seemed like a useful search feature to me.

2.	Get method: http://localhost:8080/api/workplaces/topcity -   this method returns the workplace that has the most tasks in the current month.
This can be one more useful functionality so you can always get the information which is city with the most tasks in this month, and give future tasks to some other 'Workplace'.
