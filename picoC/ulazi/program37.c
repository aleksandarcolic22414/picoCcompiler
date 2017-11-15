int counter = 0;

int strtoi(char *c)
{
    int r = 0;
    ++counter;

    while (*c)
        r = r*10 + (*c++ - '0');

    return r;
}

int main(int argc, char **argv)
{
    if (argc < 2) {
        printf("No program arguments;\n");
        return 1;
    }
    
    while (--argc) {
        int a = strtoi(*++argv);
        printf("Argument %d: %d\n", counter, a);
    }
    
    return 0;
}
/* 
    Izlaz je:
    
    Argument 1: (Vrednost prvog argumenta)
    Argument 2: (Vrednost drugog argumenta)
    Argument 3: (Vrednost treceg argumenta)
            ...

 */
