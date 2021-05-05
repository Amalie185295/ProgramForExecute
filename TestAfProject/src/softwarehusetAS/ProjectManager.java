package softwarehusetAS;
 
public class ProjectManager extends Employee{

	public ProjectManager(String initials) {
		super(initials);
		
	}
	
	public Activity addActivity(Project project, String activityName, boolean isActive) {
		Activity activity = new Activity(activityName,isActive);
		project.addActivityProject(activity);
		return activity;
	}
	
	public void staffActivity(Employee employee, Activity activity) {
		if (checkEmployeeAvailability(employee)) {
			activity.assignActivity(employee);
			employee.assignActivity(activity);
			
		}
		else {
			System.out.println("Employee is unavailable");
		}
	}
	
	public boolean checkEmployeeAvailability(Employee employee) {
		return employee.checkAvailability();
	}
	public void giveEmployeePermission(Employee employee) {
		employee.givePermission();
	}
	public void takeAwayEmployeePermission(Employee employee) {
		employee.takeAwayPermission();
	}
	
}
