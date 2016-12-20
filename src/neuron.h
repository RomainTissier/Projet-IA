/**
 * File : 	neuron.h
 * 	
 * Description : Contain the neuron structure and related functions
 *	
 * Authors :
 *		Cuartero Jean-Louis
 * 		Delpech Marc	
 * 		Tissier Romain
 */

#ifndef NEURON_H
#define NEURON_H

#include "link.h"

/** 
 * Structure representing a neuron object
 */
typedef struct neuron {
	Link **inputs_list;
	Link **outputs_list;
	double value;
	double gradient;
} Neuron;

/**
 * Constructor creating an empty neuron object
 */
Neuron *neuron_create();

/**
 * Destructor for a neuron object
 *	Param : 
 *		neuron : neuron object to destroy
 */
void neuron_destroy(Neuron * neuron);

#endif
