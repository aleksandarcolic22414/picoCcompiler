int main(int argc, char **argv)
{
    for (++argv; --argc > 0; argv++) {
        for ( ; **argv; ++*argv)
            printf("%c", **argv);
        printf("\n");
    }
    
    
    return 0;
}

/* Izlaz je:
    (Prvi argument)
    (Drugi argument)
    (Treci argument)
        ...
 */