/**
 * File : 	eventlist.c
 * 
 * Description : Contain event csv load list loading and dynamic storage
 *		
 * Authors :
 *		Cuartero Jean-Louis
 * 		Delpech Marc	
 * 		Tissier Romain
 */

#include "eventlist.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

/**
 * Constructor initialising an empty event list
 */
EventList *eventlist_create()
{
	EventList *evl = malloc(sizeof(EventList));
	evl->first = NULL;
	evl->size = 0;
	return evl;
}

/**
 * Function adding an event from an EventList object
 *	Param :
 * 		eventlist : EventList object
 *		event : new element to add
 */
unsigned long eventlist_add(EventList * eventlist, Event * event)
{
	EventListElement *new = malloc(sizeof(EventListElement));
	new->event = event;
	new->next = eventlist->first;
	new->previous = NULL;
	if (eventlist->first != NULL)
		eventlist->first->previous = new;
	eventlist->first = new;
	eventlist->size = eventlist->size + 1;
	return eventlist->size - 1;
}

/**
 * Function removing a new event to an EventList object
 *	Param :
 * 		eventlist : EventList object
 *		eventlistelement : element to remove
 */
void
eventlist_remove(EventList * eventlist, EventListElement * eventlistelement)
{
	EventListElement *previous = eventlistelement->previous;
	EventListElement *next = eventlistelement->next;
	if (previous != NULL)
		previous->next = next;
	else
		eventlist->first = next;
	if (next != NULL)
		next->previous = previous;
	eventlist->size = eventlist->size - 1;
	free(eventlistelement->event);
	free(eventlistelement);
}

/**
 * Function geting an element from an EventList object
 * 	Param :
 *		eventlist : EventList object
 *		i : indice of the element
 */
EventListElement *eventlist_get(EventList * eventlist, unsigned long i)
{
	EventListElement *res = eventlist->first;
	unsigned long j;
	for (j = 0; j < i; j++)
		res = res->next;
	return res;
}

/**
 * Destructor for an EventList object
 * 	Param :
 *		eventlist : EventList object to destroy
 */
void eventlist_destroy(EventList * eventlist)
{
	while (eventlist->first != NULL)
		eventlist_remove(eventlist, eventlist->first);
	free(eventlist);
}

/**
 * Function loadind an EventList from a csv file
 *	Param :
 *		csv_path : path to the csv file to load
 */
