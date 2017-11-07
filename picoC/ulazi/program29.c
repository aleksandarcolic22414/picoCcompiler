int main(int argc, char **argv)
{
    int i;
    for (i = 0; i < argc-1; ++i)
        printf("%s\n", *++argv);
    
    return 0;
}

/* Izlaz je:
    (Prvi argument)
    (Drugi argument)
    (Treci argument)
        ...
 */
