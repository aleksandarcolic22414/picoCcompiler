#include <stdio.h>

int main()
{
    
    int niz[3][3];
    *(*(niz + 0) + 0) = 1;
    *(*(niz + 0) + 1) = 2;
    *(*(niz + 0) + 2) = 3;
    *(*(niz + 1) + 0) = 4;
    *(*(niz + 1) + 1) = 5;
    *(*(niz + 1) + 2) = 6;
    *(*(niz + 2) + 0) = 7;
    *(*(niz + 2) + 1) = 8;
    *(*(niz + 2) + 2) = 9;    

    for (int i = 0; i < 3; ++i) {
        for (int j = 0; j < 3; ++j) {
            printf("%d%s", *(*(niz + i) + j), j == 2 ? "\n" : " ");
        }
    }

    return 0;
}

/* 
    Izlaz:
    1 2 3
    4 5 6
    7 8 9
*/