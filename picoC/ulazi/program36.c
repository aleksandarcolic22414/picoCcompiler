int **a = 1000;
char cha = 'A';
int *b = 100;
int z = 500;

char *c = "Initialized";

int main(int argc, char **argv)
{
    printf("%d\n", b);
    int a = 1000;
    b = &a;

    *b = 500;
    printf("%d\n", *b);

    z += *b += 200;
    printf("%d\n", z);

    printf("%c\n", cha);

    char cha = 'B';
    
    printf("%c\n", cha);

    printf("%s\n", c);
    c = "Override c\n";
    
    printf("%s\n", c);

    return 0;
}
/*
    Izlaz:
    
    100
    500
    1000
    A
    B
    Initialized
    Override c
*/
