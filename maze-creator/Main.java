public class Main {
    public static void main(String[] args) {
        int rows = 15, cols = 15;
        MazeGenerator generator = new MazeGenerator(rows, cols);
        char[][] maze = MazeGenerator.toCharMaze(generator.getMaze());

        System.out.println("Generated Maze:");
        printMaze(maze);

        int startX = 1, startY = 0;
        if (Pathfinder.solveMaze(maze, startX, startY)) {
            System.out.println("\nSolved Maze:");
            printMaze(maze);
        } else {
            System.out.println("No path found!");
        }
    }

    private static void printMaze(char[][] maze) {
        for (char[] row : maze) {
            for (char c : row)
                System.out.print(c + " ");
            System.out.println();
        }
    }
}
