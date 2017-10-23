void func(int i, int j, int k)
{
    for (i = 0; i < 5; i++) {
        for (j = 0; j < 5; j++) {
            for (k = 0; k < 50; ++k)
                printf("k = %d, ", k);
            printf("\n");
            printf("j = %d, ******************\n", j);
        }
        printf("i = %d, ******************\n", i);
    }
}

int main()
{
    func(5, 5, 50);
    return 0;
}
