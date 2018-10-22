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

pthread_mutex_t lock;

// function to add a node to the end of the linked list
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

// function to delete the fist element in the linked list
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

// producer 1 method (add an odd node to list)
void *producer1Runner(void *linkedlist)
{
    srand(time(0));
    struct Linked_List* list = (struct Linked_List*)linkedlist;
    
    while(list->curr_size < 30)
    {
        pthread_mutex_lock(&lock);
        printf("Producer1:\n");
        printf("\tList size before appending: %d\n", list->curr_size);
        int rand_num = ((1 + rand() % 48));
        if(rand_num % 2 == 0) { rand_num++; } // if value is even, add 1
        append(list, rand_num);
        printf("\tValue of node appended: %d\n", rand_num);
        printf("\tList size after appending: %d\n", list->curr_size);
        pthread_mutex_unlock(&lock);
    }
    return 0;
}

// producer 2 method (add an even node to list)
void *producer2Runner(void* linkedlist)
{
    srand(time(0));
    struct Linked_List* list = (struct Linked_List*)linkedlist;
    
    while(list->curr_size < 30)
    {
        pthread_mutex_lock(&lock);
        printf("Producer2:\n");
        printf("\tList size before appending: %d\n", list->curr_size);
        int rand_num = ((1 + rand() % 49));
        if(rand_num % 2 == 1) { rand_num++; } // if value is odd, add 1
        append(list, rand_num);
        printf("\tValue of node appended: %d\n", rand_num);
        printf("\tList size after appending: %d\n", list->curr_size);
        pthread_mutex_unlock(&lock);
    }
    return 0;
}

// consumer 1 method (delete first odd node in list)
void *consumer1Runner(void* linkedlist)
{
    struct Linked_List* list = (struct Linked_List*)linkedlist;
    
    while(list->curr_size > 0)
    {
        if(list->head->value % 2 == 1) // if first node in the list is odd
        {
            pthread_mutex_lock(&lock);
            printf("Consumer1:\n");
            printf("\tList size before removing: %d\n", list->curr_size);
            int value_deleted = list->head->value;
            deleteFront(list);
            printf("\tValue of node removed: %d\n", value_deleted);
            printf("\tList size after removing: %d\n", list->curr_size);
            pthread_mutex_unlock(&lock);
        }
    }
    return 0;
}

// consumer 2 method (delete first even node in list)
void *consumer2Runner(void* linkedlist)
{
    struct Linked_List* list = (struct Linked_List*)linkedlist;
    
    while(list->curr_size > 0)
    {
        if(list->head->value % 2 == 0) // if first node in the list is even
        {
            pthread_mutex_lock(&lock);
            printf("Consumer2:\n");
            printf("\tList size before removing: %d\n", list->curr_size);
            int value_deleted = list->head->value;
            deleteFront(list);
            printf("\tValue of node removed: %d\n", value_deleted);
            printf("\tList size after removing: %d\n", list->curr_size);
            pthread_mutex_unlock(&lock);
        }
    }
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
    
    // initialize mutex
    pthread_mutex_init(&lock, NULL);
    
    // declare and initialize producer and consumer threads
    pthread_t producer1;
    pthread_t producer2;
    pthread_t consumer1;
    pthread_t consumer2;
    pthread_create(&producer1, NULL, producer1Runner, (void *) linkedList);
    pthread_create(&producer2, NULL, producer2Runner, (void *) linkedList);
    pthread_create(&consumer1, NULL, consumer1Runner, (void *) linkedList);
    pthread_create(&consumer2, NULL, consumer2Runner, (void *) linkedList);
    
    // join all threads
    pthread_join(producer1, NULL);
    pthread_join(producer2, NULL);
    pthread_join(consumer1, NULL);
    pthread_join(consumer2, NULL);
    pthread_mutex_destroy(&lock);
    
    return 0;
}

