package fsm.sales;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import fsm.*;

/**
 * This is the controller for the manage Leads methods
 * @author joshf
 *
 */
public class SalesController {
	ArrayList<SalesRep> salesreps;
	ArrayList<Lead> leads;
	Scanner n;
	
	public SalesController() {
		n = new Scanner(System.in);
		salesreps = new ArrayList<SalesRep>();
		leads = new ArrayList<Lead>();
		addNewSalesRep("John");
		addNewSalesRep("Nancy");
		addNewSalesRep("Funny Third Option");
	}
	
	public void manageLeads() {
		boolean c = true;
		while(c) {
			System.out.println("");
			System.out.println("1. Log in as Marketing Representative");
			System.out.println("2. Log in as Manager");
			System.out.println("3. Log in as Sales Representative");
			System.out.println("'q' to quit");
			System.out.print(": ");
			String in = n.nextLine();
			System.out.println("");
			
			switch(in) {
				case "1":
					marketingController();
					break;
				case "2":
					managerController();
					break;
				case "3":
					salesRepController();
					break;
				case "q":
					c = false;
					break;
				default:
					System.out.println("Invalid option");
					break;
			}
		}
	}
	
	/**
	 * This contains all the actions/use cases for a marketing representative.
	 * 
	 * 1. Add new lead: Create a new lead in the system.
	 * 2. View all leads: View a list of all the leads in the system
	 * 3. Delete non-booked leads: If a lead does not have a meeting, 
	 *    this method will delete it.
	 *    
	 *  On return, this should return to the manageLeads() loop.
	 */
	public void marketingController() {
		boolean c = true;
		while(c) {
			System.out.println("");
			System.out.println("1. Add new lead");
			System.out.println("2. View all leads");
			System.out.println("3. Delete non-booked leads");
			System.out.println("'q' to quit");
			System.out.print(": ");
			String in = n.nextLine();
			System.out.println("");
			boolean c2;
			switch(in) {
				case "1":
					System.out.print("Name of the new company: ");
					String company = n.nextLine();
					c2 = true;
					ArrayList<Contact> contacts = new ArrayList<Contact>();
					while(c2) {
						System.out.print("Name of contact: ");
						String name = n.nextLine();
						System.out.print("Phone number: ");
						String pn = n.nextLine();
						contacts.add(new Contact(name, pn));
						String in2 = "";
						while(!in2.equals("y") && !in2.equals("n")) {
							System.out.print("Is this the last contact? (y/n): ");
							in2 = n.nextLine();
							if(!in2.equals("y") && !in2.equals("n")) {
								System.out.println("Please input 'y' or 'n'");
							}
						}
						if(in2.equals("y")) c2 = false;
					}
					System.out.println("Created new lead "+addNewLead(company, contacts));
					break;
				case "2":
					printAllLeads();
					break;
				case "3":
					c2 = true;
					while(c2) {
						System.out.println("Please enter a list of leads you would like to delete.");
						System.out.println("(Separate each id by a comma)");
						System.out.println("'q' to quit");
						System.out.print(":");
						String in2 = n.nextLine();
						in2 = in2.replaceAll(" ", "");
						if(!in2.equals("q")) {
							String sub;
							int ID;
							Iterator<Lead> q;
							while(in2.length()>0) {
								sub = in2.substring(0,(in2.indexOf(',')>-1) ? in2.indexOf(',') : in2.length());
								try {
									ID = Integer.parseInt(sub);
									q = leads.iterator();
									boolean didIt = false;
									while(q.hasNext() && !didIt) {
										Lead e = q.next();
										if(e.getLeadID()==ID) {
											if(e.getMeeting()==null) {
												leads.remove(e);
												if(e.getSalesRep()!=null) e.getSalesRep().removeLead(e);
												System.out.println("Deleted lead "+e.getLeadID());
											} else {
												System.out.println("Error: Lead "+e.getLeadID()+" is booked, cannot delete");
											}
										}
									}
									if(!didIt) System.out.println("Error: Lead "+sub+" does not exist");
								} catch(Exception e) {
									System.out.println("Error: '"+sub+"' is not a valid ID.");
								}
								in2 = in2.substring((in2.indexOf(',')>-1) ? in2.indexOf(',')+1 : in2.length(),in2.length());
							}
						} else {
							c2 = false;
						}
					}
					break;
				case "q":
					c = false;
					break;
				default:
					System.out.println("Invalid option");
					break;
			}
		}
	}
	
