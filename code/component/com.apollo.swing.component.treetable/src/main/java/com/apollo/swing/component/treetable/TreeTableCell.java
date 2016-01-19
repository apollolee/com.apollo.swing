/*
 * 此代码创建于 2009-9-14 下午02:37:36。
 */
package com.apollo.swing.component.treetable;

import java.awt.Color;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * <p>文件名称：TreeTableCell.java</p>
 * <p>类型描述：树表单元格类，封装了树表中的单元格数据，注意：树单元格除外。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2009-9-14</p>
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
public class TreeTableCell {

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

    /* Add Protected Field */

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /**
     * 值。
     */
    private Object m_value;

    /**
     * 标识是否可编辑。
     */
    private volatile boolean m_editable;

    /**
     * 前景色。
     */
    private Color m_foreground = null;

    /**
     * 背景色。
     */
    private Color m_background = null;

    /**
     * 渲染器。
     */
    private TableCellRenderer m_renderer;

    /**
     * 编辑器。
     */
    private TableCellEditor m_editor;

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 构造方法。
     * @param value 值对象。
     * @since T3 V1.1
     */
    public TreeTableCell(Object value) {
        this(value, false, null, null);
    }

    /**
     * 构造方法。
     * @param value 值对象。
     * @param editable 标识单元格是否允许编辑。
     * @param renderer 单元格渲染器。
     * @param editor 单元格编辑器。
     * @since T3 V1.1
     */
    public TreeTableCell(Object value, boolean editable, TableCellRenderer renderer, TableCellEditor editor) {
        m_value = value;
        m_editable = editable;
        m_renderer = renderer;
        m_editor = editor;
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * 获取字段 value 的值，值对象。
     * @return 字段 value 的值。
     * @since T3 V1.1
     */
    public Object getValue() {
        return m_value;
    }

    /**
     * 设置字段 value 的值，值对象。
     * @param value 字段 value 的值。
     * @since T3 V1.1
     */
    public void setValue(Object value) {
        m_value = value;
    }

    /**
     * 获取字段 editable 的值，标识单元格是否允许编辑。
     * @return 字段 editable 的值。
     * @since T3 V1.1
     */
    public boolean isEditable() {
        return m_editable;
    }

    /**
     * 设置字段 editable 的值，标识单元格是否允许编辑。
     * @param editable 字段 editable 的值。
     * @since T3 V1.1
     */
    public void setEditable(boolean editable) {
        m_editable = editable;
    }

    /**
     * 获取字段 foreground 的值，单元格的前景色。
     * @return 字段 foreground 的值。
     * @since T3 V1.1
     */
    public Color getForeground() {
        return m_foreground;
    }

    /**
     * 设置字段 foreground 的值，单元格的前背景色。
     * @param foreground 字段 foreground 的值。
     * @since T3 V1.1
     */
    public void setForeground(Color foreground) {
        m_foreground = foreground;
    }

    /**
     * 获取字段 background 的值，单元格的背景色，注意：表格自身可根据单元格是否可编辑来改变单元格显示的背景色，不可编辑单元格的背景色为浅灰色，但如果单元格有自己的渲染器或背景色则表格以单元格设置的渲染色为准。
     * @return 字段 background 的值。
     * @since T3 V1.1
     */
    public Color getBackground() {
        return m_background;
    }

    /**
     * 设置字段 background 的值，单元格的背景色，注意：表格自身可根据单元格是否可编辑来改变单元格显示的背景色，不可编辑单元格的背景色为浅灰色，但如果单元格有自己的渲染器或背景色则表格以单元格设置的渲染色为准。
     * @param background 字段 background 的值。
     * @since T3 V1.1
     */
    public void setBackground(Color background) {
        m_background = background;
    }

    /**
     * 获取字段 renderer 的值，单元格的渲染器。
     * @return 字段 renderer 的值。
     * @since T3 V1.1
     */
    public TableCellRenderer getRenderer() {
        return m_renderer;
    }

    /**
     * 设置字段 renderer 的值，单元格的渲染器。
     * @param renderer 字段 renderer 的值。
     * @since T3 V1.1
     */
    public void setRenderer(TableCellRenderer renderer) {
        m_renderer = renderer;
    }

    /**
     * 获取字段 editor 的值，单元格的编辑器。
     * @return 字段 editor 的值。
     * @since T3 V1.1
     */
    public TableCellEditor getEditor() {
        return m_editor;
    }

    /**
     * 设置字段 editor 的值，单元格的编辑器。
     * @param editor 字段 editor 的值。
     * @since T3 V1.1
     */
    public void setEditor(TableCellEditor editor) {
        m_editor = editor;
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
