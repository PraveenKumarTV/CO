#include <iostream>
#include <vector>
#include <queue>
#include <climits>

using namespace std;

// Define a pair to represent an edge with the destination vertex and its weight
typedef pair<int, int> Edge; // <destination_vertex, weight>

// Dijkstra's algorithm to find the shortest path from a source vertex to all other vertices
void dijkstra(const vector<vector<Edge>>& graph, int source, int V) {
    // Create a priority queue to select the vertex with the smallest distance
    priority_queue<Edge, vector<Edge>, greater<Edge>> pq;

    // Initialize distances with infinity
    vector<int> dist(V, INT_MAX);
    dist[source] = 0;

    // Push the source node with a distance of 0 into the priority queue
    pq.push({0, source});

    while (!pq.empty()) {
        // Get the vertex with the minimum distance
        int u = pq.top().second;
        int distance_u = pq.top().first;
        pq.pop();

        // If the current distance is not the best known distance, skip
        if (distance_u > dist[u]) {
            continue;
        }

        // Explore all the adjacent vertices of u
        for (const Edge& edge : graph[u]) {
            int v = edge.first;
            int weight = edge.second;

            // If a shorter path is found, update the distance and push it to the queue
            if (dist[u] + weight < dist[v]) {
                dist[v] = dist[u] + weight;
                pq.push({dist[v], v});
            }
        }
    }

    // Print the shortest distances from the source
    cout << "Shortest distances from source vertex " << source << ":\n";
    for (int i = 0; i < V; i++) {
        cout << "Vertex " << i << ": " << (dist[i] == INT_MAX ? "INF" : to_string(dist[i])) << endl;
    }
}

int main() {
    int V, E;
    cout << "Enter the number of vertices: ";
    cin >> V;
    cout << "Enter the number of edges: ";
    cin >> E;

    // Create an adjacency list for the graph
    vector<vector<Edge>> graph(V);

    // Input the edges
    cout << "Enter the edges (u, v, weight) where u and v are vertex indices (0-based):\n";
    for (int i = 0; i < E; i++) {
        int u, v, weight;
        cin >> u >> v >> weight;
        graph[u].push_back({v, weight});
        graph[v].push_back({u, weight});  // For undirected graph, add reverse edge as well
    }

    int source;
    cout << "Enter the source vertex: ";
    cin >> source;

    // Call Dijkstra's algorithm to find the shortest path
    dijkstra(graph, source, V);

    return 0;
}
