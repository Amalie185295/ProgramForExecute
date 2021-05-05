package softwarehusetAS;

import java.util.ArrayList;

public class Project {

	private String projectName;
	private String projectNumber;
	private ProjectManager projectManager;
	private Employee projectManager1;
	private int startWeek;
	private int endWeek;
	private ArrayList<Activity> activities = new ArrayList<Activity>();
	
	public Project(String projectName, String projectNumber) {
		this.projectName = projectName;
		this.projectNumber = projectNumber;
	}
	
	public void setStartWeek(int startWeek) {
		this.startWeek = startWeek;
	}
	
	public void setEndWeek(int endWeek) {
		this.endWeek = endWeek;
	}
	
	public ArrayList<Activity> getActivity() {
		return activities;
	}
	
	public ProjectManager setManager(Employee employee) {
		ProjectManager projectManager = new ProjectManager(employee.getInitials());
		this.projectManager = projectManager;
		projectManager1 = employee;
		return projectManager;
		
	}
	
	public Employee getManager() {
		return projectManager1;
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
	
	public int getStartWeek() {
		return startWeek;
	}
	
	public int getEndWeek() {
		return endWeek;
	}
	
	
}
