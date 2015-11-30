/*
 * �˴��봴���� 2008-4-23 ����09:38:27��
 */
package com.apollo.swing.component.complextable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * <p>�ļ����ƣ�ComplexTableCell.java</p>
 * <p>�����������ۺϱ�Ԫ���࣬��װ�˶��ۺϱ�Ԫ������в�����</p>
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
public class ComplexTableCell implements Comparable {

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
     * ֵ��
     */
    protected Object m_value;

    /**
     * ��ֵ��
     */
    protected Object m_baseValue;

    /**
     * ��ʶ�Ƿ�ɱ༭��
     */
    protected volatile boolean m_editable;

    /**
     * ��Ⱦ����
     */
    protected TableCellRenderer m_renderer;

    /**
     * �༭����
     */
    protected TableCellEditor m_editor;

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
     * �˵����
     */
    protected List m_menuItems = new ArrayList();

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     * @param value ��Ԫ���װ��ֵ����
     * @since T3 V1.1
     */
    public ComplexTableCell(Object value) {
        this(value, false);
    }

    /**
     * ���췽����
     * @param value ��Ԫ���װ��ֵ����
     * @param editable ��ʶ��Ԫ���Ƿ�����༭��
     * @since T3 V1.2
     */
    public ComplexTableCell(Object value, boolean editable) {
        this(value, editable, null, null, null, null, null);
    }

