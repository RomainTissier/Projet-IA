/**
 * File : 	layer.c
 * 
 * Description : Contain layer structure and related functions
 *		
 * Authors :
 *		Cuartero Jean-Louis
 * 		Delpech Marc	
 * 		Tissier Romain
 */

#include "layer.h"
#include <stdlib.h>
#include <math.h>

/**
 * Constructor of layer object 
 * 	Param : 
 *		size : number of neuron in the layer
 *		previous : pointer to the previous layer(NULL if input layer)
 */
Layer *layer_create(unsigned int size, Layer * previous)
{

	// Create the layer object
	Layer *layer = malloc(sizeof(Layer));
	layer->size = size;

	// Create the neurons list
	layer->neurons_list = malloc(size * sizeof(Neuron *));
	unsigned int n_layer;
	for (n_layer = 0; n_layer < size; n_layer++)
		layer->neurons_list[n_layer] = neuron_create();

	// Update relation with the previous layer
	if (previous != NULL) {

		// Link the current layer with the previous layer
		layer->previous = previous;
		previous->next = layer;

		// Update the previous layer outputs list
		unsigned int n_previous;
		for (n_previous = 0; n_previous < previous->size; n_previous++)
			previous->neurons_list[n_previous]->outputs_list =
			    malloc(layer->size * sizeof(Link *));

		// Loop over the newly created neurons in the current layer 
		for (n_layer = 0; n_layer < size; n_layer++) {

			// Create inputs list for the current neurons
			layer->neurons_list[n_layer]->inputs_list =
			    malloc(previous->size * sizeof(Link *));

			// Loop over previous layer neurons to update outputs 
			// links int the same times of current inputs links                     
			for (n_previous = 0; n_previous < previous->size;
			     n_previous++) {

				// Create a new link and specify its origin and 
				// destination neurons
				Link *new_link = link_create();
				new_link->origin =
				    previous->neurons_list[n_previous];
				new_link->destination =
				    layer->neurons_list[n_layer];

				// Add this link to the input
				// of the current neurons
				layer->
				    neurons_list[n_layer]->inputs_list
				    [n_previous] = new_link;

				// Add this link to the previous layer neurons
				// output list
				previous->
				    neurons_list[n_previous]->outputs_list
				    [n_layer] = new_link;
			}
		}
	}
	return layer;
}

/* 
 * Function computing neurons values of the layer
 *	Param : 
 *		layer : current layer
 */
void layer_compute_values(Layer * layer)
{

	// Declare some loop variables
	unsigned int n_layer;
	unsigned int n_previous;
	double sum;
	Neuron *n;

	// Save the size of the previous layer
	unsigned int previous_size = layer->previous->size;

	// Loop over the neurons of the current layer
	for (n_layer = 0; n_layer < layer->size; n_layer++) {

		// Point to the current neurons
		n = layer->neurons_list[n_layer];

		// Sum the inputs values and theirs weight
		sum = 0;
		for (n_previous = 0; n_previous < previous_size; n_previous++)
			sum +=
			    n->inputs_list[n_previous]->weight *
			    n->inputs_list[n_previous]->origin->value;

		// Apply the sigmoid activation function on the sum 
		// and affect it to the neurons value
		n->value = 1 / (1 + exp(-sum));
	}
}

/*
 * Desctructor of layer object
 *	Param : 
 *		layer : layer object to destroy
 */
void layer_destroy(Layer * layer)
{

	// Dereference pointer to this layer
	if (layer->previous != NULL)
		layer->previous->next = NULL;
	if (layer->next != NULL)
		layer->next->previous = NULL;

	// TODO Free links

	// Free each neurons in the layer
	unsigned int i;
	for (i = 0; i < layer->size; i++)
		neuron_destroy(layer->neurons_list[i]);

	// Free the neuron list
	free(layer->neurons_list);

	// Obviously, free the layer 
	free(layer);
}
