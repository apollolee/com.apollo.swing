/*
 * 此代码创建于 2007-6-8 上午09:37:37
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
 * <p>文件名称：TRowHeaderTable.java</p>
 * <p>文件描述：行头表控件类。</p>
 * <p>版权所有：版权所有(C)2001-2004</p>
 * <p>公　　司：菠萝梨</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2007-7-2</p>
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
public final class RowHeaderTable extends JTable {

    /*------------------------------------- Public Static Field -------------------------------------*/

    /* Add Public Static Field */

    /*------------------------------------- Public Field -------------------------------------*/

    /* Add Public Field */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 构造方法。
     * @param targetTable 目标表格。
     * @param targetColumnModelIndexs 目标列索引。
     * @exception IllegalArgumentException 设置参数非法时则抛出此异常。
     */
    public RowHeaderTable(JTable targetTable, int[] targetColumnModelIndexs) {
        if (targetTable == null) { //目标表为空
            throw new IllegalArgumentException("Target table is null !");
        }

        if (targetColumnModelIndexs == null || targetColumnModelIndexs.length == 0) { //目标列为空
            throw new IllegalArgumentException("Target column view index array is null or empty !");
        }

        /* 初始化属性 */
        m_targetTable = targetTable;
        m_targetColumnModelIndexs = targetColumnModelIndexs;

        /* 设置各个模型 */
        setModel(m_targetTable.getModel()); //行头的数据模型就是目标表的数据模型
        setSelectionModel(m_targetTable.getSelectionModel()); //行头的行选择模型就是目标表的行选择模型

        /* 设置属性 */
        setAutoResizeMode(AUTO_RESIZE_OFF);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setResizingAllowed(false);
        updatePreferredScrollableViewportSize(); //刷新一次行头表滚动视口的尺寸

        /* 设置组件移动监听器；处理键盘方向键引起的行头表滚动，但目标表（视口）未发生滚动的情形 */
        addComponentListener(new ComponentAdapter() {

            /**
             * @see java.awt.event.ComponentAdapter#componentMoved(java.awt.event.ComponentEvent)
             */
            public void componentMoved(ComponentEvent evt) {
                Container parent = m_targetTable.getParent();

                if (parent instanceof JViewport) { //目标表是放在滚动窗格里
                    JViewport viewport = (JViewport) parent;

                    int offsetY = getVisibleRect().y;
                    Point viewPosition = viewport.getViewPosition();

                    if (viewPosition.y != offsetY) { //目标表位置还未移动过
                        viewPosition.y = offsetY;
                        viewport.setViewPosition(viewPosition);
                    }
                }
            }

        });

        /* 初始化目标表列模型监听器 */
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
                /* 暂不必实现此方法 */
            }

            /**
             * @see javax.swing.event.TableColumnModelListener#columnMoved(javax.swing.event.TableColumnModelEvent)
             */
            public void columnMoved(TableColumnModelEvent evt) {
                /* 暂不必实现此方法 */
            }

            /**
             * @see javax.swing.event.TableColumnModelListener#columnSelectionChanged(javax.swing.event.ListSelectionEvent)
             */
            public void columnSelectionChanged(ListSelectionEvent evt) {
                /* 暂不必实现此方法 */
            }

        };
        m_targetTable.getColumnModel().addColumnModelListener(m_targetTableColumnModelListener);
        clearTargetColumnFromTargetTable(); //监听器设置完毕并不能立即起作用，这里需要手工清除一次目标表上不应该存在的目标列

        /* 设置目标表的属性改变监听器 */
        m_targetTable.addPropertyChangeListener(new PropertyChangeListener() {

            /**
             * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
             */
            public void propertyChange(PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();

                if ("rowHeight".equals(propertyName)) { //目标表行高变化
                    int newRowHeight = ((Integer) evt.getNewValue()).intValue();
                    setRowHeight(newRowHeight);

                } else if ("rowMargin".equals(propertyName)) { //目标表行间距变化
                    int newRowMargin = ((Integer) evt.getNewValue()).intValue();
                    setRowMargin(newRowMargin);

                } else if ("columnModel".equals(propertyName)) { //目标列模型发生变化
                    TableColumnModel oldCm = (TableColumnModel) evt.getOldValue();
                    if (oldCm != null) { //旧的列模型不为空
                        oldCm.removeColumnModelListener(m_targetTableColumnModelListener); //旧模型移除相关监听器
                    }

                    TableColumnModel newCm = (TableColumnModel) evt.getNewValue();
                    newCm.addColumnModelListener(m_targetTableColumnModelListener); //新模型移除相关监听器
                }
            }

        });

        /* 禁止目标表首列左移拖动操作 */
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
     * 获取目标表。
     * @return 目标表。
     */
    public JTable getTargetTable() {
        return m_targetTable;
    }

    /*------------------------------------- JTable Public Method -------------------------------------*/

    /**
     * @see javax.swing.JTable#getCellRenderer(int, int)
     */
    public TableCellRenderer getCellRenderer(int row, int column) {
        /* TODO 目前渲染器与目标表的渲染器保持一致，这需要目标表在渲染器中特殊处理以支持这种方式 */
        return m_targetTable.getCellRenderer(row, column);
    }

    /**
     * 只创建规定的视图列。
     * @see javax.swing.JTable#createDefaultColumnsFromModel()
     */
    public void createDefaultColumnsFromModel() {
        if (m_targetTable == null || m_targetColumnModelIndexs == null) { //行头表还没有初始化
            super.createDefaultColumnsFromModel();

        } else { //行头表已经初始化，可以实时变化行头
            TableModel dm = getModel();
            if (dm != null) {
                /* 清空当前全部列 */
                TableColumnModel cm = getColumnModel();
                for (int i = 0, size = cm.getColumnCount(); i < size; i++) {
                    cm.removeColumn(cm.getColumn(0));
                }

                /* 创建规定的列 */
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
     * 更新首选滚动视口的大小。
     */
    private void updatePreferredScrollableViewportSize() {
        Dimension thps = getTableHeader().getPreferredSize();
        Dimension psvs = getPreferredScrollableViewportSize();

        psvs.width = thps.width;

        setPreferredScrollableViewportSize(psvs);
    }

    /**
     * 清除目标表上不应该存在的目标列。
     */
    private void clearTargetColumnFromTargetTable() {
        TableColumnModel cm = m_targetTable.getColumnModel();

        for (int i = 0; i < m_targetColumnModelIndexs.length; i++) {
            int viewIndex = m_targetTable.convertColumnIndexToView(m_targetColumnModelIndexs[i]);

            if (viewIndex >= 0) { //目标列还存在
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
     * 目标表。
     */
    private JTable m_targetTable;

    /**
     * 目标列模型索引数组。
     */
    private int[] m_targetColumnModelIndexs;

    /**
     * 目标表列模型监听器。
     */
    private TableColumnModelListener m_targetTableColumnModelListener;

    /*------------------------------------- Static Initial Block -------------------------------------*/

    /* Add Static Initial Block */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

}
