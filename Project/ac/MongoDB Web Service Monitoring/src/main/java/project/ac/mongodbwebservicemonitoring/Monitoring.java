/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ac.mongodbwebservicemonitoring;

import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author Olmedo Arcila Guzman - Mentor
 * @author Juan Camilo Trillos Velosa - Ing. Multimedia
 * @author Felipe Garaycochea Lozada - Ing. Multimedia
 */
public class Monitoring extends JFrame {

    private Panel panel;

    public Monitoring(String title) throws HeadlessException {
        super(title);

        panel = new Panel();
        add(panel);

        setSize(500, 500);//Tamaño de la ventana
        setVisible(true);//se puede visualizar la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);//El JFrame se cierra al presionar salir [X]
        setResizable(false);//No se puede modificar el tamaño del JFrame manualmente
        setLocationRelativeTo(null);//JFrame aparece en el Centro
    }

    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }
}