	/**
	 * This contains the manager's actions/use cases. It consists of the following:
	 * 
	 * 1. View sales representatives: Displays list of representatives
	 * 2. View unassigned leads: Displays list of unassigned leads
	 * 3. Assign Leads: For all specified reps and lead IDs, leads are assigned to
	 *    their representatives
	 * 4. Unassign Leads: Given all rep IDs, displays current leads, and unassigns
	 * 	  the specified ones
	 * 
	 * On exit of the loop, the flow should return to the manageLeads() loop.
	 */
	public void managerController() {
		boolean c = true;
		while(c) {
			System.out.println("");
			System.out.println("1. View Sales Representatives");
			System.out.println("2. View unassigned Leads");
			System.out.println("3. Assign leads");
			System.out.println("4. Unassign leads");
			System.out.println("'q' to quit");
			System.out.print(": ");
			String in = n.nextLine();
			System.out.println("");
			String reps;
			String sub;
			ArrayList<Lead> temp;
			switch(in) {
				case "1":
					printSalesReps();
					break;
				case "2":
					printUnassignedLeads();
					break;
				case "3":
					printSalesReps();
					System.out.println("Please input one or more sales rep IDs to continue");
					System.out.println("(Separate IDs with a comma)");
					reps = n.nextLine();
					reps = reps.replaceAll(" ","");
					while(reps.length()>0) {
						sub = reps.substring(0,(reps.indexOf(',') > -1) ? reps.indexOf(',') : reps.length());
						try {
							int id = Integer.parseInt(sub);
							Iterator<SalesRep> i = salesreps.iterator();
							SalesRep j;
							boolean isFound = false;
							while(!isFound && i.hasNext()) {
								j = i.next();
								if(id==j.getSalesRepID()) {
									isFound=true;
									temp = printUnassignedLeads();
									System.out.println("Please specify one ore more lead IDs you would like to assign to "+j.getName());
									System.out.println("(Separate IDs with a comma)");
									String lead = n.nextLine();
									Iterator<Lead> w;
									String sublead;
									while(lead.length()>0) {
										sublead = lead.substring(0,(lead.indexOf(',') > -1) ? lead.indexOf(',') : lead.length());
										try {
											int leadID = Integer.parseInt(sublead);
											w = temp.iterator();
											Lead x;
											boolean foundLead = false;
											while(!foundLead && w.hasNext()) {
												x = w.next();
												if(x.getLeadID()==leadID) {
													foundLead = true;
													j.addLead(x);
													x.setSalesRep(j);
													System.out.println("Lead "+leadID+" assigned to Rep "+id);
												}
											}
											if(!foundLead) System.out.println("Error: lead "+leadID+" not found");
										} catch(Exception e) {
											System.out.println("Error: '"+sublead+"' is not a valid ID");
										}
										lead = lead.substring((lead.indexOf(',') > -1) ? lead.indexOf(',')+1 : lead.length(), lead.length());
									}
								}
							}
							if(!isFound) System.out.println("Error: rep "+sub+" not found");
						} catch(Exception e) {
							System.out.println("Error: '"+sub+"' is not a valid ID");
						}
						reps = reps.substring((reps.indexOf(',') > -1) ? reps.indexOf(',')+1 : reps.length(),reps.length());
					}
					break;
				case "4":
					printSalesReps();
					System.out.println("Please input one or more sales rep IDs to continue");
					System.out.println("(Separate IDs with a comma)");
					reps = n.nextLine();
					reps = reps.replaceAll(" ","");
					while(reps.length()>0) {
						sub = reps.substring(0,(reps.indexOf(',') > -1) ? reps.indexOf(',') : reps.length());
						try {
							int id = Integer.parseInt(sub);
							Iterator<SalesRep> i = salesreps.iterator();
							SalesRep j;
							boolean isFound = false;
							while(!isFound && i.hasNext()) {
								j = i.next();
								if(id==j.getSalesRepID()) {
									isFound=true;
									temp = j.printManagableLeads();
									System.out.println("Please specify one ore more lead IDs you would like to unassign from "+j.getName());
									System.out.println("(Separate IDs with a comma)");
									String lead = n.nextLine();
									Iterator<Lead> w;
									String sublead;
									while(lead.length()>0) {
										sublead = lead.substring(0,(lead.indexOf(',') > -1) ? lead.indexOf(',') : lead.length());
										try {
											int leadID = Integer.parseInt(sublead);
											w = temp.iterator();
											Lead x;
											boolean foundLead = false;
											while(!foundLead && w.hasNext()) {
												x = w.next();
												if(x.getLeadID()==leadID) {
													foundLead = true;
													x.setSalesRep(null);
													j.removeLead(x);
													System.out.println("Lead "+leadID+" unassigned from Rep "+id);
												}
											}
											if(!foundLead) System.out.println("Error: lead "+leadID+" not found");
										} catch(Exception e) {
											System.out.println("Error: '"+sublead+"' is not a valid ID");
										}
										lead = lead.substring((lead.indexOf(',') > -1) ? lead.indexOf(',')+1 : lead.length(), lead.length());
									}
								}
							}
							if(!isFound) System.out.println("Error: rep "+sub+" not found");
						} catch(Exception e) {
							System.out.println("Error: '"+sub+"' is not a valid ID");
						}
						reps = reps.substring((reps.indexOf(',') > -1) ? reps.indexOf(',')+1 : reps.length(),reps.length());
					}
					break;
				case "q":
					c = false;
					break;
				default:
					System.out.println("Invalid option");
					break;
			}
		}
	}
	
