/**
 * File : 	neuron.c
 * 	
 * Description : Contain the neuron structure and related functions
 *		
 * Authors :
 *		Cuartero Jean-Louis
 * 		Delpech Marc	
 * 		Tissier Romain
 */
#include "neuron.h"
#include <stdlib.h>

/**
 * Constructor creating an empty neuron object
 */
Neuron *neuron_create()
{
	Neuron *neuron = malloc(sizeof(Neuron));
	neuron->value = 0;
	neuron->gradient = 0;
	return neuron;
}

/**
 * Destructor for a neuron object
 *	Param : 
 *		neuron : neuron object to destroy
 */
void neuron_destroy(Neuron * neuron)
{
	// TODO free cross referenced links
	free(neuron);
}
