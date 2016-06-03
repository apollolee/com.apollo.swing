/*
 * 此代码创建于 2008-3-19 上午09:37:35。
 */
package com.apollo.swing.component.complextree;

import java.util.EventObject;
import java.util.List;

import javax.swing.tree.TreePath;

/**
 * <p>文件名称：ComplexTreeModelEvent.java</p>
 * <p>类型描述：综合树模型事件类，封装了综合树产生的事件。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-3-20</p>
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
public class ComplexTreeModelEvent extends EventObject {

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
     * 路径集。
     */
    protected TreePath[] m_paths = null;

    /**
     * 标识是否选择。
     */
    protected volatile boolean m_isSelected = true;

    /**
     * 异常。
     */
    protected ComplexTreeException m_exception = null;

    /**
     * 缓存集。
     */
    protected List m_caches = null;

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /* Add Public Constructor */

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /**
     * 构造方法。
     * @param source 事件源。
     */
    protected ComplexTreeModelEvent(Object source) {
        super(source);
    }

    /**
     * 构造方法。
     * @param source 事件源。
     * @param path 路径，注意：路径里包含的每个值不是节点对象，而是节点对象所带的userObject对象。
     * @param isSelected 标识是否选择。
     */
    protected ComplexTreeModelEvent(Object source, TreePath path, boolean isSelected) {
        this(source, new TreePath[] { path }, isSelected);
    }

    /**
     * 构造方法。
     * @param source 事件源。
     * @param paths 路径集，注意：路径里包含的每个值不是节点对象，而是节点对象所带的userObject对象。
     * @param isSelected 标识是否选择。
     */
    protected ComplexTreeModelEvent(Object source, TreePath[] paths, boolean isSelected) {
        super(source);
        m_paths = paths;
        m_isSelected = isSelected;
    }

    /**
     * 构造方法。
     * @param source 事件源。
     * @param exception 异常。
     */
    protected ComplexTreeModelEvent(Object source, ComplexTreeException exception) {
        super(source);
        m_exception = exception;
    }

    /**
     * 构造方法。
     * @param source 事件源。
     * @param caches 缓存集，即当前处于已载入状态的缓存节点的列表。
     */
    protected ComplexTreeModelEvent(Object source, List caches) {
        super(source);
        m_caches = caches;
    }

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * 获取路径集，注意：路径里包含的每个值不是节点对象，而是节点对象所带的userObject对象。
     * @return 路径集。
     * @since T3 V1.1
     */
    public TreePath[] getPahts() {
        return m_paths;
    }

    /**
     * 判断是否选择。
     * @return 标识是否选择。
     * @since T3 V1.1
     */
    public boolean isSelected() {
        return m_isSelected;
    }

    /**
     * 获取异常。
     * @return 异常。
     * @since T3 V1.1
     */
    public ComplexTreeException getException() {
        return m_exception;
    }

    /**
     * 获取缓存集，即当前处于已载入状态的缓存节点的列表。
     * @return 缓存集。
     * @since T3 V1.1
     */
    public List getCaches() {
        return m_caches;
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
