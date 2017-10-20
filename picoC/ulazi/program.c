/*int gcd(int a, int b)
{
    if (a == 0 || b == 0)
        return a + b;
    return gcd(b, a%b);
}

int lcm(int a, int b)
{
    return a*b / gcd(a, b);
}

int fakt(int n)
{
    if (n < 2)
        return 1;
    int a = fakt(n-1);
    printf("%d ", a);
    return n * a;
}

int fib(int n)
{
    if (n < 2)
        return n;
    int a = fib(n-1);
    int b = fib(n-2);
    printf("%d %d %d", a, b, a+b);
    return a + b;
}
*/
int main()
{
    int i = 10;
    
    if (i > 11) {
        printf("i > 11\n");
    } else if (i <= 5) {
        printf("i < 5\n");
    } else if (i == 10)
        printf("i == 10");
    else
        printf("Else");

    return 0;
}

/* a = fakt(5 + fakt(4 + fakt(3 + fakt(2))));  */

/*
int main()
{
    int a = 10, b = 10, c = 3;
    b = a % c;
    printf("%d %d %d\n", a, b, c);
    
    return (((2+2*2)+2)/2+2)/2 * 8/(12/2-6/(1+12/(4+8/(3+2/(1+4/(1+3))))));
}
*/
