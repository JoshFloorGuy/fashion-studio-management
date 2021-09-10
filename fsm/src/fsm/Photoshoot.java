package fsm;
/**
 * Container class for photoshoot object
 * @author ejshouse
 *
 */
public class Photoshoot {
	private String date;
	private String model;
	private String photographer;
	String line;
	String img;
	
	/**
	 * contstructs a photoshoot according to given parameters
	 * @param date
	 * @param model
	 * @param photographer
	 * @param line
	 * @param img
	 */
	public Photoshoot(String date, String model, String photographer, String line, String img){
		this.setDate(date);
		this.setModel(model);
		this.setPhotographer(photographer);
		this.line = line;
		this.img = img;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPhotographer() {
		return photographer;
	}

	public void setPhotographer(String photographer) {
		this.photographer = photographer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}


}
