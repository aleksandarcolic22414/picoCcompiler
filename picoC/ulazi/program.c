int main()
{
    int a = 1000;
    while (a--) {
        printf("%d ", a);
        if (a == 500) {
            while (++a < 1000)
                printf("%d ", a);
        }
    }

    return 0;
}

