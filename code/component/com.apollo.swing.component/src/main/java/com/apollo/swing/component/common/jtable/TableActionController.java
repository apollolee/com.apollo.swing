/*
 * 此代码创建于 2007-8-11 下午04:04:39
 */
package com.apollo.swing.component.common.jtable;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JTable;
import javax.swing.event.EventListenerList;

/**
 * <p>文件名称：TTableActionUtilities.java</p>
 * <p>文件描述：JTable动作控制器，可以方便得对JTable的各种导航动作的进行控制。</p>
 * <p>版权所有：版权所有(C)2001-2004</p>
 * <p>公　　司：菠萝梨</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2007-8-11</p>
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
public final class TableActionController {

    /*------------------------------------- Public Static Field -------------------------------------*/

    /* Add Public Static Field */

    /*------------------------------------- Public Field -------------------------------------*/

    /* Add Public Field */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 构造方法。
     * @param targetTable 目标表格。
     * @exception IllegalArgumentException 目标表格为空时会抛出此异常。
     */
    public TableActionController(JTable targetTable) {
        /* 目标表格判空 */
        if (targetTable == null) {
            throw new IllegalArgumentException("Target table is null !");
        }

        /* 初始化成员属性 */
        m_targetTable = targetTable;

        /* 执行初始化方法 */
        initialize();
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
     * 添加一个指定的“selectNextColumn”的动作监听器（此动作对应的键盘操作：“keyCode Right-P ; keyCode Right-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectNextColumnActionListener(ITableActionListener listener) {
        addTableActionListener("selectNextColumn", listener);
    }

    /**
     * 移除一个指定的“selectNextColumn”的动作监听器（此动作对应的键盘操作：“keyCode Right-P ; keyCode Right-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectNextColumnActionListener(ITableActionListener listener) {
        removeTableActionListener("selectNextColumn", listener);
    }

    /**
     * 添加一个指定的“selectPreviousColumn”的动作监听器（此动作对应的键盘操作：“keyCode Left-P ; keyCode Left-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectPreviousColumnActionListener(ITableActionListener listener) {
        addTableActionListener("selectPreviousColumn", listener);
    }

    /**
     * 移除一个指定的“selectPreviousColumn”的动作监听器（此动作对应的键盘操作：“keyCode Left-P ; keyCode Left-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectPreviousColumnActionListener(ITableActionListener listener) {
        removeTableActionListener("selectPreviousColumn", listener);
    }

    /**
     * 添加一个指定的“selectNextRow”的动作监听器（此动作对应的键盘操作：“keyCode Down-P ; keyCode Down-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectNextRowActionListener(ITableActionListener listener) {
        addTableActionListener("selectNextRow", listener);
    }

    /**
     * 移除一个指定的“selectNextRow”的动作监听器（此动作对应的键盘操作：“keyCode Down-P ; keyCode Down-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectNextRowActionListener(ITableActionListener listener) {
        removeTableActionListener("selectNextRow", listener);
    }

    /**
     * 添加一个指定的“selectPreviousRow”的动作监听器（此动作对应的键盘操作：“keyCode Up-P ; keyCode Up-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectPreviousRowActionListener(ITableActionListener listener) {
        addTableActionListener("selectPreviousRow", listener);
    }

    /**
     * 移除一个指定的“selectPreviousRow”的动作监听器（此动作对应的键盘操作：“keyCode Up-P ; keyCode Up-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectPreviousRowActionListener(ITableActionListener listener) {
        removeTableActionListener("selectPreviousRow", listener);
    }

    /**
     * 添加一个指定的“selectNextColumnExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode ShiftRight-P ; keyCode ShiftRight-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectNextColumnExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("selectNextColumnExtendSelection", listener);
    }

    /**
     * 移除一个指定的“selectNextColumnExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode ShiftRight-P ; keyCode ShiftRight-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectNextColumnExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("selectNextColumnExtendSelection", listener);
    }

    /**
     * 添加一个指定的“selectPreviousColumnExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode ShiftLeft-P ; keyCode ShiftLeft-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectPreviousColumnExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("selectPreviousColumnExtendSelection", listener);
    }

    /**
     * 移除一个指定的“selectPreviousColumnExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode ShiftLeft-P ; keyCode ShiftLeft-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectPreviousColumnExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("selectPreviousColumnExtendSelection", listener);
    }

    /**
     * 添加一个指定的“selectNextRowExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode ShiftDown-P ; keyCode ShiftDown-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectNextRowExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("selectNextRowExtendSelection", listener);
    }

    /**
     * 移除一个指定的“selectNextRowExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode ShiftDown-P ; keyCode ShiftDown-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectNextRowExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("selectNextRowExtendSelection", listener);
    }

    /**
     * 添加一个指定的“selectPreviousRowExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode ShiftUp-P ; keyCode ShiftUp-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectPreviousRowExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("selectPreviousRowExtendSelection", listener);
    }

    /**
     * 移除一个指定的“selectPreviousRowExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode ShiftUp-P ; keyCode ShiftUp-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectPreviousRowExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("selectPreviousRowExtendSelection", listener);
    }

    /**
     * 添加一个指定的“scrollUpChangeSelection”的动作监听器（此动作对应的键盘操作：“keyCode Page Up-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addScrollUpChangeSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("scrollUpChangeSelection", listener);
    }

    /**
     * 移除一个指定的“scrollUpChangeSelection”的动作监听器（此动作对应的键盘操作：“keyCode Page Up-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeScrollUpChangeSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("scrollUpChangeSelection", listener);
    }

    /**
     * 添加一个指定的“scrollDownChangeSelection”的动作监听器（此动作对应的键盘操作：“keyCode Page Down-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addScrollDownChangeSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("scrollDownChangeSelection", listener);
    }

    /**
     * 移除一个指定的“scrollDownChangeSelection”的动作监听器（此动作对应的键盘操作：“keyCode Page Down-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeScrollDownChangeSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("scrollDownChangeSelection", listener);
    }

    /**
     * 添加一个指定的“selectFirstColumn”的动作监听器（此动作对应的键盘操作：“keyCode Home-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectFirstColumnActionListener(ITableActionListener listener) {
        addTableActionListener("selectFirstColumn", listener);
    }

    /**
     * 移除一个指定的“selectFirstColumn”的动作监听器（此动作对应的键盘操作：“keyCode Home-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectFirstColumnActionListener(ITableActionListener listener) {
        removeTableActionListener("selectFirstColumn", listener);
    }

    /**
     * 添加一个指定的“selectLastColumn”的动作监听器（此动作对应的键盘操作：“keyCode End-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectLastColumnActionListener(ITableActionListener listener) {
        addTableActionListener("selectLastColumn", listener);
    }

    /**
     * 移除一个指定的“selectLastColumn”的动作监听器（此动作对应的键盘操作：“keyCode End-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectLastColumnActionListener(ITableActionListener listener) {
        removeTableActionListener("selectLastColumn", listener);
    }

    /**
     * 添加一个指定的“scrollUpExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode ShiftPage Up-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addScrollUpExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("scrollUpExtendSelection", listener);
    }

    /**
     * 移除一个指定的“scrollUpExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode ShiftPage Up-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeScrollUpExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("scrollUpExtendSelection", listener);
    }

    /**
     * 添加一个指定的“scrollDownExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode ShiftPage Down-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addScrollDownExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("scrollDownExtendSelection", listener);
    }

    /**
     * 移除一个指定的“scrollDownExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode ShiftPage Down-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeScrollDownExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("scrollDownExtendSelection", listener);
    }

    /**
     * 添加一个指定的“selectFirstColumnExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode ShiftHome-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectFirstColumnExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("selectFirstColumnExtendSelection", listener);
    }

    /**
     * 移除一个指定的“selectFirstColumnExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode ShiftHome-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectFirstColumnExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("selectFirstColumnExtendSelection", listener);
    }

    /**
     * 添加一个指定的“selectLastColumnExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode ShiftEnd-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectLastColumnExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("selectLastColumnExtendSelection", listener);
    }

    /**
     * 移除一个指定的“selectLastColumnExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode ShiftEnd-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectLastColumnExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("selectLastColumnExtendSelection", listener);
    }

    /**
     * 添加一个指定的“selectFirstRow”的动作监听器（此动作对应的键盘操作：“keyCode CtrlHome-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectFirstRowActionListener(ITableActionListener listener) {
        addTableActionListener("selectFirstRow", listener);
    }

    /**
     * 移除一个指定的“selectFirstRow”的动作监听器（此动作对应的键盘操作：“keyCode CtrlHome-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectFirstRowActionListener(ITableActionListener listener) {
        removeTableActionListener("selectFirstRow", listener);
    }

    /**
     * 添加一个指定的“selectLastRow”的动作监听器（此动作对应的键盘操作：“keyCode CtrlEnd-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectLastRowActionListener(ITableActionListener listener) {
        addTableActionListener("selectLastRow", listener);
    }

    /**
     * 移除一个指定的“selectLastRow”的动作监听器（此动作对应的键盘操作：“keyCode CtrlEnd-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectLastRowActionListener(ITableActionListener listener) {
        removeTableActionListener("selectLastRow", listener);
    }

    /**
     * 添加一个指定的“selectFirstRowExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode Ctrl+ShiftHome-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectFirstRowExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("selectFirstRowExtendSelection", listener);
    }

    /**
     * 移除一个指定的“selectFirstRowExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode Ctrl+ShiftHome-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectFirstRowExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("selectFirstRowExtendSelection", listener);
    }

    /**
     * 添加一个指定的“selectLastRowExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode Ctrl+ShiftEnd-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectLastRowExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("selectLastRowExtendSelection", listener);
    }

    /**
     * 移除一个指定的“selectLastRowExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode Ctrl+ShiftEnd-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectLastRowExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("selectLastRowExtendSelection", listener);
    }

    /**
     * 添加一个指定的“selectNextColumnCell”的动作监听器（此动作对应的键盘操作：“keyCode Tab-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectNextColumnCellActionListener(ITableActionListener listener) {
        addTableActionListener("selectNextColumnCell", listener);
    }

    /**
     * 移除一个指定的“selectNextColumnCell”的动作监听器（此动作对应的键盘操作：“keyCode Tab-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectNextColumnCellActionListener(ITableActionListener listener) {
        removeTableActionListener("selectNextColumnCell", listener);
    }

    /**
     * 添加一个指定的“selectPreviousColumnCell”的动作监听器（此动作对应的键盘操作：“keyCode ShiftTab-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectPreviousColumnCellActionListener(ITableActionListener listener) {
        addTableActionListener("selectPreviousColumnCell", listener);
    }

    /**
     * 移除一个指定的“selectPreviousColumnCell”的动作监听器（此动作对应的键盘操作：“keyCode ShiftTab-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectPreviousColumnCellActionListener(ITableActionListener listener) {
        removeTableActionListener("selectPreviousColumnCell", listener);
    }

    /**
     * 添加一个指定的“selectNextRowCell”的动作监听器（此动作对应的键盘操作：“keyCode Enter-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectNextRowCellActionListener(ITableActionListener listener) {
        addTableActionListener("selectNextRowCell", listener);
    }

    /**
     * 移除一个指定的“selectNextRowCell”的动作监听器（此动作对应的键盘操作：“keyCode Enter-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectNextRowCellActionListener(ITableActionListener listener) {
        removeTableActionListener("selectNextRowCell", listener);
    }

    /**
     * 添加一个指定的“selectPreviousRowCell”的动作监听器（此动作对应的键盘操作：“keyCode ShiftEnter-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectPreviousRowCellActionListener(ITableActionListener listener) {
        addTableActionListener("selectPreviousRowCell", listener);
    }

    /**
     * 移除一个指定的“selectPreviousRowCell”的动作监听器（此动作对应的键盘操作：“keyCode ShiftEnter-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectPreviousRowCellActionListener(ITableActionListener listener) {
        removeTableActionListener("selectPreviousRowCell", listener);
    }

    /**
     * 添加一个指定的“selectAll”的动作监听器（此动作对应的键盘操作：“keyCode CtrlA-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectAllActionListener(ITableActionListener listener) {
        addTableActionListener("selectAll", listener);
    }

    /**
     * 移除一个指定的“selectAll”的动作监听器（此动作对应的键盘操作：“keyCode CtrlA-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectAllActionListener(ITableActionListener listener) {
        removeTableActionListener("selectAll", listener);
    }

    /**
     * 添加一个指定的“cancel”的动作监听器（此动作对应的键盘操作：“keyCode Escape-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addCancelActionListener(ITableActionListener listener) {
        addTableActionListener("cancel", listener);
    }

    /**
     * 移除一个指定的“cancel”的动作监听器（此动作对应的键盘操作：“keyCode Escape-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeCancelActionListener(ITableActionListener listener) {
        removeTableActionListener("cancel", listener);
    }

    /**
     * 添加一个指定的“startEditing”的动作监听器（此动作对应的键盘操作：“keyCode F2-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addStartEditingActionListener(ITableActionListener listener) {
        addTableActionListener("startEditing", listener);
    }

    /**
     * 移除一个指定的“startEditing”的动作监听器（此动作对应的键盘操作：“keyCode F2-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeStartEditingActionListener(ITableActionListener listener) {
        removeTableActionListener("startEditing", listener);
    }

    /**
     * 添加一个指定的“cut”的动作监听器（此动作对应的键盘操作：“keyCode CtrlX-P ; keyCode Cut-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addCutActionListener(ITableActionListener listener) {
        addTableActionListener("cut", listener);
    }

    /**
     * 移除一个指定的“cut”的动作监听器（此动作对应的键盘操作：“keyCode CtrlX-P ; keyCode Cut-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeCutActionListener(ITableActionListener listener) {
        removeTableActionListener("cut", listener);
    }

    /**
     * 添加一个指定的“copy”的动作监听器（此动作对应的键盘操作：“keyCode Copy-P ; keyCode CtrlC-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addCopyActionListener(ITableActionListener listener) {
        addTableActionListener("copy", listener);
    }

    /**
     * 移除一个指定的“copy”的动作监听器（此动作对应的键盘操作：“keyCode Copy-P ; keyCode CtrlC-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeCopyActionListener(ITableActionListener listener) {
        removeTableActionListener("copy", listener);
    }

    /**
     * 添加一个指定的“paste”的动作监听器（此动作对应的键盘操作：“keyCode CtrlV-P ; keyCode Paste-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addPasteActionListener(ITableActionListener listener) {
        addTableActionListener("paste", listener);
    }

    /**
     * 移除一个指定的“paste”的动作监听器（此动作对应的键盘操作：“keyCode CtrlV-P ; keyCode Paste-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removePasteActionListener(ITableActionListener listener) {
        removeTableActionListener("paste", listener);
    }

    /**
     * 添加一个指定的“scrollLeftChangeSelection”的动作监听器（此动作对应的键盘操作：“keyCode CtrlPage Up-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addScrollLeftChangeSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("scrollLeftChangeSelection", listener);
    }

    /**
     * 移除一个指定的“scrollLeftChangeSelection”的动作监听器（此动作对应的键盘操作：“keyCode CtrlPage Up-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeScrollLeftChangeSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("scrollLeftChangeSelection", listener);
    }

    /**
     * 添加一个指定的“scrollRightChangeSelection”的动作监听器（此动作对应的键盘操作：“keyCode CtrlPage Down-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addScrollRightChangeSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("scrollRightChangeSelection", listener);
    }

    /**
     * 移除一个指定的“scrollRightChangeSelection”的动作监听器（此动作对应的键盘操作：“keyCode CtrlPage Down-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeScrollRightChangeSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("scrollRightChangeSelection", listener);
    }

    /**
     * 添加一个指定的“scrollLeftExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode Ctrl+ShiftPage Down-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addScrollLeftExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("scrollLeftExtendSelection", listener);
    }

    /**
     * 移除一个指定的“scrollLeftExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode Ctrl+ShiftPage Down-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeScrollLeftExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("scrollLeftExtendSelection", listener);
    }

    /**
     * 添加一个指定的“scrollRightExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode Ctrl+ShiftPage Up-P”）。
     * @param listener 表格动作监听器。
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addScrollRightExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("scrollRightExtendSelection", listener);
    }

    /**
     * 移除一个指定的“scrollRightExtendSelection”的动作监听器（此动作对应的键盘操作：“keyCode Ctrl+ShiftPage Up-P”）。
     * @param listener 表格动作监听器。
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeScrollRightExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("scrollRightExtendSelection", listener);
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
     * 初始化方法。
     */
    private void initialize() {
        initTableActionListenerListMap();
        initAllTableActionListener();
    }

    /**
     * 初始化动作监听器列表的Map。
     */
    private void initTableActionListenerListMap() {
        ActionMap actionMap = m_targetTable.getActionMap();
        Object[] allKeys = actionMap.allKeys();

        for (int i = 0; i < allKeys.length; i++) {
            Object key = allKeys[i];
            m_tableActionListenerListMap.put(key, new EventListenerList());
        }
    }

    /**
     * 初始化所有的表格动作监听器，也就是把目标表格的所有action用新的用于拦截的action替换掉。
     * @exception IllegalStateException 如果目标表格已经安装过一次用于动作拦截的action就会抛出此异常。
     */
    private void initAllTableActionListener() {
        ActionMap actionMap = m_targetTable.getActionMap();
        Object[] allKeys = actionMap.allKeys();

        for (int i = 0; i < allKeys.length; i++) {
            final Object key = allKeys[i];
            final Action defaultAction = actionMap.get(key); //缺省的action要先取出来，在拦截时会调用部分或全部缺省动作

            /* 过滤缺省的action，遵循同一个JTable用于动作拦截的action只能设置一次的原则 */
            if (defaultAction instanceof ITableActionInterceptor) { //缺省的action已经是拦截的action了
                throw new IllegalStateException("Table action interceptor is already exist !");
            }

            actionMap.put(key, new ITableActionInterceptor() { //置入拦截用的action

                              /**
                               * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
                               */
                              public void actionPerformed(ActionEvent evt) {
                                  EventListenerList listenerList = (EventListenerList) m_tableActionListenerListMap.get(key);
                                  EventListener[] listeners = listenerList.getListeners(ITableActionListener.class);
                                  boolean isExecuteDefaultAction = true; //用于标识是否执行缺省动作

                                  /* 缺省动作执行前的回调动作 */
                                  for (int i = 0; i < listeners.length; i++) {
                                      ITableActionListener listener = (ITableActionListener) listeners[i];
                                      boolean retValue = listener.executeBeforeAction(evt);

                                      if (isExecuteDefaultAction) { //目前还是允许执行缺省动作的
                                          isExecuteDefaultAction = retValue;
                                      }
                                  }

                                  /* 执行缺省动作（如果允许执行的话） */
                                  if (isExecuteDefaultAction) {
                                      defaultAction.actionPerformed(evt);
                                  }
                              }

                              /**
                               * @see javax.swing.Action#addPropertyChangeListener(java.beans.PropertyChangeListener)
                               */
                              public void addPropertyChangeListener(PropertyChangeListener listener) {
                                  defaultAction.addPropertyChangeListener(listener);
                              }

                              /**
                               * @see javax.swing.Action#removePropertyChangeListener(java.beans.PropertyChangeListener)
                               */
                              public void removePropertyChangeListener(PropertyChangeListener listener) {
                                  defaultAction.removePropertyChangeListener(listener);
                              }

                              /**
                               * @see javax.swing.Action#getValue(java.lang.String)
                               */
                              public Object getValue(String key) {
                                  return defaultAction.getValue(key);
                              }

                              /**
                               * @see javax.swing.Action#putValue(java.lang.String, java.lang.Object)
                               */
                              public void putValue(String key, Object value) {
                                  defaultAction.putValue(key, value);
                              }

                              /**
                               * @see javax.swing.Action#isEnabled()
                               */
                              public boolean isEnabled() {
                                  return defaultAction.isEnabled();
                              }

                              /**
                               * @see javax.swing.Action#setEnabled(boolean)
                               */
                              public void setEnabled(boolean enabled) {
                                  defaultAction.setEnabled(enabled);
                              }

                          });
        }
    }

    /**
     * 给目标表格添加指定actionKey的指定表格动作监听器。
     * @param actionKey 表格动作在actionMap中的key值。
     * @param listener 表格动作监听器。
     * @exception IllegalArgumentException 表格动作监听器为空会抛出此异常。
     */
    private void addTableActionListener(String actionKey, ITableActionListener listener) {
        /* 表格动作监听器对象判空 */
        if (listener == null) {
            throw new IllegalArgumentException("Table action listener is null !");
        }

        /* 添加指定actionKey的表格动作监听器 */
        EventListenerList listenerList = (EventListenerList) m_tableActionListenerListMap.get(actionKey);
        listenerList.add(ITableActionListener.class, listener);
    }

    /**
     * 从目标表格移除指定actionKey的指定表格动作监听器。
     * @param actionKey 表格动作在actionMap中的key值。
     * @param listener 表格动作监听器。
     */
    private void removeTableActionListener(String actionKey, ITableActionListener listener) {
        EventListenerList listenerList = (EventListenerList) m_tableActionListenerListMap.get(actionKey);
        listenerList.remove(ITableActionListener.class, listener);
    }

    /*------------------------------------- Public Static Inner Class -------------------------------------*/

    /**
     * 表格动作监听器（注意：此监听亦可以有阻止表格动作执行的功能）。
     */
    public static interface ITableActionListener extends EventListener {

        /**
         * 在表格缺省的动作执行前，先调用此方法，并根据返回结果决定是否执行缺省动作。
         * @param evt 表格缺省动作执时传递进来的动作事件。
         * @return 为true表示允许执行缺省动作，为false表示禁止执行缺省动作。
         */
        public boolean executeBeforeAction(ActionEvent evt);

    }

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

    /**
     * 表格动作拦截器接口（标识接口，主要用来标识出表格缺省动作是否已经替换为拦截动作了）。
     */
    private static interface ITableActionInterceptor extends Action {
    }

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
     * 目标表格。
     */
    private JTable m_targetTable;

    /**
     * 动作监听器列表的Map，里面放得都是各个actionKey对应的EventListenerList。
     */
    private final Map m_tableActionListenerListMap = new HashMap();

    /*------------------------------------- 以下字段为全部34个动作的监听器，可拷贝代码使用 -------------------------------------*/

    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“selectNextColumn”的动作。
    //	 */
    //	private ITableActionListener m_selectNextColumnActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“selectPreviousColumn”的动作。
    //	 */
    //	private ITableActionListener m_selectPreviousColumnActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“selectNextRow”的动作。
    //	 */
    //	private ITableActionListener m_selectNextRowActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“selectPreviousRow”的动作。
    //	 */
    //	private ITableActionListener m_selectPreviousRowActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“selectNextColumnExtendSelection”的动作。
    //	 */
    //	private ITableActionListener m_selectNextColumnExtendSelectionActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“selectPreviousColumnExtendSelection”的动作。
    //	 */
    //	private ITableActionListener m_selectPreviousColumnExtendSelectionActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“selectNextRowExtendSelection”的动作。
    //	 */
    //	private ITableActionListener m_selectNextRowExtendSelectionActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“selectPreviousRowExtendSelection”的动作。
    //	 */
    //	private ITableActionListener m_selectPreviousRowExtendSelectionActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“scrollUpChangeSelection”的动作。
    //	 */
    //	private ITableActionListener m_scrollUpChangeSelectionActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“scrollDownChangeSelection”的动作。
    //	 */
    //	private ITableActionListener m_scrollDownChangeSelectionActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“selectFirstColumn”的动作。
    //	 */
    //	private ITableActionListener m_selectFirstColumnActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“selectLastColumn”的动作。
    //	 */
    //	private ITableActionListener m_selectLastColumnActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“scrollUpExtendSelection”的动作。
    //	 */
    //	private ITableActionListener m_scrollUpExtendSelectionActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“scrollDownExtendSelection”的动作。
    //	 */
    //	private ITableActionListener m_scrollDownExtendSelectionActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“selectFirstColumnExtendSelection”的动作。
    //	 */
    //	private ITableActionListener m_selectFirstColumnExtendSelectionActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“selectLastColumnExtendSelection”的动作。
    //	 */
    //	private ITableActionListener m_selectLastColumnExtendSelectionActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“selectFirstRow”的动作。
    //	 */
    //	private ITableActionListener m_selectFirstRowActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“selectLastRow”的动作。
    //	 */
    //	private ITableActionListener m_selectLastRowActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“selectFirstRowExtendSelection”的动作。
    //	 */
    //	private ITableActionListener m_selectFirstRowExtendSelectionActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“selectLastRowExtendSelection”的动作。
    //	 */
    //	private ITableActionListener m_selectLastRowExtendSelectionActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“selectNextColumnCell”的动作。
    //	 */
    //	private ITableActionListener m_selectNextColumnCellActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“selectPreviousColumnCell”的动作。
    //	 */
    //	private ITableActionListener m_selectPreviousColumnCellActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“selectNextRowCell”的动作。
    //	 */
    //	private ITableActionListener m_selectNextRowCellActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“selectPreviousRowCell”的动作。
    //	 */
    //	private ITableActionListener m_selectPreviousRowCellActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“selectAll”的动作。
    //	 */
    //	private ITableActionListener m_selectAllActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“cancel”的动作。
    //	 */
    //	private ITableActionListener m_cancelActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“startEditing”的动作。
    //	 */
    //	private ITableActionListener m_startEditingActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“cut”的动作。
    //	 */
    //	private ITableActionListener m_cutActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“copy”的动作。
    //	 */
    //	private ITableActionListener m_copyActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“paste”的动作。
    //	 */
    //	private ITableActionListener m_pasteActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“scrollLeftChangeSelection”的动作。
    //	 */
    //	private ITableActionListener m_scrollLeftChangeSelectionActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“scrollRightChangeSelection”的动作。
    //	 */
    //	private ITableActionListener m_scrollRightChangeSelectionActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“scrollLeftExtendSelection”的动作。
    //	 */
    //	private ITableActionListener m_scrollLeftExtendSelectionActionListener = null;
    //
    //	/**
    //	 * 表格动作监听器，监听在ActionMap中key值为“scrollRightExtendSelection”的动作。
    //	 */
    //	private ITableActionListener m_scrollRightExtendSelectionActionListener = null;
    /*------------------------------------- Static Initial Block -------------------------------------*/

    /* Add Static Initial Block */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

}
