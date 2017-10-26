int main()
{
    int a, b, c, d;
    a = 10;
    b = 20;
    c = 30;
    d = 40;
    
    int e = (b >= a && b <= c) ? c >= b && c > d ? c : 0 : 1;

    printf("%d\n", e);
    
    return 0;
}
/* Izlaz je:
    0 
*/