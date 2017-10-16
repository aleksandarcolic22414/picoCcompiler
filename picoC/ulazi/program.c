int main()
{
    int a, b, c;
    a = 10;
    b = 20;
    c = 1 && (0 || (1 && 0) || 0);
    printf("%d\n", c);
    return c;
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
