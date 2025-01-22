#include <stdio.h>
#include "utility.h"

int countdown(int min, int max)
{
	int x;
	while (1)
{
		if (scanf("%d", &x) == 1 && x >= min && x <= max)
		{
			return x;
		}
		else {
			printf("Invalid number. Enter an Integer Between %d and %d: ", min, max);
		}
	}

	return 0;
}
