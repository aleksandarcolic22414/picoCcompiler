int main(int argc, char **argv)
{
    int i;
    for (i = 0; i < argc; ++i)
        printf("%s\n", *argv++);
    
    return 0;
}

/* Izlaz je:
    (Ime programa)
    (Prvi argument)
    (Drugi argument)
    (Treci argument)
        ...
 */
