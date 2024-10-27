#include <stdio.h>
#include <stdlib.h>

typedef struct Queue {
    int front, rear, size;
    unsigned capacity;
    int* array;
} Queue;

Queue* createQueue(unsigned capacity) {
    Queue* queue = (Queue*)malloc(sizeof(Queue));
    queue->capacity = capacity;
    queue->front = queue->size = 0;
    queue->rear = capacity - 1;
    queue->array = (int*)malloc(queue->capacity * sizeof(int));
    return queue;
}

int isFull(Queue* queue) {
    return (queue->size == queue->capacity);
}

int isEmpty(Queue* queue) {
    return (queue->size == 0);
}

void enqueue(Queue* queue, int item) {
    if (isFull(queue)) return;
    queue->rear = (queue->rear + 1) % queue->capacity;
    queue->array[queue->rear] = item;
    queue->size++;
}

int dequeue(Queue* queue) {
    if (isEmpty(queue)) return -1;
    int item = queue->array[queue->front];
    queue->front = (queue->front + 1) % queue->capacity;
    queue->size--;
    return item;
}

int front(Queue* queue) {
    if (isEmpty(queue)) return -1;
    return queue->array[queue->front];
}

typedef struct StackUsingQueues {
    Queue* q1;
    Queue* q2;
} StackUsingQueues;

StackUsingQueues* createStack(unsigned capacity) {
    StackUsingQueues* stack = (StackUsingQueues*)malloc(sizeof(StackUsingQueues));
    stack->q1 = createQueue(capacity);
    stack->q2 = createQueue(capacity);
    return stack;
}

void push(StackUsingQueues* stack, int item) {
    // Enqueue item to q2
    enqueue(stack->q2, item);

    // Enqueue all elements of q1 to q2
    while (!isEmpty(stack->q1)) {
        enqueue(stack->q2, dequeue(stack->q1));
    }

    // Swap the names of q1 and q2
    Queue* temp = stack->q1;
    stack->q1 = stack->q2;
    stack->q2 = temp;
}

int pop(StackUsingQueues* stack) {
    if (isEmpty(stack->q1)) return -1;
    return dequeue(stack->q1);
}

int top(StackUsingQueues* stack) {
    if (isEmpty(stack->q1)) return -1;
    return front(stack->q1);
}

int isStackEmpty(StackUsingQueues* stack) {
    return isEmpty(stack->q1);
}

void freeStack(StackUsingQueues* stack) {
    free(stack->q1->array);
    free(stack->q1);
    free(stack->q2->array);
    free(stack->q2);
    free(stack);
}

int main() {
    StackUsingQueues* stack = createStack(100);

    push(stack, 10);
    push(stack, 20);
    push(stack, 30);

    printf("Top element is %d\n", top(stack));
    printf("Popped element is %d\n", pop(stack));
    printf("Top element is %d\n", top(stack));

    freeStack(stack);
    return 0;
}
