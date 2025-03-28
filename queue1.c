#include<stdio.h>
#include<stdlib.h>
typedef struct{
    int size;
    int front;int rear;
    int arr[5];
}queue;
queue* create(){
    queue* q=(queue*)malloc(sizeof(queue));
    q->front=-1;
    q->rear=-1;q->size=0;
    return q;
}
int isfull(queue* q){
    if(q->size==5)
    return 1;
    else 
    return 0;
}
int isempty(queue* q){
    if(q->front==-1 || q->size==0){
        printf("empty");
    return 1;
    }
    else
    return 0;
}
void enqueue(queue* q,int val){
    if(isfull(q)){
        printf(" full ");
    return;
    }
    if(q->front==-1 && q->rear==-1){
        q->front=0;q->rear=0;
        q->arr[q->rear]=val;
    }
    else{
        
        q->rear=(q->rear+1)%5;
        //printf("%d ",q->rear);
        q->arr[q->rear]=val;
    }
    q->size++;
    return;
}
void dequeue(queue* q){
    if(isempty(q))
    return;
    if(q->size==1){
        q->front=-1;
        q->rear=-1;
    }
    else{
        q->front=(q->front+1)%5;
    }
    q->size--;
}
int main(){
    queue* q=create();
    enqueue(q,10);
    enqueue(q,20);enqueue(q,30);enqueue(q,40);enqueue(q,50);
    dequeue(q);
    enqueue(q,60);
    dequeue(q);enqueue(q,70);dequeue(q);dequeue(q);dequeue(q);dequeue(q);dequeue(q);dequeue(q);dequeue(q);
    int a=(q->front);
    int b=(q->rear);
    //printf("%d %d",a,b);
    if(a>b){
        for(int i=a;i<=b+5;i++){
        printf("%d ",q->arr[i%5]);
    }
    }
//printf("%d %d",a,b);
else{
    for(int i=a;i<=b;i++){
        printf("%d ",q->arr[i]);
    }
}

}