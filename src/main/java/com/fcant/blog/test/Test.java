package com.fcant.blog.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Test
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 下午 15:19 2022/7/9/0009
 */
public class Test {
    public static void main(String[] args) {
        calculateCost();
        vote();
    }

    // 有20个候选人参加学生会主席的竞选，最后将根据学生的投票情况决定学生会主席的获胜人选。
    // 编写一个程序，完成选票的统计工作，并显示最终的获胜者。
    public static void vote() {
        HashMap<String, Integer> box = new HashMap<>();
        boolean flag = true;
        while (flag) {
            System.out.println("请输入您预期的候选人编号(输入q退出投票)：");
            Scanner scanner = new Scanner(System.in);
            String peopleNum = scanner.next();
            if (peopleNum.equals("q")) {
                flag = false;
            }else {
                if (box.containsKey(peopleNum)) {
                    box.put(peopleNum, box.get(peopleNum) + 1);
                } else {
                    box.put(peopleNum, 1);
                }
                System.out.println("您已成功投票");
            }
        }
        System.out.println("投票结束，开始计算最大得票候选人");
        Integer max = 0;
        String maxNum = "";
        for (Map.Entry<String, Integer> next : box.entrySet()) {
            if (next.getValue() > max) {
                max = next.getValue();
                maxNum = next.getKey();
            }
        }
        System.out.println("得票最大的候选人编号是：" + maxNum);
    }

    // 为了合理利用水资源，避免浪费，水务部门规定，每个家庭每月的人均使用量在6立方米以内
    // （含6立方），则每个立方水费按照1.5元/立方计算，若人均使用量超过6立方但不足12立方，
    // 则超出的部分的单价将上浮100%，若人均使用量超出12立方以上，则超出的部分的单价将上浮200%。
    // 写一个完整的java应用程序，输入家庭人数和某月的用水量，输出该家庭该月应缴的水费。

    public static void calculateCost() {
        System.out.println("请输入家庭人数：");
        Scanner scanner = new Scanner(System.in);
        String number = scanner.next();
        int peopleNum = Integer.parseInt(number);
        System.out.println("请输当月的用水量：");
        scanner = new Scanner(System.in);
        String size = scanner.next();
        int usedSize = Integer.parseInt(size);
        int avg = usedSize / peopleNum;
        System.out.println("您当月该缴纳的电费为：");
        if (avg <= 6) {
            System.out.println(usedSize * 1.5);
        } else if (avg <= 12) {
            System.out.println(6*peopleNum * 1.5 + (usedSize-6*peopleNum) * 1.5 * 2);
        } else {
            System.out.println(12*peopleNum * 1.5 + (usedSize - 12*peopleNum) * 1.5 * 3);
        }
    }
}

