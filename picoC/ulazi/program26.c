#include <stdio.h>
#include <stdlib.h>

void strcpy(char *s1, char *s2)
{
    for (; *s1++ = *s2++ ; )
        ;
}

int main(int argc, char **argv)
{
    char *s = (char *)malloc(256);
    char *h = "Aleksandar";

    strcpy(s, h);
    printf("%s\n", s);

    printf("Argv[0] = %s\n", *argv);

    return 0;
}

/* Izlaz je:
    Aleksandar
    Argv[0] = ./run (Ili kako je vec ime fajla koji se pokrece...)
 */