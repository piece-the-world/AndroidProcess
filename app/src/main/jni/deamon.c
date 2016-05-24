#include <sys/types.h>
#include <stdlib.h>
#include <stdio.h>
#include <fcntl.h>

int main() {
    pid_t pid;
    pid = fork();
    if (pid < 0) {
        exit(EXIT_SUCCESS);
    } else if (pid == 0) {
        setsid();
        getchar();
    } else {
        exit(EXIT_SUCCESS);
    }
    return 1;
}
