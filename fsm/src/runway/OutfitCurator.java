package runway;

import java.util.ArrayList;
import java.util.Scanner;

public class OutfitCurator {
	
	public static void curateOutfits(Scanner s, RunwayEventController rw){
		ArrayList<RunwayEvent> events = (ArrayList<RunwayEvent>) rw.getEvents();
		
		if(events.size() == 0){
			System.out.println("There are no scheduled runway events, exiting");
			return;
		}
		
		System.out.println("Select a runway event:");
		
		for(int i=0; i<events.size(); i++){
			System.out.println((i+1) + " - " + events.get(i).getLine() + " on " + events.get(i).getDate());
		}
		
		int choice = 0;
		boolean succ = false;
		while(!succ){
			
			try{
				choice = Integer.parseInt(s.nextLine());
				if(choice>0 && choice<=events.size()){
					succ = true;
				}else{
					System.out.println("Selection out of range");
				}
			}catch(Exception e){
				System.out.println("Please enter an integer");
			}
			
		}
		
		if(events.get(choice-1).getBusinessTeam() == null){
			System.out.println("Selected event has no assigned business team, assign a business team first");
			return;
		}
		
		rw.selectOutfits(s, events.get(choice-1));
		
		rw.printOutfits(events.get(choice-1));
	}
}
