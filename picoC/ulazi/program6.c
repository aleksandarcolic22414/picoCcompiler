#include <stdio.h>

int main()
{
    int N = 20;
    
    for (int i = 0; i < N; ++i)
    {
        for (int j = 0; j < 2*N + 1; j++) 
        {
            if (j >= N-i && j <= N+i)
                printf("*");
            else
                printf(" ");
        }
        printf("\n");
    }
    
    for (int i = 0; i < N/10; ++i) 
    {
        for (int j = 0; j < N; j++)
            printf(" ");
        printf("*\n");
    }

    return 0;
}

/* Jelka visine N (bez postolja) */