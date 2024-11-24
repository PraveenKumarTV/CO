#include <bits/stdc++.h>
using namespace std;

// Function to add an edge in the graph (undirected)
void addEdge(vector<int> adj[], int u, int v) {
    adj[u].push_back(v);  // Add edge u-v
    adj[v].push_back(u);  // Add edge v-u (since the graph is undirected)
}

// Function to delete an edge from the graph (undirected)
void deleteEdge(vector<int> adj[], int u, int v) {
    // Remove v from the adjacency list of u
    adj[u].erase(remove(adj[u].begin(), adj[u].end(), v), adj[u].end());
    
    // Remove u from the adjacency list of v
    adj[v].erase(remove(adj[v].begin(), adj[v].end(), u), adj[v].end());
}

// BFS function
vector<int> bfs(vector<int> adj[], int n, int s) {
    queue<int> q;
    vector<int> vis(n + 1, 0);  // Visited array initialized to 0
    vis[s] = 1;  // Mark start node as visited
    vector<int> res;  // To store BFS result
    q.push(s);

    while (!q.empty()) {
        int node = q.front();
        q.pop();
        res.push_back(node);  // Add node to result

        // Explore all neighbors of the current node
        for (auto it : adj[node]) {
            if (!vis[it]) {
                vis[it] = 1;  // Mark the neighbor as visited
                q.push(it);  // Add neighbor to queue
            }
        }
    }
    return res;
}

// DFS helper function (recursive)
void dfs1(vector<int> adj[], vector<int>& res, vector<int>& vs, int node) {
    vs[node] = 1;  // Mark the current node as visited
    res.push_back(node);  // Add node to result

    // Explore all neighbors of the current node
    for (auto it : adj[node]) {
        if (!vs[it]) {  // If the neighbor hasn't been visited
            dfs1(adj, res, vs, it);  // Recur for the neighbor
        }
    }
}

// DFS function
vector<int> dfs(vector<int> adj[], int n, int s) {
    vector<int> vs(n + 1, 0);  // Visited array initialized to 0
    vector<int> res;  // To store DFS result
    dfs1(adj, res, vs, s);  // Start DFS from node 's'
    return res;
}

int main() {
    int n, m;
    cout << "Enter number of vertices: ";
    cin >> n;
    cout << "Enter number of edges: ";
    cin >> m;

    vector<int> adj[n + 1];  // Adjacency list (1-based indexing)

    // Reading edges
    for (int i = 0; i < m; i++) {
        int u, v;
        cout << "Enter edge (u v): ";
        cin >> u >> v;
        addEdge(adj, u, v);  // Add the edge to the graph
    }

    // Perform DFS starting from node 1
    vector<int> dfs_result = dfs(adj, n, 1);
    cout << "DFS Traversal: ";
    for (int i = 0; i < dfs_result.size(); i++) {
        cout << dfs_result[i] << " ";
    }
    cout << endl;

    // Perform BFS starting from node 1
    vector<int> bfs_result = bfs(adj, n, 1);
    cout << "BFS Traversal: ";
    for (int i = 0; i < bfs_result.size(); i++) {
        cout << bfs_result[i] << " ";
    }
    cout << endl;

    // Example of deleting an edge (you can modify as needed)
    int u, v;
    cout << "Enter an edge to delete (u v): ";
    cin >> u >> v;
    deleteEdge(adj, u, v);  // Delete the edge

    // Perform BFS again after deletion
    bfs_result = bfs(adj, n, 1);
    cout << "BFS Traversal after deleting edge (" << u << ", " << v << "): ";
    for (int i = 0; i < bfs_result.size(); i++) {
        cout << bfs_result[i] << " ";
    }
    cout << endl;

    return 0;
}
