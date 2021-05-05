package softwarehusetAS;

import java.util.ArrayList;

public class Company {

	private ArrayList<Project> projects = new ArrayList<Project>();
	private ArrayList<Employee> employees = new ArrayList<Employee>();
	private int runningNumber = 0;
	public ArrayList<Project> getProject() {
		return projects;
	}
	
	public ArrayList<Employee> getEmployees(){
		return employees;
	}

	public Project addProject(String name, String year) {
		String str = String.format("%04d", runningNumber);
		String year1 = year.charAt(year.length()-2) + "" + year.charAt(year.length()-1);
		String serialNumber = year1 + str;
		Project project1 = new Project(name,serialNumber);
		projects.add(project1);
		runningNumber++;
		return project1;
	}
	
	public Employee addEmployee(String initials) {
		Employee employee1 = new Employee(initials);
		employees.add(employee1);
		return employee1;
	}
}
