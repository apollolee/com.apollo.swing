/*
 * �˴��봴���� 2008-5-5 ����09:12:12��
 */
package com.apollo.swing.component.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.apollo.base.util.IBaseConstants;
import com.apollo.swing.component.complextable.ComplexTable;
import com.apollo.swing.component.complextable.ComplexTableCell;
import com.apollo.swing.component.complextable.ComplexTableColumn;
import com.apollo.swing.component.complextable.ComplexTableRow;

/**
 * <p>�ļ����ƣ�TableView.java</p>
 * <p>���������������ͼ�ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-5-5</p>
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
public class TableView extends JTable implements ITableConstants, PropertyChangeListener, MouseListener, IBaseConstants {

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

    /* Add Protected Static Method */

    /*------------------------------------- Friendly Static Method -------------------------------------*/

    /* Add Friendly Static Method */

    /*------------------------------------- Private Static Method -------------------------------------*/

    /* Add Private Static Method */

    /*------------------------------------- Public Field -------------------------------------*/

    /* Add Public Field */

    /*------------------------------------- Protected Field -------------------------------------*/

    /**
     * ��ͼԴ��
     */
    protected JTable m_source;

    /**
     * �˵���������
     */
    protected ITableMenuManager m_menuManager;

    /**
     * ��ʶ�Ƿ��Զ�������Ⱦ������������
     */
    protected boolean m_isAutoEnabledRenderer;

    /**
     * �˵���
     */
    protected JPopupMenu m_menu = new JPopupMenu();

    /**
     * ����ȱʡ������
     */
    protected List m_defaultActions = new ArrayList();

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     * @param source ��ͼԴ��
     * @param columnView ����ͼ��
     * @param menuManager �˵���������
     * @param isAutoEnabledRenderer ��ʶ�Ƿ��Զ�������Ⱦ������������
     */
    public TableView(JTable source, ColumnView columnView, ITableMenuManager menuManager, boolean isAutoEnabledRenderer) {
        super(source.getModel(), columnView, source.getSelectionModel());

        columnView.m_table = this;
        m_source = source;
        m_source.addPropertyChangeListener(this);

        m_menuManager = menuManager;
        m_isAutoEnabledRenderer = isAutoEnabledRenderer;

        setRowHeight(m_source.getRowHeight());
        setRowMargin(m_source.getRowMargin());
        setRowSelectionAllowed(m_source.getRowSelectionAllowed());
        setColumnSelectionAllowed(m_source.getColumnSelectionAllowed());

        setAutoCreateColumnsFromModel(false);

        addMouseListener(this);
        tableHeader.addMouseListener(this);
        TableUtilities.autoSyncCustoms(this);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * ��ȡ��ͼԴ��
     * @return ��ͼԴ��
     */
    public JTable getSource() {
        return m_source;
    }

    /**
     * ��ȡ����ͼ��
     * @return ����ͼ��
     */
    public ColumnView getColumnView() {
        return (ColumnView) columnModel;
    }

    /*------------------------------------- JTable Public Method -------------------------------------*/

    /**
     * @see javax.swing.JTable#getCellRenderer(int, int)
     */
    public TableCellRenderer getCellRenderer(int row, int column) {
        ColumnView columnView = getColumnView();

        if (columnView.isRowIdColumn(column)) {
            return super.getCellRenderer(row, 0);

        } else {
            return m_source.getCellRenderer(row, columnView.convertIndexToSource(column));
        }
    }

    /**
     * @see javax.swing.JTable#getCellEditor(int, int)
     */
    public TableCellEditor getCellEditor(int row, int column) {
        ColumnView columnView = getColumnView();

        if (columnView.isRowIdColumn(column)) {
            return super.getCellEditor(row, 0);

        } else {
            return m_source.getCellEditor(row, columnView.convertIndexToSource(column));
        }
    }

    /**
     * @see javax.swing.JTable#prepareRenderer(javax.swing.table.TableCellRenderer, int, int)
     */
    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {
        Component rc = super.prepareRenderer(renderer, rowIndex, columnIndex);

        ColumnView columnView = getColumnView();

        if (!columnView.isRowIdColumn(columnIndex)) {
            boolean isCellEditable = isCellEditable(rowIndex, columnIndex);

            if (m_isAutoEnabledRenderer) {
                rc.setEnabled(isCellEditable);
            }

            if (m_source instanceof ComplexTable) {
                ComplexTable ct = (ComplexTable) m_source;
                int sourceColumnIndex = columnView.convertIndexToSource(columnIndex);
                ComplexTableColumn column = (ComplexTableColumn) ct.getColumnModel().getColumn(columnIndex);
                ComplexTableRow row = ct.getRow(rowIndex);
                ComplexTableCell cell = ct.getCell(rowIndex, sourceColumnIndex);
                boolean columnHasRenderer = column.getCellRenderer() != null;
                boolean cellHasRenderer = cell.getRenderer() != null;
                String rowToolTipText = row.getToolTipText();
                String cellToolTipText = cell.getToolTipText();

                if (!columnHasRenderer && !cellHasRenderer) {
                    ((JComponent) rc).setToolTipText(cellToolTipText == null ? rowToolTipText : cellToolTipText);
                }

                if (cellHasRenderer || columnView.getColumn(columnIndex).getCellRenderer() != null) {
                    return rc;
                }

                if (!isCellSelected(rowIndex, sourceColumnIndex)) {
                    if (ct.isReadMode()) {
                        rc.setBackground(rowIndex % 2 == 1 ? Color_Bg_Row_Odd : Color_Bg_Row_Even);

                    } else {
                        Color background = ct.getRow(rowIndex).getBackground();

                        if (background == null) {
                            background = cell.getBackground();
                        }

                        rc.setBackground(background == null && !isCellEditable ? Color_Bg_Editable_False : background);
                    }
                }

                Color foreground = null;

                if (ct.isEnabledShowFlag() && cell.isChanged()) {
                    foreground = ct.getChangedCellForeground();
                }

                if (foreground == null) {
                    foreground = ct.getRow(rowIndex).getForeground();

                    if (foreground == null) {
                        foreground = cell.getForeground();
                    }
                }

                rc.setForeground(foreground);

                rc.setEnabled(ct.isEnabled());
            }
        }

        return rc;
    }

    /**
     * @see javax.swing.JTable#prepareEditor(javax.swing.table.TableCellEditor, int, int)
     */
    public Component prepareEditor(TableCellEditor editor, int row, int column) {
        Component ec = super.prepareEditor(editor, row, column);
        //TCommonUtilities.autoStopCellEditing(editor, ec);

        return ec;
    }

    /**
     * @see javax.swing.JTable#isCellEditable(int, int)
     */
    public boolean isCellEditable(int row, int column) {
        if (getColumnView().isRowIdColumn(column)) {
            return false;
        }

        return super.isCellEditable(row, column);
    }

    /**
     * @see javax.swing.JTable#getValueAt(int, int)
     */
    public Object getValueAt(int row, int column) {
        if (getColumnView().isRowIdColumn(column)) {
            return new Integer(row + 1);
        }

        return super.getValueAt(row, column);
    }

    /**
     * @see javax.swing.JTable#columnAdded(javax.swing.event.TableColumnModelEvent)
     */
    public void columnAdded(TableColumnModelEvent evt) {
        super.columnAdded(evt);
        updateViewportSize();
    }

    /**
     * @see javax.swing.JTable#columnRemoved(javax.swing.event.TableColumnModelEvent)
     */
    public void columnRemoved(TableColumnModelEvent evt) {
        super.columnRemoved(evt);
        updateViewportSize();
    }

    /*------------------------------------- PropertyChangeListener Public Method -------------------------------------*/

    /**
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();

        if ("model".equals(propertyName)) { //Ŀ��ģ�ͷ����仯
            setModel((TableModel) evt.getNewValue());

        } else if ("columnModel".equals(propertyName)) { //Ŀ����ģ�ͷ����仯
            getColumnView().setSource((TableColumnModel) evt.getNewValue());

        } else if ("rowHeight".equals(propertyName)) { //Ŀ����и߱仯
            setRowHeight(((Integer) evt.getNewValue()).intValue());

        } else if ("rowMargin".equals(propertyName)) { //Ŀ����м��仯
            setRowMargin(((Integer) evt.getNewValue()).intValue());

        } else if ("rowSelectionAllowed".equals(propertyName)) {
            setRowSelectionAllowed(((Boolean) evt.getNewValue()).booleanValue());

        } else if ("columnSelectionAllowed".equals(propertyName)) {
            setColumnSelectionAllowed(((Boolean) evt.getNewValue()).booleanValue());
        }
    }

    /*------------------------------------- MouseListener Public Method -------------------------------------*/

    /**
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    public void mouseEntered(MouseEvent evt) {
        if (evt.getSource() == getParent().getParent()) {
            mouseClicked(evt);
        }
    }

    /**
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    public void mouseClicked(MouseEvent evt) {
        if (m_source.isEnabled() && SwingUtilities.isRightMouseButton(evt)) {
            resetMenu();

            JComponent source = (JComponent) evt.getSource();
            Point point = evt.getPoint();

            if (source == getParent().getParent()) {
                if (m_menuManager != null) {
                    m_menuManager.manageScrollPaneMenu(TableView.this, m_menu);
                }

            } else if (source == tableHeader) {
                int column = tableHeader.columnAtPoint(point);

                if (column == -1) {
                    return;
                }

                if (m_menuManager != null) {
                    m_menuManager.manageTableHeaderMenu(TableView.this, m_menu, column);
                }

            } else if (source == TableView.this) {
                int row = rowAtPoint(point);
                int column = columnAtPoint(point);

                if (row == -1 || column == -1) {
                    return;
                }

                if (m_menuManager != null) {
                    m_menuManager.manageTableMenu(TableView.this, m_menu, row, column);
                }

            } else {
                return;
            }

            if (m_menu.getComponentCount() > 0) {
                m_menu.show(source, evt.getX(), evt.getY());
            }
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

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * ������ѡ�����ӿڵĴ�С��
     */
    protected void updateViewportSize() {
        TableUtilities.updateViewportSize(this);
    }

    /**
     * ���ò˵���
     */
    protected void resetMenu() {
        m_menu.removeAll();

        for (int i = 0, size = m_defaultActions.size(); i < size; i++) {
            m_menu.add((Action) m_defaultActions.get(i));
        }

        if (m_menuManager != null) {
            m_menuManager.manageCommonMenu(TableView.this, m_menu);
        }

        m_menu.revalidate();
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