	/**
	 * This contains all the actions/use cases for a sales
	 * representative. To access these, a user must log in.
	 * 
	 * 1. View leads: displays a list of active leads
	 * 2. View meetings: displays a list of booked meetings
	 * 3. Set up a Meeting: Given a lead ID, the user will be
	 *    prompted to set up a meeting
	 * 4. Start Meeting: Given a meeting ID, the presentation
	 *    of fashion lines will begin, and the user will
	 *    be prompted to make an agreement (if there is one)
	 *    
	 * On exit of the loop, the flow should return to the manageLeads() loop.
	 */
	public void salesRepController() {
		boolean b = true;
		SalesRep user = null;
		while(b) {
			System.out.print("Please enter your salesRepID ('q' to quit): ");
			String in = n.nextLine();
			if(in.equals("q")) {
				b = false;
			} else {
				try {
					int i = Integer.parseInt(in);
					Iterator<SalesRep> a = salesreps.iterator();
					while(user==null && a.hasNext()) {
						user = a.next();
						b = false;
						if(i!=user.getSalesRepID()) {
							user = null;
							b=true;
						}
					}
					if(user==null) System.out.println("Error: sales rep "+i+" not found");
				} catch(Exception e) {
					user = null;
					System.out.println("Error: '"+in+"' is not a valid id");
				}
			}
		}
		if(user!=null) {
			System.out.println("Welcome, "+user.getName());
			boolean c = true;
			ArrayList<Lead> tempL;
			ArrayList<Meeting> tempM;
			while(c) {
				System.out.println("");
				System.out.println("1. View Leads");
				System.out.println("2. View Meetings");
				System.out.println("3. Set Up Meeting");
				System.out.println("4. Start Meeting");
				System.out.println("'q' to quit");
				System.out.print(": ");
				String in = n.nextLine();
				System.out.println("");
				String in2;
				switch(in) {
					case "1":
						user.printFullLeads();
						break;
					case "2":
						user.printMeetings();
						break;
					case "3":
						tempL = user.printFullLeads();
						System.out.println("Please select the lead that you would like to set up a meeting with.");
						System.out.print("('q' to quit): ");
						in2 = n.nextLine();
						if(!in2.equals("q")) {
							try {
								int tempLeadID = Integer.parseInt(in2)-1;
								if(!(tempLeadID<0 || tempLeadID>=tempL.size())) {
									Lead j = tempL.get(tempLeadID);
									j.printContacts();
									System.out.println("Please contact these representatives.");
									while(!in2.equals("y") && !in2.equals("n")) {
										System.out.print("Was a meeting set up? (y/n): ");
										in2 = n.nextLine();
										if(!in2.equals("y") && !in2.equals("n")) System.out.println("Please input 'y' or 'n'");
									}
									if(in2.equals("y")) {
										System.out.print("Please specify a time to meet: ");
										in2 = n.nextLine();
										j.setMeeting(new Meeting(j,in2, DatabaseSupport.getLines()));
										user.addMeeting(j.getMeeting());
									}
								} else {
									System.out.println("Error: Lead "+tempLeadID+" does not exist");
								}
							} catch(Exception e) {
								System.out.println("Error: '"+in2+"' is not a valid ID");
							}
						}
						break;
					case "4":
						tempM = user.printMeetings();
						System.out.println("Please select the meeting that you would like to start.");
						System.out.print("('q' to quit): ");
						in2 = n.nextLine();
						if(!in2.equals("q")) {
							try {
								int tempMeetingID = Integer.parseInt(in2)-1;
								if(!(tempMeetingID<0 || tempMeetingID>=tempM.size())) {
									Meeting j = tempM.get(tempMeetingID);
									j.startMeeting();
									while(!in2.equals("y") && !in2.equals("n")) {
										System.out.print("Does the lead want to make an agreement? (y/n): ");
										in2 = n.nextLine();
										if(!in2.equals("y") && !in2.equals("n")) System.out.println("Please input 'y' or 'n'");
									}
									if(in2.equals("y")) {
										double p = 0;
										boolean e = true;
										while(e) {
											System.out.print("Enter the agreed price: ");
											try {
												p = n.nextDouble();
												e=false;
											} catch(Exception f) {
												n.nextLine();
												System.out.println("Please only enter a number.");
											}
										}
										ArrayList<FashionLine> want = new ArrayList<FashionLine>();
										Iterator<FashionLine> fl = j.getLines().iterator();
										while(fl.hasNext()) {
											FashionLine k = fl.next();
											in2="";
											while(!in2.equals("y") && !in2.equals("n")) {
												System.out.print("Does the lead want "+k+"? (y/n): ");
												in2 = n.nextLine();
												if(!in2.equals("y") && !in2.equals("n")) System.out.println("Please input 'y' or 'n'");
											}
											if(in2.equals("y")) want.add(k);
										}
										j.setAgreement(new Agreement(j,p,want));
									}
								} else {
									System.out.println("Error: Meeting "+tempMeetingID+" does not exist");
								}
							} catch(Exception e) {
								System.out.println("Error: '"+in2+"' is not a valid ID");
							}
						}
						break;
					case "q":
						c = false;
						break;
					default:
						System.out.println("Invalid option");
						break;
				}
			}
		}
	}
	
