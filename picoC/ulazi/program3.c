int fib(int n)
{
    if (n < 2)
        return n;
    int a = fib(n-1);
    int b = fib(n-2);
    printf("%d %d ", a, b);
    return a + b;
}

int main()
{
    fib(100);
    return 0;
}