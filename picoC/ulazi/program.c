
int funkcija(int a, int b, int c, int d, int e, int f)
{
    int z = 100, x = 200;
    printf("%d\n", a);
    printf("%d\n", b);
    printf("%d\n", c);
    printf("%d\n", d);
    printf("%d\n", z);
    printf("%d\n", x);

    return a;
}



int main()
{   
    int a = 1;
    int b = 2;
    int c = 3;
    int d = 4;
    int e = 5;
    int f = 6;

    funkcija(a, b, c, d, e, f);
    return 10;
}




/*
int main()
{
    int a;
    int b;
    int c;
    a = 10;
    b = 10;
    c = 5;
    a = 2 / a / b / c / a;
    printf("%d\n", a);
    printf("%d\n", b);
    printf("%d\n", c);
    funkcija();
    return (((2+2*2)+2)/2+2)/2 * 8/(12/2-6/(1+12/(4+8/(3+2/(1+4/(1+3))))));
    rezultat je 6.
}
*/
