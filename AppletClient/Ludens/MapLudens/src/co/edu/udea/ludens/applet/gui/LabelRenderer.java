/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.udea.ludens.applet.gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author juanga
 */
public class LabelRenderer extends DefaultTableCellRenderer {

         /**
          * @see java.io.Serializable
          */
         private static final long serialVersionUID = 2l;

         /*
          * (non-Javadoc)
          *
          * @see
          * javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent
          * (javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
          */
         public Component getTableCellRendererComponent(JTable table,
                 Object value,
                 boolean isSelected,
                 boolean hasFocus,
                 int row,
                 int column) {

             JLabel label = new JLabel(value.toString());
             label.setHorizontalTextPosition(SwingConstants.CENTER);
                  label.setHorizontalAlignment(SwingConstants.CENTER);

             return label;
         }
  }