EventList *load_dataset(char csv_path[])
{
	EventList *dataset = eventlist_create();

	// Load the csv file in read mode
	FILE *csv_file = fopen(csv_path, "r");

	// Initialize the reading variables     
	char line[4096];
	char *DER_mass_MMC_str, *DER_mass_transverse_met_lep_str,
	    *DER_mass_vis_str, *DER_pt_h_str, *DER_deltaeta_jet_jet_str,
	    *DER_mass_jet_jet_str, *DER_prodeta_jet_jet_str,
	    *DER_deltar_tau_lep_str, *DER_pt_tot_str, *DER_sum_pt_str,
	    *DER_pt_ratio_lep_tau_str, *DER_met_phi_centrality_str,
	    *DER_lep_eta_centrality_str, *PRI_tau_pt_str, *PRI_tau_eta_str,
	    *PRI_tau_phi_str, *PRI_lep_pt_str, *PRI_lep_eta_str,
	    *PRI_lep_phi_str, *PRI_met_str, *PRI_met_phi_str,
	    *PRI_met_sumet_str, *PRI_jet_num_str, *PRI_jet_leading_pt_str,
	    *PRI_jet_leading_eta_str, *PRI_jet_leading_phi_str,
	    *PRI_jet_subleading_pt_str, *PRI_jet_subleading_eta_str,
	    *PRI_jet_subleading_phi_str, *PRI_jet_all_pt_str, *interest_str;
	fgets(line, 4096, csv_file);

	// Read the file line by line
	while (fgets(line, 4096, csv_file)) {

		// Create a new event
		Event *new_event = malloc(sizeof(Event));

		// Parse the line
		strtok(line, ",");
		DER_mass_MMC_str = strtok(NULL, ",");
		DER_mass_transverse_met_lep_str = strtok(NULL, ",");
		DER_mass_vis_str = strtok(NULL, ",");
		DER_pt_h_str = strtok(NULL, ",");
		DER_deltaeta_jet_jet_str = strtok(NULL, ",");
		DER_mass_jet_jet_str = strtok(NULL, ",");
		DER_prodeta_jet_jet_str = strtok(NULL, ",");
		DER_deltar_tau_lep_str = strtok(NULL, ",");
		DER_pt_tot_str = strtok(NULL, ",");
		DER_sum_pt_str = strtok(NULL, ",");
		DER_pt_ratio_lep_tau_str = strtok(NULL, ",");
		DER_met_phi_centrality_str = strtok(NULL, ",");
		DER_lep_eta_centrality_str = strtok(NULL, ",");
		PRI_tau_pt_str = strtok(NULL, ",");
		PRI_tau_eta_str = strtok(NULL, ",");
		PRI_tau_phi_str = strtok(NULL, ",");
		PRI_lep_pt_str = strtok(NULL, ",");
		PRI_lep_eta_str = strtok(NULL, ",");
		PRI_lep_phi_str = strtok(NULL, ",");
		PRI_met_str = strtok(NULL, ",");
		PRI_met_phi_str = strtok(NULL, ",");
		PRI_met_sumet_str = strtok(NULL, ",");
		PRI_jet_num_str = strtok(NULL, ",");
		PRI_jet_leading_pt_str = strtok(NULL, ",");
		PRI_jet_leading_eta_str = strtok(NULL, ",");
		PRI_jet_leading_phi_str = strtok(NULL, ",");
		PRI_jet_subleading_pt_str = strtok(NULL, ",");
		PRI_jet_subleading_eta_str = strtok(NULL, ",");
		PRI_jet_subleading_phi_str = strtok(NULL, ",");
		PRI_jet_all_pt_str = strtok(NULL, ",");
		strtok(NULL, ",");
		interest_str = strtok(NULL, ",");

		// Load the new event values with the previous parsed values
		new_event->DER_mass_MMC = strtod(DER_mass_MMC_str, NULL);
		new_event->DER_mass_transverse_met_lep =
		    strtod(DER_mass_transverse_met_lep_str, NULL);
		new_event->DER_mass_vis = strtod(DER_mass_vis_str, NULL);
		new_event->DER_pt_h = strtod(DER_pt_h_str, NULL);
		new_event->DER_deltaeta_jet_jet =
		    strtod(DER_deltaeta_jet_jet_str, NULL);
		new_event->DER_mass_jet_jet =
		    strtod(DER_mass_jet_jet_str, NULL);
		new_event->DER_prodeta_jet_jet =
		    strtod(DER_prodeta_jet_jet_str, NULL);
		new_event->DER_deltar_tau_lep =
		    strtod(DER_deltar_tau_lep_str, NULL);
		new_event->DER_pt_tot = strtod(DER_pt_tot_str, NULL);
		new_event->DER_sum_pt = strtod(DER_sum_pt_str, NULL);
		new_event->DER_pt_ratio_lep_tau =
		    strtod(DER_pt_ratio_lep_tau_str, NULL);
		new_event->DER_met_phi_centrality =
		    strtod(DER_met_phi_centrality_str, NULL);
		new_event->DER_lep_eta_centrality =
		    strtod(DER_lep_eta_centrality_str, NULL);
		new_event->PRI_tau_pt = strtod(PRI_tau_pt_str, NULL);
		new_event->PRI_tau_eta = strtod(PRI_tau_eta_str, NULL);
		new_event->PRI_tau_phi = strtod(PRI_tau_phi_str, NULL);
		new_event->PRI_lep_pt = strtod(PRI_lep_pt_str, NULL);
		new_event->PRI_lep_eta = strtod(PRI_lep_eta_str, NULL);
		new_event->PRI_lep_phi = strtod(PRI_lep_phi_str, NULL);
		new_event->PRI_met = strtod(PRI_met_str, NULL);
		new_event->PRI_met_phi = strtod(PRI_met_phi_str, NULL);
		new_event->PRI_met_sumet = strtod(PRI_met_sumet_str, NULL);
		new_event->PRI_jet_num = strtod(PRI_jet_num_str, NULL);
		new_event->PRI_jet_leading_pt =
		    strtod(PRI_jet_leading_pt_str, NULL);
		new_event->PRI_jet_leading_eta =
		    strtod(PRI_jet_leading_eta_str, NULL);
		new_event->PRI_jet_leading_phi =
		    strtod(PRI_jet_leading_phi_str, NULL);
		new_event->PRI_jet_subleading_pt =
		    strtod(PRI_jet_subleading_pt_str, NULL);
		new_event->PRI_jet_subleading_eta =
		    strtod(PRI_jet_subleading_eta_str, NULL);
		new_event->PRI_jet_subleading_phi =
		    strtod(PRI_jet_subleading_phi_str, NULL);
		new_event->PRI_jet_all_pt = strtod(PRI_jet_all_pt_str, NULL);
		if (interest_str != NULL) {
			if (strstr(interest_str, "s") != NULL)
				new_event->interest = 1;
			else
				new_event->interest = 0;
		} else
			new_event->interest = -1;

		// Add the new element to the list
		eventlist_add(dataset, new_event);
	}

	// Obviously, return the loaded dataset
	return dataset;
}
