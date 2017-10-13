int main()
{
    int a = 12312313;
    a = 12312313 == 12312313;
    printf("%d\n", a);
    return 0;
}
/* TODO: Check NasmTools.castTo functions. When assignment is done,
    castRegister only returns string representation of casts, but doesn't emit
    actual cast instruction. So above code won't work. */

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