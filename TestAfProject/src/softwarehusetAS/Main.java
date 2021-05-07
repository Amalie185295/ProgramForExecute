package softwarehusetAS;
import com.sun.jdi.DoubleValue;

import java.util.Scanner;

public class Main {

	private static Company SoftwarehusetAS = new Company();

	public static void main(String[] args) throws OperationNotAllowedException {
		Scanner input = new Scanner(System.in);

		SoftwarehusetAS.addEmployee("AMLT");
		Employee employee1 = SoftwarehusetAS.addEmployee("BDEF");
		SoftwarehusetAS.addEmployee("DSEU");
		SoftwarehusetAS.addEmployee("VGOK");
		SoftwarehusetAS.addEmployee("NUGT");
		Project project1 = SoftwarehusetAS.addProject("Project1", "2011");
		Project project2 = SoftwarehusetAS.addProject("Project2", "2011");
		Project project3 = SoftwarehusetAS.addProject("Project3", "2011");
		ProjectManager projectManager1 = project1.setManager(employee1);
		ProjectManager projectManager2 = project2.setManager(employee1);
		ProjectManager projectManager3 = project3.setManager(employee1);
		Activity activity1=projectManager1.addActivity(project1, "Activity", true);


		System.out.print("Please enter your personel ID: ");
		String checkID = input.next();


		if (checkForManager(checkID)!=null) {
			ProjectManager manager = checkForManager(checkID);
			System.out.println("Choose action: \n 1. Create activity \n 2. Assign employee to activity \n 3. Register hours \n 4. Ask for assistance");
			int option = input.nextInt();
			optionsManager(option,manager);

		} else if (checkForEmployee(checkID)!=null) {
			Employee employee = checkForEmployee(checkID);
			System.out.println("Choose action: \n 1. Register hours \n 2. Ask for assistance");
			int option = input.nextInt();
			optionsEmployee(option,employee);

		} else if (checkID.equals("ADMIN")) {
			System.out.println("Choose action: \n 1. Create project \n 2. Define new employee \n 3. Assign manager");
			int option = input.nextInt();
			optionsCompany(option);

		}
	}

	public static ProjectManager checkForManager(String checkID) {
		for (Project p : SoftwarehusetAS.getProject()) {
			if (p.getManager().getInitials().equals(checkID)) {
				return p.getManager();
			}
		}
		return null;
	}

	public static Employee checkForEmployee(String checkID) throws OperationNotAllowedException{
		return SoftwarehusetAS.findEmployee(checkID);
	}

	public static void optionsManager(int option, ProjectManager manager) throws OperationNotAllowedException {
		Scanner input = new Scanner(System.in);
		switch (option) {

			case 1:
				System.out.println("input project number and activity name");
				String projectNumber = input.nextLine();
				String activityName = input.nextLine();
				Project project = SoftwarehusetAS.findProject(projectNumber);
				Activity activity= manager.addActivity(project, activityName, true);
				System.out.println(activity.getName() + "is created under project:" + project.getName());

			case 2:
				System.out.println("input employee initials");
				String employeeInitials= input.nextLine();
				System.out.println("input project number and activity name");
				projectNumber = input.nextLine();
				activityName = input.nextLine();

				Employee employee = SoftwarehusetAS.findEmployee(employeeInitials);
				project = SoftwarehusetAS.findProject(projectNumber);
				activity = project.findActivity(activityName);
				manager.staffActivity(employee, activity);
				System.out.println(employee.getInitials() +"is added to the activity:" + activity.getName());
				
			case 3:
				optionsEmployee(1, manager);
			case 4:
				optionsEmployee(2, manager);		
		}
	}

	public static void optionsEmployee(int option,Employee employee) throws OperationNotAllowedException {
		Scanner input = new Scanner(System.in);
		switch (option) {
			case 1:
				System.out.println("input the project number and activity name");
				String projectNumber = input.nextLine();
				String activityName = input.nextLine();

				Project project = SoftwarehusetAS.findProject(projectNumber);
				Activity activity = project.findActivity(activityName);

				System.out.println("input hours");
				String hoursString = input.nextLine();
				double hours = Double.parseDouble(hoursString);
				employee.addHours(hours, activity);
				System.out.println(hours + "was added to activity" + activity.getName());

			case 2:
				

		}
	}

	public static void optionsCompany(int option) throws OperationNotAllowedException {
		Scanner input = new Scanner(System.in);
		switch (option) {
			case 1:
				System.out.println("input project name and the year");
				String projectName = input.nextLine();
				String year = input.nextLine();
				SoftwarehusetAS.addProject(projectName, year);
				System.out.println(projectName + "was created");
				
			case 2: 
				System.out.println("input employee initials");
				String initials = input.nextLine();
				Employee employee= SoftwarehusetAS.addEmployee(initials);
				System.out.println("welcome to the company" + employee.getInitials() + "!");

			case 3:
				System.out.println("input employee initials and and project name");
				initials = input.nextLine();
				projectName = input.nextLine();

				Employee manager = SoftwarehusetAS.findEmployee(initials);
				Project Project = SoftwarehusetAS.findProject(projectName);
				Project.setManager(manager);
				System.out.println(manager.getInitials()+"was assigned manager of project"+ Project.getName());
				}
			}
		}