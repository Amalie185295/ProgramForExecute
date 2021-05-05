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
		projectManager1.addActivity(project1, "Activity", true);


		System.out.print("Please enter your personel ID: ");
		String checkID = input.next();


		if (checkForManager(checkID)) {
			System.out.println("Choose action: \n 1. Create activity \n 2. Assign employee to activity");
			int option = input.nextInt();
			optionsManager(option);

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

	public static boolean checkForManager(String checkID) {
		for (Project p : SoftwarehusetAS.getProject()) {
			if (p.getManager().getInitials().equals(checkID)) {
				return true;
			}
		}
		return false;
	}

	public static Employee checkForEmployee(String checkID) {
		Employee employee = null;
		for (Employee e : SoftwarehusetAS.getEmployees()) {
			if (e.getInitials().equals(checkID)) {
				employee = e;
			}
		}
		return employee;
	}

	public static void optionsManager(int option) {
		Scanner input = new Scanner(System.in);
		switch (option) {

			case 1:
				System.out.println("input activity name");
				String ActivityName = input.nextLine();
				Activity activity1 = new Activity(ActivityName, true);

			case 2:

		}
	}

	public static void optionsEmployee(int option,Employee employee) throws OperationNotAllowedException {
		Scanner input = new Scanner(System.in);
		switch (option) {
			case 1:
				Project project;
				Activity activity;
				System.out.println("input the project");
				String projectName = input.nextLine();

				for (Project p : SoftwarehusetAS.getProject()){
					if (projectName.equals(p.getName())){
						project = p;
						System.out.println("input the activity");
						String activityName = input.nextLine();

						for (Activity a : project.getActivity()){
							if(activityName.equals(a.getName()));
							activity = a;
							System.out.println("input hours");
							String hoursString = input.nextLine();
							double hours = Double.parseDouble(hoursString);
							employee.addHours(hours, activity);
							System.out.println(activity.getHours());
						}
					}
				}
			case 2:

		}
	}

	public static void optionsCompany(int option) {
		Scanner input = new Scanner(System.in);
		switch (option) {
			case 1:
				System.out.println("input project name and the year");
				String projectName = input.nextLine();
				String year = input.nextLine();
				SoftwarehusetAS.addProject(projectName, year);

			case 2:
				System.out.println("input employee initials and and project name");
				String initials = input.nextLine();
				projectName = input.nextLine();
				Employee manager;
				for (Employee e : SoftwarehusetAS.getEmployees()) {
					if (initials.equals(e.getInitials())) {
						manager = e;
						for (Project p : SoftwarehusetAS.getProject()) {
							if (projectName.equals(p.getName())) {
								p.setManager(manager);
						}
					}
				}
			}
		}
	}
}
