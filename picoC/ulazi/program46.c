#include <stdio.h>

int main()
{
    char *niz[3];
    
    niz[0] = "Direct";
    niz[1] = "array";
    niz[2] = "subsrcipting";

    for (int i = 0; i < 3; i++)
        printf("%s\n", niz[i]);
    

    return 0;
}
/*
    Output:
    Direct
    array
    subsripting
*/
