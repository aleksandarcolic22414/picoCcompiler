#include <stdio.h>

int *niz[5];

int main()
{
    niz[0] = "Extern";
    niz[1] = "Array";
    niz[2] = "Direct";
    niz[3] = "Access";
    niz[4] = "Ha!";

    for (int i = 0; i < 5; ++i)
        printf("%s\n", niz[i]);
    return 0;
}
/*
    Out:
    Extern
    Array
    Direct
    Access
    Ha!
*/