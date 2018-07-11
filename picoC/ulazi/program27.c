#include <stdio.h>

int main(int argc, char **argv)
{
    for (int i = 0; i < argc; ++i) 
    {
        for (char *ch = *argv++; *ch; ++ch)
            printf("%c", *ch);
        printf("\n");
    }

    return 0;
}

/* Izlaz je:
    (Ime programa)
    (Prvi argument)
    (Drugi argument)
    (Treci argument)
        ...
 */