	/**
	 * Creates a new empty SalesRep in the system.
	 * @param name name of the sales rep
	 * @return new sales rep
	 */
	public SalesRep addNewSalesRep(String name) {
		SalesRep ret = new SalesRep((salesreps.isEmpty()) ? 1 : salesreps.get(salesreps.size()-1).getSalesRepID()+1, name);
		salesreps.add(ret);
		return ret;
	}
	
	/**
	 * Creates a new lead in the system
	 * @param n
	 * @param c
	 * @return
	 */
	public Lead addNewLead(String n, ArrayList<Contact> c) {
		Lead ret = new Lead((leads.isEmpty()) ? 1 : leads.get(leads.size()-1).getLeadID()+1, n, c);
		leads.add(ret);
		return ret;
	}
	
	/**
	 * Prints a list of all the leads
	 */
	public void printAllLeads() {
		Iterator<Lead> i = leads.iterator();
		while(i.hasNext()) {
			System.out.println(i.next());
		}
	}
	
	/**
	 * Prints a list of leads that have no SalesRep
	 * @return list of printed leads
	 */
	public ArrayList<Lead> printUnassignedLeads() {
		Iterator<Lead> i = leads.iterator();
		Lead j;
		ArrayList<Lead> ret = new ArrayList<Lead>();
		while(i.hasNext()) {
			j = i.next();
			if(j.getSalesRep()==null) {
				System.out.println(j);
				ret.add(j);
			}
		}
		return ret;
	}
	
	/**
	 * Prints list of all sales reps
	 */
	public void printSalesReps() {
		Iterator<SalesRep> i = salesreps.iterator();
		while(i.hasNext()) {
			System.out.println(i.next());
		}
	}
}