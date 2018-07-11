#include <stdio.h>

int main()
{
    int a = 10, b, c, d;
    b = c = d = 0;
    scanf("%d%d%d%d", &a, &b, &c, &d);
    printf("a = %d, b = %d, c = %d, d = %d\n", a, b, c, d);
    
    return 0;
}

/* Izlaz je:
    a = (uneti prvi parametar sa tastature), 
    b = (uneti drugi parametar sa tastature), 
    c = (uneti treci parametar sa tastature), 
    d = (uneti cetvrti parametar sa tastature) -> u jednom redu sve. 
 */

