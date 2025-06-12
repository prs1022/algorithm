package com.algorithm.leetcode.寻路;
import java.util.*;
/**
 * @author rensong.pu
 * @date 2025/6/11
 */
public class Graph {
        private int vertices; // 顶点数量
        private LinkedList<Integer>[] adjacencyList; // 邻接表

        // 构造函数
        Graph(int vertices) {
            this.vertices = vertices;
            adjacencyList = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjacencyList[i] = new LinkedList<>();
            }
        }

        // 添加边
        void addEdge(int src, int dest) {
            adjacencyList[src].add(dest);
            // 如果是无向图，取消下面的注释
            // adjacencyList[dest].add(src);
        }

        // 广度优先搜索
        void BFS(int startVertex, int endVertex) {
            boolean[] visited = new boolean[vertices];
            Queue<Integer> queue = new LinkedList<>();
            //key: 当前节点  value: 前一个节点
            Map<Integer, Integer> path = new HashMap<>();
            int[] distance = new int[vertices]; // 记录从起点到每个顶点的距离
            distance[startVertex] = 0;
            visited[startVertex] = true;
            queue.add(startVertex);

            while (!queue.isEmpty()) {
                int currentVertex = queue.poll();
                System.out.print(currentVertex + " ");

                Iterator<Integer> iterator = adjacencyList[currentVertex].listIterator();
                while (iterator.hasNext()) {
                    int adjVertex = iterator.next();
                    if (!visited[adjVertex]) {
                        visited[adjVertex] = true;
                        path.put(adjVertex, currentVertex);
                        queue.add(adjVertex);
                        distance[adjVertex] = distance[currentVertex] + 1;
                        if(adjVertex == endVertex){
                            break;
                        }
                    }
                }
            }

            // 打印最短路径长度
            System.out.println("The shortest path length from " + startVertex + " to " + endVertex + " is: " + distance[endVertex]);

            // 输出路径
            System.out.println("\nPath from " + startVertex + " to " + endVertex + ":");
            while (endVertex != startVertex) {
                System.out.print(endVertex + " <- ");
                endVertex = path.get(endVertex);
                if (endVertex == -1) {
                    System.out.println("No path found.");
                }
            }
        }


    // 深度优先搜索
    void DFS(int startVertex) {
        boolean[] visited = new boolean[vertices];
        DFSUtil(startVertex, visited);
    }

    // 深度优先搜索的辅助递归函数
    private void DFSUtil(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        Iterator<Integer> iterator = adjacencyList[vertex].listIterator();
        while (iterator.hasNext()) {
            int adjVertex = iterator.next();
            if (!visited[adjVertex]) {
                DFSUtil(adjVertex, visited);
            }
        }
    }

    /**
     * 0 -> 1 ->3
     *          |
     *   -> 2 ->4
     * @param args
     */


    public static void main(String[] args) {
            Graph graph = new Graph(5);
            graph.addEdge(0, 2);
            graph.addEdge(0, 1);
            graph.addEdge(1, 3);
            graph.addEdge(2, 1);
            graph.addEdge(3, 4); // 添加一条边以形成更复杂的路径
            System.out.println("BFS traversal starting from vertex 0 to vertex 4:");
            graph.BFS(0, 4);
            System.out.println("\nDFS traversal starting from vertex 0:");
            graph.DFS(0);
        }

}
