/**
 * File : 	main.c
 * 		
 * Authors :
 *		Cuartero Jean-Louis
 * 		Delpech Marc	
 * 		Tissier Romain
 */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include "test.h"

//TODO int main(int argc, char *argv[])
int main(){
	srand(time(NULL));
	test_all();
	return EXIT_SUCCESS; 
}
