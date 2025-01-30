#include <stdio.h>
#include <string.h>

int main() {

	char input[51];

	while (1) {
	
	printf("Enter a single word command: ");
	scanf("%50s", input);

	if (strcmp(input, "quit") == 0) {
	break;

	}
	else {
		int len = strlen(input);
		printf("%d letters\n", len);
	}
	}
	return 0;

	
}
