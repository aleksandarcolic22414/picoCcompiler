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

/*
int main()
{
    int a = 10, b = 10, c = 3;
    b = a % c;
    printf("%d %d %d\n", a, b, c);
    
    return (((2+2*2)+2)/2+2)/2 * 8/(12/2-6/(1+12/(4+8/(3+2/(1+4/(1+3))))));
}
*/
