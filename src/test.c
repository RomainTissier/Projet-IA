/**
 * File : 	test.c
 * 
 * Description : Contain some unit test for our neural network
 *	
 * Authors :
 *		Cuartero Jean-Louis
 * 		Delpech Marc	
 * 		Tissier Romain
 */

#include "test.h"
#include "link.h"
#include "neuron.h"
#include "layer.h"
#include "network.h"
#include <stdio.h>

void test_all()
{
	test_link();
	test_neuron();
	test_layer();
	test_network();
}

void test_link()
{
	Link *link = link_create();
	printf("[TEST] Create link, weight is: %f @%lu\n", link->weight,
	       (long unsigned)&(link));
	link_destroy(link);
}

void test_neuron()
{
	Neuron *neuron = neuron_create();
	printf("[TEST] Create neuron, value is: %f, @%lu\n", neuron->value,
	       (unsigned long)&(neuron->gradient));
	neuron_destroy(neuron);
}

void test_layer()
{
	Layer *layer1 = layer_create(5, NULL);
	int i;
	for (i = 0; i < 5; i++)
		printf("[TEST] Layer neuron %d : %f @%lu\n", i + 1,
		       layer1->neurons_list[i]->value,
		       (long unsigned)&(layer1->neurons_list[i]->value));
	Layer *layer2 = layer_create(2, layer1);
	for (i = 0; i < 5; i++)
		printf("[TEST] Sub layer list: %f @%lu \n",
		       layer2->neurons_list[0]->inputs_list[i]->weight,
		       (long unsigned)&(layer2->neurons_list[0]->
					inputs_list[i]->weight));
}

void test_network()
{
	unsigned int couches[3] = { 4, 3, 2 };
	Network *network = network_create(3, couches, 3, 2);
	double input1[3] = { 2, 3, 4 };
	double input2[3] = { 7, 2, 3 };
	network_run(network, input1);
	printf("[TEST] run: %f %f\n", network->output->neurons_list[0]->value,
	       network->output->neurons_list[1]->value);
	network_run(network, input1);
	printf("[TEST] run: %f %f\n", network->output->neurons_list[0]->value,
	       network->output->neurons_list[1]->value);
	network_run(network, input2);
	printf("[TEST] run: %f %f\n", network->output->neurons_list[0]->value,
	       network->output->neurons_list[1]->value);

	// Test train 
	network_run(network, input1);
	double mem_output[2];
	mem_output[0] = network->output->neurons_list[0]->value;
	mem_output[1] = network->output->neurons_list[1]->value;

	network_train(network, input1, mem_output, 0.5);
	network_run(network, input1);
	printf("[TEST] train same value: %f %f\n",
	       network->output->neurons_list[0]->value,
	       network->output->neurons_list[1]->value);

	mem_output[0] = 0;
	mem_output[1] = 1;
	network_train(network, input1, mem_output, 0.5);
	network_run(network, input1);
	printf("[TEST] train: %f %f\n", network->output->neurons_list[0]->value,
	       network->output->neurons_list[1]->value);
}
