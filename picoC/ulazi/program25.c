int main()
{
    int q = 10;
    int w = 20;
    int e = 30;
    int r = 40;
    int t = 50;
    int y = 60;

    
    int *a = &y;
    int i;
    for (i = 0; i < 6; ++i)
        printf("%s%d", i ? " " : "", *(a + i));
    printf("\n");

    return 0;
}

/* Izlaz je:
    60 50 40 30 20 10
 */
