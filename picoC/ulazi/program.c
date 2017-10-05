/* TODO: Put function call as expression. */

int saberi(int a, int b)
{
    return a + b;
}

int oduzmi(int a, int b)
{
    return a - b;
}

int pomnozi(int a, int b)
{
    return a * b;
}

int podeli(int a, int b)
{
    return a / b;
}
/*
int main()
{   
    int a = 10;
    int b = 20;

    int d = pomnozi(saberi(a, podeli(b, a)), a);
    printf("%d\n", d);

    return 10;
}
*/




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
    
    return (((2+2*2)+2)/2+2)/2 * 8/(12/2-6/(1+12/(4+8/(3+2/(1+4/(1+3))))));
    
}

