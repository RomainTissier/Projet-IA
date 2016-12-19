/**
 * File : 	neuron.h
 * 		
 * Authors :
 *		Cuartero Jean-Louis
 * 		Delpech Marc	
 * 		Tissier Romain
 */
#ifndef NEURON_H
#define NEURON_H
#include "link.h"

typedef struct neuron{
	Link **inputs_list; 
	Link **outputs_list;
	double value;
	double gradient;
}Neuron;

Neuron *neuron_create();

void neuron_destroy(Neuron *neuron);

#endif
