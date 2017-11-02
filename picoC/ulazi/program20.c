int main()
{
    char A = 65;
    char B = 66;
    char C = 67;
    char D = 68;
    char E = 69;
    char F = 70;

    char *a = &A;
    char *b = &B;
    char *c = &C;
    char *d = &D;
    char *e = &E;
    char *f = &F;
    
    char **s = &f;
    

    int i;
    for (i = 0; i < 6; i++)
        printf("%c\n", *(*s++));

    return 0;
}

/* Izlaz je:
    F
    E
    D
    C
    B
    A
 */
