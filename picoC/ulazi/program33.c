#include <stdio.h>

int main(int argc, char **argv)
{
    char ch = '0';
    
    do 
    {
        if (ch == 'A' || ch == 'B')
            continue;
        printf("%c", ch);
    } 
    while (ch++ < 'z');
    
    printf("\n");
    return 0;
}

/* 0123...^[AB]...xyz */
