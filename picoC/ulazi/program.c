int fib(int n)
{
    if (n < 2)
        return n;
    int a = fib(n-1) + fib(n-2);
    printf("%d ", a);
    return a;
}

int main()
{
    int a;
    a = fib(30);
    printf("%d\n", a);
    
    return 0;
}


/*
int main()
{
    int a = 10, b = 10, c = 5;
    a = 2 / a / b / c / a;
    printf("%d %d %d\n", a, b, c);
    
    return (((2+2*2)+2)/2+2)/2 * 8/(12/2-6/(1+12/(4+8/(3+2/(1+4/(1+3))))));
}
*/
