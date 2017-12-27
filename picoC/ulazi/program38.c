#include <stdio.h>
#include <stdlib.h>

char *c = "Aleksandar Colic";

int isdigit(int ch)
{
    return ch >= '0' && ch <= '9';
}

int isspace(int ch) // samo 3 space znaka
{
    return ch == '\n' || ch == '\t' || ch == '\r';
}

int strlen(char *s)
{
    char *a;
    for (a = s; *s; s++)
        ;
    return s - a;
}

void strcpy(char *s1, char *s2)
{
    while (*s1++ = *s2++)
        ;
}

char *strdup(char *s)
{
    int len = strlen(s);
    char *nstr = malloc(len + 16);
    strcpy(nstr, s);

    return nstr;
}

int atoia(char *c)
{
    int i, n, sign;
    while (isspace(*c)) // ignorisu se escape-znaci
        ++c;
    sign = *c == '-' ? -1 : 1;  // uzmi predznak ako postoji
    if (*c == '-' || *c == '+')  // preskoci predznak ako postoji
        ++c;

    for (n = 0; isdigit(*c); ++c) 
        n = n * 10 + (*c - '0');
    return sign * n;
}

int main(int argc, char **argv)
{
    while (--argc)          // stampaj brojeve sa ulaza
        printf("%d\n", atoia(*++argv));
    
    return 0;
}

/* 
    Input: n brojeva
    Izlaz: n brojeva kao int-ovi
 */