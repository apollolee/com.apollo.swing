/*
 * �˴��봴���� 2007-8-11 ����04:04:39
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
 * <p>�ļ����ƣ�TTableActionUtilities.java</p>
 * <p>�ļ�������JTable���������������Է���ö�JTable�ĸ��ֵ��������Ľ��п��ơ�</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004</p>
 * <p>������˾��������</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2007-8-11</p>
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
public final class TableActionController {

    /*------------------------------------- Public Static Field -------------------------------------*/

    /* Add Public Static Field */

    /*------------------------------------- Public Field -------------------------------------*/

    /* Add Public Field */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     * @param targetTable Ŀ����
     * @exception IllegalArgumentException Ŀ����Ϊ��ʱ���׳����쳣��
     */
    public TableActionController(JTable targetTable) {
        /* Ŀ�����п� */
        if (targetTable == null) {
            throw new IllegalArgumentException("Target table is null !");
        }

        /* ��ʼ����Ա���� */
        m_targetTable = targetTable;

        /* ִ�г�ʼ������ */
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
     * ���һ��ָ���ġ�selectNextColumn���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Right-P ; keyCode Right-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectNextColumnActionListener(ITableActionListener listener) {
        addTableActionListener("selectNextColumn", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�selectNextColumn���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Right-P ; keyCode Right-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectNextColumnActionListener(ITableActionListener listener) {
        removeTableActionListener("selectNextColumn", listener);
    }

    /**
     * ���һ��ָ���ġ�selectPreviousColumn���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Left-P ; keyCode Left-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectPreviousColumnActionListener(ITableActionListener listener) {
        addTableActionListener("selectPreviousColumn", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�selectPreviousColumn���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Left-P ; keyCode Left-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectPreviousColumnActionListener(ITableActionListener listener) {
        removeTableActionListener("selectPreviousColumn", listener);
    }

    /**
     * ���һ��ָ���ġ�selectNextRow���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Down-P ; keyCode Down-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectNextRowActionListener(ITableActionListener listener) {
        addTableActionListener("selectNextRow", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�selectNextRow���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Down-P ; keyCode Down-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectNextRowActionListener(ITableActionListener listener) {
        removeTableActionListener("selectNextRow", listener);
    }

    /**
     * ���һ��ָ���ġ�selectPreviousRow���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Up-P ; keyCode Up-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectPreviousRowActionListener(ITableActionListener listener) {
        addTableActionListener("selectPreviousRow", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�selectPreviousRow���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Up-P ; keyCode Up-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectPreviousRowActionListener(ITableActionListener listener) {
        removeTableActionListener("selectPreviousRow", listener);
    }

    /**
     * ���һ��ָ���ġ�selectNextColumnExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode ShiftRight-P ; keyCode ShiftRight-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectNextColumnExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("selectNextColumnExtendSelection", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�selectNextColumnExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode ShiftRight-P ; keyCode ShiftRight-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectNextColumnExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("selectNextColumnExtendSelection", listener);
    }

    /**
     * ���һ��ָ���ġ�selectPreviousColumnExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode ShiftLeft-P ; keyCode ShiftLeft-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectPreviousColumnExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("selectPreviousColumnExtendSelection", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�selectPreviousColumnExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode ShiftLeft-P ; keyCode ShiftLeft-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectPreviousColumnExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("selectPreviousColumnExtendSelection", listener);
    }

    /**
     * ���һ��ָ���ġ�selectNextRowExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode ShiftDown-P ; keyCode ShiftDown-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectNextRowExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("selectNextRowExtendSelection", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�selectNextRowExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode ShiftDown-P ; keyCode ShiftDown-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectNextRowExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("selectNextRowExtendSelection", listener);
    }

    /**
     * ���һ��ָ���ġ�selectPreviousRowExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode ShiftUp-P ; keyCode ShiftUp-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectPreviousRowExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("selectPreviousRowExtendSelection", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�selectPreviousRowExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode ShiftUp-P ; keyCode ShiftUp-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectPreviousRowExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("selectPreviousRowExtendSelection", listener);
    }

    /**
     * ���һ��ָ���ġ�scrollUpChangeSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Page Up-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addScrollUpChangeSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("scrollUpChangeSelection", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�scrollUpChangeSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Page Up-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeScrollUpChangeSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("scrollUpChangeSelection", listener);
    }

    /**
     * ���һ��ָ���ġ�scrollDownChangeSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Page Down-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addScrollDownChangeSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("scrollDownChangeSelection", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�scrollDownChangeSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Page Down-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeScrollDownChangeSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("scrollDownChangeSelection", listener);
    }

    /**
     * ���һ��ָ���ġ�selectFirstColumn���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Home-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectFirstColumnActionListener(ITableActionListener listener) {
        addTableActionListener("selectFirstColumn", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�selectFirstColumn���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Home-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectFirstColumnActionListener(ITableActionListener listener) {
        removeTableActionListener("selectFirstColumn", listener);
    }

    /**
     * ���һ��ָ���ġ�selectLastColumn���Ķ������������˶�����Ӧ�ļ��̲�������keyCode End-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectLastColumnActionListener(ITableActionListener listener) {
        addTableActionListener("selectLastColumn", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�selectLastColumn���Ķ������������˶�����Ӧ�ļ��̲�������keyCode End-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectLastColumnActionListener(ITableActionListener listener) {
        removeTableActionListener("selectLastColumn", listener);
    }

    /**
     * ���һ��ָ���ġ�scrollUpExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode ShiftPage Up-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addScrollUpExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("scrollUpExtendSelection", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�scrollUpExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode ShiftPage Up-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeScrollUpExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("scrollUpExtendSelection", listener);
    }

    /**
     * ���һ��ָ���ġ�scrollDownExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode ShiftPage Down-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addScrollDownExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("scrollDownExtendSelection", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�scrollDownExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode ShiftPage Down-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeScrollDownExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("scrollDownExtendSelection", listener);
    }

    /**
     * ���һ��ָ���ġ�selectFirstColumnExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode ShiftHome-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectFirstColumnExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("selectFirstColumnExtendSelection", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�selectFirstColumnExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode ShiftHome-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectFirstColumnExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("selectFirstColumnExtendSelection", listener);
    }

    /**
     * ���һ��ָ���ġ�selectLastColumnExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode ShiftEnd-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectLastColumnExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("selectLastColumnExtendSelection", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�selectLastColumnExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode ShiftEnd-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectLastColumnExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("selectLastColumnExtendSelection", listener);
    }

    /**
     * ���һ��ָ���ġ�selectFirstRow���Ķ������������˶�����Ӧ�ļ��̲�������keyCode CtrlHome-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectFirstRowActionListener(ITableActionListener listener) {
        addTableActionListener("selectFirstRow", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�selectFirstRow���Ķ������������˶�����Ӧ�ļ��̲�������keyCode CtrlHome-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectFirstRowActionListener(ITableActionListener listener) {
        removeTableActionListener("selectFirstRow", listener);
    }

    /**
     * ���һ��ָ���ġ�selectLastRow���Ķ������������˶�����Ӧ�ļ��̲�������keyCode CtrlEnd-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectLastRowActionListener(ITableActionListener listener) {
        addTableActionListener("selectLastRow", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�selectLastRow���Ķ������������˶�����Ӧ�ļ��̲�������keyCode CtrlEnd-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectLastRowActionListener(ITableActionListener listener) {
        removeTableActionListener("selectLastRow", listener);
    }

    /**
     * ���һ��ָ���ġ�selectFirstRowExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Ctrl+ShiftHome-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectFirstRowExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("selectFirstRowExtendSelection", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�selectFirstRowExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Ctrl+ShiftHome-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectFirstRowExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("selectFirstRowExtendSelection", listener);
    }

    /**
     * ���һ��ָ���ġ�selectLastRowExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Ctrl+ShiftEnd-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectLastRowExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("selectLastRowExtendSelection", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�selectLastRowExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Ctrl+ShiftEnd-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectLastRowExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("selectLastRowExtendSelection", listener);
    }

    /**
     * ���һ��ָ���ġ�selectNextColumnCell���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Tab-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectNextColumnCellActionListener(ITableActionListener listener) {
        addTableActionListener("selectNextColumnCell", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�selectNextColumnCell���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Tab-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectNextColumnCellActionListener(ITableActionListener listener) {
        removeTableActionListener("selectNextColumnCell", listener);
    }

    /**
     * ���һ��ָ���ġ�selectPreviousColumnCell���Ķ������������˶�����Ӧ�ļ��̲�������keyCode ShiftTab-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectPreviousColumnCellActionListener(ITableActionListener listener) {
        addTableActionListener("selectPreviousColumnCell", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�selectPreviousColumnCell���Ķ������������˶�����Ӧ�ļ��̲�������keyCode ShiftTab-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectPreviousColumnCellActionListener(ITableActionListener listener) {
        removeTableActionListener("selectPreviousColumnCell", listener);
    }

    /**
     * ���һ��ָ���ġ�selectNextRowCell���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Enter-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectNextRowCellActionListener(ITableActionListener listener) {
        addTableActionListener("selectNextRowCell", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�selectNextRowCell���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Enter-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectNextRowCellActionListener(ITableActionListener listener) {
        removeTableActionListener("selectNextRowCell", listener);
    }

    /**
     * ���һ��ָ���ġ�selectPreviousRowCell���Ķ������������˶�����Ӧ�ļ��̲�������keyCode ShiftEnter-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectPreviousRowCellActionListener(ITableActionListener listener) {
        addTableActionListener("selectPreviousRowCell", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�selectPreviousRowCell���Ķ������������˶�����Ӧ�ļ��̲�������keyCode ShiftEnter-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectPreviousRowCellActionListener(ITableActionListener listener) {
        removeTableActionListener("selectPreviousRowCell", listener);
    }

    /**
     * ���һ��ָ���ġ�selectAll���Ķ������������˶�����Ӧ�ļ��̲�������keyCode CtrlA-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addSelectAllActionListener(ITableActionListener listener) {
        addTableActionListener("selectAll", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�selectAll���Ķ������������˶�����Ӧ�ļ��̲�������keyCode CtrlA-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeSelectAllActionListener(ITableActionListener listener) {
        removeTableActionListener("selectAll", listener);
    }

    /**
     * ���һ��ָ���ġ�cancel���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Escape-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addCancelActionListener(ITableActionListener listener) {
        addTableActionListener("cancel", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�cancel���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Escape-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeCancelActionListener(ITableActionListener listener) {
        removeTableActionListener("cancel", listener);
    }

    /**
     * ���һ��ָ���ġ�startEditing���Ķ������������˶�����Ӧ�ļ��̲�������keyCode F2-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addStartEditingActionListener(ITableActionListener listener) {
        addTableActionListener("startEditing", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�startEditing���Ķ������������˶�����Ӧ�ļ��̲�������keyCode F2-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeStartEditingActionListener(ITableActionListener listener) {
        removeTableActionListener("startEditing", listener);
    }

    /**
     * ���һ��ָ���ġ�cut���Ķ������������˶�����Ӧ�ļ��̲�������keyCode CtrlX-P ; keyCode Cut-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addCutActionListener(ITableActionListener listener) {
        addTableActionListener("cut", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�cut���Ķ������������˶�����Ӧ�ļ��̲�������keyCode CtrlX-P ; keyCode Cut-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeCutActionListener(ITableActionListener listener) {
        removeTableActionListener("cut", listener);
    }

    /**
     * ���һ��ָ���ġ�copy���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Copy-P ; keyCode CtrlC-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addCopyActionListener(ITableActionListener listener) {
        addTableActionListener("copy", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�copy���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Copy-P ; keyCode CtrlC-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeCopyActionListener(ITableActionListener listener) {
        removeTableActionListener("copy", listener);
    }

    /**
     * ���һ��ָ���ġ�paste���Ķ������������˶�����Ӧ�ļ��̲�������keyCode CtrlV-P ; keyCode Paste-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addPasteActionListener(ITableActionListener listener) {
        addTableActionListener("paste", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�paste���Ķ������������˶�����Ӧ�ļ��̲�������keyCode CtrlV-P ; keyCode Paste-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removePasteActionListener(ITableActionListener listener) {
        removeTableActionListener("paste", listener);
    }

    /**
     * ���һ��ָ���ġ�scrollLeftChangeSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode CtrlPage Up-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addScrollLeftChangeSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("scrollLeftChangeSelection", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�scrollLeftChangeSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode CtrlPage Up-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeScrollLeftChangeSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("scrollLeftChangeSelection", listener);
    }

    /**
     * ���һ��ָ���ġ�scrollRightChangeSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode CtrlPage Down-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addScrollRightChangeSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("scrollRightChangeSelection", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�scrollRightChangeSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode CtrlPage Down-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeScrollRightChangeSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("scrollRightChangeSelection", listener);
    }

    /**
     * ���һ��ָ���ġ�scrollLeftExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Ctrl+ShiftPage Down-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addScrollLeftExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("scrollLeftExtendSelection", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�scrollLeftExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Ctrl+ShiftPage Down-P������
     * @param listener �������������
     * @see #removeTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void removeScrollLeftExtendSelectionActionListener(ITableActionListener listener) {
        removeTableActionListener("scrollLeftExtendSelection", listener);
    }

    /**
     * ���һ��ָ���ġ�scrollRightExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Ctrl+ShiftPage Up-P������
     * @param listener �������������
     * @see #addTableActionListener(String, com.zte.ican.gui.component.common.jtable.TTableActionController.ITableActionListener)
     */
    public void addScrollRightExtendSelectionActionListener(ITableActionListener listener) {
        addTableActionListener("scrollRightExtendSelection", listener);
    }

    /**
     * �Ƴ�һ��ָ���ġ�scrollRightExtendSelection���Ķ������������˶�����Ӧ�ļ��̲�������keyCode Ctrl+ShiftPage Up-P������
     * @param listener �������������
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
     * ��ʼ��������
     */
    private void initialize() {
        initTableActionListenerListMap();
        initAllTableActionListener();
    }

    /**
     * ��ʼ�������������б��Map��
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
     * ��ʼ�����еı������������Ҳ���ǰ�Ŀ���������action���µ��������ص�action�滻����
     * @exception IllegalStateException ���Ŀ�����Ѿ���װ��һ�����ڶ������ص�action�ͻ��׳����쳣��
     */
    private void initAllTableActionListener() {
        ActionMap actionMap = m_targetTable.getActionMap();
        Object[] allKeys = actionMap.allKeys();

        for (int i = 0; i < allKeys.length; i++) {
            final Object key = allKeys[i];
            final Action defaultAction = actionMap.get(key); //ȱʡ��actionҪ��ȡ������������ʱ����ò��ֻ�ȫ��ȱʡ����

            /* ����ȱʡ��action����ѭͬһ��JTable���ڶ������ص�actionֻ������һ�ε�ԭ�� */
            if (defaultAction instanceof ITableActionInterceptor) { //ȱʡ��action�Ѿ������ص�action��
                throw new IllegalStateException("Table action interceptor is already exist !");
            }

            actionMap.put(key, new ITableActionInterceptor() { //���������õ�action

                              /**
                               * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
                               */
                              public void actionPerformed(ActionEvent evt) {
                                  EventListenerList listenerList = (EventListenerList) m_tableActionListenerListMap.get(key);
                                  EventListener[] listeners = listenerList.getListeners(ITableActionListener.class);
                                  boolean isExecuteDefaultAction = true; //���ڱ�ʶ�Ƿ�ִ��ȱʡ����

                                  /* ȱʡ����ִ��ǰ�Ļص����� */
                                  for (int i = 0; i < listeners.length; i++) {
                                      ITableActionListener listener = (ITableActionListener) listeners[i];
                                      boolean retValue = listener.executeBeforeAction(evt);

                                      if (isExecuteDefaultAction) { //Ŀǰ��������ִ��ȱʡ������
                                          isExecuteDefaultAction = retValue;
                                      }
                                  }

                                  /* ִ��ȱʡ�������������ִ�еĻ��� */
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
     * ��Ŀ�������ָ��actionKey��ָ���������������
     * @param actionKey �������actionMap�е�keyֵ��
     * @param listener �������������
     * @exception IllegalArgumentException �����������Ϊ�ջ��׳����쳣��
     */
    private void addTableActionListener(String actionKey, ITableActionListener listener) {
        /* ����������������п� */
        if (listener == null) {
            throw new IllegalArgumentException("Table action listener is null !");
        }

        /* ���ָ��actionKey�ı���������� */
        EventListenerList listenerList = (EventListenerList) m_tableActionListenerListMap.get(actionKey);
        listenerList.add(ITableActionListener.class, listener);
    }

    /**
     * ��Ŀ�����Ƴ�ָ��actionKey��ָ���������������
     * @param actionKey �������actionMap�е�keyֵ��
     * @param listener �������������
     */
    private void removeTableActionListener(String actionKey, ITableActionListener listener) {
        EventListenerList listenerList = (EventListenerList) m_tableActionListenerListMap.get(actionKey);
        listenerList.remove(ITableActionListener.class, listener);
    }

    /*------------------------------------- Public Static Inner Class -------------------------------------*/

    /**
     * �������������ע�⣺�˼������������ֹ�����ִ�еĹ��ܣ���
     */
    public static interface ITableActionListener extends EventListener {

        /**
         * �ڱ��ȱʡ�Ķ���ִ��ǰ���ȵ��ô˷����������ݷ��ؽ�������Ƿ�ִ��ȱʡ������
         * @param evt ���ȱʡ����ִʱ���ݽ����Ķ����¼���
         * @return Ϊtrue��ʾ����ִ��ȱʡ������Ϊfalse��ʾ��ִֹ��ȱʡ������
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
     * ������������ӿڣ���ʶ�ӿڣ���Ҫ������ʶ�����ȱʡ�����Ƿ��Ѿ��滻Ϊ���ض����ˣ���
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
     * Ŀ����
     */
    private JTable m_targetTable;

    /**
     * �����������б��Map������ŵö��Ǹ���actionKey��Ӧ��EventListenerList��
     */
    private final Map m_tableActionListenerListMap = new HashMap();

    /*------------------------------------- �����ֶ�Ϊȫ��34�������ļ��������ɿ�������ʹ�� -------------------------------------*/

    //	/**
    //	 * �������������������ActionMap��keyֵΪ��selectNextColumn���Ķ�����
    //	 */
    //	private ITableActionListener m_selectNextColumnActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��selectPreviousColumn���Ķ�����
    //	 */
    //	private ITableActionListener m_selectPreviousColumnActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��selectNextRow���Ķ�����
    //	 */
    //	private ITableActionListener m_selectNextRowActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��selectPreviousRow���Ķ�����
    //	 */
    //	private ITableActionListener m_selectPreviousRowActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��selectNextColumnExtendSelection���Ķ�����
    //	 */
    //	private ITableActionListener m_selectNextColumnExtendSelectionActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��selectPreviousColumnExtendSelection���Ķ�����
    //	 */
    //	private ITableActionListener m_selectPreviousColumnExtendSelectionActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��selectNextRowExtendSelection���Ķ�����
    //	 */
    //	private ITableActionListener m_selectNextRowExtendSelectionActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��selectPreviousRowExtendSelection���Ķ�����
    //	 */
    //	private ITableActionListener m_selectPreviousRowExtendSelectionActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��scrollUpChangeSelection���Ķ�����
    //	 */
    //	private ITableActionListener m_scrollUpChangeSelectionActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��scrollDownChangeSelection���Ķ�����
    //	 */
    //	private ITableActionListener m_scrollDownChangeSelectionActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��selectFirstColumn���Ķ�����
    //	 */
    //	private ITableActionListener m_selectFirstColumnActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��selectLastColumn���Ķ�����
    //	 */
    //	private ITableActionListener m_selectLastColumnActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��scrollUpExtendSelection���Ķ�����
    //	 */
    //	private ITableActionListener m_scrollUpExtendSelectionActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��scrollDownExtendSelection���Ķ�����
    //	 */
    //	private ITableActionListener m_scrollDownExtendSelectionActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��selectFirstColumnExtendSelection���Ķ�����
    //	 */
    //	private ITableActionListener m_selectFirstColumnExtendSelectionActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��selectLastColumnExtendSelection���Ķ�����
    //	 */
    //	private ITableActionListener m_selectLastColumnExtendSelectionActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��selectFirstRow���Ķ�����
    //	 */
    //	private ITableActionListener m_selectFirstRowActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��selectLastRow���Ķ�����
    //	 */
    //	private ITableActionListener m_selectLastRowActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��selectFirstRowExtendSelection���Ķ�����
    //	 */
    //	private ITableActionListener m_selectFirstRowExtendSelectionActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��selectLastRowExtendSelection���Ķ�����
    //	 */
    //	private ITableActionListener m_selectLastRowExtendSelectionActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��selectNextColumnCell���Ķ�����
    //	 */
    //	private ITableActionListener m_selectNextColumnCellActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��selectPreviousColumnCell���Ķ�����
    //	 */
    //	private ITableActionListener m_selectPreviousColumnCellActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��selectNextRowCell���Ķ�����
    //	 */
    //	private ITableActionListener m_selectNextRowCellActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��selectPreviousRowCell���Ķ�����
    //	 */
    //	private ITableActionListener m_selectPreviousRowCellActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��selectAll���Ķ�����
    //	 */
    //	private ITableActionListener m_selectAllActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��cancel���Ķ�����
    //	 */
    //	private ITableActionListener m_cancelActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��startEditing���Ķ�����
    //	 */
    //	private ITableActionListener m_startEditingActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��cut���Ķ�����
    //	 */
    //	private ITableActionListener m_cutActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��copy���Ķ�����
    //	 */
    //	private ITableActionListener m_copyActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��paste���Ķ�����
    //	 */
    //	private ITableActionListener m_pasteActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��scrollLeftChangeSelection���Ķ�����
    //	 */
    //	private ITableActionListener m_scrollLeftChangeSelectionActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��scrollRightChangeSelection���Ķ�����
    //	 */
    //	private ITableActionListener m_scrollRightChangeSelectionActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��scrollLeftExtendSelection���Ķ�����
    //	 */
    //	private ITableActionListener m_scrollLeftExtendSelectionActionListener = null;
    //
    //	/**
    //	 * �������������������ActionMap��keyֵΪ��scrollRightExtendSelection���Ķ�����
    //	 */
    //	private ITableActionListener m_scrollRightExtendSelectionActionListener = null;
    /*------------------------------------- Static Initial Block -------------------------------------*/

    /* Add Static Initial Block */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

}
