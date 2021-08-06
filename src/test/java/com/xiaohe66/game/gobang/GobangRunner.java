package com.xiaohe66.game.gobang;

import java.util.Scanner;

/**
 * @author xiaohe
 * @time 2020.07.13 18:20
 */
public class GobangRunner {


    public static void main(String[] args) {

        Gobang gobang = new Gobang();
        gobang.init();

//        gobang.down(7,7);
//        gobang.down(7,8);
//        gobang.down(6,7);
//        gobang.down(6,8);
//        gobang.down(5,7);
//        gobang.down(5,8);
//        gobang.down(4,7);
//        gobang.down(4,8);
        gobang.outArr();

        Scanner scanner = new Scanner(System.in);

        while(true){
            String next;
            try {
                next = scanner.next();
            } catch (Exception e) {
                continue;
            }

            if("1".equals(next)){
                return;
            }

            if(next.length() > 3){
                continue;
            }

            int oneInt = next.charAt(0) - 'a';
            String two = next.substring(1);
            int twoInt = Integer.parseInt(two)-1;

            try {
                gobang.down(oneInt,twoInt);
            } catch (Exception e) {
                System.out.println("输入有误");
                continue;
            }
            gobang.outArr();
            boolean isWin = gobang.checkFiveTogether(oneInt, twoInt);
            if(isWin){
                System.out.println("游戏结束");
                break;
            }

        }

    }
}
