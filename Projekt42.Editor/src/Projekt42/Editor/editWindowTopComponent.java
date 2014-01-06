/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projekt42.Editor;

import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//Projekt42.Editor//editWindow//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "editWindowTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "Projekt42.Editor.editWindowTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_editWindowAction",
        preferredID = "editWindowTopComponent"
)
@Messages({
    "CTL_editWindowAction=editWindow",
    "CTL_editWindowTopComponent=editWindow Window",
    "HINT_editWindowTopComponent=This is a editWindow window"
})
public final class editWindowTopComponent extends TopComponent {

    //Die Liste mit den ganzen Aktionen, die bei Kombinationen von Gegenständen zur verfügung stehen werden
    ArrayList<actionTableRow> actionTableData = new ArrayList<actionTableRow>();

    //Die Liste mit den Ganzen Gegenständen, die man für die Aktionen dann auswählekann
    ArrayList<GegenstandTableRow> gegenstandTableData = new ArrayList<GegenstandTableRow>();

    //Die JComboBox, die die Auswahl jeweils eines der Gegenstände für die Aktionen ermöglicht
    JComboBox<String> actionTableGegenstandAuswahl = new JComboBox<String>();

    //Das TableModel für die Tabelle mit den Aktionen
    private ActionTableModel actionTableModel;

    public editWindowTopComponent() {
        initComponents();
        setName(Bundle.CTL_editWindowTopComponent());
        setToolTipText(Bundle.HINT_editWindowTopComponent());
        actionTableGegenstandAuswahl.addItem("blablaTest");

    }

    public class GegenstandTableRow {

        private String enumConstant, userDisplayName, tooltip, imageFileNameSnippets;

        public GegenstandTableRow(String enumConstant, String userDisplayName, String tooltip, String imageFileNameSnippets) {
            this.enumConstant = enumConstant;
            this.userDisplayName = userDisplayName;
            this.tooltip = tooltip;
            this.imageFileNameSnippets = imageFileNameSnippets;
        }

        public GegenstandTableRow() {
            this("", "", "", "");
        }

        public String getEnumConstant() {
            return enumConstant;
        }

        public void setEnumConstant(String enumConstant) {
            this.enumConstant = enumConstant;
        }

        public String getUserDisplayName() {
            return userDisplayName;
        }

        public void setUserDisplayName(String userDisplayName) {
            this.userDisplayName = userDisplayName;
        }

        public String getTooltip() {
            return tooltip;
        }

        public void setTooltip(String tooltip) {
            this.tooltip = tooltip;
        }

        public String getImageFileNameSnippets() {
            return imageFileNameSnippets;
        }

        public void setImageFileNameSnippets(String imageFileNameSnippets) {
            this.imageFileNameSnippets = imageFileNameSnippets;
        }

        @Override
        public String toString() {
            return "GegenstandTableRow{" + "enumConstant=" + enumConstant + ", userDisplayName=" + userDisplayName + ", tooltip=" + tooltip + ", imageFileNameSnippets=" + imageFileNameSnippets + '}';
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 53 * hash + (this.enumConstant != null ? this.enumConstant.hashCode() : 0);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final GegenstandTableRow other = (GegenstandTableRow) obj;
            return !((this.enumConstant == null) ? (other.enumConstant != null) : !this.enumConstant.equals(other.enumConstant));
        }

    }

    public class actionTableRow {

        private String bewegterGegenstand, zielGegenstand, methode, Argumente;
        private boolean zielIstInventar;

        public actionTableRow() {
            bewegterGegenstand = "";
            zielGegenstand = "";
            zielIstInventar = false;
            methode = "";
            Argumente = "";
        }

        public String getBewegterGegenstand() {
            return bewegterGegenstand;
        }

        public void setBewegterGegenstand(String bewegterGegenstand) {
            this.bewegterGegenstand = bewegterGegenstand;
        }

        public String getZielGegenstand() {
            return zielGegenstand;
        }

        public void setZielGegenstand(String zielGegenstand) {
            this.zielGegenstand = zielGegenstand;
        }

        public String getMethode() {
            return methode;
        }

        public void setMethode(String methode) {
            this.methode = methode;
        }

        public String getArgumente() {
            return Argumente;
        }

        public void setArgumente(String Argumente) {
            this.Argumente = Argumente;
        }

        public boolean isZielIstInventar() {
            return zielIstInventar;
        }

        public void setZielIstInventar(boolean zielIstInventar) {
            this.zielIstInventar = zielIstInventar;
        }
    }

