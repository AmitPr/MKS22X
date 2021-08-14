import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class USACO {
    public static void main(String[] args) {
        try {
            System.out.println(bronze("TestCases/makelake.5.in"));
            System.out.println(silver("TestCases/ctravel.5.in"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int bronze(String filename) {
        try {
            File f = new File(filename);
            Scanner s = new Scanner(f);
            String init = s.nextLine();
            String[] args = init.split(" ");
            if (args.length != 4) {
                s.close();
                throw new IllegalArgumentException();
            }

            int r, c, e, n = 0;
            r = Integer.parseInt(args[0]);
            c = Integer.parseInt(args[1]);
            e = Integer.parseInt(args[2]);
            n = Integer.parseInt(args[3]);
            int[][] pasture = new int[r][c];
            for (int i = 0; i < r; i++) {
                String line = s.nextLine();
                int j = 0;
                for (String square : line.split(" ")) {
                    int val = Integer.parseInt(square);
                    pasture[i][j] = val;
                    j++;
                }
            }
            int[][] instructions = new int[n][3];
            for (int i = 0; i < n; i++) {
                String line = s.nextLine();
                int j = 0;
                for (String val : line.split(" ")) {
                    int x = Integer.parseInt(val);
                    instructions[i][j] = x;
                    j++;
                }
            }
            for (int i = 0; i < n; i++) {
                int r_s = instructions[i][0];
                int c_s = instructions[i][1];
                int d_s = instructions[i][2];
                int max = 0;
                for (int y = r_s - 1; y < r_s + 2; y++) {
                    for (int x = c_s - 1; x < c_s + 2; x++) {
                        if (pasture[y][x] > max) {
                            max = pasture[y][x];
                        }
                    }
                }
                max -= d_s;
                for (int y = r_s - 1; y < r_s + 2; y++) {
                    for (int x = c_s - 1; x < c_s + 2; x++) {
                        pasture[y][x] = pasture[y][x] > max ? max : pasture[y][x];
                    }
                }
            }
            int total = 0;
            for (int y = 0; y < r; y++) {
                for (int x = 0; x < c; x++) {
                    if (pasture[y][x] < e) {
                        pasture[y][x] = e - pasture[y][x];
                        total += pasture[y][x];
                    } else {
                        pasture[y][x] = 0;
                    }
                }
            }
            s.close();
            return total * 72 * 72;
        } catch (IOException e) {
            return -1;
        }
    }

    public static String arrToString(int[][] arr) {
        int spacer = 5;
        String s = "";
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                int x = arr[i][j];
                int len = (x + "").length();
                for (int l = len; l < spacer; l++) {
                    s += " ";
                }
                s += x;
            }
            s += "\n";
        }
        return s;
    }

    private static char[][] pasture = null;

    public static int silver(String filename) {
        try {
            File f = new File(filename);
            Scanner s = new Scanner(f);
            String init = s.nextLine();
            String[] args = init.split(" ");
            if (args.length != 3) {
                s.close();
                throw new IllegalArgumentException();
            }
            int n, m, t = 0;
            n = Integer.parseInt(args[0]);
            m = Integer.parseInt(args[1]);
            t = Integer.parseInt(args[2]);
            pasture = new char[n][m];
            int[][] possible = new int[n][m];
            for (int i = 0; i < n; i++) {
                String line = s.nextLine();
                int j = 0;
                for (int c = 0; c < line.length(); c++) {
                    char val = line.charAt(c);
                    pasture[i][j] = val;
                    j++;
                }
            }
            int r1, c1, r2, c2 = 0;
            args = s.nextLine().split(" ");
            if (args.length != 4) {
                s.close();
                throw new IllegalArgumentException();
            }
            r1 = Integer.parseInt(args[0]) - 1;
            c1 = Integer.parseInt(args[1]) - 1;
            r2 = Integer.parseInt(args[2]) - 1;
            c2 = Integer.parseInt(args[3]) - 1;
            possible[r1][c1] = 1;
            int[][] temp = new int[n][m];
            for (int p = 0; p < t; p++) {
                for (int row = 0; row < n; row++) {
                    for (int col = 0; col < m; col++) {
                        if (row < possible.length - 1 && possible[row + 1][col] > 0 && pasture[row + 1][col] == '.') {
                            temp[row][col] += possible[row + 1][col];
                        }
                        if (row > 0 && possible[row - 1][col] > 0 && pasture[row - 1][col] == '.') {
                            temp[row][col] += possible[row - 1][col];
                        }
                        if (col < possible[0].length - 1 && possible[row][col + 1] > 0
                                && pasture[row][col + 1] == '.') {
                            temp[row][col] += possible[row][col + 1];
                        }
                        if (col > 0 && possible[row][col - 1] > 0 && pasture[row][col - 1] == '.') {
                            temp[row][col] += possible[row][col - 1];
                        }
                    }
                }
                possible = temp;
                temp = new int[n][m];
            }
            s.close();
            int answer = possible[r2][c2];
            return answer;
        } catch (IOException e) {
            return -1;
        }
    }
}