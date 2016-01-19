/*
 * �˴��봴���� 2008-4-26 ����12:31:37��
 */
package com.apollo.swing.component.table;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.apollo.base.util.BaseUtilities;
import com.apollo.base.util.IBaseConstants;
import com.apollo.swing.component.editor.IntegerFieldCellEditor;
import com.apollo.swing.component.text.IntegerField;

/**
 * <p>�ļ����ƣ�TableCustomDialog.java</p>
 * <p>�����������б��ƶԻ����ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-4-26</p>
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
public class TableCustomDialog extends JDialog
    implements
        IBaseConstants,
        ITableConstants,
        WindowListener,
        WindowFocusListener,
        TableModelListener,
        ListSelectionListener {

    /*------------------------------------- Public (Static) Inner Class -------------------------------------*/

    /* Add Public (Static) Inner Class */

    /*------------------------------------- Protected (Static) Inner Class -------------------------------------*/

    /**
     * ���ñ���ģ���ࡣ
     */
    protected static class TConfigTableModel extends AbstractTableModel {

        /**
         * �����б�����Ϣ��
         */
        protected List m_customs = new ArrayList();

        /**
         * ���췽����
         * @param table ���
         */
        public TConfigTableModel(JTable table) {
            setTable(table);
        }

        /**
         * �ж�ָ���Ķ������Ƿ���ʾ��
         * @param row ���������ڵ���������
         * @return Ϊtrue��ʾָ���Ķ���������ʾ�ģ�Ϊfalse��ʾָ���Ķ����������صġ�
         */
        public boolean isVisible(int row) {
            return ((TableCustom) m_customs.get(row)).m_visible.booleanValue();
        }

        /**
         * �ж�ָ�����м��Ƿ�ȫ�����ء�
         * @param rows ָ�����С�
         * @return Ϊtrue��ʾָ�����м���ȫ�����أ�Ϊfalse��ʾָ�����м�����ȫ�����ء�
         */
        public boolean isHide(int[] rows) {
            for (int i = 0; i < rows.length; i++) {
                if (isVisible(rows[i])) {
                    return false;
                }
            }

            return true;
        }

        /**
         * �жϳ�ָ���Ķ�������������������Ƿ�ȫ�����ء�
         * @param row ���������ڵ���������
         * @return Ϊtrue��ʾ��ָ���Ķ��������������������ȫ�����أ�Ϊfalse��ʾ��ָ���Ķ�����������������в���ȫ�����ء�
         */
        public boolean isHideOther(int row) {
            for (int i = 0, size = m_customs.size(); i < size; i++) {
                if (i != row && isVisible(i)) {
                    return false;
                }
            }

            return true;
        }

        /**
         * @see javax.swing.table.TableModel#getColumnCount()
         */
        public int getColumnCount() {
            return 3;
        }

        /**
         * @see javax.swing.table.AbstractTableModel#getColumnName(int)
         */
        public String getColumnName(int column) {
            switch (column) {

            case 0:
                return I18N_INFO_COLUMNNAME;

            case 1:
                return I18N_INFO_VISIBLE;

            case 2:
                return I18N_INFO_WIDTH;

            default:
                return super.getColumnName(column);
            }
        }

        /**
         * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
         */
        public Class getColumnClass(int column) {
            if (column == 1) { //�����Ƿ�ɼ�������Ҫ��ɱ༭��ʹ��ȱʡ����Ⱦ�ͱ༭�����㶨Boolean����
                return Boolean.class;

            } else if (column == 2) {
                return BigInteger.class;

            } else {
                return super.getColumnClass(column);
            }
        }

        /**
         * @see javax.swing.table.TableModel#getRowCount()
         */
        public int getRowCount() {
            return m_customs.size();
        }

        /**
         * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
         */
        public boolean isCellEditable(int row, int column) {
            switch (column) {

            case 0:
                return false;

            case 1:
                return !isHideOther(row);

            case 2:
                return true;

            default:
                return false;
            }
        }

        /**
         * @see javax.swing.table.TableModel#getValueAt(int, int)
         */
        public Object getValueAt(int row, int column) {
            TableCustom custom = getCustom(row);

            switch (column) {

            case 0:
                return custom.m_column.getIdentifier();

            case 1:
                return custom.m_visible;

            case 2:
                return custom.m_width;

            default:
                return null;
            }
        }

        /**
         * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
         */
        public void setValueAt(Object value, int row, int column) {
            if (value == null) {
                return;
            }

            TableCustom custom = getCustom(row);

            if (column == 1) {
                custom.m_visible = (Boolean) value;

            } else if (column == 2) {
                custom.m_width = (BigInteger) value;
            }

            fireTableCellUpdated(row, column);
        }

        /**
         * ���ñ��
         * @param table ���
         */
        protected void setTable(JTable table) {
            m_customs.clear();
            List customs = TableUtilities.getTableCustoms(table);

            if (customs != null) {
                m_customs.addAll(customs);
            }

            if (m_customs.isEmpty()) {
                m_customs = TableUtilities.createTableCustoms(table);
                TableUtilities.setTableCustoms(table, new ArrayList(m_customs));

            } else {
                syncVisibleFromTable(table);
                syncWidthFromColumn();
            }

            fireTableDataChanged();
        }

        /**
         * �ӱ���������á�
         * @param table ���
         */
        protected void resetFromTable(JTable table) {
            syncCustomsFromTable(table);
            syncVisibleFromTable(table);
            syncWidthFromColumn();
        }

        /**
         * �ӱ��ͬ���б�����Ϣ��
         * @param table ���
         */
        protected void syncCustomsFromTable(JTable table) {
            List customs = TableUtilities.getTableCustoms(table);

            if (customs == null) {
                setTable(table);

            } else {
                if (!m_customs.equals(customs)) {
                    m_customs.clear();
                    m_customs.addAll(customs);

                    fireTableDataChanged();
                }
            }
        }

        /**
         * �ӱ��ͬ�����Ƿ�ɼ���
         * @param table ���
         */
        protected void syncVisibleFromTable(JTable table) {
            for (int i = 0, size = m_customs.size(); i < size; i++) {
                TableCustom custom = (TableCustom) m_customs.get(i);
                custom.m_visible = new Boolean(TableUtilities.contains(table.getColumnModel(), custom.m_column));
            }
        }

        /**
         * ���ж���ͬ���п�
         */
        protected void syncWidthFromColumn() {
            for (int i = 0, size = m_customs.size(); i < size; i++) {
                ((TableCustom) m_customs.get(i)).syncWidthFromColumn();
            }
        }

        /**
         * ��ȡ����Ϣ��
         * @param row ��������
         * @return ����Ϣ��
         */
        protected TableCustom getCustom(int row) {
            return (TableCustom) m_customs.get(row);
        }

    }

    /**
     * ���ñ���ࡣ
     */
    protected static class TConfigTable extends JTable implements MouseListener {

        /**
         * ��񣨱����ã���
         */
        protected JTable m_table;

        /**
         * ������塣
         */
        protected JScrollPane m_sp = new JScrollPane();

        /**
         * ��ͼ��
         */
        protected JPanel m_view = new JPanel(new GridBagLayout());

        /**
         * ���˵���
         */
        protected JPopupMenu m_menu = new JPopupMenu();

        /**
         * ȫ���п��ǩ��
         */
        protected JLabel m_allColumnWidthLabel = new JLabel();

        /**
         * ȫ���п������
         */
        protected IntegerField m_allColumnWidthField = new IntegerField(2, 1, 999, 75);

        /**
         * ���ö�����
         */
        protected Action m_set = new AbstractAction() {

            /**
             * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
             */
            public void actionPerformed(ActionEvent evt) {
                stopEditing();

                TConfigTableModel ctm = getConfigTableModel();

                BigInteger allColumnWidth = m_allColumnWidthField.getInteger();

                for (int i = 0, size = getRowCount(); i < size; i++) {
                    ctm.setValueAt(allColumnWidth, i, 2);
                    ctm.fireTableCellUpdated(i, 2);
                }
            }

        };

        /**
         * ��ʾ�ж�����
         */
        protected Action m_show = new AbstractAction() {

            {
                BaseUtilities.setAction(this, I18N_INFO_SHOW, 'S');
            }

            /**
             * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
             */
            public void actionPerformed(ActionEvent evt) {
                stopEditing();

                int[] rows = getSelectedRows();
                TConfigTableModel ctm = getConfigTableModel();

                for (int i = 0; i < rows.length; i++) {
                    ctm.setValueAt(Boolean.TRUE, rows[i], 1);
                    ctm.fireTableCellUpdated(rows[i], 1);
                }
            }

        };

        /**
         * �����ж�����
         */
        protected Action m_hide = new AbstractAction() {

            {
                BaseUtilities.setAction(this, I18N_INFO_HIDE, 'H');
            }

            /**
             * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
             */
            public void actionPerformed(ActionEvent evt) {
                stopEditing();

                int[] rows = getSelectedRows();
                TConfigTableModel ctm = getConfigTableModel();

                for (int i = 0; i < rows.length; i++) {
                    ctm.setValueAt(Boolean.FALSE, rows[i], 1);
                    ctm.fireTableCellUpdated(rows[i], 1);
                }
            }

        };

        /**
         * �����ж�����
         */
        protected Action m_moveToUp = new AbstractAction() {

            {
                BaseUtilities.setAction(this, I18N_INFO_MOVETOUP, 'U', ICON_MOVETOUP);
            }

            /**
             * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
             */
            public void actionPerformed(ActionEvent evt) {
                stopEditing();

                TConfigTableModel ctm = getConfigTableModel();
                ListSelectionModel sm = getSelectionModel();

                int[] rows = getSelectedRows();
                Arrays.sort(rows);
                sm.clearSelection();

                for (int i = 0; i < rows.length; i++) {
                    ctm.m_customs.add(rows[i] - 1, ctm.m_customs.remove(rows[i]));
                    sm.addSelectionInterval(rows[i] - 1, rows[i] - 1);
                }
            }

        };

        /**
         * �����ж�����
         */
        protected Action m_moveToDown = new AbstractAction() {

            {
                BaseUtilities.setAction(this, I18N_INFO_MOVETODOWN, 'D', ICON_MOVETODOWN);
            }

            /**
             * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
             */
            public void actionPerformed(ActionEvent evt) {
                stopEditing();

                TConfigTableModel ctm = getConfigTableModel();
                ListSelectionModel sm = getSelectionModel();

                int[] rows = getSelectedRows();
                Arrays.sort(rows);
                sm.clearSelection();

                for (int i = rows.length - 1; i >= 0; i--) {
                    ctm.m_customs.add(rows[i] + 1, ctm.m_customs.remove(rows[i]));
                    sm.addSelectionInterval(rows[i] + 1, rows[i] + 1);
                }
            }

        };

        /**
         * ���췽����
         * @param table ���
         */
        public TConfigTable(JTable table) {
            super(new TConfigTableModel(table));

            m_table = table;
            buildView();

            m_menu.add(m_show);
            m_menu.add(m_hide);
            m_menu.addSeparator();
            m_menu.add(m_moveToUp);
            m_menu.add(m_moveToDown);

            boolean isRowHeader = isRowHeader();

            char mnemonic = isRowHeader ? 'P' : 'W';
            m_allColumnWidthLabel.setText(BaseUtilities.getMnemonicText(I18N_INFO_ALLWIDTH, mnemonic) + ":");
            m_allColumnWidthLabel.setDisplayedMnemonic(mnemonic);
            m_allColumnWidthLabel.setLabelFor(m_allColumnWidthField);

            m_allColumnWidthField.setMinimumSize(m_allColumnWidthField.getPreferredSize());

            BaseUtilities.setAction(m_set, I18N_INFO_SET, isRowHeader ? 'H' : 'T');

            setDefaultEditor(BigInteger.class, new IntegerFieldCellEditor() {

                /**
                 * @see com.apollo.swing.component.editor.FormattedFieldCellEditor#getTableCellEditorComponent(javax.swing.JTable, java.lang.Object, boolean, int, int)
                 */
                public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                    IntegerField widthField = (IntegerField) super.getTableCellEditorComponent(table, value, isSelected, row, column);
                    TableColumn targetColumn = ((TableCustom) getConfigTableModel().m_customs.get(row)).m_column;
                    widthField.setMinimum(null);
                    widthField.setMaximum(null);
                    widthField.setMinimum(targetColumn.getMinWidth());
                    widthField.setMaximum(targetColumn.getMaxWidth());
                    return widthField;
                }

            });

            TableColumnModel cm = getColumnModel();

            TableColumn columnName = cm.getColumn(0);
            columnName.setPreferredWidth(100);
            columnName.setResizable(false);

            TableColumn isVisible = cm.getColumn(1);
            isVisible.setMaxWidth(50);
            isVisible.setMinWidth(50);
            isVisible.setPreferredWidth(50);
            isVisible.setResizable(false);

            TableColumn columnWidth = cm.getColumn(2);
            columnWidth.setMaxWidth(80);
            columnWidth.setMinWidth(80);
            columnWidth.setPreferredWidth(80);
            columnWidth.setResizable(false);

            addMouseListener(this);
            m_sp.addMouseListener(this);
        }

        /**
         * ��ȡ��ͼ��
         * @return ��ͼ��
         */
        public JPanel getView() {
            return m_view;
        }

        /**
         * �ж�����δѡ������Ƿ�ȫ�����ء�
         * @return Ϊtrue��ʾ����δѡ�������ȫ�����أ�Ϊfalse��ʾ����δѡ����в���ȫ�����ء�
         */
        public boolean isHideDeselectedRows() {
            return getConfigTableModel().isHide(getDeselectedRows());
        }

        /**
         * ��ȡ����δѡ����С�
         * @return ����δѡ����С�
         */
        public int[] getDeselectedRows() {
            int rowCount = getRowCount();
            int[] selectedRows = getSelectedRows();
            int[] deselectedRows = new int[rowCount - selectedRows.length];

            for (int i = 0, cursor = 0; i < rowCount; i++) {
                if (!BaseUtilities.contains(selectedRows, i)) {
                    deselectedRows[cursor++] = i;
                }
            }

            return deselectedRows;
        }

        /**
         * @see javax.swing.JTable#prepareRenderer(javax.swing.table.TableCellRenderer, int, int)
         */
        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
            Component rc = super.prepareRenderer(renderer, row, column);
            rc.setEnabled(isCellEditable(row, column));
            return rc;
        }

        /**
         * @see javax.swing.JTable#tableChanged(javax.swing.event.TableModelEvent)
         */
        public void tableChanged(TableModelEvent evt) {
            super.tableChanged(evt);
            constrain();

            /* ȫˢ��Ϊ����ϡ���ʾ���е�Լ��ˢ�£�TODO ���ﵽ�ǿ����Ż����������ڱ��С�������ܲ����ж�����������Ծ���ʡ�´����� */
            repaint();
        }

        /**
         * @see javax.swing.JTable#valueChanged(javax.swing.event.ListSelectionEvent)
         */
        public void valueChanged(ListSelectionEvent evt) {
            super.valueChanged(evt);
            constrain();
        }

        /**
         * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
         */
        public void mouseEntered(MouseEvent evt) {
            if (evt.getSource() == m_sp) {
                mouseClicked(evt);
            }
        }

        /**
         * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
         */
        public void mouseClicked(MouseEvent evt) {
            if (SwingUtilities.isRightMouseButton(evt)) {
                JComponent source = (JComponent) evt.getSource();

                if (source == TConfigTable.this) {
                    int row = rowAtPoint(evt.getPoint());

                    if (row != -1) {
                        ListSelectionModel sm = getSelectionModel();

                        if (!sm.isSelectedIndex(row)) {
                            sm.setSelectionInterval(row, row);
                        }
                    }
                }

                m_menu.show(source, evt.getX(), evt.getY());
            }
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

        /**
         * ������ͼ��
         */
        protected void buildView() {
            m_sp.setViewportView(this);

            String title = isRowHeader() ? I18N_INFO_ROWHEADER : I18N_INFO_BODY;
            m_view.setBorder(BorderFactory.createTitledBorder(title));

            Dimension size = new Dimension(250, 300);
            m_view.setPreferredSize(size);
            m_view.setMinimumSize(size);

            m_view.add(m_sp, new GridBagConstraints(0,
                                                    0,
                                                    3,
                                                    1,
                                                    1.0,
                                                    1.0,
                                                    GridBagConstraints.WEST,
                                                    GridBagConstraints.BOTH,
                                                    INSETS_DEFAULT,
                                                    IPADX_DEFAULT,
                                                    IPADY_DEFAULT));
            m_view.add(m_allColumnWidthLabel, new GridBagConstraints(0,
                                                                     1,
                                                                     1,
                                                                     1,
                                                                     0.0,
                                                                     0.0,
                                                                     GridBagConstraints.WEST,
                                                                     GridBagConstraints.NONE,
                                                                     INSETS_DEFAULT,
                                                                     IPADX_DEFAULT,
                                                                     IPADY_DEFAULT));
            m_view.add(m_allColumnWidthField, new GridBagConstraints(1,
                                                                     1,
                                                                     1,
                                                                     1,
                                                                     1.0,
                                                                     0.0,
                                                                     GridBagConstraints.WEST,
                                                                     GridBagConstraints.HORIZONTAL,
                                                                     INSETS_DEFAULT,
                                                                     IPADX_DEFAULT,
                                                                     IPADY_DEFAULT));
            m_view.add(new JButton(m_set), new GridBagConstraints(2,
                                                                  1,
                                                                  1,
                                                                  1,
                                                                  0.0,
                                                                  0.0,
                                                                  GridBagConstraints.WEST,
                                                                  GridBagConstraints.NONE,
                                                                  INSETS_DEFAULT,
                                                                  IPADX_DEFAULT,
                                                                  IPADY_DEFAULT));
        }

        /**
         * Լ����
         */
        protected void constrain() {
            if (m_show == null) {
                return;
            }

            int[] rows = getSelectedRows();

            if (rows.length == 0) {
                m_show.setEnabled(false);
                m_hide.setEnabled(false);
                m_moveToUp.setEnabled(false);
                m_moveToDown.setEnabled(false);

            } else {
                m_show.setEnabled(true);

                if (isHideDeselectedRows()) {
                    m_hide.setEnabled(false);

                } else {
                    m_hide.setEnabled(true);
                }

                Arrays.sort(rows);

                boolean isRowHeader = isRowHeader();

                if (rows[0] == 0 || (isRowHeader && rows[0] == 1)) {
                    m_moveToUp.setEnabled(false);

                } else {
                    m_moveToUp.setEnabled(true);
                }

                if (rows[rows.length - 1] == getRowCount() - 1 || (isRowHeader && rows[0] == 0)) {
                    m_moveToDown.setEnabled(false);

                } else {
                    m_moveToDown.setEnabled(true);
                }
            }
        }

        /**
         * ֹͣ�༭��
         */
        protected void stopEditing() {
            if (isEditing()) {
                removeEditor();
            }
        }

        /**
         * ���á�
         */
        protected void reset() {
            getConfigTableModel().resetFromTable(m_table);
            constrainAllColumnWidth();
        }

        /**
         * Լ����
         */
        protected void constrainAllColumnWidth() {
            long minWidth = Long.MIN_VALUE;
            long maxWidth = Long.MAX_VALUE;

            List customs = getConfigTableModel().m_customs;
            for (int i = 0, size = customs.size(); i < size; i++) {
                TableColumn column = ((TableCustom) customs.get(i)).m_column;
                minWidth = Math.max(minWidth, column.getMinWidth());
                maxWidth = Math.min(maxWidth, column.getMaxWidth());
            }

            if (minWidth < maxWidth) {
                m_allColumnWidthField.setEnabledPopupTip(true);
                m_allColumnWidthField.setEnabled(true);
                m_set.setEnabled(true);

                m_allColumnWidthField.setMinimum(null);
                m_allColumnWidthField.setMaximum(null);
                m_allColumnWidthField.setMinimum(minWidth);
                m_allColumnWidthField.setMaximum(maxWidth);
                m_allColumnWidthField.setInteger((minWidth < 75 && 75 < maxWidth) ? 75 : minWidth);

            } else {
                m_allColumnWidthField.setEnabledPopupTip(false);
                m_allColumnWidthField.setEnabled(false);
                m_set.setEnabled(false);

                m_allColumnWidthField.setMinimum(null);
                m_allColumnWidthField.setMaximum(null);
                m_allColumnWidthField.setInteger(75);
            }
        }

        /**
         * ��ȡ���ñ��ģ�͡�
         * @return ���ñ��ģ�͡�
         */
        protected TConfigTableModel getConfigTableModel() {
            return (TConfigTableModel) dataModel;
        }

        /**
         * �ж��Ƿ�����ͷ��
         * @return Ϊtrue��ʾ����ͷ��Ϊfalse��ʾ������ͷ��
         */
        protected boolean isRowHeader() {
            return m_table instanceof TableRowHeader;
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

    /**
     * �����б��ƶԻ���
     * @param body ���塣
     * @param owner ӵ�иöԻ����������
     * @param customKey �б��ƹؼ��֡�
     * @return �б��ƶԻ���
     */
    public static TableCustomDialog create(JTable body, Component owner, String customKey) {
        return create(body, null, owner, customKey);
    }

    /**
     * �����б��ƶԻ���
     * @param body ���塣
     * @param rowHeader ��ͷ��
     * @param owner ӵ�иöԻ����������
     * @param customKey �б��ƹؼ��֡�
     * @return �б��ƶԻ���
     */
    public static TableCustomDialog create(JTable body, TableRowHeader rowHeader, Component owner, String customKey) {
        return create(body, rowHeader, owner, null, true, customKey);
    }

    /**
     * �����б��ƶԻ���
     * @param body ���塣
     * @param rowHeader ��ͷ��
     * @param owner ӵ�иöԻ����������
     * @param title �Ի�����⡣
     * @param isModal Ϊtrue��ʾ��ģ̬�Ի���Ϊfalse��ʾ�Ƿ�ģ̬�Ի���
     * @param customKey �б��ƹؼ��֡�
     * @return �б��ƶԻ���
     */
    public static TableCustomDialog create(JTable body,
                                           TableRowHeader rowHeader,
                                           Component owner,
                                           String title,
                                           boolean isModal,
                                           String customKey) {
        Window win = BaseUtilities.getWindow(owner);

        if (win instanceof Frame) {
            return new TableCustomDialog(body, rowHeader, (Frame) win, title, isModal, customKey);

        } else if (win instanceof Dialog) {
            return new TableCustomDialog(body, rowHeader, (Dialog) win, title, isModal, customKey);

        } else {
            return new TableCustomDialog(body, rowHeader, (Frame) null, title, isModal, customKey);
        }
    }

    /*------------------------------------- Protected Static Method -------------------------------------*/

    /**
     * ��ȡ���⡣
     * @param title ���⡣
     * @return �������Ϊ�վͷ���ȱʡ���⡣
     */
    protected static String getTitle(String title) {
        return title == null ? I18N_INFO_TABLECUSTOM : title;
    }

    /**
     * �ƶ�ѡ����С�
     * @param from ���Ե����ñ��
     * @param to �ƶ��������ñ��
     */
    protected static void moveColumns(TConfigTable from, TConfigTable to) {
        from.stopEditing();
        to.stopEditing();

        TConfigTableModel fromCtm = from.getConfigTableModel();
        TConfigTableModel toCtm = to.getConfigTableModel();
        ListSelectionModel toSm = to.getSelectionModel();

        int[] willMoveRows = from.getSelectedRows();
        Arrays.sort(willMoveRows);
        toSm.clearSelection();

        for (int i = willMoveRows.length - 1, index = toCtm.getRowCount(); i >= 0; i--) {
            toCtm.m_customs.add(index, fromCtm.m_customs.remove(willMoveRows[i]));

            fromCtm.fireTableRowsDeleted(willMoveRows[i], willMoveRows[i]);
            toCtm.fireTableRowsInserted(index, index);

            toSm.addSelectionInterval(index, index);
        }

        from.constrainAllColumnWidth();
        to.constrainAllColumnWidth();
    }

    protected static void restoreDefaults(TConfigTable ct) {
        TableUtilities.restoreDefaultCustoms(ct.m_table);
        ct.getConfigTableModel().syncCustomsFromTable(ct.m_table);
        ct.constrainAllColumnWidth();
    }

    /**
     * Ӧ�á�
     * @param ct ���ñ��
     */
    protected static void apply(TConfigTable ct) {
        ct.stopEditing();

        TConfigTableModel ctm = ct.getConfigTableModel();
        TableUtilities.setTableCustoms(ct.m_table, new ArrayList(ctm.m_customs));

        TableColumnModel cm = ct.m_table.getColumnModel();
        TableUtilities.clear(cm);

        for (int i = 0, size = ctm.m_customs.size(); i < size; i++) {
            TableCustom custom = (TableCustom) ctm.m_customs.get(i);
            custom.syncWidthToColumn();

            if (custom.m_visible.booleanValue()) {
                cm.addColumn(custom.m_column);
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
     * �б��ƹؼ��֡�
     */
    protected String m_customKey;

    /**
     * �����ƶ�ѡ���е���ͷ������
     */
    protected Action m_moveToLeft = new AbstractAction() {

        {
            BaseUtilities.setAction(this, I18N_INFO_MOVETOLEFT, 'L', ICON_MOVETOLEFT);
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            moveColumns(m_bodyCt, m_rhCt);
        }

    };

    /**
     * ��ͷ�ƶ�ѡ���е����嶯����
     */
    protected Action m_moveToRight = new AbstractAction() {

        {
            BaseUtilities.setAction(this, I18N_INFO_MOVETORIGHT, 'R', ICON_MOVETORIGHT);
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            moveColumns(m_rhCt, m_bodyCt);
        }

    };

    /**
     * �ָ�ȱʡֵ������
     */
    protected Action m_restoreDefaults = new AbstractAction() {

        {
            BaseUtilities.setAction(this, I18N_INFO_RESTOREDEFAULTS, 'D');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            restoreDefaults();
        }

    };

    /**
     * Ӧ�ö�����
     */
    protected Action m_apply = new AbstractAction() {

        {
            BaseUtilities.setAction(this, I18N_INFO_APPLY, 'A');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            apply();
        }

    };

    /**
     * ȷ��������
     */
    protected Action m_ok = new AbstractAction() {

        {
            BaseUtilities.setAction(this, I18N_INFO_OK);
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_apply.actionPerformed(evt);
            dispose();

            if (m_customKey != null && !"".equals(m_customKey)) {
                TableUtilities.saveCustom(m_customKey, m_rhCt == null ? m_bodyCt.m_table : m_rhCt.m_table);
            }
        }

    };

    /**
     * ȡ��������
     */
    protected Action m_cancel = new AbstractAction() {

        {
            BaseUtilities.setAction(this, I18N_INFO_CANCEL);
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            dispose();
        }

    };

    /**
     * ��������ñ��
     */
    protected TConfigTable m_bodyCt;

    /**
     * ��ͷ�����ñ��
     */
    protected TConfigTable m_rhCt;

    /**
     * ���ư�ť��
     */
    protected JButton m_moveToUpBtn = new JButton();

    /**
     * ���ư�ť��
     */
    protected JButton m_moveToDownBtn = new JButton();

    /**
     * ȡ����ť��
     */
    protected JButton m_cancelBtn = new JButton(m_cancel);

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     * @param body ���塣
     * @param rowHeader ��ͷ��
     * @param customKey �б��ƹؼ��֡�
     */
    public TableCustomDialog(JTable body, TableRowHeader rowHeader, String customKey) {
        super((Frame) null, getTitle(null));
        initialize(body, rowHeader, customKey);
    }

    /**
     * ���췽����
     * @param body ���塣
     * @param rowHeader ��ͷ��
     * @param owner ӵ�иöԻ����������
     * @param title �Ի�����⡣
     * @param isModal Ϊtrue��ʾ��ģ̬�Ի���Ϊfalse��ʾ�Ƿ�ģ̬�Ի���
     * @param customKey �б��ƹؼ��֡�
     */
    public TableCustomDialog(JTable body, TableRowHeader rowHeader, Frame owner, String title, boolean isModal, String customKey) {
        super(owner, getTitle(title), isModal);
        initialize(body, rowHeader, customKey);
    }

    /**
     * ���췽����
     * @param body ���塣
     * @param rowHeader ��ͷ��
     * @param owner ӵ�иöԻ����������
     * @param title �Ի�����⡣
     * @param isModal Ϊtrue��ʾ��ģ̬�Ի���Ϊfalse��ʾ�Ƿ�ģ̬�Ի���
     * @param customKey �б��ƹؼ��֡�
     */
    public TableCustomDialog(JTable body, TableRowHeader rowHeader, Dialog owner, String title, boolean isModal, String customKey) {
        super(owner, getTitle(title), isModal);
        initialize(body, rowHeader, customKey);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /* Add Public Method */

    /*------------------------------------- Dialog Public Method -------------------------------------*/

    /**
     * @see java.awt.Dialog#show()
     */
    @SuppressWarnings("deprecation")
    public void show() {
        reset();
        super.show();
    }

    /*------------------------------------- WindowListener Public Method -------------------------------------*/

    /**
     * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
     */
    public void windowOpened(WindowEvent evt) {
        m_bodyCt.getSelectionModel().setSelectionInterval(0, 0);
        m_cancelBtn.requestFocus();
    }

    /**
     * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
     */
    public void windowClosed(WindowEvent evt) {
        /* �ݲ���ʵ�� */
    }

    /**
     * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
     */
    public void windowClosing(WindowEvent evt) {
        /* �ݲ���ʵ�� */
    }

    /**
     * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
     */
    public void windowActivated(WindowEvent evt) {
        /* �ݲ���ʵ�� */
    }

    /**
     * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
     */
    public void windowDeactivated(WindowEvent evt) {
        /* �ݲ���ʵ�� */
    }

    /**
     * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
     */
    public void windowIconified(WindowEvent evt) {
        /* �ݲ���ʵ�� */
    }

    /**
     * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
     */
    public void windowDeiconified(WindowEvent evt) {
        /* �ݲ���ʵ�� */
    }

    /*------------------------------------- WindowFocusListener Public Method -------------------------------------*/

    /**
     * @see java.awt.event.WindowFocusListener#windowGainedFocus(java.awt.event.WindowEvent)
     */
    public void windowGainedFocus(WindowEvent evt) {
        stopEditing();
    }

    /**
     * @see java.awt.event.WindowFocusListener#windowLostFocus(java.awt.event.WindowEvent)
     */
    public void windowLostFocus(WindowEvent evt) {
        stopEditing();
    }

    /*------------------------------------- TableModelListener Public Method -------------------------------------*/

    /**
     * @see javax.swing.event.TableModelListener#tableChanged(javax.swing.event.TableModelEvent)
     */
    public void tableChanged(TableModelEvent evt) {
        constrain();
    }

    /*------------------------------------- ListSelectionListener Public Method -------------------------------------*/

    /**
     * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
     */
    public void valueChanged(ListSelectionEvent evt) {
        constrain();

        Object source = evt.getSource();
        ListSelectionModel bodyCtSm = m_bodyCt.getSelectionModel();
        ListSelectionModel rhCtSm = m_rhCt.getSelectionModel();

        if (source == bodyCtSm && m_bodyCt.getSelectedRows().length != 0) {
            BaseUtilities.setIconButton(m_moveToUpBtn, m_bodyCt.m_moveToUp);
            BaseUtilities.setIconButton(m_moveToDownBtn, m_bodyCt.m_moveToDown);

            rhCtSm.clearSelection();
            m_bodyCt.requestFocus();

        } else if (source == rhCtSm && m_rhCt.getSelectedRows().length != 0) {
            BaseUtilities.setIconButton(m_moveToUpBtn, m_rhCt.m_moveToUp);
            BaseUtilities.setIconButton(m_moveToDownBtn, m_rhCt.m_moveToDown);

            bodyCtSm.clearSelection();
            m_rhCt.requestFocus();
        }
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * Լ����
     */
    protected void constrain() {
        int[] rows = m_bodyCt.getSelectedRows();

        if (rows.length == 0 || rows.length == m_bodyCt.getRowCount() || m_bodyCt.isHideDeselectedRows()) {
            m_moveToLeft.setEnabled(false);

        } else {
            m_moveToLeft.setEnabled(true);
        }

        rows = m_rhCt.getSelectedRows();
        Arrays.sort(rows);

        if (rows.length == 0 || rows.length == m_rhCt.getRowCount() || rows[0] == 0 || m_rhCt.isHideDeselectedRows()) {
            m_moveToRight.setEnabled(false);

        } else {
            m_moveToRight.setEnabled(true);
        }
    }

    /**
     * ֹͣ�༭��
     */
    protected void stopEditing() {
        m_bodyCt.stopEditing();

        if (m_rhCt != null) {
            m_rhCt.stopEditing();
        }
    }

    /**
     * ���á�
     */
    protected void reset() {
        m_bodyCt.reset();

        if (m_rhCt != null) {
            m_rhCt.reset();
        }
    }

    /**
     * �ָ�ȱʡֵ��
     */
    protected void restoreDefaults() {
        restoreDefaults(m_bodyCt);

        if (m_rhCt != null) {
            restoreDefaults(m_rhCt);
        }

        apply();
    }

    /**
     * Ӧ�á�
     */
    protected void apply() {
        apply(m_bodyCt);

        if (m_rhCt != null) {
            apply(m_rhCt);
        }
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /**
     * ��ʼ����
     * @param body ���塣
     * @param rowHeader ��ͷ��
     * @param customKey �б��ƹؼ��֡�
     */
    private void initialize(JTable body, TableRowHeader rowHeader, String customKey) {
        if (body == null) {
            throw new NullPointerException("Body is null !");
        }

        m_customKey = customKey;
        m_bodyCt = new TConfigTable(body);
        m_rhCt = rowHeader == null ? null : new TConfigTable(rowHeader);

        JPanel view = new JPanel(new GridBagLayout());
        JPanel mtView = m_bodyCt.getView();
        JPanel operationView = createOperationView();

        if (m_rhCt == null) {
            view.add(mtView, new GridBagConstraints(0,
                                                    0,
                                                    1,
                                                    1,
                                                    1.0,
                                                    1.0,
                                                    GridBagConstraints.WEST,
                                                    GridBagConstraints.BOTH,
                                                    INSETS_DEFAULT,
                                                    IPADX_DEFAULT,
                                                    IPADY_DEFAULT));
            view.add(operationView, new GridBagConstraints(0,
                                                           1,
                                                           1,
                                                           1,
                                                           1.0,
                                                           0.0,
                                                           GridBagConstraints.WEST,
                                                           GridBagConstraints.HORIZONTAL,
                                                           INSETS_DEFAULT,
                                                           IPADX_DEFAULT,
                                                           IPADY_DEFAULT));

        } else {
            m_bodyCt.m_menu.addSeparator();
            m_bodyCt.m_menu.add(m_moveToLeft);
            m_rhCt.m_menu.addSeparator();
            m_rhCt.m_menu.add(m_moveToRight);

            TableUtilities.addTableModelListener(m_rhCt, this);
            TableUtilities.addTableModelListener(m_bodyCt, this);
            TableUtilities.addListSelectionListener(m_rhCt, this);
            TableUtilities.addListSelectionListener(m_bodyCt, this);

            constrain();

            JPanel rhView = m_rhCt.getView();
            JPanel moveView = createMoveView();

            view.add(rhView, new GridBagConstraints(0,
                                                    0,
                                                    1,
                                                    1,
                                                    0.5,
                                                    1.0,
                                                    GridBagConstraints.WEST,
                                                    GridBagConstraints.BOTH,
                                                    INSETS_DEFAULT,
                                                    IPADX_DEFAULT,
                                                    IPADY_DEFAULT));
            view.add(moveView, new GridBagConstraints(1,
                                                      0,
                                                      1,
                                                      1,
                                                      0.0,
                                                      1.0,
                                                      GridBagConstraints.WEST,
                                                      GridBagConstraints.VERTICAL,
                                                      INSETS_DEFAULT,
                                                      IPADX_DEFAULT,
                                                      IPADY_DEFAULT));
            view.add(mtView, new GridBagConstraints(2,
                                                    0,
                                                    1,
                                                    1,
                                                    0.5,
                                                    1.0,
                                                    GridBagConstraints.WEST,
                                                    GridBagConstraints.BOTH,
                                                    INSETS_DEFAULT,
                                                    IPADX_DEFAULT,
                                                    IPADY_DEFAULT));
            view.add(operationView, new GridBagConstraints(0,
                                                           1,
                                                           3,
                                                           1,
                                                           1.0,
                                                           0.0,
                                                           GridBagConstraints.WEST,
                                                           GridBagConstraints.HORIZONTAL,
                                                           INSETS_DEFAULT,
                                                           IPADX_DEFAULT,
                                                           IPADY_DEFAULT));
        }

        addWindowListener(this);
        addWindowFocusListener(this);
        addComponentListener(BaseUtilities.createSizeLimiter());

        setContentPane(view);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        rootPane.setDefaultButton(m_cancelBtn);
        pack();

        BaseUtilities.center(this);
    }

    /**
     * �����ƶ���ͼ��
     * @return �ƶ���ͼ��
     */
    private JPanel createMoveView() {
        JPanel moveView = new JPanel(new GridBagLayout());

        moveView.add(m_moveToUpBtn, new GridBagConstraints(0,
                                                           0,
                                                           1,
                                                           1,
                                                           1.0,
                                                           0.0,
                                                           GridBagConstraints.CENTER,
                                                           GridBagConstraints.HORIZONTAL,
                                                           INSETS_DEFAULT,
                                                           IPADX_DEFAULT,
                                                           IPADY_DEFAULT));
        moveView.add(m_moveToDownBtn, new GridBagConstraints(0,
                                                             1,
                                                             1,
                                                             1,
                                                             1.0,
                                                             0.0,
                                                             GridBagConstraints.CENTER,
                                                             GridBagConstraints.HORIZONTAL,
                                                             INSETS_DEFAULT,
                                                             IPADX_DEFAULT,
                                                             IPADY_DEFAULT));
        moveView.add(BaseUtilities.getIconButton(m_moveToLeft), new GridBagConstraints(0,
                                                                                       2,
                                                                                       1,
                                                                                       1,
                                                                                       1.0,
                                                                                       0.0,
                                                                                       GridBagConstraints.CENTER,
                                                                                       GridBagConstraints.HORIZONTAL,
                                                                                       INSETS_DEFAULT,
                                                                                       IPADX_DEFAULT,
                                                                                       IPADY_DEFAULT));
        moveView.add(BaseUtilities.getIconButton(m_moveToRight), new GridBagConstraints(0,
                                                                                        3,
                                                                                        1,
                                                                                        1,
                                                                                        1.0,
                                                                                        0.0,
                                                                                        GridBagConstraints.CENTER,
                                                                                        GridBagConstraints.HORIZONTAL,
                                                                                        INSETS_DEFAULT,
                                                                                        IPADX_DEFAULT,
                                                                                        IPADY_DEFAULT));

        return moveView;
    }

    /**
     * ����������ͼ��
     * @return ������ͼ��
     */
    private JPanel createOperationView() {
        JPanel operationView = new JPanel(new GridBagLayout());

        operationView.add(new JLabel(), new GridBagConstraints(0,
                                                               0,
                                                               1,
                                                               1,
                                                               1.0,
                                                               0.0,
                                                               GridBagConstraints.WEST,
                                                               GridBagConstraints.HORIZONTAL,
                                                               INSETS_DEFAULT,
                                                               IPADX_DEFAULT,
                                                               IPADY_DEFAULT));
        operationView.add(new JButton(m_restoreDefaults), new GridBagConstraints(1,
                                                                                 0,
                                                                                 1,
                                                                                 1,
                                                                                 0.0,
                                                                                 0.0,
                                                                                 GridBagConstraints.EAST,
                                                                                 GridBagConstraints.NONE,
                                                                                 INSETS_DEFAULT,
                                                                                 IPADX_DEFAULT,
                                                                                 IPADY_DEFAULT));
        operationView.add(new JButton(m_apply), new GridBagConstraints(2,
                                                                       0,
                                                                       1,
                                                                       1,
                                                                       0.0,
                                                                       0.0,
                                                                       GridBagConstraints.EAST,
                                                                       GridBagConstraints.NONE,
                                                                       INSETS_DEFAULT,
                                                                       IPADX_DEFAULT,
                                                                       IPADY_DEFAULT));
        operationView.add(new JButton(m_ok), new GridBagConstraints(3,
                                                                    0,
                                                                    1,
                                                                    1,
                                                                    0.0,
                                                                    0.0,
                                                                    GridBagConstraints.EAST,
                                                                    GridBagConstraints.NONE,
                                                                    INSETS_DEFAULT,
                                                                    IPADX_DEFAULT,
                                                                    IPADY_DEFAULT));
        operationView.add(m_cancelBtn, new GridBagConstraints(4,
                                                              0,
                                                              1,
                                                              1,
                                                              0.0,
                                                              0.0,
                                                              GridBagConstraints.EAST,
                                                              GridBagConstraints.NONE,
                                                              INSETS_DEFAULT,
                                                              IPADX_DEFAULT,
                                                              IPADY_DEFAULT));

        return operationView;
    }

}
