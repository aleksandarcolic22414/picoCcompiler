void strcpy(char *s1, char *s2)
{
    while (*s1++ = *s2++)
        ;
}

int main(int argc, char **argv)
{
    char *s1 = "Aleksandar Colic";
    char *s2 = malloc(256);
    strcpy(s2, s1);

    printf("%s\n", s2);

    return 0;
}
/*
    Izlaz je:
    Aleksandar Colic
*/