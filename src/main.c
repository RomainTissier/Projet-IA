/**
 * File : 	main.c
 * 
 * Description : Entry point of the program
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

/**
 * Entry point of the program
 */
int main()
{

	//TODO int main(int argc, char *argv[]) to include params

	// Initialise random function
	srand(time(NULL));

	// Example running test tools
	test_all();

	// Return success to the shell
	return EXIT_SUCCESS;
}
