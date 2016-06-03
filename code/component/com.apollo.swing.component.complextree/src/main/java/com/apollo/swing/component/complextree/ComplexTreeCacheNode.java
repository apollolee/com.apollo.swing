/*
 * 此代码创建于 2007-9-12 下午05:36:44
 */
package com.apollo.swing.component.complextree;

import javax.swing.Icon;

import com.apollo.swing.component.common.jtree.SingleIconTreeCellRenderer;

/**
 * <p>文件名称：ComplexTreeCacheNode.java</p>
 * <p>文件描述：综合树缓存节点类，封装了允许缓存的节点的操作和数据。</p>
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
public class ComplexTreeCacheNode extends ComplexTreeChoiceNode {

    /*------------------------------------- Public (Static) Inner Class -------------------------------------*/

    /* Add Public (Static) Inner Class */

    /*------------------------------------- Protected (Static) Inner Class -------------------------------------*/

    /* Add Protected (Static) Inner Class */

    /*------------------------------------- Friendly (Static) Inner Class -------------------------------------*/

    /* Add Friendly (Static) Inner Class */

    /*------------------------------------- Private (Static) Inner Class -------------------------------------*/

    /* Add Private (Static) Inner Class */

    /*------------------------------------- Public Static Field -------------------------------------*/

    /**
     * 国际化信息------载入中。
     * @since T3 V1.1
     */
    public static final String I18N_INFO_LOADING = Utilities.getI18nMessage("loading");

    /**
     * 图标------载入中。
     * @since T3 V1.1
     */
    public static final Icon ICON_LOADING = Utilities.getIcon("loading.gif");

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
     * 载入中节点。
     */
    protected ComplexTreeNode m_loadingNode = new ComplexTreeNode(I18N_INFO_LOADING);

    /**
     * 状态。
     */
    protected volatile int m_state = STATE_READY;

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 构造方法。
     * @param userObject 用户对象。
     * @since T3 V1.1
     */
    public ComplexTreeCacheNode(Object userObject) {
        this(userObject, true);
    }

    /**
     * 构造方法。
     * @param userObject 用户对象。
     * @param allowsChildren 标识是否允许该节点拥有子节点。
     * @since T3 V1.1
     */
    public ComplexTreeCacheNode(Object userObject, boolean allowsChildren) {
        super(userObject, allowsChildren);

        m_loadingNode.setRenderer(new SingleIconTreeCellRenderer(ICON_LOADING));
        add(m_loadingNode);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * 判断节点是否处于准备状态，准备状态即缓存节点下还没有载入子节点的状态。
     * @return 标识节点是否处于准备状态。
     * @since T3 V1.1
     */
    public synchronized boolean isReady() {
        return m_state == STATE_READY;
    }

    /**
     * 判断节点是否处于载入中状态，载入中状态即缓存节点正在载入子节点的状态。
     * @return 标识节点是否处于载入中状态。
     * @since T3 V1.1
     */
    public synchronized boolean isLoading() {
        return m_state == STATE_LOADING;
    }

    /**
     * 判断节点是否处于已载入状态，已载入状态即缓存节点已经载入了子节点的状态。
     * @return 标识节点是否处于已载入状态。
     * @since T3 V1.1
     */
    public synchronized boolean isLoaded() {
        return m_state == STATE_LOADED;
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * 判断是否已经拥有了载入中节点。
     * @return 标识是否已经拥有了载入中节点。
     */
    protected boolean hasLoadingNode() {
        return getChildCount() == 1 ? (getChildAt(0) == m_loadingNode) : false;
    }

    /**
     * 设置载入中节点。
     * @return 标识是否成功设置了载入中节点。
     */
    protected boolean setLoadingNode() {
        if (hasLoadingNode()) {
            return false;

        } else {
            removeAllChildren();
            add(m_loadingNode);
            return true;
        }
    }

    /**
     * 进入准备状态。
     * @return 标识是否成功设置了载入中节点。
     */
    protected synchronized boolean ready() {
        m_state = STATE_READY;
        return setLoadingNode();
    }

    /**
     * 进入载入中状态。
     * @return 标识是否成功设置了载入中节点。
     */
    protected synchronized boolean loading() {
        m_state = STATE_LOADING;
        return setLoadingNode();
    }

    /**
     * 进入已载入状态。
     * @param loadedNodes 已载入的节点集。
     */
    protected synchronized void loaded(ComplexTreeNode[] loadedNodes) {
        removeAllChildren();

        if (loadedNodes != null) {
            for (int i = 0; i < loadedNodes.length; i++) {
                add(loadedNodes[i]);
            }
        }

        m_state = STATE_LOADED;
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
