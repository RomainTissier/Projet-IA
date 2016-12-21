/**
 * File : 	network.h
 * 
 * Description : Contain network structure and related functions
 *		
 * Authors :
 *		Cuartero Jean-Louis
 * 		Delpech Marc	
 * 		Tissier Romain
 */
#ifndef NETWORK_H
#define NETWORK_H

#include "layer.h"
#include <stdio.h>

/**
 * Structure representing a network object
 */
typedef struct {
	unsigned int nb_hidden_layer;
	Layer *input;
	Layer *output;
	Layer **hidden_layers_list;
} Network;

/**
 * Constructor initializing a network object
 *	Param :
 *	 	nb_hidden_layer : number of hidden layer of the network
 *		hidden_layer_sizes[] : vector containing layer deepness
 * 		input_size : size of the input vector
 *		output_size : size of the output vector
 */
Network *network_create(unsigned int nb_hidden_layer,
			unsigned int hidden_layer_sizes[],
			unsigned int input_size, unsigned int output_size);

/**
 * Destructor for network object
 *	Param : 
 *		network : network to destroy
 */
void network_destroy(Network * network);

/**
 * Function executing the current network with a new input
 *	Param : 
 *		network : network to execute
 *		inputs : new set of inputs to execute in the network
 */
void network_run(Network * network, double inputs[]);

/**
 * Function taining the network after running it for a set of inputs
 *	Param :
 *		network : network to train
 *		inputs : set of inputs to apply 
 *		expected_outputs : wanted outputs (supervised learning)
 *		learning_coeficient : learning coeficient to apply
 */
void network_train(Network * network, double inputs[],
		   double expected_outputs[], double learning_coeficient);

/**
 *	Function saving the current network into a JSON file
 *		Param :
 *			network : network to work
 *			TP : number of true positive
 *			TN : number of true negative
 *			FP : number of false positive
 *			FN : number of false negative
 */
void network_to_json(Network * network, unsigned long TP, unsigned long TN,
		     unsigned long FP, unsigned long FN);

#endif
