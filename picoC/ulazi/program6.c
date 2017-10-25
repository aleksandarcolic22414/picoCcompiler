int main()
{
    int i, j;
    int N = 20;
    
    for (i = 0; i < N; ++i) {
        for (j = 0; j < 2*N + 1; j++) {
            if (j >= N-i && j <= N+i)
                printf("*");
            else
                printf(" ");
        }
        printf("\n");
    }
    
    for (i = 0; i < N/10; ++i) {
        for (j = 0; j < N; j++)
            printf(" ");
        printf("*\n");
    }

    return 0;
}

/* Jelka visine N (bez postolja) */