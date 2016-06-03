/*
 * 此代码创建于 2008-5-5 下午04:13:40。
 */
package com.apollo.swing.component.table;

import java.math.BigInteger;

import javax.swing.table.TableColumn;

/**
 * <p>文件名称：TableCustom.java</p>
 * <p>类型描述：列表定制信息类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-5-5</p>
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
public class TableCustom {

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
     * 列对象。
     */
    protected TableColumn m_column;

    /**
     * 标识列是否可见。
     */
    protected Boolean m_visible;

    /**
     * 列宽。
     */
    protected BigInteger m_width;

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 构造方法。
     * @param column 列对象。
     * @param visible 标识列是否可见。
     */
    public TableCustom(TableColumn column, boolean visible) {
        this(column, new Boolean(visible));
    }

    /**
     * 构造方法。
     * @param column 列对象。
     * @param visible 标识列是否可见。
     */
    public TableCustom(TableColumn column, Boolean visible) {
        m_column = column;
        m_visible = visible;

        syncWidthFromColumn();
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    protected TableCustom(TableCustom custom) {
        m_column = custom.m_column;
        m_visible = Boolean.valueOf(custom.m_visible.booleanValue());
        m_width = BigInteger.valueOf(custom.m_width.longValue());
    }

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * 获取字段 column 的值。
     * @return 字段 column 的值。
     */
    public TableColumn getColumn() {
        return m_column;
    }

    /**
     * 获取字段 visible 的值。
     * @return 字段 visible 的值。
     */
    public boolean isVisible() {
        return m_visible.booleanValue();
    }

    public void setVisible(Boolean visible) {
        m_visible = visible;
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * 从列对象同步列宽。
     */
    protected void syncWidthFromColumn() {
        m_width = BigInteger.valueOf(m_column.getWidth());
    }

    /**
     * 同步列宽到列对象。
     */
    protected void syncWidthToColumn() {
        m_column.setPreferredWidth(m_width.intValue());
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
