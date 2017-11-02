int main()
{
    int q = 10;
    int w = 20;
    int e = 30;
    int r = 40;
    int t = 50;
    int y = 60;

    int b = 5;
    int *a = &y;
    a += b;

    printf("%d\n", *a);

    return 0;
}

/* Izlaz je:
    10
 */
