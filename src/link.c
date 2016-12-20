/**
 * File : 	link.c
 * 
 * Description : Contain link structure and related functions
 *		
 * Authors :
 *		Cuartero Jean-Louis
 * 		Delpech Marc	
 * 		Tissier Romain
 */
#include "link.h"
#include <stdlib.h>
#include <time.h>

/*
 * Constructor initialising a link object
 *
 */
Link *link_create()
{
	Link *link = malloc(sizeof(Link));
	link->weight = ((double)rand() / (double)RAND_MAX) - 0.5;
	return link;
}

/**
 * Destructor for link object
 *	Param :
 *		link : link to destroy 
 */
void link_destroy(Link * link)
{
	free(link);
}
