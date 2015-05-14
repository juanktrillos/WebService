package project.ac.mongoserviceclient.app;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import java.util.Date;
import java.util.LinkedList;
import java.util.Set;
import project.ac.mongoservice.MongoDB;
import project.ac.mongoservice.MongoDB_Service;
import project.ac.mongoserviceclient.data.CambiosSelectorValor;
import project.ac.mongoserviceclient.data.Dispositivo;
import project.ac.mongoserviceclient.data.RelacionDispositivoSelector;
import project.ac.mongoserviceclient.data.Selector;
import project.ac.mongoserviceclient.data.SelectorValor;
import project.ac.mongoserviceclient.tools.Panel;

/**
 *
 * @author oarcila
 */
public class TestUsoDispositivo extends javax.swing.JFrame {

    private static MongoDB port;

    private String nombreDispositivo;
    private Selector selector;
    private SelectorValor selectorValor;
    private Panel panel;

    /**
     * Creates new form NewJFrame
     */
    public TestUsoDispositivo() {
        initComponents();
        port = getMongoDBPort();
        panel = new Panel();
        fondo.setVisible(false);
        panel.setSize(fondo.getSize());
        System.out.println(fondo.getSize());
        fondo.add(panel);

        nombreDispositivo = "First Device";
        cargarValoresSelector(nombreDispositivo);

        if (bSelector.isEnabled()) {
            configurarInicioGUI();
        }

    }

    private void configurarInicioGUI() {
        bSelector.setSelected(selectorValor.getLuz());

        sNivelLuz.setMaximum(100);
        sNivelLuz.setMinimum(0);
        sNivelLuz.setValue(selectorValor.getValor());

        if (bSelector.isSelected()) {
            bSelector.setText("Apagar");
            tEstadoSelector.setText("Encendido");
        } else {
            sNivelLuz.setEnabled(false);
        }
    }

