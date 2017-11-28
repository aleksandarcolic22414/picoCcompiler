int main()
{
    int a = 255;
    int c = 0;

    while (a) {
        c += a & 1;
        a /= 2;
    }
    printf("%d\n", c);

    return 0;
}