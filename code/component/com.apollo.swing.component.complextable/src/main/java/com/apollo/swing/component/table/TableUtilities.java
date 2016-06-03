/*
 * 此代码创建于 2007-7-8 下午02:46:20
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
 * <p>文件名称：TableUtilities.java</p>
 * <p>类型描述：表格工具类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-4-24</p>
 * <p>修改记录：</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * @version 1.0
 * @author 李镇
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
     * 对综合表进行文本搜索定位。
     * @param text 文本。
     * @param table 综合表。
     * @param parent 父组件。
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
     * 对综合表进行文本搜索定位。
     * @param text 文本。
     * @param table 综合表。
     * @return 标识是否搜索到指定的文本。
     */
    public static boolean searchText(String text, ComplexTable table) {
        return searchText(text, table, true);
    }

    /**
     * 对综合表进行文本搜索定位。
     * @param text 文本。
     * @param table 综合表。
     * @param isCircle 标识是否循环搜索，如果为true则表示全表都会搜索到，如果为false则表示只会查从当前选中行开始以下的所有行。
     * @return 标识是否搜索到指定的文本。
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
     * 对表格进行文本搜索定位，如果遇到行号列则忽略（注意：此方法未进行参数过滤，谨慎使用）。
     * @param text 文本。
     * @param table 表格。
     * @param row 行。
     * @param column 列。
     * @return 标识是否搜索到指定的文本。
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
     * 获取表格指定列的首选宽度。
     * @param table 表格。
     * @param columnIndex 指定列的视图索引。
     * @return 表格指定列的首选宽度。
     */
    public static int getPreferredWidth(JTable table, int columnIndex) {
        return getPreferredWidth(table, columnIndex, 0, table.getRowCount());
    }

    /**
     * 获取表格指定列的首选宽度。
     * @param table 表格。
     * @param columnIndex 指定列的视图索引。
     * @param rowStart 指定行的启始位置。
     * @param rowSize 指定行数。
     * @return 表格指定列的首选宽度。
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
     * 设置表格的首选宽度。
     * @param table 表格。
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
     * 设置表格指定列的首选宽度。
     * @param table 表格。
     * @param columnIndex 指定列的视图索引。
     * @return 表格指定列的首选宽度。
     */
    public static int setPreferredWidth(JTable table, int columnIndex) {
        return setPreferredWidth(table, columnIndex, 0, table.getRowCount());
    }

    /**
     * 设置表格指定列的首选宽度。
     * @param table 表格。
     * @param columnIndex 指定列的视图索引。
     * @param rowStart 指定行的启始位置。
     * @param rowSize 指定行数。
     * @return 表格指定列的首选宽度。
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
     * 更新表格首选滚动视口的大小。
     * @param table 表格。
     */
    public static void updateViewportSize(JTable table) {
        Dimension psvs = table.getPreferredScrollableViewportSize();
        psvs.width = table.getTableHeader().getPreferredSize().width;

        table.setPreferredScrollableViewportSize(psvs);
    }

    /**
     * 添加表格模型监听器。
     * @param table 表格。
     * @param listener 监听器。
     */
    public static void addTableModelListener(JTable table, TableModelListener listener) {
        TableModel model = table.getModel();

        model.removeTableModelListener(table);
        model.addTableModelListener(listener);
        model.addTableModelListener(table);
    }

    /**
     * 添加表格行选择监听器。
     * @param table 表格。
     * @param listener 监听器。
     */
    public static void addListSelectionListener(JTable table, ListSelectionListener listener) {
        ListSelectionModel sm = table.getSelectionModel();

        sm.removeListSelectionListener(table);
        sm.addListSelectionListener(listener);
        sm.addListSelectionListener(table);
    }

    /**
     * 选择表格的所有行。
     * @param table 表格。
     */
    public static void selectAllRow(JTable table) {
        table.setRowSelectionInterval(0, table.getRowCount() - 1);
    }

    /**
     * 选择表格的素有列。
     * @param table 表格。
     */
    public static void selectAllColumn(JTable table) {
        table.setColumnSelectionInterval(0, table.getColumnCount() - 1);
    }

    /**
     * 清理列模型。
     * @param columnModel 列模型。
     */
    public static void clear(TableColumnModel columnModel) {
        for (int i = columnModel.getColumnCount() - 1; i >= 0; i--) {
            columnModel.removeColumn(columnModel.getColumn(i));
        }
    }

    /**
     * 判断列是否在列模型中。
     * @param tableColumnModel 列模型。
     * @param column 列。
     * @return 标识列是否在列模型中。
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
     * 从列模型获取所有列标识。
     * @param columnModel 列模型。
     * @return 所有列标识。
     */
    public static List getColumnIdentifiers(TableColumnModel columnModel) {
        List columns = new ArrayList();

        for (int i = 0, size = columnModel.getColumnCount(); i < size; i++) {
            columns.add(columnModel.getColumn(i).getIdentifier());
        }

        return columns;
    }

    /**
     * 获取列。
     * @param columnModel 列模型。
     * @param modelIndex 模型索引。
     * @return 列。
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
     * 从列模型获取所有列。
     * @param columnModel 列模型。
     * @return 所有列。
     */
    public static List getColumns(TableColumnModel columnModel) {
        List columns = new ArrayList();

        for (int i = 0, size = columnModel.getColumnCount(); i < size; i++) {
            columns.add(columnModel.getColumn(i));
        }

        return columns;
    }

    /**
     * 设置所有列到列模型。
     * @param columnModel 列模型。
     * @param columns 所有列。
     */
    public static void setColumns(TableColumnModel columnModel, List columns) {
        clear(columnModel);

        for (int i = 0, size = columns.size(); i < size; i++) {
            columnModel.addColumn((TableColumn) columns.get(i));
        }
    }

    /**
     * 禁止列左移。
     * @param table 表格。
     * @param disabledColumnViewIndex 禁止列的视图索引。
     */
    public static void disableColumnLeftMove(final JTable table, final int disabledColumnViewIndex) {
        final JTableHeader th = table.getTableHeader();

        th.addMouseMotionListener(new MouseMotionAdapter() {

            /**
             * @see java.awt.event.MouseMotionAdapter#mouseDragged(java.awt.event.MouseEvent)
             */
            public void mouseDragged(MouseEvent evt) {
                if (th.getDraggedDistance() == 0) { //没有列拖动的距离时不需要向下处理
                    return;
                }

                if (th.getDraggedColumn() == null) { //拖动列为空
                    return;
                }

                if (th.getDraggedDistance() >= 0) { //向右拖动
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
     * 禁止列右移。
     * @param table 表格。
     * @param disabledColumnViewIndex 禁止列的视图索引。
     */
    public static void disableColumnRightMove(final JTable table, final int disabledColumnViewIndex) {
        final JTableHeader th = table.getTableHeader();

        th.addMouseMotionListener(new MouseMotionAdapter() {

            /**
             * @see java.awt.event.MouseMotionAdapter#mouseDragged(java.awt.event.MouseEvent)
             */
            public void mouseDragged(MouseEvent evt) {
                if (th.getDraggedDistance() == 0) { //没有列拖动的距离时不需要向下处理
                    return;
                }

                if (th.getDraggedColumn() == null) { //拖动列为空
                    return;
                }

                if (th.getDraggedDistance() <= 0) { //向左拖动
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
     * 根据列模型索引获取表格的列表定制项。
     * @param table 表格。
     * @param modelIndex 列模型索引。
     * @return 列表定制项。
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
     * 获取表格的所有列表定制项。
     * @param table 表格。
     * @return 表格的所有列表定制项。
     */
    public static List getTableCustoms(JTable table) {
        return (List) table.getClientProperty(CP_TABLECUSTOM);
    }

    /**
     * 获取表格的缺省所有列表定制项。
     * @param table 表格。
     * @return 表格的缺省所有列表定制项。
     */
    public static List<TableCustom> getTableDefaultCustoms(JTable table) {
        return (List<TableCustom>) table.getClientProperty(CP_TableDefaultCustom);
    }

    /**
     * 创建表格的所有列表定制项。
     * @param table 表格。
     * @return 表格的所有列表定制项。
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
     * 设置表格的所有列表定制项。
     * @param table 表格。
     * @param customs 表格的所有列表定制项。
     */
    public static void setTableCustoms(JTable table, List customs) {
        table.putClientProperty(CP_TABLECUSTOM, customs);
    }

    /**
     * 设置表格的缺省所有列表定制项。
     * @param table 表格。
     * @param customs 表格的缺省所有列表定制项。
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
     * 自动同步表格的所有列表定制项。
     * @param table 表格。
     */
    public static void autoSyncCustoms(final JTable table) {
        table.getColumnModel().addColumnModelListener(new TableColumnModelListener() {

            /**
             * @see javax.swing.event.TableColumnModelListener#columnMarginChanged(javax.swing.event.ChangeEvent)
             */
            public void columnMarginChanged(ChangeEvent evt) {
                /* 暂不必实现 */
            }

            /**
             * @see javax.swing.event.TableColumnModelListener#columnSelectionChanged(javax.swing.event.ListSelectionEvent)
             */
            public void columnSelectionChanged(ListSelectionEvent evt) {
                /* 暂不必实现 */
            }

            /**
             * @see javax.swing.event.TableColumnModelListener#columnAdded(javax.swing.event.TableColumnModelEvent)
             */
            public void columnAdded(TableColumnModelEvent evt) {
                /* 暂不必实现 */
            }

            /**
             * @see javax.swing.event.TableColumnModelListener#columnRemoved(javax.swing.event.TableColumnModelEvent)
             */
            public void columnRemoved(TableColumnModelEvent evt) {
                /* 暂不必实现 */
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
     * 自动管理指定表格的所有列表定制项，此方法应该在pack()和setVisible(true)之前调用。
     * @param key 目标表格关键字，不能为null或""，用来唯一标识表格的列表定制信息，唯一性由外界保证。
     * @param targetTable 目标表格，不能为null。
     * @throws IllegalArgumentException 如果参数非法则会抛出此异常。
     * @deprecated 自动管理列表定制的表格对象只有到JVM退出时才允许释放内存，所以请尽量手动管理表格的列表定制。
     * @see #loadCustom(String, JTable)
     * @see #loadCustom(String, JTable, File)
     * @see #saveCustom(String, JTable)
     * @see #saveCustom(String, JTable, File)
     */
    public static synchronized void autoManageCustoms(final String key, final JTable targetTable) {
        /* 目标表关键字非空判断 */
        if (key == null || "".equals(key)) {
            throw new IllegalArgumentException("Target table key is null or \"\" !");
        }

        /* 目标表非空判断 */
        if (targetTable == null) {
            throw new IllegalArgumentException("Target table is null !");
        }

        /* 当组件的“祖先”改变时进行load和save动作 */
        targetTable.addPropertyChangeListener("ancestor", new PropertyChangeListener() {

            /**
             * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
             */
            public void propertyChange(PropertyChangeEvent evt) {
                if (targetTable.isDisplayable()) { //祖先改变且组件可显示表明调用了Window的pack()或setVisible(true)方法。
                    loadCustom(key, targetTable);

                } else { //祖先改变且组件不可显示表明调用了Window的dispose()方法，但dispose()方法无法保证退出时一定调用，所以才需要下面的jvm退出时的处理。
                    saveCustom(key, targetTable);
                }
            }

        });

        /* 当jvm退出时也save一次，确保能够保存下列表定制信息 */
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
     * 将指定表格的列表定制信息存入到缺省的列表定制文件中，线程安全。
     * @param key 目标表格关键字，不能为null或""，用来唯一标识表格的列表定制信息，唯一性由外界保证。
     * @param targetTable 目标表格，不能为null。
     */
    public static synchronized void saveCustom(String key, JTable targetTable) {
        /* 获取缺省的列表定制文件 */
        URL url = BaseUtilities.getClassLocation(TableUtilities.class, true, true);
        File customFile = new File(url.getFile() + "/" + DEFAULT_CUSTOMFILENAME);

        /* 把列表定制信息存入列表定制文件中 */
        saveCustom(key, targetTable, customFile);
    }

    /**
     * 将指定表格的列表定制信息存入到指定的列表定制文件中，线程安全。
     * @param key 目标表格关键字，不能为null或""，用来唯一标识表格的列表定制信息，唯一性由外界保证。
     * @param targetTable 目标表格，不能为null。
     * @param customFile 指定的列表定制文件，不能为null，如果指定的文件不存在会创建一个，但如果创建失败则确定此参数是非法的。
     * @throws IllegalArgumentException 如果参数非法则会抛出此异常。
     */
    public static synchronized void saveCustom(String key, JTable targetTable, File customFile) {
        /* 目标表关键字非空判断 */
        if (key == null || "".equals(key)) {
            throw new IllegalArgumentException("Target table key is null or \"\" !");
        }

        /* 目标表非空判断 */
        if (targetTable == null) {
            throw new IllegalArgumentException("Target table is null !");
        }

        /* 列表定制文件非空判断 */
        if (customFile == null) {
            throw new IllegalArgumentException("Custom file is null !");
        }

        /* 从customFile中读取列表定制文档模型，如果读取失败则创建一个新的文档模型 */
        SAXBuilder builder = new SAXBuilder();
        Document doc;
        Element tables;
        try {
            doc = builder.build(customFile);
            tables = doc.getRootElement(); //根节点

        } catch (Exception ex) {
            tables = new Element("tables");
            doc = new Document(tables);
        }

        /* 查找指定targetTableKey的table，如果找不到则创建一个新的 */
        Element table = getTable(key, tables);
        if (table == null) {
            table = new Element("table");
            table.setAttribute("key", key);
            tables.addContent(table);
        }

        /* 获取新的列信息，并替换掉原来的老的列信息 */
        table.removeChildren("column"); //删除原来存在的老的列信息
        Element[] columns = getColumns(targetTable); //获取新的列信息
        for (int i = 0; i < columns.length; i++) { //迭代添加各个列信息子节点
            table.addContent(columns[i]);
        }

        /* 将信息写入到指定的xml文件中 */
        try {
            new XMLOutputter().output(doc, new FileOutputStream(customFile));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 用缺省的列表定制文件来配置指定的表格，线程安全。
     * @param key 目标表格关键字，不能为null或""，用来唯一标识表格的列表定制信息，唯一性由外界保证。
     * @param targetTable 目标表格，不能为null。
     * @return 标识载入是否成功。
     */
    public static synchronized boolean loadCustom(String key, JTable targetTable) {
        /* 获取缺省的列表定制文件 */
        URL url = BaseUtilities.getClassLocation(TableUtilities.class, true, true);
        File customFile = new File(url.getFile() + "/" + DEFAULT_CUSTOMFILENAME);

        /* 把列表定制信息存入列表定制文件中 */
        return loadCustom(key, targetTable, customFile);
    }

    /**
     * 用指定的列表定制文件来配置指定的表格，线程安全。
     * @param key 目标表格关键字，不能为null或""，用来唯一标识表格的列表定制信息，唯一性由外界保证。
     * @param targetTable 目标表格，不能为null。
     * @param customFile 指定的列表定制文件，不能为null或文件不存在。
     * @return 标识载入是否成功。
     * @throws IllegalArgumentException 如果参数非法则会抛出此异常。
     */
    public static synchronized boolean loadCustom(String key, JTable targetTable, File customFile) {
        /* 目标表关键字非空判断 */
        if (key == null || "".equals(key)) {
            throw new IllegalArgumentException("Target table key is null or \"\" !");
        }

        /* 目标表非空判断 */
        if (targetTable == null) {
            throw new IllegalArgumentException("Target table is null !");
        }

        /* 列表定制文件非空判断 */
        if (customFile == null) {
            throw new IllegalArgumentException("Custom file is null !");
        }

        /* 列表定制文件不存在判断 */
        if (!customFile.exists()) {
            System.out.println("Custom file is not exist !");
            return false;
        }

        /*
         * 列表定制已载入判断
         * TODO 对一个已载入列表定制的表格重复载入是没有意义的，至少现在还没这个需求，而且下面的syncViewIndex方法也对此有算法上的限制
         */
        if (getTableCustoms(targetTable) != null) {
            System.out.println("Custom is loaded !");
            return false;
        }

        /* 从customFile中读取列表定制模型 */
        SAXBuilder builder = new SAXBuilder();
        Document doc;
        try {
            doc = builder.build(customFile);

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        Element tables = doc.getRootElement(); //根节点

        /* 遍历表格，找到对应的key，取出去信息设置到targetTable中去 */
        Element table = getTable(key, tables);
        if (table == null) {
            System.out.println("Target table custom is not found !");
            return false;
        }

        /* 读出配置数据 */
        List columns = table.getChildren("column");

        /* 过滤配置数据不合法的情形 */
        if (columns.size() != getColumnCount(targetTable)) {
            System.out.println("Target table custom is illegal !");
            return false;
        }

        /* 遍历列信息列表，把读出配置数据设置到targetTable中去 */
        for (int i = 0, size = columns.size(); i < size; i++) {
            setColumn((Element) columns.get(i), targetTable);
        }

        /* 更新行头首选滚动视口的大小 */
        TableRowHeader rowHeader = getRowHeader(targetTable);
        if (rowHeader != null) {
            updateViewportSize(rowHeader);
        }

        /* 更新表体首选滚动视口的大小 */
        updateViewportSize(getBody(targetTable));

        return true;
    }

    /**
     * 停止编辑。
     * @param table 表格。
     */
    public static void stopEditing(JTable table) {
        if (table.isEditing()) {
            table.removeEditor();
        }
    }

    /*------------------------------------- Protected Static Method -------------------------------------*/

    /**
     * 通过key来获取列表定制的国际化信息。
     * @param i18nKey 国际化信息关键字。
     * @return 国际化信息。
     */
    protected static String getI18nMessage(String i18nKey) {
        return BaseUtilities.getI18nMessage("com.apollo.swing.component.table.resource.Table", i18nKey);
    }

    /**
     * 获取图标。
     * @param iconFilename 图标文件名。
     * @return 图标。
     */
    protected static Icon getIcon(String iconFilename) {
        return BaseUtilities.getIcon(TableUtilities.class, "com/apollo/swing/component/table/resource/icon/", iconFilename);
    }

    /*------------------------------------- Friendly Static Method -------------------------------------*/

    /* Add Friendly Static Method */

    /*------------------------------------- Private Static Method -------------------------------------*/

    /**
     * 禁止列的拖动的操作。
     * @param th 表头。
     * @param evt 鼠标拖拽事件。
     */
    private static void disableColumnDrag(JTableHeader th, MouseEvent evt) {
        th.setDraggedColumn(null); //没有此设置，界面上会出现挤压抖动，估计是UI类里面做处理时发生的动作

        /* 构造当前点鼠标抬起的事件，发送给相应的UI类处理的 */
        MouseEvent releasedEvt = new MouseEvent((Component) evt.getSource(),
                                                MouseEvent.MOUSE_RELEASED,
                                                System.currentTimeMillis(),
                                                evt.getModifiers(),
                                                evt.getX(),
                                                evt.getY(),
                                                1,
                                                false,
                                                evt.getButton());
        /* 构造当前点鼠标按下的事件，发送给相应的UI类处理的 */
        MouseEvent pressedEvt = new MouseEvent((Component) evt.getSource(),
                                               MouseEvent.MOUSE_PRESSED,
                                               System.currentTimeMillis(),
                                               evt.getModifiers(),
                                               evt.getX(),
                                               evt.getY(),
                                               1,
                                               false,
                                               evt.getButton());
        /* 构造当前点鼠标移动的事件，发送给相应的UI类处理的 */
        MouseEvent movedEvt = new MouseEvent((Component) evt.getSource(),
                                             MouseEvent.MOUSE_MOVED,
                                             System.currentTimeMillis(),
                                             evt.getModifiers(),
                                             evt.getX(),
                                             evt.getY(),
                                             1,
                                             false,
                                             evt.getButton());

        /* 发送两个事件到AWT事件队列中，给UI类注册的鼠标事件监听器去处理 */
        EventQueue eq = Toolkit.getDefaultToolkit().getSystemEventQueue();
        eq.postEvent(releasedEvt);
        eq.postEvent(pressedEvt);
        eq.postEvent(movedEvt);
    }

    /**
     * 根据指定的tableKey在tableCustom里面查找table。
     * @param key 表格关键字。
     * @param tables 表格配置元素。
     * @return 表格元素。
     */
    private static Element getTable(String key, Element tables) {
        List children = tables.getChildren("table");

        for (int i = 0, size = children.size(); i < size; i++) {
            Element table = (Element) children.get(i);

            if (key.equalsIgnoreCase(table.getAttributeValue("key"))) {
                return table;
            }
        }

        return null; //没有找到就返回null
    }

    /**
     * 获取指定目标表格的全部列信息。
     * @param targetTable 目标表格。
     * @return 指定目标表格的全部列信息。
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
     * 设置一个指定的列信息到目标表格。
     * @param column 列信息。
     * @param targetTable 目标表格。
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
     * 获取列数。
     * @param targetTable 目标表格。
     * @return 列数。
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
     * 获取行头。
     * @param targetTable 目标表格。
     * @return 行头。
     */
    private static TableRowHeader getRowHeader(JTable targetTable) {
        return targetTable instanceof TableRowHeader ? (TableRowHeader) targetTable : null;
    }

    /**
     * 获取表体。
     * @param targetTable 行头。
     * @return 表体。
     */
    private static JTable getBody(JTable targetTable) {
        return targetTable instanceof TableRowHeader ? ((TableRowHeader) targetTable).m_body : targetTable;
    }

    /**
     * 添加列表定制信息。
     * @param table 表格。
     * @param custom 列表定制信息。
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
     * 同步视图索引。
     * @param table 表格。
     * @param column 列。
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

        /* 把列移动到已知可见列的后面，注意：这里要求customs是逐步添加的，所以不允许在一个列表定制已载入好的表格上再次载入列表定制信息 */
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
     * 构造方法。
     */
    private TableUtilities() {
        /* 禁止从外部实例化此类 */
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
