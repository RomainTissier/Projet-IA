/**
 * File : 	layer.h
 * 
 * Description : Contain layer structure and related functions
 *		
 * Authors :
 *		Cuartero Jean-Louis
 * 		Delpech Marc	
 * 		Tissier Romain
 */

#ifndef LAYER_H
#define LAYER_H

#include "neuron.h"

/**
 * Structure representing a layer
 */
typedef struct layer {
	Neuron **neurons_list;
	struct layer *previous;
	struct layer *next;
	unsigned int size;
} Layer;

/**
 * Constructor of layer object 
 * 	Param : 
 *		size : number of neuron in the layer
 *		previous : pointer to the previous layer(NULL if input layer)
 */
Layer *layer_create(unsigned int size, Layer * previous);

/*
 * Desctructor of layer object
 *	Param : 
 *		layer : layer object to destroy
 */
void layer_destroy(Layer * layer);

/* 
 * Function computing neurons values of the layer
 *	Param : 
 *		layer : current layer
 */
void layer_compute_values(Layer * layer);

#endif
