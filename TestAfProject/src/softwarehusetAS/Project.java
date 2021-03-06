package softwarehusetAS;
//Skrevet af Amalie H
import java.util.ArrayList;

public class Project {

	private String projectName;
	private String projectNumber;
	private ProjectManager projectManager;
	private ArrayList<Activity> activities = new ArrayList<Activity>();
	
	public Project(String projectName, String projectNumber) {
		this.projectName = projectName;
		this.projectNumber = projectNumber;
	}

	
	public ArrayList<Activity> getActivity() {
		return activities;
	}
	
	public ProjectManager setManager(Employee employee) {
		ProjectManager projectManager = new ProjectManager(employee.getInitials());
		this.projectManager = projectManager;
		return projectManager;
	}
	
	public ProjectManager getManager() {
		return projectManager;
	}
	
	public void addActivityProject(Activity activity) {
		activities.add(activity);
	}
	
	public String getName() {
		return projectName;
	}
	public String getProjectNumber() {
		return projectNumber;
	}
	
	public Activity findActivity(String activityName) throws OperationNotAllowedException{
		for (Activity a: activities){
			if (activityName.equals(a.getName())){
				return a;
			}
		}
		throw new OperationNotAllowedException("Activity does not exist");
	}
}
