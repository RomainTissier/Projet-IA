/**
 * File : 	link.h
 * 		
 * Authors :
 *		Cuartero Jean-Louis
 * 		Delpech Marc	
 * 		Tissier Romain
 */

#ifndef LINK_H
#define LINK_H

struct neuron;  

typedef struct link{
	double weight; 
	struct neuron *origin;
	struct neuron *destination; 
}Link;

Link *link_create();

void link_destroy(Link *link);

#endif 
