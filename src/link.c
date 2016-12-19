/**
 * File : 	link.c
 * 		
 * Authors :
 *		Cuartero Jean-Louis
 * 		Delpech Marc	
 * 		Tissier Romain
 */
#include "link.h"
#include <stdlib.h>
#include <time.h>


Link *link_create(){
	Link *link = malloc(sizeof(Link));
	link->weight = ((double)rand() / (double)RAND_MAX) - 0.5;
	return link;
}

void link_destroy(Link *link){
	free(link);
}
