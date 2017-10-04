/* TODO: Implement function call for arguments that are not local variables.
         Put function call and arguments as expression.
         Make new type of data: POINTER TYPE */

int funkcija(int a, int b)
{
    int c = a + b;
    printf("%d\n", c);
    printf("%d\n", a);
    printf("%d\n", b);

    return c;
}



int main()
{   
    int a = 1;
    int b = 6;

    funkcija(a + b * 10, a - b);
    return 10;
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
    printf("%d\n", a);
    printf("%d\n", b);
    printf("%d\n", c);
    funkcija();
    return (((2+2*2)+2)/2+2)/2 * 8/(12/2-6/(1+12/(4+8/(3+2/(1+4/(1+3))))));
    rezultat je 6.
}
*/
