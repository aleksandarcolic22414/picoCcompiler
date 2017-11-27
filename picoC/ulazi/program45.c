int main()
{
    int niz[10][5];
    int i, j;

    for (i = 0; i < 10; ++i) {
        for (j = 0; j < 5; ++j) {
            niz[i][j] = i;
        }
    }
        
    for (i = 0; i < 10; ++i) {
        for (j = 0; j < 5; ++j) {
            printf("%d ", niz[i][j]);
        }
        printf("\n");
    }

    printf("%d\n", niz[4][4]);

    return 0;
}
/*
    Izlaz:
    0 0 0 0 0 
    1 1 1 1 1 
    2 2 2 2 2 
    3 3 3 3 3 
    4 4 4 4 4 
    5 5 5 5 5 
    6 6 6 6 6 
    7 7 7 7 7 
    8 8 8 8 8 
    9 9 9 9 9 
    4

*/