package softwarehusetAS;

import java.util.ArrayList;
import java.util.Arrays;

public class Employee {
	private double hours = 0;
	private String initials;
	private ArrayList<Activity> activities = new ArrayList<Activity>();
	private ArrayList<Activity> activitiesAssisting = new ArrayList<Activity>();
	private ArrayList<Activity> standardActivity = new ArrayList<>
			(Arrays.asList(new Activity("sick",false), new Activity("maternity",false), new Activity("vacation",false), new Activity("education",false)));
	private boolean hasPermission = false;
	private int activeActivities1 = 0;
	private int approvedActivities = 10;
	
	public Employee(String initials) {
		this.initials = initials;
	}
	
	public void updateHours(double before, double after, Activity activity) throws OperationNotAllowedException  {
		if (!(activity.isActivityActive() && this.checkAvailability())) {
				throw new OperationNotAllowedException(" ");
		} else {
			double newHours = after - before;
			activity.addToHours(newHours);
		}
	}
	public void addHours(double hours, Activity activity) throws OperationNotAllowedException {
		if(!(activity.isActivityActive() && this.checkAvailability())){
			throw new OperationNotAllowedException("");
		} else{
			activity.addToHours(hours);
		}
	}
	
	 public boolean checkAvailability() {
		int activeActivities = 0;
		if (activities.isEmpty()) {
			return true;
		}
		for (int i = 0 ; i < activities.size() ; i++) {
			if (activities.get(i).isActivityActive()) {
				activeActivities ++;
			}
		}
		if (activeActivities < approvedActivities) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void assignActivity(Activity activity){
	if(this.checkAvailability()){
		activities.add(activity);
		if (activity.isActivityActive()) {
        			activeActivities1++;
       		}
		}		
	}

	public Activity standardActivity(String name, double hours, int startWeek, int endWeek) throws OperationNotAllowedException{
		Activity standActivity=null;
		for (Activity a : standardActivity) {
			if (a.getName().equals(name)) {
				standActivity = a;
			}
		}
		if(standActivity==null){
			throw new OperationNotAllowedException("Enter valid standard activity");
		}
		standActivity.setIsActive();
		standActivity.setStartWeek(startWeek);
		standActivity.setEndWeek(endWeek);

		standActivity.addToHours(hours);

		return standActivity;
	}
	
	public void givePermission() {
		if (hasPermission == false) {
			hasPermission = true;
			approvedActivities = 20;
			
		}
	}
	
	public void takeAwayPermission() {
		if (hasPermission == true) {
			approvedActivities = 10;
			hasPermission = false;
		}
	
	}
	public boolean hasPermission() {
		return hasPermission;
	}

	public int getActiveActivities() {
		return activeActivities1;
	}
	
	public String getInitials() {
		return initials;
	}
	
	public int getApprovedActivities() {
		return approvedActivities;
	}
	
	public ArrayList<Activity> getActivities(){
		return activities;
	}
	public ArrayList<Activity> getAssisting(){
	    return activitiesAssisting;
	}
}
