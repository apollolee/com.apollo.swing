/*
 * �˴��봴���� 2008-4-23 ����09:40:33��
 */
package com.apollo.swing.component.complextable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * <p>�ļ����ƣ�ComplexTableModel.java</p>
 * <p>�����������ۺϱ�ģ���࣬��װ���ۺϱ�ģ�����ݺͲ�������������ۺϱ��߼����ܵĺ��ġ�</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-4-23</p>
 * <p>�޸ļ�¼��</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * @version 1.0
 * @author ����
 * @since T3 V1.1
 */
@SuppressWarnings("all")
public class ComplexTableModel extends AbstractTableModel implements IConstants {

    /*------------------------------------- Public (Static) Inner Class -------------------------------------*/

    /* Add Public (Static) Inner Class */

    /*------------------------------------- Protected (Static) Inner Class -------------------------------------*/

    /* Add Protected (Static) Inner Class */

    /*------------------------------------- Friendly (Static) Inner Class -------------------------------------*/

    /* Add Friendly (Static) Inner Class */

    /*------------------------------------- Private (Static) Inner Class -------------------------------------*/

    /* Add Private (Static) Inner Class */

    /*------------------------------------- Public Static Field -------------------------------------*/

    /* Add Public Static Field */

    /*------------------------------------- Protected Static Field -------------------------------------*/

    /* Add Protected Static Field */

    /*------------------------------------- Friendly Static Field -------------------------------------*/

    /* Add Friendly Static Field */

    /*------------------------------------- Private Static Field -------------------------------------*/

    /* Add Private Static Field */

    /*------------------------------------- Static Initial Block -------------------------------------*/

    /* Add Static Initial Block */

    /*------------------------------------- Public Static Method -------------------------------------*/

    /**
     * �����м������߷�����
     * @param data ���������ݼ���
     * @return �м���
     * @since T3 V1.1
     */
    public static ComplexTableRow[] createRows(Object[][] data) {
        ComplexTableRow[] rows = new ComplexTableRow[data.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = ComplexTableRow.create(i, data[i]);
        }

        return rows;
    }

    /**
     * �����м������߷�����
     * @param identifiers �б�ʶ���󼯡�
     * @return �м���
     * @since T3 V1.1
     */
    public static ComplexTableColumn[] createColumns(Object[] identifiers) {
        ComplexTableColumn[] columns = new ComplexTableColumn[identifiers.length];

        for (int i = 0; i < columns.length; i++) {
            columns[i] = ComplexTableColumn.create(identifiers[i]);
        }

        return columns;
    }

    /*------------------------------------- Protected Static Method -------------------------------------*/

    /* Add Protected Static Method */

    /*------------------------------------- Friendly Static Method -------------------------------------*/

    /* Add Friendly Static Method */

    /*------------------------------------- Private Static Method -------------------------------------*/

    /* Add Private Static Method */

    /*------------------------------------- Public Field -------------------------------------*/

    /* Add Public Field */

    /*------------------------------------- Protected Field -------------------------------------*/

    /**
     * �����С�
     */
    protected List m_rows = new ArrayList();

    /**
     * �����С�
     */
    protected List m_columns = new ArrayList();

    /**
     * ��ʶ�Ƿ������
     */
    protected volatile boolean m_sortable = true;

