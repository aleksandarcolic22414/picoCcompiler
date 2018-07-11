#include <stdio.h>

int main()
{
    int niz[5][5][5];
    
    for (int i = 0; i < 5; ++i) {
        for (int j = 0; j < 5; ++j) {
            for (int k = 0; k < 5; ++k) {
                niz[i][j][k] = i + j + k;
            }   
        }
    }

    for (int i = 0; i < 5; ++i) {
        for (int j = 0; j < 5; ++j) {
            for (int k = 0; k < 5; ++k) {
                printf("%d ", niz[i][j][k]);
            }   
            printf("\n");
        }
        printf("\n");
    }

    return 0;
}
/* 
    Output:
 
    0 1 2 3 4 
    1 2 3 4 5 
    2 3 4 5 6 
    3 4 5 6 7 
    4 5 6 7 8 

    1 2 3 4 5 
    2 3 4 5 6 
    3 4 5 6 7 
    4 5 6 7 8 
    5 6 7 8 9 

    2 3 4 5 6 
    3 4 5 6 7 
    4 5 6 7 8 
    5 6 7 8 9 
    6 7 8 9 10 

    3 4 5 6 7 
    4 5 6 7 8 
    5 6 7 8 9 
    6 7 8 9 10 
    7 8 9 10 11 

    4 5 6 7 8 
    5 6 7 8 9 
    6 7 8 9 10 
    7 8 9 10 11 
    8 9 10 11 12
*/