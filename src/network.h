/**
 * File : 	network.h
 * 		
 * Authors :
 *		Cuartero Jean-Louis
 * 		Delpech Marc	
 * 		Tissier Romain
 */
#ifndef NETWORK_H
#define NETWORK_H

#include "layer.h"

typedef struct{
	unsigned int nb_hidden_layer;
	Layer *input;
	Layer *output; 
	Layer **hidden_layers_list;
}Network;

Network *network_create(unsigned int nb_hidden_layer,unsigned int hidden_layer_sizes[], unsigned int input_size, unsigned int output_size);

void network_destroy(Network *network);

void network_run(Network *network, double inputs[]);

void network_train(Network *network, double inputs[], double expected_output[], double learning_coeficient);

//TODO createNetwork(nbcouchecachee)
//TODO calcNewInput();
//TODO majerreur();
//TODO destroy

#endif


