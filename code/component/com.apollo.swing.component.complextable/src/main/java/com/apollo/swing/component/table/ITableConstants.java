/*
 * 此代码创建于 2008-4-25 上午09:24:14。
 */
package com.apollo.swing.component.table;

import javax.swing.Icon;

/**
 * <p>文件名称：ITableConstants.java</p>
 * <p>类型描述：表格常量类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-4-25</p>
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
public interface ITableConstants {

    /*------------------------------------- Public Static Final Field -------------------------------------*/

    /**
     * 缺省的行号列宽度。
     */
    public static final int DEFAULT_ROWIDWIDTH = 50;

    /**
     * 缺省的定制文件名，注意：此文件将放在TTableUtilities.class所在的类路径（Jar包）一级的目录中。
     */
    public static final String DEFAULT_CUSTOMFILENAME = "default-tablecustom.xml";

    /**
     * 客户端属性------列表定制。
     */
    public static final String CP_TABLECUSTOM = "TableCustom";

    /**
     * 客户端属性------缺省列表定制。
     */
    public static final String CP_TableDefaultCustom = "TableDefaultCustom";

    /**
     * 国际化信息------列表定制。
     */
    public static final String I18N_INFO_TABLECUSTOM = TableUtilities.getI18nMessage("tableCustom");

    /**
     * 国际化信息------行号。
     */
    public static final String I18N_INFO_ROWID = TableUtilities.getI18nMessage("rowId");

    /**
     * 国际化信息------行头（固定列集合）。
     */
    public static final String I18N_INFO_ROWHEADER = TableUtilities.getI18nMessage("rowHeader");

    /**
     * 国际化信息------表体（可拖动列集合）。
     */
    public static final String I18N_INFO_BODY = TableUtilities.getI18nMessage("body");

    /**
     * 国际化信息------列名。
     */
    public static final String I18N_INFO_COLUMNNAME = TableUtilities.getI18nMessage("columnName");

    /**
     * 国际化信息------显示。
     */
    public static final String I18N_INFO_VISIBLE = TableUtilities.getI18nMessage("visible");

    /**
     * 国际化信息------列宽。
     */
    public static final String I18N_INFO_WIDTH = TableUtilities.getI18nMessage("width");

    /**
     * 国际化信息------全局列宽。
     */
    public static final String I18N_INFO_ALLWIDTH = TableUtilities.getI18nMessage("allWidth");

    /**
     * 国际化信息------显示列。
     */
    public static final String I18N_INFO_SHOW = TableUtilities.getI18nMessage("show");

    /**
     * 国际化信息------隐藏列。
     */
    public static final String I18N_INFO_HIDE = TableUtilities.getI18nMessage("hide");

    /**
     * 图标------列表定制。
     */
    public static final Icon ICON_TABLECUSTOM = TableUtilities.getIcon("table-custom.gif");

    /*------------------------------------- Public Abstract Method -------------------------------------*/

    /* Add Public Abstract Method */

}
