/*
 * 此代码创建于 2008-4-23 上午10:37:44。
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
 * <p>文件名称：ComplexTableColumn.java</p>
 * <p>类型描述：综合表列类，这个类主要是封装了综合表列对象的相关操作和数据。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-4-23</p>
 * <p>修改记录：</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * @version 1.0
 * @author 李镇
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
     * 创建列，这是一个工具方法。
     * @param identifier 列标识对象。
     * @return 列。
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
     * 类型。
     */
    protected Class m_type;

    /**
     * 标识是否可排序。
     */
    protected volatile boolean m_sortable;

    /**
     * 标识是否可编辑。
     */
    protected volatile boolean m_editable;

    /**
     * 排序图标。
     */
    protected SortIcon m_sort = new SortIcon();

    /**
     * 渲染器组件。
     */
    protected JLabel m_rc = new JLabel(m_sort);

    /**
     * 菜单项集（列头和列单元格都会显示）。
     */
    protected List m_menuItems = new ArrayList();

    /**
     * 列头菜单项集。
     */
    protected List m_headerMenuItems = new ArrayList();

    /**
     * 列单元格菜单项集。
     */
    protected List m_cellMenuItems = new ArrayList();

    /*------------------------------------- Friendly Field -------------------------------------*/

    /**
     * 标识是否使能。
     */
    volatile boolean m_isEnabled = true;

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 构造方法。
     * @param identifier 标识对象。
     * @since T3 V1.1
     */
    public ComplexTableColumn(Object identifier) {
        this(identifier, true);
    }

    /**
     * 构造方法。
     * @param identifier 标识对象。
     * @param preferredWidth 列的首选宽度。
     * @since T3 V1.1
     */
    public ComplexTableColumn(Object identifier, int preferredWidth) {
        this(identifier, Object.class, true, true, null, preferredWidth);
    }

    /**
     * 构造方法。
     * @param identifier 标识对象。
     * @param sortable 标识列是否允许排序，缺省允许。
     * @since T3 V1.1
     */
    public ComplexTableColumn(Object identifier, boolean sortable) {
        this(identifier, sortable, true);
    }

    /**
     * 构造方法。
     * @param identifier 标识对象。
     * @param sortable 标识列是否允许排序，缺省允许。
     * @param editable 标识列下的单元格是否允许编辑，缺省允许。
     * @since T3 V1.2
     */
    public ComplexTableColumn(Object identifier, boolean sortable, boolean editable) {
        this(identifier, Object.class, sortable, editable, null);
    }

    /**
     * 构造方法。
     * @param identifier 标识对象。
     * @param type 列的类型，可用于表的公共渲染，此值缺省为Object.class。
     * @param sortable 标识列是否允许排序，缺省允许。
     * @param editable 标识列下的单元格是否允许编辑，缺省允许。
     * @param menuItems 列菜单项集，在列头和列的单元格上都有效。
     * @since T3 V1.1
     */
    public ComplexTableColumn(Object identifier, Class type, boolean sortable, boolean editable, JMenuItem[] menuItems) {
        this(identifier, type, sortable, editable, menuItems, 75);
    }

    /**
     * 构造方法。
     * @param identifier 标识对象。
     * @param type 列的类型，可用于表的公共渲染，此值缺省为Object.class。
     * @param sortable 标识列是否允许排序，缺省允许。
     * @param editable 标识列下的单元格是否允许编辑，缺省允许。
     * @param menuItems 列菜单项集，在列头和列的单元格上都有效。
     * @param preferredWidth 列的首选宽度。
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
     * 获取字段 type 的值，列的类型，可用于表的公共渲染，此值缺省为Object.class。
     * @return 字段 type 的值。
     * @since T3 V1.1
     */
    public Class getType() {
        return m_type;
    }

    /**
     * 设置字段 type 的值，列的类型，可用于表的公共渲染，此值缺省为Object.class。
     * @param type 字段 type 的值。
     * @since T3 V1.1
     */
    public void setType(Class type) {
        m_type = type;
    }

    /**
     * 获取字段 sortable 的值，标识列是否允许排序，缺省允许。
     * @return 字段 sortable 的值。
     * @since T3 V1.1
     */
    public boolean isSortable() {
        return m_sortable;
    }

    /**
     * 设置字段 sortable 的值，标识列是否允许排序，缺省允许。
     * @param sortable 字段 sortable 的值。
     * @since T3 V1.1
     */
    public void setSortable(boolean sortable) {
        m_sortable = sortable;

        if (!m_sortable) {
            setSortState(SORTSTATE_NORMAL);
        }
    }

    /**
     * 获取字段 editable 的值，标识列下的单元格是否允许编辑，缺省允许。
     * @return 字段 editable 的值。
     * @since T3 V1.1
     */
    public boolean isEditable() {
        return m_editable;
    }

    /**
     * 设置字段 editable 的值，标识列下的单元格是否允许编辑，缺省允许。
     * @param editable 字段 editable 的值。
     * @since T3 V1.1
     */
    public void setEditable(boolean editable) {
        m_editable = editable;
    }

    /**
     * 获取排序索引，多列排序中此值标识为第几排序列。
     * @return 排序索引。
     * @since T3 V1.1
     */
    public int getSortIndex() {
        return m_sort.getIndex();
    }

    /**
     * 设置排序索引，多列排序中此值标识为第几排序列，注意：这个方法主要用于初始表格时就指定排序方式，这个值的一致性由应用自己维护。
     * @param sortIndex 排序索引。
     * @since T3 V1.1
     */
    public void setSortIndex(int sortIndex) {
        m_sort.setIndex(sortIndex);
    }

    /**
     * 获取排序状态，即当前列是降序、升序还是无序。
     * @return 排序状态。
     * @since T3 V1.1
     */
    public int getSortState() {
        return m_sort.getState();
    }

    /**
     * 设置排序状态，即设置当前列是降序、升序还是无序。
     * @param sortState 排序状态。
     * @since T3 V1.1
     */
    public void setSortState(int sortState) {
        m_sort.setState(sortState);
    }

    /**
     * 获取菜单项集（列头和列的单元格都有效）。
     * @return 菜单项集。
     * @since T3 V1.1
     */
    public JMenuItem[] getMenuItems() {
        return (JMenuItem[]) m_menuItems.toArray(new JMenuItem[m_menuItems.size()]);
    }

    /**
     * 获取列头菜单项集（仅列头有效）。
     * @return 列头菜单项集。
     * @since T3 V1.1
     */
    public JMenuItem[] getHeaderMenuItems() {
        return (JMenuItem[]) m_headerMenuItems.toArray(new JMenuItem[m_headerMenuItems.size()]);
    }

    /**
     * 获取列单元格菜单项集（仅列的单元格都有效）。
     * @return 列单元格菜单项集。
     * @since T3 V1.1
     */
    public JMenuItem[] getCellMenuItems() {
        return (JMenuItem[]) m_cellMenuItems.toArray(new JMenuItem[m_cellMenuItems.size()]);
    }

    /**
     * 添加菜单项动作（列头和列的单元格都有效）。
     * @param action 菜单项动作。
     * @since T3 V1.1
     */
    public void addAction(Action action) {
        addMenuItem(new JMenuItem(action));
    }

    /**
     * 添加菜单项动作（列头和列的单元格都有效）。
     * @param index 索引。
     * @param action 菜单项动作。
     * @since T3 V1.1
     */
    public void addAction(int index, Action action) {
        addMenuItem(index, new JMenuItem(action));
    }

    /**
     * 移除菜单项动作（列头和列的单元格都有效）。
     * @param index 索引。
     * @since T3 V1.1
     */
    public void removeAction(int index) {
        m_menuItems.remove(index);
    }

    /**
     * 移除菜单项动作（列头和列的单元格都有效）。
     * @param action 菜单项动作。
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
     * 判断是否包含指定的菜单项动作（列头和列的单元格都有效）。
     * @param action 菜单项动作。
     * @return 标识是否包含指定的菜单项动作。
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
     * 添加列头菜单项动作（仅列头有效）。
     * @param headerAction 列头菜单项动作。
     * @since T3 V1.1
     */
    public void addHeaderAction(Action headerAction) {
        addHeaderMenuItem(new JMenuItem(headerAction));
    }

    /**
     * 添加列头菜单项动作（仅列头有效）。
     * @param index 索引。
     * @param headerAction 列头菜单项动作。
     * @since T3 V1.1
     */
    public void addHeaderAction(int index, Action headerAction) {
        addHeaderMenuItem(index, new JMenuItem(headerAction));
    }

    /**
     * 移除列头菜单项动作（仅列头有效）。
     * @param index 索引。
     * @since T3 V1.1
     */
    public void removeHeaderAction(int index) {
        m_headerMenuItems.remove(index);
    }

    /**
     * 移除列头菜单项动作（仅列头有效）。
     * @param headerAction 列头菜单项动作。
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
     * 判断是否包含指定的列头菜单项动作（仅列头有效）。
     * @param headerAction 列头菜单项动作。
     * @return 标识是否包含指定的列头菜单项动作。
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
     * 添加列单元格菜单项动作（仅列的单元格都有效）。
     * @param cellAction 列单元格菜单项动作。
     * @since T3 V1.1
     */
    public void addCellAction(Action cellAction) {
        addCellMenuItem(new JMenuItem(cellAction));
    }

    /**
     * 添加列单元格菜单项动作（仅列的单元格都有效）。
     * @param index 索引。
     * @param cellAction 列单元格菜单项动作。
     * @since T3 V1.1
     */
    public void addCellAction(int index, Action cellAction) {
        addCellMenuItem(index, new JMenuItem(cellAction));
    }

    /**
     * 移除列单元格菜单项动作（仅列的单元格都有效）。
     * @param index 索引。
     * @since T3 V1.1
     */
    public void removeCellAction(int index) {
        m_cellMenuItems.remove(index);
    }

    /**
     * 移除列单元格菜单项动作（仅列的单元格都有效）。
     * @param cellAction 列单元格菜单项动作。
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
     * 判断是否包含指定的列单元格菜单项动作（仅列的单元格都有效）。
     * @param cellAction 列单元格菜单项动作。
     * @return 标识是否包含指定的列单元格菜单项动作。
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
     * 添加菜单项（列头和列的单元格都有效）。
     * @param menuItem 菜单项。
     * @since T3 V1.1
     */
    public void addMenuItem(JMenuItem menuItem) {
        m_menuItems.add(menuItem);
    }

    /**
     * 添加菜单项（列头和列的单元格都有效）。
     * @param index 索引。
     * @param menuItem 菜单项。
     * @since T3 V1.1
     */
    public void addMenuItem(int index, JMenuItem menuItem) {
        m_menuItems.add(index, menuItem);
    }

    /**
     * 移除菜单项（列头和列的单元格都有效）。
     * @param index 索引。
     * @since T3 V1.1
     */
    public void removeMenuItem(int index) {
        m_menuItems.remove(index);
    }

    /**
     * 移除菜单项（列头和列的单元格都有效）。
     * @param menuItem 菜单项。
     * @since T3 V1.1
     */
    public void removeMenuItem(JMenuItem menuItem) {
        m_menuItems.remove(menuItem);
    }

    /**
     * 判断是否包含指定的菜单项（列头和列的单元格都有效）。
     * @param menuItem 菜单项。
     * @return 标识是否包含指定的菜单项。
     * @since T3 V1.1
     */
    public boolean containsMenuItem(JMenuItem menuItem) {
        return m_menuItems.contains(menuItem);
    }

    /**
     * 添加列头菜单项（仅列头有效）。
     * @param headerMenuItem 列头菜单项。
     * @since T3 V1.1
     */
    public void addHeaderMenuItem(JMenuItem headerMenuItem) {
        m_headerMenuItems.add(headerMenuItem);
    }

    /**
     * 添加列头菜单项（仅列头有效）。
     * @param index 索引。
     * @param headerMenuItem 列头菜单项。
     * @since T3 V1.1
     */
    public void addHeaderMenuItem(int index, JMenuItem headerMenuItem) {
        m_headerMenuItems.add(index, headerMenuItem);
    }

    /**
     * 移除列头菜单项（仅列头有效）。
     * @param index 索引。
     * @since T3 V1.1
     */
    public void removeHeaderMenuItem(int index) {
        m_headerMenuItems.remove(index);
    }

    /**
     * 移除列头菜单项（仅列头有效）。
     * @param headerMenuItem 列头菜单项。
     * @since T3 V1.1
     */
    public void removeHeaderMenuItem(JMenuItem headerMenuItem) {
        m_headerMenuItems.remove(headerMenuItem);
    }

    /**
     * 判断是否包含指定的列头菜单项（仅列头有效）。
     * @param headerMenuItem 列头菜单项。
     * @return 标识是否包含指定的列头菜单项。
     * @since T3 V1.1
     */
    public boolean containsHeaderMenuItem(JMenuItem headerMenuItem) {
        return m_headerMenuItems.contains(headerMenuItem);
    }

    /**
     * 添加列单元格菜单项（仅列的单元格都有效）。
     * @param cellMenuItem 列单元格菜单项。
     * @since T3 V1.1
     */
    public void addCellMenuItem(JMenuItem cellMenuItem) {
        m_cellMenuItems.add(cellMenuItem);
    }

    /**
     * 添加列单元格菜单项（仅列的单元格都有效）。
     * @param index 索引。
     * @param cellMenuItem 列单元格菜单项。
     * @since T3 V1.1
     */
    public void addCellMenuItem(int index, JMenuItem cellMenuItem) {
        m_cellMenuItems.add(index, cellMenuItem);
    }

    /**
     * 移除列单元格菜单项（仅列的单元格都有效）。
     * @param index 索引。
     * @since T3 V1.1
     */
    public void removeCellMenuItem(int index) {
        m_cellMenuItems.remove(index);
    }

    /**
     * 移除列单元格菜单项（仅列的单元格都有效）。
     * @param cellMenuItem 列单元格菜单项。
     * @since T3 V1.1
     */
    public void removeCellMenuItem(JMenuItem cellMenuItem) {
        m_cellMenuItems.remove(cellMenuItem);
    }

    /**
     * 判断是否包含指定的列单元格菜单项（仅列的单元格都有效）。
     * @param cellMenuItem 列单元格菜单项。
     * @return 标识是否包含指定的列单元格菜单项。
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
