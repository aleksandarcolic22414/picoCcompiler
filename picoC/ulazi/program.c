int cmp(int a, int b)
{
    return a - b;
}

int oduzmi(int a, int b)
{
    return a - b;
}

int main()
{
    int a = 1234567;
    int b = 123456789;
    b = a == (b = 1234567);
    printf("a: %d\n", a);
    printf("b: %d\n", b);
    return 0;
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
    printf("%d %d %d\n", a, b, c);
    
    return (((2+2*2)+2)/2+2)/2 * 8/(12/2-6/(1+12/(4+8/(3+2/(1+4/(1+3))))));
}
*/
