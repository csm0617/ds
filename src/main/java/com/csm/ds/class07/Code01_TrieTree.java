package com.csm.ds.class07;

public class Code01_TrieTree {
    public static class TrieNode {
        int pass;//有多少字符串的前缀包含当前节点的（字符串经过这个节点的次数）
        int end;//有多少个字符串以这个节点结尾
        public TrieNode[] nexts;//hashMap<Char,Node> todo 类型为hashmap的前缀树

        public TrieNode(int pass, int end, TrieNode[] nexts) {
            this.pass = pass;
            this.end = end;
            //nexts[0]==null 没有走向'a'的路
            //nexts[0]!=null 有走向'a'的路
            //...
            //nexts[25]!=null 有走向'z'的路
            this.nexts = new TrieNode[26];
        }

        public TrieNode() {
        }
    }

    public static class Trie {
        public static TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        /**
         * 前缀树加入一个单词
         *
         * @param word
         */
        public void insert(String word) {
            if (word == null) {
                return;
            }
            //把单词转换成字符数组
            char[] chs = word.toCharArray();
            //从根节点开始
            TrieNode node = root;
            node.pass++;
            int index;
            //遍历单词的字符数组
            for (int i = 0; i < chs.length; i++) {
                //计算索引位置
                index = chs[i] - 'a';
                //由字符对应走哪条路，没有这个字符的节点就创建
                if (node.nexts[index] == null) {
                    node.nexts[i] = new TrieNode();
                }
                //node来到下一个节点
                node = node.nexts[index];
                //经过节点的次数++
                node.pass++;
            }
            //单词结束了，最后一个字符节点end++，标记为一次字符串的结束
            node.end++;
        }

        public void delete(String word) {
            if (search(word) != 0) {//确定树中加入word，才删除
                char[] chs = word.toCharArray();
                TrieNode node = root;
                node.pass--;
                int index;
                for (int i = 0; i < chs.length; i++) {
                    index = chs[i] - 'a';
                    if (--node.nexts[index].pass == 0) {
                        node.nexts[index] = null;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }

        /**
         * 这个单词之前加入过几次
         *
         * @param word
         * @return
         */
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index;
            for (char ch : chs) {
                index = ch - 'a';
                if (node.nexts[index] == null) {//当查找 abc 但是 只加入了ab，就会来到null的情况，返回0
                    return 0;
                }
                node = node.nexts[index];//继续向前缀树向下查找
            }
            //最终node会来到最后一个字符的node上
            return node.end;
        }

        /**
         * 返回有多少个单词以pre作为前缀
         *
         * @param pre
         * @return
         */
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            TrieNode node = root;
            int index;
            for (char ch : chs) {
                index = ch - 'a';
                if (node.nexts[index] == null) {//当查找 abc 但是 只加入了ab，就会来到null的情况，返回0
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
    }

}
