/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ac.mongoserviceclient.tools;

import com.mongodb.BasicDBObject;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.LinkedList;
import javax.swing.JPanel;
import project.ac.mongoserviceclient.data.CambiosSelectorValor;

/**
 *
 * @author JuanCamilo
 */
public class Panel extends JPanel {

    private LinkedList<CambiosSelectorValor> list;

    public Panel() {

        list = new LinkedList<>();

        setVisible(true);//se puede visualizar el JPanel
        setLayout(null);//permite organizar el contenido del panel libremente, sin restricciones
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        int[] data_1 = graphic(g2, 622, 25, 200, 31, 101, 20, 10);
        int[] data_2 = graphic(g2, 622, 240, 400, 31, 2, 5, 1);

        graphicData_1(g2, data_1);
        graphicData_2(g2, data_2);
    }

    private int[] graphic(Graphics2D g2, int xf, int y0, int yf,
            int xCoordNumbers, int yCoordNumbers, int xStringNumbers, int yStringNumbers) {

        int arrows = 10;
        int scales = 5;
        int[] data = new int[3];
        data[0] = yf;
        int origen = 6;
        int distString = 20;
        int x = 50;
        int yInx = 50;
        int xIny = yf;

//        // x-axis
        g2.drawLine(x, xIny, xf, xIny);
//        // y-axis
        g2.drawLine(yInx, y0, yInx, yf);
//        // x-axis arrow
        g2.drawLine(xf - arrows, xIny - scales, xf, xIny);
        g2.drawLine(xf - arrows, xIny + scales, xf, xIny);
//        // y-axis arrow
        g2.drawLine(yInx - scales, y0 + arrows, yInx, y0);
        g2.drawLine(yInx + scales, y0 + arrows, yInx, y0);
//
//        // draw origin Point
        g2.fillOval(x - (origen / 2), yf - (origen / 2), origen, origen);
//
//        // draw text "X" and draw text "Y"
        g2.drawString("X", xf - distString / 2, xIny + distString);
        g2.drawString("Y", yInx - distString,
                y0 + distString / 2);
        g2.drawString("(0, 0)", x - distString, yf + distString);

        int xLength = (xf - x)
                / (xCoordNumbers - 1);
        data[1] = xLength;
        int yLength = (yf - y0)
                / (yCoordNumbers - 1);
        data[2] = yLength;

        // draw x-axis numbers
        for (int i = 1; i < xCoordNumbers; i++) {
            g2.drawLine(x + (i * xLength), xIny - scales, x + (i * xLength), xIny + scales);
            if ((double) (i % xStringNumbers) == 0) {
                g2.drawString(Integer.toString(i), x + (i * xLength) - 3, xIny + distString);
            }
        }

        //draw y-axis numbers
        for (int i = 1; i < yCoordNumbers; i++) {

            if ((double) (i % 10) == 0) {
                g2.drawLine(yInx - scales, yf - (i * yLength), yInx + scales, yf - (i * yLength));
            }
            if ((double) (i % yStringNumbers) == 0) {
                g2.drawString(Integer.toString(i), yInx - distString, yf - (i * yLength));
            }
        }
        return data;
    }

    public LinkedList<CambiosSelectorValor> getList() {
        return list;
    }

    public void setList(LinkedList<CambiosSelectorValor> list) {
        this.list = list;
    }

    private void graphicData_1(Graphics2D g2, int[] data) {

        if (!list.isEmpty()) {
            int x = 50;
            int xf = data[0];//depende del atributo yf del metodo graphic()
            int yLen = data[2];//depende de la longitud de las muestran en Y (yLength) en el metodo graphic()
            int com = 0;
            int i = 0;
            int yf = 0;
            for (CambiosSelectorValor obj : list) {
                i++;
                int y = 50 + (data[1] * i);//depende de la longitud de las muestras en X (xLength) en el metodo graphic()
                if (com < obj.getValor()) {
                    yf = xf - ((obj.getValor() - com) * yLen);
                } else if (com > obj.getValor()) {
                    yf = xf + ((com - obj.getValor()) * yLen);
                }
                g2.drawLine(x, xf, y, yf);
                x = y;
                xf = yf;
                com = obj.getValor();
            }
        }
    }

    private void graphicData_2(Graphics2D g2, int[] data) {

        if (!list.isEmpty()) {

            int x = 50;
            int xf = data[0];//depende del atributo yf del metodo graphic()
            int yLen = data[2];//depende de la longitud de las muestran en Y (yLength) en el metodo graphic()
            int i = 0;
            int com = 0;
            int yf = 0;
            for (CambiosSelectorValor obj : list) {

                int lig = obj.getLuz().compareTo(Boolean.FALSE);
                i++;
                int y = 50 + (data[1] * i);//depende de la longitud de las muestras en X (xLength) en el metodo graphic()
                if (lig == 0 && com == 0) {
                    yf = xf;
                } else if (lig == 0 && com == 1) {
                    yf = xf + (yLen);
                } else if (lig == 1 && com == 0) {
                    yf = xf - (yLen);
                } else if (lig == 1 && com == 1) {
                    yf = xf;
                }
                g2.drawLine(x, xf, y, yf);
                System.out.println(i+":"+obj.getLuz());

                x = y;
                xf = yf;
                com = lig;
            }
        }

    }

}
