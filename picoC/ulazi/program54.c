int onbits(int n)
{
    int c = 0;
    while (n) {
        c += n & 1;
        n >>= 1;
    }
    return c;
}

int main()
{
    int n = 0;
    printf("Unosi se prirodan broj: ");
    scanf("%d", &n);

    printf("Broj jedinica u binarnoj reprezentaciji broja je: %d\n", onbits(n));

    return 0;
}
/* 
    In: Prirodan broj
    Out: Broj jedinica u binarnoj reprezentaciji broja 
 */
