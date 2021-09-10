package advertisement;

import runway.*;
import fsm.*;
/**
 * contains data for advertisement objects
 * @author ejshouse
 *
 */
public class Advertisement {
	private AdvertisementBusinessTeam team;
	private String schedulingInfoNotes;
	private RunwayEvent event;
	private Photoshoot photoshoot;
	private String model;
	private String photographer;
	private FashionLine line;
	private Item item;
	private String targetAge;
	private String targetGender;
	private String targetLocation;

	public Advertisement(AdvertisementBusinessTeam team, String schedulingInfoNotes, RunwayEvent event,
			Photoshoot photoshoot, String model, String photographer, FashionLine line, Item item, String targetAge,
			String targetGender, String targetLocation) {
		this.team = team;
		this.schedulingInfoNotes = schedulingInfoNotes;
		this.event = event;
		this.photoshoot = photoshoot;
		this.model = model;
		this.photographer = photographer;
		this.line = line;
		this.item = item;
		this.targetAge = targetAge;
		this.targetGender = targetGender;
		this.targetLocation = targetLocation;
	}

	/**
	 * @return the team
	 */
	public AdvertisementBusinessTeam getTeam() {
		return team;
	}

	/**
	 * @return the schedulingInfoNotes
	 */
	public String getSchedulingInfoNotes() {
		return schedulingInfoNotes;
	}

	/**
	 * @return the event
	 */
	public RunwayEvent getEvent() {
		return event;
	}

	/**
	 * @return the photoshoot
	 */
	public Photoshoot getPhotoshoot() {
		return photoshoot;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @return the photographer
	 */
	public String getPhotographer() {
		return photographer;
	}

	/**
	 * @return the line
	 */
	public FashionLine getLine() {
		return line;
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @return the targetAge
	 */
	public String getTargetAge() {
		return targetAge;
	}

	/**
	 * @return the targetGender
	 */
	public String getTargetGender() {
		return targetGender;
	}

	/**
	 * @return the targetLocation
	 */
	public String getTargetLocation() {
		return targetLocation;
	}

	/**
	 * @param team
	 *            the team to set
	 */
	public void setTeam(AdvertisementBusinessTeam team) {
		this.team = team;
	}

	/**
	 * @param schedulingInfoNotes
	 *            the schedulingInfoNotes to set
	 */
	public void setSchedulingInfoNotes(String schedulingInfoNotes) {
		this.schedulingInfoNotes = schedulingInfoNotes;
	}

	/**
	 * @param event
	 *            the event to set
	 */
	public void setEvent(RunwayEvent event) {
		this.event = event;
	}

	/**
	 * @param photoshoot
	 *            the photoshoot to set
	 */
	public void setPhotoshoot(Photoshoot photoshoot) {
		this.photoshoot = photoshoot;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @param photographer
	 *            the photographer to set
	 */
	public void setPhotographer(String photographer) {
		this.photographer = photographer;
	}

	/**
	 * @param line
	 *            the line to set
	 */
	public void setLine(FashionLine line) {
		this.line = line;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * @param targetAge
	 *            the targetAge to set
	 */
	public void setTargetAge(String targetAge) {
		this.targetAge = targetAge;
	}

	/**
	 * @param targetGender
	 *            the targetGender to set
	 */
	public void setTargetGender(String targetGender) {
		this.targetGender = targetGender;
	}

	/**
	 * @param targetLocation
	 *            the targetLocation to set
	 */
	public void setTargetLocation(String targetLocation) {
		this.targetLocation = targetLocation;
	}
}
