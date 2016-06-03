/*
 * 此代码创建于 2008-5-11 下午01:59:06。
 */
package com.apollo.swing.component.complextable;

/**
 * <p>文件名称：IComplexTableManager.java</p>
 * <p>类型描述：综合表管理器接口，主要用于一些特殊的综合扩展功能，后续由可能进一步扩充接口。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-5-11</p>
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
public interface IComplexTableManager {

    /*------------------------------------- Public Static Final Field -------------------------------------*/

    /* Add Public Static Final Field */

    /*------------------------------------- Public Abstract Method -------------------------------------*/

    /**
     * <p>综合表菜单上如果允许插入新行，则新行的行数据初始化可通过这个方法回调生成出来。</p>
     * <p>注意：如果不实现这个方法而又允许插入新行，则缺省的每行数据都是空字符串类型，这样是有可能发生模型层value值转型异常的。</p>
     * @param table 需要增加行的综合表对象。
     * @param index 新的行所在的索引。
     * @return 行数据对象。
     * @since T3 V1.1
     */
    public abstract ComplexTableRow createRow(ComplexTable table, int index);

}
