int strcpy(char *s1, char *s2)
{
    while (*s1++ = *s2++)   
        ;
    return 0;
}

char **func()
{
    char **array = malloc(256);
    char **h = array;
    int i;
    
    for (i = 0; i < 10; i++) {
        *h = malloc(128);
        strcpy(*h++, "check");
    }
    return array;
}

int main(int argc, int argv)
{
    char **s = func();
    int i = 10;
    while (i--)
        printf("%s\n", *s++);

    return 0;
}

/*
    Izlaz je:
    check x 10
*/
