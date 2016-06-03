/*
 * 此代码创建于 2007-9-12 下午05:36:18
 */
package com.apollo.swing.component.complextree;

import javax.swing.tree.TreePath;

/**
 * <p>文件名称：ComplexTreeChoiceNode.java</p>
 * <p>文件描述：综合树选择节点类，封装了允许三态选择的节点的操作和数据。</p>
 * <p>版权所有：版权所有(C)2001-2004</p>
 * <p>公　　司：菠萝梨</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2007-9-12</p>
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
public class ComplexTreeChoiceNode extends ComplexTreeNode {

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

    /**
     * 选择状态------已选择。
     */
    private static final byte STATE_SELECTED = 0;

    /**
     * 选择状态------未选择。
     */
    private static final byte STATE_DESELECTED = 1;

    /**
     * 选择状态------不确定（半选）。
     */
    private static final byte STATE_INDETERMINATE = 2;

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
     * 选择状态。
     */
    private volatile byte m_state = STATE_DESELECTED;

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 构造方法。
     * @param userObject 用户对象。
     * @since T3 V1.1
     */
    public ComplexTreeChoiceNode(Object userObject) {
        this(userObject, true);
    }

    /**
     * 构造方法。
     * @param userObject 用户对象。
     * @param allowsChildren 标识是否允许该节点拥有子节点。
     * @since T3 V1.1
     */
    public ComplexTreeChoiceNode(Object userObject, boolean allowsChildren) {
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
     * 获取选中用的路径，即从此节点到根节点的路径，注意：路径里包含的每个值不是节点对象，而是节点对象所带的userObject对象。
     * @return 选中用的路径。
     * @since T3 V1.1
     */
    public TreePath getChosenPath() {
        return new TreePath(getUserObjectPath());
    }

    /**
     * 判断是否为已选择状态，即界面上此节点前面的选择框是否处于勾选状态。
     * @return 标识是否为已选择状态。
     * @since T3 V1.1
     */
    public boolean isSelected() {
        return m_state == STATE_SELECTED;
    }

    /**
     * 设置为已选择状态，即界面上此节点前面的选择框是否处于勾选状态。
     * @since T3 V1.1
     */
    public void setSelected() {
        m_state = STATE_SELECTED;
    }

    /**
     * 判断是否为未选择状态，即界面上此节点前面的选择框是否处于未勾选状态。
     * @return 标识是否为未选择状态。
     * @since T3 V1.1
     */
    public boolean isDeselected() {
        return m_state == STATE_DESELECTED;
    }

    /**
     * 设置为未选择状态，即界面上此节点前面的选择框是否处于未勾选状态。
     * @since T3 V1.1
     */
    public void setDeselected() {
        m_state = STATE_DESELECTED;
    }

    /**
     * 判断是否为不确定状态，即界面上此节点前面的选择框是否处于半勾选状态。
     * @return 标识是否为不确定状态。
     * @since T3 V1.1
     */
    public boolean isIndetermined() {
        return m_state == STATE_INDETERMINATE;
    }

    /**
     * 设置为不确定状态，即界面上此节点前面的选择框是否处于半勾选状态。
     * @since T3 V1.1
     */
    public void setIndetermined() {
        m_state = STATE_INDETERMINATE;
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
