/**
 * 八皇后问题
 *
 * @author boring
 * @date 2018/04/06
 */
public class Queen8 {

    /**
     * 合理方案数
     * 皇后数
     * rows保存第n行放置棋子的列数i(n,i属于[0,7])
     */
    private int num = 0;
    private final int QUEENS = 8;
    private int[] rows = new int[QUEENS];

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
    }

    public Queen8() {
        tryAddress(0);
    }

    /**
     * 输出棋盘
     */
    private void printChessBoard() {
        System.out.println("第" + num + "种方法：");
        for (int i = 0; i < QUEENS; ++i) {
            for (int j = 0; j < QUEENS; ++j) {
                if (j == rows[i]) {
                    System.out.print("○ ");
                } else {
                    System.out.print("× ");
                }
            }
            System.out.println();
        }
    }

    /**
     * 尝试棋子地址
     *
     * b=0, b<a
     * rows[a] == ros[b] → 第a行和第b行放置棋子的位置一样
     * rows[a] - rows[b] == a - b → 第a行和第b行放置棋子成反斜线\
     * rows[a] - rows[b] == b - a → 第a行和第b行防止棋子成斜线/
     *
     * @param thisRow
     */
    private void tryAddress(int thisRow) {
        //标记当前行中不合法的列的位置,boolean数组默认false,所以将不合法值设为true
        boolean[] cols = new boolean[QUEENS];
        for (int i = 0; i < thisRow; ++i) {
            //将与之前行中相同的列置为不合法
            cols[rows[i]] = true;
            int differ = thisRow-i;
            //将与之前行中成斜线的列置为不合法/
            if (rows[i] - differ >= 0) {
                cols[rows[i] - differ] = true;
            }
            //将与之前行中成反斜线的列置为不合法\
            if (rows[i] + differ < QUEENS) {
                cols[rows[i] + differ] = true;
            }
        }

        for (int j = 0; j < QUEENS; ++j) {
            //当前列不合法则跳过
            if (cols[j]) {
                continue;
            }
            //将该行的合法列设为j
            rows[thisRow] = j;
            //判断是否最后一行
            if (thisRow < QUEENS - 1) {
                tryAddress(thisRow + 1);
            } else {
                ++num;
                printChessBoard();
            }
        }
    }
}
