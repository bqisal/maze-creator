import java.util.*;

public class MazeGenerator {
    private static final int WALL = 1;
    private static final int PATH = 0;
    private final int rows, cols;
    private final int[][] maze;
    private final Random rand = new Random();

    public MazeGenerator(int rows, int cols) {
        this.rows = rows % 2 == 0 ? rows + 1 : rows;
        this.cols = cols % 2 == 0 ? cols + 1 : cols;
        this.maze = new int[this.rows][this.cols];
        generateMaze();
    }

    public int[][] getMaze() {
        return maze;
    }

    private void generateMaze() {
        for (int[] row : maze)
            Arrays.fill(row, WALL);

        carve(1, 1);

        maze[1][0] = PATH; // Start
        maze[rows - 2][cols - 1] = PATH; // Exit
    }

    private void carve(int r, int c) {
        int[] dr = { -2, 2, 0, 0 };
        int[] dc = { 0, 0, -2, 2 };
        Integer[] directions = { 0, 1, 2, 3 };
        Collections.shuffle(Arrays.asList(directions));

        for (int dir : directions) {
            int nr = r + dr[dir], nc = c + dc[dir];
            if (nr > 0 && nr < rows && nc > 0 && nc < cols && maze[nr][nc] == WALL) {
                maze[nr][nc] = PATH;
                maze[r + dr[dir] / 2][c + dc[dir] / 2] = PATH;
                carve(nr, nc);
            }
        }
    }

    public static char[][] toCharMaze(int[][] maze) {
        char[][] charMaze = new char[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                charMaze[i][j] = switch (maze[i][j]) {
                    case WALL -> '#';
                    case PATH -> ' ';
                    default -> '?';
                };
            }
        }
        charMaze[1][0] = 'S';
        charMaze[maze.length - 2][maze[0].length - 1] = 'E';
        return charMaze;
    }
}
