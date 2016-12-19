#ifndef LAYER_H
#define LAYER_H

#include "neuron.h"

typedef struct layer{
	Neuron **neurons_list; 
	struct layer *previous; 
	struct layer *next; 
	unsigned int size; 
}Layer; 

Layer *layer_create(unsigned int size, Layer *previous);
void layer_destroy(Layer *layer);

void layer_compute_values(Layer *layer);

//TODO createLayer();
//TODO getNextLayer();
//TODO getPreviousLayer();
//TODO getNeurons_list();
//TODO getSize;
//TODO destroy()

#endif
