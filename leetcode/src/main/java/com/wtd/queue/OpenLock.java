package com.wtd.queue;

import java.util.*;

public class OpenLock {

    public static void main(String[] args) {
        String[] deadends= {"8887","8889","8878","8898","8788","8988","7888","9888"};
        String target= "8888";
        int count = new OpenLock().openLock(deadends,target);
        System.out.println(count);
    }

    /*public int openLock(String[] deadends, String target) {
        int count = 0;
        Queue<String> queue = new LinkedList<>();//下一步所包含的所有可能
        String root = "0000";//其实节点
        queue.offer(root);
        List<String> visits = new ArrayList<>();
        visits.add(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0;i < size;i++){
                String current = queue.poll();

                List<String> neibors = findNeibors(current);
                for(String neibor:neibors){
                    //如果是目标值，就马上返回
                    if(neibor.equals(target)){
                        count++;
                        return count;
                    }
                    if(findUsed(visits,neibor)){
                        continue;
                    }
                    if(!findUsed(Arrays.asList(deadends),neibor)){
                        queue.offer(neibor);//将当前临近值放到队列中，用于下次寻找
                        visits.add(neibor);
                    }
                }
            }
            count++;
        }
        return -1;
    }*/
    /*
     * 双向搜索，效率快
     */
    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();
        String init = "0000";
        if (dead.contains(init) || dead.contains(target)) {
            return -1;
        }

        if (target.equals(init)) {
            return 0;
        }

        Set<String> set1 = new HashSet<>();
        set1.add(init);
        Set<String> set2 = new HashSet<>();
        set2.add(target);
        int count=0;
        while (!set1.isEmpty() && !set2.isEmpty()) {
            //将最小的集合遍历
            if (set1.size() > set2.size()) {
                Set<String> temp = set1;
                set1 = set2;
                set2 = temp;
            }
            Set<String> set3 = new HashSet<>();
            for (String curLock : set1) {
                List<String> neibors=findNeibors(curLock);
                for (String nextLock : neibors) {
                    //如果set2中包含了这个Lock,则表示初试和目标在途中相遇到了
                    if(set2.contains(nextLock)) {
                        return count+1;
                    }
                    if (!dead.contains(nextLock) && !visited.contains(nextLock)) {
                        visited.add(nextLock);
                        set3.add(nextLock);
                    }
                }
            }
            count++;
            set1 = set3;
        }
        return -1;
    }

    /**
     * 获取当前数值下一步有可能的所有数值
     * @param current 当前值
     * @return
     */
    public List<String> findNeibors(String current){
        List<String> neibors = new ArrayList<>();
        for(int i = 0;i < current.length();i++){
            //当前位+1
            String temp = current;
            char[] charArray = temp.toCharArray();
            if(charArray[i] == '9'){
                charArray[i] = '0';
            }else{
                charArray[i] = (char) (charArray[i] + 1);
            }
            temp = new String(charArray);
            neibors.add(temp);
            //当前位-1
            temp = current;
            charArray = temp.toCharArray();
            if(charArray[i] == '0'){
                charArray[i] = '9';
            }else{
                charArray[i] = (char) (charArray[i] - 1);
            }
            temp = new String(charArray);
            neibors.add(temp);
        }
        return neibors;
    }

    //判断当前数字是否在集合范围内
    public boolean findUsed(List<String> list, String code){
        for(String c:list){
            if(c.equals(code)){
                return true;
            }
        }
        return false;
    }

    public void printList(List<String> list){
        String result = "";
        for(String s:list){
            result += s + ",";
        }

        System.out.println(result);
    }
}
