package fsm.sales;

import fsm.*;
import java.util.ArrayList;

/**
 * This represents a contract between a company/lead and
 * the fashion studio
 * @author joshf
 *
 */
public class Agreement {
	private Meeting meeting;
	private double price;
	private ArrayList<FashionLine> lines;

	public Agreement(Meeting m, double p, ArrayList<FashionLine> f) {
		meeting = m;
		price = p;
		lines = f;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public double getPrice() {
		return price;
	}

	public ArrayList<FashionLine> getLines() {
		return lines;
	}
}
