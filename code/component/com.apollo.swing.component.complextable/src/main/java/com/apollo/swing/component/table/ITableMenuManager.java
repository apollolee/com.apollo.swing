/*
 * �˴��봴���� 2008-5-8 ����03:27:52��
 */
package com.apollo.swing.component.table;

import javax.swing.JPopupMenu;
import javax.swing.JTable;

/**
 * <p>�ļ����ƣ�ITableMenuManager.java</p>
 * <p>�������������˵��������ӿڡ�</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-5-8</p>
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
public interface ITableMenuManager {

    /*------------------------------------- Public Static Final Field -------------------------------------*/

    /* Add Public Static Final Field */

    /*------------------------------------- Public Abstract Method -------------------------------------*/

    /**
     * �������˵���
     * @param table ���
     * @param menu �˵���
     */
    public abstract void manageCommonMenu(JTable table, JPopupMenu menu);

    /**
     * ����������˵���
     * @param table ���
     * @param menu �˵���
     */
    public abstract void manageScrollPaneMenu(JTable table, JPopupMenu menu);

    /**
     * �����ͷ�˵���
     * @param table ���
     * @param menu �˵���
     * @param column ��������
     */
    public abstract void manageTableHeaderMenu(JTable table, JPopupMenu menu, int column);

    /**
     * ������˵���
     * @param table ���
     * @param menu �˵���
     * @param row ��������
     * @param column ��������
     */
    public abstract void manageTableMenu(JTable table, JPopupMenu menu, int row, int column);

}
