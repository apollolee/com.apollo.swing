/*
 * 此代码创建于 2008-5-8 下午03:27:52。
 */
package com.apollo.swing.component.table;

import javax.swing.JPopupMenu;
import javax.swing.JTable;

/**
 * <p>文件名称：ITableMenuManager.java</p>
 * <p>类型描述：表格菜单管理器接口。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-5-8</p>
 * <p>修改记录：</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * @version 1.0
 * @author 李镇
 */
public interface ITableMenuManager {

    /*------------------------------------- Public Static Final Field -------------------------------------*/

    /* Add Public Static Final Field */

    /*------------------------------------- Public Abstract Method -------------------------------------*/

    /**
     * 管理公共菜单。
     * @param table 表格。
     * @param menu 菜单。
     */
    public abstract void manageCommonMenu(JTable table, JPopupMenu menu);

    /**
     * 管理滚动面板菜单。
     * @param table 表格。
     * @param menu 菜单。
     */
    public abstract void manageScrollPaneMenu(JTable table, JPopupMenu menu);

    /**
     * 管理表头菜单。
     * @param table 表格。
     * @param menu 菜单。
     * @param column 列索引。
     */
    public abstract void manageTableHeaderMenu(JTable table, JPopupMenu menu, int column);

    /**
     * 管理表格菜单。
     * @param table 表格。
     * @param menu 菜单。
     * @param row 行索引。
     * @param column 列索引。
     */
    public abstract void manageTableMenu(JTable table, JPopupMenu menu, int row, int column);

}
