/*
 * �˴��봴���� 2008-4-23 ����09:39:24��
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
 * <p>�ļ����ƣ�ComplexTable.java</p>
 * <p>�����������ۺϱ��࣬ע�⣺�˱�������һ��ģ�Ͷ���������ͼ��һ����RowHeader����һ����Body����ComplexTable�������ǲ�����ʾ������</p>
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
public class ComplexTable extends JTable implements IConstants, IClose, MouseListener, ITableMenuManager, IComplexTableManager {

    /*------------------------------------- Public (Static) Inner Class -------------------------------------*/

    /* Add Public (Static) Inner Class */

    /*------------------------------------- Protected (Static) Inner Class -------------------------------------*/

    /**
     * ��ǰ��ͼ�ࡣ
     */
    protected class TCurrentView extends JTable {

        /**
         * �ָ�������
         */
        protected int m_dividedIndex;

        /**
         * ���췽����
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
     * ��Ӷ������˵���
     * @param menu �˵���
     * @param action ������
     */
    protected static void addAction(JPopupMenu menu, Action action) {
        addMenuItem(menu, new JMenuItem(action));
    }

    /**
     * ��Ӷ��������˵���
     * @param menu �˵���
     * @param actions ��������
     */
    protected static void addActions(JPopupMenu menu, Action[] actions) {
        addMenuItems(menu, BaseUtilities.createMenuItems(actions));
    }

    /**
     * ��Ӳ˵���˵���
     * @param menu �˵���
     * @param menuItem �˵��
     */
    protected static void addMenuItem(JPopupMenu menu, JMenuItem menuItem) {
        addMenuItems(menu, new JMenuItem[] { menuItem });
    }

    /**
     * ��Ӳ˵�����˵���
     * @param menu �˵���
     * @param menuItems �˵����
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
     * ��ʶ�Ƿ���������С�
     */
    protected volatile boolean m_isEnabledInsertRow;

    /**
     * ��ʶ�Ƿ������Ƴ��С�
     */
    protected volatile boolean m_isEnabledRemoveRow;

    /**
     * �ۺϱ��������
     */
    protected IComplexTableManager m_manager;

    /**
     * �б��ƹؼ��֡�
     */
    protected String m_customKey;

    /**
     * �˵����
     */
    protected List m_menuItems = new ArrayList();

    /**
     * ��ʶ�Ƿ����Ķ�ģʽ��
     */
    protected volatile boolean m_isReadMode = false;

    /**
     * ���塣
     */
    protected TableBody m_body;

    /**
     * ��ͷ��
     */
    protected TableRowHeader m_rowHeader;

    /**
     * ѡ�еı����ͼ��
     */
    protected TableView m_chooseTableView;

    /**
     * ѡ�е���������
     */
    protected volatile int m_chooseColumnIndex;

    /**
     * �Ѹı䵥Ԫ���ǰ��ɫ��
     */
    protected Color m_changedCellForeground = new Color(16741122);

    /**
     * ��ʶ�Ƿ�����չ�ֱ�ǡ�
     */
    protected volatile boolean m_isEnabledShowFlag = true;

    /**
     * ��ʶ�Ƿ�ʹ�ܡ�
     */
    protected volatile boolean m_isEnabled = true;

    /**
     * �к���Ⱦ����
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
     * ȡ������
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
     * ȡ����������
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
     * ѡ���С�
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
     * �����е���ǰ�е�ǰ�档
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
     * �����е���ǰ�еĺ��档
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
     * �����е����С�
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
     * �����е�ĩ�С�
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
     * �Ƴ�ѡ���С�
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
     * �Ƴ������С�
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
     * ����ѡ���С�
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
     * ���������С�
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
     * �ָ�ѡ���С�
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
     * �ָ������С�
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
     * ˢ�¡�
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
     * ���췽����
     * @param model �ۺϱ�ģ�͡�
     * @since T3 V1.1
     */
    public ComplexTable(ComplexTableModel model) {
        this(model, null);
    }

    /**
     * ���췽����
     * @param model �ۺϱ�ģ�͡�
     * @param customKey �б��ƹؼ��֣����Ϊ�վͲ������б�����Ϣ�������ǿ��Խ����б��ơ�
     * @since T3 V1.1
     */
    public ComplexTable(ComplexTableModel model, String customKey) {
        this(model, false, false, null, customKey);
    }

    /**
     * ���췽����
     * @param model �ۺϱ�ģ�͡�
     * @param isEnabledInsertRow ��ʶ�Ƿ�����ͨ���ۺϱ��Դ��Ĳ˵����������С�
     * @param isEnabledRemoveRow ��ʶ�Ƿ�����ͨ���ۺϱ��Դ��Ĳ˵������Ƴ��С�
     * @param manager �ۺϱ��������
     * @since T3 V1.1
     */
    public ComplexTable(ComplexTableModel model, boolean isEnabledInsertRow, boolean isEnabledRemoveRow, IComplexTableManager manager) {
        this(model, isEnabledInsertRow, isEnabledRemoveRow, manager, null);
    }

    /**
     * ���췽����
     * @param model �ۺϱ�ģ�͡�
     * @param isEnabledInsertRow ��ʶ�Ƿ�����ͨ���ۺϱ��Դ��Ĳ˵����������С�
     * @param isEnabledRemoveRow ��ʶ�Ƿ�����ͨ���ۺϱ��Դ��Ĳ˵������Ƴ��С�
     * @param manager �ۺϱ��������
     * @param customKey �б��ƹؼ��֣����Ϊ�վͲ������б�����Ϣ�������ǿ��Զ����б�������޷���֤����Ϣ���䣬������Ϊnull�Խ�ֹ�����б�����Ϣ��
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
     * ���췽����
     * @param model �ۺϱ�ģ�͡�
     * @param isEnabledInsertRow ��ʶ�Ƿ�����ͨ���ۺϱ��Դ��Ĳ˵����������С�
     * @param isEnabledRemoveRow ��ʶ�Ƿ�����ͨ���ۺϱ��Դ��Ĳ˵������Ƴ��С�
     * @param manager �ۺϱ��������
     * @param customKey �б��ƹؼ��֣����Ϊ�վͲ������б�����Ϣ�������ǿ��Զ����б�������޷���֤����Ϣ���䣬������Ϊnull�Խ�ֹ�����б�����Ϣ��
     * @param menuItems ���˵����
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
     * ���췽����
     * @param model �ۺϱ�ģ�͡�
     * @param isEnabledInsertRow ��ʶ�Ƿ�����ͨ���ۺϱ��Դ��Ĳ˵����������С�
     * @param isEnabledRemoveRow ��ʶ�Ƿ�����ͨ���ۺϱ��Դ��Ĳ˵������Ƴ��С�
     * @param manager �ۺϱ��������
     * @param customKey �б��ƹؼ��֣����Ϊ�վͲ������б�����Ϣ�������ǿ��Զ����б�������޷���֤����Ϣ���䣬������Ϊnull�Խ�ֹ�����б�����Ϣ��
     * @param menuItems ���˵����
     * @param isAutoEnabledRenderer ��ʶ�Ƿ��Զ�������Ⱦ������ļ���״̬��Ϊtrue����Ⱦ���������״̬����Ԫ���Ƿ�ɱ༭����������Ϊfalse��������
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
     * ���췽����
     * @param model �ۺϱ�ģ�͡�
     * @param isEnabledInsertRow ��ʶ�Ƿ�����ͨ���ۺϱ��Դ��Ĳ˵����������С�
     * @param isEnabledRemoveRow ��ʶ�Ƿ�����ͨ���ۺϱ��Դ��Ĳ˵������Ƴ��С�
     * @param manager �ۺϱ��������
     * @param customKey �б��ƹؼ��֣����Ϊ�վͲ������б�����Ϣ�������ǿ��Զ����б�������޷���֤����Ϣ���䣬������Ϊnull�Խ�ֹ�����б�����Ϣ��
     * @param menuItems ���˵����
     * @param isAutoEnabledRenderer ��ʶ�Ƿ��Զ�������Ⱦ������ļ���״̬��Ϊtrue����Ⱦ���������״̬����Ԫ���Ƿ�ɱ༭����������Ϊfalse��������
     * @param isAutoSetPreferredWidth ��ʶ�Ƿ��Զ�������ѿ�ȣ����������Ҫ�����������б��Ƶ�����£����б���������ǰ��������һ������п�ʹ��һ�α��ʹ��ʱ�ܹ���������п�ȱʡΪfalse��
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
     * ���췽����
     * @param model �ۺϱ�ģ�͡�
     * @param isEnabledInsertRow ��ʶ�Ƿ�����ͨ���ۺϱ��Դ��Ĳ˵����������С�
     * @param isEnabledRemoveRow ��ʶ�Ƿ�����ͨ���ۺϱ��Դ��Ĳ˵������Ƴ��С�
     * @param manager �ۺϱ��������
     * @param customKey �б��ƹؼ��֣����Ϊ�վͲ������б�����Ϣ�������ǿ��Զ����б�������޷���֤����Ϣ���䣬������Ϊnull�Խ�ֹ�����б�����Ϣ��
     * @param menuItems ���˵����
     * @param isAutoEnabledRenderer ��ʶ�Ƿ��Զ�������Ⱦ������ļ���״̬��Ϊtrue����Ⱦ���������״̬����Ԫ���Ƿ�ɱ༭����������Ϊfalse��������
     * @param isAutoSetPreferredWidth ��ʶ�Ƿ��Զ�������ѿ�ȣ����������Ҫ�����������б��Ƶ�����£����б���������ǰ��������һ������п�ʹ��һ�α��ʹ��ʱ�ܹ���������п�ȱʡΪfalse��
     * @param bodyDefaultCustoms ����ȱʡ���б�����Ϣ����
     * @param rowHeaderDefaultCustoms ��ͷȱʡ���б�����Ϣ����
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
     * ������ѿ�ȡ�
     */
    public void setPreferredWidth() {
        TableUtilities.setPreferredWidth(m_body);
        TableUtilities.setPreferredWidth(m_rowHeader);
    }

    /**
     * ��ȡ���壬���ۺϱ�ʵ����ʾ�ڹ������������Ǹ�JTable��
     * @return ���塣
     * @since T3 V1.1
     */
    public TableBody getBody() {
        return m_body;
    }

    /**
     * ��ȡ��ͷ�����ۺϱ�ʵ����ʾ�ڹ������RowHeaderλ�õĵ��Ǹ�JTable��
     * @return ��ͷ��
     * @since T3 V1.1
     */
    public TableRowHeader getRowHeader() {
        return m_rowHeader;
    }

    /**
     * ��ȡ��ǰ��ͼ�������ͼ�����ڸ���ӡģ��ʹ�ã�������ִ���κ�д������
     * @return ��ǰ��ͼ��
     * @since T3 V1.1
     */
    public JTable getCurrentView() {
        return new TCurrentView();
    }

    /**
     * ��ȡ�˵����
     * @return �˵����
     * @since T3 V1.1
     */
    public JMenuItem[] getMenuItems() {
        return (JMenuItem[]) m_menuItems.toArray(new JMenuItem[m_menuItems.size()]);
    }

    /**
     * ��ȡ�ֶ� isReadMode ��ֵ��
     * @return �ֶ� isReadMode ��ֵ��
     */
    public boolean isReadMode() {
        return m_isReadMode;
    }

    /**
     * �����ֶ� isReadMode ��ֵ��
     * @param isReadMode �ֶ� isReadMode ��ֵ��
     */
    public void setReadMode(boolean isReadMode) {
        m_isReadMode = isReadMode;
    }

    /**
     * ��Ӳ˵������
     * @param action �˵������
     * @since T3 V1.1
     */
    public void addAction(Action action) {
        addMenuItem(new JMenuItem(action));
    }

    /**
     * ��Ӳ˵������
     * @param index ������
     * @param action �˵������
     * @since T3 V1.1
     */
    public void addAction(int index, Action action) {
        addMenuItem(index, new JMenuItem(action));
    }

    /**
     * �Ƴ��˵������
     * @param index ������
     * @since T3 V1.1
     */
    public void removeAction(int index) {
        m_menuItems.remove(index);
    }

    /**
     * �Ƴ��˵������
     * @param action �˵������
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
     * �ж��Ƿ����ָ���Ĳ˵������
     * @param action �˵������
     * @return ��ʶ�Ƿ����ָ���Ĳ˵������
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
     * ��Ӳ˵��
     * @param menuItem �˵��
     * @since T3 V1.1
     */
    public void addMenuItem(JMenuItem menuItem) {
        m_menuItems.add(menuItem);
    }

    /**
     * ��Ӳ˵��
     * @param index ������
     * @param menuItem �˵��
     * @since T3 V1.1
     */
    public void addMenuItem(int index, JMenuItem menuItem) {
        m_menuItems.add(index, menuItem);
    }

    /**
     * �Ƴ��˵��
     * @param index ������
     * @since T3 V1.1
     */
    public void removeMenuItem(int index) {
        m_menuItems.remove(index);
    }

    /**
     * �Ƴ��˵��
     * @param menuItem �˵��
     * @since T3 V1.1
     */
    public void removeMenuItem(JMenuItem menuItem) {
        m_menuItems.remove(menuItem);
    }

    /**
     * �ж��Ƿ����ָ���Ĳ˵��
     * @param menuItem �˵��
     * @return ��ʶ�Ƿ����ָ���Ĳ˵��
     * @since T3 V1.1
     */
    public boolean containsMenuItem(JMenuItem menuItem) {
        return m_menuItems.contains(menuItem);
    }

    /**
     * ��ȡ�ֶ� changedCellForeground ��ֵ������Ԫ��ֵ�ı�󣬵�Ԫ���ǰ��ɫ���ʲô��ɫ��ȱʡ�ǵ���ɫ��
     * @return �ֶ� changedCellForeground ��ֵ��
     * @since T3 V1.1
     */
    public Color getChangedCellForeground() {
        return m_changedCellForeground;
    }

    /**
     * �����ֶ� changedCellForeground ��ֵ������Ԫ��ֵ�ı�󣬵�Ԫ���ǰ��ɫ���ʲô��ɫ��ȱʡ�ǵ���ɫ��
     * @param changedCellForeground �ֶ� changedCellForeground ��ֵ��
     * @since T3 V1.1
     */
    public void setChangedCellForeground(Color changedCellForeground) {
        m_changedCellForeground = changedCellForeground;
    }

    /**
     * ��ȡ�ֶ� isEnabledShowFlag ��ֵ����ʶ�Ƿ�����չ�ֱ�ǣ�ȱʡ������ġ�
     * @return �ֶ� isEnabledShowFlag ��ֵ��
     * @since T3 V1.2
     */
    public boolean isEnabledShowFlag() {
        return m_isEnabledShowFlag;
    }

    /**
     * �����ֶ� isEnabledShowFlag ��ֵ����ʶ�Ƿ�����չ�ֱ�ǣ�ȱʡ������ġ�
     * @param isEnabledShowFlag �ֶ� isEnabledShowFlag ��ֵ��
     * @since T3 V1.2
     */
    public void setEnabledShowFlag(boolean isEnabledShowFlag) {
        m_isEnabledShowFlag = isEnabledShowFlag;
        repaintAll();
    }

    /**
     * ��ȡ�ۺϱ�ģ�͡�
     * @return �ۺϱ�ģ�͡�
     * @since T3 V1.1
     */
    public ComplexTableModel getComplexTableModel() {
        return (ComplexTableModel) dataModel;
    }

    /**
     * ��ȡ�ֶ� sortable ��ֵ�����жϵ�ǰ�ۺϱ��Ƿ���������ͷ����
     * @return �ֶ� sortable ��ֵ��
     * @since T3 V1.1
     */
    public boolean isSortable() {
        return getComplexTableModel().isSortable();
    }

    /**
     * �����ֶ� sortable ��ֵ�������õ�ǰ�ۺϱ��Ƿ���������ͷ����
     * @param sortable �ֶ� sortable ��ֵ��
     * @since T3 V1.1
     */
    public void setSortable(boolean sortable) {
        getComplexTableModel().setSortable(sortable);
        repaintAll();
    }

    /**
     * ��ȡ��ָ������ͼ�������ж���
     * @param row ��������
     * @return �С�
     * @since T3 V1.1
     */
    public ComplexTableRow getRow(int row) {
        return getComplexTableModel().getRow(row);
    }

    /**
     * ��ȡ��Ԫ�����
     * @param row ����ͼ������
     * @param column ����ͼ������ע�⣺�˴�����ͼ�������ڴ��ڶ����ͼ����ʱ������ȷ��������Ӧ��ʹ��ģ�͵�getCell������ͨ��ģ����������ȡ��Ԫ�����ݡ�
     * @return ��Ԫ��
     * @see ComplexTableModel#getCell(int, int)
     * @since T3 V1.1
     */
    public ComplexTableCell getCell(int row, int column) {
        return getComplexTableModel().getCell(row, convertColumnIndexToModel(column));
    }

    /**
     * ���棬�������д����޸�״̬�������ݱ����ģ�ͣ���ͷ����Ӧ���Ǻ�Ҳ����ʧ��
     * @since T3 V1.1
     */
    public void save() {
        for (int i = getRowCount() - 1; i >= 0; i--) {
            saveRow(i);
        }

        repaintAll();
    }

    /**
     * �ָ����������д����޸�״̬����������ģ���е����ݻָ���������ͷ����Ӧ���Ǻ�Ҳ����ʧ��
     * @since T3 V1.1
     */
    public void restore() {
        for (int i = getRowCount() - 1; i >= 0; i--) {
            restoreRow(i);
        }

        repaintAll();
    }

    /**
     * ˢ�£���ˢ�½����ϲ�һ�µ�״̬������ģ�Ͳ�ĳЩֵ�޸��˵�����ͷ����״̬��һ�£���������������л���������һ�¡�
     * @since T3 V1.1
     */
    public void refresh() {
        getComplexTableModel().sort();
    }

    /**
     * ֹͣ�༭��
     * @since T3 V1.1
     */
    public void stopEditing() {
        if (isEditing()) {
            getCellEditor().stopCellEditing();
        }
    }

    /**
     * ȡ�������е�����
     * @since T3 V1.1
     */
    public void cancelSort() {
        stopEditing();
        getComplexTableModel().cancelSort();
        repaintAll();
    }

    /**
     * <p>���������ݣ������л�ص��ۺϱ��������createRow�����������ݹ�����</p>
     * <p>ע�⣺������뷽������ֱ�Ӹ�ģ�����ݵģ����Խ����������е���ͷ����һ���Ӻű�ʶ������������Ա��棬Ҳ���Ա����ˡ�</p>
     * @param index ������
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
     * �Ƴ��м���ע�⣺���ɾ����������ֱ�Ӹ�ģ�����ݵģ����Խ����ϲ�������ɾ����Щ�У���Щ�е���ͷ�ϻ���һ����ű�ʶ�����Ҳ�����༭������������Ա��棬Ҳ���Ա����ˡ�
     * @param rows �м���
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
     * �Ƴ������У�ע�⣺���ɾ����������ֱ�Ӹ�ģ�����ݵģ����Խ����ϲ�������ɾ�������У�ֻ���������е���ͷ�ϻ���һ����ű�ʶ�����Ҳ�����༭������������Ա��棬Ҳ���Ա����ˡ�
     * @since T3 V1.1
     */
    public void removeAllRow() {
        removeRows(BaseUtilities.createIntegers(getRowCount()));
    }

    /**
     * �����У�����ָ���е������ݱ����ģ�ͣ���ͷ����Ӧ���Ǻ�Ҳ����ʧ��
     * @param index ������
     * @since T3 V1.1
     */
    public void saveRow(int index) {
        stopEditing();
        getComplexTableModel().saveRow(index);
    }

    /**
     * �ָ��У�����ָ���е���������ģ���е����ݻָ���������ͷ����Ӧ���Ǻ�Ҳ����ʧ��
     * @param index ������
     * @since T3 V1.1
     */
    public void restoreRow(int index) {
        stopEditing();
        getComplexTableModel().restoreRow(index);
    }

    /**
     * ��ȡ����ѡ�е��ۺϱ��С�
     * @return ����ѡ�е��ۺϱ��С�
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
     * �ػ����У���Ϊ�ۺϱ����Body��RowHeader����������������ǵ���������JTable��repaint������
     * @since T3 V1.1
     */
    public void repaintAll() {
        m_body.repaint();
        m_body.getTableHeader().repaint();

        m_rowHeader.repaint();
        m_rowHeader.getTableHeader().repaint();
    }

    /**
     * �����б�����Ϣ��
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
     * @deprecated �˷�����JTable�����еķ��������ۺϱ���ʹ�û��ճ����⣬��ʹ���ۺϱ�ģ�͵���ط��������Ӧ���ܡ�
     * @see javax.swing.JTable#addColumn(javax.swing.table.TableColumn)
     * @see ComplexTableModel#addColumn(ComplexTableColumn)
     */
    public void addColumn(TableColumn aColumn) {
        super.addColumn(aColumn);
    }

    /**
     * @deprecated �˷�����JTable�����еķ��������ۺϱ���ʹ�û��ճ����⣬��ʹ���ۺϱ�ģ�͵���ط��������Ӧ���ܡ�
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
                        /* ��ListSelectionModel.SINGLE_INTERVAL_SELECTIONʱ�������Ͽ��ܻ������������õ�ѡ����������ʾ���� */
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
        /* �ݲ���ʵ�� */
    }

    /**
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    public void mouseExited(MouseEvent evt) {
        /* �ݲ���ʵ�� */
    }

    /**
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    public void mousePressed(MouseEvent evt) {
        /* �ݲ���ʵ�� */
    }

    /**
     * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    public void mouseReleased(MouseEvent evt) {
        /* �ݲ���ʵ�� */
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
        /* �ݲ���ʵ�� */
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
     * Լ����
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
     * ѡ�������С�
     */
    protected void selectAllColumn() {
        m_body.setColumnSelectionInterval(0, m_body.getColumnCount() - 1);
        m_rowHeader.setColumnSelectionInterval(0, m_rowHeader.getColumnCount() - 1);
    }

    /**
     * ���������в˵���
     * @return �����в˵���
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
     * �����Ƴ��в˵���
     * @return �Ƴ��в˵���
     */
    protected JMenu createRemoveRowMenu() {
        JMenu removeRowMenu = new JMenu(I18N_INFO_REMOVEROW);

        removeRowMenu.add(m_removeRows);
        removeRowMenu.add(m_removeAllRow);

        return removeRowMenu;
    }

    /**
     * ���������в˵���
     * @return �����в˵���
     */
    protected JMenu createSaveRowMenu() {
        JMenu saveRowMenu = new JMenu(I18N_INFO_SAVEROW);

        saveRowMenu.add(m_saveRows);
        saveRowMenu.add(m_saveAllRow);

        return saveRowMenu;
    }

    /**
     * �����ָ��в˵���
     * @return �ָ��в˵���
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
 * <p>�ļ����ƣ�ComplexTable.java</p>
 * <p>�����������ۺϱ����ӿڡ�</p>
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
interface IConstants extends IBaseConstants {

    /*------------------------------------- Public Static Final Field -------------------------------------*/

    /**
     * ����״̬------����
     */
    public static final int SORTSTATE_ASCENDING = SortIcon.STATE_ASCENDING;

    /**
     * ����״̬------����
     */
    public static final int SORTSTATE_DESCENDING = SortIcon.STATE_DESCENDING;

    /**
     * ����״̬------������ͨ״̬����
     */
    public static final int SORTSTATE_NORMAL = SortIcon.STATE_NORMAL;

    /**
     * ���ʻ���Ϣ------���ɱ༭��
     */
    public static final String I18N_INFO_INEDITABLE = Utilities.getI18nMessage("ineditable");

    /**
     * ���ʻ���Ϣ------�����С�
     */
    public static final String I18N_INFO_INSERTROW = Utilities.getI18nMessage("insertRow");

    /**
     * ���ʻ���Ϣ------���뵽��ǰ�е�ǰ�档
     */
    public static final String I18N_INFO_INSERTROWATPREVIOUS = Utilities.getI18nMessage("insertRowAtPrevious");

    /**
     * ���ʻ���Ϣ------���뵽��ǰ�еĺ��档
     */
    public static final String I18N_INFO_INSERTROWATNEXT = Utilities.getI18nMessage("insertRowAtNext");

    /**
     * ���ʻ���Ϣ------���뵽���С�
     */
    public static final String I18N_INFO_INSERTROWATFIRST = Utilities.getI18nMessage("insertRowAtFirst");

    /**
     * ���ʻ���Ϣ------���뵽ĩ�С�
     */
    public static final String I18N_INFO_INSERTROWATLAST = Utilities.getI18nMessage("insertRowAtLast");

    /**
     * ���ʻ���Ϣ------ɾ���С�
     */
    public static final String I18N_INFO_REMOVEROW = Utilities.getI18nMessage("removeRow");

    /**
     * ���ʻ���Ϣ------ɾ��ѡ���С�
     */
    public static final String I18N_INFO_REMOVEROWS = Utilities.getI18nMessage("removeRows");

    /**
     * ���ʻ���Ϣ------ɾ�������С�
     */
    public static final String I18N_INFO_REMOVEALLROW = Utilities.getI18nMessage("removeAllRow");

    /**
     * ���ʻ���Ϣ------�����С�
     */
    public static final String I18N_INFO_SAVEROW = Utilities.getI18nMessage("saveRow");

    /**
     * ���ʻ���Ϣ------����ѡ���С�
     */
    public static final String I18N_INFO_SAVEROWS = Utilities.getI18nMessage("saveRows");

    /**
     * ���ʻ���Ϣ------���������С�
     */
    public static final String I18N_INFO_SAVEALLROW = Utilities.getI18nMessage("saveAllRow");

    /**
     * ���ʻ���Ϣ------�ָ��С�
     */
    public static final String I18N_INFO_RESTOREROW = Utilities.getI18nMessage("restoreRow");

    /**
     * ���ʻ���Ϣ------�ָ�ѡ���С�
     */
    public static final String I18N_INFO_RESTOREROWS = Utilities.getI18nMessage("restoreRows");

    /**
     * ���ʻ���Ϣ------�ָ������С�
     */
    public static final String I18N_INFO_RESTOREALLROW = Utilities.getI18nMessage("restoreAllRow");

    /**
     * ���ʻ���Ϣ------ˢ���С�
     */
    public static final String I18N_INFO_REFRESHROW = Utilities.getI18nMessage("refreshRow");

    /**
     * ���ʻ���Ϣ------ȡ������
     */
    public static final String I18N_INFO_CANCELSORT = Utilities.getI18nMessage("cancelSort");

    /**
     * ���ʻ���Ϣ------ȡ����������
     */
    public static final String I18N_INFO_CANCELALLSORT = Utilities.getI18nMessage("cancelAllSort");

    /**
     * ���ʻ���Ϣ------ѡ���С�
     */
    public static final String I18N_INFO_CHOOSECOLUMN = Utilities.getI18nMessage("chooseColumn");

    /**
     * ͼ��------�Ѳ��롣
     */
    public static final Icon ICON_INSERTED = Utilities.getIcon("inserted.gif");

    /**
     * ͼ��------���Ƴ���
     */
    public static final Icon ICON_REMOVED = Utilities.getIcon("removed.gif");

    /**
     * ͼ��------���޸ġ�
     */
    public static final Icon ICON_CHANGED = Utilities.getIcon("changed.gif");

    /*------------------------------------- Public Abstract Method -------------------------------------*/

    /* Add Public Abstract Method */

}

/**
 * <p>�ļ����ƣ�ComplexTable.java</p>
 * <p>�����������ۺϱ����ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-5-10</p>
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
     * ͨ��key����ȡ�б��ƵĹ��ʻ���Ϣ��
     * @param i18nKey ���ʻ���Ϣ�ؼ��֡�
     * @return ���ʻ���Ϣ��
     */
    protected static String getI18nMessage(String i18nKey) {
        return BaseUtilities.getI18nMessage("com.apollo.swing.component.complextable.resource.ComplexTable", i18nKey);
    }

    /**
     * ��ȡͼ�ꡣ
     * @param iconFilename ͼ���ļ�����
     * @return ͼ�ꡣ
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
     * ���췽����
     */
    private Utilities() {
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
