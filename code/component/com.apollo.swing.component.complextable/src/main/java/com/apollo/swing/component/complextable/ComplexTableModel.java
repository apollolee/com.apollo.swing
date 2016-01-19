/*
 * 此代码创建于 2008-4-23 上午09:40:33。
 */
package com.apollo.swing.component.complextable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * <p>文件名称：ComplexTableModel.java</p>
 * <p>类型描述：综合表模型类，封装了综合表模型数据和操作，这个类是综合表逻辑功能的核心。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-4-23</p>
 * <p>修改记录：</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * @version 1.0
 * @author 李镇
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
     * 创建行集，工具方法。
     * @param data 行数据数据集。
     * @return 行集。
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
     * 创建列集，工具方法。
     * @param identifiers 列标识对象集。
     * @return 列集。
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
     * 所有行。
     */
    protected List m_rows = new ArrayList();

    /**
     * 所有列。
     */
    protected List m_columns = new ArrayList();

    /**
     * 标识是否可排序。
     */
    protected volatile boolean m_sortable = true;

    /**
     * 排序比较器。
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
     * 构造方法。
     * @param columns 所有列对象。
     * @since T3 V1.1
     */
    public ComplexTableModel(ComplexTableColumn[] columns) {
        this(new ComplexTableRow[0], columns);
    }

    /**
     * 构造方法。
     * @param data 所有数据，二位数据表结构。
     * @param identifiers 所有列标识对象。
     * @since T3 V1.1
     */
    public ComplexTableModel(Object[][] data, Object[] identifiers) {
        this(createRows(data), identifiers);
    }

    /**
     * 构造方法。
     * @param rows 所有行对象。
     * @param identifiers 所有列标识对象。
     * @since T3 V1.1
     */
    public ComplexTableModel(ComplexTableRow[] rows, Object[] identifiers) {
        this(rows, createColumns(identifiers));
    }

    /**
     * 构造方法。
     * @param rows 所有行对象。
     * @param columns 所有列对象。
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
     * 获取字段 sortable 的值，即标识整个综合表是否允许排序，缺省是允许。
     * @return 字段 sortable 的值。
     * @since T3 V1.1
     */
    public boolean isSortable() {
        return m_sortable;
    }

    /**
     * 设置字段 sortable 的值，即标识整个综合表是否允许排序，缺省是允许，注意：此方法调用后需要使用TComplexTable.repaintAll()方法以刷新表头。
     * @param sortable 字段 sortable 的值。
     * @since T3 V1.1
     */
    public void setSortable(boolean sortable) {
        m_sortable = sortable;

        if (!m_sortable) {
            cancelSort();
        }
    }

    /**
     * 取消排序，即取消某一个列的排序。
     * @param column 列。
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
     * 取消全部列的排序。
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
     * 排序某一列。
     * @param column 列。
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
     * 获取行对象。
     * @param row 行视图索引。
     * @return 行。
     * @since T3 V1.1
     */
    public ComplexTableRow getRow(int row) {
        return (ComplexTableRow) m_rows.get(row);
    }

    /**
     * 获取行视图索引。
     * @param row 行。
     * @return 行索引。
     * @since T3 V1.1
     */
    public int getRowIndex(ComplexTableRow row) {
        return m_rows.indexOf(row);
    }

    /**
     * 获取所有已添加但还没有加入模型中的行，即界面上行头有加号标识的行。
     * @return 所有已添加的行。
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
     * 获取所有已移除但还么有从模型中剔除的行，即界面上行头有减号标识的行。
     * @return 所有已移除的行。
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
     * 获取所有已修改但还没有保存进模型的行，即界面上行头有星号标识的行。
     * @return 所有已修改的行。
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
     * 获取所有行对象，顺序是视图上的当前顺序。
     * @return 所有行。
     * @since T3 V1.1
     */
    public ComplexTableRow[] getRows() {
        return (ComplexTableRow[]) m_rows.toArray(new ComplexTableRow[m_rows.size()]);
    }

    /**
     * 添加一行数据到表格尾部。
     * @param row 行。
     * @since T3 V1.1
     */
    public void addRow(ComplexTableRow row) {
        addRow(getRowCount(), row);
    }

    /**
     * 添加一行数据到指定行索引位置。
     * @param index 索引。
     * @param row 行。
     * @since T3 V1.1
     */
    public void addRow(int index, ComplexTableRow row) {
        addRows(index, new ComplexTableRow[] { row });
    }

    /**
     * 添加一行数据到指定行索引位置。
     * @param index 视图索引。
     * @param modelIndex 模型索引。
     * @param row 行。
     * @since T3 V1.1
     */
    public void addRow(int index, int modelIndex, ComplexTableRow row) {
        addRows(index, modelIndex, new ComplexTableRow[] { row });
    }

    /**
     * 添加一个行数据集和到表格尾部。
     * @param rows 行集。
     * @since T3 V1.1
     */
    public void addRows(ComplexTableRow[] rows) {
        addRows(getRowCount(), rows);
    }

    /**
     * 添加一个行数据集和到指定行索引位置。
     * @param index 索引。
     * @param rows 行集。
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
     * 添加一个行数据集和到指定行索引位置。
     * @param index 视图索引。
     * @param modelIndex 模型索引。
     * @param rows 行集。
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
     * 移除指定行数据。
     * @param row 行。
     * @return 移除的行。
     * @since T3 V1.1
     */
    public ComplexTableRow removeRow(ComplexTableRow row) {
        return removeRow(getRowIndex(row));
    }

    /**
     * 移除指定索引位置的行数据。
     * @param index 索引。
     * @return 移除的行。
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
     * 移除指定的行数据集合。
     * @param rows 行集。
     * @since T3 V1.1
     */
    public void removeRows(ComplexTableRow[] rows) {
        for (int i = 0; i < rows.length; i++) {
            removeRow(rows[i]);
        }
    }

    /**
     * 移除指定索引位置的行数据集和。
     * @param indexes 行索引集。
     * @since T3 V1.1
     */
    public void removeRows(int[] indexes) {
        Arrays.sort(indexes);

        for (int i = indexes.length - 1; i >= 0; i--) {
            removeRow(indexes[i]);
        }
    }

    /**
     * 移除所有行数据。
     * @since T3 V1.1
     */
    public void removeAllRow() {
        m_rows.clear();
        fireTableDataChanged();
    }

    /**
     * 设置所有行数据。
     * @param data 所有数据。
     * @since T3 V1.1
     */
    public void setRows(Object[][] data) {
        setRows(createRows(data));
    }

    /**
     * 设置所有行数据。
     * @param rows 所有行。
     * @since T3 V1.1
     */
    public void setRows(ComplexTableRow[] rows) {
        setRowsWithoutNotify(rows);
        fireTableDataChanged();
    }

    /**
     * 获取列对象。
     * @param column 列模型索引。
     * @return 列。
     * @since T3 V1.1
     */
    public ComplexTableColumn getColumn(int column) {
        return (ComplexTableColumn) m_columns.get(column);
    }

    /**
     * 获取列模型索引。
     * @param column 列对象。
     * @return 列索引。
     * @since T3 V1.1
     */
    public int getColumnIndex(ComplexTableColumn column) {
        return m_columns.indexOf(column);
    }

    /**
     * 获取所有列对象。
     * @return 所有列。
     * @since T3 V1.1
     */
    public ComplexTableColumn[] getColumns() {
        return (ComplexTableColumn[]) m_columns.toArray(new ComplexTableColumn[m_columns.size()]);
    }

    /**
     * 添加列对象到表格列的尾部。
     * @param column 列。
     * @since T3 V1.1
     */
    public void addColumn(ComplexTableColumn column) {
        addColumn(getColumnCount(), column);
    }

    /**
     * 添加列对象到指定列模型索引的位置。
     * @param index 模型。
     * @param column 列。
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
     * 移除指定的列对象。
     * @param column 列。
     * @return 移除的列。
     * @since T3 V1.1
     */
    public ComplexTableColumn removeColumn(ComplexTableColumn column) {
        return removeColumn(getColumnIndex(column));
    }

    /**
     * 移除指定列模型索引位置的列对象。
     * @param index 索引。
     * @return 移除的列。
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
     * 设置所有列对象。
     * @param identifiers 所有列标识。
     * @since T3 V1.1
     */
    public void setColumns(Object[] identifiers) {
        setColumns(createColumns(identifiers));
    }

    /**
     * 设置所有列对象。
     * @param columns 所有列。
     * @since T3 V1.1
     */
    public void setColumns(ComplexTableColumn[] columns) {
        setColumnsWithoutNotify(columns);
        fireTableStructureChanged();
    }

    /**
     * 设置表格所有数据。
     * @param data 所有数据。
     * @param identifiers 所有列标识。
     * @since T3 V1.1
     */
    public void setData(Object[][] data, Object[] identifiers) {
        setData(createRows(data), identifiers);
    }

    /**
     * 设置表格所有数据。
     * @param data 所有数据。
     * @param columns 所有列。
     * @since T3 V1.1
     */
    public void setData(Object[][] data, ComplexTableColumn[] columns) {
        setData(createRows(data), columns);
    }

    /**
     * 设置表格所有数据。
     * @param rows 所有行。
     * @param identifiers 所有列标识。
     * @since T3 V1.1
     */
    public void setData(ComplexTableRow[] rows, Object[] identifiers) {
        setData(rows, createColumns(identifiers));
    }

    /**
     * 设置表格所有数据。
     * @param rows 所有行。
     * @param columns 所有列。
     * @since T3 V1.1
     */
    public void setData(ComplexTableRow[] rows, ComplexTableColumn[] columns) {
        setRowsWithoutNotify(rows);
        setColumnsWithoutNotify(columns);

        fireTableStructureChanged();
    }

    /**
     * 获取指定位置的单元格对象。
     * @param row 行模型索引。
     * @param column 列模型索引。
     * @return 单元格。
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
     * 设置所有行（不发通知）。
     * @param rows 所有行。
     */
    protected void setRowsWithoutNotify(ComplexTableRow[] rows) {
        m_rows.clear();
        m_rows.addAll(Arrays.asList(rows));

        for (int i = 0, size = m_rows.size(); i < size; i++) {
            ((ComplexTableRow) m_rows.get(i)).setModelIndex(i);
        }
    }

    /**
     * 设置所有列（不发通知）。
     * @param columns 所有列。
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
     * 排序。
     */
    protected void sort() {
        Collections.sort(m_rows, m_sorter);
        fireTableDataChanged();
    }

    /**
     * 获取所有排序列。
     * @return 所有排序列。
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
     * 保存行。
     * @param rowIndex 索引。
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
     * 恢复行。
     * @param rowIndex 索引。
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
     * 获取模型行索引集。
     * @param viewRows 视图行索引集。
     * @return 模型行索引集。
     */
    protected int[] getModelRows(int[] viewRows) {
        int[] modelRows = new int[viewRows.length];

        for (int i = 0; i < modelRows.length; i++) {
            modelRows[i] = getRow(viewRows[i]).getModelIndex();
        }

        return modelRows;
    }

    /**
     * 获取视图行索引集。
     * @param modelRows 模型行索引集。
     * @return 视图行索引集。
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
