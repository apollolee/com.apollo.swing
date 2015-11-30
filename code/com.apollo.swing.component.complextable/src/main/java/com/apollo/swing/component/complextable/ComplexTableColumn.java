/*
 * �˴��봴���� 2008-4-23 ����10:37:44��
 */
package com.apollo.swing.component.complextable;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.apollo.swing.component.icon.SortIcon;

/**
 * <p>�ļ����ƣ�ComplexTableColumn.java</p>
 * <p>�����������ۺϱ����࣬�������Ҫ�Ƿ�װ���ۺϱ��ж������ز��������ݡ�</p>
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
public class ComplexTableColumn extends TableColumn implements SwingConstants, IConstants, TableCellRenderer {

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
     * �����У�����һ�����߷�����
     * @param identifier �б�ʶ����
     * @return �С�
     * @since T3 V1.1
     */
    public static ComplexTableColumn create(Object identifier) {
        if (identifier instanceof ComplexTableColumn) {
            return (ComplexTableColumn) identifier;

        } else {
            return new ComplexTableColumn(identifier);
        }
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
     * ���͡�
     */
    protected Class m_type;

    /**
     * ��ʶ�Ƿ������
     */
    protected volatile boolean m_sortable;

    /**
     * ��ʶ�Ƿ�ɱ༭��
     */
    protected volatile boolean m_editable;

    /**
     * ����ͼ�ꡣ
     */
    protected SortIcon m_sort = new SortIcon();

    /**
     * ��Ⱦ�������
     */
    protected JLabel m_rc = new JLabel(m_sort);

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

    /**
     * ��ʶ�Ƿ�ʹ�ܡ�
     */
    volatile boolean m_isEnabled = true;

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     * @param identifier ��ʶ����
     * @since T3 V1.1
     */
    public ComplexTableColumn(Object identifier) {
        this(identifier, true);
    }

    /**
     * ���췽����
     * @param identifier ��ʶ����
     * @param preferredWidth �е���ѡ��ȡ�
     * @since T3 V1.1
     */
    public ComplexTableColumn(Object identifier, int preferredWidth) {
        this(identifier, Object.class, true, true, null, preferredWidth);
    }

    /**
     * ���췽����
     * @param identifier ��ʶ����
     * @param sortable ��ʶ���Ƿ���������ȱʡ����
     * @since T3 V1.1
     */
    public ComplexTableColumn(Object identifier, boolean sortable) {
        this(identifier, sortable, true);
    }

    /**
     * ���췽����
     * @param identifier ��ʶ����
     * @param sortable ��ʶ���Ƿ���������ȱʡ����
     * @param editable ��ʶ���µĵ�Ԫ���Ƿ�����༭��ȱʡ����
     * @since T3 V1.2
     */
    public ComplexTableColumn(Object identifier, boolean sortable, boolean editable) {
        this(identifier, Object.class, sortable, editable, null);
    }

    /**
     * ���췽����
     * @param identifier ��ʶ����
     * @param type �е����ͣ������ڱ�Ĺ�����Ⱦ����ֵȱʡΪObject.class��
     * @param sortable ��ʶ���Ƿ���������ȱʡ����
     * @param editable ��ʶ���µĵ�Ԫ���Ƿ�����༭��ȱʡ����
     * @param menuItems �в˵��������ͷ���еĵ�Ԫ���϶���Ч��
     * @since T3 V1.1
     */
    public ComplexTableColumn(Object identifier, Class type, boolean sortable, boolean editable, JMenuItem[] menuItems) {
        this(identifier, type, sortable, editable, menuItems, 75);
    }

    /**
     * ���췽����
     * @param identifier ��ʶ����
     * @param type �е����ͣ������ڱ�Ĺ�����Ⱦ����ֵȱʡΪObject.class��
     * @param sortable ��ʶ���Ƿ���������ȱʡ����
     * @param editable ��ʶ���µĵ�Ԫ���Ƿ�����༭��ȱʡ����
     * @param menuItems �в˵��������ͷ���еĵ�Ԫ���϶���Ч��
     * @param preferredWidth �е���ѡ��ȡ�
     * @since T3 V1.1
     */
    public ComplexTableColumn(Object identifier, Class type, boolean sortable, boolean editable, JMenuItem[] menuItems, int preferredWidth) {
        setIdentifier(identifier);
        setHeaderValue(this);
        setHeaderRenderer(this);

        setMinWidth(15);
        setMaxWidth(99999);
        setPreferredWidth(preferredWidth);
        setWidth(preferredWidth);

        m_type = type;
        m_sortable = sortable;
        m_editable = editable;

        m_rc.setHorizontalTextPosition(LEFT);
        m_rc.setBorder(UIManager.getBorder("TableHeader.cellBorder"));

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
     * ��ȡ�ֶ� type ��ֵ���е����ͣ������ڱ�Ĺ�����Ⱦ����ֵȱʡΪObject.class��
     * @return �ֶ� type ��ֵ��
     * @since T3 V1.1
     */
    public Class getType() {
        return m_type;
    }

    /**
     * �����ֶ� type ��ֵ���е����ͣ������ڱ�Ĺ�����Ⱦ����ֵȱʡΪObject.class��
     * @param type �ֶ� type ��ֵ��
     * @since T3 V1.1
     */
    public void setType(Class type) {
        m_type = type;
    }

    /**
     * ��ȡ�ֶ� sortable ��ֵ����ʶ���Ƿ���������ȱʡ����
     * @return �ֶ� sortable ��ֵ��
     * @since T3 V1.1
     */
    public boolean isSortable() {
        return m_sortable;
    }

    /**
     * �����ֶ� sortable ��ֵ����ʶ���Ƿ���������ȱʡ����
     * @param sortable �ֶ� sortable ��ֵ��
     * @since T3 V1.1
     */
    public void setSortable(boolean sortable) {
        m_sortable = sortable;

        if (!m_sortable) {
            setSortState(SORTSTATE_NORMAL);
        }
    }

    /**
     * ��ȡ�ֶ� editable ��ֵ����ʶ���µĵ�Ԫ���Ƿ�����༭��ȱʡ����
     * @return �ֶ� editable ��ֵ��
     * @since T3 V1.1
     */
    public boolean isEditable() {
        return m_editable;
    }

    /**
     * �����ֶ� editable ��ֵ����ʶ���µĵ�Ԫ���Ƿ�����༭��ȱʡ����
     * @param editable �ֶ� editable ��ֵ��
     * @since T3 V1.1
     */
    public void setEditable(boolean editable) {
        m_editable = editable;
    }

    /**
     * ��ȡ�������������������д�ֵ��ʶΪ�ڼ������С�
     * @return ����������
     * @since T3 V1.1
     */
    public int getSortIndex() {
        return m_sort.getIndex();
    }

    /**
     * �����������������������д�ֵ��ʶΪ�ڼ������У�ע�⣺���������Ҫ���ڳ�ʼ���ʱ��ָ������ʽ�����ֵ��һ������Ӧ���Լ�ά����
     * @param sortIndex ����������
     * @since T3 V1.1
     */
    public void setSortIndex(int sortIndex) {
        m_sort.setIndex(sortIndex);
    }

    /**
     * ��ȡ����״̬������ǰ���ǽ�������������
     * @return ����״̬��
     * @since T3 V1.1
     */
    public int getSortState() {
        return m_sort.getState();
    }

    /**
     * ��������״̬�������õ�ǰ���ǽ�������������
     * @param sortState ����״̬��
     * @since T3 V1.1
     */
    public void setSortState(int sortState) {
        m_sort.setState(sortState);
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

    /*------------------------------------- Object Public Method -------------------------------------*/

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("<html>");
        sb.append(getIdentifier().toString());

        sb.append(" (<font color=\"#0000ff\">");
        sb.append(m_sort.toString());
        sb.append("</font>");

        if (!isEditable()) {
            sb.append(" , <font color=\"#ff0000\">");
            sb.append(I18N_INFO_INEDITABLE);
            sb.append("</font>");
        }

        sb.append(")");

        return sb.toString();
    }

    /*------------------------------------- TableCellRenderer Public Method -------------------------------------*/

    /**
     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        m_rc.setText(getIdentifier().toString());
        m_rc.setToolTipText(toString());

        if (table != null) {
            JTableHeader header = table.getTableHeader();

            if (header != null) {
                m_rc.setForeground(header.getForeground());
                m_rc.setBackground(header.getBackground());
                m_rc.setFont(header.getFont());
            }
        }

        m_rc.setEnabled(m_isEnabled);

        return m_rc;
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
