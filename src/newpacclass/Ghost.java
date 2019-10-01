package newpacclass;

import DLibX.DConsole;
import java.awt.Color;

public class Ghost { //manage drawing the ghost and ai as an array 

    private DConsole dc;
    private Field f;
    private Pac p;
    private Dot d;
    private int change = 0;
    // private int multiply = 2;                f.scorePlus(100 * this.multiply);                this.multiply *= 2;
    private int x = (int) (Math.random() * 2) + 12;
    private int y = (int) (Math.random() * 2) + 13;
    private int moveX[] = {0, 0, -1, 1};
    private int moveY[] = {-1, 1, 0, 0};
    private int direction = 3;
    private boolean move = true;
    private boolean die = false;
    private boolean killing = false;
    private Color shade = new Color((int) (Math.random() * 255) + 0, (int) (Math.random() * 255) + 0, (int) (Math.random() * 255) + 0);
    private String[][] player = {{"png/blue_up.png", "png/blue_down.png", "png/blue_left.png", "png/blue_right.png"},
    {"png/pink_up.png", "png/pink_down.png", "png/pink_left.png", "png/pink_right.png"},
    {"png/red_up.png", "png/red_down.png", "png/red_left.png", "png/red_right.png"},
    {"png/yellow_up.png", "png/yellow_down.png", "png/yellow_left.png", "png/yellow_right.png"}};
    private String[] playerN = {"png/ghost_up.png", "png/ghost_down.png", "png/ghost_left.png", "png/ghost_right.png"};

    public void StartGhost(DConsole dc, Field f, Pac p, Dot d) {
        this.dc = dc;
        this.f = f;
        this.p = p;
        this.d = d;
    }

    public void Reset() {
        this.change = 0;
        this.die = false;
        this.move = true;
        this.killing = false;
        this.direction = 3;
        this.x = (int) (Math.random() * 2) + 12;
        this.y = (int) (Math.random() * 2) + 13;
    }

    public void draw(int num, boolean classic) { //draw the ghost
        if (!d.powerUp() && !this.die) {
            if (classic) { // classic icnoic colors opposed to random generated
                dc.drawImage(this.player[num][this.direction], this.x * 16, this.y * 16);
            } else {
                dc.setPaint(this.shade);
                dc.fillRect((this.x * 16) + 1, (this.y * 16) + 1, 14, 14);
                dc.drawImage(this.playerN[this.direction], this.x * 16, this.y * 16);
            }
        } else if (d.powerUp() && !this.die) { // if pac is powered up and not eaten draw
            dc.drawImage("png/dead.png", this.x * 16, this.y * 16);
        } else if (d.powerUp() && this.die) { // if pacman is powerup and eaten draw
            dc.drawImage("png/eyes.png", this.x * 16, this.y * 16);
        }
        this.change++;
    }

    public void move() {//basic ai... chose random direction to move upon hitting wall/going to intersection
        if (!die) { //if not eaten
            if (!this.move && this.change % 3 == 2) { //if called to stop moving then change direction and try to move again
                this.direction = (int) (Math.random() * 4) + 0;
                this.move = true;
            }
            int place = 0;
            for (int q = 0; q < 4; q++) { //cycle to determine if in an intersection
                if (f.getV(this.x + this.moveX[q], this.y + this.moveY[q]) == 0 || f.getV(this.x + this.moveX[q], this.y + this.moveY[q]) == 1) {
                    place++;
                }
            }
            if (place >= 3 && ((int) (Math.random() * 3) + 0) == 0) { // in intersection
                this.direction = (int) (Math.random() * 4) + 0;
            }
            if (f.getV(this.x + this.moveX[this.direction], this.y + this.moveY[this.direction]) == 2 && this.move) {// hits walls stop moving
                this.move = false;
            }
            if (f.getV(this.x + this.moveX[this.direction], this.y + this.moveY[this.direction]) == 3) {// teleports
                this.x = 1;
            }
            if (f.getV(this.x + this.moveX[this.direction], this.y + this.moveY[this.direction]) == 4) {// teleports
                this.x = 26;
            }
            if (this.move && this.change % 3 == 2) { //move if not going to pass into a wall
                this.x += moveX[direction];
                this.y += moveY[direction];
            }
            if (this.x == p.getX() && this.y == p.getY() && d.powerUp()) { //if pacman has a power pellet and is touching the ghost
                die = true;
                this.x = (int) (Math.random() * 2) + 13;
                this.y = (int) (Math.random() * 2) + 14;
            } else if (this.x == p.getX() && this.y == p.getY()) {
                p.EditLives(-1);
                killing = true;
            }
        } else { // if eaten
            if (!d.powerUp()) {
                this.die = false;
            }
        }
    }

    public boolean Kill() {
        return killing;
    }

    public void SwitchKill() {
        if (this.killing) {
            this.killing = false;
        }
    }

    public int getX() { //return the x value of the ghost
        return this.x;
    }

    public int getY() { //return the y value of the ghost
        return this.y;
    }
}
