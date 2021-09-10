package runway;

import java.util.*;

/**
 * a business team containing various different types of people involved in
 * creating a runway event
 * 
 * @author Erik
 *
 */
public class RunwayEventBusinessTeam {
	ArrayList<RunwayEventTeamMember> models;
	ArrayList<RunwayEventTeamMember> supportingStaff;
	ArrayList<RunwayEventTeamMember> managers;
	ArrayList<RunwayEventTeamMember> photographers;
	ArrayList<RunwayEventTeamMember> designers;
	ArrayList<RunwayEventTeamMember> influencers;

	/**
	 * creates a business team according to the given parameters
	 * @param models
	 * @param supportingStaff
	 * @param managers
	 * @param photographers
	 * @param designers
	 * @param influencers
	 */
	public RunwayEventBusinessTeam(ArrayList<RunwayEventTeamMember> models,
			ArrayList<RunwayEventTeamMember> supportingStaff, ArrayList<RunwayEventTeamMember> managers,
			ArrayList<RunwayEventTeamMember> photographers, ArrayList<RunwayEventTeamMember> designers,
			ArrayList<RunwayEventTeamMember> influencers) {
		this.models = models;
		this.supportingStaff = supportingStaff;
		this.managers = managers;
		this.photographers = photographers;
		this.designers = designers;
		this.influencers = influencers;
	}

	/**
	 * 
	 * @return the sum total salary of every person in this business team. salary is
	 *         defined as the amount it would cost them to work for you for that
	 *         singular runway event the reason for this is because some of these
	 *         people clearly work on commission
	 */
	public double calculateFullSalary() {
		double salary = 0;
		for (RunwayEventTeamMember model : models) {
			salary += model.salary;
		}
		for (RunwayEventTeamMember supportingStaff : supportingStaff) {
			salary += supportingStaff.salary;
		}
		for (RunwayEventTeamMember manager : managers) {
			salary += manager.salary;
		}
		for (RunwayEventTeamMember photographer : photographers) {
			salary += photographer.salary;
		}
		for (RunwayEventTeamMember designer : designers) {
			salary += designer.salary;
		}
		for (RunwayEventTeamMember influencer : influencers) {
			salary += influencer.salary;
		}
		return salary;
	}

	/**
	 * @return the models
	 */
	public ArrayList<RunwayEventTeamMember> getModels() {
		return models;
	}

	/**
	 * @return the supportingStaff
	 */
	public ArrayList<RunwayEventTeamMember> getSupportingStaff() {
		return supportingStaff;
	}

	/**
	 * @return the managers
	 */
	public ArrayList<RunwayEventTeamMember> getManagers() {
		return managers;
	}

	/**
	 * @return the photographers
	 */
	public ArrayList<RunwayEventTeamMember> getPhotographers() {
		return photographers;
	}

	/**
	 * @return the designers
	 */
	public ArrayList<RunwayEventTeamMember> getDesigners() {
		return designers;
	}

	/**
	 * @return the influencers
	 */
	public ArrayList<RunwayEventTeamMember> getInfluencers() {
		return influencers;
	}

	/**
	 * @param models the models to set
	 */
	public void setModels(ArrayList<RunwayEventTeamMember> models) {
		this.models = models;
	}

	/**
	 * @param supportingStaff the supportingStaff to set
	 */
	public void setSupportingStaff(ArrayList<RunwayEventTeamMember> supportingStaff) {
		this.supportingStaff = supportingStaff;
	}

	/**
	 * @param managers the managers to set
	 */
	public void setManagers(ArrayList<RunwayEventTeamMember> managers) {
		this.managers = managers;
	}

	/**
	 * @param photographers the photographers to set
	 */
	public void setPhotographers(ArrayList<RunwayEventTeamMember> photographers) {
		this.photographers = photographers;
	}

	/**
	 * @param designers the designers to set
	 */
	public void setDesigners(ArrayList<RunwayEventTeamMember> designers) {
		this.designers = designers;
	}

	/**
	 * @param influencers the influencers to set
	 */
	public void setInfluencers(ArrayList<RunwayEventTeamMember> influencers) {
		this.influencers = influencers;
	}
}
