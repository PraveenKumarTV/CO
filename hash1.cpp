#include <iostream>
using namespace std;

class HashTable {
private:
    static const int tableSize = 10;
    int *table;

public:
    HashTable() {
        table = new int[tableSize];  // Create a table of the given size
        for (int i = 0; i < tableSize; i++) {
            table[i] = -1;  // Initialize the table with -1 (indicating empty slots)
        }
    }

    // Hash function to compute index for a key
    int hashFunction(int key) {
        return key % tableSize;
    }

    // Insert a key into the hash table using linear probing for collision resolution
    void insert(int key) {
        int index = hashFunction(key);

        // Linear Probing: If the index is already occupied, probe the next index
        while (table[index] != -1) {
            index = (index + 1) % tableSize;  // Move to the next slot (wrap around if needed)
        }

        table[index] = key;
    }

    // Search for a key in the hash table
    bool search(int key) {
        int index = hashFunction(key);
        int startIndex = index;

        // Search for the key, using linear probing if necessary
        while (table[index] != -1) {
            if (table[index] == key) {
                return true;
            }
            index = (index + 1) % tableSize;  // Move to the next slot (wrap around if needed)
            if (index == startIndex) {
                break;  // We've come full circle, key not found
            }
        }
        return false;  // Key not found
    }

    // Delete a key from the hash table
    void remove(int key) {
        int index = hashFunction(key);
        int startIndex = index;

        while (table[index] != -1) {
            if (table[index] == key) {
                table[index] = -1;  // Remove the key
                return;
            }
            index = (index + 1) % tableSize;
            if (index == startIndex) {
                break;  // We've come full circle, key not found
            }
        }
    }

    // Print the hash table
    void display() {
        for (int i = 0; i < tableSize; i++) {
            cout << "Index " << i << ": " << (table[i] == -1 ? "Empty" : to_string(table[i])) << endl;
        }
    }
};

int main() {
    HashTable ht;

    ht.insert(10);
    ht.insert(20);
    ht.insert(25);
    ht.insert(30);
    ht.insert(35);

    cout << "Hash Table after insertions: \n";
    ht.display();

    cout << "Searching for 25: " << (ht.search(25) ? "Found" : "Not Found") << endl;
    cout << "Searching for 40: " << (ht.search(40) ? "Found" : "Not Found") << endl;

    ht.remove(25);
    cout << "Hash Table after removing 25: \n";
    ht.display();

    return 0;
}
