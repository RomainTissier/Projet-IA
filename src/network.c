/**
 * File : 	network.c
 * 		
 * Authors :
 *		Cuartero Jean-Louis
 * 		Delpech Marc	
 * 		Tissier Romain
 */

#include "network.h"
#include <stdlib.h>

Network *network_create(unsigned int nb_hidden_layer,unsigned int hidden_layer_sizes[],unsigned int input_size, unsigned int output_size){
	Network *network = malloc(sizeof(Network));

	// Create input
	network->input = layer_create(input_size,NULL);
	Layer *mem_previous= network->input;
	network->nb_hidden_layer=nb_hidden_layer;

	// Create hidden layer
	network->hidden_layers_list=malloc(nb_hidden_layer*sizeof(Layer*));
	unsigned int n_layer;
	for(n_layer=0;n_layer<nb_hidden_layer;n_layer++){
		network->hidden_layers_list[n_layer]=layer_create(hidden_layer_sizes[n_layer],mem_previous);
		mem_previous=network->hidden_layers_list[n_layer];
	}
	// Create output
	
	network->output=layer_create(output_size,mem_previous);

	return network;
}

void network_run(Network * network, double inputs[]){
	unsigned int i;
	for(i=0;i<network->input->size;i++){
		network->input->neurons_list[i]->value=inputs[i];
	}

	for(i=0;i<network->nb_hidden_layer;i++){
		layer_compute_values(network->hidden_layers_list[i]);
	}
	layer_compute_values(network->output);
}

void network_train(Network *network, double inputs[], double expected_output[], double learning_coeficient){
	// On éxécute le réseau
	network_run(network,inputs);
	// Couche de sortie
	unsigned int i;
	Neuron *n;
	double delta;
	unsigned int previous_size=network->output->previous->size;
	unsigned int j;
	for(i=0;i<network->output->size;i++){
		n=network->output->neurons_list[i];
		n->gradient=(expected_output[i]-n->value)*n->value*(1-n->value);
		// Maj des poids 
		//delta= coef*gradient*sortieprec
		
		// Maj des poids pour toute les entrées. 
		for(j=0;j<previous_size;j++){
			delta=learning_coeficient*n->gradient*n->inputs_list[j]->origin->value;
			n->inputs_list[j]->weight+=delta;
		}
	}
	// erreur=wanted - réel
	// Gradient= = erreur*obs[1 − obs]
	
	Layer *current_layer=network->output->previous;
	// Couches cachées de la dernière à la première
	while(current_layer->previous!=NULL){ 
		unsigned int previous_size=current_layer->previous->size;
		// POur tout les neuronne de cette couche: 
		for(i=0;i<current_layer->size;i++){

			// Calcul sur la somme des sorties : gradient * poids.
			double sum=0; 
			n= current_layer->neurons_list[i];
			// pour tout les liens de sorties
			for(j=0;j<current_layer->next->size;j++){
				sum+= n->outputs_list[j]->destination->gradient*n->outputs_list[j]->weight;
			}			
			
			//Calcul gradient courant
			n->gradient=n->value*(1-n->value)*sum;
			
			//Calcul delta pour les entrées du neuronne	
			for(j=0;j<previous_size;j++){
				delta=learning_coeficient*n->gradient*n->inputs_list[j]->origin->value;
				n->inputs_list[j]->weight+=delta;
			}
		}
	
		// On passe à la couche inférieure
		current_layer=current_layer->previous;
	}

}

void network_destroy(Network * network){
	//TODO free layers
	free(network);
}
