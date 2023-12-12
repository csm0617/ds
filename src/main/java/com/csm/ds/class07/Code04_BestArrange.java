package com.csm.ds.class07;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 贪心算法：安排会议
 * 假设只有一个会议室，两个会议不能同时开，请在给出的时间点后尽可能多地安排会议
 */
public class Code04_BestArrange {
    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class ProgramComparator implements Comparator<Program> {
        //会议按结束时间从早到晚排序
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    /**
     * @param programs  项目
     * @param timePoint 一开始的时间点
     * @return 可以安排会议的数量
     */
    public static int bestArrange(Program[] programs, int timePoint) {
        //把会议按结束时间最早排序
        Arrays.sort(programs, new ProgramComparator());
        int result = 0;//安排会议的数量
        for (Program program : programs) {
            //如果时间点比会的开始时间早，说明该会议可以被安排
            if (timePoint <= program.start) {
                result++;
                //把会议结束的时间作为新的时间点
                timePoint = program.end;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        //方法测试
    }
}
