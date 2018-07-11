#include <stdio.h>
#include <stdlib.h>

char *func()
{
    return malloc(256);
}

int strcpy(char *s1, char *s2)
{
    while (*s1++ = *s2++)   
        ;
    return 0;
}

int main(int argc, int argv)
{
    char *s = func();
    strcpy(s, "Aleksandar Colic");
    printf("%s\n", s);
    return 0;
}

/*
    Izlaz je:
    Aleksandar Colic
*/