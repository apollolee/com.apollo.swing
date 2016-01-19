/*
 * �˴��봴���� 2007-7-8 ����02:46:20
 */
package com.apollo.swing.component.table;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import com.apollo.base.util.BaseUtilities;
import com.apollo.base.util.IBaseConstants;
import com.apollo.swing.component.complextable.ComplexTable;

/**
 * <p>�ļ����ƣ�TableUtilities.java</p>
 * <p>������������񹤾��ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-4-24</p>
 * <p>�޸ļ�¼��</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * @version 1.0
 * @author ����
 */
@SuppressWarnings("all")
public class TableUtilities implements IBaseConstants, ITableConstants {

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
     * ���ۺϱ�����ı�������λ��
     * @param text �ı���
     * @param table �ۺϱ�
     * @param parent �������
     */
    public static void searchText(String text, ComplexTable table, Component parent) {
        String oldText = text;
        text = text == null ? null : text.trim().toLowerCase();

        if (text == null || "".equals(text)) {
            JOptionPane.showMessageDialog(parent, I18N_SearchTextIsNull, I18N_INFO_ERROR, JOptionPane.ERROR_MESSAGE);

        } else {
            if (!searchText(text, table)) {
                String desc = "<html><font color=\"#ff0000\">" + oldText + "</font>" + I18N_IsNotFound;
                JOptionPane.showMessageDialog(parent, desc, I18N_Info, JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * ���ۺϱ�����ı�������λ��
     * @param text �ı���
     * @param table �ۺϱ�
     * @return ��ʶ�Ƿ�������ָ�����ı���
     */
    public static boolean searchText(String text, ComplexTable table) {
        return searchText(text, table, true);
    }

    /**
     * ���ۺϱ�����ı�������λ��
     * @param text �ı���
     * @param table �ۺϱ�
     * @param isCircle ��ʶ�Ƿ�ѭ�����������Ϊtrue���ʾȫ���������������Ϊfalse���ʾֻ���ӵ�ǰѡ���п�ʼ���µ������С�
     * @return ��ʶ�Ƿ�������ָ�����ı���
     */
    public static boolean searchText(String text, ComplexTable table, boolean isCircle) {
        text = text == null ? null : text.trim().toLowerCase();

        if (text == null || table == null) {
            return false;
        }

        int[] selectedRows = table.getSelectedRows();
        int rowCount = table.getRowCount();
        JTable rowHeader = table.getRowHeader();
        JTable body = table.getBody();

        if (selectedRows.length == 0) {
            for (int i = 0; i < rowCount; i++) {
                if (searchText(text, rowHeader, i, 0) || searchText(text, body, i, 0)) {
                    return true;
                }
            }

        } else {
            int row = selectedRows[selectedRows.length - 1];

            int[] rowHeaderSelectedColumns = rowHeader.getSelectedColumns();

            if (rowHeaderSelectedColumns.length == 0) {
                int[] bodySelectedColumns = body.getSelectedColumns();
                int column = bodySelectedColumns.length == 0 ? 0 : (bodySelectedColumns[bodySelectedColumns.length - 1] + 1);
                if (searchText(text, body, row, column)) {
                    return true;
                }

            } else {
                int column = rowHeaderSelectedColumns[rowHeaderSelectedColumns.length - 1] + 1;
                if (searchText(text, rowHeader, row, column)) {
                    return true;
                }

                if (searchText(text, body, row, 0)) {
                    return true;
                }
            }

            for (int i = row + 1; i < rowCount; i++) {
                if (searchText(text, rowHeader, i, 0) || searchText(text, body, i, 0)) {
                    return true;
                }
            }

            if (isCircle) {
                for (int i = 0; i <= row; i++) {
                    if (searchText(text, rowHeader, i, 0) || searchText(text, body, i, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * �Ա������ı�������λ����������к�������ԣ�ע�⣺�˷���δ���в������ˣ�����ʹ�ã���
     * @param text �ı���
     * @param table ���
     * @param row �С�
     * @param column �С�
     * @return ��ʶ�Ƿ�������ָ�����ı���
     */
    public static boolean searchText(String text, JTable table, int row, int column) {
        for (int i = column, size = table.getColumnCount(); i < size; i++) {
            if (table instanceof TableRowHeader && ColumnView.isRowIdColumn(table.getColumnModel().getColumn(i))) {
                continue;
            }

            Object value = table.getValueAt(row, i);
            if (value != null && value.toString().trim().toLowerCase().indexOf(text) > -1) {
                table.changeSelection(row, i, false, false);
                table.requestFocus();
                return true;
            }
        }
        return false;
    }

    /**
     * ��ȡ���ָ���е���ѡ��ȡ�
     * @param table ���
     * @param columnIndex ָ���е���ͼ������
     * @return ���ָ���е���ѡ��ȡ�
     */
    public static int getPreferredWidth(JTable table, int columnIndex) {
        return getPreferredWidth(table, columnIndex, 0, table.getRowCount());
    }

    /**
     * ��ȡ���ָ���е���ѡ��ȡ�
     * @param table ���
     * @param columnIndex ָ���е���ͼ������
     * @param rowStart ָ���е���ʼλ�á�
     * @param rowSize ָ��������
     * @return ���ָ���е���ѡ��ȡ�
     */
    public static int getPreferredWidth(JTable table, int columnIndex, int rowStart, int rowSize) {
        JTableHeader header = table.getTableHeader();
        TableColumnModel columnModel = header.getColumnModel();
        TableColumn column = columnModel.getColumn(columnIndex);

        TableCellRenderer renderer = column.getHeaderRenderer();
        if (renderer == null) {
            renderer = header.getDefaultRenderer();
        }

        int width = renderer.getTableCellRendererComponent(table, column.getHeaderValue(), false, false, 0, columnIndex).getPreferredSize().width;

        for (int i = rowStart, size = rowStart + rowSize; i < size; i++) {
            renderer = table.getCellRenderer(i, columnIndex);
            width = Math.max(width,
                             renderer.getTableCellRendererComponent(table, table.getValueAt(i, columnIndex), false, false, i, columnIndex)
                                     .getPreferredSize().width);
        }

        return width + columnModel.getColumnMargin();
    }

    /**
     * ���ñ�����ѡ��ȡ�
     * @param table ���
     */
    public static void setPreferredWidth(JTable table) {
        int allWidth = 0;

        for (int i = 0, size = table.getColumnModel().getColumnCount(); i < size; i++) {
            allWidth += setPreferredWidth(table, i);
        }

        Dimension psvs = table.getPreferredScrollableViewportSize();
        psvs.width = allWidth;

        table.setPreferredScrollableViewportSize(psvs);
    }

    /**
     * ���ñ��ָ���е���ѡ��ȡ�
     * @param table ���
     * @param columnIndex ָ���е���ͼ������
     * @return ���ָ���е���ѡ��ȡ�
     */
    public static int setPreferredWidth(JTable table, int columnIndex) {
        return setPreferredWidth(table, columnIndex, 0, table.getRowCount());
    }

    /**
     * ���ñ��ָ���е���ѡ��ȡ�
     * @param table ���
     * @param columnIndex ָ���е���ͼ������
     * @param rowStart ָ���е���ʼλ�á�
     * @param rowSize ָ��������
     * @return ���ָ���е���ѡ��ȡ�
     */
    public static int setPreferredWidth(JTable table, int columnIndex, int rowStart, int rowSize) {
        TableColumnModel columnModel = table.getColumnModel();
        TableColumn column = columnModel.getColumn(columnIndex);

        if (table instanceof TableRowHeader && ColumnView.isRowIdColumn(column)) {
            return column.getPreferredWidth();
        }

        int width = getPreferredWidth(table, columnIndex, rowStart, rowSize) + columnModel.getColumnMargin();
        columnModel.getColumn(columnIndex).setPreferredWidth(width);

        return width;
    }

    /**
     * ���±����ѡ�����ӿڵĴ�С��
     * @param table ���
     */
    public static void updateViewportSize(JTable table) {
        Dimension psvs = table.getPreferredScrollableViewportSize();
        psvs.width = table.getTableHeader().getPreferredSize().width;

        table.setPreferredScrollableViewportSize(psvs);
    }

    /**
     * ��ӱ��ģ�ͼ�������
     * @param table ���
     * @param listener ��������
     */
    public static void addTableModelListener(JTable table, TableModelListener listener) {
        TableModel model = table.getModel();

        model.removeTableModelListener(table);
        model.addTableModelListener(listener);
        model.addTableModelListener(table);
    }

    /**
     * ��ӱ����ѡ���������
     * @param table ���
     * @param listener ��������
     */
    public static void addListSelectionListener(JTable table, ListSelectionListener listener) {
        ListSelectionModel sm = table.getSelectionModel();

        sm.removeListSelectionListener(table);
        sm.addListSelectionListener(listener);
        sm.addListSelectionListener(table);
    }

    /**
     * ѡ����������С�
     * @param table ���
     */
    public static void selectAllRow(JTable table) {
        table.setRowSelectionInterval(0, table.getRowCount() - 1);
    }

    /**
     * ѡ����������С�
     * @param table ���
     */
    public static void selectAllColumn(JTable table) {
        table.setColumnSelectionInterval(0, table.getColumnCount() - 1);
    }

    /**
     * ������ģ�͡�
     * @param columnModel ��ģ�͡�
     */
    public static void clear(TableColumnModel columnModel) {
        for (int i = columnModel.getColumnCount() - 1; i >= 0; i--) {
            columnModel.removeColumn(columnModel.getColumn(i));
        }
    }

    /**
     * �ж����Ƿ�����ģ���С�
     * @param tableColumnModel ��ģ�͡�
     * @param column �С�
     * @return ��ʶ���Ƿ�����ģ���С�
     */
    public static boolean contains(TableColumnModel tableColumnModel, TableColumn column) {
        for (int i = 0, size = tableColumnModel.getColumnCount(); i < size; i++) {
            if (column == tableColumnModel.getColumn(i)) {
                return true;
            }
        }

        return false;
    }

    /**
     * ����ģ�ͻ�ȡ�����б�ʶ��
     * @param columnModel ��ģ�͡�
     * @return �����б�ʶ��
     */
    public static List getColumnIdentifiers(TableColumnModel columnModel) {
        List columns = new ArrayList();

        for (int i = 0, size = columnModel.getColumnCount(); i < size; i++) {
            columns.add(columnModel.getColumn(i).getIdentifier());
        }

        return columns;
    }

    /**
     * ��ȡ�С�
     * @param columnModel ��ģ�͡�
     * @param modelIndex ģ��������
     * @return �С�
     */
    public static TableColumn getColumn(TableColumnModel columnModel, int modelIndex) {
        for (int i = 0, size = columnModel.getColumnCount(); i < size; i++) {
            TableColumn column = columnModel.getColumn(i);

            if (modelIndex == column.getModelIndex()) {
                return column;
            }
        }

        return null;
    }

    /**
     * ����ģ�ͻ�ȡ�����С�
     * @param columnModel ��ģ�͡�
     * @return �����С�
     */
    public static List getColumns(TableColumnModel columnModel) {
        List columns = new ArrayList();

        for (int i = 0, size = columnModel.getColumnCount(); i < size; i++) {
            columns.add(columnModel.getColumn(i));
        }

        return columns;
    }

    /**
     * ���������е���ģ�͡�
     * @param columnModel ��ģ�͡�
     * @param columns �����С�
     */
    public static void setColumns(TableColumnModel columnModel, List columns) {
        clear(columnModel);

        for (int i = 0, size = columns.size(); i < size; i++) {
            columnModel.addColumn((TableColumn) columns.get(i));
        }
    }

    /**
     * ��ֹ�����ơ�
     * @param table ���
     * @param disabledColumnViewIndex ��ֹ�е���ͼ������
     */
    public static void disableColumnLeftMove(final JTable table, final int disabledColumnViewIndex) {
        final JTableHeader th = table.getTableHeader();

        th.addMouseMotionListener(new MouseMotionAdapter() {

            /**
             * @see java.awt.event.MouseMotionAdapter#mouseDragged(java.awt.event.MouseEvent)
             */
            public void mouseDragged(MouseEvent evt) {
                if (th.getDraggedDistance() == 0) { //û�����϶��ľ���ʱ����Ҫ���´���
                    return;
                }

                if (th.getDraggedColumn() == null) { //�϶���Ϊ��
                    return;
                }

                if (th.getDraggedDistance() >= 0) { //�����϶�
                    return;
                }

                int draggedColumnViewIndex = table.convertColumnIndexToView(th.getDraggedColumn().getModelIndex());
                if (draggedColumnViewIndex == disabledColumnViewIndex) {
                    disableColumnDrag(th, evt);
                }
            }

        });
    }

    /**
     * ��ֹ�����ơ�
     * @param table ���
     * @param disabledColumnViewIndex ��ֹ�е���ͼ������
     */
    public static void disableColumnRightMove(final JTable table, final int disabledColumnViewIndex) {
        final JTableHeader th = table.getTableHeader();

        th.addMouseMotionListener(new MouseMotionAdapter() {

            /**
             * @see java.awt.event.MouseMotionAdapter#mouseDragged(java.awt.event.MouseEvent)
             */
            public void mouseDragged(MouseEvent evt) {
                if (th.getDraggedDistance() == 0) { //û�����϶��ľ���ʱ����Ҫ���´���
                    return;
                }

                if (th.getDraggedColumn() == null) { //�϶���Ϊ��
                    return;
                }

                if (th.getDraggedDistance() <= 0) { //�����϶�
                    return;
                }

                int draggedColumnViewIndex = table.convertColumnIndexToView(th.getDraggedColumn().getModelIndex());
                if (draggedColumnViewIndex == disabledColumnViewIndex) {
                    disableColumnDrag(th, evt);
                }
            }

        });
    }

    /**
     * ������ģ��������ȡ�����б����
     * @param table ���
     * @param modelIndex ��ģ��������
     * @return �б����
     */
    public static TableCustom getTableCustom(JTable table, int modelIndex) {
        List customs = getTableCustoms(table);

        if (customs == null) {
            return null;
        }

        for (int i = 0, size = customs.size(); i < size; i++) {
            TableCustom custom = (TableCustom) customs.get(i);

            if (modelIndex == custom.m_column.getModelIndex()) {
                return custom;
            }
        }

        return null;
    }

    /**
     * ��ȡ���������б����
     * @param table ���
     * @return ���������б����
     */
    public static List getTableCustoms(JTable table) {
        return (List) table.getClientProperty(CP_TABLECUSTOM);
    }

    /**
     * ��ȡ����ȱʡ�����б����
     * @param table ���
     * @return ����ȱʡ�����б����
     */
    public static List<TableCustom> getTableDefaultCustoms(JTable table) {
        return (List<TableCustom>) table.getClientProperty(CP_TableDefaultCustom);
    }

    /**
     * �������������б����
     * @param table ���
     * @return ���������б����
     */
    public static List createTableCustoms(JTable table) {
        List customs = new ArrayList();

        TableColumnModel cm = table.getColumnModel();
        for (int i = 0, size = cm.getColumnCount(); i < size; i++) {
            customs.add(new TableCustom(cm.getColumn(i), true));
        }

        return customs;
    }

    public static List<TableCustom> createTableDefaultCustoms(JTable table) {
        List<TableCustom> customs = createTableCustoms(table);

        for (TableCustom custom : customs) {
            custom.m_width = BigInteger.valueOf(custom.m_column.getPreferredWidth());
        }

        return customs;
    }

    /**
     * ���ñ��������б����
     * @param table ���
     * @param customs ���������б����
     */
    public static void setTableCustoms(JTable table, List customs) {
        table.putClientProperty(CP_TABLECUSTOM, customs);
    }

    /**
     * ���ñ���ȱʡ�����б����
     * @param table ���
     * @param customs ����ȱʡ�����б����
     */
    public static void setTableDefaultCustoms(JTable table, List<TableCustom> customs) {
        table.putClientProperty(CP_TableDefaultCustom, customs);
    }

    public static List<TableCustom> getCloneCustoms(List<TableCustom> customs) {
        List<TableCustom> cloneCustoms = new ArrayList<TableCustom>();

        for (TableCustom custom : customs) {
            cloneCustoms.add(new TableCustom(custom));
        }
        return cloneCustoms;
    }

    public static void restoreDefaultCustoms(JTable table) {
        setTableCustoms(table, getCloneCustoms(getTableDefaultCustoms(table)));
    }

    public static void applyDefaultCustoms(JTable table) {
        applyCustoms(table, getCloneCustoms(getTableDefaultCustoms(table)));
    }

    public static void applyCustoms(JTable table, List<TableCustom> customs) {
        if (table.isEditing()) {
            table.removeEditor();
        }

        setTableCustoms(table, customs);

        TableColumnModel cm = table.getColumnModel();
        clear(cm);

        for (TableCustom custom : customs) {
            custom.syncWidthToColumn();

            if (custom.m_visible.booleanValue()) {
                cm.addColumn(custom.m_column);
            }
        }
    }

    /**
     * �Զ�ͬ�����������б����
     * @param table ���
     */
    public static void autoSyncCustoms(final JTable table) {
        table.getColumnModel().addColumnModelListener(new TableColumnModelListener() {

            /**
             * @see javax.swing.event.TableColumnModelListener#columnMarginChanged(javax.swing.event.ChangeEvent)
             */
            public void columnMarginChanged(ChangeEvent evt) {
                /* �ݲ���ʵ�� */
            }

            /**
             * @see javax.swing.event.TableColumnModelListener#columnSelectionChanged(javax.swing.event.ListSelectionEvent)
             */
            public void columnSelectionChanged(ListSelectionEvent evt) {
                /* �ݲ���ʵ�� */
            }

            /**
             * @see javax.swing.event.TableColumnModelListener#columnAdded(javax.swing.event.TableColumnModelEvent)
             */
            public void columnAdded(TableColumnModelEvent evt) {
                /* �ݲ���ʵ�� */
            }

            /**
             * @see javax.swing.event.TableColumnModelListener#columnRemoved(javax.swing.event.TableColumnModelEvent)
             */
            public void columnRemoved(TableColumnModelEvent evt) {
                /* �ݲ���ʵ�� */
            }

            /**
             * @see javax.swing.event.TableColumnModelListener#columnMoved(javax.swing.event.TableColumnModelEvent)
             */
            public void columnMoved(TableColumnModelEvent evt) {
                List customs = getTableCustoms(table);
                int fi = evt.getFromIndex();
                int ti = evt.getToIndex();

                if (fi == ti || customs == null) {
                    return;
                }

                int fmi = table.convertColumnIndexToModel(Math.min(fi, ti));
                int tmi = table.convertColumnIndexToModel(Math.max(fi, ti));

                TableCustom ftc = getTableCustom(table, fmi);
                TableCustom ttc = getTableCustom(table, tmi);

                if (ftc == null || ttc == null) {
                    return;
                }

                customs.remove(ftc);
                customs.add(customs.indexOf(ttc), ftc);
            }

        });

    }

    /**
     * �Զ�����ָ�����������б�����˷���Ӧ����pack()��setVisible(true)֮ǰ���á�
     * @param key Ŀ����ؼ��֣�����Ϊnull��""������Ψһ��ʶ�����б�����Ϣ��Ψһ������籣֤��
     * @param targetTable Ŀ���񣬲���Ϊnull��
     * @throws IllegalArgumentException ��������Ƿ�����׳����쳣��
     * @deprecated �Զ������б��Ƶı�����ֻ�е�JVM�˳�ʱ�������ͷ��ڴ棬�����뾡���ֶ���������б��ơ�
     * @see #loadCustom(String, JTable)
     * @see #loadCustom(String, JTable, File)
     * @see #saveCustom(String, JTable)
     * @see #saveCustom(String, JTable, File)
     */
    public static synchronized void autoManageCustoms(final String key, final JTable targetTable) {
        /* Ŀ���ؼ��ַǿ��ж� */
        if (key == null || "".equals(key)) {
            throw new IllegalArgumentException("Target table key is null or \"\" !");
        }

        /* Ŀ���ǿ��ж� */
        if (targetTable == null) {
            throw new IllegalArgumentException("Target table is null !");
        }

        /* ������ġ����ȡ��ı�ʱ����load��save���� */
        targetTable.addPropertyChangeListener("ancestor", new PropertyChangeListener() {

            /**
             * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
             */
            public void propertyChange(PropertyChangeEvent evt) {
                if (targetTable.isDisplayable()) { //���ȸı����������ʾ����������Window��pack()��setVisible(true)������
                    loadCustom(key, targetTable);

                } else { //���ȸı������������ʾ����������Window��dispose()��������dispose()�����޷���֤�˳�ʱһ�����ã����Բ���Ҫ�����jvm�˳�ʱ�Ĵ���
                    saveCustom(key, targetTable);
                }
            }

        });

        /* ��jvm�˳�ʱҲsaveһ�Σ�ȷ���ܹ��������б�����Ϣ */
        Runtime.getRuntime().addShutdownHook(new Thread() {

            /**
             * @see java.lang.Thread#run()
             */
            public void run() {
                saveCustom(key, targetTable);
            }

        });
    }

    /**
     * ��ָ�������б�����Ϣ���뵽ȱʡ���б����ļ��У��̰߳�ȫ��
     * @param key Ŀ����ؼ��֣�����Ϊnull��""������Ψһ��ʶ�����б�����Ϣ��Ψһ������籣֤��
     * @param targetTable Ŀ���񣬲���Ϊnull��
     */
    public static synchronized void saveCustom(String key, JTable targetTable) {
        /* ��ȡȱʡ���б����ļ� */
        URL url = BaseUtilities.getClassLocation(TableUtilities.class, true, true);
        File customFile = new File(url.getFile() + "/" + DEFAULT_CUSTOMFILENAME);

        /* ���б�����Ϣ�����б����ļ��� */
        saveCustom(key, targetTable, customFile);
    }

    /**
     * ��ָ�������б�����Ϣ���뵽ָ�����б����ļ��У��̰߳�ȫ��
     * @param key Ŀ����ؼ��֣�����Ϊnull��""������Ψһ��ʶ�����б�����Ϣ��Ψһ������籣֤��
     * @param targetTable Ŀ���񣬲���Ϊnull��
     * @param customFile ָ�����б����ļ�������Ϊnull�����ָ�����ļ������ڻᴴ��һ�������������ʧ����ȷ���˲����ǷǷ��ġ�
     * @throws IllegalArgumentException ��������Ƿ�����׳����쳣��
     */
    public static synchronized void saveCustom(String key, JTable targetTable, File customFile) {
        /* Ŀ���ؼ��ַǿ��ж� */
        if (key == null || "".equals(key)) {
            throw new IllegalArgumentException("Target table key is null or \"\" !");
        }

        /* Ŀ���ǿ��ж� */
        if (targetTable == null) {
            throw new IllegalArgumentException("Target table is null !");
        }

        /* �б����ļ��ǿ��ж� */
        if (customFile == null) {
            throw new IllegalArgumentException("Custom file is null !");
        }

        /* ��customFile�ж�ȡ�б����ĵ�ģ�ͣ������ȡʧ���򴴽�һ���µ��ĵ�ģ�� */
        SAXBuilder builder = new SAXBuilder();
        Document doc;
        Element tables;
        try {
            doc = builder.build(customFile);
            tables = doc.getRootElement(); //���ڵ�

        } catch (Exception ex) {
            tables = new Element("tables");
            doc = new Document(tables);
        }

        /* ����ָ��targetTableKey��table������Ҳ����򴴽�һ���µ� */
        Element table = getTable(key, tables);
        if (table == null) {
            table = new Element("table");
            table.setAttribute("key", key);
            tables.addContent(table);
        }

        /* ��ȡ�µ�����Ϣ�����滻��ԭ�����ϵ�����Ϣ */
        table.removeChildren("column"); //ɾ��ԭ�����ڵ��ϵ�����Ϣ
        Element[] columns = getColumns(targetTable); //��ȡ�µ�����Ϣ
        for (int i = 0; i < columns.length; i++) { //������Ӹ�������Ϣ�ӽڵ�
            table.addContent(columns[i]);
        }

        /* ����Ϣд�뵽ָ����xml�ļ��� */
        try {
            new XMLOutputter().output(doc, new FileOutputStream(customFile));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * ��ȱʡ���б����ļ�������ָ���ı���̰߳�ȫ��
     * @param key Ŀ����ؼ��֣�����Ϊnull��""������Ψһ��ʶ�����б�����Ϣ��Ψһ������籣֤��
     * @param targetTable Ŀ���񣬲���Ϊnull��
     * @return ��ʶ�����Ƿ�ɹ���
     */
    public static synchronized boolean loadCustom(String key, JTable targetTable) {
        /* ��ȡȱʡ���б����ļ� */
        URL url = BaseUtilities.getClassLocation(TableUtilities.class, true, true);
        File customFile = new File(url.getFile() + "/" + DEFAULT_CUSTOMFILENAME);

        /* ���б�����Ϣ�����б����ļ��� */
        return loadCustom(key, targetTable, customFile);
    }

    /**
     * ��ָ�����б����ļ�������ָ���ı���̰߳�ȫ��
     * @param key Ŀ����ؼ��֣�����Ϊnull��""������Ψһ��ʶ�����б�����Ϣ��Ψһ������籣֤��
     * @param targetTable Ŀ���񣬲���Ϊnull��
     * @param customFile ָ�����б����ļ�������Ϊnull���ļ������ڡ�
     * @return ��ʶ�����Ƿ�ɹ���
     * @throws IllegalArgumentException ��������Ƿ�����׳����쳣��
     */
    public static synchronized boolean loadCustom(String key, JTable targetTable, File customFile) {
        /* Ŀ���ؼ��ַǿ��ж� */
        if (key == null || "".equals(key)) {
            throw new IllegalArgumentException("Target table key is null or \"\" !");
        }

        /* Ŀ���ǿ��ж� */
        if (targetTable == null) {
            throw new IllegalArgumentException("Target table is null !");
        }

        /* �б����ļ��ǿ��ж� */
        if (customFile == null) {
            throw new IllegalArgumentException("Custom file is null !");
        }

        /* �б����ļ��������ж� */
        if (!customFile.exists()) {
            System.out.println("Custom file is not exist !");
            return false;
        }

        /*
         * �б����������ж�
         * TODO ��һ���������б��Ƶı���ظ�������û������ģ��������ڻ�û������󣬶��������syncViewIndex����Ҳ�Դ����㷨�ϵ�����
         */
        if (getTableCustoms(targetTable) != null) {
            System.out.println("Custom is loaded !");
            return false;
        }

        /* ��customFile�ж�ȡ�б���ģ�� */
        SAXBuilder builder = new SAXBuilder();
        Document doc;
        try {
            doc = builder.build(customFile);

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        Element tables = doc.getRootElement(); //���ڵ�

        /* ��������ҵ���Ӧ��key��ȡ��ȥ��Ϣ���õ�targetTable��ȥ */
        Element table = getTable(key, tables);
        if (table == null) {
            System.out.println("Target table custom is not found !");
            return false;
        }

        /* ������������ */
        List columns = table.getChildren("column");

        /* �����������ݲ��Ϸ������� */
        if (columns.size() != getColumnCount(targetTable)) {
            System.out.println("Target table custom is illegal !");
            return false;
        }

        /* ��������Ϣ�б��Ѷ��������������õ�targetTable��ȥ */
        for (int i = 0, size = columns.size(); i < size; i++) {
            setColumn((Element) columns.get(i), targetTable);
        }

        /* ������ͷ��ѡ�����ӿڵĴ�С */
        TableRowHeader rowHeader = getRowHeader(targetTable);
        if (rowHeader != null) {
            updateViewportSize(rowHeader);
        }

        /* ���±�����ѡ�����ӿڵĴ�С */
        updateViewportSize(getBody(targetTable));

        return true;
    }

    /**
     * ֹͣ�༭��
     * @param table ���
     */
    public static void stopEditing(JTable table) {
        if (table.isEditing()) {
            table.removeEditor();
        }
    }

    /*------------------------------------- Protected Static Method -------------------------------------*/

    /**
     * ͨ��key����ȡ�б��ƵĹ��ʻ���Ϣ��
     * @param i18nKey ���ʻ���Ϣ�ؼ��֡�
     * @return ���ʻ���Ϣ��
     */
    protected static String getI18nMessage(String i18nKey) {
        return BaseUtilities.getI18nMessage("com.apollo.swing.component.table.resource.Table", i18nKey);
    }

    /**
     * ��ȡͼ�ꡣ
     * @param iconFilename ͼ���ļ�����
     * @return ͼ�ꡣ
     */
    protected static Icon getIcon(String iconFilename) {
        return BaseUtilities.getIcon(TableUtilities.class, "com/apollo/swing/component/table/resource/icon/", iconFilename);
    }

    /*------------------------------------- Friendly Static Method -------------------------------------*/

    /* Add Friendly Static Method */

    /*------------------------------------- Private Static Method -------------------------------------*/

    /**
     * ��ֹ�е��϶��Ĳ�����
     * @param th ��ͷ��
     * @param evt �����ק�¼���
     */
    private static void disableColumnDrag(JTableHeader th, MouseEvent evt) {
        th.setDraggedColumn(null); //û�д����ã������ϻ���ּ�ѹ������������UI������������ʱ�����Ķ���

        /* ���쵱ǰ�����̧����¼������͸���Ӧ��UI�ദ��� */
        MouseEvent releasedEvt = new MouseEvent((Component) evt.getSource(),
                                                MouseEvent.MOUSE_RELEASED,
                                                System.currentTimeMillis(),
                                                evt.getModifiers(),
                                                evt.getX(),
                                                evt.getY(),
                                                1,
                                                false,
                                                evt.getButton());
        /* ���쵱ǰ����갴�µ��¼������͸���Ӧ��UI�ദ��� */
        MouseEvent pressedEvt = new MouseEvent((Component) evt.getSource(),
                                               MouseEvent.MOUSE_PRESSED,
                                               System.currentTimeMillis(),
                                               evt.getModifiers(),
                                               evt.getX(),
                                               evt.getY(),
                                               1,
                                               false,
                                               evt.getButton());
        /* ���쵱ǰ������ƶ����¼������͸���Ӧ��UI�ദ��� */
        MouseEvent movedEvt = new MouseEvent((Component) evt.getSource(),
                                             MouseEvent.MOUSE_MOVED,
                                             System.currentTimeMillis(),
                                             evt.getModifiers(),
                                             evt.getX(),
                                             evt.getY(),
                                             1,
                                             false,
                                             evt.getButton());

        /* ���������¼���AWT�¼������У���UI��ע�������¼�������ȥ���� */
        EventQueue eq = Toolkit.getDefaultToolkit().getSystemEventQueue();
        eq.postEvent(releasedEvt);
        eq.postEvent(pressedEvt);
        eq.postEvent(movedEvt);
    }

    /**
     * ����ָ����tableKey��tableCustom�������table��
     * @param key ���ؼ��֡�
     * @param tables �������Ԫ�ء�
     * @return ���Ԫ�ء�
     */
    private static Element getTable(String key, Element tables) {
        List children = tables.getChildren("table");

        for (int i = 0, size = children.size(); i < size; i++) {
            Element table = (Element) children.get(i);

            if (key.equalsIgnoreCase(table.getAttributeValue("key"))) {
                return table;
            }
        }

        return null; //û���ҵ��ͷ���null
    }

    /**
     * ��ȡָ��Ŀ�����ȫ������Ϣ��
     * @param targetTable Ŀ����
     * @return ָ��Ŀ�����ȫ������Ϣ��
     */
    private static Element[] getColumns(JTable targetTable) {
        List columns = new ArrayList();

        TableRowHeader rowHeader = getRowHeader(targetTable);
        JTable body = getBody(targetTable);

        if (rowHeader != null) {
            List customs = getTableCustoms(rowHeader);

            if (customs == null) {
                customs = createTableCustoms(rowHeader);
            }

            for (int i = 0, size = customs.size(); i < size; i++) {
                TableCustom custom = (TableCustom) customs.get(i);

                Element column = new Element("column");
                column.setAttribute("fixed", Boolean.TRUE.toString());
                column.setAttribute("visible", custom.m_visible.toString());
                column.setAttribute("view-index", String.valueOf(i));
                column.setAttribute("model-index", String.valueOf(custom.m_column.getModelIndex()));
                column.setAttribute("width", String.valueOf(custom.m_column.getPreferredWidth()));

                columns.add(column);
            }
        }

        List customs = getTableCustoms(body);

        if (customs == null) {
            customs = createTableCustoms(body);
        }

        for (int i = 0, size = customs.size(); i < size; i++) {
            TableCustom custom = (TableCustom) customs.get(i);

            Element column = new Element("column");
            column.setAttribute("fixed", Boolean.FALSE.toString());
            column.setAttribute("visible", custom.m_visible.toString());
            column.setAttribute("view-index", String.valueOf(i));
            column.setAttribute("model-index", String.valueOf(custom.m_column.getModelIndex()));
            column.setAttribute("width", String.valueOf(custom.m_column.getPreferredWidth()));

            columns.add(column);
        }

        return (Element[]) columns.toArray(new Element[columns.size()]);
    }

    /**
     * ����һ��ָ��������Ϣ��Ŀ����
     * @param column ����Ϣ��
     * @param targetTable Ŀ����
     */
    private static void setColumn(Element column, JTable targetTable) {
        boolean fixed = new Boolean(column.getAttributeValue("fixed")).booleanValue();
        boolean visible = new Boolean(column.getAttributeValue("visible")).booleanValue();
        int viewIndex = Integer.parseInt(column.getAttributeValue("view-index"));
        int modelIndex = Integer.parseInt(column.getAttributeValue("model-index"));
        int width = Integer.parseInt(column.getAttributeValue("width"));

        TableRowHeader rowHeader = getRowHeader(targetTable);
        JTable body = getBody(targetTable);

        if (fixed) {
            ColumnView cv = rowHeader.getColumnView();
            TableColumn tc = viewIndex == 0 ? cv.getColumn(0) : cv.getSyncColumn(modelIndex);

            if (!visible) {
                cv.removeColumn(tc);
            }

            tc.setPreferredWidth(width);

            addCustom(rowHeader, new TableCustom(tc, Boolean.valueOf(visible)));

            if (viewIndex != 0) {
                TableColumnModel cm = body.getColumnModel();
                cm.removeColumn(getColumn(cm, modelIndex));
            }

        } else {
            TableColumnModel cm = body.getColumnModel();
            TableColumn tc = getColumn(cm, modelIndex);
            syncViewIndex(body, tc);

            if (!visible) {
                body.getColumnModel().removeColumn(tc);
            }

            tc.setPreferredWidth(width);

            addCustom(body, new TableCustom(tc, Boolean.valueOf(visible)));
        }
    }

    /**
     * ��ȡ������
     * @param targetTable Ŀ����
     * @return ������
     */
    private static int getColumnCount(JTable targetTable) {
        int columnCount = getBody(targetTable).getColumnCount();

        TableRowHeader rowHeader = getRowHeader(targetTable);
        if (rowHeader != null) {
            columnCount += rowHeader.getColumnCount();
        }

        return columnCount;
    }

    /**
     * ��ȡ��ͷ��
     * @param targetTable Ŀ����
     * @return ��ͷ��
     */
    private static TableRowHeader getRowHeader(JTable targetTable) {
        return targetTable instanceof TableRowHeader ? (TableRowHeader) targetTable : null;
    }

    /**
     * ��ȡ���塣
     * @param targetTable ��ͷ��
     * @return ���塣
     */
    private static JTable getBody(JTable targetTable) {
        return targetTable instanceof TableRowHeader ? ((TableRowHeader) targetTable).m_body : targetTable;
    }

    /**
     * ����б�����Ϣ��
     * @param table ���
     * @param custom �б�����Ϣ��
     */
    private static void addCustom(JTable table, TableCustom custom) {
        List customs = getTableCustoms(table);

        if (customs == null) {
            customs = new ArrayList();
            setTableCustoms(table, customs);
        }

        customs.add(custom);
    }

    /**
     * ͬ����ͼ������
     * @param table ���
     * @param column �С�
     */
    private static void syncViewIndex(JTable table, TableColumn column) {
        int viewIndex = 0;
        TableColumnModel cm = table.getColumnModel();
        List customs = getTableCustoms(table);

        if (customs != null) {
            for (int i = customs.size() - 1; i >= 0; i--) {
                TableCustom custom = (TableCustom) customs.get(i);

                if (custom.m_visible.booleanValue()) {
                    viewIndex = cm.getColumnIndex(custom.m_column.getIdentifier()) + 1;
                    break;
                }
            }
        }

        /* �����ƶ�����֪�ɼ��еĺ��棬ע�⣺����Ҫ��customs������ӵģ����Բ�������һ���б���������õı�����ٴ������б�����Ϣ */
        cm.moveColumn(cm.getColumnIndex(column.getIdentifier()), viewIndex);
    }

    /*------------------------------------- Public Field -------------------------------------*/

    /* Add Public Field */

    /*------------------------------------- Protected Field -------------------------------------*/

    /* Add Protected Field */

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /* Add Public Constructor */

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /**
     * ���췽����
     */
    private TableUtilities() {
        /* ��ֹ���ⲿʵ�������� */
    }

    /*------------------------------------- Public Method -------------------------------------*/

    /* Add Public Method */

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
