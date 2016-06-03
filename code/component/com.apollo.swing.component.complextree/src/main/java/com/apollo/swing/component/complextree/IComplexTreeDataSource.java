/*
 * 此代码创建于 2007-9-12 下午05:50:27
 */
package com.apollo.swing.component.complextree;

/**
 * <p>文件名称：IComplexTreeDataSource.java</p>
 * <p>类型描述：综合树数据源接口，主要处理缓存节点获取数据的功能。</p>
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
public interface IComplexTreeDataSource {

    /*------------------------------------- Public Static Final Field -------------------------------------*/

    /* Add Public Static Final Field */

    /*------------------------------------- Public Abstract Method -------------------------------------*/

    /**
     * 载入指定父缓存节点的所有子节点，注意：此回调方法会在非事件派发线程中调用，以方便应用直接在这个方法里下发命令。
     * @param parent 指定的父缓存节点，也就是需要载入子节点的节点。
     * @return 指定父缓存节点的所有子节点。
     * @throws ComplexTreeException 如果在载入过程中发生错误则抛出此异常。
     * @since T3 V1.1
     */
    public abstract ComplexTreeNode[] load(ComplexTreeCacheNode parent) throws ComplexTreeException;

}
