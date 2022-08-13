import java.util.Arrays;

public class Soduko {
    public static final int GRID_SIZE = 9;

    private static boolean isNumberInRow(int[][] board, int row, int num) {
        for (int i = 0; i < GRID_SIZE; ++i) {
            if (board[row][i] == num)
                return true;
        }
        return false;
    }

    private static boolean isNumberInCol(int[][] board, int col, int num) {
        for (int i = 0; i < GRID_SIZE; ++i) {
            if (board[i][col] == num)
                return true;
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlacement(int[][] board, int col, int row, int num) {
        return !isNumberInRow(board, row, num) &&
                !isNumberInCol(board, col, num) &&
                !isNumberInBox(board, num, row, col);
    }

    private static boolean solve(int[][] board) {
        for (int i = 0; i < GRID_SIZE; ++i) {
            for (int j = 0; j < GRID_SIZE; ++j) {
                if (board[i][j] == 0) {
                    for (int number = 1; number <= GRID_SIZE; ++number) {
                        if (isValidPlacement(board, j, i, number)) {
                            board[i][j] = number;

                            if (solve(board)) {
                                return true;
                            } else {
                                board[i][j] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

        int[][] board = {
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };

        if (solve(board)) {
            for (int i = 0; i < GRID_SIZE; ++i) {
                System.out.println(Arrays.toString(board[i]));
            }
            System.exit(0);
        } else
            System.out.println("Unsolvable sudoku");
    }
}
