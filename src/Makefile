project: link.o neuron.o layer.o network.o test.o main.o
	gcc -c link.c -Wall -Wextra -Werror
	gcc -c neuron.c -Wall -Wextra -Werror
	gcc -c layer.c -Wall -Wextra -Werror
	gcc -c network.c -Wall -Wextra -Werror
	gcc -c test.c -Wall -Wextra -Werror
	gcc -c main.c -Wall -Wextra -Werror
	gcc link.o neuron.o layer.o network.o test.o main.o -o project -lm -Wall -Wextra -Werror
clean: 
	rm *.o project
