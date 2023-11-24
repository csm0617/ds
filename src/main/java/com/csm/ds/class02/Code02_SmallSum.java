package com.csm.ds.class02;

import java.util.Arrays;

/**
 * 归并排序的拓展1：小和问题
 * 在一个数组中，每一个数左边比当前数小的累加起来，叫做这个数组的小和，求这个数组的小和
 */
public class Code02_SmallSum {
    /*
     * 例子:[1,3,4,2,5] 1左边比1小的数没有；3左边比1小的数，1；4左边比4小的数，1，3；2左边比2小的数，1；5左边比5小的数1，3，4，2；
     * 所以小和 1+1+3+1+2+1+3+4+2=16
     */

    /*
        思路：归并排序,逆向想，求小和可以转化成
             求右边的数有多少个比 当前数 的大，那么就有多少个当前数参与累加
             1的右边有4个数比1大，所以有4个1参与累加 4
             3的右边有2个数比3大，所以有2个3参与累加 6
             4的右边有1个数比4大，所以有1个4参与累加 4
             2的右边有1个数比2大，所以有1个2参与累加 2
             所以最小和 4+6+4+2=16
             当在递归归并比较的时候才进行累加
     */
    public static int smallSum(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        return process(nums, 0, nums.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) {//只有一个元素了，结束递归
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid)
                + process(arr, mid + 1, R)
                + merge(arr, L, mid, R);
    }

    public static int merge(int[] arr, int L, int M, int R) {
        int res = 0;
        int[] help = new int[R - L + 1];//辅助数组
        int i = 0;//数组赋值的辅助指针
        //创建两个变量p1,p2指向数组两块区域的开始部分
        int p1 = L;
        int p2 = M + 1;
        //只要p1和p2没出区域的右边界
        while (p1 <= M && p2 <= R) {
            res += arr[p1] < arr[p2] ? (R - p2 + 1) * arr[p1] : 0;//求小和
            //!!!这里复制到辅助数组时和归并排序细节不一样，当相等的时候复制右边的值到数组中，让p2++,主要是为计算有多少个数(R - p2 + 1)比arr[p1]大
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];//p1和p2指向的位置比较谁更小，小的就赋值到新数组中，并且指针++
        }
        //其中一个数组越界了，就把另一个数组中剩余的元素复制到辅助数组中
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        //最后把辅助数组的值覆盖到原数组中
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];//这一步很关键
        }
        System.out.println(Arrays.toString(help));
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 4, 2, 5};
        int[] arr2 = {2, 8, 1, 3, 7, 9, 6};
        System.out.println(smallSum(arr1));
        System.out.println();
        System.out.println(smallSum(arr2));
    }
}
