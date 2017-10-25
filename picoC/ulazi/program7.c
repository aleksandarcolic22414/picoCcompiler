int main()
{
    int a = 10, b = 20;
    a *= 100;
    printf("%d\n", a);
    a /= b;
    printf("%d\n", a);
    printf("%d %d\n", b += a /= b, a -= 4);
    
    return 0;
}

/* Izlaz:
1000
50
22 -2
 */
