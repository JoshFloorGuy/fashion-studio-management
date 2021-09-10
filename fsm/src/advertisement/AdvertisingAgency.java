package advertisement;

/**
 * contains data for advertising agency objects
 * 
 * @author ejshouse
 *
 */
public class AdvertisingAgency {
	private String agencyName;
	private String contactName;
	private String contactEmail;
	private double fee;

	/**
	 * constructs an advertising agency to the given parameters
	 * 
	 * @param agency
	 * @param contactN
	 * @param contactE
	 */
	public AdvertisingAgency(String agency, String contactN, String contactE, double fee) {
		agencyName = agency;
		contactN = contactName;
		contactE = contactEmail;
		this.fee = fee;
	}

	/**
	 * @return the agencyName
	 */
	public String getAgencyName() {
		return agencyName;
	}

	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * @return the contactEmail
	 */
	public String getContactEmail() {
		return contactEmail;
	}

	/**
	 * @param agencyName
	 *            the agencyName to set
	 */
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	/**
	 * @param contactName
	 *            the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * @param contactEmail
	 *            the contactEmail to set
	 */
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	/**
	 * @return the fee
	 */
	public double getFee() {
		return fee;
	}

	/**
	 * @param fee
	 *            the fee to set
	 */
	public void setFee(double fee) {
		this.fee = fee;
	}
}
