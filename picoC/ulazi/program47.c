int main(int argc, char *argv[])
{
    while (argc--) {
        for (; *argv; ++argv) {
            while (*argv[0])
                printf("%c", *argv[0]++);
            printf("\n");
        }
    }

    return 0;
}
/*
    Output:
    (1. argument)
    (2. argument)
    (3. argument)
        ...
*/
