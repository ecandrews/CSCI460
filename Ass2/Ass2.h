//  CSCI 460 - Assignment 2
//  Elizabeth Andrews

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

struct Node {
    int value;
    struct Node* next;
    struct Node* prev;
};

int main(int argc, char *argv[])
{
    void append(struct Node* head_ref, int new_val);
    srand(time(0));
    
    // use mutex to lock the linked list
    // TODO:
    // create a doubly linked list with maximum 30 nodes
    // each node has random integer value <= 50
    // initialize containing three nodes
    
    // create initial three nodes
    
    struct Node* head_ref = (struct Node*)malloc(sizeof(struct Node));
    head_ref->value = (rand() % (50 + 0 + 1)) + 0;
    
    //append(head_ref, (rand() % (50 + 1)));
    //append(head_ref, (rand() % (50 + 1)));
    
    printf("first node value: %i\n", head_ref->value);
    //printf("second node value: %i", head_ref->next->value);
    //printf("third node value: %i", head_ref->next->next->value);
    
    // declare runner methods
    void *producer1Runner(void *argv);
    void *producer2Runner(void *argv);
    void *consumer1Runner(void *argv);
    void *consumer2Runner(void *argv);
    
    // declare and initialize producers and consumers
    pthread_t producer1;
    pthread_create(&producer1, NULL, producer1Runner, argv[1]);
    pthread_t producer2;
    pthread_create(&producer2, NULL, producer2Runner, argv[1]);
    pthread_t consumer1;
    pthread_create(&consumer1, NULL, consumer1Runner, argv[1]);
    pthread_t consumer2;
    pthread_create(&consumer2, NULL, consumer2Runner, argv[1]);
    
    printf("hellooooooooo\n");
    
    //free(head_ref);
    //free(head_ref->next);
    //free(head_ref->next->next);
    return 0;
}

void *producer1Runner()
{
    
    return 0;
}

void *producer2Runner()
{
    
    return 0;
}

void *consumer1Runner()
{
 
    return 0;
}

void *consumer2Runner()
{
    
    return 0;
}

void append(struct Node* head_ref, int new_value)
{
    struct Node* new_node = (struct Node*)malloc(sizeof(struct Node));
    
    if(new_node == NULL)
    {
        printf("attempted to allocate, failed");
    }
    
    struct Node* last = head_ref;
    new_node->value = new_value;
    new_node->next = NULL;
    if(head_ref == NULL) {
        new_node->prev = NULL;
        head_ref = new_node;
        return;
    }
    while(last->next != NULL)
    {
        last = last->next;
    }
    last->next = new_node;
    new_node->prev = last;
    return;
}