/*
 * 此代码创建于 2007-6-5 上午11:38:55
 */
package com.apollo.swing.component.common.jtable;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * <p>文件名称：TTableHeaderController.java</p>
 * <p>文件描述：JTable表头控制器，可以方便的控制表头的各种负责操作。</p>
 * <p>版权所有：版权所有(C)2001-2004</p>
 * <p>公　　司：菠萝梨</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2007-6-5</p>
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
public final class TableHeaderController implements MouseMotionListener {

    /*------------------------------------- Public Static Field -------------------------------------*/

    /* Add Public Static Field */

    /*------------------------------------- Public Field -------------------------------------*/

    /* Add Public Field */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 表头控制器单参数构造器。
     * @param controlledTableHeader 需要被控制的表，这里的表必须是完成表模型和列模型以及表头初始化的。
     * @exception IllegalArgumentException 需要被控制的表为null时则会抛出此异常。
     */
    public TableHeaderController(JTableHeader controlledTableHeader) {
        if (controlledTableHeader == null) {
            throw new IllegalArgumentException("Controlled table header is null !");
        }

        m_controlledTableHeader = controlledTableHeader;
        m_controlledTableHeader.addMouseMotionListener(this);
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
     * 添加一个禁止改变次序的列（线程安全）。
     * @param columnViewIndex 视图中的列索引，重复的索引只添加一次。
     * @exception IllegalArgumentException 如果视图列索引值不符合规范则会抛出此异常。
     */
    public void addDisabledReorderingColumnViewIndex(int columnViewIndex) {
        if (columnViewIndex < 0 || columnViewIndex >= m_controlledTableHeader.getColumnModel().getColumnCount()) {
            throw new IllegalArgumentException("Column view index is overflow ! [Index = " + columnViewIndex + "]");
        }

        addColumnViewIndex(columnViewIndex, m_disabledReorderingColumnViewIndexs);
    }

    /**
     * 添加一个禁止改变宽度的列，这里的改变宽度是相对的，需要看AutoResizeMode的策略（非线程安全，建议放在awt事件线程中运行）。
     * @param columnViewIndex 视图中的列索引，重复的索引只添加一次。
     * @exception IllegalArgumentException 如果视图列索引值不符合规范则会抛出此异常。
     */
    public void addDisabledResizingColumnViewIndex(int columnViewIndex) {
        if (columnViewIndex < 0 || columnViewIndex >= m_controlledTableHeader.getColumnModel().getColumnCount()) {
            throw new IllegalArgumentException("Column view index is overflow ! [Index = " + columnViewIndex + "]");
        }

        if (addColumnViewIndex(columnViewIndex, m_disabledResizingColumnViewIndexs)) {
            /* TODO 下面代码线程不安全 */
            /* TODO 把本列设置为不可改变宽度，这里的改变宽度是相对的，需要看AutoResizeMode的策略，例如通过拖动禁止列和前一列之间的分割线也可以改变 */
            m_controlledTableHeader.getColumnModel().getColumn(columnViewIndex).setResizable(false);
        }
    }

    /**
     * 添加一个禁止左移的列（线程安全）。
     * @param columnViewIndex 视图中的列索引，重复的索引只添加一次。
     * @exception IllegalArgumentException 如果视图列索引值不符合规范则会抛出此异常。
     */
    public void addDisabledMovingLeftColumnViewIndex(int columnViewIndex) {
        if (columnViewIndex < 0 || columnViewIndex >= m_controlledTableHeader.getColumnModel().getColumnCount()) {
            throw new IllegalArgumentException("Column view index is overflow ! [Index = " + columnViewIndex + "]");
        }

        addColumnViewIndex(columnViewIndex, m_disabledMovingLeftColumnViewIndexs);
    }

    /**
     * 添加一个禁止右移的列（线程安全）。
     * @param columnViewIndex 视图中的列索引，重复的索引只添加一次。
     * @exception IllegalArgumentException 如果视图列索引值不符合规范则会抛出此异常。
     */
    public void addDisabledMovingRightColumnViewIndex(int columnViewIndex) {
        if (columnViewIndex < 0 || columnViewIndex >= m_controlledTableHeader.getColumnModel().getColumnCount()) {
            throw new IllegalArgumentException("Column view index is overflow ! [Index = " + columnViewIndex + "]");
        }

        addColumnViewIndex(columnViewIndex, m_disabledMovingRightColumnViewIndexs);
    }

    /**
     * 获取所有禁止改变次序的列的视图索引（线程安全）。
     * @return 所有禁止改变次序的列的视图索引。
     */
    public int[] getDisabledReorderingColumnViewIndexs() {
        return getColumnViewIndexs(m_disabledReorderingColumnViewIndexs);
    }

    /**
     * 获取所有禁止改变宽度的列的视图索引（线程安全）。
     * @return 所有禁止改变宽度的列的视图索引。
     */
    public int[] getDisabledResizingColumnViewIndexs() {
        return getColumnViewIndexs(m_disabledResizingColumnViewIndexs);
    }

    /**
     * 获取所有禁止左移的列的视图索引（线程安全）。
     * @return 所有禁止左移的列的视图索引。
     */
    public int[] getDisabledMovingLeftColumnViewIndexs() {
        return getColumnViewIndexs(m_disabledMovingLeftColumnViewIndexs);
    }

    /**
     * 获取所有禁止右移的列的视图索引（线程安全）。
     * @return 所有禁止右移的列的视图索引。
     */
    public int[] getDisabledMovingRightColumnViewIndexs() {
        return getColumnViewIndexs(m_disabledMovingRightColumnViewIndexs);
    }

    /**
     * 以本表头控制器数据更新全部禁止改变宽度的列。
     */
    public void updateDisabledResizingColumn() {
        if (m_controlledTableHeader == null) {
            return;
        }

        if (!m_controlledTableHeader.getResizingAllowed()) { //如果全部都禁止Resizing，下面的工作都不用干了
            return;
        }

        TableColumnModel cm = m_controlledTableHeader.getColumnModel();
        if (cm == null) {
            return;
        }

        /* TODO 下面的代码只把由本控制器规定的禁止Resizing的列给禁止掉，其他列一律可以Resizing，包括用户通过其它方式禁止Resizing的列 */
        for (int i = 0, size = cm.getColumnCount(); i < size; i++) {
            TableColumn column = cm.getColumn(i);
            column.setResizable(true);
        }

        int[] disabledResizingColumnViewIndexArray = getDisabledResizingColumnViewIndexs();
        for (int i = 0; i < disabledResizingColumnViewIndexArray.length; i++) {
            TableColumn column = cm.getColumn(disabledResizingColumnViewIndexArray[i]);
            column.setResizable(false);
        }
    }

    /**
     * 释放资源。
     */
    public void close() {
        m_controlledTableHeader.removeMouseMotionListener(this);
    }

    /*------------------------------------- MouseMotionListener Public Method -------------------------------------*/

    /**
     * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
     * @exception IllegalArgumentException 事件源不是表头控件时则会抛出此异常。
     */
    public void mouseDragged(MouseEvent draggedMouseEvent) {
        Object source = draggedMouseEvent.getSource();
        if (!(source instanceof JTableHeader)) {
            throw new IllegalArgumentException("Event source must be JTableHeader !");
        }

        JTableHeader tableHeader = (JTableHeader) source;
        if (tableHeader.getDraggedDistance() == 0) { //没有列拖动的距离时不需要向下处理
            return;
        }

        /* 虽然已经用鼠标事件干预了UI类的处理流程，但由于线程问题，这里还是得进行一次判空过滤 */
        if (tableHeader.getDraggedColumn() == null) { //拖动列为空
            return;
        }

        if (processDisabledReorderingColumn(tableHeader, draggedMouseEvent)) { //处理禁止排序列
            return;
        }

        if (processDisabledMovingLeftColumn(tableHeader, draggedMouseEvent)) { //处理禁止左移列
            return;
        }

        if (processDisabledMovingRightColumn(tableHeader, draggedMouseEvent)) { //处理禁止右移列
            return;
        }
    }

    /**
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    public void mouseMoved(MouseEvent event) {
        /* 无鼠标移动监听的动作 */
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
     * 处理禁止排序列。
     * @param tableHeader 表头。
     * @param draggedMouseEvent 鼠标拖拽事件。
     * @return 为true表示禁止掉了当前拖动的动作，为false表示没有禁止掉当前拖动的动作。
     */
    private boolean processDisabledReorderingColumn(JTableHeader tableHeader, MouseEvent draggedMouseEvent) {
        TableColumn draggedColumn = tableHeader.getDraggedColumn();
        JTable table = tableHeader.getTable();
        int draggedColumnViewIndex = table.convertColumnIndexToView(draggedColumn.getModelIndex());
        int[] disabledReorderingColumnViewIndexArray = getDisabledReorderingColumnViewIndexs();

        for (int i = 0; i < disabledReorderingColumnViewIndexArray.length; i++) {
            /* 拖动列就是限制列 */
            if (draggedColumnViewIndex == disabledReorderingColumnViewIndexArray[i]) {
                disabledDragColumn(tableHeader, draggedMouseEvent);
                return true;
            }

            /* 拖动列就是限制列的后一列 */
            if (draggedColumnViewIndex == disabledReorderingColumnViewIndexArray[i] + 1) {
                if (tableHeader.getDraggedDistance() < 0) {
                    disabledDragColumn(tableHeader, draggedMouseEvent);
                    return true;
                }
                continue;
            }

            /* 拖动列就是限制列的前一列 */
            if (draggedColumnViewIndex == disabledReorderingColumnViewIndexArray[i] - 1) {
                if (tableHeader.getDraggedDistance() > 0) {
                    disabledDragColumn(tableHeader, draggedMouseEvent);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 处理禁止左移列。
     * @param tableHeader 表头。
     * @param draggedMouseEvent 鼠标拖拽事件。
     * @return 为true表示禁止掉了当前拖动的动作，为false表示没有禁止掉当前拖动的动作。
     */
    private boolean processDisabledMovingLeftColumn(JTableHeader tableHeader, MouseEvent draggedMouseEvent) {
        if (tableHeader.getDraggedDistance() >= 0) { //向右拖动
            return false;
        }

        TableColumn draggedColumn = tableHeader.getDraggedColumn();
        JTable table = tableHeader.getTable();
        int draggedColumnViewIndex = table.convertColumnIndexToView(draggedColumn.getModelIndex());
        int[] disabledMovingLeftColumnViewIndexArray = getDisabledMovingLeftColumnViewIndexs();

        for (int i = 0; i < disabledMovingLeftColumnViewIndexArray.length; i++) {
            /* 拖动列就是限制列 */
            if (draggedColumnViewIndex == disabledMovingLeftColumnViewIndexArray[i]) {
                disabledDragColumn(tableHeader, draggedMouseEvent);
                return true;
            }
        }

        return false;
    }

    /**
     * 处理禁止右移列。
     * @param tableHeader 表头。
     * @param draggedMouseEvent 鼠标拖拽事件。
     * @return 为true表示禁止掉了当前拖动的动作，为false表示没有禁止掉当前拖动的动作。
     */
    private boolean processDisabledMovingRightColumn(JTableHeader tableHeader, MouseEvent draggedMouseEvent) {
        if (tableHeader.getDraggedDistance() <= 0) { //向左拖动
            return false;
        }

        TableColumn draggedColumn = tableHeader.getDraggedColumn();
        JTable table = tableHeader.getTable();
        int draggedColumnViewIndex = table.convertColumnIndexToView(draggedColumn.getModelIndex());
        int[] disabledMovingRightColumnViewIndexArray = getDisabledMovingRightColumnViewIndexs();

        for (int i = 0; i < disabledMovingRightColumnViewIndexArray.length; i++) {
            /* 拖动列就是限制列 */
            if (draggedColumnViewIndex == disabledMovingRightColumnViewIndexArray[i]) {
                disabledDragColumn(tableHeader, draggedMouseEvent);
                return true;
            }
        }

        return false;
    }

    /**
     * 重构代码时产生的方法，优化了代码结构。
     * @param columnViewIndex 列的视图索引。
     * @param columnViewIndexList 列的视图索引列表。
     * @return 为true表示操作成功，为false表示操作失败。
     */
    private boolean addColumnViewIndex(int columnViewIndex, List columnViewIndexList) {
        Integer index = new Integer(columnViewIndex);

        synchronized (columnViewIndexList) {
            if (!columnViewIndexList.contains(index)) {
                columnViewIndexList.add(index);
                return true;
            }
        }

        return false;
    }

    /**
     * 重构代码时产生的方法，优化了代码结构。
     * @param columnViewIndexs 列的视图索引列表。
     * @return 列的视图索引数组。
     */
    private int[] getColumnViewIndexs(List columnViewIndexs) {
        synchronized (columnViewIndexs) {
            int[] indexArray = new int[columnViewIndexs.size()];
            for (int i = 0; i < indexArray.length; i++) {
                Integer index = (Integer) columnViewIndexs.get(i);
                indexArray[i] = index.intValue();
            }
            return indexArray;
        }
    }

    /**
     * 禁止拖动列的操作。
     * @param tableHeader 表头。
     * @param draggedMouseEvent 鼠标拖拽事件。
     */
    private void disabledDragColumn(JTableHeader tableHeader, MouseEvent draggedMouseEvent) {
        tableHeader.setDraggedColumn(null); //没有此设置，界面上会出现挤压抖动，估计是UI类里面做处理时发生的动作

        /* 构造当前点鼠标抬起的事件，发送给相应的UI类处理的 */
        MouseEvent releasedEvt = new MouseEvent((Component) draggedMouseEvent.getSource(),
                                                MouseEvent.MOUSE_RELEASED,
                                                System.currentTimeMillis(),
                                                draggedMouseEvent.getModifiers(),
                                                draggedMouseEvent.getX(),
                                                draggedMouseEvent.getY(),
                                                1,
                                                false,
                                                draggedMouseEvent.getButton());
        /* 构造当前点鼠标按下的事件，发送给相应的UI类处理的 */
        MouseEvent pressedEvt = new MouseEvent((Component) draggedMouseEvent.getSource(),
                                               MouseEvent.MOUSE_PRESSED,
                                               System.currentTimeMillis(),
                                               draggedMouseEvent.getModifiers(),
                                               draggedMouseEvent.getX(),
                                               draggedMouseEvent.getY(),
                                               1,
                                               false,
                                               draggedMouseEvent.getButton());
        /* 构造当前点鼠标移动的事件，发送给相应的UI类处理的 */
        MouseEvent movedEvt = new MouseEvent((Component) draggedMouseEvent.getSource(),
                                             MouseEvent.MOUSE_MOVED,
                                             System.currentTimeMillis(),
                                             draggedMouseEvent.getModifiers(),
                                             draggedMouseEvent.getX(),
                                             draggedMouseEvent.getY(),
                                             1,
                                             false,
                                             draggedMouseEvent.getButton());

        /* 发送两个事件到AWT事件队列中，给UI类注册的鼠标事件监听器去处理 */
        EventQueue eq = Toolkit.getDefaultToolkit().getSystemEventQueue();
        eq.postEvent(releasedEvt);
        eq.postEvent(pressedEvt);
        eq.postEvent(movedEvt);
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
     * 被控制的表头控件。
     */
    private JTableHeader m_controlledTableHeader;

    /**
     * 禁止从新排列位置的列视图索引列表。
     */
    private List m_disabledReorderingColumnViewIndexs = new ArrayList();

    /**
     * 禁止从新改变大小的列视图索引列表。
     */
    private List m_disabledResizingColumnViewIndexs = new ArrayList();

    /**
     * 禁止左移的列视图索引列表。
     */
    private List m_disabledMovingLeftColumnViewIndexs = new ArrayList();

    /**
     * 禁止右移的列视图索引列表。
     */
    private List m_disabledMovingRightColumnViewIndexs = new ArrayList();

    /*------------------------------------- Static Initial Block -------------------------------------*/

    /* Add Static Initial Block */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

}
