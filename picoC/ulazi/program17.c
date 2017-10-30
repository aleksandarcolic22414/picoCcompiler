
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

    char e = f(a && b || !c, b >= a && d >= c, c >= b && c <= d, 0, 62, 5 && 0);
    char f = f(a && b || !c, b >= a && d >= c, c >= b && c == d, 0, 5-5 || 0, 97);
    char g = f(
        !(a && b || !c),
        b >= a && !(d >= c),
        c >= b && c == d,
        0,
        3/2-1 || (25*5) && 0,
        97
    );

    printf("%c%c%c\n", e, f, g);

    return 0;
}

/*  Izlaz je:
    Aca
*/
