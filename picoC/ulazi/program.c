int main()
{
    int i, j;
    int N = 80;
        for (i = 0; i <= N; i++) {
            for (j = 0; j < 2*N + 1; j++) {
                if (j >= N - i && j <= N + i)
                    printf("*");
                else
                    printf(" ");
            }
            printf("\n");
        }
        
    for (i = 0; i < N/10; ++i)
        for (j = 0; j <= N; ++j)
            if (j == N)
                printf("*\n");
            else
                printf(" ");
    return 0;
}

/*
int main()
{
    int a = 10, b = 10, c = 3;
    b = a % c;
    printf("%d %d %d\n", a, b, c);
    
    return (((2+2*2)+2)/2+2)/2 * 8/(12/2-6/(1+12/(4+8/(3+2/(1+4/(1+3))))));
}
*/
