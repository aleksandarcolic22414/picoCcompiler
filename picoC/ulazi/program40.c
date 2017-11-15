int cmp(int *a, int *b)
{
    return *a - *b;
}

void memcpy(void *a, void *b, int size)
{
    while (size--)
        *a++ = *b++;
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

int main(int argc, char **argv)
{
    int i, N;
    N = 20;
    int *niz = malloc(1000);
    int *h = niz;
    for (i = 0; i < N; i++)
        *(h+i) = rand() % 50;
    parray(niz, N);
    quickSort(niz, N, 4);
    parray(niz, N);

    return 0;
}

/*
    Izlaz:
    20 random brojeva
    sortirani brojevi
*/