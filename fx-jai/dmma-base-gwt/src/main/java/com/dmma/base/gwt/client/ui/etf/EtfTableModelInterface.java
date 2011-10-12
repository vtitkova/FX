package com.dmma.base.gwt.client.ui.etf;

import java.util.ArrayList;

/**
 *  The <code>GwtTableModel</code> interface specifies the methods the
 *  <code>GwtTable</code> will use to interrogate a tabular data model. <p>
 *
 *  The <code>GwtTable</code> can be set up to display any data
 *  model which implements the 
 *  <code>GwtTableModel</code> interface with a couple of lines of code:  <p>
 *  <pre>
 *  	GwtTableModel myData = new MyTableModel(); 
 *  	GwtTable table = new GwtTable(myData);
 *  </pre><p>
 *
 * @author marcenkovsd
 */

public interface EtfTableModelInterface{
    /**
     * Returns the number of rows in the model. A
     * <code>GwtTable</code> uses this method to determine how many rows it
     * should display.  This method should be quick, as it
     * is called frequently during rendering.
     *
     * @return the number of rows in the model
     * @see #getColumnCount
     */
    public int getRowCount();

    /**
     * Returns the number of columns in the model. A
     * <code>GwtTable</code> uses this method to determine how many columns it
     * should create and display by default.
     *
     * @return the number of columns in the model
     * @see #getRowCount
     */
    public int getColumnCount();

    /**
     * Returns the name of the column at <code>columnIndex</code>.  This is used
     * to initialize the table's column header name.  Note: this name does
     * not need to be unique; two columns in a table can have the same name.
     *
     * @param	columnIndex	the index of the column
     * @return  the name of the column
     */
    public String getColumnName(int columnIndex);

    /**
     * Returns the width of the column at <code>columnIndex</code>.  This is used
     * to set table's column width.
     *
     * @param	columnIndex	the index of the column
     * @return  width of the column 
     */
    public int getColumnWidth(int columnIndex);
	
    
    /**
     * Returns the most specific superclass for all the cell values 
     * in the column.  This is used by the <code>GwtTable</code> to set up a 
     * default renderer and editor for the column.
     *
     * @param columnIndex  the index of the column
     * @return the common ancestor class of the object values in the model.
     */
    public Class<?> getColumnClass(int columnIndex);

    /**
     * Returns true if the cell at <code>rowIndex</code> and
     * <code>columnIndex</code>
     * is editable.  Otherwise, <code>setValueAt</code> on the cell will not
     * change the value of that cell.
     *
     * @param	rowIndex	the row whose value to be queried
     * @param	columnIndex	the column whose value to be queried
     * @return	true if the cell is editable
     * @see #setValueAt
     */
    
    public boolean isCellEditable(int rowIndex, int columnIndex);

    /**
     * Returns the value for the cell at <code>columnIndex</code> and
     * <code>rowIndex</code>.
     *
     * @param	rowIndex	the row whose value is to be queried
     * @param	columnIndex 	the column whose value is to be queried
     * @return	the value Object at the specified cell
     */
    public Object getValueAt(int rowIndex, int columnIndex);

    /**
     * Sets the value in the cell at <code>columnIndex</code> and
     * <code>rowIndex</code> to <code>aValue</code>.
     *
     * @param	aValue		 the new value
     * @param	rowIndex	 the row whose value is to be changed
     * @param	columnIndex 	 the column whose value is to be changed
     * @see #getValueAt
     * @see #isCellEditable
     */
    public void setValueAt(Object aValue, int rowIndex, int columnIndex);

    
    /**This is way how model can implict to table
     * for example mode was changed by app. 
     * to update view, fire GwtTableModelEvent
     * tha will notify table about changes*/
	public void setTableModelListener(EtfTableModelListener listenerr);
		
	
	
	
	public int[] getIndexesByFilter(ArrayList<String>  filterStrings);

	public boolean isColumnFilterable(int columnIndex);

	public Object getObjectId(int rowIndex);
	
	
	
}

