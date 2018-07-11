#include <stdio.h>
#include <stdlib.h>

int numcmp(void *a, void *b)
{
    return *(int *)a - *(int *)b;
}

void memcpy(void *buff, void *a, int size)
{
    while (size--)
    {
        *(char *)buff++ = *(char *)a++;
    }
}

void swapVal(void *a, void *b, int size)
{
    char buff[64];
    memcpy(buff, a, size);
    memcpy(a, b, size);
    memcpy(b, buff, size);
}

void *part(void *niz, void* start, void* end, int size)
{
    void *pivot = start;                  /* pivot je prvi element podniza */
	void *j = start;

    for (void *i = start + size; i <= end; i += size)
    {
        if (numcmp(i, pivot) < 0)       /* ukoliko je element na koji pokazuje *i manji od pivota */
        {
            j += size;
            swapVal(i, j, size);    /* menjaju se vrednosti dva elementa */
        }
    }
        
    swapVal(pivot, j, size);    /* postavlja se pivot u "sredinu" */
    return j;       /* vraca se njegova pozicija */
}

void quickSortEx(void *niz, void *start, void *end, int size)
{
    if (start >= end)                          /* Uslov za izlazak */
    {
        return;
    }  
    
    void *i = part(niz, start, end, size);     /* pivotiranje */
    quickSortEx(niz, start, i-size, size);     /* poziv za levi podniz */
    quickSortEx(niz, i+size, end, size);       /* poziv za desni podniz */
}

void quickSort(void *niz, int num, int size)
{
    quickSortEx(niz, niz, niz + size*(num-1), size);
}

void stampaj(int *niz, int size)
{
    for (int i = 0; i < size; i++)
    {
        printf("%s%d", i ? " " : "", niz[i]);
    }
    
    printf("\n");
}

int main(int argc, char *argv[])
{
    int niz[20];
    
    for (int i = 0; i < 20; ++i)
    {
        niz[i] = rand() % 50;
    }

    stampaj(niz, 20);
    quickSort(niz, 20, 4);
    stampaj(niz, 20);

    return 0;
}

/*
    Out:
    20 random numbers
    Those numbers sorted
*/