int gcd(int a, int b)
{
    if (a == 0 || b == 0)
        return a + b;
    return gcd(b, a%b);
}

int lcm(int a, int b)
{
    return a*b / gcd(a, b);
}

int main()
{
    int a, b;
    a = lcm(42, 68);
    printf("%d %d\n", --a, ++a);
    b = 15 + ++a;
    printf("%d\n", b);
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
