#include "layer.h"
#include <stdlib.h>
#include <math.h>

Layer *layer_create(unsigned int size, Layer *previous){
	
	// Create the layer object
	Layer *layer = malloc(sizeof(Layer));
	layer->size=size;
	layer->previous=previous;

	// Create the neuron list
	layer->neurons_list=malloc(size*sizeof(Neuron*));
	unsigned int n_layer;	
	for(n_layer=0;n_layer<size;n_layer++)
		layer->neurons_list[n_layer]=neuron_create();
	
	// Si la couche précédente n'est pas nul
	if(previous != NULL){
		
		// On lie la couche précédente à la courante
		previous->next=layer;

		// On créer la couche output précédente:
		unsigned int n_previous;
		for(n_previous=0;n_previous<previous->size;n_previous++)
			previous->neurons_list[n_previous]->outputs_list=malloc(layer->size*sizeof(Link*));
		
		// On itére sur le neuronnes de la couche courante 
		for(n_layer=0;n_layer<size;n_layer++){

			// On créer une liste d'entrée pour le neuronne i de la couche courante. 
			layer->neurons_list[n_layer]->inputs_list=malloc(previous->size*sizeof(Link*));
			
			// Pour chacun des neuronne de la couche précédente			
			for(n_previous=0;n_previous<previous->size;n_previous++){
				
				// On créer le lien
				Link *new_link=link_create();
				new_link->origin=previous->neurons_list[n_previous];//Neuronne J couche précédente
				new_link->destination=layer->neurons_list[n_layer];// Neuronne i cournat
				
				// On l'ajoute aux inputs courante: 
				layer->neurons_list[n_layer]->inputs_list[n_previous]=new_link;

				// et aux outputs du précédent
				previous->neurons_list[n_previous]->outputs_list[n_layer]=new_link;
			}
		}
	} 
	return layer;
}

void layer_compute_values(Layer *layer){
	unsigned int previous_size=layer->previous->size;
	unsigned int n_layer;
	unsigned int n_previous;
	double res;
	Neuron *n;
	for(n_layer=0;n_layer<layer->size;n_layer++){
		n= layer->neurons_list[n_layer];
		res=0;

		// Sommation entrée et poids
		for(n_previous=0;n_previous<previous_size;n_previous++){
			res+=n->inputs_list[n_previous]->weight*n->inputs_list[n_previous]->origin->value;
		}	
		
		// Fonction activation sigmoïde 1/(1+exp(-x))
		res=1/(1+exp(-res));
		n->value=res;
	}
}

void layer_destroy(Layer *layer){
	if(layer->previous != NULL)
		layer->previous->next=NULL;
	if(layer->next !=NULL)
		layer->next->previous=NULL;

	// On free la liste de neuronne
	unsigned int i;
	for(i=0;i<layer->size;i++){
		//TODO : clean links
		neuron_destroy(layer->neurons_list[i]);
	}
	free(layer->neurons_list); 
	free(layer);
}
