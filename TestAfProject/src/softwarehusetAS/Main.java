package softwarehusetAS;

import com.sun.jdi.DoubleValue;

import java.util.Scanner;

public class Main {

    private static final Company SoftwarehusetAS = new Company();
    private static boolean run = true;

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
        Activity activity1 = projectManager1.addActivity(project1, "Activity", true);


        System.out.print("Please enter your personel ID: ");
        String checkID = input.next();

        if (!(checkID.equals("ADMIN"))&&checkForManager(checkID) == null&&checkForEmployee(checkID) == null){
            System.out.println("Please enter a valid ID!");
            checkID = input.next();
        }

        if (checkID.equals("ADMIN")) {
			while (run) {
				System.out.println("Choose action: \n 1. Create project \n 2. Define new employee \n 3. Assign manager \n 4. exit");
				int option = input.nextInt();
				optionsCompany(option);
			}
		}

        else if (checkForManager(checkID) != null) {
            ProjectManager manager = checkForManager(checkID);
            while (run) {
                System.out.println("Choose action: \n 1. Create activity \n 2. Assign employee to activity \n 3. Register hours \n 4. Ask for assistance \n 5. Add details to project \n 6. Create report \n 7. Register hours on standard activity \n 8. exit");
                int option = input.nextInt();
                optionsManager(option, manager);
            }

        } else if (checkForEmployee(checkID) != null) {
            Employee employee = checkForEmployee(checkID);
            while (run) {
				System.out.println("Choose action: \n 1. Register hours \n 2. Update hours \n 3. Ask for assistance \n 4. Register hours on standard activity \n 5. exit");
				int option = input.nextInt();
				optionsEmployee(option, employee);
			}

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

    public static Employee checkForEmployee(String checkID) throws OperationNotAllowedException {
        for (Employee e: SoftwarehusetAS.getEmployees()){
            if (e.getInitials().equals(checkID)) {
                return e;
            }
        }
        return null;
    }

    public static void optionsManager(int option, ProjectManager manager) throws OperationNotAllowedException {
        Scanner input = new Scanner(System.in);

        switch (option) {
            case 1:
                System.out.println("input project number and activity name");
                String projectNumber = input.nextLine();
                String activityName = input.nextLine();
                Project project = SoftwarehusetAS.findProject(projectNumber);
                Activity activity = manager.addActivity(project, activityName, true);
                System.out.println(activity.getName() + " is created under project: " + project.getName());
                break;

            case 2:
                System.out.println("input employee initials");
                String employeeInitials = input.nextLine();
                System.out.println("input project number and activity name");
                projectNumber = input.nextLine();
                activityName = input.nextLine();

                Employee employee = SoftwarehusetAS.findEmployee(employeeInitials);
                project = SoftwarehusetAS.findProject(projectNumber);
                activity = project.findActivity(activityName);
                manager.staffActivity(employee, activity);
                System.out.println(employee.getInitials() + " is added to the activity: " + activity.getName());
                break;

            case 3:
                optionsEmployee(1, manager);
                break;

            case 4:
                optionsEmployee(2, manager);
                break;

            case 5:
                System.out.println("input project number and activity name");
                projectNumber = input.nextLine();
                activityName = input.nextLine();
                Project project1 = SoftwarehusetAS.findProject(projectNumber);
                activity = project1.findActivity(activityName);
                System.out.println("1. set start week set end week \n 2. set timebudget");
                int option1 = input.nextInt();
                switch (option1) {
                    case 1:
                        System.out.println("input start week and end week");
                        int startWeek = input.nextInt();
                        int endWeek = input.nextInt();
                        activity.setStartWeek(startWeek);
                        activity.setEndWeek(endWeek);
                        break;
                    case 2:
                        System.out.println("input timebudget");
                        int timebudget = input.nextInt();
                        activity.setTimeBudget(timebudget);
                        break;
                }
                break;
            case 6:
                System.out.println("input project number");
                projectNumber = input.nextLine();
                project = SoftwarehusetAS.findProject(projectNumber);
                System.out.println(manager.getReport(project));
                break;

            case 7: optionsEmployee(3,manager);

            case 8:
                run = false;
                break;
        }
    }


    public static void optionsEmployee(int option, Employee employee) throws OperationNotAllowedException {
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
                System.out.println(hours + " was added to activity " + activity.getName());
                break;

            case 2:
                System.out.println("input the project number and activity name");
                projectNumber = input.nextLine();
                activityName = input.nextLine();

                project = SoftwarehusetAS.findProject(projectNumber);
                activity = project.findActivity(activityName);

                System.out.println("input the before and after hours");
                String beforeHoursString = input.nextLine();
                double hoursBefore = Double.parseDouble(beforeHoursString);
                String afterHoursString = input.nextLine();
                double hoursAfter = Double.parseDouble(afterHoursString);
                employee.updateHours(hoursBefore, hoursAfter, activity);
                System.out.println("The hours on " + activity.getName() + " is now updated");
                break;

            case 3:
                System.out.println("input your coworkers name, project number and the activity name");
                String coworkerName = input.nextLine();
                projectNumber = input.nextLine();
                activityName = input.nextLine();
                Employee coworker = SoftwarehusetAS.findEmployee(coworkerName);
                project = SoftwarehusetAS.findProject(projectNumber);
                activity = project.findActivity(activityName);
                employee.askCoworker(coworker, activity);
                System.out.println("Your coworker " + coworkerName + " is added to " + activityName);
                break;

			case 4:
				System.out.println("input reason of absence, hours, start week and end week");
				System.out.println("choose between Sick, Maternity, Vacation, Education");
				String reason = input.nextLine();
				hoursString = input.nextLine();
				hours = Double.parseDouble(hoursString);
				int startWeek = input.nextInt();
				int endWeek = input.nextInt();
                System.out.println("You are registered \n " + employee.standardActivity(reason, hours, startWeek, endWeek));

			case 5:
				run = false;
				break;
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
                System.out.println(projectName + " was created");
                break;

            case 2:
                System.out.println("input employee initials");
                String initials = input.nextLine();
                Employee employee = SoftwarehusetAS.addEmployee(initials);
                System.out.println("welcome to the company " + employee.getInitials() + "!");
                break;

            case 3:
                System.out.println("input employee initials and and project number");
                initials = input.nextLine();
                String projectNumber = input.nextLine();

                Employee manager = SoftwarehusetAS.findEmployee(initials);
                Project Project = SoftwarehusetAS.findProject(projectNumber);
                Project.setManager(manager);
                System.out.println(manager.getInitials() + " was assigned manager of project " + Project.getName());
                break;

			case 4:
				run=false;
				break;
        }
    }
}