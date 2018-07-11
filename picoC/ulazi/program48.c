#include <stdio.h>

int main(int argc, char *argv[])
{
    int niz[5][5];
    
    for (int i = 0; i < 5; ++i) {
        for (int j = 0; j < 5; ++j) {
            niz[i][j] = i + j;
        }
    }

    for (int i = 0; i < 5; ++i) {
        for (int j = 0; j < 5; ++j) {
            printf("%d ", niz[i][j]);
        }
        printf("\n");
    }

    return 0;
}

