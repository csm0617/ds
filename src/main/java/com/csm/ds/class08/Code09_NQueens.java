package com.csm.ds.class08;

/**
 * n皇后问题（不能让两个皇后在同一行和同一列）
 * 时间复杂度O(n^n)没办法优化了，因为找下一层
 */
public class Code09_NQueens {
    public static int nmu1(int n) {
        if (n < 1) {
            return 0;
        }
        //record[i] ->对应i行的的皇后，放在了第几列
        int[] record = new int[n];
        return process1(0, record, n);

    }

    private static int process1(int i, int[] record, int n) {
        //当i来到终止说明从0 ... n-1行的皇后都已经摆好了，此时record数组就是一种摆皇后的解决方案
        if (i == n) {
            //在这里找到摆放方案可以打印
//            printNQueens(record, n);
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            //当前i行的皇后，放在第j列，会不会和之前0...i-1的皇后，共行共列或者共斜线
            //如果是认为无效
            //否则认为无效
            if (isValid(record, i, j)) {
                record[i] = j;//把第i行j列的皇后加入record[]
                //递归继续深度搜索找第i+1行的皇后的所有可能性
                res += process1(i + 1, record, n);
            }
        }
        return res;

    }

    //当前来到了第i行，record[0...i-1]记录了前n行皇后所在的列
    //返回第i行的皇后，在第j列是否有效
    public static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {//依次遍历前i-1行的皇后
            //（共行是不可能的）1.检测是否共列 或 2.检测是否共45°斜线(也就是两个皇后的横坐标的距离和纵坐标的距离是否相等，等腰直角三角形)
            if (record[k] == j || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    public static void printNQueens(int[] record, int n) {
        for (int queen : record) {
            for (int i = 0; i < n; i++) {
                System.out.printf("%-3d", 0);
                if (i == queen) {
                    System.out.printf("%-3d", 1);
                }
            }
            System.out.println();
        }
        for (int i = 0; i < n; i++) {
            System.out.print("---");
            if (i == n - 1) {
                System.out.print("-\n");
            }
        }
    }

    //常数时间进行优化，用二进制位代替record数组
    //因为int是32位所以只支持32位的，如果要64位的改为long
    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1; //定义limit为n位二进制数全1
        //当最后 列 的限制也变为全1了说明，n皇后已经排好了
        return process2(limit, 0, 0, 0);
    }

    /**
     * @param limit        n位皇后限制（用来判断n位是否排满了）
     * @param colLim       候选皇后的 列限制，1的位置不能放皇后，0的位置可以
     * @param lefDiaLim    候选皇后的 左对角线限制，1的位置不能放皇后，0的位置可以
     * @param rightDiaLime 候选皇后的 右对角线限制，1的位置不能放皇后，0的位置可以
     * @return 多少种放那个法
     */
    public static int process2(int limit,
                               int colLim,
                               int lefDiaLim,
                               int rightDiaLime) {
        if (colLim == limit) {
            //列限制已经填满了，说明n位皇后都放好了
            return 1;
        }
        int pos = 0;
        int mostRightOne = 0;
        /*
         *得到候选皇后的位置：
         * colLim：      00010100
         * lefDiaLim：   00101000             //更新左对角线限制只需要左移一位
         * rightDiaLime：00001010             //更新右对角线限制只需要右移一位
         * ~(colLim | lefDiaLim | rightDiaLime)表示，此时取反后的1变成了可以放皇后的位置
         *符号位的(1111)  11000001
         * limit &的作用是截去 取反后填充的（1111）
         */
        //此时pos二进制表示的1就是可以存放的候选皇后的位置
        pos = limit & (~(colLim | lefDiaLim | rightDiaLime));
        //依次取出pos中最右侧的1
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            //把pos最右侧的1放上皇后，去寻找放好这个皇后的拜访的可能性
            res += process2(limit, colLim | mostRightOne,
                    (lefDiaLim | mostRightOne) << 1,
                    (rightDiaLime | mostRightOne) >>> 1);
            //更新取走后剩下的1
            pos = pos - mostRightOne;
        }
        return res;
    }

    public static void main(String[] args) {

        System.out.println(nmu1(10));
        System.out.println(num2(10));
    }
}
