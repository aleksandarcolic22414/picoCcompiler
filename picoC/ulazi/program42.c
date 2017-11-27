int main()
{
    
    int niz[10];
    int i;
    *(niz + 0) = 1;
    *(niz + 1) = 2; 
    *(niz + 2) = 3; 
    *(niz + 3) = 4; 
    *(niz + 4) = 5; 
    *(niz + 5) = 6; 
    *(niz + 6) = 7; 
    *(niz + 7) = 8; 
    *(niz + 8) = 9; 
    *(niz + 9) = 10; 

    for (i = 0; i < 10; ++i)
        printf("%s%d", i ? " " : "", *(niz + i));
    printf("\n");

    return 0;
}
/*
    Output:
    1 2 3 4 5 6 7 8 9 10
*/