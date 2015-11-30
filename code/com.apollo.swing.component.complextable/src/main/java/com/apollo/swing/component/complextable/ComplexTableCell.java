/*
 * 此代码创建于 2008-4-23 上午09:38:27。
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
 * <p>文件名称：ComplexTableCell.java</p>
 * <p>类型描述：综合表单元格类，封装了对综合表单元格的所有操作。</p>
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
     * 值。
     */
    protected Object m_value;

    /**
     * 基值。
     */
    protected Object m_baseValue;

    /**
     * 标识是否可编辑。
     */
    protected volatile boolean m_editable;

    /**
     * 渲染器。
     */
    protected TableCellRenderer m_renderer;

    /**
     * 编辑器。
     */
    protected TableCellEditor m_editor;

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
     * 菜单项集。
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
     * 构造方法。
     * @param value 单元格封装的值对象。
     * @since T3 V1.1
     */
    public ComplexTableCell(Object value) {
        this(value, false);
    }

    /**
     * 构造方法。
     * @param value 单元格封装的值对象。
     * @param editable 标识单元格是否允许编辑。
     * @since T3 V1.2
     */
    public ComplexTableCell(Object value, boolean editable) {
        this(value, editable, null, null, null, null, null);
    }

    /**
     * 构造方法。
     * @param value 单元格封装的值对象。
     * @param editable 标识单元格是否允许编辑。
     * @param renderer 用于渲染单元格的渲染器。
     * @param editor 用于编辑单元格的编辑器。
     * @param background 单元格的背景色，如设置了渲染器则此参数无效。
     * @param foreground 单元格的前景色，如设置了渲染器则此参数无效。
     * @param menuItems 单元格的菜单项集。
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
     * 构造方法。
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
     * 获取字段 value 的值，即：单元格封装的值对象。
     * @return 字段 value 的值。
     * @since T3 V1.1
     */
    public Object getValue() {
        return m_value;
    }

    /**
     * 设置字段 value 的值，即：单元格封装的值对象。
     * @param value 字段 value 的值。
     * @since T3 V1.1
     */
    public void setValue(Object value) {
        m_value = value;
    }

    /**
     * 获取排序值，注意：点击列头排序就是使用这个返回的对象，设置方式是单元格封装的值对象实现Comparable接口即可，如果没实现则使用值对象的toString作为排序值。
     * @return 排序值。
     * @since T3 V1.1
     */
    public Comparable getSortValue() {
        return m_value instanceof Comparable ? (Comparable) m_value : m_value.toString();
    }

    /**
     * 保存值，即把当前值写入模型中。
     * @since T3 V1.1
     */
    public void saveValue() {
        m_baseValue = m_value;
    }

    /**
     * 恢复值，即用模型中的值覆盖当前值。
     * @since T3 V1.1
     */
    public void restoreValue() {
        m_value = m_baseValue;
    }

    /**
     * 获取字段 baseValue 的值，即获取模型中的值。
     * @return 字段 baseValue 的值。
     * @since T3 V1.1
     */
    public Object getBaseValue() {
        return m_baseValue;
    }

    /**
     * 设置字段 baseValue 的值，即设置模型中的值。
     * @param baseValue 字段 baseValue 的值。
     * @since T3 V1.1
     */
    public void setBaseValue(Object baseValue) {
        m_baseValue = baseValue;
    }

    /**
     * 判断单元格的值是否已改变。
     * @return 为true表示单元格已改变，为false表示单元格未改变。
     * @since T3 V1.1
     */
    public boolean isChanged() {
        return m_value == null ? m_baseValue != null : !m_value.equals(m_baseValue);
    }

    /**
     * 获取字段 editable 的值，即判断单元格是否可编辑。
     * @return 字段 editable 的值。
     * @since T3 V1.1
     */
    public boolean isEditable() {
        return m_editable;
    }

    /**
     * 设置字段 editable 的值，即设置单元格是否可编辑。
     * @param editable 字段 editable 的值。
     * @since T3 V1.1
     */
    public void setEditable(boolean editable) {
        m_editable = editable;
    }

    /**
     * 获取字段 renderer 的值，即获取单元格的渲染器对象。
     * @return 字段 renderer 的值。
     * @since T3 V1.1
     */
    public TableCellRenderer getRenderer() {
        return m_renderer;
    }

    /**
     * 设置字段 renderer 的值，即设置单元格的渲染器对象。
     * @param renderer 字段 renderer 的值。
     * @since T3 V1.1
     */
    public void setRenderer(TableCellRenderer renderer) {
        m_renderer = renderer;
    }

    /**
     * 获取字段 editor 的值，即获取单元格的编辑器对象。
     * @return 字段 editor 的值。
     * @since T3 V1.1
     */
    public TableCellEditor getEditor() {
        return m_editor;
    }

    /**
     * 设置字段 editor 的值，即设置单元格的编辑器对象。
     * @param editor 字段 editor 的值。
     * @since T3 V1.1
     */
    public void setEditor(TableCellEditor editor) {
        m_editor = editor;
    }

    /**
     * 获取字段 background 的值，即获取单元格的背景色，注意：设置渲染器后此值无效。
     * @return 字段 background 的值。
     * @since T3 V1.1
     */
    public Color getBackground() {
        return m_background;
    }

    /**
     * 设置字段 background 的值，即设置单元格的背景色，注意：设置渲染器后此值无效。
     * @param background 字段 background 的值。
     * @since T3 V1.1
     */
    public void setBackground(Color background) {
        m_background = background;
    }

    /**
     * 获取字段 foreground 的值，即获取单元格的前景色，注意：设置渲染器后此值无效。
     * @return 字段 foreground 的值。
     * @since T3 V1.1
     */
    public Color getForeground() {
        return m_foreground;
    }

    /**
     * 设置字段 foreground 的值，即设置单元格的前景色，注意：设置渲染器后此值无效。
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
     * 获取单元格的菜单项集。
     * @return 菜单项集。
     * @since T3 V1.1
     */
    public JMenuItem[] getMenuItems() {
        return (JMenuItem[]) m_menuItems.toArray(new JMenuItem[m_menuItems.size()]);
    }

    /**
     * 添加菜单项动作。
     * @param action 菜单项动作。
     * @since T3 V1.1
     */
    public void addAction(Action action) {
        addMenuItem(new JMenuItem(action));
    }

    /**
     * 添加菜单项动作。
     * @param index 索引。
     * @param action 菜单项动作。
     * @since T3 V1.1
     */
    public void addAction(int index, Action action) {
        addMenuItem(index, new JMenuItem(action));
    }

    /**
     * 移除菜单项动作。
     * @param index 索引。
     * @since T3 V1.1
     */
    public void removeAction(int index) {
        m_menuItems.remove(index);
    }

    /**
     * 移除菜单项动作。
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
     * 判断是否包含指定的菜单项动作。
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
     * 添加菜单项。
     * @param menuItem 菜单项。
     * @since T3 V1.1
     */
    public void addMenuItem(JMenuItem menuItem) {
        m_menuItems.add(menuItem);
    }

    /**
     * 添加菜单项。
     * @param index 索引。
     * @param menuItem 菜单项。
     * @since T3 V1.1
     */
    public void addMenuItem(int index, JMenuItem menuItem) {
        m_menuItems.add(index, menuItem);
    }

    /**
     * 移除菜单项。
     * @param index 索引。
     * @since T3 V1.1
     */
    public void removeMenuItem(int index) {
        m_menuItems.remove(index);
    }

    /**
     * 移除菜单项。
     * @param menuItem 菜单项。
     * @since T3 V1.1
     */
    public void removeMenuItem(JMenuItem menuItem) {
        m_menuItems.remove(menuItem);
    }

    /**
     * 判断是否包含指定的菜单项。
     * @param menuItem 菜单项。
     * @return 标识是否包含指定的菜单项。
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
