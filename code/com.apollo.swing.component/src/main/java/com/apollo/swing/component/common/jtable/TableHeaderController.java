/*
 * �˴��봴���� 2007-6-5 ����11:38:55
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
 * <p>�ļ����ƣ�TTableHeaderController.java</p>
 * <p>�ļ�������JTable��ͷ�����������Է���Ŀ��Ʊ�ͷ�ĸ��ָ��������</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004</p>
 * <p>������˾��������</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2007-6-5</p>
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
public final class TableHeaderController implements MouseMotionListener {

    /*------------------------------------- Public Static Field -------------------------------------*/

    /* Add Public Static Field */

    /*------------------------------------- Public Field -------------------------------------*/

    /* Add Public Field */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ��ͷ��������������������
     * @param controlledTableHeader ��Ҫ�����Ƶı�����ı��������ɱ�ģ�ͺ���ģ���Լ���ͷ��ʼ���ġ�
     * @exception IllegalArgumentException ��Ҫ�����Ƶı�Ϊnullʱ����׳����쳣��
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
     * ���һ����ֹ�ı������У��̰߳�ȫ����
     * @param columnViewIndex ��ͼ�е����������ظ�������ֻ���һ�Ρ�
     * @exception IllegalArgumentException �����ͼ������ֵ�����Ϲ淶����׳����쳣��
     */
    public void addDisabledReorderingColumnViewIndex(int columnViewIndex) {
        if (columnViewIndex < 0 || columnViewIndex >= m_controlledTableHeader.getColumnModel().getColumnCount()) {
            throw new IllegalArgumentException("Column view index is overflow ! [Index = " + columnViewIndex + "]");
        }

        addColumnViewIndex(columnViewIndex, m_disabledReorderingColumnViewIndexs);
    }

    /**
     * ���һ����ֹ�ı��ȵ��У�����ĸı�������Եģ���Ҫ��AutoResizeMode�Ĳ��ԣ����̰߳�ȫ���������awt�¼��߳������У���
     * @param columnViewIndex ��ͼ�е����������ظ�������ֻ���һ�Ρ�
     * @exception IllegalArgumentException �����ͼ������ֵ�����Ϲ淶����׳����쳣��
     */
    public void addDisabledResizingColumnViewIndex(int columnViewIndex) {
        if (columnViewIndex < 0 || columnViewIndex >= m_controlledTableHeader.getColumnModel().getColumnCount()) {
            throw new IllegalArgumentException("Column view index is overflow ! [Index = " + columnViewIndex + "]");
        }

        if (addColumnViewIndex(columnViewIndex, m_disabledResizingColumnViewIndexs)) {
            /* TODO ��������̲߳���ȫ */
            /* TODO �ѱ�������Ϊ���ɸı��ȣ�����ĸı�������Եģ���Ҫ��AutoResizeMode�Ĳ��ԣ�����ͨ���϶���ֹ�к�ǰһ��֮��ķָ���Ҳ���Ըı� */
            m_controlledTableHeader.getColumnModel().getColumn(columnViewIndex).setResizable(false);
        }
    }

    /**
     * ���һ����ֹ���Ƶ��У��̰߳�ȫ����
     * @param columnViewIndex ��ͼ�е����������ظ�������ֻ���һ�Ρ�
     * @exception IllegalArgumentException �����ͼ������ֵ�����Ϲ淶����׳����쳣��
     */
    public void addDisabledMovingLeftColumnViewIndex(int columnViewIndex) {
        if (columnViewIndex < 0 || columnViewIndex >= m_controlledTableHeader.getColumnModel().getColumnCount()) {
            throw new IllegalArgumentException("Column view index is overflow ! [Index = " + columnViewIndex + "]");
        }

        addColumnViewIndex(columnViewIndex, m_disabledMovingLeftColumnViewIndexs);
    }

    /**
     * ���һ����ֹ���Ƶ��У��̰߳�ȫ����
     * @param columnViewIndex ��ͼ�е����������ظ�������ֻ���һ�Ρ�
     * @exception IllegalArgumentException �����ͼ������ֵ�����Ϲ淶����׳����쳣��
     */
    public void addDisabledMovingRightColumnViewIndex(int columnViewIndex) {
        if (columnViewIndex < 0 || columnViewIndex >= m_controlledTableHeader.getColumnModel().getColumnCount()) {
            throw new IllegalArgumentException("Column view index is overflow ! [Index = " + columnViewIndex + "]");
        }

        addColumnViewIndex(columnViewIndex, m_disabledMovingRightColumnViewIndexs);
    }

    /**
     * ��ȡ���н�ֹ�ı������е���ͼ�������̰߳�ȫ����
     * @return ���н�ֹ�ı������е���ͼ������
     */
    public int[] getDisabledReorderingColumnViewIndexs() {
        return getColumnViewIndexs(m_disabledReorderingColumnViewIndexs);
    }

    /**
     * ��ȡ���н�ֹ�ı��ȵ��е���ͼ�������̰߳�ȫ����
     * @return ���н�ֹ�ı��ȵ��е���ͼ������
     */
    public int[] getDisabledResizingColumnViewIndexs() {
        return getColumnViewIndexs(m_disabledResizingColumnViewIndexs);
    }

    /**
     * ��ȡ���н�ֹ���Ƶ��е���ͼ�������̰߳�ȫ����
     * @return ���н�ֹ���Ƶ��е���ͼ������
     */
    public int[] getDisabledMovingLeftColumnViewIndexs() {
        return getColumnViewIndexs(m_disabledMovingLeftColumnViewIndexs);
    }

    /**
     * ��ȡ���н�ֹ���Ƶ��е���ͼ�������̰߳�ȫ����
     * @return ���н�ֹ���Ƶ��е���ͼ������
     */
    public int[] getDisabledMovingRightColumnViewIndexs() {
        return getColumnViewIndexs(m_disabledMovingRightColumnViewIndexs);
    }

    /**
     * �Ա���ͷ���������ݸ���ȫ����ֹ�ı��ȵ��С�
     */
    public void updateDisabledResizingColumn() {
        if (m_controlledTableHeader == null) {
            return;
        }

        if (!m_controlledTableHeader.getResizingAllowed()) { //���ȫ������ֹResizing������Ĺ��������ø���
            return;
        }

        TableColumnModel cm = m_controlledTableHeader.getColumnModel();
        if (cm == null) {
            return;
        }

        /* TODO ����Ĵ���ֻ���ɱ��������涨�Ľ�ֹResizing���и���ֹ����������һ�ɿ���Resizing�������û�ͨ��������ʽ��ֹResizing���� */
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
     * �ͷ���Դ��
     */
    public void close() {
        m_controlledTableHeader.removeMouseMotionListener(this);
    }

    /*------------------------------------- MouseMotionListener Public Method -------------------------------------*/

    /**
     * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
     * @exception IllegalArgumentException �¼�Դ���Ǳ�ͷ�ؼ�ʱ����׳����쳣��
     */
    public void mouseDragged(MouseEvent draggedMouseEvent) {
        Object source = draggedMouseEvent.getSource();
        if (!(source instanceof JTableHeader)) {
            throw new IllegalArgumentException("Event source must be JTableHeader !");
        }

        JTableHeader tableHeader = (JTableHeader) source;
        if (tableHeader.getDraggedDistance() == 0) { //û�����϶��ľ���ʱ����Ҫ���´���
            return;
        }

        /* ��Ȼ�Ѿ�������¼���Ԥ��UI��Ĵ������̣��������߳����⣬���ﻹ�ǵý���һ���пչ��� */
        if (tableHeader.getDraggedColumn() == null) { //�϶���Ϊ��
            return;
        }

        if (processDisabledReorderingColumn(tableHeader, draggedMouseEvent)) { //�����ֹ������
            return;
        }

        if (processDisabledMovingLeftColumn(tableHeader, draggedMouseEvent)) { //�����ֹ������
            return;
        }

        if (processDisabledMovingRightColumn(tableHeader, draggedMouseEvent)) { //�����ֹ������
            return;
        }
    }

    /**
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    public void mouseMoved(MouseEvent event) {
        /* ������ƶ������Ķ��� */
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
     * �����ֹ�����С�
     * @param tableHeader ��ͷ��
     * @param draggedMouseEvent �����ק�¼���
     * @return Ϊtrue��ʾ��ֹ���˵�ǰ�϶��Ķ�����Ϊfalse��ʾû�н�ֹ����ǰ�϶��Ķ�����
     */
    private boolean processDisabledReorderingColumn(JTableHeader tableHeader, MouseEvent draggedMouseEvent) {
        TableColumn draggedColumn = tableHeader.getDraggedColumn();
        JTable table = tableHeader.getTable();
        int draggedColumnViewIndex = table.convertColumnIndexToView(draggedColumn.getModelIndex());
        int[] disabledReorderingColumnViewIndexArray = getDisabledReorderingColumnViewIndexs();

        for (int i = 0; i < disabledReorderingColumnViewIndexArray.length; i++) {
            /* �϶��о��������� */
            if (draggedColumnViewIndex == disabledReorderingColumnViewIndexArray[i]) {
                disabledDragColumn(tableHeader, draggedMouseEvent);
                return true;
            }

            /* �϶��о��������еĺ�һ�� */
            if (draggedColumnViewIndex == disabledReorderingColumnViewIndexArray[i] + 1) {
                if (tableHeader.getDraggedDistance() < 0) {
                    disabledDragColumn(tableHeader, draggedMouseEvent);
                    return true;
                }
                continue;
            }

            /* �϶��о��������е�ǰһ�� */
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
     * �����ֹ�����С�
     * @param tableHeader ��ͷ��
     * @param draggedMouseEvent �����ק�¼���
     * @return Ϊtrue��ʾ��ֹ���˵�ǰ�϶��Ķ�����Ϊfalse��ʾû�н�ֹ����ǰ�϶��Ķ�����
     */
    private boolean processDisabledMovingLeftColumn(JTableHeader tableHeader, MouseEvent draggedMouseEvent) {
        if (tableHeader.getDraggedDistance() >= 0) { //�����϶�
            return false;
        }

        TableColumn draggedColumn = tableHeader.getDraggedColumn();
        JTable table = tableHeader.getTable();
        int draggedColumnViewIndex = table.convertColumnIndexToView(draggedColumn.getModelIndex());
        int[] disabledMovingLeftColumnViewIndexArray = getDisabledMovingLeftColumnViewIndexs();

        for (int i = 0; i < disabledMovingLeftColumnViewIndexArray.length; i++) {
            /* �϶��о��������� */
            if (draggedColumnViewIndex == disabledMovingLeftColumnViewIndexArray[i]) {
                disabledDragColumn(tableHeader, draggedMouseEvent);
                return true;
            }
        }

        return false;
    }

    /**
     * �����ֹ�����С�
     * @param tableHeader ��ͷ��
     * @param draggedMouseEvent �����ק�¼���
     * @return Ϊtrue��ʾ��ֹ���˵�ǰ�϶��Ķ�����Ϊfalse��ʾû�н�ֹ����ǰ�϶��Ķ�����
     */
    private boolean processDisabledMovingRightColumn(JTableHeader tableHeader, MouseEvent draggedMouseEvent) {
        if (tableHeader.getDraggedDistance() <= 0) { //�����϶�
            return false;
        }

        TableColumn draggedColumn = tableHeader.getDraggedColumn();
        JTable table = tableHeader.getTable();
        int draggedColumnViewIndex = table.convertColumnIndexToView(draggedColumn.getModelIndex());
        int[] disabledMovingRightColumnViewIndexArray = getDisabledMovingRightColumnViewIndexs();

        for (int i = 0; i < disabledMovingRightColumnViewIndexArray.length; i++) {
            /* �϶��о��������� */
            if (draggedColumnViewIndex == disabledMovingRightColumnViewIndexArray[i]) {
                disabledDragColumn(tableHeader, draggedMouseEvent);
                return true;
            }
        }

        return false;
    }

    /**
     * �ع�����ʱ�����ķ������Ż��˴���ṹ��
     * @param columnViewIndex �е���ͼ������
     * @param columnViewIndexList �е���ͼ�����б�
     * @return Ϊtrue��ʾ�����ɹ���Ϊfalse��ʾ����ʧ�ܡ�
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
     * �ع�����ʱ�����ķ������Ż��˴���ṹ��
     * @param columnViewIndexs �е���ͼ�����б�
     * @return �е���ͼ�������顣
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
     * ��ֹ�϶��еĲ�����
     * @param tableHeader ��ͷ��
     * @param draggedMouseEvent �����ק�¼���
     */
    private void disabledDragColumn(JTableHeader tableHeader, MouseEvent draggedMouseEvent) {
        tableHeader.setDraggedColumn(null); //û�д����ã������ϻ���ּ�ѹ������������UI������������ʱ�����Ķ���

        /* ���쵱ǰ�����̧����¼������͸���Ӧ��UI�ദ��� */
        MouseEvent releasedEvt = new MouseEvent((Component) draggedMouseEvent.getSource(),
                                                MouseEvent.MOUSE_RELEASED,
                                                System.currentTimeMillis(),
                                                draggedMouseEvent.getModifiers(),
                                                draggedMouseEvent.getX(),
                                                draggedMouseEvent.getY(),
                                                1,
                                                false,
                                                draggedMouseEvent.getButton());
        /* ���쵱ǰ����갴�µ��¼������͸���Ӧ��UI�ദ��� */
        MouseEvent pressedEvt = new MouseEvent((Component) draggedMouseEvent.getSource(),
                                               MouseEvent.MOUSE_PRESSED,
                                               System.currentTimeMillis(),
                                               draggedMouseEvent.getModifiers(),
                                               draggedMouseEvent.getX(),
                                               draggedMouseEvent.getY(),
                                               1,
                                               false,
                                               draggedMouseEvent.getButton());
        /* ���쵱ǰ������ƶ����¼������͸���Ӧ��UI�ദ��� */
        MouseEvent movedEvt = new MouseEvent((Component) draggedMouseEvent.getSource(),
                                             MouseEvent.MOUSE_MOVED,
                                             System.currentTimeMillis(),
                                             draggedMouseEvent.getModifiers(),
                                             draggedMouseEvent.getX(),
                                             draggedMouseEvent.getY(),
                                             1,
                                             false,
                                             draggedMouseEvent.getButton());

        /* ���������¼���AWT�¼������У���UI��ע�������¼�������ȥ���� */
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
     * �����Ƶı�ͷ�ؼ���
     */
    private JTableHeader m_controlledTableHeader;

    /**
     * ��ֹ��������λ�õ�����ͼ�����б�
     */
    private List m_disabledReorderingColumnViewIndexs = new ArrayList();

    /**
     * ��ֹ���¸ı��С������ͼ�����б�
     */
    private List m_disabledResizingColumnViewIndexs = new ArrayList();

    /**
     * ��ֹ���Ƶ�����ͼ�����б�
     */
    private List m_disabledMovingLeftColumnViewIndexs = new ArrayList();

    /**
     * ��ֹ���Ƶ�����ͼ�����б�
     */
    private List m_disabledMovingRightColumnViewIndexs = new ArrayList();

    /*------------------------------------- Static Initial Block -------------------------------------*/

    /* Add Static Initial Block */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

}
