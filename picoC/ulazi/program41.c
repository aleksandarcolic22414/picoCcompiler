#include <stdio.h>
#include <stdlib.h>

int cmp(char *a, char *b)
{
    return *a - *b;
}

void memcpy(void *a, void *b, int size)
{
    while (size--)
        *(char *)a++ = *(char *)b++;
}

void swapVal(void *a, void *b, int size)
{
    void *buff = malloc(64);
    memcpy(buff, a, size);
    memcpy(a, b, size);
    memcpy(b, buff, size);
}


void *part(void *niz, void* start, void* end, int size)
{
    void *i, *j, *pivot;
    pivot = start;                  /* pivot je prvi element podniza */
    for (i = start + size, j = start; i <= end; i += size)
        if (cmp(i, pivot) < 0)       /* ukoliko je element na koji pokazuje *i manji od pivota */
            swapVal(i, j += size, size);    /* menjaju se vrednosti dva elementa */
    swapVal(pivot, j, size);    /* postavlja se pivot u "sredinu" */
    return j;       /* vraca se njegova pozicija */
}

void quickSortEx(void *niz, void *start, void *end, int size)
{
    if (start >= end)   /* Uslov za izlazak */
        return;
    void *i = part(niz, start, end, size);     /* pivotiranje */
    quickSortEx(niz, start, i-size, size);     /* poziv za levi podniz */
    quickSortEx(niz, i+size, end, size);       /* poziv za desni podniz */
}


void quickSort(void *niz, int num, int size)
{
    quickSortEx(niz, niz, niz + size*(num-1), size);
}

/* Funkcija za stampanje elemenata niza brojeva. */
void parray(int *niz, int size)
{
    int i;
    for (i = 0; i < size; i++)
        printf("%s%d", i ? " " : "", *(niz + i));
    printf("\n");
}

void strcpy(char *s1, char *s2)
{
    while (*s1++ = *s2++)
        ;
}

int strlen(char *s)
{
    char *p = s;
    while (*s)
        ++s;
    return s - p;
}

int main(int argc, char **argv)
{
    int i, N;
    N = 30;
    char *s = malloc(1000);
    scanf("%s", s);
    printf("%s\n", s);
    quickSort(s, strlen(s), 1);
    printf("%s\n", s);

    return 0;
}

/*
    Ulaz:   Recenica za sortiranje
    Izlaz:  Sortirana recenica
    
*/
