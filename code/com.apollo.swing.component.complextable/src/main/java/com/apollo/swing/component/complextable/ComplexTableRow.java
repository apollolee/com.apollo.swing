/*
 * �˴��봴���� 2008-4-23 ����09:39:01��
 */
package com.apollo.swing.component.complextable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Action;
import javax.swing.JMenuItem;

/**
 * <p>�ļ����ƣ�ComplexTableRow.java</p>
 * <p>�����������ۺϱ����࣬��װ���ۺϱ���ж�������ݺͲ������˶���Ҳ��һ��List�����������ж�������ĵ�Ԫ�����</p>
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
public class ComplexTableRow extends ArrayList implements IConstants {

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

    /**
     * �����У����߷�����
     * @param modelIndex ģ�����������ɺ��������ж����ظ�����ģ�������ĸ������ģ������������һ���ġ�
     * @param cells ��Ԫ�񼯡�
     * @return �С�
     * @since T3 V1.1
     */
    public static ComplexTableRow create(int modelIndex, Object[] cells) {
        ComplexTableRow row = new ComplexTableRow(modelIndex);

        for (int i = 0; i < cells.length; i++) {
            row.add(cells[i] instanceof ComplexTableCell ? cells[i] : new ComplexTableCell(cells[i]));
        }

        return row;
    }

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
     * ģ��������
     */
    protected volatile int m_modelIndex;

    /**
     * ��ʶ�Ƿ�Ϊ����ӵ��С�
     */
    protected volatile boolean m_isInserted;

    /**
     * ��ʶ�Ƿ�Ϊ���Ƴ����С�
     */
    protected volatile boolean m_isRemoved;

    /**
     * ��ʶ�Ƿ�ɱ༭��
     */
    protected volatile boolean m_editable;

    /**
     * ����ɫ��
     */
    protected Color m_background;

    /**
     * ǰ��ɫ��
     */
    protected Color m_foreground;

    /**
     * ��ʾ��
     */
    protected String m_toolTipText = null;

    /**
     * �û�����
     */
    protected Object m_userObject = null;

    /**
     * �˵������ͷ���е�Ԫ�񶼻���ʾ����
     */
    protected List m_menuItems = new ArrayList();

    /**
     * ��ͷ�˵����
     */
    protected List m_headerMenuItems = new ArrayList();

    /**
     * �е�Ԫ��˵����
     */
    protected List m_cellMenuItems = new ArrayList();

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     * @since T3 V1.1
     */
    public ComplexTableRow() {
        this(1);
    }

    /**
     * ���췽����
     * @param modelIndex ģ�����������ɺ��������ж����ظ�����ģ�������ĸ������ģ������������һ���ġ�
     * @since T3 V1.1
     */
    public ComplexTableRow(int modelIndex) {
        this(modelIndex, false, false, true, null, null, null);
    }

    /**
     * ���췽����
     * @param modelIndex ģ�����������ɺ��������ж����ظ�����ģ�������ĸ������ģ������������һ���ġ�
     * @param isInserted ��ʶ�Ƿ�Ϊ����ӵ��У�Ϊtrue����ͷ�ϻ���һ���Ӻŵı�ʶ��ȱʡΪfalse��
     * @param isRemoved ��ʶ�Ƿ�Ϊ���Ƴ����У�Ϊtrue����ͷ�ϻ���һ�����ŵı�ʶ��ȱʡΪfalse��
     * @param editable ��ʶ�Ƿ�����༭��ȱʡΪ����༭��
     * @param background �������е�Ԫ��ı���ɫ�������ñ���ɫ�ĵ�Ԫ�����Դ˲�����
     * @param foreground �������е�Ԫ���ǰ��ɫ��������ǰ��ɫ�ĵ�Ԫ�����Դ˲�����
     * @param menuItems �����ݲ˵������ͷ���еĵ�Ԫ����Ч����
     * @since T3 V1.1
     */
    public ComplexTableRow(int modelIndex,
                           boolean isInserted,
                           boolean isRemoved,
                           boolean editable,
                           Color background,
                           Color foreground,
                           JMenuItem[] menuItems) {
        m_modelIndex = modelIndex;
        m_isInserted = isInserted;
        m_isRemoved = isRemoved;
        m_editable = editable;
        m_background = background;
        m_foreground = foreground;

        if (menuItems != null) {
            m_menuItems.addAll(Arrays.asList(menuItems));
        }
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * ��ȡ�ֶ� modelIndex ��ֵ����ȡģ�����������ɺ��������ж����ظ�����ģ�������ĸ������ģ������������һ���ġ�
     * @return �ֶ� modelIndex ��ֵ��
     * @since T3 V1.1
     */
    public int getModelIndex() {
        return m_modelIndex;
    }

    /**
     * �����ֶ� modelIndex ��ֵ������ģ�����������ɺ��������ж����ظ�����ģ�������ĸ������ģ������������һ���ġ�
     * @param modelIndex �ֶ� modelIndex ��ֵ��
     * @since T3 V1.1
     */
    public void setModelIndex(int modelIndex) {
        m_modelIndex = modelIndex;
    }

    /**
     * ��ȡ�ֶ� isInserted ��ֵ���ж��Ƿ�Ϊ����ӵ��У�Ϊtrue����ͷ�ϻ���һ���Ӻŵı�ʶ��ȱʡΪfalse��
     * @return �ֶ� isInserted ��ֵ��
     * @since T3 V1.1
     */
    public boolean isInserted() {
        return m_isInserted;
    }

    /**
     * �����ֶ� isInserted ��ֵ�������Ƿ�Ϊ����ӵ��У�Ϊtrue����ͷ�ϻ���һ���Ӻŵı�ʶ��ȱʡΪfalse����
     * @param isInserted �ֶ� isInserted ��ֵ��
     * @since T3 V1.1
     */
    public void setInserted(boolean isInserted) {
        m_isInserted = isInserted;
    }

    /**
     * ��ȡ�ֶ� isRemoved ��ֵ���ж��Ƿ�Ϊ���Ƴ����У�Ϊtrue����ͷ�ϻ���һ�����ŵı�ʶ��ȱʡΪfalse��
     * @return �ֶ� isRemoved ��ֵ��
     * @since T3 V1.1
     */
    public boolean isRemoved() {
        return m_isRemoved;
    }

    /**
     * �����ֶ� isRemoved ��ֵ�������Ƿ�Ϊ���Ƴ����У�Ϊtrue����ͷ�ϻ���һ�����ŵı�ʶ��ȱʡΪfalse��
     * @param isRemoved �ֶ� isRemoved ��ֵ��
     * @since T3 V1.1
     */
    public void setRemoved(boolean isRemoved) {
        m_isRemoved = isRemoved;
        setEditable(!m_isRemoved);
    }

    /**
     * ��ȡ�ֶ� isChanged ��ֵ���ж��Ƿ�Ϊ���޸ĵ��У�Ϊtrue����ͷ�ϻ���һ���Ǻŵı�ʶ��
     * @return �ֶ� isChanged ��ֵ��
     * @since T3 V1.1
     */
    public boolean isChanged() {
        for (int i = 0, size = size(); i < size; i++) {
            if (getCell(i).isChanged()) {
                return true;
            }
        }

        return false;
    }

    /**
     * ��ȡ�ֶ� editable ��ֵ���ж��Ƿ�����༭��ȱʡΪ����༭��
     * @return �ֶ� editable ��ֵ��
     * @since T3 V1.1
     */
    public boolean isEditable() {
        return m_editable;
    }

    /**
     * �����ֶ� editable ��ֵ�������Ƿ�����༭��ȱʡΪ����༭��
     * @param editable �ֶ� editable ��ֵ��
     * @since T3 V1.1
     */
    public void setEditable(boolean editable) {
        m_editable = editable;
    }

    /**
     * ��ȡ�ֶ� background ��ֵ����ȡ�������е�Ԫ��ı���ɫ�������ñ���ɫ�ĵ�Ԫ�����Դ˲�����
     * @return �ֶ� background ��ֵ��
     * @since T3 V1.1
     */
    public Color getBackground() {
        return m_background;
    }

    /**
     * �����ֶ� background ��ֵ�������������е�Ԫ��ı���ɫ�������ñ���ɫ�ĵ�Ԫ�����Դ˲�����
     * @param background �ֶ� background ��ֵ��
     * @since T3 V1.1
     */
    public void setBackground(Color background) {
        m_background = background;
    }

    /**
     * ��ȡ�ֶ� foreground ��ֵ����ȡ�������е�Ԫ���ǰ��ɫ��������ǰ��ɫ�ĵ�Ԫ�����Դ˲�����
     * @return �ֶ� foreground ��ֵ��
     * @since T3 V1.1
     */
    public Color getForeground() {
        return m_foreground;
    }

    /**
     * �����ֶ� foreground ��ֵ�������������е�Ԫ���ǰ��ɫ��������ǰ��ɫ�ĵ�Ԫ�����Դ˲�����
     * @param foreground �ֶ� foreground ��ֵ��
     * @since T3 V1.1
     */
    public void setForeground(Color foreground) {
        m_foreground = foreground;
    }

    /**
     * ��ȡ��ʾ��
     * @return ��ʾ��
     */
    public String getToolTipText() {
        return m_toolTipText;
    }

    /**
     * ������ʾ��
     * @param toolTipText ��ʾ��
     */
    public void setToolTipText(String toolTipText) {
        m_toolTipText = toolTipText;
    }

    /**
     * ��ȡ�ֶ� userObject ��ֵ����ȡ�û�������������ۺϱ���ȫ��ʹ�ã����ṩ��Ӧ�������а�һ�����ݶ���ġ�
     * @return �ֶ� userObject ��ֵ��
     * @since T3 V1.1
     */
    public Object getUserObject() {
        return m_userObject;
    }

    /**
     * �����ֶ� userObject ��ֵ�������û�������������ۺϱ���ȫ��ʹ�ã����ṩ��Ӧ�������а�һ�����ݶ���ġ�
     * @param userObject �ֶ� userObject ��ֵ��
     * @since T3 V1.1
     */
    public void setUserObject(Object userObject) {
        m_userObject = userObject;
    }

    /**
     * ��ȡ�˵������ͷ���еĵ�Ԫ����Ч����
     * @return �˵����
     * @since T3 V1.1
     */
    public JMenuItem[] getMenuItems() {
        return (JMenuItem[]) m_menuItems.toArray(new JMenuItem[m_menuItems.size()]);
    }

    /**
     * ��ȡ��ͷ�˵��������ͷ��Ч����
     * @return ��ͷ�˵����
     * @since T3 V1.1
     */
    public JMenuItem[] getHeaderMenuItems() {
        return (JMenuItem[]) m_headerMenuItems.toArray(new JMenuItem[m_headerMenuItems.size()]);
    }

    /**
     * ��ȡ�е�Ԫ��˵�������еĵ�Ԫ����Ч����
     * @return �е�Ԫ��˵����
     * @since T3 V1.1
     */
    public JMenuItem[] getCellMenuItems() {
        return (JMenuItem[]) m_cellMenuItems.toArray(new JMenuItem[m_cellMenuItems.size()]);
    }

    /**
     * ��Ӳ˵��������ͷ���еĵ�Ԫ����Ч����
     * @param action �˵������
     * @since T3 V1.1
     */
    public void addAction(Action action) {
        addMenuItem(new JMenuItem(action));
    }

    /**
     * ��Ӳ˵��������ͷ���еĵ�Ԫ����Ч����
     * @param index ������
     * @param action �˵������
     * @since T3 V1.1
     */
    public void addAction(int index, Action action) {
        addMenuItem(index, new JMenuItem(action));
    }

    /**
     * �Ƴ��˵��������ͷ���еĵ�Ԫ����Ч����
     * @param index ������
     * @since T3 V1.1
     */
    public void removeAction(int index) {
        m_menuItems.remove(index);
    }

    /**
     * �Ƴ��˵��������ͷ���еĵ�Ԫ����Ч����
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
     * �ж��Ƿ����ָ���Ĳ˵��������ͷ���еĵ�Ԫ����Ч����
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
     * �����ͷ�˵����������ͷ��Ч����
     * @param headerAction ��ͷ�˵������
     * @since T3 V1.1
     */
    public void addHeaderAction(Action headerAction) {
        addHeaderMenuItem(new JMenuItem(headerAction));
    }

    /**
     * �����ͷ�˵����������ͷ��Ч����
     * @param index ������
     * @param headerAction ��ͷ�˵������
     * @since T3 V1.1
     */
    public void addHeaderAction(int index, Action headerAction) {
        addHeaderMenuItem(index, new JMenuItem(headerAction));
    }

    /**
     * �Ƴ���ͷ�˵����������ͷ��Ч����
     * @param index ������
     * @since T3 V1.1
     */
    public void removeHeaderAction(int index) {
        m_headerMenuItems.remove(index);
    }

    /**
     * �Ƴ���ͷ�˵����������ͷ��Ч����
     * @param headerAction ��ͷ�˵������
     * @since T3 V1.1
     */
    public void removeHeaderAction(Action headerAction) {
        for (int i = m_headerMenuItems.size() - 1; i >= 0; i--) {
            if (((JMenuItem) m_headerMenuItems.get(i)).getAction() == headerAction) {
                m_headerMenuItems.remove(i);
            }
        }
    }

    /**
     * �ж��Ƿ����ָ������ͷ�˵����������ͷ��Ч����
     * @param headerAction ��ͷ�˵������
     * @return ��ʶ�Ƿ����ָ������ͷ�˵������
     * @since T3 V1.1
     */
    public boolean containsHeaderAction(Action headerAction) {
        for (int i = m_headerMenuItems.size() - 1; i >= 0; i--) {
            if (((JMenuItem) m_headerMenuItems.get(i)).getAction() == headerAction) {
                return true;
            }
        }
        return false;
    }

    /**
     * ����е�Ԫ��˵���������еĵ�Ԫ����Ч����
     * @param cellAction �е�Ԫ��˵������
     * @since T3 V1.1
     */
    public void addCellAction(Action cellAction) {
        addCellMenuItem(new JMenuItem(cellAction));
    }

    /**
     * ����е�Ԫ��˵���������еĵ�Ԫ����Ч����
     * @param index ������
     * @param cellAction �е�Ԫ��˵������
     * @since T3 V1.1
     */
    public void addCellAction(int index, Action cellAction) {
        addCellMenuItem(index, new JMenuItem(cellAction));
    }

    /**
     * �Ƴ��е�Ԫ��˵���������еĵ�Ԫ����Ч����
     * @param index ������
     * @since T3 V1.1
     */
    public void removeCellAction(int index) {
        m_cellMenuItems.remove(index);
    }

    /**
     * �Ƴ��е�Ԫ��˵���������еĵ�Ԫ����Ч����
     * @param cellAction �е�Ԫ��˵������
     * @since T3 V1.1
     */
    public void removeCellAction(Action cellAction) {
        for (int i = m_cellMenuItems.size() - 1; i >= 0; i--) {
            if (((JMenuItem) m_cellMenuItems.get(i)).getAction() == cellAction) {
                m_cellMenuItems.remove(i);
            }
        }
    }

    /**
     * �ж��Ƿ����ָ�����е�Ԫ��˵���������еĵ�Ԫ����Ч����
     * @param cellAction �е�Ԫ��˵������
     * @return ��ʶ�Ƿ����ָ�����е�Ԫ��˵������
     * @since T3 V1.1
     */
    public boolean containsCellAction(Action cellAction) {
        for (int i = m_cellMenuItems.size() - 1; i >= 0; i--) {
            if (((JMenuItem) m_cellMenuItems.get(i)).getAction() == cellAction) {
                return true;
            }
        }
        return false;
    }

    /**
     * ��Ӳ˵����ͷ���еĵ�Ԫ����Ч����
     * @param menuItem �˵��
     * @since T3 V1.1
     */
    public void addMenuItem(JMenuItem menuItem) {
        m_menuItems.add(menuItem);
    }

    /**
     * ��Ӳ˵����ͷ���еĵ�Ԫ����Ч����
     * @param index ������
     * @param menuItem �˵��
     * @since T3 V1.1
     */
    public void addMenuItem(int index, JMenuItem menuItem) {
        m_menuItems.add(index, menuItem);
    }

    /**
     * �Ƴ��˵����ͷ���еĵ�Ԫ����Ч����
     * @param index ������
     * @since T3 V1.1
     */
    public void removeMenuItem(int index) {
        m_menuItems.remove(index);
    }

    /**
     * �Ƴ��˵����ͷ���еĵ�Ԫ����Ч����
     * @param menuItem �˵��
     * @since T3 V1.1
     */
    public void removeMenuItem(JMenuItem menuItem) {
        m_menuItems.remove(menuItem);
    }

    /**
     * �ж��Ƿ����ָ���Ĳ˵����ͷ���еĵ�Ԫ����Ч����
     * @param menuItem �˵��
     * @return ��ʶ�Ƿ����ָ���Ĳ˵��
     * @since T3 V1.1
     */
    public boolean containsMenuItem(JMenuItem menuItem) {
        return m_menuItems.contains(menuItem);
    }

    /**
     * �����ͷ�˵������ͷ��Ч����
     * @param headerMenuItem ��ͷ�˵��
     * @since T3 V1.1
     */
    public void addHeaderMenuItem(JMenuItem headerMenuItem) {
        m_headerMenuItems.add(headerMenuItem);
    }

    /**
     * �����ͷ�˵������ͷ��Ч����
     * @param index ������
     * @param headerMenuItem ��ͷ�˵��
     * @since T3 V1.1
     */
    public void addHeaderMenuItem(int index, JMenuItem headerMenuItem) {
        m_headerMenuItems.add(index, headerMenuItem);
    }

    /**
     * �Ƴ���ͷ�˵������ͷ��Ч����
     * @param index ������
     * @since T3 V1.1
     */
    public void removeHeaderMenuItem(int index) {
        m_headerMenuItems.remove(index);
    }

    /**
     * �Ƴ���ͷ�˵������ͷ��Ч����
     * @param headerMenuItem ��ͷ�˵��
     * @since T3 V1.1
     */
    public void removeHeaderMenuItem(JMenuItem headerMenuItem) {
        m_headerMenuItems.remove(headerMenuItem);
    }

    /**
     * �ж��Ƿ����ָ������ͷ�˵������ͷ��Ч����
     * @param headerMenuItem ��ͷ�˵��
     * @return ��ʶ�Ƿ����ָ������ͷ�˵��
     * @since T3 V1.1
     */
    public boolean containsHeaderMenuItem(JMenuItem headerMenuItem) {
        return m_headerMenuItems.contains(headerMenuItem);
    }

    /**
     * ����е�Ԫ��˵�����еĵ�Ԫ����Ч����
     * @param cellMenuItem �е�Ԫ��˵��
     * @since T3 V1.1
     */
    public void addCellMenuItem(JMenuItem cellMenuItem) {
        m_cellMenuItems.add(cellMenuItem);
    }

    /**
     * ����е�Ԫ��˵�����еĵ�Ԫ����Ч����
     * @param index ������
     * @param cellMenuItem �е�Ԫ��˵��
     * @since T3 V1.1
     */
    public void addCellMenuItem(int index, JMenuItem cellMenuItem) {
        m_cellMenuItems.add(index, cellMenuItem);
    }

    /**
     * �Ƴ��е�Ԫ��˵�����еĵ�Ԫ����Ч����
     * @param index ������
     * @since T3 V1.1
     */
    public void removeCellMenuItem(int index) {
        m_cellMenuItems.remove(index);
    }

    /**
     * �Ƴ��е�Ԫ��˵�����еĵ�Ԫ����Ч����
     * @param cellMenuItem �е�Ԫ��˵��
     * @since T3 V1.1
     */
    public void removeCellMenuItem(JMenuItem cellMenuItem) {
        m_cellMenuItems.remove(cellMenuItem);
    }

    /**
     * �ж��Ƿ����ָ�����е�Ԫ��˵�����еĵ�Ԫ����Ч����
     * @param cellMenuItem �е�Ԫ��˵��
     * @return ��ʶ�Ƿ����ָ�����е�Ԫ��˵��
     * @since T3 V1.1
     */
    public boolean containsCellMenuItem(JMenuItem cellMenuItem) {
        return m_cellMenuItems.contains(cellMenuItem);
    }

    /**
     * ��ȡ��Ԫ�����
     * @param index ��ģ��������
     * @return ��Ԫ��
     * @since T3 V1.1
     */
    public ComplexTableCell getCell(int index) {
        return (ComplexTableCell) get(index);
    }

    /*------------------------------------- AbstractCollection Public Method -------------------------------------*/

    /**
     * @see java.util.AbstractCollection#toString()
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("<html><font color=\"#0000ff\">");

        if (isRemoved()) {
            sb.append(I18N_INFO_REMOVED);

        } else if (isInserted()) {
            sb.append(I18N_INFO_INSERTED);

        } else if (isChanged()) {
            sb.append(I18N_INFO_CHANGED);

        } else {
            sb.append(I18N_INFO_UNCHANGED);
        }

        sb.append("</font>");

        if (!isEditable()) {
            sb.append(", <font color=\"#ff0000\">");
            sb.append(I18N_INFO_INEDITABLE);
            sb.append("</font>");
        }

        return sb.toString();
    }

    /*------------------------------------- ArrayList Public Method -------------------------------------*/

    /**
     * @see java.util.ArrayList#get(int)
     */
    public Object get(int index) {
        int size = size();

        if (index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == size) {
            add(index, new ComplexTableCell());
        }

        return super.get(index);
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * ���档
     */
    protected void save() {
        for (int i = 0, size = size(); i < size; i++) {
            getCell(i).saveValue();
        }

        m_isInserted = false;
    }

    /**
     * �ָ���
     */
    protected void restore() {
        if (m_isRemoved) {
            setRemoved(false);

        } else {
            for (int i = 0, size = size(); i < size; i++) {
                getCell(i).restoreValue();
            }
        }
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
