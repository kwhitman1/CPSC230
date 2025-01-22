#include <stdio.h>
#include "utility.h"

int main()
{
 
 	int min = 1;
	int max = 10;
 	printf("Enter an Integer Between %d and %d: ", min, max);
	int x = countdown(min, max);
	for (int i = x; i >= 0; i--) {
		printf("%d\n", i);
	}	
 	return 0;
}

