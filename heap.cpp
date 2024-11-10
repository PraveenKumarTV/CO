#include<bits/stdc++.h>
using namespace std;
class minheap{
    private:
    vector<int>heap;
    int left(int i){
        return 2*i+1;
    }
    int right(int i){
        return 2*i+2;
    }
    void heapify(int i){
        int small=i;
        int lc=left(i);
        int rc=right(i);
        if(lc<heap.size()&&heap[lc]<heap[small]){
            small=lc;
        }
        if(rc<heap.size()&&heap[rc]<heap[small]){
            small=rc;
        }
        if(small!=i){
            swap(heap[i],heap[small]);
            heapify(small);
        }
    }
    public:
    void build(vector<int>arr){
        heap=arr;
        for(int i=heap.size()/2-1;i>=0;--i){
            heapify(i);
        }
    }
    void print(){
        for(int val:heap){
            cout<<val<<" ";
        }
        cout<<endl;
    }
    void deleteroot(){
        if(heap.size()==0){
            cout<<"Empty elements";
            return;
        }
        heap[0]=heap[heap.size()-1];
        heap.pop_back();
        heapify(0);
    }

};

int main(){
    vector<int>arr={10,20,5,30,15};
    minheap heap;
    heap.build(arr);
    cout<<"created heap: ";
    heap.print();
    arr.push_back(4);
    heap.build(arr);
    heap.print();
    arr.push_back(16);
    heap.build(arr);
    heap.print();
    heap.deleteroot();
    heap.print();

}