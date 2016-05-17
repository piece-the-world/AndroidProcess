#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <sys/system_properties.h>
#include <stdlib.h>
#include <stdio.h>
#include <signal.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <errno.h>


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
