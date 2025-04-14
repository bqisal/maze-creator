public class Pathfinder {
    public static boolean solveMaze(char[][] maze, int x, int y) {
        if (x < 0 || y < 0 || x >= maze.length || y >= maze[0].length)
            return false;

        if (maze[x][y] == 'E')
            return true;

        if (maze[x][y] != ' ' && maze[x][y] != 'S')
            return false;

        if (maze[x][y] != 'S')
            maze[x][y] = '.';

        if (solveMaze(maze, x + 1, y) || solveMaze(maze, x - 1, y)
                || solveMaze(maze, x, y + 1) || solveMaze(maze, x, y - 1))
            return true;

        if (maze[x][y] != 'S')
            maze[x][y] = ' ';

        return false;
    }
}
