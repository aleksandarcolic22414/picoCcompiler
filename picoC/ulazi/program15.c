char f(char a, char b, int c, char d, int e, int f)
{
    return a+b+c+d+e+f;
}

int main()
{
    char a, b, c, d;
    a = 10;
    b = 20;
    c = 30;
    d = 40;

    printf("%d\n", f(a, b, c, d, 50, 60));

    char e = a && b;
    printf("%d\n", e);

    return 0;
}

/*  Izlaz:
    -46
    1
*/

