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

struct Linked_List {
    int max_size;
    int curr_size;
    struct Node* head;
    struct Node* end;
};

void append(struct Linked_List* linkedlist, int new_value)
{
    struct Node* new_node = (struct Node*)malloc(sizeof(struct Node));
    new_node->value = new_value;
    
    if(linkedlist->curr_size == 0) // if this is the first node to be added to the list
    {
        linkedlist->head = new_node;
        linkedlist->end = new_node;
        new_node->prev = NULL;
        new_node->next = NULL;
        linkedlist->curr_size++;
        return;
    }
    
    linkedlist->end->next = new_node;
    new_node->prev = linkedlist->end;
    linkedlist->end = new_node;
    new_node->next = NULL;
    linkedlist->curr_size++;
    return;
}

void deleteFront(struct Linked_List* linkedlist)
{
    struct Node* to_delete = linkedlist->head;
    
    if(linkedlist->curr_size == 1) // if the first element is the only element
    {
        linkedlist->head = NULL;
        linkedlist->end = NULL;
        linkedlist->curr_size = 0;
        return;
    }
    
    to_delete->next->prev = NULL;
    linkedlist->head = to_delete->next;
    to_delete->prev = NULL;
    to_delete->next = NULL;
    linkedlist->curr_size--;
    free(to_delete);
    return;
}

void *producer1Runner(void* linkedlist)
{
    // generate a node and append it
    // odd integer less than 50
    // if buffer (?) is full
    // printf("producer1 waiting...\n");
    
    return 0;
}

void *producer2Runner(void* linkedlist)
{
    // generate a node and append it
    // even integer less than 50
    // if buffer (?) is full
    // printf("producer2 waiting...\n");
    
    return 0;
}

void *consumer1Runner(void* linkedlist)
{
    // if first node is odd, delete it from front of list
    // else wait
    // if buffer (?) is empty
    // printf("consumer1 waiting...\n");
    
    return 0;
}

void *consumer2Runner(void* linkedlist)
{
    // if first node is even, delete it from front of list
    // else wait
    // if buffer (?) is empty
    // printf("consumer2 waiting...\n");
    
    return 0;
}

int main(int argc, char *argv[])
{
    srand(time(0));
    
    // create initial three nodes
    struct Linked_List* linkedList = (struct Linked_List*)malloc(sizeof(struct Linked_List));
    linkedList->max_size = 30;
    linkedList->curr_size = 0;
    append(linkedList, (rand() % (50 + 1)));
    append(linkedList, (rand() % (50 + 1)));
    append(linkedList, (rand() % (50 + 1)));
    
    // declare and initialize producer and consumer threads
    pthread_t producer1;
    pthread_t producer2;
    pthread_t consumer1;
    pthread_t consumer2;
    pthread_create(&producer1, NULL, producer1Runner, (void *) linkedList);
    pthread_create(&producer2, NULL, producer2Runner, (void *) linkedList);
    pthread_create(&consumer1, NULL, consumer1Runner, (void *) linkedList);
    pthread_create(&consumer2, NULL, consumer2Runner, (void *) linkedList);
    
    printf("hellooooooooo\n");
    
    // join all threads
    pthread_join(producer1, NULL);
    pthread_join(producer2, NULL);
    pthread_join(consumer1, NULL);
    pthread_join(consumer2, NULL);
    return 0;
}