    class ActionTableModel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return actionTableData.size();
        }

        @Override
        public int getColumnCount() {
            return 5;
        }

        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        @SuppressWarnings("Unchecked")
        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            System.out.println("row: " + rowIndex + " column: " + columnIndex);
            switch (columnIndex) {
                case 0:
                    actionTableData.get(rowIndex).setBewegterGegenstand((String) aValue);
                case 1:
                    actionTableData.get(rowIndex).setZielGegenstand((String) aValue);
                case 2:
                    actionTableData.get(rowIndex).setZielIstInventar(aValue == null ? false : (Boolean) aValue);
                case 3:
                    actionTableData.get(rowIndex).setMethode((String) aValue);
                case 4:
                    actionTableData.get(rowIndex).setArgumente((String) aValue);
            }
        }

        @Override
        public int findColumn(String columnName) {
            if ("bewegter Gegenstand" == columnName) {
                return 0;
            }
            if ("Zielgegenstand" == columnName) {
                return 1;
            }
            if ("Zielgegenstand im Inventar?" == columnName) {
                return 2;
            }
            if ("Methode" == columnName) {
                return 3;
            }
            if ("Argumente" == columnName) {
                return 4;
            }
            return -1;

        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "bewegter Gegenstand";
                case 1:
                    return "Zielgegenstand";
                case 2:
                    return "Zielgegenstand im Inventar?";
                case 3:
                    return "Methode";
                case 4:
                    return "Argumente";
                default:
                    return null;
            }
        }

        public void notifyRowAdded() {
            fireTableRowsInserted(getRowCount(), getRowCount());
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return true;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return actionTableData.get(rowIndex).getBewegterGegenstand();
                case 1:
                    return actionTableData.get(rowIndex).getZielGegenstand();
                case 2:
                    return actionTableData.get(rowIndex).isZielIstInventar();
                case 3:
                    return actionTableData.get(rowIndex).getMethode();
                case 4:
                    return actionTableData.get(rowIndex).getArgumente();
            }
            return null;
        }

    }

    class GegentandTableModel extends AbstractTableModel {//TODO: Implement something updating the JComboBox<String> to reflect changes made in the Gegenstand list(or, table)

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            synchronized (actionTableData) {
                switch (columnIndex) {
                    case 0:
                        gegenstandTableData.get(rowIndex).setEnumConstant((String) aValue);
                    case 1:
                        gegenstandTableData.get(rowIndex).setUserDisplayName((String) aValue);
                    case 2:
                        gegenstandTableData.get(rowIndex).setTooltip((String) aValue);
                    case 3:
                        gegenstandTableData.get(rowIndex).setImageFileNameSnippets((String) aValue);
                }
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public int findColumn(String columnName) {
            if ("Enum Konstante".equals(columnName)) {
                return 0;
            }
            if ("Nutzer Anzeige Name".equals(columnName)) {
                return 1;
            }
            if ("Tooltip".equals(columnName)) {
                return 2;
            }
            if ("Bild Datei Namen Schnipsel".equals(columnName)) {
                return 3;
            }
            throw new IndexOutOfBoundsException("No Column with the name: \"" + columnName + "\" exists.");
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "Enum Konstante";
                case 1:
                    return "Nutzer Anzeige Name";
                case 2:
                    return "Tooltip";
                case 3:
                    return "Bild Datei Namen Schnipsel";
                default:
                    throw new IndexOutOfBoundsException("Column index out of bounds. (" + column + ")");
            }
        }

        @Override
        public int getRowCount() {
            return gegenstandTableData.size();
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return gegenstandTableData.get(rowIndex).getEnumConstant();
                case 1:
                    return gegenstandTableData.get(rowIndex).getUserDisplayName();
                case 2:
                    return gegenstandTableData.get(rowIndex).getTooltip();
                case 3:
                    return gegenstandTableData.get(rowIndex).getImageFileNameSnippets();
                default:
                    throw new IndexOutOfBoundsException("Parameter columnIndex out of bounds. (" + columnIndex + ")");
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        jTable1.setModel(actionTableModel = new ActionTableModel());
        jTable1.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(actionTableGegenstandAuswahl));
        jTable1.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(actionTableGegenstandAuswahl));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(editWindowTopComponent.class, "editWindowTopComponent.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        synchronized (actionTableData) {
            actionTableData.add(new actionTableRow());
        }
        actionTableModel.notifyRowAdded();// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
}
