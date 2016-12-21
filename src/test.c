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
#include "eventlist.h"
#include <stdio.h>
#include <stdlib.h>

void test_all()
{
	test_link();
	test_neuron();
	test_layer();
	test_network();
	test_eventlist();
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

void test_eventlist()
{

	EventList *eventlist = eventlist_create();

	Event *e1 = malloc(sizeof(Event));
	Event *e2 = malloc(sizeof(Event));
	Event *e3 = malloc(sizeof(Event));
	Event *e4 = malloc(sizeof(Event));
	Event *e5 = malloc(sizeof(Event));
	Event *e6 = malloc(sizeof(Event));
	Event *e7 = malloc(sizeof(Event));
	e1->interest = 'R';
	e2->interest = 'O';
	e3->interest = 'M';
	e4->interest = 'M';
	e5->interest = 'A';
	e6->interest = 'I';
	e7->interest = 'N';

	eventlist_add(eventlist, e1);
	eventlist_add(eventlist, e2);
	eventlist_add(eventlist, e3);
	eventlist_add(eventlist, e4);
	eventlist_add(eventlist, e5);
	eventlist_add(eventlist, e6);
	eventlist_add(eventlist, e7);

	printf("[TEST] list l: %lu\n", eventlist->size);
	eventlist_remove(eventlist, eventlist_get(eventlist, 3));
	printf("[TEST] list l2: %lu\n", eventlist->size);
	unsigned long i;
	for (i = 0; i < eventlist->size; i++) {
		EventListElement *el = eventlist_get(eventlist, i);
		printf("[TEST] l2[%lu]: %c\n", i, el->event->interest);
	}
}
