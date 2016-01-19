/*
 * �˴��봴���� 2007-6-8 ����09:37:37
 */
package com.apollo.swing.component.common.jtable;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * <p>�ļ����ƣ�TRowHeaderTable.java</p>
 * <p>�ļ���������ͷ��ؼ��ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004</p>
 * <p>������˾��������</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2007-7-2</p>
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
public final class RowHeaderTable extends JTable {

    /*------------------------------------- Public Static Field -------------------------------------*/

    /* Add Public Static Field */

    /*------------------------------------- Public Field -------------------------------------*/

    /* Add Public Field */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     * @param targetTable Ŀ����
     * @param targetColumnModelIndexs Ŀ����������
     * @exception IllegalArgumentException ���ò����Ƿ�ʱ���׳����쳣��
     */
    public RowHeaderTable(JTable targetTable, int[] targetColumnModelIndexs) {
        if (targetTable == null) { //Ŀ���Ϊ��
            throw new IllegalArgumentException("Target table is null !");
        }

        if (targetColumnModelIndexs == null || targetColumnModelIndexs.length == 0) { //Ŀ����Ϊ��
            throw new IllegalArgumentException("Target column view index array is null or empty !");
        }

        /* ��ʼ������ */
        m_targetTable = targetTable;
        m_targetColumnModelIndexs = targetColumnModelIndexs;

        /* ���ø���ģ�� */
        setModel(m_targetTable.getModel()); //��ͷ������ģ�;���Ŀ��������ģ��
        setSelectionModel(m_targetTable.getSelectionModel()); //��ͷ����ѡ��ģ�;���Ŀ������ѡ��ģ��

        /* �������� */
        setAutoResizeMode(AUTO_RESIZE_OFF);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setResizingAllowed(false);
        updatePreferredScrollableViewportSize(); //ˢ��һ����ͷ������ӿڵĳߴ�

        /* ��������ƶ���������������̷�����������ͷ���������Ŀ����ӿڣ�δ�������������� */
        addComponentListener(new ComponentAdapter() {

            /**
             * @see java.awt.event.ComponentAdapter#componentMoved(java.awt.event.ComponentEvent)
             */
            public void componentMoved(ComponentEvent evt) {
                Container parent = m_targetTable.getParent();

                if (parent instanceof JViewport) { //Ŀ����Ƿ��ڹ���������
                    JViewport viewport = (JViewport) parent;

                    int offsetY = getVisibleRect().y;
                    Point viewPosition = viewport.getViewPosition();

                    if (viewPosition.y != offsetY) { //Ŀ���λ�û�δ�ƶ���
                        viewPosition.y = offsetY;
                        viewport.setViewPosition(viewPosition);
                    }
                }
            }

        });

        /* ��ʼ��Ŀ�����ģ�ͼ����� */
        m_targetTableColumnModelListener = new TableColumnModelListener() {

            /**
             * @see javax.swing.event.TableColumnModelListener#columnAdded(javax.swing.event.TableColumnModelEvent)
             */
            public void columnAdded(TableColumnModelEvent evt) {
                clearTargetColumnFromTargetTable();
            }

            /**
             * @see javax.swing.event.TableColumnModelListener#columnRemoved(javax.swing.event.TableColumnModelEvent)
             */
            public void columnRemoved(TableColumnModelEvent evt) {
                clearTargetColumnFromTargetTable();
            }

            /**
             * @see javax.swing.event.TableColumnModelListener#columnMarginChanged(javax.swing.event.ChangeEvent)
             */
            public void columnMarginChanged(ChangeEvent evt) {
                /* �ݲ���ʵ�ִ˷��� */
            }

            /**
             * @see javax.swing.event.TableColumnModelListener#columnMoved(javax.swing.event.TableColumnModelEvent)
             */
            public void columnMoved(TableColumnModelEvent evt) {
                /* �ݲ���ʵ�ִ˷��� */
            }

            /**
             * @see javax.swing.event.TableColumnModelListener#columnSelectionChanged(javax.swing.event.ListSelectionEvent)
             */
            public void columnSelectionChanged(ListSelectionEvent evt) {
                /* �ݲ���ʵ�ִ˷��� */
            }

        };
        m_targetTable.getColumnModel().addColumnModelListener(m_targetTableColumnModelListener);
        clearTargetColumnFromTargetTable(); //������������ϲ��������������ã�������Ҫ�ֹ����һ��Ŀ����ϲ�Ӧ�ô��ڵ�Ŀ����

        /* ����Ŀ�������Ըı������ */
        m_targetTable.addPropertyChangeListener(new PropertyChangeListener() {

            /**
             * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
             */
            public void propertyChange(PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();

                if ("rowHeight".equals(propertyName)) { //Ŀ����и߱仯
                    int newRowHeight = ((Integer) evt.getNewValue()).intValue();
                    setRowHeight(newRowHeight);

                } else if ("rowMargin".equals(propertyName)) { //Ŀ����м��仯
                    int newRowMargin = ((Integer) evt.getNewValue()).intValue();
                    setRowMargin(newRowMargin);

                } else if ("columnModel".equals(propertyName)) { //Ŀ����ģ�ͷ����仯
                    TableColumnModel oldCm = (TableColumnModel) evt.getOldValue();
                    if (oldCm != null) { //�ɵ���ģ�Ͳ�Ϊ��
                        oldCm.removeColumnModelListener(m_targetTableColumnModelListener); //��ģ���Ƴ���ؼ�����
                    }

                    TableColumnModel newCm = (TableColumnModel) evt.getNewValue();
                    newCm.addColumnModelListener(m_targetTableColumnModelListener); //��ģ���Ƴ���ؼ�����
                }
            }

        });

        /* ��ֹĿ������������϶����� */
        TableHeaderController thc = new TableHeaderController(m_targetTable.getTableHeader());
        thc.addDisabledMovingLeftColumnViewIndex(0);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Static Method -------------------------------------*/

    /* Add Public Static Method */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * ��ȡĿ���
     * @return Ŀ���
     */
    public JTable getTargetTable() {
        return m_targetTable;
    }

    /*------------------------------------- JTable Public Method -------------------------------------*/

    /**
     * @see javax.swing.JTable#getCellRenderer(int, int)
     */
    public TableCellRenderer getCellRenderer(int row, int column) {
        /* TODO Ŀǰ��Ⱦ����Ŀ������Ⱦ������һ�£�����ҪĿ�������Ⱦ�������⴦����֧�����ַ�ʽ */
        return m_targetTable.getCellRenderer(row, column);
    }

    /**
     * ֻ�����涨����ͼ�С�
     * @see javax.swing.JTable#createDefaultColumnsFromModel()
     */
    public void createDefaultColumnsFromModel() {
        if (m_targetTable == null || m_targetColumnModelIndexs == null) { //��ͷ��û�г�ʼ��
            super.createDefaultColumnsFromModel();

        } else { //��ͷ���Ѿ���ʼ��������ʵʱ�仯��ͷ
            TableModel dm = getModel();
            if (dm != null) {
                /* ��յ�ǰȫ���� */
                TableColumnModel cm = getColumnModel();
                for (int i = 0, size = cm.getColumnCount(); i < size; i++) {
                    cm.removeColumn(cm.getColumn(0));
                }

                /* �����涨���� */
                for (int i = 0; i < m_targetColumnModelIndexs.length; i++) {
                    TableColumn newColumn = new TableColumn(m_targetColumnModelIndexs[i]);
                    addColumn(newColumn);
                }
            }
        }
    }

    /*------------------------------------- Protected Static Method -------------------------------------*/

    /* Add Protected Static Method */

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Static Method -------------------------------------*/

    /* Add Friendly Static Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Static Method -------------------------------------*/

    /* Add Private Static Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /**
     * ������ѡ�����ӿڵĴ�С��
     */
    private void updatePreferredScrollableViewportSize() {
        Dimension thps = getTableHeader().getPreferredSize();
        Dimension psvs = getPreferredScrollableViewportSize();

        psvs.width = thps.width;

        setPreferredScrollableViewportSize(psvs);
    }

    /**
     * ���Ŀ����ϲ�Ӧ�ô��ڵ�Ŀ���С�
     */
    private void clearTargetColumnFromTargetTable() {
        TableColumnModel cm = m_targetTable.getColumnModel();

        for (int i = 0; i < m_targetColumnModelIndexs.length; i++) {
            int viewIndex = m_targetTable.convertColumnIndexToView(m_targetColumnModelIndexs[i]);

            if (viewIndex >= 0) { //Ŀ���л�����
                cm.removeColumn(cm.getColumn(viewIndex));
            }
        }
    }

    /*------------------------------------- Public Static Inner Class -------------------------------------*/

    /* Add Public Static Inner Class */

    /*------------------------------------- Public Inner Class -------------------------------------*/

    /* Add Public Inner Class */

    /*------------------------------------- Protected Static Inner Class -------------------------------------*/

    /* Add Protected Static Inner Class */

    /*------------------------------------- Protected Inner Class -------------------------------------*/

    /* Add Protected Inner Class */

    /*------------------------------------- Friendly Static Inner Class -------------------------------------*/

    /* Add Friendly Static Inner Class */

    /*------------------------------------- Friendly Inner Class -------------------------------------*/

    /* Add Friendly Inner Class */

    /*------------------------------------- Private Static Inner Class -------------------------------------*/

    /* Add Private Static Inner Class */

    /*------------------------------------- Private Inner Class -------------------------------------*/

    /* Add Private Inner Class */

    /*------------------------------------- Protected Static Field -------------------------------------*/

    /* Add Protected Static Field */

    /*------------------------------------- Protected Field -------------------------------------*/

    /* Add Protected Field */

    /*------------------------------------- Friendly Static Field -------------------------------------*/

    /* Add Friendly Static Field */

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Static Field -------------------------------------*/

    /* Add Private Static Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /**
     * Ŀ���
     */
    private JTable m_targetTable;

    /**
     * Ŀ����ģ���������顣
     */
    private int[] m_targetColumnModelIndexs;

    /**
     * Ŀ�����ģ�ͼ�������
     */
    private TableColumnModelListener m_targetTableColumnModelListener;

    /*------------------------------------- Static Initial Block -------------------------------------*/

    /* Add Static Initial Block */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

}
