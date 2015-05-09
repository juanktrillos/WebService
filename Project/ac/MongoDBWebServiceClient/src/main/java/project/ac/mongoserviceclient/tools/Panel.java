/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ac.mongoserviceclient.tools;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author JuanCamilo
 */
public class Panel extends JPanel {

    // x-axis coord constants
    private final int x0;
    private final int xf0;
    private final int xIny0;

    private final int x1;
    private final int xf1;
    private final int xIny1;
    // y-axis coord constants
    private final int y0;
    private final int yf0;
    private final int yInx0;

    private final int y1;
    private final int yf1;
    private final int yInx1;
    //arrows of axis are represented with "hipotenuse" of 
    //triangle
    // now we are define length of cathetas of that triangle
    private final int arrows;
    private final int scales;
    // size of start coordinate lenght
    private final int origen;
    // distance of coordinate strings from axis
    private final int distString;

    private int xvalor;
    private int yvalor;

    public Panel() {
        //672 440
        x0 = 50;
        xf0 = 650;
        xIny0 = 480;

        y0 = 260;
        yf0 = 480;
        yInx0 = 50;

        x1 = 50;
        xf1 = 650;
        xIny1 = 650;

        y1 = 500;
        yf1 = 650;
        yInx1 = 50;

        arrows = 10;
        scales = 5;

        origen = 6;
        distString = 20;

        xvalor = xIny1;
        yvalor = yInx1;

        setVisible(true);//se puede visualizar el JPanel
        setLayout(null);//permite organizar el contenido del panel libremente, sin restricciones
    }

    public void graphic(Graphics graphics, Integer valor, Boolean luz) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // x-axis
        g2.drawLine(x0, xIny0, xf0, xIny0);
        g2.drawLine(x1, xIny1, xf1, xIny1);
        // y-axis
        g2.drawLine(yInx0, y0, yInx0, yf0);
        g2.drawLine(yInx1, y1, yInx1, yf1);

        // x-axis arrow
        g2.drawLine(xf0 - arrows, xIny0 - scales, xf0, xIny0);
        g2.drawLine(xf0 - arrows, xIny0 + scales, xf0, xIny0);
        g2.drawLine(xf1 - arrows, xIny1 - scales, xf1, xIny1);
        g2.drawLine(xf1 - arrows, xIny1 + scales, xf1, xIny1);

        // y-axis arrow
        g2.drawLine(yInx0 - scales, y0 + arrows, yInx0, y0);
        g2.drawLine(yInx0 + scales, y0 + arrows, yInx0, y0);
        g2.drawLine(yInx1 - scales, y1 + arrows, yInx1, y1);
        g2.drawLine(yInx1 + scales, y1 + arrows, yInx1, y1);

        // draw origin Point
        g2.fillOval(x0 - (origen / 2), yf0 - (origen / 2), origen, origen);
        g2.fillOval(x1 - (origen / 2), yf1 - (origen / 2), origen, origen);

        // draw text "X" and draw text "Y"
        g2.drawString("X", xf0 - distString / 2, xIny0 + distString);
        g2.drawString("Y", yInx0 - distString,
                y0 + distString / 2);
        g2.drawString("(0, 0)", x0 - distString, yf0 + distString);
        g2.drawString("X", xf1 - distString / 2, xIny1 + distString);
        g2.drawString("Y", yInx1 - distString,
                y1 + distString / 2);
        g2.drawString("(0, 0)", x1 - distString, yf1 + distString);

        // numerate axis
        int xCoordNumbers = 51;
        int xStringNumbers = 10;
        int yCoordNumbers = 101;
        int yStringNumbers = 20;
        int xLength = (xf0 - x0)
                / xCoordNumbers;
        int yLength = (yf0 - y0)
                / yCoordNumbers;

        int xCoordNumbers1 = 51;
        int xStringNumbers1 = 10;
        int yCoordNumbers1 = 2;
        int yStringNumbers1 = 20;
        int xLength1 = (xf1 - x1)
                / xCoordNumbers1;
        int yLength1 = (yf1 - y1)
                / yCoordNumbers1;

        // draw x-axis numbers
        for (int i = 1; i < xCoordNumbers; i++) {
            g2.drawLine(x0 + (i * xLength), xIny0 - scales, x0 + (i * xLength), xIny0 + scales);
            if ((double) (i % xStringNumbers) == 0) {
                g2.drawString(Integer.toString(i), x0 + (i * xLength) - 3, xIny0 + distString);
            }
        }
        for (int i = 1; i < xCoordNumbers1; i++) {
            g2.drawLine(x1 + (i * xLength1), xIny1 - scales, x1 + (i * xLength1), xIny1 + scales);
            if ((double) (i % xStringNumbers1) == 0) {
                g2.drawString(Integer.toString(i), x1 + (i * xLength1) - 3, xIny1 + distString);
            }
        }

        //draw y-axis numbers
        for (int i = 1; i < yCoordNumbers; i++) {

            if ((double) (i % yStringNumbers) == 0) {
                g2.drawLine(yInx0 - scales, yf0 - (i * yLength), yInx0 + scales, yf0 - (i * yLength));
            }
            if ((double) (i % yStringNumbers) == 0) {
                g2.drawString(Integer.toString(i), yInx0 - distString, yf0 - (i * yLength));
            }
        }
        for (int i = 1; i < yCoordNumbers1; i++) {

            g2.drawLine(yInx1 - scales, yf1 - (i * yLength1), yInx1 + scales, yf1 - (i * yLength1));
            g2.drawString(Integer.toString(i), yInx1 - distString, yf1 - (i * yLength1));
        }
    }

}
