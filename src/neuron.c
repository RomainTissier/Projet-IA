#include "neuron.h"
#include <stdlib.h>

Neuron *neuron_create(){
	Neuron *neuron= malloc(sizeof(Neuron));
	neuron->value=0;
	neuron->gradient=0;
	return neuron;
}

void neuron_destroy(Neuron *neuron){
	//TODO si elle existent: free des listes entrées et sortie
	// TODO free dans un seul des sens les liens. 
	free(neuron);
}
