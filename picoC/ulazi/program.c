int main()
{
    int i, c = 15;
    
    for (i = 0; i < 50; i++) {
        if (((i >= 20 && i <= 30) || i == 35) && c-- > -10)
            continue;
        else if (i == 40) {
            int a = i*i;
            printf("\nI je cetrdeset. a = %d\n", a);
            break;
        }
        printf("%d ", i);
    }

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
