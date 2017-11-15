int strcmp(char *s1, char *s2)
{
    for (; *s1 == *s2; s1++, s2++)
        if (*s1 == '\0')
            return 0;
    return *s1 - *s2;
}

int main(int argc, char **argv)
{
    if (argc != 3) {
        printf("Wrong number of arguments. Expecting 2;\n");
        return 1;
    }
    
    printf("%s arguments\n", !strcmp(*++argv, *++argv) ? "Same" : "Diferent");
    
    return 0;
}

/*  Uneti dva argumenta za slovno poredjenje
    Izlaz:  Same arguments -> ako su isti
            Diferent arguments -> ako su razliciti
*/
