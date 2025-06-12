package com.hw;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *      * 题目描述
 * 公司创新实验室正在研究如何最小化资源成本，最大化资源利用率，请你设计算法帮他们
 * 解决一个任务混部问题：有taskNum 项任务，每个任务有开始时间（startTime
 * ），结束时间（endTime），并行度(parallelism)三个属性，并行度是指这个任务运行时将
 * 会占用的服务器数量，一个服务器在每个时刻可以被任意任务使用但最多被一个任务占用
 * ，任务运行完会立即释放（结束时刻不占用）。任务混部问题是指给定一批任务，让这批
 * 任务由同一批服务器承载运行，请你计算完成这批任务混部最少需要多少服务器，从而最
 * 大化控制资源成本。
 * 输入描述
 * 第一行输入为taskNum，表示有taskNum 项任务
 * 接下来taskNum 行，每行三个整数，表示每个任务的开始时间（startTime
 * ），结束时间（endTime），并行度(parallelism)
 * 输出描述
 * 一个整数，表示最少需要的服务器数量
 * @author rensong.pu
 * @date 2025/6/11
 */
public class 任务混部 {

    static class Event {
        int time;
        int type; // 1表示开始，-1表示结束
        int parallelism;

        Event(int time, int type, int parallelism) {
            this.time = time;
            this.type = type;
            this.parallelism = parallelism;
        }
    }

    /**
     * 这是一个经典的 区间调度问题 ，可以用 扫描线算法 来解决：
     *
     * ## 算法思路
     * 1. 事件化处理 ：将每个任务转换为开始和结束事件
     * 2. 时间排序 ：按时间顺序处理所有事件
     * 3. 动态统计 ：维护当前时刻所需的服务器数量
     * 4. 记录最大值 ：跟踪过程中的最大服务器需求
     * @param taskNum
     * @param tasks
     * @return
     */
    public static int minServers(int taskNum, int[][] tasks) {
        List<Event> events = new ArrayList<>();

        // 将任务转换为事件
        for (int[] task : tasks) {
            int startTime = task[0];
            int endTime = task[1];
            int parallelism = task[2];
            events.add(new Event(startTime, 1, parallelism));
            events.add(new Event(endTime, -1, parallelism));
        }

        // 按时间排序，结束事件优先于开始事件（相同时间）
        events.sort((a, b) -> {
            if (a.time != b.time) {
                return Integer.compare(a.time, b.time);
            }
            return Integer.compare(a.type, b.type); // -1 < 1，结束优先
        });

        int currentServers = 0;
        int maxServers = 0;

        // 扫描线处理事件
        for (Event event : events) {
            if (event.type == 1) { // 任务开始
                currentServers += event.parallelism;
            } else { // 任务结束
                currentServers -= event.parallelism;
            }
            maxServers = Math.max(maxServers, currentServers);
        }

        return maxServers;

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int taskNum = scanner.nextInt();
        int[][] tasks = new int[taskNum][3];

        for (int i = 0; i < taskNum; i++) {
            tasks[i][0] = scanner.nextInt(); // startTime
            tasks[i][1] = scanner.nextInt(); // endTime
            tasks[i][2] = scanner.nextInt(); // parallelism
        }

        System.out.println(minServers(taskNum, tasks));
    }

}
