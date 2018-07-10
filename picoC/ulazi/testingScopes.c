#include <stdio.h>

int main(int argc, char *argv[])
{
	int a = 10;
	int b = 15;

	if (a == 10)
	{
		int a = 20;
		b += a;
		printf("%d ", b);
		int b = 5;
		printf("%d ", b);
		b += a;
		int c;

		c += b;
	}

	printf("%d\n", b);
	
	return b;
}

/*
    Izlaz je:
    35 5 35
*/