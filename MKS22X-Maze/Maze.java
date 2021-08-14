import java.util.*;
import java.io.*;

public class Maze {
    public static void main(String[] args) {
        String filename = "data2.dat";
        try {
            Maze f;
            f = new Maze(filename);
            f.setAnimate(true);
            System.out.println(f.solve());
        } catch (Exception e) {
            System.out.println("Invalid filename: " + filename);
        }
    }

    private char[][] maze;
    // Default: False
    private boolean animate;

    /*
     * Constructor loads a maze text file, and sets animate to false by default. 1.
     * The file contains a rectangular ascii maze, made with the following 4
     * characters: '#' - Walls - locations that cannot be moved onto
     * 
     * ' ' - Empty Space - locations that can be moved onto
     * 
     * 'E' - the location of the goal (exactly 1 per file)
     * 
     * 'S' - the location of the start(exactly 1 per file)
     * 
     * 2. The maze has a border of '#' around the edges. So you don't have to check
     * for out of bounds!
     * 
     * 3. When the file is not found OR the file is invalid (not exactly 1 E and 1
     * S) then: throw a FileNotFoundException or IllegalStateException
     */
    public Maze(String filename) throws FileNotFoundException {
        animate = false;
        File f = new File(filename);
        Scanner s = new Scanner(f);
        StringBuilder sb = new StringBuilder();
        int numLines = 0;
        int lenLines = 0;
        while (s.hasNextLine()) {
            String line = s.nextLine();
            sb.append(line + "\n");
            lenLines = line.length();
            numLines++;
        }
        s.close();
        maze = new char[numLines][lenLines];
        int curLine = 0;
        for (String line : sb.toString().split("\n")) {
            for (int i = 0; i < line.length(); i++) {
                maze[curLine][i] = line.charAt(i);
            }
            curLine++;
        }
    }

    private void wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
    }

    public void setAnimate(boolean b) {
        animate = b;
    }

    public void clearTerminal() {
        // erase terminal, go to top left of screen.
        System.out.println("\033[2J\033[1;1H");
    }

    /*
     * Wrapper Solve Function returns the helper function
     * 
     * Note the helper function has the same name, but different parameters. Since
     * the constructor exits when the file is not found or is missing an E or S, we
     * can assume it exists.
     * 
     */
    public int solve() {
        for(int y = 0; y < maze.length;y++){
            for(int x = 0; x < maze[y].length;x++){
                if(maze[y][x]=='S'){
                    maze[y][x]=' ';
                    return solve(x,y,0);
                }
            }
        }
        return -1;
    }

    /*
     * Recursive Solve function:
     * 
     * A solved maze has a path marked with '@' from S to E.
     * 
     * Returns the number of @ symbols from S to E when the maze is solved, Returns
     * -1 when the maze has no solution.
     * 
     * 
     * Postcondition:
     * 
     * The S is replaced with '@' but the 'E' is not.
     * 
     * All visited spots that were not part of the solution are changed to '.'
     * 
     * All visited spots that are part of the solution are changed to '@'
     */
    private int solve(int x, int y,int len) {
        if (animate) {
            clearTerminal();
            System.out.println(this);
            wait(20);
        }
        if(maze[y][x]!='E'){
            maze[y][x]='@';
            if(maze[y-1][x]==' '||maze[y-1][x]=='E'){
                int z = solve(x,y-1,len+1);
                if(z!=-1){
                    return z;
                }
            }if(maze[y][x+1]==' '||maze[y][x+1]=='E'){
                int z = solve(x+1,y,len+1);
                if(z!=-1){
                    return z;
                }
            }if(maze[y+1][x]==' '||maze[y+1][x]=='E'){
                int z = solve(x,y+1,len+1);
                if(z!=-1){
                    return z;
                }
            }if(maze[y][x-1]==' '||maze[y][x-1]=='E'){
                int z = solve(x-1,y,len+1);
                if(z!=-1){
                    return z;
                }
            }
        }else{
            return len;
        }
        
        maze[y][x]='.';
        return -1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[y].length; x++) {
                sb.append(maze[y][x]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}