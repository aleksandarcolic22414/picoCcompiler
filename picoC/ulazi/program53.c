#include <stdio.h>

int matrix[5][5];

void printMatrix(int matrix[][5]) 
{
    int i, j;
    for (i = 0; i < 5; ++i) {
        for (j = 0; j < 5; ++j) {
            printf("%d ", matrix[i][j]);
        }
        printf("\n");
    }
}

int main()
{
    int i, j;
    
    for (i = 0; i < 5; ++i) {
        for (j = 0; j < 5; ++j) {
            matrix[i][j] = i + j;
        }
    }
    
    printMatrix(matrix);

    return 0;
}
/*
    Out:
    0 1 2 3 4 
    1 2 3 4 5 
    2 3 4 5 6 
    3 4 5 6 7 
    4 5 6 7 8 
*/
