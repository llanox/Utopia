/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.ludens.applet.gui;

import co.edu.udea.ludens.domain.Element;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author telematica
 */
public class ElementTableModel extends AbstractTableModel {

    String[] columnNames;
    List<Object> elements = new ArrayList<Object>();

    public ElementTableModel(String[] columnNames, List<Object> elements) {
        this.columnNames = columnNames;
        this.elements = elements;
    }

    @Override
    public String getColumnName(int col) {

        return (this.columnNames[col]);
    }

    @Override
    public int getRowCount() {
        if (this.elements == null) {
            return 0;
        }

        return (this.elements.size());
    }

    @Override
    public int getColumnCount() {

        return (this.columnNames.length);
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (elements == null) {

            return null;
        }

        Element element = (Element) elements.get(row);
        if (col == 0) {

            return element.getIncrementable().getName();
        } else if (col == 1) {

            return element.getQuantity() + "";
        } else if (col == 2) {

            return element.getCalculatedValue() + "";
        } else if (col == 3) {

            return element.getLevel() + "";
        }

        return null;
    }

    @Override
    public Class<?> getColumnClass(int column) {
        if (0 < this.getRowCount()) {

            return getValueAt(0, column).getClass();
        } else {

            return null;
        }
    }
}