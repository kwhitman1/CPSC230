#include <stdio.h>

int countdown(int x);

int main()
{
	
	int x;

	printf("Enter an Integer: ");
	scanf("%d", &x);
	countdown(x);
	return 0;
}

int countdown(int x)
{
	while (x >= 0)
	{
		printf("%d\n", x);
		x--;
	}
	return 0;
}



