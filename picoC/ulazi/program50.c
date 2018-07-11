#include <stdio.h>

int niz[5][5][5];

int main()
{
    int i, j, k;
    
    for (i = 0; i < 5; ++i) {
        for (j = 0; j < 5; ++j) {
            for (k = 0; k < 5; ++k) {
                niz[i][j][k] = i + j + k;
            }   
        }
    }

    for (i = 0; i < 5; ++i) {
        for (j = 0; j < 5; ++j) {
            for (k = 0; k < 5; ++k) {
                printf("%d ", niz[i][j][k]);
            }   
            printf("\n");
        }
        printf("\n");
    }

    return 0;
}
