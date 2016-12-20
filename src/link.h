/**
 * File : 	link.h
 * 
 * Description : Contain link structure and related functions
 *		
 * Authors :
 *		Cuartero Jean-Louis
 * 		Delpech Marc	
 * 		Tissier Romain
 */

#ifndef LINK_H
#define LINK_H

// Reference use for cross/compilation
struct neuron;

/**
 * Structure representing a link between two neurons
 */
typedef struct link {
	double weight;
	struct neuron *origin;
	struct neuron *destination;
} Link;

/*
 * Constructor initialising a link object
 *
 */
Link *link_create();

/**
 * Destructor for link object
 *	Param :
 *		link : link to destroy 
 */
void link_destroy(Link * link);

#endif
