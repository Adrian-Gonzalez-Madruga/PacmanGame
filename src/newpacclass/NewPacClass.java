package newpacclass;

import DLibX.DConsole;
import java.awt.Color;

public class NewPacClass {

    public static void main(String[] args) {
        int n = 64;
        int multiply = 2;
        double gc = 4; // how many ghosts will be present

        DConsole dc = new DConsole(28 * 16, 35 * 16);
        Field f = new Field();
        Pac p = new Pac();
        Dot d = new Dot();
        Ghost[] g = new Ghost[n];

        boolean classic = false;

        for (int q = 0; q < n; q++) { // ghost linking classes
            g[q] = new Ghost();
            g[q].StartGhost(dc, f, p, d);
        }
        f.StartField(dc, p, d, g); //link each class together
        p.StartPac(dc, f, d, g);
        d.StartDot(dc, f, p, g);

        
        //while (!dc.isKeyPressed(' ')); // used to halt for presentation
        
        while (true) { //start the game
            dc.clear();

            f.draw(); //draw the field (always draw first)
            f.score();
            for (int q = 0; q < (int) gc; q++) { // in for loop manage ghosts
                g[q].draw(q % 4, classic); //draw ghost
                if (d.powerUp() && p.getX() == g[q].getX() && p.getY() == g[q].getY()) { //score for eating ghosts
                    f.scorePlus(100 * multiply);
                    multiply *= 2;
                } else if (!d.powerUp()) { // reset score ghost multiplier
                    multiply = 2;
                }
                if (g[q].Kill()) { // if ghost touches pacman
                    p.Reset();
                    for (int e = 0; e < n; e++) {
                        g[e].Reset();
                        g[e].SwitchKill();
                    }
                }
                g[q].move(); // move the ghost

            }
            p.Lives();
            p.move(); //take in keys and hitbox then move
            d.Eat(); //eat the dot
            p.draw(); //draw pacman

            if (dc.isKeyPressed(' ')) { //change from classic mode to my mode
                if (classic) {
                    classic = false;
                } else {
                    classic = true;
                }
                dc.pause(100);
            }
            if (d.Left() == 0) { // if pac ate all the pacman
                d.Reset();
                f.Reset();
                p.Reset();
                for (int q = 0; q < n; q++) {
                    g[q].Reset();
                }
                gc += 0.5;
            }

            dc.redraw();
            dc.pause(30);
        }
    }
}
