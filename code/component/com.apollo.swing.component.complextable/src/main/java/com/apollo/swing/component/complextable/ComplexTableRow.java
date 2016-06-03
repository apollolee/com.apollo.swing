/*
 * 此代码创建于 2008-4-23 上午09:39:01。
 */
package com.apollo.swing.component.complextable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Action;
import javax.swing.JMenuItem;

/**
 * <p>文件名称：ComplexTableRow.java</p>
 * <p>类型描述：综合表行类，封装了综合表的行对象的数据和操作，此对象也是一个List，用于容纳行对象包含的单元格对象。</p>
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
     * 创建行，工具方法。
     * @param modelIndex 模型索引，不可和其他的行对象重复，行模型索引的概念和列模型索引概念是一样的。
     * @param cells 单元格集。
     * @return 行。
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
     * 模型索引。
     */
    protected volatile int m_modelIndex;

    /**
     * 标识是否为已添加的行。
     */
    protected volatile boolean m_isInserted;

    /**
     * 标识是否为已移除的行。
     */
    protected volatile boolean m_isRemoved;

    /**
     * 标识是否可编辑。
     */
    protected volatile boolean m_editable;

    /**
     * 背景色。
     */
    protected Color m_background;

    /**
     * 前景色。
     */
    protected Color m_foreground;

    /**
     * 提示。
     */
    protected String m_toolTipText = null;

    /**
     * 用户对象。
     */
    protected Object m_userObject = null;

    /**
     * 菜单项集（行头和行单元格都会显示）。
     */
    protected List m_menuItems = new ArrayList();

    /**
     * 行头菜单项集。
     */
    protected List m_headerMenuItems = new ArrayList();

    /**
     * 行单元格菜单项集。
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
     * 构造方法。
     * @since T3 V1.1
     */
    public ComplexTableRow() {
        this(1);
    }

    /**
     * 构造方法。
     * @param modelIndex 模型索引，不可和其他的行对象重复，行模型索引的概念和列模型索引概念是一样的。
     * @since T3 V1.1
     */
    public ComplexTableRow(int modelIndex) {
        this(modelIndex, false, false, true, null, null, null);
    }

    /**
     * 构造方法。
     * @param modelIndex 模型索引，不可和其他的行对象重复，行模型索引的概念和列模型索引概念是一样的。
     * @param isInserted 标识是否为已添加的行，为true则行头上会有一个加号的标识，缺省为false。
     * @param isRemoved 标识是否为已移除的行，为true则行头上会有一个减号的标识，缺省为false。
     * @param editable 标识是否允许编辑，缺省为允许编辑。
     * @param background 行上所有单元格的背景色，已设置背景色的单元格会忽略此参数。
     * @param foreground 行上所有单元格的前景色，已设置前景色的单元格会忽略此参数。
     * @param menuItems 行数据菜单项集（行头和行的单元格都有效）。
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
     * 获取字段 modelIndex 的值，获取模型索引，不可和其他的行对象重复，行模型索引的概念和列模型索引概念是一样的。
     * @return 字段 modelIndex 的值。
     * @since T3 V1.1
     */
    public int getModelIndex() {
        return m_modelIndex;
    }

    /**
     * 设置字段 modelIndex 的值，设置模型索引，不可和其他的行对象重复，行模型索引的概念和列模型索引概念是一样的。
     * @param modelIndex 字段 modelIndex 的值。
     * @since T3 V1.1
     */
    public void setModelIndex(int modelIndex) {
        m_modelIndex = modelIndex;
    }

    /**
     * 获取字段 isInserted 的值，判断是否为已添加的行，为true则行头上会有一个加号的标识，缺省为false。
     * @return 字段 isInserted 的值。
     * @since T3 V1.1
     */
    public boolean isInserted() {
        return m_isInserted;
    }

    /**
     * 设置字段 isInserted 的值，设置是否为已添加的行，为true则行头上会有一个加号的标识，缺省为false。。
     * @param isInserted 字段 isInserted 的值。
     * @since T3 V1.1
     */
    public void setInserted(boolean isInserted) {
        m_isInserted = isInserted;
    }

    /**
     * 获取字段 isRemoved 的值，判断是否为已移除的行，为true则行头上会有一个减号的标识，缺省为false。
     * @return 字段 isRemoved 的值。
     * @since T3 V1.1
     */
    public boolean isRemoved() {
        return m_isRemoved;
    }

    /**
     * 设置字段 isRemoved 的值，设置是否为已移除的行，为true则行头上会有一个减号的标识，缺省为false。
     * @param isRemoved 字段 isRemoved 的值。
     * @since T3 V1.1
     */
    public void setRemoved(boolean isRemoved) {
        m_isRemoved = isRemoved;
        setEditable(!m_isRemoved);
    }

    /**
     * 获取字段 isChanged 的值，判断是否为已修改的行，为true则行头上会有一个星号的标识。
     * @return 字段 isChanged 的值。
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
     * 获取字段 editable 的值，判断是否允许编辑，缺省为允许编辑。
     * @return 字段 editable 的值。
     * @since T3 V1.1
     */
    public boolean isEditable() {
        return m_editable;
    }

    /**
     * 设置字段 editable 的值，设置是否允许编辑，缺省为允许编辑。
     * @param editable 字段 editable 的值。
     * @since T3 V1.1
     */
    public void setEditable(boolean editable) {
        m_editable = editable;
    }

    /**
     * 获取字段 background 的值，获取行上所有单元格的背景色，已设置背景色的单元格会忽略此参数。
     * @return 字段 background 的值。
     * @since T3 V1.1
     */
    public Color getBackground() {
        return m_background;
    }

    /**
     * 设置字段 background 的值，设置行上所有单元格的背景色，已设置背景色的单元格会忽略此参数。
     * @param background 字段 background 的值。
     * @since T3 V1.1
     */
    public void setBackground(Color background) {
        m_background = background;
    }

    /**
     * 获取字段 foreground 的值，获取行上所有单元格的前景色，已设置前景色的单元格会忽略此参数。
     * @return 字段 foreground 的值。
     * @since T3 V1.1
     */
    public Color getForeground() {
        return m_foreground;
    }

    /**
     * 设置字段 foreground 的值，设置行上所有单元格的前景色，已设置前景色的单元格会忽略此参数。
     * @param foreground 字段 foreground 的值。
     * @since T3 V1.1
     */
    public void setForeground(Color foreground) {
        m_foreground = foreground;
    }

    /**
     * 获取提示。
     * @return 提示。
     */
    public String getToolTipText() {
        return m_toolTipText;
    }

    /**
     * 设置提示。
     * @param toolTipText 提示。
     */
    public void setToolTipText(String toolTipText) {
        m_toolTipText = toolTipText;
    }

    /**
     * 获取字段 userObject 的值，获取用户对象，这个对象综合表完全不使用，是提供给应用用于行绑定一个数据对象的。
     * @return 字段 userObject 的值。
     * @since T3 V1.1
     */
    public Object getUserObject() {
        return m_userObject;
    }

    /**
     * 设置字段 userObject 的值，设置用户对象，这个对象综合表完全不使用，是提供给应用用于行绑定一个数据对象的。
     * @param userObject 字段 userObject 的值。
     * @since T3 V1.1
     */
    public void setUserObject(Object userObject) {
        m_userObject = userObject;
    }

    /**
     * 获取菜单项集（行头和行的单元格都有效）。
     * @return 菜单项集。
     * @since T3 V1.1
     */
    public JMenuItem[] getMenuItems() {
        return (JMenuItem[]) m_menuItems.toArray(new JMenuItem[m_menuItems.size()]);
    }

    /**
     * 获取行头菜单项集（仅行头有效）。
     * @return 行头菜单项集。
     * @since T3 V1.1
     */
    public JMenuItem[] getHeaderMenuItems() {
        return (JMenuItem[]) m_headerMenuItems.toArray(new JMenuItem[m_headerMenuItems.size()]);
    }

    /**
     * 获取行单元格菜单项集（仅行的单元格有效）。
     * @return 行单元格菜单项集。
     * @since T3 V1.1
     */
    public JMenuItem[] getCellMenuItems() {
        return (JMenuItem[]) m_cellMenuItems.toArray(new JMenuItem[m_cellMenuItems.size()]);
    }

    /**
     * 添加菜单项动作（行头和行的单元格都有效）。
     * @param action 菜单项动作。
     * @since T3 V1.1
     */
    public void addAction(Action action) {
        addMenuItem(new JMenuItem(action));
    }

    /**
     * 添加菜单项动作（行头和行的单元格都有效）。
     * @param index 索引。
     * @param action 菜单项动作。
     * @since T3 V1.1
     */
    public void addAction(int index, Action action) {
        addMenuItem(index, new JMenuItem(action));
    }

    /**
     * 移除菜单项动作（行头和行的单元格都有效）。
     * @param index 索引。
     * @since T3 V1.1
     */
    public void removeAction(int index) {
        m_menuItems.remove(index);
    }

    /**
     * 移除菜单项动作（行头和行的单元格都有效）。
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
     * 判断是否包含指定的菜单项动作（行头和行的单元格都有效）。
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
     * 添加行头菜单项动作（仅行头有效）。
     * @param headerAction 行头菜单项动作。
     * @since T3 V1.1
     */
    public void addHeaderAction(Action headerAction) {
        addHeaderMenuItem(new JMenuItem(headerAction));
    }

    /**
     * 添加行头菜单项动作（仅行头有效）。
     * @param index 索引。
     * @param headerAction 行头菜单项动作。
     * @since T3 V1.1
     */
    public void addHeaderAction(int index, Action headerAction) {
        addHeaderMenuItem(index, new JMenuItem(headerAction));
    }

    /**
     * 移除行头菜单项动作（仅行头有效）。
     * @param index 索引。
     * @since T3 V1.1
     */
    public void removeHeaderAction(int index) {
        m_headerMenuItems.remove(index);
    }

    /**
     * 移除行头菜单项动作（仅行头有效）。
     * @param headerAction 行头菜单项动作。
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
     * 判断是否包含指定的行头菜单项动作（仅行头有效）。
     * @param headerAction 行头菜单项动作。
     * @return 标识是否包含指定的行头菜单项动作。
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
     * 添加行单元格菜单项动作（仅行的单元格有效）。
     * @param cellAction 行单元格菜单项动作。
     * @since T3 V1.1
     */
    public void addCellAction(Action cellAction) {
        addCellMenuItem(new JMenuItem(cellAction));
    }

    /**
     * 添加行单元格菜单项动作（仅行的单元格有效）。
     * @param index 索引。
     * @param cellAction 行单元格菜单项动作。
     * @since T3 V1.1
     */
    public void addCellAction(int index, Action cellAction) {
        addCellMenuItem(index, new JMenuItem(cellAction));
    }

    /**
     * 移除行单元格菜单项动作（仅行的单元格有效）。
     * @param index 索引。
     * @since T3 V1.1
     */
    public void removeCellAction(int index) {
        m_cellMenuItems.remove(index);
    }

    /**
     * 移除行单元格菜单项动作（仅行的单元格有效）。
     * @param cellAction 行单元格菜单项动作。
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
     * 判断是否包含指定的行单元格菜单项动作（仅行的单元格有效）。
     * @param cellAction 行单元格菜单项动作。
     * @return 标识是否包含指定的行单元格菜单项动作。
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
     * 添加菜单项（行头和行的单元格都有效）。
     * @param menuItem 菜单项。
     * @since T3 V1.1
     */
    public void addMenuItem(JMenuItem menuItem) {
        m_menuItems.add(menuItem);
    }

    /**
     * 添加菜单项（行头和行的单元格都有效）。
     * @param index 索引。
     * @param menuItem 菜单项。
     * @since T3 V1.1
     */
    public void addMenuItem(int index, JMenuItem menuItem) {
        m_menuItems.add(index, menuItem);
    }

    /**
     * 移除菜单项（行头和行的单元格都有效）。
     * @param index 索引。
     * @since T3 V1.1
     */
    public void removeMenuItem(int index) {
        m_menuItems.remove(index);
    }

    /**
     * 移除菜单项（行头和行的单元格都有效）。
     * @param menuItem 菜单项。
     * @since T3 V1.1
     */
    public void removeMenuItem(JMenuItem menuItem) {
        m_menuItems.remove(menuItem);
    }

    /**
     * 判断是否包含指定的菜单项（行头和行的单元格都有效）。
     * @param menuItem 菜单项。
     * @return 标识是否包含指定的菜单项。
     * @since T3 V1.1
     */
    public boolean containsMenuItem(JMenuItem menuItem) {
        return m_menuItems.contains(menuItem);
    }

    /**
     * 添加行头菜单项（仅行头有效）。
     * @param headerMenuItem 行头菜单项。
     * @since T3 V1.1
     */
    public void addHeaderMenuItem(JMenuItem headerMenuItem) {
        m_headerMenuItems.add(headerMenuItem);
    }

    /**
     * 添加行头菜单项（仅行头有效）。
     * @param index 索引。
     * @param headerMenuItem 行头菜单项。
     * @since T3 V1.1
     */
    public void addHeaderMenuItem(int index, JMenuItem headerMenuItem) {
        m_headerMenuItems.add(index, headerMenuItem);
    }

    /**
     * 移除行头菜单项（仅行头有效）。
     * @param index 索引。
     * @since T3 V1.1
     */
    public void removeHeaderMenuItem(int index) {
        m_headerMenuItems.remove(index);
    }

    /**
     * 移除行头菜单项（仅行头有效）。
     * @param headerMenuItem 行头菜单项。
     * @since T3 V1.1
     */
    public void removeHeaderMenuItem(JMenuItem headerMenuItem) {
        m_headerMenuItems.remove(headerMenuItem);
    }

    /**
     * 判断是否包含指定的行头菜单项（仅行头有效）。
     * @param headerMenuItem 行头菜单项。
     * @return 标识是否包含指定的行头菜单项。
     * @since T3 V1.1
     */
    public boolean containsHeaderMenuItem(JMenuItem headerMenuItem) {
        return m_headerMenuItems.contains(headerMenuItem);
    }

    /**
     * 添加行单元格菜单项（仅行的单元格有效）。
     * @param cellMenuItem 行单元格菜单项。
     * @since T3 V1.1
     */
    public void addCellMenuItem(JMenuItem cellMenuItem) {
        m_cellMenuItems.add(cellMenuItem);
    }

    /**
     * 添加行单元格菜单项（仅行的单元格有效）。
     * @param index 索引。
     * @param cellMenuItem 行单元格菜单项。
     * @since T3 V1.1
     */
    public void addCellMenuItem(int index, JMenuItem cellMenuItem) {
        m_cellMenuItems.add(index, cellMenuItem);
    }

    /**
     * 移除行单元格菜单项（仅行的单元格有效）。
     * @param index 索引。
     * @since T3 V1.1
     */
    public void removeCellMenuItem(int index) {
        m_cellMenuItems.remove(index);
    }

    /**
     * 移除行单元格菜单项（仅行的单元格有效）。
     * @param cellMenuItem 行单元格菜单项。
     * @since T3 V1.1
     */
    public void removeCellMenuItem(JMenuItem cellMenuItem) {
        m_cellMenuItems.remove(cellMenuItem);
    }

    /**
     * 判断是否包含指定的行单元格菜单项（仅行的单元格有效）。
     * @param cellMenuItem 行单元格菜单项。
     * @return 标识是否包含指定的行单元格菜单项。
     * @since T3 V1.1
     */
    public boolean containsCellMenuItem(JMenuItem cellMenuItem) {
        return m_cellMenuItems.contains(cellMenuItem);
    }

    /**
     * 获取单元格对象。
     * @param index 列模型索引。
     * @return 单元格。
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
     * 保存。
     */
    protected void save() {
        for (int i = 0, size = size(); i < size; i++) {
            getCell(i).saveValue();
        }

        m_isInserted = false;
    }

    /**
     * 恢复。
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
