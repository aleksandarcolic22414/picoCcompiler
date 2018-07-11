#include <stdio.h>

int main(int argc, char **argv)
{
    for (int i = 0; i < 10; i++) 
    {
        printf("i = %d***********************\n", i);
        int j = 10;
        if (i == 7) 
        {
            printf("Preskace se i = 7\n");
            continue;
        }

        while (j) 
        {
            if (--j == 5) 
            {
                printf("Preskace se j = 5\n");
                continue;
            } 
            else if (j == 2) 
            {
                printf("Izlaz iz while-a za j = 2\n");
                break;
            }

            printf("j = %d%s", j, j ? " " : "\n");
        }
    }

    return 0;
}

/*
    Izlaz je:
    
    i = 0***********************
    j = 9 j = 8 j = 7 j = 6 Preskace se j = 5
    j = 4 j = 3 Izlaz iz while-a za j = 2
    i = 1***********************
    j = 9 j = 8 j = 7 j = 6 Preskace se j = 5
    j = 4 j = 3 Izlaz iz while-a za j = 2
    i = 2***********************
    j = 9 j = 8 j = 7 j = 6 Preskace se j = 5
    j = 4 j = 3 Izlaz iz while-a za j = 2
    i = 3***********************
    j = 9 j = 8 j = 7 j = 6 Preskace se j = 5
    j = 4 j = 3 Izlaz iz while-a za j = 2
    i = 4***********************
    j = 9 j = 8 j = 7 j = 6 Preskace se j = 5
    j = 4 j = 3 Izlaz iz while-a za j = 2
    i = 5***********************
    j = 9 j = 8 j = 7 j = 6 Preskace se j = 5
    j = 4 j = 3 Izlaz iz while-a za j = 2
    i = 6***********************
    j = 9 j = 8 j = 7 j = 6 Preskace se j = 5
    j = 4 j = 3 Izlaz iz while-a za j = 2
    i = 7***********************
    Preskace se i = 7
    i = 8***********************
    j = 9 j = 8 j = 7 j = 6 Preskace se j = 5
    j = 4 j = 3 Izlaz iz while-a za j = 2
    i = 9***********************
    j = 9 j = 8 j = 7 j = 6 Preskace se j = 5
    j = 4 j = 3 Izlaz iz while-a za j = 2

*/

