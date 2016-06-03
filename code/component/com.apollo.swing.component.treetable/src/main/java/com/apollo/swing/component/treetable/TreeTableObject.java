/*
 * 此代码创建于 2009-9-16 上午10:14:36。
 */
package com.apollo.swing.component.treetable;

import java.awt.Color;
import java.util.List;

/**
 * <p>文件名称：TreeTableObject.java</p>
 * <p>类型描述：树表对象类，这个类就是封装的综合树节点的userObject对象，里面有对单元格列表的封装。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2009-9-16</p>
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
public class TreeTableObject {

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
     * 节点前景色。
     */
    private Color m_NodeForeground = null;

    /**
     * 节点背景色。
     */
    private Color m_NodeBackground = null;

    /**
     * 单元格集。
     */
    private List m_cells;

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 构造方法。
     * @param value 值对象，这个值对象用于综合树节点的显示，相当于综合树节点的userObject，只不过被封装得更深了。
     * @since T3 V1.1
     */
    public TreeTableObject(Object value) {
        this(value, null);
    }

    /**
     * 构造方法。
     * @param value 值对象，这个值对象用于综合树节点的显示，相当于综合树节点的userObject，只不过被封装得更深了。
     * @param cells 单元格集，这里的单元格集的长度不一定要和列一样长，当对应列上没有单元格对象时，相应的单元格值是空字符串，不可编辑，并且使用缺省渲染。
     * @since T3 V1.1
     */
    public TreeTableObject(Object value, List cells) {
        this(value, true, cells);
    }

    /**
     * 构造方法。
     * @param value 值对象，这个值对象用于综合树节点的显示，相当于综合树节点的userObject，只不过被封装得更深了。
     * @param editable 标识这个节点所带的单元格是否允许编辑，注意：这里不是标识树节点是否允许编辑的，树表中的节点是不允许编辑的。
     * @param cells 单元格集，这里的单元格集的长度不一定要和列一样长，当对应列上没有单元格对象时，相应的单元格值是空字符串，不可编辑，并且使用缺省渲染。
     * @since T3 V1.1
     */
    public TreeTableObject(Object value, boolean editable, List cells) {
        m_value = value;
        m_editable = editable;
        m_cells = cells;
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * 获取字段 value 的值，值对象，这个值对象用于综合树节点的显示，相当于综合树节点的userObject，只不过被封装得更深了。
     * @return 字段 value 的值。
     * @since T3 V1.1
     */
    public Object getValue() {
        return m_value;
    }

    /**
     * 设置字段 value 的值，值对象，这个值对象用于综合树节点的显示，相当于综合树节点的userObject，只不过被封装得更深了。
     * @param value 字段 value 的值。
     * @since T3 V1.1
     */
    public void setValue(Object value) {
        m_value = value;
    }

    /**
     * 获取字段 editable 的值，标识这个节点所带的单元格是否允许编辑，注意：这里不是标识树节点是否允许编辑的，树表中的节点是不允许编辑的。
     * @return 字段 editable 的值。
     * @since T3 V1.1
     */
    public boolean isEditable() {
        return m_editable;
    }

    /**
     * 设置字段 editable 的值，标识这个节点所带的单元格是否允许编辑，注意：这里不是标识树节点是否允许编辑的，树表中的节点是不允许编辑的。
     * @param editable 字段 editable 的值。
     * @since T3 V1.1
     */
    public void setEditable(boolean editable) {
        m_editable = editable;
    }

    /**
     * 获取字段 foreground 的值，这个节点所带的单元格的前景色，注意：这里不是指树节点的前景色。
     * @return 字段 foreground 的值。
     * @since T3 V1.1
     */
    public Color getForeground() {
        return m_foreground;
    }

    /**
     * 设置字段 foreground 的值，这个节点所带的单元格的前景色，注意：这里不是指树节点的前景色。
     * @param foreground 字段 foreground 的值。
     * @since T3 V1.1
     */
    public void setForeground(Color foreground) {
        m_foreground = foreground;
    }

    /**
     * 获取字段 background 的值，这个节点所带的单元格的背景色，注意：这里不是指树节点的背景色。
     * @return 字段 background 的值。
     * @since T3 V1.1
     */
    public Color getBackground() {
        return m_background;
    }

    /**
     * 设置字段 background 的值，这个节点所带的单元格的背景色，注意：这里不是指树节点的背景色。
     * @param background 字段 background 的值。
     * @since T3 V1.1
     */
    public void setBackground(Color background) {
        m_background = background;
    }

    /**
     * 获取字段 nodeForeground 的值，树节点前景色。
     * @return 字段 nodeForeground 的值。
     * @since T3 V1.1
     */
    public Color getNodeForeground() {
        return m_NodeForeground;
    }

    /**
     * 设置字段 nodeForeground 的值，树节点前景色。
     * @param nodeForeground 字段 nodeForeground 的值。
     * @since T3 V1.1
     */
    public void setNodeForeground(Color nodeForeground) {
        m_NodeForeground = nodeForeground;
    }

    /**
     * 获取字段 nodeBackground 的值，树节点背景色。
     * @return 字段 nodeBackground 的值。
     * @since T3 V1.1
     */
    public Color getNodeBackground() {
        return m_NodeBackground;
    }

    /**
     * 设置字段 nodeBackground 的值，树节点背景色。
     * @param nodeBackground 字段 nodeBackground 的值。
     * @since T3 V1.1
     */
    public void setNodeBackground(Color nodeBackground) {
        m_NodeBackground = nodeBackground;
    }

    /**
     * 获取字段 cells 的值，单元格列表，注意：这里是模型中节点列表的引用，应用模块有责任维护这个列表的数据一致性和完整性。
     * @return 字段 cells 的值。
     * @since T3 V1.1
     */
    public List getCells() {
        return m_cells;
    }

    /**
     * 设置字段 cells 的值，单元格列表，注意：这里是模型中节点列表的引用，应用模块有责任维护这个列表的数据一致性和完整性。
     * @param cells 字段 cells 的值。
     * @since T3 V1.1
     */
    public void setCells(List cells) {
        m_cells = cells;
    }

    /*------------------------------------- Object Public Method -------------------------------------*/

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return getValue().toString();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        return getValue().equals(((TreeTableObject) obj).getValue());
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
