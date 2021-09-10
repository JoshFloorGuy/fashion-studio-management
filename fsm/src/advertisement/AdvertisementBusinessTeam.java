package advertisement;

import runway.*;

import java.util.ArrayList;

import fsm.*;
import hr.*;
/**
 * contains data for advertisement business team objects
 * @author ejshouse
 *
 */
public class AdvertisementBusinessTeam {
	private Employee lead;
	private ArrayList<Employee> memberList;
	private double revenueGeneratedByTeam;
	private double sumPayoutForTeam;
	private AdvertisingAgency agency;
/**
 * constructs an advertisement business team with given parameters
 * @param lead
 * @param memberList
 * @param rev
 * @param payout
 * @param agency
 */
	public AdvertisementBusinessTeam(Employee lead, ArrayList<Employee> memberList, double rev, double payout,
			AdvertisingAgency agency) {
		this.lead = lead;
		this.memberList = memberList;
		this.revenueGeneratedByTeam = rev;
		this.sumPayoutForTeam = payout;
		this.agency = agency;
	}

	
	
	/**
	 * @return the lead
	 */
	public Employee getLead() {
		return lead;
	}

	/**
	 * @return the memberList
	 */
	public ArrayList<Employee> getMemberList() {
		return memberList;
	}

	/**
	 * @return the revenueGeneratedByTeam
	 */
	public double getRevenueGeneratedByTeam() {
		return revenueGeneratedByTeam;
	}

	/**
	 * @return the sumPayoutForTeam
	 */
	public double getSumPayoutForTeam() {
		return sumPayoutForTeam;
	}

	/**
	 * @return the agency
	 */
	public AdvertisingAgency getAgency() {
		return agency;
	}

	/**
	 * @param lead
	 *            the lead to set
	 */
	public void setLead(Employee lead) {
		this.lead = lead;
	}

	/**
	 * @param memberList
	 *            the memberList to set
	 */
	public void setMemberList(ArrayList<Employee> memberList) {
		this.memberList = memberList;
	}

	/**
	 * @param revenueGeneratedByTeam
	 *            the revenueGeneratedByTeam to set
	 */
	public void setRevenueGeneratedByTeam(double revenueGeneratedByTeam) {
		this.revenueGeneratedByTeam = revenueGeneratedByTeam;
	}

	/**
	 * @param sumPayoutForTeam
	 *            the sumPayoutForTeam to set
	 */
	public void setSumPayoutForTeam(double sumPayoutForTeam) {
		this.sumPayoutForTeam = sumPayoutForTeam;
	}

	/**
	 * @param agency
	 *            the agency to set
	 */
	public void setAgency(AdvertisingAgency agency) {
		this.agency = agency;
	}

}
