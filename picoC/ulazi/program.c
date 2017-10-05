/* TODO: Put function call as expression. */

int funkcija(int a, int b)
{
    return a + b;
}



int main()
{   
    int a = 10;
    int b = 20;

    int d = funkcija(funkcija(a, b), funkcija(a + b, a - b));
    printf("%d\n", d);

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
