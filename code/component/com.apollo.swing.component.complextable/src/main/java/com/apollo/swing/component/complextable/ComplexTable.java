/*
 * 此代码创建于 2008-4-23 上午09:39:24。
 */
package com.apollo.swing.component.complextable;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.apollo.base.util.BaseUtilities;
import com.apollo.base.util.IBaseConstants;
import com.apollo.swing.component.IClose;
import com.apollo.swing.component.icon.SortIcon;
import com.apollo.swing.component.table.ColumnView;
import com.apollo.swing.component.table.ITableMenuManager;
import com.apollo.swing.component.table.RowIdRenderer;
import com.apollo.swing.component.table.TableBody;
import com.apollo.swing.component.table.TableCustom;
import com.apollo.swing.component.table.TableRowHeader;
import com.apollo.swing.component.table.TableUtilities;
import com.apollo.swing.component.table.TableView;

/**
 * <p>文件名称：ComplexTable.java</p>
 * <p>类型描述：综合表类，注意：此表的设计是一个模型对于两个视图，一个叫RowHeader，另一个叫Body，而ComplexTable对象本身是并不显示出来。</p>
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
public class ComplexTable extends JTable implements IConstants, IClose, MouseListener, ITableMenuManager, IComplexTableManager {

    /*------------------------------------- Public (Static) Inner Class -------------------------------------*/

    /* Add Public (Static) Inner Class */

    /*------------------------------------- Protected (Static) Inner Class -------------------------------------*/

    /**
     * 当前视图类。
     */
    protected class TCurrentView extends JTable {

        /**
         * 分割索引。
         */
        protected int m_dividedIndex;

        /**
         * 构造方法。
         */
        public TCurrentView() {
            super(ComplexTable.this.getModel());

            TableColumnModel currentColumnModel = new DefaultTableColumnModel();
            TableColumnModel rowHeaderColumnModel = m_rowHeader.getColumnModel();
            for (int i = 1, size = rowHeaderColumnModel.getColumnCount(); i < size; i++) {
                currentColumnModel.addColumn(rowHeaderColumnModel.getColumn(i));
            }
            m_dividedIndex = currentColumnModel.getColumnCount();
            TableColumnModel bodyColumnModel = m_body.getColumnModel();
            for (int i = 0, size = bodyColumnModel.getColumnCount(); i < size; i++) {
                currentColumnModel.addColumn(bodyColumnModel.getColumn(i));
            }
            setColumnModel(currentColumnModel);

            setAutoResizeMode(ComplexTable.this.getAutoResizeMode());
            setRowHeight(ComplexTable.this.getRowHeight());
            setCellSelectionEnabled(false);

            tableHeader.setReorderingAllowed(false);
            tableHeader.setResizingAllowed(false);
        }

        /**
         * @see javax.swing.JTable#isCellEditable(int, int)
         */
        public boolean isCellEditable(int row, int column) {
            return false;
        }

        /**
         * @see javax.swing.JTable#prepareRenderer(javax.swing.table.TableCellRenderer, int, int)
         */
        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
            if (column < m_dividedIndex) {
                return m_rowHeader.prepareRenderer(renderer, row, column + 1);

            } else {
                return m_body.prepareRenderer(renderer, row, column - m_dividedIndex);
            }
        }

        /**
         * @see javax.swing.JTable#getCellRenderer(int, int)
         */
        public TableCellRenderer getCellRenderer(int row, int column) {
            if (column < m_dividedIndex) {
                return m_rowHeader.getCellRenderer(row, column + 1);

            } else {
                return m_body.getCellRenderer(row, column - m_dividedIndex);
            }
        }

    }

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

    /* Add Public Static Method */

    /*------------------------------------- Protected Static Method -------------------------------------*/

    /**
     * 添加动作到菜单。
     * @param menu 菜单。
     * @param action 动作。
     */
    protected static void addAction(JPopupMenu menu, Action action) {
        addMenuItem(menu, new JMenuItem(action));
    }

    /**
     * 添加动作集到菜单。
     * @param menu 菜单。
     * @param actions 动作集。
     */
    protected static void addActions(JPopupMenu menu, Action[] actions) {
        addMenuItems(menu, BaseUtilities.createMenuItems(actions));
    }

    /**
     * 添加菜单项到菜单。
     * @param menu 菜单。
     * @param menuItem 菜单项。
     */
    protected static void addMenuItem(JPopupMenu menu, JMenuItem menuItem) {
        addMenuItems(menu, new JMenuItem[] { menuItem });
    }

    /**
     * 添加菜单项集到菜单。
     * @param menu 菜单。
     * @param menuItems 菜单项集。
     */
    protected static void addMenuItems(JPopupMenu menu, JMenuItem[] menuItems) {
        if (menuItems.length > 0) {
            if (menu.getSubElements().length > 0) {
                menu.addSeparator();
            }

            for (int i = 0; i < menuItems.length; i++) {
                menu.add(menuItems[i]);
            }
        }
    }

    /*------------------------------------- Friendly Static Method -------------------------------------*/

    /* Add Friendly Static Method */

    /*------------------------------------- Private Static Method -------------------------------------*/

    /* Add Private Static Method */

    /*------------------------------------- Public Field -------------------------------------*/

    /* Add Public Field */

    /*------------------------------------- Protected Field -------------------------------------*/

    /**
     * 标识是否允许插入行。
     */
    protected volatile boolean m_isEnabledInsertRow;

    /**
     * 标识是否允许移除行。
     */
    protected volatile boolean m_isEnabledRemoveRow;

    /**
     * 综合表管理器。
     */
    protected IComplexTableManager m_manager;

    /**
     * 列表定制关键字。
     */
    protected String m_customKey;

    /**
     * 菜单项集。
     */
    protected List m_menuItems = new ArrayList();

    /**
     * 标识是否是阅读模式。
     */
    protected volatile boolean m_isReadMode = false;

    /**
     * 表体。
     */
    protected TableBody m_body;

    /**
     * 行头。
     */
    protected TableRowHeader m_rowHeader;

    /**
     * 选中的表格视图。
     */
    protected TableView m_chooseTableView;

    /**
     * 选中的列索引。
     */
    protected volatile int m_chooseColumnIndex;

    /**
     * 已改变单元格的前景色。
     */
    protected Color m_changedCellForeground = new Color(16741122);

    /**
     * 标识是否允许展现标记。
     */
    protected volatile boolean m_isEnabledShowFlag = true;

    /**
     * 标识是否使能。
     */
    protected volatile boolean m_isEnabled = true;

    /**
     * 行号渲染器。
     */
    protected TableCellRenderer m_rowIdRenderer = new RowIdRenderer() {

        /**
         * @see com.apollo.swing.component.table.RowIdRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
         */
        public Component getTableCellRendererComponent(JTable table,
                                                       Object value,
                                                       boolean isSelected,
                                                       boolean hasFocus,
                                                       int rowIndex,
                                                       int columnIndex) {
            Component rc = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, rowIndex, columnIndex);

            ComplexTableRow row = getRow(rowIndex);

            if (m_isEnabledShowFlag && row.isRemoved()) {
                setIcon(ICON_REMOVED);

            } else if (m_isEnabledShowFlag && row.isInserted()) {
                setIcon(ICON_INSERTED);

            } else if (m_isEnabledShowFlag && row.isChanged()) {
                setIcon(ICON_CHANGED);

            } else {
                setIcon(null);
            }

            setToolTipText("<html>" + getToolTipText() + " (" + row.toString() + ")");

            rc.setEnabled(m_isEnabled);

            return rc;
        }

    };

    /**
     * 取消排序。
     */
    protected Action m_cancelSort = new AbstractAction() {

        {
            BaseUtilities.setAction(this, I18N_INFO_CANCELSORT, 'S');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            TableColumn column = m_chooseTableView.getColumnModel().getColumn(m_chooseColumnIndex);

            if (column instanceof ComplexTableColumn) {
                stopEditing();
                getComplexTableModel().cancelSort((ComplexTableColumn) column);
                repaintAll();
            }
        }

    };

    /**
     * 取消所有排序。
     */
    protected Action m_cancelAllSort = new AbstractAction() {

        {
            BaseUtilities.setAction(this, I18N_INFO_CANCELALLSORT, 'A');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            cancelSort();
        }

    };

    /**
     * 选择列。
     */
    protected Action m_chooseColumn = new AbstractAction() {

        {
            BaseUtilities.setAction(this, I18N_INFO_CHOOSECOLUMN, 'H');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_chooseTableView.setColumnSelectionInterval(m_chooseColumnIndex, m_chooseColumnIndex);
            setRowSelectionInterval(0, getRowCount() - 1);
        }

    };

    /**
     * 插入行到当前行的前面。
     */
    protected Action m_insertRowAtPrevious = new AbstractAction() {

        {
            BaseUtilities.setAction(this, I18N_INFO_INSERTROWATPREVIOUS, 'P');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            insertRow(getSelectedRow());
        }

    };

    /**
     * 插入行到当前行的后面。
     */
    protected Action m_insertRowAtNext = new AbstractAction() {

        {
            BaseUtilities.setAction(this, I18N_INFO_INSERTROWATNEXT, 'N');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            insertRow(getSelectedRow() + 1);
        }

    };

    /**
     * 插入行到首行。
     */
    protected Action m_insertRowAtFirst = new AbstractAction() {

        {
            BaseUtilities.setAction(this, I18N_INFO_INSERTROWATFIRST, 'F');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            insertRow(0);
        }

    };

    /**
     * 插入行到末行。
     */
    protected Action m_insertRowAtLast = new AbstractAction() {

        {
            BaseUtilities.setAction(this, I18N_INFO_INSERTROWATLAST, 'L');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            insertRow(getRowCount());
        }

    };

    /**
     * 移除选中行。
     */
    protected Action m_removeRows = new AbstractAction() {

        {
            BaseUtilities.setAction(this, I18N_INFO_REMOVEROWS, 'R');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            removeRows(getSelectedRows());
        }

    };

    /**
     * 移除所有行。
     */
    protected Action m_removeAllRow = new AbstractAction() {

        {
            BaseUtilities.setAction(this, I18N_INFO_REMOVEALLROW, 'E');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            removeAllRow();
        }

    };

    /**
     * 保存选中行。
     */
    protected Action m_saveRows = new AbstractAction() {

        {
            BaseUtilities.setAction(this, I18N_INFO_SAVEROWS, 'S');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            int[] rows = getSelectedRows();
            Arrays.sort(rows);

            for (int i = rows.length - 1; i >= 0; i--) {
                saveRow(rows[i]);
            }

            repaintAll();
        }

    };

    /**
     * 保存所有行。
     */
    protected Action m_saveAllRow = new AbstractAction() {

        {
            BaseUtilities.setAction(this, I18N_INFO_SAVEALLROW, 'V');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            save();
        }

    };

    /**
     * 恢复选中行。
     */
    protected Action m_restoreRows = new AbstractAction() {

        {
            BaseUtilities.setAction(this, I18N_INFO_RESTOREROWS, 'T');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            int[] rows = getSelectedRows();
            Arrays.sort(rows);

            for (int i = rows.length - 1; i >= 0; i--) {
                restoreRow(rows[i]);
            }

            repaintAll();
        }

    };

    /**
     * 恢复所有行。
     */
    protected Action m_restoreAllRow = new AbstractAction() {

        {
            BaseUtilities.setAction(this, I18N_INFO_RESTOREALLROW, 'O');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            restore();
        }

    };

    /**
     * 刷新。
     */
    protected Action m_refresh = new AbstractAction() {

        {
            BaseUtilities.setAction(this, I18N_INFO_REFRESHROW, 'F', ICON_REFRESH);
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            stopEditing();
            refresh();
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
     * @param model 综合表模型。
     * @since T3 V1.1
     */
    public ComplexTable(ComplexTableModel model) {
        this(model, null);
    }

    /**
     * 构造方法。
     * @param model 综合表模型。
     * @param customKey 列表定制关键字，如果为空就不保存列表定制信息，但还是可以进行列表定制。
     * @since T3 V1.1
     */
    public ComplexTable(ComplexTableModel model, String customKey) {
        this(model, false, false, null, customKey);
    }

    /**
     * 构造方法。
     * @param model 综合表模型。
     * @param isEnabledInsertRow 标识是否允许通过综合表自带的菜单项来插入行。
     * @param isEnabledRemoveRow 标识是否允许通过综合表自带的菜单项来移除行。
     * @param manager 综合表管理器。
     * @since T3 V1.1
     */
    public ComplexTable(ComplexTableModel model, boolean isEnabledInsertRow, boolean isEnabledRemoveRow, IComplexTableManager manager) {
        this(model, isEnabledInsertRow, isEnabledRemoveRow, manager, null);
    }

    /**
     * 构造方法。
     * @param model 综合表模型。
     * @param isEnabledInsertRow 标识是否允许通过综合表自带的菜单项来插入行。
     * @param isEnabledRemoveRow 标识是否允许通过综合表自带的菜单项来移除行。
     * @param manager 综合表管理器。
     * @param customKey 列表定制关键字，如果为空就不保存列表定制信息，但还是可以定制列表，如果你无法保证列信息不变，请设置为null以禁止保存列表定制信息。
     * @since T3 V1.1
     */
    public ComplexTable(ComplexTableModel model,
                        boolean isEnabledInsertRow,
                        boolean isEnabledRemoveRow,
                        IComplexTableManager manager,
                        String customKey) {
        this(model, isEnabledInsertRow, isEnabledRemoveRow, manager, customKey, null);
    }

    /**
     * 构造方法。
     * @param model 综合表模型。
     * @param isEnabledInsertRow 标识是否允许通过综合表自带的菜单项来插入行。
     * @param isEnabledRemoveRow 标识是否允许通过综合表自带的菜单项来移除行。
     * @param manager 综合表管理器。
     * @param customKey 列表定制关键字，如果为空就不保存列表定制信息，但还是可以定制列表，如果你无法保证列信息不变，请设置为null以禁止保存列表定制信息。
     * @param menuItems 表级菜单项集。
     * @since T3 V1.1
     */
    public ComplexTable(ComplexTableModel model,
                        boolean isEnabledInsertRow,
                        boolean isEnabledRemoveRow,
                        IComplexTableManager manager,
                        String customKey,
                        JMenuItem[] menuItems) {
        this(model, isEnabledInsertRow, isEnabledRemoveRow, manager, customKey, menuItems, false);
    }

    /**
     * 构造方法。
     * @param model 综合表模型。
     * @param isEnabledInsertRow 标识是否允许通过综合表自带的菜单项来插入行。
     * @param isEnabledRemoveRow 标识是否允许通过综合表自带的菜单项来移除行。
     * @param manager 综合表管理器。
     * @param customKey 列表定制关键字，如果为空就不保存列表定制信息，但还是可以定制列表，如果你无法保证列信息不变，请设置为null以禁止保存列表定制信息。
     * @param menuItems 表级菜单项集。
     * @param isAutoEnabledRenderer 标识是否自动管理渲染器组件的激活状态，为true则渲染器组件激活状态跟单元格是否可编辑保持联动，为false则不联动。
     * @since T3 V1.1
     */
    public ComplexTable(ComplexTableModel model,
                        boolean isEnabledInsertRow,
                        boolean isEnabledRemoveRow,
                        IComplexTableManager manager,
                        String customKey,
                        JMenuItem[] menuItems,
                        boolean isAutoEnabledRenderer) {
        this(model, isEnabledInsertRow, isEnabledRemoveRow, manager, customKey, menuItems, isAutoEnabledRenderer, false);
    }

    /**
     * 构造方法。
     * @param model 综合表模型。
     * @param isEnabledInsertRow 标识是否允许通过综合表自带的菜单项来插入行。
     * @param isEnabledRemoveRow 标识是否允许通过综合表自带的菜单项来移除行。
     * @param manager 综合表管理器。
     * @param customKey 列表定制关键字，如果为空就不保存列表定制信息，但还是可以定制列表，如果你无法保证列信息不变，请设置为null以禁止保存列表定制信息。
     * @param menuItems 表级菜单项集。
     * @param isAutoEnabledRenderer 标识是否自动管理渲染器组件的激活状态，为true则渲染器组件激活状态跟单元格是否可编辑保持联动，为false则不联动。
     * @param isAutoSetPreferredWidth 标识是否自动设置最佳宽度，这个参数主要作用是在有列表定制的情况下，在列表定制起作用前，先设置一下最佳列宽，使第一次表格使用时能够设置最佳列宽，缺省为false。
     * @since T3 V1.1
     */
    public ComplexTable(ComplexTableModel model,
                        boolean isEnabledInsertRow,
                        boolean isEnabledRemoveRow,
                        IComplexTableManager manager,
                        String customKey,
                        JMenuItem[] menuItems,
                        boolean isAutoEnabledRenderer,
                        boolean isAutoSetPreferredWidth) {
        this(model, isEnabledInsertRow, isEnabledRemoveRow, manager, customKey, menuItems, isAutoEnabledRenderer, false, null, null);
    }

    /**
     * 构造方法。
     * @param model 综合表模型。
     * @param isEnabledInsertRow 标识是否允许通过综合表自带的菜单项来插入行。
     * @param isEnabledRemoveRow 标识是否允许通过综合表自带的菜单项来移除行。
     * @param manager 综合表管理器。
     * @param customKey 列表定制关键字，如果为空就不保存列表定制信息，但还是可以定制列表，如果你无法保证列信息不变，请设置为null以禁止保存列表定制信息。
     * @param menuItems 表级菜单项集。
     * @param isAutoEnabledRenderer 标识是否自动管理渲染器组件的激活状态，为true则渲染器组件激活状态跟单元格是否可编辑保持联动，为false则不联动。
     * @param isAutoSetPreferredWidth 标识是否自动设置最佳宽度，这个参数主要作用是在有列表定制的情况下，在列表定制起作用前，先设置一下最佳列宽，使第一次表格使用时能够设置最佳列宽，缺省为false。
     * @param bodyDefaultCustoms 表体缺省的列表定制信息集。
     * @param rowHeaderDefaultCustoms 行头缺省的列表定制信息集。
     * @since T3 V1.1
     */
    public ComplexTable(ComplexTableModel model,
                        boolean isEnabledInsertRow,
                        boolean isEnabledRemoveRow,
                        IComplexTableManager manager,
                        String customKey,
                        JMenuItem[] menuItems,
                        boolean isAutoEnabledRenderer,
                        boolean isAutoSetPreferredWidth,
                        List<TableCustom> bodyDefaultCustoms,
                        List<TableCustom> rowHeaderDefaultCustoms) {
        super(model);

        m_isEnabledInsertRow = isEnabledInsertRow;
        m_isEnabledRemoveRow = isEnabledRemoveRow;
        m_manager = manager == null ? this : manager;
        m_customKey = customKey;

        if (menuItems != null) {
            m_menuItems.addAll(Arrays.asList(menuItems));
        }

        m_body = new TableBody(this, this, isAutoEnabledRenderer);
        m_rowHeader = new TableRowHeader(this, m_body, true, m_rowIdRenderer, this, isAutoEnabledRenderer, customKey);

        if (isAutoSetPreferredWidth) {
            setPreferredWidth();
        }

        if (bodyDefaultCustoms == null || rowHeaderDefaultCustoms == null) {
            bodyDefaultCustoms = TableUtilities.createTableDefaultCustoms(m_body);
            rowHeaderDefaultCustoms = TableUtilities.createTableDefaultCustoms(m_rowHeader);

        } else {
            rowHeaderDefaultCustoms.addAll(0, TableUtilities.createTableDefaultCustoms(m_rowHeader));
        }

        TableUtilities.setTableDefaultCustoms(m_body, bodyDefaultCustoms);
        TableUtilities.setTableDefaultCustoms(m_rowHeader, rowHeaderDefaultCustoms);

        if (m_customKey == null || "".equals(m_customKey) || !TableUtilities.loadCustom(m_customKey, m_rowHeader)) {
            TableUtilities.applyDefaultCustoms(m_body);
            TableUtilities.applyDefaultCustoms(m_rowHeader);
        }

        m_body.getTableHeader().addMouseListener(this);
        m_rowHeader.getTableHeader().addMouseListener(this);

        setDragEnabled(true);
        setAutoResizeMode(AUTO_RESIZE_OFF);
        setRowHeight(RowHeight_Table_Default);

        if (getRowCount() > 0) {
            setRowSelectionInterval(0, 0);
            selectAllColumn();
        }

        constrain();
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * 设置最佳宽度。
     */
    public void setPreferredWidth() {
        TableUtilities.setPreferredWidth(m_body);
        TableUtilities.setPreferredWidth(m_rowHeader);
    }

    /**
     * 获取表体，即综合表实际显示在滚动面板中央的那个JTable。
     * @return 表体。
     * @since T3 V1.1
     */
    public TableBody getBody() {
        return m_body;
    }

    /**
     * 获取行头，即综合表实际显示在滚动面板RowHeader位置的的那个JTable。
     * @return 行头。
     * @since T3 V1.1
     */
    public TableRowHeader getRowHeader() {
        return m_rowHeader;
    }

    /**
     * 获取当前视图，这个视图仅用于给打印模块使用，不允许执行任何写操作。
     * @return 当前视图。
     * @since T3 V1.1
     */
    public JTable getCurrentView() {
        return new TCurrentView();
    }

    /**
     * 获取菜单项集。
     * @return 菜单项集。
     * @since T3 V1.1
     */
    public JMenuItem[] getMenuItems() {
        return (JMenuItem[]) m_menuItems.toArray(new JMenuItem[m_menuItems.size()]);
    }

    /**
     * 获取字段 isReadMode 的值。
     * @return 字段 isReadMode 的值。
     */
    public boolean isReadMode() {
        return m_isReadMode;
    }

    /**
     * 设置字段 isReadMode 的值。
     * @param isReadMode 字段 isReadMode 的值。
     */
    public void setReadMode(boolean isReadMode) {
        m_isReadMode = isReadMode;
    }

    /**
     * 添加菜单项动作。
     * @param action 菜单项动作。
     * @since T3 V1.1
     */
    public void addAction(Action action) {
        addMenuItem(new JMenuItem(action));
    }

    /**
     * 添加菜单项动作。
     * @param index 索引。
     * @param action 菜单项动作。
     * @since T3 V1.1
     */
    public void addAction(int index, Action action) {
        addMenuItem(index, new JMenuItem(action));
    }

    /**
     * 移除菜单项动作。
     * @param index 索引。
     * @since T3 V1.1
     */
    public void removeAction(int index) {
        m_menuItems.remove(index);
    }

    /**
     * 移除菜单项动作。
     * @param action 菜单项动作。
     * @since T3 V1.1
     */
    public void removeAction(Action action) {
        for (int i = m_menuItems.size() - 1; i >= 0; i--) {
            if (((JMenuItem) m_menuItems.get(i)).getAction() == action) {
                m_menuItems.remove(i);
            }
        }
    }

    /**
     * 判断是否包含指定的菜单项动作。
     * @param action 菜单项动作。
     * @return 标识是否包含指定的菜单项动作。
     * @since T3 V1.1
     */
    public boolean containsAction(Action action) {
        for (int i = m_menuItems.size() - 1; i >= 0; i--) {
            if (((JMenuItem) m_menuItems.get(i)).getAction() == action) {
                return true;
            }
        }
        return false;
    }

    /**
     * 添加菜单项。
     * @param menuItem 菜单项。
     * @since T3 V1.1
     */
    public void addMenuItem(JMenuItem menuItem) {
        m_menuItems.add(menuItem);
    }

    /**
     * 添加菜单项。
     * @param index 索引。
     * @param menuItem 菜单项。
     * @since T3 V1.1
     */
    public void addMenuItem(int index, JMenuItem menuItem) {
        m_menuItems.add(index, menuItem);
    }

    /**
     * 移除菜单项。
     * @param index 索引。
     * @since T3 V1.1
     */
    public void removeMenuItem(int index) {
        m_menuItems.remove(index);
    }

    /**
     * 移除菜单项。
     * @param menuItem 菜单项。
     * @since T3 V1.1
     */
    public void removeMenuItem(JMenuItem menuItem) {
        m_menuItems.remove(menuItem);
    }

    /**
     * 判断是否包含指定的菜单项。
     * @param menuItem 菜单项。
     * @return 标识是否包含指定的菜单项。
     * @since T3 V1.1
     */
    public boolean containsMenuItem(JMenuItem menuItem) {
        return m_menuItems.contains(menuItem);
    }

    /**
     * 获取字段 changedCellForeground 的值，即单元格值改变后，单元格的前景色变成什么颜色，缺省是淡红色。
     * @return 字段 changedCellForeground 的值。
     * @since T3 V1.1
     */
    public Color getChangedCellForeground() {
        return m_changedCellForeground;
    }

    /**
     * 设置字段 changedCellForeground 的值，即单元格值改变后，单元格的前景色变成什么颜色，缺省是淡红色。
     * @param changedCellForeground 字段 changedCellForeground 的值。
     * @since T3 V1.1
     */
    public void setChangedCellForeground(Color changedCellForeground) {
        m_changedCellForeground = changedCellForeground;
    }

    /**
     * 获取字段 isEnabledShowFlag 的值，标识是否允许展现标记，缺省是允许的。
     * @return 字段 isEnabledShowFlag 的值。
     * @since T3 V1.2
     */
    public boolean isEnabledShowFlag() {
        return m_isEnabledShowFlag;
    }

    /**
     * 设置字段 isEnabledShowFlag 的值，标识是否允许展现标记，缺省是允许的。
     * @param isEnabledShowFlag 字段 isEnabledShowFlag 的值。
     * @since T3 V1.2
     */
    public void setEnabledShowFlag(boolean isEnabledShowFlag) {
        m_isEnabledShowFlag = isEnabledShowFlag;
        repaintAll();
    }

    /**
     * 获取综合表模型。
     * @return 综合表模型。
     * @since T3 V1.1
     */
    public ComplexTableModel getComplexTableModel() {
        return (ComplexTableModel) dataModel;
    }

    /**
     * 获取字段 sortable 的值，即判断当前综合表是否允许点击列头排序。
     * @return 字段 sortable 的值。
     * @since T3 V1.1
     */
    public boolean isSortable() {
        return getComplexTableModel().isSortable();
    }

    /**
     * 设置字段 sortable 的值，即设置当前综合表是否允许点击列头排序。
     * @param sortable 字段 sortable 的值。
     * @since T3 V1.1
     */
    public void setSortable(boolean sortable) {
        getComplexTableModel().setSortable(sortable);
        repaintAll();
    }

    /**
     * 获取在指定行视图索引的行对象。
     * @param row 行索引。
     * @return 行。
     * @since T3 V1.1
     */
    public ComplexTableRow getRow(int row) {
        return getComplexTableModel().getRow(row);
    }

    /**
     * 获取单元格对象。
     * @param row 行视图索引。
     * @param column 列视图索引，注意：此处列视图索引由于存在多个视图，有时并不好确定，这是应该使用模型的getCell方法，通过模型索引来获取单元格数据。
     * @return 单元格。
     * @see ComplexTableModel#getCell(int, int)
     * @since T3 V1.1
     */
    public ComplexTableCell getCell(int row, int column) {
        return getComplexTableModel().getCell(row, convertColumnIndexToModel(column));
    }

    /**
     * 保存，即把所有处于修改状态的行数据保存进模型，行头上相应的星号也会消失。
     * @since T3 V1.1
     */
    public void save() {
        for (int i = getRowCount() - 1; i >= 0; i--) {
            saveRow(i);
        }

        repaintAll();
    }

    /**
     * 恢复，即把所有处于修改状态的行数据用模型中的数据恢复过来，行头上相应的星号也会消失。
     * @since T3 V1.1
     */
    public void restore() {
        for (int i = getRowCount() - 1; i >= 0; i--) {
            restoreRow(i);
        }

        repaintAll();
    }

    /**
     * 刷新，即刷新界面上不一致的状态，比如模型层某些值修改了导致列头排序状态不一致，调用这个方法后，列会重新排列一下。
     * @since T3 V1.1
     */
    public void refresh() {
        getComplexTableModel().sort();
    }

    /**
     * 停止编辑。
     * @since T3 V1.1
     */
    public void stopEditing() {
        if (isEditing()) {
            getCellEditor().stopCellEditing();
        }
    }

    /**
     * 取消所有列的排序。
     * @since T3 V1.1
     */
    public void cancelSort() {
        stopEditing();
        getComplexTableModel().cancelSort();
        repaintAll();
    }

    /**
     * <p>插入行数据，插入中会回调综合表管理器的createRow方法进行数据构建。</p>
     * <p>注意：这个插入方法不是直接改模型数据的，所以界面上新增行的行头会有一个加号标识，这个操作可以保存，也可以被回退。</p>
     * @param index 索引。
     * @since T3 V1.1
     */
    public void insertRow(int index) {
        stopEditing();

        ComplexTableRow row = m_manager.createRow(this, index);

        if (row == null) {
            return;
        }

        row.setInserted(true);
        getComplexTableModel().addRow(index, row);

        setRowSelectionInterval(index, index);
        selectAllColumn();
    }

    /**
     * 移除行集，注意：这个删除方法不是直接改模型数据的，所以界面上不会立即删除这些行，这些行的行头上会有一个叉号标识，并且不允许编辑，这个操作可以保存，也可以被回退。
     * @param rows 行集。
     * @since T3 V1.1
     */
    public void removeRows(int[] rows) {
        stopEditing();

        for (int i = rows.length - 1; i >= 0; i--) {
            ComplexTableRow row = getRow(rows[i]);

            if (row.isInserted()) {
                getComplexTableModel().removeRow(rows[i]);

            } else {
                row.setRemoved(true);
            }
        }

        repaintAll();
    }

    /**
     * 移除所有行，注意：这个删除方法不是直接改模型数据的，所以界面上不会立即删除所以行，只是在所有行的行头上会有一个叉号标识，并且不允许编辑，这个操作可以保存，也可以被回退。
     * @since T3 V1.1
     */
    public void removeAllRow() {
        removeRows(BaseUtilities.createIntegers(getRowCount()));
    }

    /**
     * 保存行，即把指定行的行数据保存进模型，行头上相应的星号也会消失。
     * @param index 索引。
     * @since T3 V1.1
     */
    public void saveRow(int index) {
        stopEditing();
        getComplexTableModel().saveRow(index);
    }

    /**
     * 恢复行，即把指定行的行数据用模型中的数据恢复过来，行头上相应的星号也会消失。
     * @param index 索引。
     * @since T3 V1.1
     */
    public void restoreRow(int index) {
        stopEditing();
        getComplexTableModel().restoreRow(index);
    }

    /**
     * 获取所有选中的综合表列。
     * @return 所有选中的综合表列。
     * @since T3 V1.1
     */
    public ComplexTableColumn[] getChosenColumns() {
        List allColumns = new ArrayList();

        ColumnView cv = m_body.getColumnView();
        int[] columns = m_body.getSelectedColumns();

        for (int i = 0; i < columns.length; i++) {
            TableColumn column = cv.getColumn(i);

            if (column instanceof ComplexTableColumn) {
                allColumns.add(column);
            }
        }

        cv = m_rowHeader.getColumnView();
        columns = m_rowHeader.getSelectedColumns();

        for (int i = 0; i < columns.length; i++) {
            TableColumn column = cv.getColumn(i);

            if (column instanceof ComplexTableColumn) {
                allColumns.add(column);
            }
        }

        return (ComplexTableColumn[]) allColumns.toArray(new ComplexTableColumn[allColumns.size()]);
    }

    /**
     * 重绘所有，因为综合表存在Body和RowHeader，所以这个方法就是调用这两个JTable的repaint方法。
     * @since T3 V1.1
     */
    public void repaintAll() {
        m_body.repaint();
        m_body.getTableHeader().repaint();

        m_rowHeader.repaint();
        m_rowHeader.getTableHeader().repaint();
    }

    /**
     * 清理列表定制信息。
     * @since T3 V1.1
     */
    public void clearCustoms() {
        if (m_body != null) {
            TableUtilities.setTableCustoms(m_body, null);
        }

        if (m_rowHeader != null) {
            List customs = TableUtilities.getTableCustoms(m_rowHeader);

            if (customs != null) {
                for (int i = 0, size = customs.size(); i < size; i++) {
                    TableCustom custom = (TableCustom) customs.get(i);
                    TableColumn column = custom.getColumn();

                    if (ColumnView.isRowIdColumn(column) && !custom.isVisible()) {
                        m_rowHeader.addColumn(column);
                        m_rowHeader.moveColumn(m_rowHeader.getColumnCount() - 1, 0);
                        break;
                    }
                }
            }

            TableUtilities.setTableCustoms(m_rowHeader, null);
        }
    }

    /*------------------------------------- JComponent Public Method -------------------------------------*/

    /**
     * @see java.awt.Component#isEnabled()
     */
    public boolean isEnabled() {
        return m_isEnabled;
    }

    /**
     * @see javax.swing.JComponent#setEnabled(boolean)
     */
    public void setEnabled(boolean isEnabled) {
        if (m_isEnabled != isEnabled) {
            m_isEnabled = isEnabled;

            stopEditing();

            m_body.setEnabled(m_isEnabled);
            JTableHeader bodyTableHeader = m_body.getTableHeader();
            bodyTableHeader.setEnabled(m_isEnabled);
            bodyTableHeader.setReorderingAllowed(m_isEnabled);
            bodyTableHeader.setResizingAllowed(m_isEnabled);

            m_rowHeader.setEnabled(m_isEnabled);
            m_rowHeader.getTableHeader().setEnabled(m_isEnabled);

            ComplexTableColumn[] columns = getComplexTableModel().getColumns();
            for (int i = 0; i < columns.length; i++) {
                columns[i].m_isEnabled = m_isEnabled;
            }

            repaintAll();
        }
    }

    /*------------------------------------- JTable Public Method -------------------------------------*/

    /**
     * @see javax.swing.JTable#getCellRenderer(int, int)
     */
    public TableCellRenderer getCellRenderer(int row, int column) {
        TableCellRenderer renderer = getColumnModel().getColumn(column).getCellRenderer();

        if (renderer == null) {
            renderer = getCell(row, column).getRenderer();

            if (renderer == null) {
                renderer = getDefaultRenderer(getColumnClass(column));
            }
        }

        return renderer;
    }

    /**
     * @see javax.swing.JTable#getCellEditor(int, int)
     */
    public TableCellEditor getCellEditor(int row, int column) {
        TableCellEditor editor = getColumnModel().getColumn(column).getCellEditor();

        if (editor == null) {
            editor = getCell(row, column).getEditor();

            if (editor == null) {
                editor = getDefaultEditor(getColumnClass(column));
            }
        }

        return editor;
    }

    /**
     * @see javax.swing.JTable#getCellEditor()
     */
    public TableCellEditor getCellEditor() {
        return m_body.isEditing() ? m_body.getCellEditor() : m_rowHeader.getCellEditor();
    }

    /**
     * @see javax.swing.JTable#isEditing()
     */
    public boolean isEditing() {
        return m_body == null ? false : (m_body.isEditing() || m_rowHeader.isEditing());
    }

    /**
     * @see javax.swing.JTable#removeEditor()
     */
    public void removeEditor() {
        if (m_body.isEditing()) {
            m_body.removeEditor();
        }

        if (m_rowHeader.isEditing()) {
            m_rowHeader.removeEditor();
        }
    }

    /**
     * @see javax.swing.JTable#tableChanged(javax.swing.event.TableModelEvent)
     */
    public void tableChanged(TableModelEvent evt) {
        if (TableModelEvent.HEADER_ROW == evt.getFirstRow()) {
            clearCustoms();
        }

        super.tableChanged(evt);
    }

    /**
     * @see javax.swing.JTable#valueChanged(javax.swing.event.ListSelectionEvent)
     */
    public void valueChanged(ListSelectionEvent evt) {
        super.valueChanged(evt);
        constrain();
    }

    /**
     * @see javax.swing.JTable#createDefaultColumnsFromModel()
     */
    public void createDefaultColumnsFromModel() {
        ComplexTableModel ctm = getComplexTableModel();

        if (ctm != null) {
            TableUtilities.setColumns(columnModel, ctm.m_columns);
        }
    }

    /**
     * @deprecated 此方法是JTable管理列的方法，在综合表中使用会照成问题，请使用综合表模型的相关方法完成相应功能。
     * @see javax.swing.JTable#addColumn(javax.swing.table.TableColumn)
     * @see ComplexTableModel#addColumn(ComplexTableColumn)
     */
    public void addColumn(TableColumn aColumn) {
        super.addColumn(aColumn);
    }

    /**
     * @deprecated 此方法是JTable管理列的方法，在综合表中使用会照成问题，请使用综合表模型的相关方法完成相应功能。
     * @see javax.swing.JTable#removeColumn(javax.swing.table.TableColumn)
     * @see ComplexTableModel#removeColumn(ComplexTableColumn)
     */
    public void removeColumn(TableColumn aColumn) {
        super.removeColumn(aColumn);
    }

    /*------------------------------------- IClose Public Method -------------------------------------*/

    /**
     * @see com.apollo.swing.component.IClose#close()
     */
    public void close() {
        if (m_customKey != null && !"".equals(m_customKey)) {
            TableUtilities.saveCustom(m_customKey, m_rowHeader);
        }
    }

    /*------------------------------------- MouseListener Public Method -------------------------------------*/

    /**
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    public void mouseClicked(MouseEvent evt) {
        if (m_isEnabled && isSortable() && SwingUtilities.isLeftMouseButton(evt)) {
            JTableHeader header = (JTableHeader) evt.getSource();
            int columnIndex = header.columnAtPoint(evt.getPoint());

            if (columnIndex == -1) {
                return;
            }

            if (header.getCursor().getType() == Cursor.E_RESIZE_CURSOR) {
                if (evt.getClickCount() != 2) {
                    return;
                }

                if (evt.getX() - header.getHeaderRect(columnIndex).x < 4) {
                    columnIndex--;
                }

                TableUtilities.setPreferredWidth(header.getTable(), columnIndex);
                return;
            }

            TableColumn column = header.getColumnModel().getColumn(columnIndex);

            if (column instanceof ComplexTableColumn) {
                ComplexTableColumn ctc = (ComplexTableColumn) column;

                if (ctc.isSortable()) {
                    stopEditing();

                    ComplexTableModel ctm = getComplexTableModel();
                    int[] modelRows = ctm.getModelRows(getSelectedRows());

                    getComplexTableModel().sort(ctc);

                    int[] viewRows = ctm.getViewRows(modelRows);
                    for (int i = 0; i < viewRows.length; i++) {
                        /* 当ListSelectionModel.SINGLE_INTERVAL_SELECTION时，界面上可能会由于重新设置的选择不连续而显示错误 */
                        getSelectionModel().addSelectionInterval(viewRows[i], viewRows[i]);
                    }

                    repaintAll();
                }
            }
        }
    }

    /**
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    public void mouseEntered(MouseEvent evt) {
        /* 暂不必实现 */
    }

    /**
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    public void mouseExited(MouseEvent evt) {
        /* 暂不必实现 */
    }

    /**
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    public void mousePressed(MouseEvent evt) {
        /* 暂不必实现 */
    }

    /**
     * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    public void mouseReleased(MouseEvent evt) {
        /* 暂不必实现 */
    }

    /*------------------------------------- ITableMenuManager Public Method -------------------------------------*/

    /**
     * @see com.apollo.swing.component.table.ITableMenuManager#manageCommonMenu(javax.swing.JTable, javax.swing.JPopupMenu)
     */
    public void manageCommonMenu(JTable table, JPopupMenu menu) {
        /*if (menu.getSubElements().length > 0 && (m_isEnabledInsertRow || m_isEnabledRemoveRow)) {
            menu.addSeparator();
        }

        if (m_isEnabledInsertRow) {
            menu.add(createInsertRowMenu());
        }

        if (m_isEnabledRemoveRow) {
            menu.add(createRemoveRowMenu());
        }

        if (m_isEnabledInsertRow || m_isEnabledRemoveRow) {
            menu.add(createSaveRowMenu());
            menu.add(createRestoreRowMenu());
            menu.add(m_refresh);
        }

        addMenuItems(menu, getMenuItems());*/
    }

    /**
     * @see com.apollo.swing.component.table.ITableMenuManager#manageTableHeaderMenu(javax.swing.JTable, javax.swing.JPopupMenu, int)
     */
    public void manageTableHeaderMenu(JTable table, JPopupMenu menu, int columnIndex) {
        m_chooseTableView = (TableView) table;
        m_chooseColumnIndex = columnIndex;

        /* Start */
        addAction(menu, m_rowHeader.m_custom);
        /* End */

        if (getCellSelectionEnabled() && !m_chooseTableView.getColumnView().isRowIdColumn(m_chooseColumnIndex)) {
            addAction(menu, m_chooseColumn);
        }

        TableColumn column = table.getColumnModel().getColumn(columnIndex);

        if (column instanceof ComplexTableColumn) {
            ComplexTableColumn ctc = (ComplexTableColumn) column;

            if (getComplexTableModel().getSortColumns().length > 0) {
                menu.addSeparator();

                if (ctc.isSortable() && SORTSTATE_NORMAL != ctc.getSortState()) {
                    menu.add(m_cancelSort);
                }

                menu.add(m_cancelAllSort);
            }

            addMenuItems(menu, ctc.getMenuItems());
            addMenuItems(menu, ctc.getHeaderMenuItems());
        }
    }

    /**
     * @see com.apollo.swing.component.table.ITableMenuManager#manageTableMenu(javax.swing.JTable, javax.swing.JPopupMenu, int, int)
     */
    public void manageTableMenu(JTable table, JPopupMenu menu, int rowIndex, int columnIndex) {
        if (!getSelectionModel().isSelectedIndex(rowIndex)) {
            setRowSelectionInterval(rowIndex, rowIndex);
        }

        if (!table.getColumnModel().getSelectionModel().isSelectedIndex(columnIndex)) {
            table.setColumnSelectionInterval(columnIndex, columnIndex);
        }

        /* Start */
        if (menu.getSubElements().length > 0 && (m_isEnabledInsertRow || m_isEnabledRemoveRow)) {
            menu.addSeparator();
        }

        if (m_isEnabledInsertRow) {
            menu.add(createInsertRowMenu());
        }

        if (m_isEnabledRemoveRow) {
            menu.add(createRemoveRowMenu());
        }

        if (m_isEnabledInsertRow || m_isEnabledRemoveRow) {
            menu.add(createSaveRowMenu());
            menu.add(createRestoreRowMenu());
            menu.add(m_refresh);
        }

        addMenuItems(menu, getMenuItems());
        /* End */

        ComplexTableRow row = getRow(rowIndex);

        addMenuItems(menu, row.getMenuItems());
        if (table == m_rowHeader && m_rowHeader.getColumnView().isRowIdColumn(columnIndex)) {
            addMenuItems(menu, row.getHeaderMenuItems());

        } else {
            addMenuItems(menu, row.getCellMenuItems());
        }

        TableColumn column = table.getColumnModel().getColumn(columnIndex);

        if (column instanceof ComplexTableColumn) {
            ComplexTableColumn ctc = (ComplexTableColumn) column;

            addMenuItems(menu, ctc.getMenuItems());
            addMenuItems(menu, ctc.getCellMenuItems());
        }

        addMenuItems(menu, row.getCell(table.convertColumnIndexToModel(columnIndex)).getMenuItems());
    }

    /**
     * @see com.apollo.swing.component.table.ITableMenuManager#manageScrollPaneMenu(javax.swing.JTable, javax.swing.JPopupMenu)
     */
    public void manageScrollPaneMenu(JTable table, JPopupMenu menu) {
        /* 暂不必实现 */
    }

    /*------------------------------------- IComplexTableManager Public Method -------------------------------------*/

    /**
     * @see com.apollo.swing.component.complextable.IComplexTableManager#createRow(com.apollo.swing.component.complextable.ComplexTable, int)
     */
    public ComplexTableRow createRow(ComplexTable table, int index) {
        ComplexTableRow row = new ComplexTableRow(getRowCount());

        ComplexTableModel ctm = getComplexTableModel();
        for (int i = 0, size = ctm.getColumnCount(); i < size; i++) {
            ComplexTableCell cell = new ComplexTableCell();
            cell.setEditable(ctm.getColumn(i).isEditable());
            row.add(cell);
        }

        return row;
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * 约束。
     */
    protected void constrain() {
        if (m_insertRowAtPrevious == null) {
            return;
        }

        int rowCount = getSelectedRowCount();

        if (rowCount == 1) {
            m_insertRowAtPrevious.setEnabled(true);
            m_insertRowAtNext.setEnabled(true);

        } else {
            m_insertRowAtPrevious.setEnabled(false);
            m_insertRowAtNext.setEnabled(false);
        }

        if (rowCount > 0) {
            m_removeRows.setEnabled(true);
            m_saveRows.setEnabled(true);
            m_restoreRows.setEnabled(true);

        } else {
            m_removeRows.setEnabled(false);
            m_saveRows.setEnabled(false);
            m_restoreRows.setEnabled(false);
        }
    }

    /**
     * 选择所有列。
     */
    protected void selectAllColumn() {
        m_body.setColumnSelectionInterval(0, m_body.getColumnCount() - 1);
        m_rowHeader.setColumnSelectionInterval(0, m_rowHeader.getColumnCount() - 1);
    }

    /**
     * 创建插入行菜单。
     * @return 插入行菜单。
     */
    protected JMenu createInsertRowMenu() {
        JMenu insertRowMenu = new JMenu(I18N_INFO_INSERTROW);

        insertRowMenu.add(m_insertRowAtPrevious);
        insertRowMenu.add(m_insertRowAtNext);
        insertRowMenu.add(m_insertRowAtFirst);
        insertRowMenu.add(m_insertRowAtLast);

        return insertRowMenu;
    }

    /**
     * 创建移除行菜单。
     * @return 移除行菜单。
     */
    protected JMenu createRemoveRowMenu() {
        JMenu removeRowMenu = new JMenu(I18N_INFO_REMOVEROW);

        removeRowMenu.add(m_removeRows);
        removeRowMenu.add(m_removeAllRow);

        return removeRowMenu;
    }

    /**
     * 创建保存行菜单。
     * @return 保存行菜单。
     */
    protected JMenu createSaveRowMenu() {
        JMenu saveRowMenu = new JMenu(I18N_INFO_SAVEROW);

        saveRowMenu.add(m_saveRows);
        saveRowMenu.add(m_saveAllRow);

        return saveRowMenu;
    }

    /**
     * 创建恢复行菜单。
     * @return 恢复行菜单。
     */
    protected JMenu createRestoreRowMenu() {
        JMenu restoreRowMenu = new JMenu(I18N_INFO_RESTOREROW);

        restoreRowMenu.add(m_restoreRows);
        restoreRowMenu.add(m_restoreAllRow);

        return restoreRowMenu;
    }

    /*------------------------------------- JTable Protected Method -------------------------------------*/

    /**
     * @see javax.swing.JTable#configureEnclosingScrollPane()
     */
    protected void configureEnclosingScrollPane() {
        Container parent = getParent();

        if (parent instanceof JViewport) {
            parent = parent.getParent();

            if (parent instanceof JScrollPane) {
                JScrollPane sp = (JScrollPane) parent;

                sp.setViewportView(m_body);

                sp.setRowHeaderView(m_rowHeader);
                sp.setCorner(JScrollPane.UPPER_LEFT_CORNER, m_rowHeader.getTableHeader());
            }
        }

        super.configureEnclosingScrollPane();
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}

/**
 * <p>文件名称：ComplexTable.java</p>
 * <p>类型描述：综合表常量接口。</p>
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
interface IConstants extends IBaseConstants {

    /*------------------------------------- Public Static Final Field -------------------------------------*/

    /**
     * 排序状态------升序。
     */
    public static final int SORTSTATE_ASCENDING = SortIcon.STATE_ASCENDING;

    /**
     * 排序状态------降序。
     */
    public static final int SORTSTATE_DESCENDING = SortIcon.STATE_DESCENDING;

    /**
     * 排序状态------无序（普通状态）。
     */
    public static final int SORTSTATE_NORMAL = SortIcon.STATE_NORMAL;

    /**
     * 国际化信息------不可编辑。
     */
    public static final String I18N_INFO_INEDITABLE = Utilities.getI18nMessage("ineditable");

    /**
     * 国际化信息------插入行。
     */
    public static final String I18N_INFO_INSERTROW = Utilities.getI18nMessage("insertRow");

    /**
     * 国际化信息------插入到当前行的前面。
     */
    public static final String I18N_INFO_INSERTROWATPREVIOUS = Utilities.getI18nMessage("insertRowAtPrevious");

    /**
     * 国际化信息------插入到当前行的后面。
     */
    public static final String I18N_INFO_INSERTROWATNEXT = Utilities.getI18nMessage("insertRowAtNext");

    /**
     * 国际化信息------插入到首行。
     */
    public static final String I18N_INFO_INSERTROWATFIRST = Utilities.getI18nMessage("insertRowAtFirst");

    /**
     * 国际化信息------插入到末行。
     */
    public static final String I18N_INFO_INSERTROWATLAST = Utilities.getI18nMessage("insertRowAtLast");

    /**
     * 国际化信息------删除行。
     */
    public static final String I18N_INFO_REMOVEROW = Utilities.getI18nMessage("removeRow");

    /**
     * 国际化信息------删除选中行。
     */
    public static final String I18N_INFO_REMOVEROWS = Utilities.getI18nMessage("removeRows");

    /**
     * 国际化信息------删除所有行。
     */
    public static final String I18N_INFO_REMOVEALLROW = Utilities.getI18nMessage("removeAllRow");

    /**
     * 国际化信息------保存行。
     */
    public static final String I18N_INFO_SAVEROW = Utilities.getI18nMessage("saveRow");

    /**
     * 国际化信息------保存选中行。
     */
    public static final String I18N_INFO_SAVEROWS = Utilities.getI18nMessage("saveRows");

    /**
     * 国际化信息------保存所有行。
     */
    public static final String I18N_INFO_SAVEALLROW = Utilities.getI18nMessage("saveAllRow");

    /**
     * 国际化信息------恢复行。
     */
    public static final String I18N_INFO_RESTOREROW = Utilities.getI18nMessage("restoreRow");

    /**
     * 国际化信息------恢复选中行。
     */
    public static final String I18N_INFO_RESTOREROWS = Utilities.getI18nMessage("restoreRows");

    /**
     * 国际化信息------恢复所有行。
     */
    public static final String I18N_INFO_RESTOREALLROW = Utilities.getI18nMessage("restoreAllRow");

    /**
     * 国际化信息------刷新行。
     */
    public static final String I18N_INFO_REFRESHROW = Utilities.getI18nMessage("refreshRow");

    /**
     * 国际化信息------取消排序。
     */
    public static final String I18N_INFO_CANCELSORT = Utilities.getI18nMessage("cancelSort");

    /**
     * 国际化信息------取消所有排序。
     */
    public static final String I18N_INFO_CANCELALLSORT = Utilities.getI18nMessage("cancelAllSort");

    /**
     * 国际化信息------选择列。
     */
    public static final String I18N_INFO_CHOOSECOLUMN = Utilities.getI18nMessage("chooseColumn");

    /**
     * 图标------已插入。
     */
    public static final Icon ICON_INSERTED = Utilities.getIcon("inserted.gif");

    /**
     * 图标------已移除。
     */
    public static final Icon ICON_REMOVED = Utilities.getIcon("removed.gif");

    /**
     * 图标------已修改。
     */
    public static final Icon ICON_CHANGED = Utilities.getIcon("changed.gif");

    /*------------------------------------- Public Abstract Method -------------------------------------*/

    /* Add Public Abstract Method */

}

/**
 * <p>文件名称：ComplexTable.java</p>
 * <p>类型描述：综合表工具类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-5-10</p>
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
class Utilities implements IConstants {

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

    /* Add Public Static Method */

    /*------------------------------------- Protected Static Method -------------------------------------*/

    /**
     * 通过key来获取列表定制的国际化信息。
     * @param i18nKey 国际化信息关键字。
     * @return 国际化信息。
     */
    protected static String getI18nMessage(String i18nKey) {
        return BaseUtilities.getI18nMessage("com.apollo.swing.component.complextable.resource.ComplexTable", i18nKey);
    }

    /**
     * 获取图标。
     * @param iconFilename 图标文件名。
     * @return 图标。
     */
    protected static Icon getIcon(String iconFilename) {
        return BaseUtilities.getIcon(Utilities.class, "com/apollo/swing/component/complextable/resource/icon/", iconFilename);
    }

    /*------------------------------------- Friendly Static Method -------------------------------------*/

    /* Add Friendly Static Method */

    /*------------------------------------- Private Static Method -------------------------------------*/

    /* Add Private Static Method */

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
    private Utilities() {
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
