#include<iostream>
struct node{
    int data;
    node* l;
    node* r;
    node(int a){
        data=a;
        l=nullptr;r=nullptr;
    }
};
using namespace std;
node* insert(node* root,int val){
    if(root==nullptr){
        return new node(val);
    }
    if(val<root->data){
        root->l=insert(root->l,val);
    }
    else{
        root->r=insert(root->r,val);
    }
    return root;
}

void inorder(node* root){
    if(root!=nullptr){
        inorder(root->l);
        cout<<root->data<<" ";
        inorder(root->r);
    }
}

void preorder(node* root){
    if(root!=nullptr){
        cout<<root->data<<" ";
        preorder(root->l);
        preorder(root->r);
    }
}

void postorder(node* root){
    if(root!=nullptr){
        postorder(root->l);
        postorder(root->r);
        cout<<root->data<<" ";
    }
}

node* find(node* root,int val){
    if(root->data==val||root==nullptr){
        return root;
    }
    if(val<root->data){
        return find(root->l,val);
    }
    else{
        return find(root->r,val);
    }
}

node* minvalue(node* root) {
        node* current = root;
        while (current && current->l != nullptr) {
            current = current->l;
        }
        return current;
    }

node* del(node* root,int val){
    if(root==nullptr){
        return root;
    }
    if(val<root->data){
        root->l=del(root->l,val);
    }
    else if(val>root->data){
        root->r=del(root->r,val);
    }
    else{
        if(root->l==nullptr){
            node* tmp=root->r;
            delete root;
            return tmp;
        }
        else if(root->r==nullptr){
            node* tmp=root->l;
            delete root;
            return tmp;
        }
        node* tmp=minvalue(root->r);
        root->data=tmp->data;
        root->r=del(root->r,tmp->data);
    }
    return root;
} 

int main(){
    node* root=nullptr;
    root=insert(root,5);
    int arr[]={3,7,2,4,6,8};
    for(int i=0;i<6;i++){
        insert(root,arr[i]);
    }
    root=del(root,4);
    inorder(root);
    cout<<endl;
    /*preorder(root);
    cout<<endl;
    postorder(root);
    node* n=find(root,4);
    cout<<n->data;*/

}