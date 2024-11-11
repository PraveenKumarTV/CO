#include <iostream>
#include <queue>
#include <string>
using namespace std;

// A simple struct to represent a Task
struct Task {
    int task_id;    // Task ID
    int priority;   // Priority of the task (lower value means higher priority)
    string description;  // Task description

    // Constructor
    Task(int id, int pr, string desc) : task_id(id), priority(pr), description(desc) {}

    // Comparator for min-heap (tasks with lower priority values come first)
    bool operator>(const Task& other) const {
        return priority > other.priority;
    }
};

class TaskScheduler {
private:
    priority_queue<Task, vector<Task>, greater<Task>> minHeap;  // Min-heap to store tasks

public:
    // Add a new task to the scheduler
    void addTask(int id, int priority, string desc) {
        Task task(id, priority, desc);
        minHeap.push(task);
    }

    // Execute the task with the highest priority (min priority value)
    void executeTask() {
        if (minHeap.empty()) {
            cout << "No tasks to execute.\n";
            return;
        }
        Task task = minHeap.top();
        minHeap.pop();
        cout << "Executing task: " << task.description << " (ID: " << task.task_id << "), Priority: " << task.priority << endl;
    }
};

int main() {
    TaskScheduler scheduler;

    // Add some tasks to the scheduler
    scheduler.addTask(1, 5, "Low priority task");
    scheduler.addTask(2, 1, "High priority task");
    scheduler.addTask(3, 3, "Medium priority task");

    // Execute tasks based on priority
    scheduler.executeTask();  // Executes "High priority task"
    scheduler.executeTask();  // Executes "Medium priority task"
    scheduler.executeTask();  // Executes "Low priority task"
    scheduler.executeTask();  // No tasks left

    return 0;
}
