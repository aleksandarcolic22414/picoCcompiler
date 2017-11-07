int main(int argc, char **argv)
{
    int i;
    for (i = 0; i < argc; ++i) {
        char *ch = *argv++;
        for (; *ch; ++ch)
            printf("%c", *ch);
        printf("\n");
    }

    return 0;
}

/* Izlaz je:
    (Ime programa)
    (Prvi argument)
    (Drugi argument)
    (Treci argument)
        ...
 */