int saberi(int a, int b)
{
    return a + b;
}

int main(int z)
{   
    int a = 10;
    int b = 20;
    int c = - saberi(-a, - b);
    printf("%d %d %d\n", a, b, c);

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