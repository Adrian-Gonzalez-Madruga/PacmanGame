package newpacclass;

import DLibX.DConsole;
import java.awt.Color;

public class Pac {

    private DConsole dc;
    private Field f;
    private Ghost[] g;
    private Dot d;
    private int change = 0;
    private int x = 13;
    private int y = 18;
    private int lives = 2000;
    private int[][][] p1 = {{{20, 20, 40 + 90, 320 + 90}, {20, 20, 40 + 270, 320 + 270}, {20, 20, 40 + 180, 320 + 180}, {20, 20, 40, 320}},
    {{20, 20, 20 + 90, 340 + 90}, {20, 20, 20 + 270, 340 + 270}, {20, 20, 20 + 180, 340 + 180}, {20, 20, 20, 340}}}; // [open/close][direction][things to draw]
    private char[] key = {'w', 's', 'a', 'd'};
    private int[] moveX = {0, 0, -1, 1};
    private int[] moveY = {-1, 1, 0, 0};
    private int direction = 3; //direction
    boolean move = false;


    public void StartPac(DConsole dc, Field f, Dot d, Ghost g[]) {
        this.dc = dc;
        this.f = f;
        this.d = d;
        this.g = g;
    }

    public void Reset() {
        this.change = 0;
        this.move = false;
        this.y = 18;
        this.x = 13;
        this.direction = 3;
    }

    public void draw() { //draw pacman
        if (d.powerUp()) {
            dc.setPaint(new Color(0, 204, 204, d.getTimer()));
        } else {
            dc.setPaint(Color.YELLOW);
        }
        /*if (this.move) {
         this.dc.fillArc(((this.x) * 16), ((this.y) * 16), p1[(this.change % 4) / 2][this.direction][0], p1[(this.change % 4) / 2][this.direction][1], p1[(this.change % 4) / 2][this.direction][2], p1[(this.change % 4) / 2][this.direction][3]);
         } else {
         this.dc.fillArc(((this.x) * 16), ((this.y) * 16), p1[0][this.direction][0], p1[0][this.direction][1], p1[0][this.direction][2], p1[0][this.direction][3]);
         }*/
        dc.fillEllipse((this.x) * 16, (this.y) * 16, p1[(this.change % 4) / 2][this.direction][0], p1[(this.change % 4) / 2][this.direction][1]);
        this.change++;
    }

    public void move() {
        for (int q = 0; q < 4; q++) { //decide which way i'm traveling
            if (f.getV(this.x + moveX[q], this.y + moveY[q]) != 2 && f.getV(this.x + moveX[q], this.y + moveY[q]) != 6 && dc.isKeyPressed(this.key[q])) {
                this.direction = q;
            }
        }
        /* if (f.getV(this.x + moveX[1], this.y + moveY[1]) != 2 && f.getV(this.x + moveX[1], this.y + moveY[1]) != 6 && j.analogVertical(0) > 70) {
         direction = 1;
         } else if (f.getV(this.x + moveX[0], this.y + moveY[0]) != 2 && f.getV(this.x + moveX[0], this.y + moveY[0]) != 6 && j.analogVertical(0) < 30) {
         direction = 0;
         }
         if (f.getV(this.x + moveX[3], this.y + moveY[3]) != 2 && f.getV(this.x + moveX[3], this.y + moveY[3]) != 6 && j.analogHorizontal(0) > 70) {
         direction = 3;
         } else if (f.getV(this.x + moveX[2], this.y + moveY[2]) != 2 && f.getV(this.x + moveX[2], this.y + moveY[2]) != 6 && j.analogHorizontal(0) < 30) {
         direction = 2;
         }*/
         if (f.getV(this.x + this.moveX[this.direction], this.y + this.moveY[this.direction]) == 3) {//teleports
         this.x = 1;
         }
         if (f.getV(this.x + this.moveX[this.direction], this.y + this.moveY[this.direction]) == 4) {//teleports
         this.x = 26;
         }
        // \/if im not going to run into a wall move else stop moving
        if (f.getV(this.x + this.moveX[this.direction], this.y + this.moveY[this.direction]) != 2 && f.getV(this.x + this.moveX[this.direction], this.y + this.moveY[this.direction]) != 6 && this.change % 3 == 2) {
            this.move = true;
            this.x += this.moveX[this.direction];
            this.y += this.moveY[this.direction];
        } else {
            this.move = false;
        }
    }

    public void Lives() {
        for (int q = 1; q <= this.lives; q++) {
            dc.setPaint(Color.YELLOW);
            dc.fillEllipse((q * 16) + 320, 524, p1[(this.change % 4) / 2][3][0] / 2, p1[(this.change % 4) / 2][3][1] / 2);
            //this.dc.fillArc((q * 16) + 320, 524, p1[(this.change % 4) / 2][3][0] / 2, p1[(this.change % 4) / 2][3][1] / 2, p1[(this.change % 4) / 2][3][2], p1[(this.change % 4) / 2][3][3]);
        }
        if (lives == -1) {
            System.out.println(f.getScore());
            System.exit(0);
        }
    }

    public int getLives() {
        return this.lives;
    }

    public void EditLives(int life) {
        this.lives += life;
    }

    public int getX() { //return pacmans x
        return this.x;
    }

    public int getY() { //return pacmans y
        return this.y;
    }
}