    /**
     * ���췽����
     * @param value ��Ԫ���װ��ֵ����
     * @param editable ��ʶ��Ԫ���Ƿ�����༭��
     * @param renderer ������Ⱦ��Ԫ�����Ⱦ����
     * @param editor ���ڱ༭��Ԫ��ı༭����
     * @param background ��Ԫ��ı���ɫ������������Ⱦ����˲�����Ч��
     * @param foreground ��Ԫ���ǰ��ɫ������������Ⱦ����˲�����Ч��
     * @param menuItems ��Ԫ��Ĳ˵����
     * @since T3 V1.1
     */
    public ComplexTableCell(Object value,
                            boolean editable,
                            TableCellRenderer renderer,
                            TableCellEditor editor,
                            Color background,
                            Color foreground,
                            JMenuItem[] menuItems) {
        m_value = value;
        m_baseValue = m_value;
        m_editable = editable;
        m_renderer = renderer;
        m_editor = editor;
        m_background = background;
        m_foreground = foreground;

        if (menuItems != null) {
            m_menuItems.addAll(Arrays.asList(menuItems));
        }
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /**
     * ���췽����
     */
    protected ComplexTableCell() {
        this("");
    }

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * ��ȡ�ֶ� value ��ֵ��������Ԫ���װ��ֵ����
     * @return �ֶ� value ��ֵ��
     * @since T3 V1.1
     */
    public Object getValue() {
        return m_value;
    }

    /**
     * �����ֶ� value ��ֵ��������Ԫ���װ��ֵ����
     * @param value �ֶ� value ��ֵ��
     * @since T3 V1.1
     */
    public void setValue(Object value) {
        m_value = value;
    }

    /**
     * ��ȡ����ֵ��ע�⣺�����ͷ�������ʹ��������صĶ������÷�ʽ�ǵ�Ԫ���װ��ֵ����ʵ��Comparable�ӿڼ��ɣ����ûʵ����ʹ��ֵ�����toString��Ϊ����ֵ��
     * @return ����ֵ��
     * @since T3 V1.1
     */
    public Comparable getSortValue() {
        return m_value instanceof Comparable ? (Comparable) m_value : m_value.toString();
    }

    /**
     * ����ֵ�����ѵ�ǰֵд��ģ���С�
     * @since T3 V1.1
     */
    public void saveValue() {
        m_baseValue = m_value;
    }

    /**
     * �ָ�ֵ������ģ���е�ֵ���ǵ�ǰֵ��
     * @since T3 V1.1
     */
    public void restoreValue() {
        m_value = m_baseValue;
    }

    /**
     * ��ȡ�ֶ� baseValue ��ֵ������ȡģ���е�ֵ��
     * @return �ֶ� baseValue ��ֵ��
     * @since T3 V1.1
     */
    public Object getBaseValue() {
        return m_baseValue;
    }

    /**
     * �����ֶ� baseValue ��ֵ��������ģ���е�ֵ��
     * @param baseValue �ֶ� baseValue ��ֵ��
     * @since T3 V1.1
     */
    public void setBaseValue(Object baseValue) {
        m_baseValue = baseValue;
    }

    /**
     * �жϵ�Ԫ���ֵ�Ƿ��Ѹı䡣
     * @return Ϊtrue��ʾ��Ԫ���Ѹı䣬Ϊfalse��ʾ��Ԫ��δ�ı䡣
     * @since T3 V1.1
     */
    public boolean isChanged() {
        return m_value == null ? m_baseValue != null : !m_value.equals(m_baseValue);
    }

    /**
     * ��ȡ�ֶ� editable ��ֵ�����жϵ�Ԫ���Ƿ�ɱ༭��
     * @return �ֶ� editable ��ֵ��
     * @since T3 V1.1
     */
    public boolean isEditable() {
        return m_editable;
    }

    /**
     * �����ֶ� editable ��ֵ�������õ�Ԫ���Ƿ�ɱ༭��
     * @param editable �ֶ� editable ��ֵ��
     * @since T3 V1.1
     */
    public void setEditable(boolean editable) {
        m_editable = editable;
    }

    /**
     * ��ȡ�ֶ� renderer ��ֵ������ȡ��Ԫ�����Ⱦ������
     * @return �ֶ� renderer ��ֵ��
     * @since T3 V1.1
     */
    public TableCellRenderer getRenderer() {
        return m_renderer;
    }

    /**
     * �����ֶ� renderer ��ֵ�������õ�Ԫ�����Ⱦ������
     * @param renderer �ֶ� renderer ��ֵ��
     * @since T3 V1.1
     */
    public void setRenderer(TableCellRenderer renderer) {
        m_renderer = renderer;
    }

    /**
     * ��ȡ�ֶ� editor ��ֵ������ȡ��Ԫ��ı༭������
     * @return �ֶ� editor ��ֵ��
     * @since T3 V1.1
     */
    public TableCellEditor getEditor() {
        return m_editor;
    }

    /**
     * �����ֶ� editor ��ֵ�������õ�Ԫ��ı༭������
     * @param editor �ֶ� editor ��ֵ��
     * @since T3 V1.1
     */
    public void setEditor(TableCellEditor editor) {
        m_editor = editor;
    }

    /**
     * ��ȡ�ֶ� background ��ֵ������ȡ��Ԫ��ı���ɫ��ע�⣺������Ⱦ�����ֵ��Ч��
     * @return �ֶ� background ��ֵ��
     * @since T3 V1.1
     */
    public Color getBackground() {
        return m_background;
    }

    /**
     * �����ֶ� background ��ֵ�������õ�Ԫ��ı���ɫ��ע�⣺������Ⱦ�����ֵ��Ч��
     * @param background �ֶ� background ��ֵ��
     * @since T3 V1.1
     */
    public void setBackground(Color background) {
        m_background = background;
    }

    /**
     * ��ȡ�ֶ� foreground ��ֵ������ȡ��Ԫ���ǰ��ɫ��ע�⣺������Ⱦ�����ֵ��Ч��
     * @return �ֶ� foreground ��ֵ��
     * @since T3 V1.1
     */
    public Color getForeground() {
        return m_foreground;
    }

    /**
     * �����ֶ� foreground ��ֵ�������õ�Ԫ���ǰ��ɫ��ע�⣺������Ⱦ�����ֵ��Ч��
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
     * ��ȡ��Ԫ��Ĳ˵����
     * @return �˵����
     * @since T3 V1.1
     */
    public JMenuItem[] getMenuItems() {
        return (JMenuItem[]) m_menuItems.toArray(new JMenuItem[m_menuItems.size()]);
    }

    /**
     * ��Ӳ˵������
     * @param action �˵������
     * @since T3 V1.1
     */
    public void addAction(Action action) {
        addMenuItem(new JMenuItem(action));
    }

    /**
     * ��Ӳ˵������
     * @param index ������
     * @param action �˵������
     * @since T3 V1.1
     */
    public void addAction(int index, Action action) {
        addMenuItem(index, new JMenuItem(action));
    }

    /**
     * �Ƴ��˵������
     * @param index ������
     * @since T3 V1.1
     */
    public void removeAction(int index) {
        m_menuItems.remove(index);
    }

    /**
     * �Ƴ��˵������
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
     * �ж��Ƿ����ָ���Ĳ˵������
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
     * ��Ӳ˵��
     * @param menuItem �˵��
     * @since T3 V1.1
     */
    public void addMenuItem(JMenuItem menuItem) {
        m_menuItems.add(menuItem);
    }

    /**
     * ��Ӳ˵��
     * @param index ������
     * @param menuItem �˵��
     * @since T3 V1.1
     */
    public void addMenuItem(int index, JMenuItem menuItem) {
        m_menuItems.add(index, menuItem);
    }

    /**
     * �Ƴ��˵��
     * @param index ������
     * @since T3 V1.1
     */
    public void removeMenuItem(int index) {
        m_menuItems.remove(index);
    }

    /**
     * �Ƴ��˵��
     * @param menuItem �˵��
     * @since T3 V1.1
     */
    public void removeMenuItem(JMenuItem menuItem) {
        m_menuItems.remove(menuItem);
    }

    /**
     * �ж��Ƿ����ָ���Ĳ˵��
     * @param menuItem �˵��
     * @return ��ʶ�Ƿ����ָ���Ĳ˵��
     * @since T3 V1.1
     */
    public boolean containsMenuItem(JMenuItem menuItem) {
        return m_menuItems.contains(menuItem);
    }

    /*------------------------------------- Comparable Public Method -------------------------------------*/

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object cell) {
        if (cell instanceof ComplexTableCell) {
            return getSortValue().compareTo(((ComplexTableCell) cell).getSortValue());

        } else {
            return 1;
        }
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
