/**
 * File : 	eventlist.h
 * 
 * Description : Contain event csv load list loading and dynamic storage
 *		
 * Authors :
 *		Cuartero Jean-Louis
 * 		Delpech Marc	
 * 		Tissier Romain
 */

#ifndef EVENTLIST_H
#define EVENTLIST_H

/**
 * Structure describing an event
 */
typedef struct event {
	float DER_mass_MMC;
	float DER_mass_transverse_met_lep;
	float DER_mass_vis;
	float DER_pt_h;
	float DER_deltaeta_jet_jet;
	float DER_mass_jet_jet;
	float DER_prodeta_jet_jet;
	float DER_deltar_tau_lep;
	float DER_pt_tot;
	float DER_sum_pt;
	float DER_pt_ratio_lep_tau;
	float DER_met_phi_centrality;
	float DER_lep_eta_centrality;
	float PRI_tau_pt;
	float PRI_tau_eta;
	float PRI_tau_phi;
	float PRI_lep_pt;
	float PRI_lep_eta;
	float PRI_lep_phi;
	float PRI_met;
	float PRI_met_phi;
	float PRI_met_sumet;
	float PRI_jet_num;
	float PRI_jet_leading_pt;
	float PRI_jet_leading_eta;
	float PRI_jet_leading_phi;
	float PRI_jet_subleading_pt;
	float PRI_jet_subleading_eta;
	float PRI_jet_subleading_phi;
	float PRI_jet_all_pt;
	char interest;
} Event;

/**
 * Structure representing an element of the event list
 */
typedef struct eventListElement {
	Event *event;
	struct eventListElement *next;
	struct eventListElement *previous;
} EventListElement;

/**
 * Structure representing the double linked-list of events
 */
typedef struct {
	EventListElement *first;
	unsigned long size;
} EventList;

/**
 * Constructor initialising an empty event list
 */
EventList *eventlist_create();

/**
 * Function adding an event from an EventList object
 *	Param :
 * 		eventlist : EventList object
 *		event : new element to add
 */
unsigned long eventlist_add(EventList * eventlist, Event * event);

/**
 * Function removing a new event to an EventList object
 *	Param :
 * 		eventlist : EventList object
 *		eventlistelement : element to remove
 */
void eventlist_remove(EventList * eventlist,
		      EventListElement * eventlistelement);

/**
 * Function geting an element from an EventList object
 * 	Param :
 *		eventlist : EventList object
 *		i : indice of the element
 */
EventListElement *eventlist_get(EventList * eventlist, long unsigned i);

/**
 * Destructor for an EventList object
 * 	Param :
 *		eventlist : EventList object to destroy
 */
void eventlist_destroy(EventList * eventlist);

/**
 * Function loadind an EventList from a csv file
 *	Param :
 *		csv_path : path to the csv file to load
 */
EventList *load_dataset(char csv_path[]);

#endif
