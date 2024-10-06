#include<stdio.h>
#include<stdlib.h>

typedef struct{
    int data;
    struct node* next;
    struct node* prev;
}node;

node* createll(int arr[],int n){
    node* head=(node*)malloc(sizeof(node));
    head->data=arr[0];
    head->next=NULL;
    node* cur=head;
    for(int i=1;i<4;i++){
        node* tmp=(node*)malloc(sizeof(node));
        tmp->data=arr[i];
        cur->next=tmp;tmp->prev=cur;
        tmp->next=NULL;
        cur=cur->next;
    }
    cur->next=head;
    head->prev=cur;
    return head;
}

node* insertfirst(node* head,int val){
    if(head==NULL){
        node* nhead=(node*)malloc(sizeof(node));
        nhead->data=val;
        nhead->next=nhead;
        nhead->prev=nhead;
        return nhead;
    }
    node* nhead=(node*)malloc(sizeof(node));
    nhead->data=val;
    node* tail=(node*)malloc(sizeof(node));
    tail=head->prev;
    head->prev=nhead;
    nhead->next=head;
    tail->next=nhead;
    nhead->prev=tail;
    return nhead;


}

node* insertany(node* head,int val,int pos){
        if(head==NULL || pos==0){
            return insertfirst(head,val);
        }
        node* n=(node*)malloc(sizeof(node));
        n->data=val;
        int i=0;node* cur=head;
        do{
            if(i==pos-1){
                n->next=cur->next;
                node* tmp=cur->next;
                tmp->prev=n;
                cur->next=n;
                n->prev=cur;
                break;
            }
            i++;
            cur=cur->next;
        }while(cur!=head);
        return head;

}

node* delefir(node* head){
    if(head==NULL || head->next==head)
        return NULL;
    node* nxt=head->next;node* pv=head->prev;
    nxt->prev=head->prev;
    pv->next=nxt;
    head->next=NULL;head->prev=NULL;
    return nxt;


}

node* deleteany(node* head,int pos){
    if(head==NULL || head->next==head || pos==0){
        return delefir(head);
    }
    int i=0;
    node* cur=head;
    do{
        if(i==pos){
            node* pv=cur->prev;
            node* nxt=cur->next;
            nxt->prev=pv;pv->next=nxt;
            free(cur);
            break;
        }
        i++;cur=cur->next;
    }while(cur!=head);
    return head;

}

int main(){
    int arr[]={1,2,3,4};
    node* head=(node*)malloc(sizeof(node));
    head=createll(arr,4);
    head=insertany(head,10,0);
    head=deleteany(head,0);
    node* cur=(node*)malloc(sizeof(node));
    cur=head;
    do{
        printf("%d ",cur->data);
        cur=cur->next;
    }while(cur!=head);

}
