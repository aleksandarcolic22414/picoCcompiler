#include <stdio.h>

int main()
{
    
    int niz[2][2][2];

    *(*(*(niz + 0) + 0) + 0) = 1;
    *(*(*(niz + 0) + 0) + 1) = 2;
    *(*(*(niz + 0) + 1) + 0) = 3;
    *(*(*(niz + 0) + 1) + 1) = 4;
    *(*(*(niz + 1) + 0) + 0) = 5;
    *(*(*(niz + 1) + 0) + 1) = 6;    
    *(*(*(niz + 1) + 1) + 0) = 7;
    *(*(*(niz + 1) + 1) + 1) = 8;    

    for (int i = 0; i < 2; ++i) {
        for (int j = 0; j < 2; ++j) {
            for (int k = 0; k < 2; ++k) {
                printf("%d ", *(*(*(niz + i) + j) + k));
            }
        }
    }
    printf("\n");

    return 0;
}

/* 
    Output:
    1 2 3 4 5 6 7 8
*/