    private void cargarValoresSelector(String nombreDispositivo) {

        LinkedList<Dispositivo> dispositivos = (LinkedList<Dispositivo>)//
                findCollection(Dispositivo.class.getCanonicalName(), Dispositivo.nombre, nombreDispositivo);

        if (dispositivos.size() > 0) {
            Dispositivo dispositivo = dispositivos.getFirst();

            String keyDisp = dispositivo.getString("_id");

            LinkedList<RelacionDispositivoSelector> lRelDisSel = (LinkedList<RelacionDispositivoSelector>)//
                    findCollection(RelacionDispositivoSelector.class.getCanonicalName(), "idDispositivo", keyDisp);
            RelacionDispositivoSelector relDisSel = lRelDisSel.getFirst();
            String keySel = relDisSel.getIdSelector();

            LinkedList<Selector> selectores = (LinkedList<Selector>) //
                    findCollection(Selector.class.getCanonicalName(), "_id", keySel);

            LinkedList<SelectorValor> selectoresValores = (LinkedList<SelectorValor>)//
                    findCollection(SelectorValor.class.getCanonicalName(), SelectorValor.idSelector, keySel);

            selector = selectores.getFirst();
            selectorValor = selectoresValores.getFirst();
        } else {
            bSelector.setEnabled(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bSelector = new javax.swing.JToggleButton();
        tEstadoSelector = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        sNivelLuz = new javax.swing.JSlider();
        tNivelLuz = new javax.swing.JLabel();
        fondo = new javax.swing.JPanel();
        bGraphic = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 700));

        bSelector.setText("Encender");
        bSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSelectorActionPerformed(evt);
            }
        });

        tEstadoSelector.setText("Apagado");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Encendedor Alcoba Principal");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Selector Alcoba Principal");

        sNivelLuz.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sNivelLuzStateChanged(evt);
            }
        });
        sNivelLuz.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sNivelLuzMouseReleased(evt);
            }
        });

        tNivelLuz.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tNivelLuz.setText("0");

        javax.swing.GroupLayout fondoLayout = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(fondoLayout);
        fondoLayout.setHorizontalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        fondoLayout.setVerticalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        bGraphic.setText("Monitorear");
        bGraphic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGraphicActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sNivelLuz, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bSelector)
                                .addGap(18, 18, 18)
                                .addComponent(tEstadoSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tNivelLuz, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(bGraphic)
                .addGap(40, 40, 40))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(bGraphic))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSelector)
                    .addComponent(tEstadoSelector)
                    .addComponent(tNivelLuz))
                .addGap(18, 18, 18)
                .addComponent(sNivelLuz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSelectorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_bSelectorActionPerformed
    {//GEN-HEADEREND:event_bSelectorActionPerformed
        boolean estadoLuz = false;
        if (bSelector.isSelected()) {
            estadoLuz = true;
            bSelector.setText("Apagar");
            tEstadoSelector.setText("Encendido");
        } else {
            bSelector.setText("Encender");
            tEstadoSelector.setText("Apagado");
        }
        selectorValor.setLuz(estadoLuz);
        sNivelLuz.setEnabled(estadoLuz);

        actualizarValorAlmacenadoLuz(selectorValor);
        insertarNuevoValorCambio(selector, selectorValor);
        if (fondo.isVisible()) {
            monitorearCambioSectorValor();
        }
    }//GEN-LAST:event_bSelectorActionPerformed

    private void sNivelLuzStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_sNivelLuzStateChanged
    {//GEN-HEADEREND:event_sNivelLuzStateChanged
        selectorValor.setValor(sNivelLuz.getValue());
        tNivelLuz.setText("" + sNivelLuz.getValue());
    }//GEN-LAST:event_sNivelLuzStateChanged

    private void sNivelLuzMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_sNivelLuzMouseReleased
    {//GEN-HEADEREND:event_sNivelLuzMouseReleased
        actualizarValorAlmacenadoNivel(selectorValor);
        insertarNuevoValorCambio(selector, selectorValor);
        if (fondo.isVisible()) {
            monitorearCambioSectorValor();
        }
    }//GEN-LAST:event_sNivelLuzMouseReleased

    private void bGraphicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGraphicActionPerformed
        // TODO add your handling code here:
        fondo.setVisible(!fondo.isVisible());
        if (fondo.isVisible()) {
            monitorearCambioSectorValor();
        }
    }//GEN-LAST:event_bGraphicActionPerformed

    private void monitorearCambioSectorValor() {
        LinkedList<CambiosSelectorValor> csv = (LinkedList<CambiosSelectorValor>)//
                findAllCollection(CambiosSelectorValor.class.getCanonicalName());
        if (!csv.isEmpty()) {
            panel.setList(csv);
            repaint();
        }
    }

    private void actualizarValorAlmacenadoLuz(SelectorValor selectorValor) {
//        CriterioActualizacion criterioActualizacion = new CriterioActualizacion();
//        criterioActualizacion.setCriterio(SelectorValor.idSelector, selectorValor.getIdSelector());
//        criterioActualizacion.setNuevoValor(SelectorValor.luz, selectorValor.getLuz());

        updateCollection(SelectorValor.class.getCanonicalName(), SelectorValor.idSelector,//
                selectorValor.getIdSelector(), SelectorValor.luz, selectorValor.getLuz().toString());
    }

    private void actualizarValorAlmacenadoNivel(SelectorValor selectorValor) {
//        CriterioActualizacion criterioActualizacion = new CriterioActualizacion();
//        criterioActualizacion.setCriterio(SelectorValor.idSelector, selectorValor.getIdSelector());
//        criterioActualizacion.setNuevoValor(SelectorValor.valor, selectorValor.getValor());

        updateCollection(SelectorValor.class.getCanonicalName(), SelectorValor.idSelector,//
                selectorValor.getIdSelector(), SelectorValor.valor, selectorValor.getValor().toString());
    }

    private void insertarNuevoValorCambio(Selector selector, SelectorValor selectorValor) {
        Date date = new Date();
        CambiosSelectorValor cambiosSelectorValor = new CambiosSelectorValor("", selector.getString("_id"),//
                date, selectorValor.getLuz(), selectorValor.getValor());

        addCollection(CambiosSelectorValor.class.getCanonicalName(), cambiosSelectorValor.toString());
    }

    /**
     * Get service port stub for Calculator web service.
     */
    private MongoDB getMongoDBPort() {
        MongoDB_Service service = new MongoDB_Service();
        return service.getMongoDBPort();
    }

    private void addCollection(String className, String data) {

        String message = port.add(className, data);
        port.log(message);
        System.out.println(message);
    }

    private static void removeCollection(String className, String data) {

        String delete = port.delete(className, data);
        port.log(delete);
        System.out.println(delete);
    }

    private LinkedList findCollection(String className, String atributo, String data) {

        LinkedList<BasicDBObject> list = new LinkedList<>();
        String find = port.find(className, atributo, data);
        String[] found = find.split("&&");
        for (String dt : found) {
//            System.out.println("Object " + (i + 1) + ": " + found[i]);
//            port.log("Object " + (i + 1) + ": " + found[i]);
            list.add(create(className, dt));
        }
        return list;
    }

    private LinkedList findAllCollection(String className) {

        LinkedList<BasicDBObject> list = new LinkedList<>();
        String findAll = port.findAll(className);
        String[] foundAll = findAll.split("&&");
        for (String found : foundAll) {
            list.add(create(className, found));
        }
        return list;
    }

    private void updateCollection(String className, String cClave, String cValor, String aClave, String aValor) {

        String update = port.update(className, cClave, cValor, aClave, aValor);
        port.log(update);
        System.out.println(update);
    }

    private static BasicDBObject create(String className, String data) {
        BasicDBObject o = null;
        try {
            Class clase = Class.forName(className);
            if (clase != null) {
                o = (BasicDBObject) clase.newInstance();
                if (o != null) {
                    BasicDBObject obj = (BasicDBObject) JSON.parse(data);
                    Set<String> keys = obj.keySet();
                    for (String key : keys) {
                        Object val = obj.get(key);
                        ((BasicDBObject) o).put(key, val);
                    }
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
        }
        return o;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestUsoDispositivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestUsoDispositivo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bGraphic;
    private javax.swing.JToggleButton bSelector;
    private javax.swing.JPanel fondo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSlider sNivelLuz;
    private javax.swing.JLabel tEstadoSelector;
    private javax.swing.JLabel tNivelLuz;
    // End of variables declaration//GEN-END:variables
}
