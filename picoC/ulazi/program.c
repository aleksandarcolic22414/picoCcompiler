#include <stdio.h>

int main(int argc, char *argv[])
{
    int a = 5;
    int b = 11;
    int c = 16;
    
    if (a == 5) {
        printf("A == 5!\n");
        if (a == 5 && b == 11) {
            printf("Dobar\n");
            if (c == 16) {
                printf("Odlican\n");
            }
            printf("Odlican\n");
        } else
            printf("Los\n");
        printf("Opet odlican\n");
    } else if (b == 10)
        printf("B == 10\n");
    else if (c == 15)
        printf("c == 15\n");
    else
        printf("print anyway\n");
    return 0;
}

