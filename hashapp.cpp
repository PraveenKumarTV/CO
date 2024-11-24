#include <iostream>
#include <unordered_map>
#include <list>
#include <string>
using namespace std;

// Symbol Table Entry (Structure to represent each symbol)
struct Symbol {
    string name;   // Symbol name
    string type;   // Type (int, float, string, etc.)
    string scope;  // Scope (local, global)
    
    Symbol(string n, string t, string s) : name(n), type(t), scope(s) {}
};

// Symbol Table using Hash Map
class SymbolTable {
private:
    unordered_map<string, list<Symbol>> table;  // Hash table with key as symbol name and value as a list of Symbols

public:
    // Insert a symbol into the symbol table
    void insert(string name, string type, string scope) {
        Symbol sym(name, type, scope);
        table[name].push_back(sym);  // Insert symbol into list for the given symbol name
        cout << "Inserted: " << name << " (" << type << ", " << scope << ")\n";
    }

    // Lookup a symbol in the symbol table
    bool lookup(string name) {
        if (table.find(name) != table.end()) {
            cout << "Found symbol: " << name << "\n";
            return true;
        }
        return false;
    }

    // Delete a symbol from the symbol table
    void deleteSymbol(string name) {
        if (table.find(name) != table.end()) {
            table[name].clear();  // Clear the list for that symbol name
            cout << "Deleted symbol: " << name << "\n";
        } else {
            cout << "Symbol " << name << " not found to delete.\n";
        }
    }

    // Display the contents of the symbol table
    void display() {
        cout << "Symbol Table:\n";
        for (auto& entry : table) {
            cout << "Symbol: " << entry.first << "\n";
            for (auto& sym : entry.second) {
                cout << "  - Name: " << sym.name << ", Type: " << sym.type << ", Scope: " << sym.scope << "\n";
            }
        }
    }
};

int main() {
    SymbolTable symbolTable;

    // Inserting symbols into the table
    symbolTable.insert("x", "int", "local");
    symbolTable.insert("y", "float", "global");
    symbolTable.insert("z", "int", "local");
    symbolTable.insert("myFunc", "function", "global");

    // Lookup for a symbol
    if (symbolTable.lookup("x")) {
        cout << "Symbol x found.\n";
    }

    // Delete a symbol
    symbolTable.deleteSymbol("y");

    // Display the current symbol table
    symbolTable.display();

    return 0;
}
