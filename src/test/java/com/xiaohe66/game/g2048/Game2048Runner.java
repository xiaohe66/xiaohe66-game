package com.xiaohe66.game.g2048;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * @author xiaohe
 * @time 2020.07.13 11:11
 */
public class Game2048Runner {

    private static final Logger log = LoggerFactory.getLogger(Game2048Runner.class);

    private static int line = 4;

    private static Game2048 game2048 = new Game2048();

    private static void init() {
        int[][] gameArr = new int[line][line];
        gameArr[0][1] = 2;
//        gameArr[0][2] = 4;
//        gameArr[0][3] = 8;

//        gameArr[1][0] = 16;
//        gameArr[1][1] = 32;
//        gameArr[1][2] = 64;
//        gameArr[1][3] = 128;

        gameArr[2][0] = 4;
        gameArr[2][1] = 8;
        gameArr[2][2] = 64;
        gameArr[2][3] = 512;

        gameArr[3][0] = 128;
        gameArr[3][1] = 512;
        gameArr[3][2] = 1024;
        gameArr[3][3] = 2048;

        game2048.init(gameArr);
        game2048.outArr();
    }

    public static void main(String[] args) {

        init();
        Scanner scanner = new Scanner(System.in);
        while(true){
            String next = scanner.next();
            if("e".equalsIgnoreCase(next)){
                break;
            }else if("r".equalsIgnoreCase(next)){
                game2048.right();
            }else if("l".equalsIgnoreCase(next)){
                game2048.left();
            }else if("u".equalsIgnoreCase(next)){
                game2048.up();
            }else if("d".equalsIgnoreCase(next)){
                game2048.down();
            }
            game2048.outArr();
            log.info("remain : {}",game2048.getRemain());
        }
    }
}
