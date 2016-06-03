/*
 * 此代码创建于 2008-3-19 上午09:35:49。
 */
package com.apollo.swing.component.complextree;

import java.util.EventListener;

/**
 * <p>文件名称：IComplexTreeModelListener.java</p>
 * <p>类型描述：综合树模型监听器接口，监听综合表模型上发生的特殊事件。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-3-19</p>
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
public interface IComplexTreeModelListener extends EventListener {

    /*------------------------------------- Public Static Final Field -------------------------------------*/

    /* Add Public Static Final Field */

    /*------------------------------------- Public Abstract Method -------------------------------------*/

    /**
     * 节点选择已改变，有节点的三态选择状态发生变更后产生的事件。
     * @param evt 综合树模型事件。
     * @since T3 V1.1
     */
    public abstract void nodeChoiceChanged(ComplexTreeModelEvent evt);

    /**
     * 节点即将载入，缓存节点在载入子节点的前一刻产生的事件。
     * @param evt 综合树模型事件。
     * @since T3 V1.1
     */
    public abstract void nodeWillLoad(ComplexTreeModelEvent evt);

    /**
     * 节点已载入，缓存节点在载入子节点后产生的事件。
     * @param evt 综合树模型事件。
     * @since T3 V1.1
     */
    public abstract void nodeLoaded(ComplexTreeModelEvent evt);

    /**
     * 节点载入已失败，缓存节点在载入子节点失败后产生的事件。
     * @param evt 综合树模型事件。
     * @since T3 V1.1
     */
    public abstract void nodeLoadFailed(ComplexTreeModelEvent evt);

    /**
     * 释放缓存，在有释放缓存的动作发生时产生的事件，一般应用不需要监听这个事件的发生。
     * @param evt 综合树模型事件。
     * @since T3 V1.1
     */
    public abstract void releaseCache(ComplexTreeModelEvent evt);

}
