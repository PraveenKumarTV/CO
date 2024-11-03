#include <iostream>
#include <queue>
#include <unordered_map>
#include <string>

using namespace std;

struct Node {
    char data;
    int freq;
    Node* left;
    Node* right;

    Node(char d, int f) : data(d), freq(f), left(nullptr), right(nullptr) {}
};

struct Compare {
    bool operator()(Node* l, Node* r) {
        return l->freq > r->freq;
    }
};

Node* buildHuffmanTree(const string& text) {
    unordered_map<char, int> freqMap;

    for (char ch : text) {
        freqMap[ch]++;
    }

    priority_queue<Node*, vector<Node*>, Compare> pq;

    for (auto pair : freqMap) {
        pq.push(new Node(pair.first, pair.second));
    }

    while (pq.size() > 1) {
        Node* left = pq.top(); pq.pop();
        Node* right = pq.top(); pq.pop();
        Node* internalNode = new Node('\0', left->freq + right->freq);
        internalNode->left = left;
        internalNode->right = right;
        pq.push(internalNode);
    }

    return pq.top();
}

void generateHuffmanCodes(Node* root, const string& str, unordered_map<char, string>& huffmanCode) {
    if (!root) return;

    if (root->left == nullptr && root->right == nullptr) {
        huffmanCode[root->data] = str;
    }

    generateHuffmanCodes(root->left, str + "0", huffmanCode);
    generateHuffmanCodes(root->right, str + "1", huffmanCode);
}

void printHuffmanCodes(const unordered_map<char, string>& huffmanCode) {
    cout << "Character Huffman Codes:\n";
    for (auto pair : huffmanCode) {
        cout << pair.first << ": " << pair.second << endl;
    }
}

int main() {
    string text = "huffman coding example";
    Node* root = buildHuffmanTree(text);
    unordered_map<char, string> huffmanCode;
    generateHuffmanCodes(root, "", huffmanCode);
    printHuffmanCodes(huffmanCode);
    return 0;
}