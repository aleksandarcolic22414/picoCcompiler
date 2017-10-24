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

/* Dobar izlaz je: 
0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 
I je cetrdeset. a = 1600
*/
