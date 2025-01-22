#include <stdio.h>
#include "utility.h"

int countdown(int x)
{
	while (x >= 0)
	{
		printf("%d\n", x);
		x--;
	}

	return 0;
}
