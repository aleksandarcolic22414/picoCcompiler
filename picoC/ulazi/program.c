int fakt(int n)
{
    if (n < 2)
        return n;
    int a = fakt(n-1);
    printf("%d ", a);

    return n * fakt(n-1);
}


int main()
{
    int a;
    a = fakt(100); 
    printf("%d\n", a);
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