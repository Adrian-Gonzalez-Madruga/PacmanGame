package newpacclass;

import DLibX.DConsole;
import java.awt.Color;
import java.awt.Font;

public class Field { // create the playing field returning values and drawing the maze

    private static String TimesNewRoman;

    private DConsole dc;
    private Pac p;
    private Ghost[] g;
    private Dot d;
    private int map[][] = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
    {2, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 2},
    {2, 5, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 5, 2},
    {2, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 2},
    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
    {2, 0, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 0, 2},
    {2, 0, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 0, 2},
    {2, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 2},
    {2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2},
    {1, 1, 1, 1, 1, 2, 0, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 0, 2, 1, 1, 1, 1, 1},
    {1, 1, 1, 1, 1, 2, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 2, 1, 1, 1, 1, 1},
    {1, 1, 1, 1, 1, 2, 0, 2, 2, 0, 2, 2, 2, 6, 6, 2, 2, 2, 0, 2, 2, 0, 2, 1, 1, 1, 1, 1},
    {2, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 1, 2, 1, 1, 2, 1, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 2},
    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 2, 1, 1, 2, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
    {2, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 1, 2, 2, 2, 2, 1, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 2},
    {1, 1, 1, 1, 1, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 1, 1, 1, 1, 1},
    {1, 1, 1, 1, 1, 2, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 2, 1, 1, 1, 1, 1},
    {1, 1, 1, 1, 1, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 1, 1, 1, 1, 1},
    {2, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 2},
    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
    {2, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 2},
    {2, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 2},
    {2, 5, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 5, 2},
    {2, 2, 2, 0, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 0, 2, 2, 2},
    {2, 2, 2, 0, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 0, 2, 2, 2},
    {2, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 2},
    {2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2},
    {2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2},
    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    private long score = 0;

    public void StartField(DConsole dc, Pac p, Dot d, Ghost[] g) {
        this.dc = dc;
        this.p = p;
        this.d = d;
        this.g = g;
    }

    public void Reset() {
        for (int c = 0; c <= 32; c++) {     //draw game
            for (int v = 0; v <= 27; v++) {
                if (map[c][v] == -1) {
                    map[c][v] = 0;
                } else if (map[c][v] == -2) {
                    map[c][v] = 5;
                }
            }
        }
    }

    public void draw() { //draw the dots, maze
        this.dc.setPaint(Color.BLACK);
        this.dc.fillRect(0, 0, 1000, 1000);
        this.dc.drawImage("png/bg.png", 0, 16);
        for (int c = 0; c <= 32; c++) {     //draw game
            for (int v = 0; v <= 27; v++) {
                if (map[c][v] == 0) {
                    this.dc.setPaint(Color.BLACK);
                    this.dc.fillRect(v * 16, c * 16, 16, 16);
                    this.dc.drawImage("png/smalldot.png", (v * 16) + 4, (c * 16) + 4);
                } else if (map[c][v] == 1 || map[c][v] == -1 || map[c][v] == -2) {
                    this.dc.setPaint(Color.BLACK);
                    this.dc.fillRect(v * 16, c * 16, 16, 16);
                } else if (map[c][v] == 5) {
                    this.dc.setPaint(Color.BLACK);
                    this.dc.fillRect(v * 16, c * 16, 16, 16);
                    this.dc.drawImage("png/bigdot.png", (v * 16) + 4, (c * 16) + 4);
                }
            }
        }
    }

    public void scorePlus(int num) {
        this.score += num;
        if (score % 10000 == 0) {
            p.EditLives(1);
        }
    }

    public void score() {
        dc.setPaint(Color.BLACK);
        dc.fillRect(0, 528, 100, 100);
        dc.setFont(new Font(TimesNewRoman, Font.ITALIC, 30));
        dc.setPaint(Color.WHITE);
        dc.drawString("SCORE:  " + this.score, 30, 502);
    }

    public long getScore() {
        return score;
    }

    public int getV(int x, int y) {// get the value of a location on the grid
        return this.map[y][x];
    }

    public void setV(int x, int y, int value) { //set a value at the specified location
        this.map[y][x] = value;
    }
}
