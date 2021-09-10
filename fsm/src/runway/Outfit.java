package runway;

import java.util.ArrayList;

public class Outfit {
	public ArrayList<fsm.Item> items;
	public RunwayEventTeamMember model;
	
	public Outfit(ArrayList<fsm.Item> items, RunwayEventTeamMember model){
		this.items = items;
		this.model = model;
	}
	
	public Outfit(RunwayEventTeamMember model){
		this.model = model;
	}
	
	public void addItem(fsm.Item item){
		items.add(item);
	}
	
	@Override
	public String toString(){
		return this.model.getName() + " will wear " + items;
	}
}
