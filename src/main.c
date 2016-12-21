/**
 * File : 	main.c
 * 
 * Description : Entry point of the program
 *		
 * Authors :
 *		Cuartero Jean-Louis
 * 		Delpech Marc	
 * 		Tissier Romain
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include "test.h"
#include "eventlist.h"
#include "network.h"

/**
 * Entry point of the program
 */
int main(int argc, char *argv[])
{
	
	// Check params
	if (argc < 4) {
		printf("use: ./launch <learning_coeficient> <training.csv> <size hidden layer 1> <size hidden layer 2> [...] <size hidden layer n>\n");
		printf
		    ("\t* <learning_coeficient> is the learning coeficient use by backpropagation\n");
		printf("\t* <training.csv> is the training file\n");
		printf("\t* <size layer n> is the number of neurons of the hidden layer n\n");
		return EXIT_FAILURE;
	}

	// Initialise random function
	srand(time(NULL));

	int nb_layer=argc-3;
	unsigned int couches[nb_layer];
	int i;
	for(i=0;i<nb_layer;i++)
		couches[i]=atoi(argv[3+i]);

	// Parse the learning coeficient
	double learning_coeficient = strtod(argv[1], NULL);

	// Load dataset
	EventList *dataset = load_dataset(argv[2]);
	Network *network = network_create(nb_layer, couches, 30, 1);

	// Load inputs from event and train the network
	double inputs[30];
	double outputs[1];
	for (i = 0; i < 200000; i++) {
		EventListElement *elmt = dataset->first;
		inputs[0] = elmt->event->DER_mass_MMC;
		inputs[1] = elmt->event->DER_mass_transverse_met_lep;
		inputs[2] = elmt->event->DER_mass_vis;
		inputs[3] = elmt->event->DER_pt_h;
		inputs[4] = elmt->event->DER_deltaeta_jet_jet;
		inputs[5] = elmt->event->DER_mass_jet_jet;
		inputs[6] = elmt->event->DER_prodeta_jet_jet;
		inputs[7] = elmt->event->DER_deltar_tau_lep;
		inputs[8] = elmt->event->DER_pt_tot;
		inputs[9] = elmt->event->DER_sum_pt;
		inputs[10] = elmt->event->DER_pt_ratio_lep_tau;
		inputs[11] = elmt->event->DER_met_phi_centrality;
		inputs[12] = elmt->event->DER_lep_eta_centrality;
		inputs[13] = elmt->event->PRI_tau_pt;
		inputs[14] = elmt->event->PRI_tau_eta;
		inputs[15] = elmt->event->PRI_tau_phi;
		inputs[16] = elmt->event->PRI_lep_pt;
		inputs[17] = elmt->event->PRI_lep_eta;
		inputs[18] = elmt->event->PRI_lep_phi;
		inputs[19] = elmt->event->PRI_met;
		inputs[20] = elmt->event->PRI_met_phi;
		inputs[21] = elmt->event->PRI_met_sumet;
		inputs[22] = elmt->event->PRI_jet_num;
		inputs[23] = elmt->event->PRI_jet_leading_pt;
		inputs[24] = elmt->event->PRI_jet_leading_eta;
		inputs[25] = elmt->event->PRI_jet_leading_phi;
		inputs[26] = elmt->event->PRI_jet_subleading_pt;
		inputs[27] = elmt->event->PRI_jet_subleading_eta;
		inputs[28] = elmt->event->PRI_jet_subleading_phi;
		inputs[29] = elmt->event->PRI_jet_all_pt;

		outputs[0] = elmt->event->interest;
		network_train(network, inputs, outputs, learning_coeficient);
		eventlist_remove(dataset, dataset->first);
	}

	// Load and check the model 
	unsigned long TP = 0, TN = 0, FP = 0, FN = 0;
	unsigned long inf_zero = 0, sup_one = 0, quart_one = 0, quart_two =
	    0, quart_three = 0, quart_four = 0;
	for (i = 0; i < 50000; i++) {
		EventListElement *elmt = dataset->first;
		inputs[0] = elmt->event->DER_mass_MMC;
		inputs[1] = elmt->event->DER_mass_transverse_met_lep;
		inputs[2] = elmt->event->DER_mass_vis;
		inputs[3] = elmt->event->DER_pt_h;
		inputs[4] = elmt->event->DER_deltaeta_jet_jet;
		inputs[5] = elmt->event->DER_mass_jet_jet;
		inputs[6] = elmt->event->DER_prodeta_jet_jet;
		inputs[7] = elmt->event->DER_deltar_tau_lep;
		inputs[8] = elmt->event->DER_pt_tot;
		inputs[9] = elmt->event->DER_sum_pt;
		inputs[10] = elmt->event->DER_pt_ratio_lep_tau;
		inputs[11] = elmt->event->DER_met_phi_centrality;
		inputs[12] = elmt->event->DER_lep_eta_centrality;
		inputs[13] = elmt->event->PRI_tau_pt;
		inputs[14] = elmt->event->PRI_tau_eta;
		inputs[15] = elmt->event->PRI_tau_phi;
		inputs[16] = elmt->event->PRI_lep_pt;
		inputs[17] = elmt->event->PRI_lep_eta;
		inputs[18] = elmt->event->PRI_lep_phi;
		inputs[19] = elmt->event->PRI_met;
		inputs[20] = elmt->event->PRI_met_phi;
		inputs[21] = elmt->event->PRI_met_sumet;
		inputs[22] = elmt->event->PRI_jet_num;
		inputs[23] = elmt->event->PRI_jet_leading_pt;
		inputs[24] = elmt->event->PRI_jet_leading_eta;
		inputs[25] = elmt->event->PRI_jet_leading_phi;
		inputs[26] = elmt->event->PRI_jet_subleading_pt;
		inputs[27] = elmt->event->PRI_jet_subleading_eta;
		inputs[28] = elmt->event->PRI_jet_subleading_phi;
		inputs[29] = elmt->event->PRI_jet_all_pt;

		// Run the network and get the output value
		network_run(network, inputs);
		double seuil = 0.5;
		double output = network->output->neurons_list[0]->value;

		// Update indicators
		if (output >= seuil && elmt->event->interest == 1) 
			TP++;
		else if (output >= seuil && elmt->event->interest == 0) 
			FP++;
		 else if (output < seuil && elmt->event->interest == 0) 
			TN++;
		 else if (output < seuil && elmt->event->interest == 1) 
			FN++;
		if (output < 0)
			inf_zero++;
		if (output > 1)
			sup_one++;
		if (output >= 0 && output < 0.25)
			quart_one++;
		if (output >= 0.25 && output < 0.5)
			quart_two++;
		if (output >= 0.5 && output < 0.75)
			quart_three++;
		if (output >= 0.75 && output <= 1)
			quart_four++;

		eventlist_remove(dataset, dataset->first);
	}
	printf("* Ouput histogram:\n");
	printf("\t[-inf;0[ => %lu\n", inf_zero);
	printf("\t[0;0.25[ => %lu\n", quart_one);
	printf("\t[0.25;0.5[ => %lu\n", quart_two);
	printf("\t[0.5;0.75[ => %lu\n", quart_three);
	printf("\t[0.75;1] => %lu\n", quart_four);
	printf("\t]1;+inf[ => %lu\n", sup_one);
	printf("* Learning: TP=%lu TN=%lu FP=%lu FN=%lu\n", TP, TN, FP, FN);
	printf("* Would you save this experiment in json?\n");
	char res;
	scanf("%c", &res);
	if (res == 'y' || res == 'Y')
		network_to_json(network, TP, TN, FP, FN);

	// Return success to the shell
	return EXIT_SUCCESS;
}
