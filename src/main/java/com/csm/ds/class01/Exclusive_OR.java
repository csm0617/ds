package com.csm.ds.class01;

/**
 * 异或的性质和应用
 */
public class Exclusive_OR {
    /*
        异或性质：
            1） 0 ^ N == N     N ^ N ==0
            2)  交换律  结合律  (结论来自：无进位相加)
                a^b == b^a    (a^b)^c==a^(b^c)
    */
    public static void swap(int[] arr, int i, int j) {

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];  //arr[j] = (arr[i]^arr[j])^arr[j] == arr[i]^(arr[j]^arr[j]) == arr[i]
        arr[i] = arr[j] ^ arr[i];  //arr[i] = arr[i]^(arr[i]^arr[j]) == (arr[i]^arr[i])^arr[j] == arr[j]
    }

    /**
     * 题目1：一个数组中有一个数出现了 奇数次 ，其他数都出现了偶数次 ，求这个数
     * 要求：时间复杂度O(N)，空间复杂度O(1)
     */
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
//        for (int i = 0; i < arr.length; i++) {
//            eor ^= arr[i];
//        }
        for (int cur : arr) {
            eor ^= cur;
        }
        System.out.println(eor);
    }

    /**
     * 题目2：一个数组中有两个数出现了 奇数次 ，其他数都出现了偶数次 ，求这两个数
     * 要求：时间复杂度O(N)
     */
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int cur : arr) {
            eor ^= cur;
        }

        //假设这两个数是a和b,
        //eor = a ^ b
        //eor != 0 所以eor在二进制表示上至少有一个1,这个位置的1要么a有，要么b有,可以区分a,b
        int rightOne = eor & (~eor + 1);//提取最右边的1
        /*
            提取一个数最右边的1： a & (~a + 1)
            假设          eor: 1010111100
                        ~eor: 0101000011
                      ~eor+1: 0101000100
            eor & (~eor + 1): 0000000100
         */
        int onlyOne = 0; //eor'
        //
        for (int cur : arr) {
            if ((cur & rightOne) == 0) {
                onlyOne ^= cur;//将数组里的有这个1的全部^就可以得到a,b中的一个
            }
        }
        System.out.println(onlyOne + "\t" + (onlyOne ^ eor));
    }


    public static void main(String[] args) {
        int[] arr1 = {2, 2, 3, 3, 4, 4, 9, 9, 9, 8, 8, 6, 6, 4, 7, 4, 7};
        printOddTimesNum1(arr1);
        int[] arr2 = {2, 3, 2, 3, 5, 6, 6, 5, 7, 7, 7, 9, 9, 9, 8, 4, 8,4};
        printOddTimesNum2(arr2);
    }

}