    /**
     * ����Ƚ�����
     */
    protected Comparator m_sorter = new Comparator() {

        /**
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        public int compare(Object o1, Object o2) {
            ComplexTableRow r1 = (ComplexTableRow) o1;
            ComplexTableRow r2 = (ComplexTableRow) o2;

            ComplexTableColumn[] sortColumns = getSortColumns();
            for (int i = 0; i < sortColumns.length; i++) {
                int ci = getColumnIndex(sortColumns[i]);
                int cr = r1.getCell(ci).compareTo(r2.getCell(ci));

                if (cr != 0) {
                    return sortColumns[i].getSortState() == SORTSTATE_ASCENDING ? cr : -cr;
                }
            }

            return r1.m_modelIndex < r2.m_modelIndex ? -1 : 1;
        }

    };

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     * @param columns �����ж���
     * @since T3 V1.1
     */
    public ComplexTableModel(ComplexTableColumn[] columns) {
        this(new ComplexTableRow[0], columns);
    }

    /**
     * ���췽����
     * @param data �������ݣ���λ���ݱ�ṹ��
     * @param identifiers �����б�ʶ����
     * @since T3 V1.1
     */
    public ComplexTableModel(Object[][] data, Object[] identifiers) {
        this(createRows(data), identifiers);
    }

    /**
     * ���췽����
     * @param rows �����ж���
     * @param identifiers �����б�ʶ����
     * @since T3 V1.1
     */
    public ComplexTableModel(ComplexTableRow[] rows, Object[] identifiers) {
        this(rows, createColumns(identifiers));
    }

    /**
     * ���췽����
     * @param rows �����ж���
     * @param columns �����ж���
     * @since T3 V1.1
     */
    public ComplexTableModel(ComplexTableRow[] rows, ComplexTableColumn[] columns) {
        setData(rows, columns);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * ��ȡ�ֶ� sortable ��ֵ������ʶ�����ۺϱ��Ƿ���������ȱʡ������
     * @return �ֶ� sortable ��ֵ��
     * @since T3 V1.1
     */
    public boolean isSortable() {
        return m_sortable;
    }

    /**
     * �����ֶ� sortable ��ֵ������ʶ�����ۺϱ��Ƿ���������ȱʡ������ע�⣺�˷������ú���Ҫʹ��TComplexTable.repaintAll()������ˢ�±�ͷ��
     * @param sortable �ֶ� sortable ��ֵ��
     * @since T3 V1.1
     */
    public void setSortable(boolean sortable) {
        m_sortable = sortable;

        if (!m_sortable) {
            cancelSort();
        }
    }

    /**
     * ȡ�����򣬼�ȡ��ĳһ���е�����
     * @param column �С�
     * @since T3 V1.1
     */
    public void cancelSort(ComplexTableColumn column) {
        int sortIndex = column.getSortIndex();
        column.setSortState(SORTSTATE_NORMAL);

        ComplexTableColumn[] sortColumns = getSortColumns();
        for (int i = 0; i < sortColumns.length; i++) {
            int si = sortColumns[i].getSortIndex();

            if (si > sortIndex) {
                sortColumns[i].setSortIndex(si - 1);
            }
        }

        column.setSortIndex(-1);

        sort();
    }

    /**
     * ȡ��ȫ���е�����
     * @since T3 V1.1
     */
    public void cancelSort() {
        ComplexTableColumn[] sortColumns = getSortColumns();
        for (int i = 0; i < sortColumns.length; i++) {
            sortColumns[i].setSortState(SORTSTATE_NORMAL);
            sortColumns[i].setSortIndex(-1);
        }

        sort();
    }

    /**
     * ����ĳһ�С�
     * @param column �С�
     * @since T3 V1.1
     */
    public void sort(ComplexTableColumn column) {
        if (!column.isSortable()) {
            return;
        }

        ComplexTableColumn[] sortColumns = getSortColumns();

        int sortState = column.getSortState();

        if (SORTSTATE_ASCENDING == sortState) {
            column.setSortState(SORTSTATE_DESCENDING);

        } else {
            if (SORTSTATE_NORMAL == sortState) {
                column.setSortIndex(sortColumns.length + 1);
            }

            column.setSortState(SORTSTATE_ASCENDING);
        }

        sort();
    }

    /**
     * ��ȡ�ж���
     * @param row ����ͼ������
     * @return �С�
     * @since T3 V1.1
     */
    public ComplexTableRow getRow(int row) {
        return (ComplexTableRow) m_rows.get(row);
    }

    /**
     * ��ȡ����ͼ������
     * @param row �С�
     * @return ��������
     * @since T3 V1.1
     */
    public int getRowIndex(ComplexTableRow row) {
        return m_rows.indexOf(row);
    }

    /**
     * ��ȡ��������ӵ���û�м���ģ���е��У�����������ͷ�мӺű�ʶ���С�
     * @return ��������ӵ��С�
     * @since T3 V1.1
     */
    public ComplexTableRow[] getInsertedRows() {
        List insertedRows = new ArrayList();

        for (int i = 0, size = getRowCount(); i < size; i++) {
            ComplexTableRow row = getRow(i);

            if (row.isInserted()) {
                insertedRows.add(row);
            }
        }

        return (ComplexTableRow[]) insertedRows.toArray(new ComplexTableRow[insertedRows.size()]);
    }

    /**
     * ��ȡ�������Ƴ�����ô�д�ģ�����޳����У�����������ͷ�м��ű�ʶ���С�
     * @return �������Ƴ����С�
     * @since T3 V1.1
     */
    public ComplexTableRow[] getRemovedRows() {
        List removedRows = new ArrayList();

        for (int i = 0, size = getRowCount(); i < size; i++) {
            ComplexTableRow row = getRow(i);

            if (row.isRemoved()) {
                removedRows.add(row);
            }
        }

        return (ComplexTableRow[]) removedRows.toArray(new ComplexTableRow[removedRows.size()]);
    }

    /**
     * ��ȡ�������޸ĵ���û�б����ģ�͵��У�����������ͷ���Ǻű�ʶ���С�
     * @return �������޸ĵ��С�
     * @since T3 V1.1
     */
    public ComplexTableRow[] getChangedRows() {
        List changedRows = new ArrayList();

        for (int i = 0, size = getRowCount(); i < size; i++) {
            ComplexTableRow row = getRow(i);

            if (row.isChanged()) {
                changedRows.add(row);
            }
        }

        return (ComplexTableRow[]) changedRows.toArray(new ComplexTableRow[changedRows.size()]);
    }

    /**
     * ��ȡ�����ж���˳������ͼ�ϵĵ�ǰ˳��
     * @return �����С�
     * @since T3 V1.1
     */
    public ComplexTableRow[] getRows() {
        return (ComplexTableRow[]) m_rows.toArray(new ComplexTableRow[m_rows.size()]);
    }

    /**
     * ���һ�����ݵ����β����
     * @param row �С�
     * @since T3 V1.1
     */
    public void addRow(ComplexTableRow row) {
        addRow(getRowCount(), row);
    }

    /**
     * ���һ�����ݵ�ָ��������λ�á�
     * @param index ������
     * @param row �С�
     * @since T3 V1.1
     */
    public void addRow(int index, ComplexTableRow row) {
        addRows(index, new ComplexTableRow[] { row });
    }

    /**
     * ���һ�����ݵ�ָ��������λ�á�
     * @param index ��ͼ������
     * @param modelIndex ģ��������
     * @param row �С�
     * @since T3 V1.1
     */
    public void addRow(int index, int modelIndex, ComplexTableRow row) {
        addRows(index, modelIndex, new ComplexTableRow[] { row });
    }

    /**
     * ���һ�������ݼ��͵����β����
     * @param rows �м���
     * @since T3 V1.1
     */
    public void addRows(ComplexTableRow[] rows) {
        addRows(getRowCount(), rows);
    }

    /**
     * ���һ�������ݼ��͵�ָ��������λ�á�
     * @param index ������
     * @param rows �м���
     * @since T3 V1.1
     */
    public void addRows(int index, ComplexTableRow[] rows) {
        for (int i = 0; i < rows.length; i++) {
            rows[i].setModelIndex(getRowCount());
            m_rows.add(index + i, rows[i]);
        }

        fireTableRowsInserted(index, index + rows.length - 1);
    }

    /**
     * ���һ�������ݼ��͵�ָ��������λ�á�
     * @param index ��ͼ������
     * @param modelIndex ģ��������
     * @param rows �м���
     * @since T3 V1.1
     */
    public void addRows(int index, int modelIndex, ComplexTableRow[] rows) {
        for (int i = 0, size = m_rows.size(); i < size; i++) {
            ComplexTableRow row = (ComplexTableRow) m_rows.get(i);
            int mi = row.getModelIndex();
            if (mi >= modelIndex) {
                row.setModelIndex(mi + rows.length);
            }
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].setModelIndex(modelIndex + i);
            m_rows.add(index + i, rows[i]);
        }

        fireTableRowsInserted(index, index + rows.length - 1);
    }

    /**
     * �Ƴ�ָ�������ݡ�
     * @param row �С�
     * @return �Ƴ����С�
     * @since T3 V1.1
     */
    public ComplexTableRow removeRow(ComplexTableRow row) {
        return removeRow(getRowIndex(row));
    }

    /**
     * �Ƴ�ָ������λ�õ������ݡ�
     * @param index ������
     * @return �Ƴ����С�
     * @since T3 V1.1
     */
    public ComplexTableRow removeRow(int index) {
        ComplexTableRow removedRow = (ComplexTableRow) m_rows.remove(index);

        for (int i = 0, size = getRowCount(); i < size; i++) {
            ComplexTableRow row = getRow(i);

            if (row.m_modelIndex > removedRow.m_modelIndex) {
                row.m_modelIndex--;
            }
        }

        fireTableRowsDeleted(index, index);

        return removedRow;
    }

    /**
     * �Ƴ�ָ���������ݼ��ϡ�
     * @param rows �м���
     * @since T3 V1.1
     */
    public void removeRows(ComplexTableRow[] rows) {
        for (int i = 0; i < rows.length; i++) {
            removeRow(rows[i]);
        }
    }

    /**
     * �Ƴ�ָ������λ�õ������ݼ��͡�
     * @param indexes ����������
     * @since T3 V1.1
     */
    public void removeRows(int[] indexes) {
        Arrays.sort(indexes);

        for (int i = indexes.length - 1; i >= 0; i--) {
            removeRow(indexes[i]);
        }
    }

    /**
     * �Ƴ����������ݡ�
     * @since T3 V1.1
     */
    public void removeAllRow() {
        m_rows.clear();
        fireTableDataChanged();
    }

    /**
     * �������������ݡ�
     * @param data �������ݡ�
     * @since T3 V1.1
     */
    public void setRows(Object[][] data) {
        setRows(createRows(data));
    }

    /**
     * �������������ݡ�
     * @param rows �����С�
     * @since T3 V1.1
     */
    public void setRows(ComplexTableRow[] rows) {
        setRowsWithoutNotify(rows);
        fireTableDataChanged();
    }

    /**
     * ��ȡ�ж���
     * @param column ��ģ��������
     * @return �С�
     * @since T3 V1.1
     */
    public ComplexTableColumn getColumn(int column) {
        return (ComplexTableColumn) m_columns.get(column);
    }

    /**
     * ��ȡ��ģ��������
     * @param column �ж���
     * @return ��������
     * @since T3 V1.1
     */
    public int getColumnIndex(ComplexTableColumn column) {
        return m_columns.indexOf(column);
    }

    /**
     * ��ȡ�����ж���
     * @return �����С�
     * @since T3 V1.1
     */
    public ComplexTableColumn[] getColumns() {
        return (ComplexTableColumn[]) m_columns.toArray(new ComplexTableColumn[m_columns.size()]);
    }

    /**
     * ����ж��󵽱���е�β����
     * @param column �С�
     * @since T3 V1.1
     */
    public void addColumn(ComplexTableColumn column) {
        addColumn(getColumnCount(), column);
    }

    /**
     * ����ж���ָ����ģ��������λ�á�
     * @param index ģ�͡�
     * @param column �С�
     * @since T3 V1.1
     */
    public void addColumn(int index, ComplexTableColumn column) {
        if (!isSortable()) {
            column.setSortState(SORTSTATE_NORMAL);
        }

        column.setModelIndex(getColumnCount());
        m_columns.add(index, column);

        fireTableStructureChanged();
    }

    /**
     * �Ƴ�ָ�����ж���
     * @param column �С�
     * @return �Ƴ����С�
     * @since T3 V1.1
     */
    public ComplexTableColumn removeColumn(ComplexTableColumn column) {
        return removeColumn(getColumnIndex(column));
    }

    /**
     * �Ƴ�ָ����ģ������λ�õ��ж���
     * @param index ������
     * @return �Ƴ����С�
     * @since T3 V1.1
     */
    public ComplexTableColumn removeColumn(int index) {
        ComplexTableColumn removedColumn = (ComplexTableColumn) m_columns.remove(index);

        for (int i = 0, size = getColumnCount(); i < size; i++) {
            ComplexTableColumn column = getColumn(i);
            int modelIndex = column.getModelIndex();

            if (modelIndex > removedColumn.getModelIndex()) {
                column.setModelIndex(modelIndex - 1);
            }
        }

        fireTableStructureChanged();

        return removedColumn;
    }

    /**
     * ���������ж���
     * @param identifiers �����б�ʶ��
     * @since T3 V1.1
     */
    public void setColumns(Object[] identifiers) {
        setColumns(createColumns(identifiers));
    }

    /**
     * ���������ж���
     * @param columns �����С�
     * @since T3 V1.1
     */
    public void setColumns(ComplexTableColumn[] columns) {
        setColumnsWithoutNotify(columns);
        fireTableStructureChanged();
    }

    /**
     * ���ñ���������ݡ�
     * @param data �������ݡ�
     * @param identifiers �����б�ʶ��
     * @since T3 V1.1
     */
    public void setData(Object[][] data, Object[] identifiers) {
        setData(createRows(data), identifiers);
    }

    /**
     * ���ñ���������ݡ�
     * @param data �������ݡ�
     * @param columns �����С�
     * @since T3 V1.1
     */
    public void setData(Object[][] data, ComplexTableColumn[] columns) {
        setData(createRows(data), columns);
    }

    /**
     * ���ñ���������ݡ�
     * @param rows �����С�
     * @param identifiers �����б�ʶ��
     * @since T3 V1.1
     */
    public void setData(ComplexTableRow[] rows, Object[] identifiers) {
        setData(rows, createColumns(identifiers));
    }

    /**
     * ���ñ���������ݡ�
     * @param rows �����С�
     * @param columns �����С�
     * @since T3 V1.1
     */
    public void setData(ComplexTableRow[] rows, ComplexTableColumn[] columns) {
        setRowsWithoutNotify(rows);
        setColumnsWithoutNotify(columns);

        fireTableStructureChanged();
    }

    /**
     * ��ȡָ��λ�õĵ�Ԫ�����
     * @param row ��ģ��������
     * @param column ��ģ��������
     * @return ��Ԫ��
     * @since T3 V1.1
     */
    public ComplexTableCell getCell(int row, int column) {
        return (ComplexTableCell) getRow(row).get(column);
    }

    /*------------------------------------- TableModel Public Method -------------------------------------*/

    /**
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    public int getColumnCount() {
        return m_columns.size();
    }

    /**
     * @see javax.swing.table.AbstractTableModel#getColumnName(int)
     */
    public String getColumnName(int column) {
        String columnName = getColumn(column).getIdentifier().toString();
        return columnName == null ? super.getColumnName(column) : columnName;
    }

    /**
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    public Class getColumnClass(int column) {
        Class columnType = getColumn(column).m_type;
        return columnType == null ? super.getColumnClass(column) : columnType;
    }

    /**
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public int getRowCount() {
        return m_rows.size();
    }

    /**
     * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
     */
    public boolean isCellEditable(int row, int column) {
        if (getRow(row).isEditable()) {
            if (getColumn(column).isEditable()) {
                return getCell(row, column).isEditable();
            }
        }

        return false;
    }

    /**
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(int row, int column) {
        return getCell(row, column).getValue();
    }

    /**
     * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
     */
    public void setValueAt(Object value, int row, int column) {
        getCell(row, column).setValue(value);
        fireTableCellUpdated(row, column);
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * ���������У�����֪ͨ����
     * @param rows �����С�
     */
    protected void setRowsWithoutNotify(ComplexTableRow[] rows) {
        m_rows.clear();
        m_rows.addAll(Arrays.asList(rows));

        for (int i = 0, size = m_rows.size(); i < size; i++) {
            ((ComplexTableRow) m_rows.get(i)).setModelIndex(i);
        }
    }

    /**
     * ���������У�����֪ͨ����
     * @param columns �����С�
     */
    protected void setColumnsWithoutNotify(ComplexTableColumn[] columns) {
        m_columns.clear();
        m_columns.addAll(Arrays.asList(columns));

        for (int i = 0, size = getColumnCount(); i < size; i++) {
            ComplexTableColumn column = getColumn(i);

            if (!isSortable()) {
                column.setSortState(SORTSTATE_NORMAL);
            }

            column.setModelIndex(i);
        }
    }

    /**
     * ����
     */
    protected void sort() {
        Collections.sort(m_rows, m_sorter);
        fireTableDataChanged();
    }

    /**
     * ��ȡ���������С�
     * @return ���������С�
     */
    protected ComplexTableColumn[] getSortColumns() {
        List sortColumns = new ArrayList();

        for (int i = 0, size = getColumnCount(); i < size; i++) {
            ComplexTableColumn column = getColumn(i);

            if (column.isSortable() && column.getSortState() != SORTSTATE_NORMAL) {
                sortColumns.add(column);
            }
        }

        for (int i = 0, size = sortColumns.size(); i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int iSortIndex = ((ComplexTableColumn) sortColumns.get(i)).getSortIndex();
                int jSortIndex = ((ComplexTableColumn) sortColumns.get(j)).getSortIndex();

                if (iSortIndex > jSortIndex) {
                    Collections.swap(sortColumns, i, j);
                }
            }
        }

        return (ComplexTableColumn[]) sortColumns.toArray(new ComplexTableColumn[sortColumns.size()]);
    }

    /**
     * �����С�
     * @param rowIndex ������
     */
    protected void saveRow(int rowIndex) {
        ComplexTableRow row = getRow(rowIndex);

        if (row.isRemoved()) {
            removeRow(rowIndex);

        } else {
            row.save();
            fireTableRowsUpdated(rowIndex, rowIndex);
        }
    }

    /**
     * �ָ��С�
     * @param rowIndex ������
     */
    protected void restoreRow(int rowIndex) {
        ComplexTableRow row = getRow(rowIndex);

        if (row.isInserted()) {
            m_rows.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex);

        } else {
            row.restore();
            fireTableRowsUpdated(rowIndex, rowIndex);
        }
    }

    /**
     * ��ȡģ������������
     * @param viewRows ��ͼ����������
     * @return ģ������������
     */
    protected int[] getModelRows(int[] viewRows) {
        int[] modelRows = new int[viewRows.length];

        for (int i = 0; i < modelRows.length; i++) {
            modelRows[i] = getRow(viewRows[i]).getModelIndex();
        }

        return modelRows;
    }

    /**
     * ��ȡ��ͼ����������
     * @param modelRows ģ������������
     * @return ��ͼ����������
     */
    protected int[] getViewRows(int[] modelRows) {
        int[] viewRows = new int[modelRows.length];

        for (int i = 0; i < viewRows.length; i++) {
            for (int j = 0, size = m_rows.size(); j < size; j++) {
                if (((ComplexTableRow) m_rows.get(j)).getModelIndex() == modelRows[i]) {
                    viewRows[i] = j;
                    break;
                }
            }
        }

        return viewRows;
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
