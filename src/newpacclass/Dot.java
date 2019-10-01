package newpacclass;

import DLibX.DConsole;
import java.awt.Color;

public class Dot { // manage dot, score, power pellets, and other items

    private DConsole dc;
    private Field f;
    private Pac p;
    private Ghost[] g;
    private int pellets = 298;
    private int timer = 0;
    private boolean pow;

    public void StartDot(DConsole dc, Field f, Pac p, Ghost[] g) {
        this.dc = dc;
        this.f = f;
        this.p = p;
        this.g = g;
    }
    
    public void Reset() {
        this.pellets = 298;
        this.timer = -1;
        this.pow = false;
    }

    public void Eat() { //eat the dots and subtract from counter
        if (f.getV(p.getX(), p.getY()) == 0) {
            f.setV(p.getX(), p.getY(), -1);
            f.scorePlus(10);
            this.pellets--;
        } else if (f.getV(p.getX(), p.getY()) == 5) {
            f.setV(p.getX(), p.getY(), -2);
            f.scorePlus(50);
            this.pellets--;
            this.timer = 180;
        }
        if (this.timer > 0) { //timer for if power up is active
            this.timer--;
            this.pow = true;
        } else {
            this.pow = false;
        }
    }

    public boolean powerUp() { //boolean if power up is active
        return this.pow;
    }

    public int getTimer() {
        return this.timer;
    }

    public int Left() {  //return remaining pellets
        return this.pellets;
    }
}
