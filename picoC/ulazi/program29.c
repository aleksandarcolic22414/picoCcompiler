#include <stdio.h>

int main(int argc, char **argv)
{
    for (int i = 0; i < argc-1; ++i)
    {
        printf("%s\n", *++argv);
    }
    
    return 0;
}

/* Izlaz je:
    (Prvi argument)
    (Drugi argument)
    (Treci argument)
        ...
 */
