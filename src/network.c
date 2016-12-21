/**
 * File : 	network.c
 * 
 * Description : Contain network structure and related functions
 * 		
 * Authors :
 *		Cuartero Jean-Louis
 * 		Delpech Marc	
 * 		Tissier Romain
 */

#include "network.h"
#include <stdlib.h>

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
			unsigned int input_size, unsigned int output_size)
{

	// Create an empty network object
	Network *network = malloc(sizeof(Network));

	// Create the input layer
	network->input = layer_create(input_size, NULL);

	// Create the list of hidden layer
	network->hidden_layers_list = malloc(nb_hidden_layer * sizeof(Layer *));

	// Save the lastest added layer 
	Layer *mem_previous = network->input;

	// Loop in the layer list to initialize each layers
	network->nb_hidden_layer = nb_hidden_layer;
	unsigned int n_layer;
	for (n_layer = 0; n_layer < nb_hidden_layer; n_layer++) {
		network->hidden_layers_list[n_layer] =
		    layer_create(hidden_layer_sizes[n_layer], mem_previous);

		// The new layer is save as the lastest added layer
		mem_previous = network->hidden_layers_list[n_layer];
	}

	// Create the output layer
	network->output = layer_create(output_size, mem_previous);

	return network;
}

/**
 * Function executing the current network with a new input
 *	Param : 
 *		network : network to execute
 *		inputs : new set of inputs to execute in the network
 */
void network_run(Network * network, double inputs[])
{
	unsigned int i;

	// Update the input layer neurons with their new values
	for (i = 0; i < network->input->size; i++)
		network->input->neurons_list[i]->value = inputs[i];

	// Compute and propagate values layer by layer
	for (i = 0; i < network->nb_hidden_layer; i++)
		layer_compute_values(network->hidden_layers_list[i]);

	// Update the output layer
	layer_compute_values(network->output);
}

/**
 * Function taining the network after running it for a set of inputs
 *	Param :
 *		network : network to train
 *		inputs : set of inputs to apply 
 *		expected_outputs : wanted outputs (supervised learning)
 *		learning_coeficient : learning coeficient to apply
 */
void
network_train(Network * network, double inputs[],
	      double expected_outputs[], double learning_coeficient)
{

	// Run the network with the new set of values
	network_run(network, inputs);

	// Initialize some variable before running loops
	Neuron *n;
	double delta;
	unsigned int previous_size;
	unsigned int i;
	unsigned int j;

	// Loop over output layer neurons to compute their gradient and delta
	previous_size = network->output->previous->size;
	for (i = 0; i < network->output->size; i++) {

		// Point to the current neuron
		n = network->output->neurons_list[i];

		// Compute the new gradient
		n->gradient =
		    (expected_outputs[i] - n->value) * n->value * (1 -
								   n->value);

		// Compute weight delta and update neurons inputs weight
		for (j = 0; j < previous_size; j++) {
			delta =
			    learning_coeficient * n->gradient *
			    n->inputs_list[j]->origin->value;
			n->inputs_list[j]->weight += delta;
		}
	}

	// Point to the current layer and declare sum variable before looping
	Layer *current_layer = network->output->previous;
	double sum;

	// Now Loop over hidden layer to compute and update their weights
	// Stop when we are in the input layer (ie there is no previous layer)
	while (current_layer->previous != NULL) {
		previous_size = current_layer->previous->size;

		// Loop over the neurons of the current layer
		for (i = 0; i < current_layer->size; i++) {

			// Point to the current neuron
			n = current_layer->neurons_list[i];

			// Compute the sum of current neurons outputs 
			// weights and gradient product
			sum = 0;
			for (j = 0; j < current_layer->next->size; j++)
				sum +=
				    n->outputs_list[j]->destination->gradient *
				    n->outputs_list[j]->weight;

			// Compute the current gradient using the previous sum
			n->gradient = n->value * (1 - n->value) * sum;

			// Compute delta and update inputs weight of the neuron 
			for (j = 0; j < previous_size; j++) {
				delta =
				    learning_coeficient * n->gradient *
				    n->inputs_list[j]->origin->value;
				n->inputs_list[j]->weight += delta;
			}
		}

		// Point to the previous layer
		current_layer = current_layer->previous;
	}
}

/**
 * Destructor for network object
 *	Param : 
 *		network : network to destroy
 */
void network_destroy(Network * network)
{
	// TODO free layer by layer
	free(network);
}

/**
 *	Function saving the current network into a JSON file
 *		Param :
 *			network : network to work
 *			TP : number of true positive
 *			TN : number of true negative
 *			FP : number of false positive
 *			FN : number of false negative
 */
void
network_to_json(Network * network, unsigned long TP, unsigned long TN,
		unsigned long FP, unsigned long FN)
{

	// Create a new output file (erase the previous)
	FILE *jsonFile = fopen("jsonOutput.json", "w");

	fprintf(jsonFile, "{\n");

	// Write confusion matrix       
	fprintf(jsonFile, "\tTP:\"%lu\"\n", TP);
	fprintf(jsonFile, "\tTN:\"%lu\"\n", TN);
	fprintf(jsonFile, "\tFP:\"%lu\"\n", FP);
	fprintf(jsonFile, "\tFN:\"%lu\"\n", FN);

	// Write layers list
	fprintf(jsonFile, "\tlayers:[\n");
	Layer *currentLayer = network->input->next;
	unsigned int i, j;
	while (currentLayer->next != NULL) {

		// Write layer by layer
		fprintf(jsonFile, "\t\tlayer:{\n");
		for (i = 0; i < currentLayer->size; i++) {

			// Write neuron by neuron
			fprintf(jsonFile, "\t\t\tneuron:{\n");
			fprintf(jsonFile, "\t\t\t\tinputs:[\n");

			// Write current neuron inputs weights
			for (j = 0; j < currentLayer->previous->size; j++)
				fprintf(jsonFile, "\t\t\t\t\t\"%f\"\n",
					currentLayer->neurons_list[i]->
					inputs_list[j]->weight);

			fprintf(jsonFile, "\t\t\t\t]\n");
			fprintf(jsonFile, "\t\t\t\toutputs:[\n");

			// Write current neuron outputs weights
			for (j = 0; j < currentLayer->next->size; j++)
				fprintf(jsonFile, "\t\t\t\t\t\"%f\"\n",
					currentLayer->neurons_list[i]->
					outputs_list[j]->weight);

			fprintf(jsonFile, "\t\t\t\t]\n");
			fprintf(jsonFile, "\t\t\t}\n");
		}
		fprintf(jsonFile, "\t\t}\n");
		currentLayer = currentLayer->next;
	}

	fprintf(jsonFile, "\t]\n");
	fprintf(jsonFile, "}\n");

	// Close the output file
	fclose(jsonFile);
}
