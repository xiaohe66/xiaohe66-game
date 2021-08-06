package com.xiaohe66.game.gobang;

/**
 * @author xiaohe
 * @time 2020.07.13 14:41
 */
public class Gobang {

    private static final int SIZE = 15;
    private byte[][] gameArr;
    private byte[][] copyArr;

    /**
     * 0 - 无
     * 1 - 黑
     * 2 - 白
     */
    private byte nextValue = 1;

    public void init() {
        gameArr = new byte[SIZE][SIZE];
        copyArr = new byte[SIZE][SIZE];
    }

    public void outArr() {
        System.out.println("------------------------------------------------");
        for (int y = 0; y < SIZE; y++) {
            System.out.print(y+1);
            System.out.print("\t");
            for (int x = 0; x < SIZE; x++) {
                System.out.print('|');
                if (gameArr[x][y] == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print(gameArr[x][y] == 1 ? "黑" : "白");
                }
            }
            System.out.println('|');
        }
        System.out.print("    |");
        for (int i = 0; i < SIZE; i++) {
            System.out.print((char)('a'+i));
            System.out.print(" |");
        }
        System.out.println();
    }

    public void pass(){
        next();
    }

    public void down(int x, int y) {
        checkXAndY(x, y);
        if (gameArr[x][y] != 0) {
            throw new IllegalArgumentException("目标点已存在");
        }

        gameArr[x][y] = nextValue;

        next();
    }

    private void next(){
        nextValue = (byte) (nextValue == 1 ? 2 : 1);
    }

    public boolean checkFiveTogether(int x, int y) {
        checkXAndY(x, y);
        if (gameArr[x][y] == 0) {
            throw new IllegalArgumentException("目标点不存在");
        }
        return checkFiveTogether(x, y, -1, 0) ||
                checkFiveTogether(x, y, -1, -1) ||
                checkFiveTogether(x, y, 0, -1) ||
                checkFiveTogether(x, y, 1, -1);
    }

    private boolean checkFiveTogether(int x, int y, int xDivisor, int yDivisor) {

        int qty = 1;

        int x1 = x;
        int y1 = y;

        while (true) {
            x1 += xDivisor;
            y1 += yDivisor;

            // todo : 处理数组越界
            if (gameArr[x1][y1] == 0 || gameArr[x1][y1] != gameArr[x][y]) {
                break;
            }
            qty += 1;
        }

        x1 = x;
        y1 = y;
        while (true) {
            x1 -= xDivisor;
            y1 -= yDivisor;

            if (gameArr[x1][y1] == 0 || gameArr[x1][y1] != gameArr[x][y]) {
                break;
            }
            qty += 1;
        }

        return qty >= 5;
    }

    private byte[][] getGameArr(){
        System.arraycopy(gameArr,0,copyArr,0,SIZE);
        return copyArr;
    }

    private void checkXAndY(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            throw new IllegalArgumentException("x或y取值错误");
        }
    }

}
