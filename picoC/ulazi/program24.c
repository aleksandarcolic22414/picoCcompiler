#include <stdio.h>

int main()
{
    int q = 10, w = 20, e = 30, r = 40, t = 50, y = 60;
    
    for (int i = 0, *a = &y; i < 6; ++i)
    {
        printf("%d ", *(a + i));
    }

    return 0;
}

/* Izlaz je:
    60 50 40 30 20 10
 */

