/*
 * 此代码创建于 2007-9-13 上午09:27:26
 */
package com.apollo.swing.component.complextree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;

/**
 * <p>文件名称：ComplexTreeNode.java</p>
 * <p>文件描述：综合树节点类，封装了综合树节点的操作和数据。</p>
 * <p>版权所有：版权所有(C)2001-2004</p>
 * <p>公　　司：菠萝梨</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2007-9-13</p>
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
public class ComplexTreeNode extends DefaultMutableTreeNode implements IConstants {

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
     * 标识节点是否可编辑。
     */
    protected volatile boolean m_isEditable = false;

    /**
     * 节点的渲染器。
     */
    protected TreeCellRenderer m_renderer = null;

    /**
     * 节点编辑器，由于点击选择节点复选框的限制导致了shouldSelectCell()方法不会被正常调用到，所以m_editor不必实现此方法。
     */
    protected TreeCellEditor m_editor = null;

    /**
     * 标识节点是否自动（设置）工具提示。
     */
    protected boolean m_isAutoToolTip = false;

    /**
     * 工具提示文本。
     */
    protected String m_toolTipText = null;

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
    public ComplexTreeNode() {
        this(null);
    }

    /**
     * 构造方法。
     * @param userObject 用户对象。
     * @since T3 V1.1
     */
    public ComplexTreeNode(Object userObject) {
        this(userObject, true);
    }

    /**
     * 构造方法。
     * @param userObject 用户对象。
     * @param allowsChildren 标识是否允许该节点拥有子节点。
     * @since T3 V1.1
     */
    public ComplexTreeNode(Object userObject, boolean allowsChildren) {
        super(userObject, allowsChildren);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * 判断节点是否可编辑。
     * @return 标识节点是否可编辑。
     * @since T3 V1.1
     */
    public boolean isEditable() {
        return m_isEditable;
    }

    /**
     * 设置节点是否可编辑。
     * @param isEditable 标识节点是否可编辑。
     * @since T3 V1.1
     */
    public void setEditable(boolean isEditable) {
        m_isEditable = isEditable;
    }

    /**
     * 获取节点渲染器。
     * @return 节点渲染器。
     * @since T3 V1.1
     */
    public TreeCellRenderer getRenderer() {
        return m_renderer;
    }

    /**
     * 设置节点渲染器。
     * @param renderer 节点渲染器。
     * @since T3 V1.1
     */
    public void setRenderer(TreeCellRenderer renderer) {
        m_renderer = renderer;
    }

    /**
     * 获取节点编辑器。
     * @return 节点编辑器。
     * @since T3 V1.1
     */
    public TreeCellEditor getEditor() {
        return m_editor;
    }

    /**
     * 设置节点编辑器。
     * @param editor 节点编辑器。
     * @since T3 V1.1
     */
    public void setEditor(TreeCellEditor editor) {
        m_editor = editor;
    }

    /**
     * 判断节点是否自动（设置）工具提示，即光标移到节点，自动弹出提示信息，信息内容为节点的userObject的toString，对于长节点被滚动面板遮住的情况下很有用。
     * @return 标识节点是否自动（设置）工具提示。
     * @since T3 V1.1
     */
    public boolean isAutoToolTip() {
        return m_isAutoToolTip;
    }

    /**
     * 设置节点是否自动（设置）工具提示，即光标移到节点，自动弹出提示信息，信息内容为节点的userObject的toString，对于长节点被滚动面板遮住的情况下很有用。
     * @param isAutoToolTip 标识节点是否自动（设置）工具提示。
     * @since T3 V1.1
     */
    public void setAutoToolTip(boolean isAutoToolTip) {
        m_isAutoToolTip = isAutoToolTip;
    }

    /**
     * 获取工具提示文本。
     * @return 工具提示文本。
     * @since T3 V1.1
     */
    public String getToolTipText() {
        if (m_toolTipText == null && m_isAutoToolTip) {
            return userObject == null ? null : userObject.toString();

        } else {
            return m_toolTipText;
        }
    }

    /**
     * 设置工具提示文本，如果设置了提示文本，则autoToolTip无效，以设置的提示文本为准。
     * @param toolTipText 工具提示文本。
     * @since T3 V1.1
     */
    public void setToolTipText(String toolTipText) {
        m_toolTipText = toolTipText;
    }

    /*------------------------------------- DefaultMutableTreeNode Public Method -------------------------------------*/

    /**
     * @see javax.swing.tree.DefaultMutableTreeNode#insert(javax.swing.tree.MutableTreeNode, int)
     * @throws IllegalArgumentException 设置的新节点不是一个TComplexTreeNode或设置的新节点是一个TChoiceTreeNode，但父节点（本节点）不是一个TChoiceTreeNode则会抛出此异常。
     */
    public void insert(MutableTreeNode newChild, int childIndex) {
        if (!(newChild instanceof ComplexTreeNode)) {
            throw new IllegalArgumentException("New child is not a TComplexTreeNode !");
        }

        if (newChild instanceof ComplexTreeChoiceNode && !(this instanceof ComplexTreeChoiceNode)) {
            throw new IllegalArgumentException("New child is a TChoiceTreeNode, but parent is not a TChoiceTreeNode !");
        }

        super.insert(newChild, childIndex);
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
