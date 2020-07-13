package com.xiaohe66.game.g2048;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @author xiaohe
 * @time 2020.07.13 09:40
 */
public class Game2048 {

    private static final Logger log = LoggerFactory.getLogger(Game2048.class);

    private static final int LINE = 4;

    private int[][] gameArr;
    private int[][] copyArr = new int[LINE][LINE];
    private int[] tmpArr = new int[LINE];
    /**
     * 剩余空间
     */
    private int remain;
    private Random random = new Random(System.currentTimeMillis());
    private int score;

    public void init() {
        gameArr = new int[LINE][LINE];
        remain = LINE * LINE;
        addItem();
    }

    public void init(int lv) {
        if (lv < 0 || lv > 3) {
            throw new IllegalArgumentException("lv不能小于0或大于3");
        }
        gameArr = new int[LINE][LINE];
        remain = LINE * LINE;
        int num = lv * 2048;
        gameArr[LINE - 1][LINE - 1] = num;
        remain -= 1;
        addItem();
    }

    public void init(int[][] gameArr) {
        this.gameArr = gameArr;
        remain = 0;
        for (int[] arr : gameArr) {
            for (int num : arr) {
                if (num == 0) {
                    remain++;
                }
            }
        }
    }

    public void outArr() {
        if (log.isDebugEnabled()) {
            log.debug("---------------------------------");
            for (int y = 0; y < LINE; y++) {
                log.debug("|\t{}\t|\t{}\t|\t{}\t|\t{}\t|", gameArr[0][y], gameArr[1][y], gameArr[2][y], gameArr[3][y]);
                log.debug("---------------------------------");
            }
        }
    }

    private int nextNumber() {
        return (random.nextInt(2) + 1) * 2;
    }

    private int nextPosition(int remain) {
        return random.nextInt(remain);
    }

    private void addItem() {
        if (remain == 0) {
            checkGameOver();
            return;
        }
        int pos = nextPosition(remain);
        for (int y = 0; y < LINE; y++) {
            for (int x = 0; x < LINE; x++) {

                if (gameArr[x][y] == 0) {
                    if (pos == 0) {
                        int num = nextNumber();
                        gameArr[x][y] = num;
                        remain--;
                        return;
                    }
                    pos--;
                }
            }
        }
    }

    private void checkGameOver() {
        // todo : impl
    }

    public boolean change(int[] tmpArr) {
        int sum;
        int index = 0;
        boolean isChange = false;

        for (int i = 0; i < LINE; i++) {
            if (tmpArr[i] != 0) {
                sum = tmpArr[i];

                for (int j = i + 1; j < LINE; j++) {

                    if (tmpArr[j] != 0) {
                        if (tmpArr[i] == tmpArr[j]) {
                            // 合并
                            sum += tmpArr[j];
                            tmpArr[j] = 0;
                            remain++;
                        }
                        break;
                    }
                }
                if (sum != tmpArr[index]) {
                    isChange = true;
                    tmpArr[index] = sum;
                    if (index != i) {
                        tmpArr[i] = 0;
                    }
                }
                index++;
            }
        }
        return isChange;
    }

    public synchronized int[][] up() {
        int change = 0;
        for (int x = 0; x < LINE; x++) {
            if (change(gameArr[x])) {
                change++;
            }
        }
        if (change > 0) {
            addItem();
        }
        return getArr();
    }

    public synchronized int[][] down() {
        int change = 0;
        for (int x = 0; x < LINE; x++) {
            tmpArr[3] = gameArr[x][0];
            tmpArr[2] = gameArr[x][1];
            tmpArr[1] = gameArr[x][2];
            tmpArr[0] = gameArr[x][3];
            if (change(tmpArr)) {
                change++;
                gameArr[x][3] = tmpArr[0];
                gameArr[x][2] = tmpArr[1];
                gameArr[x][1] = tmpArr[2];
                gameArr[x][0] = tmpArr[3];
            }
        }
        if (change > 0) {
            addItem();
        }
        return getArr();
    }

    public synchronized int[][] left() {
        int change = 0;
        for (int y = 0; y < LINE; y++) {
            tmpArr[0] = gameArr[0][y];
            tmpArr[1] = gameArr[1][y];
            tmpArr[2] = gameArr[2][y];
            tmpArr[3] = gameArr[3][y];
            if (change(tmpArr)) {
                change++;
            }
            gameArr[0][y] = tmpArr[0];
            gameArr[1][y] = tmpArr[1];
            gameArr[2][y] = tmpArr[2];
            gameArr[3][y] = tmpArr[3];
        }
        if (change > 0) {
            addItem();
        }
        return getArr();
    }

    public synchronized int[][] right() {
        int change = 0;
        for (int y = 0; y < LINE; y++) {
            tmpArr[0] = gameArr[3][y];
            tmpArr[1] = gameArr[2][y];
            tmpArr[2] = gameArr[1][y];
            tmpArr[3] = gameArr[0][y];
            if (change(tmpArr)) {
                change++;
            }
            gameArr[0][y] = tmpArr[3];
            gameArr[1][y] = tmpArr[2];
            gameArr[2][y] = tmpArr[1];
            gameArr[3][y] = tmpArr[0];
        }
        if (change > 0) {
            addItem();
        }
        return gameArr;
    }

    public int[][] getArr() {
        System.arraycopy(gameArr, 0, copyArr, 0, LINE);
        return copyArr;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }

}
