package runway;

import fsm.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Container class for runwayevent object
 * 
 * @author ejshouse
 *
 */
public class RunwayEvent {
	String date;
	FashionLine line;
	int duration; // duration in hours, rounded
	String venue;
	String cateringCompany;
	ArrayList<String> unpaidInvitees; // customers, publicity, etc.
	double ticketPrice;
	RunwayEventBusinessTeam businessTeam;
	
	ArrayList<Outfit> outfits;
	
	public ArrayList<Outfit> getOutfits(){
		return outfits;
	}
	
	public void setOutfits(ArrayList<Outfit> o){
		this.outfits = o;
	}

	/**
	 * constructs a runway event with the given parameters
	 * 
	 * @param date
	 * @param line
	 * @param duration
	 * @param venue
	 * @param cateringCompany
	 * @param unpaidInvitees
	 * @param ticketPrice
	 */
	public RunwayEvent(String date, FashionLine line, int duration, String venue, String cateringCompany,
			ArrayList<String> unpaidInvitees, double ticketPrice) {
		this.date = date;
		this.line = line;
		this.duration = duration;
		this.venue = venue;
		this.cateringCompany = cateringCompany;
		this.unpaidInvitees = unpaidInvitees;
		this.ticketPrice = ticketPrice;
		businessTeam = null;
	}

	public String getDate() {
		return date;
	}

	public FashionLine getLine() {
		return line;
	}

	public int getDuration() {
		return duration;
	}

	public String getVenue() {
		return venue;
	}

	public String getCateringCompany() {
		return cateringCompany;
	}

	public ArrayList<String> getUnpaidInvitees() {
		return unpaidInvitees;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public RunwayEventBusinessTeam getBusinessTeam() {
		return businessTeam;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setLine(FashionLine line) {
		this.line = line;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public void setCateringCompany(String cateringCompany) {
		this.cateringCompany = cateringCompany;
	}

	public void setUnpaidInvitees(ArrayList<String> unpaidInvitees) {
		this.unpaidInvitees = unpaidInvitees;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public void setBusinessTeam(RunwayEventBusinessTeam businessTeam) {
		this.businessTeam = businessTeam;
	}
}