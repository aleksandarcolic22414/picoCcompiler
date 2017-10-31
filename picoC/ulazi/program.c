int main()
{
    int b = 10;
    int ***a = &b;
    
    printf("*a = %d\n", a);
    scanf("%d", a);
    printf("b = %d\n", b);

    return 0;
}

