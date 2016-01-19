/*
 * 此代码创建于 2008-5-4 上午11:31:21。
 */
package com.apollo.swing.component.table;

import java.beans.PropertyChangeEvent;

import javax.swing.JTable;

/**
 * <p>文件名称：TableBody.java</p>
 * <p>类型描述：表体类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-5-4</p>
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
@SuppressWarnings("all")
public class TableBody extends TableView {

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

    /* Add Protected Field */

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 构造方法。
     * @param source 视图源。
     */
    public TableBody(JTable source) {
        this(source, null, true);
    }

    /**
     * 构造方法。
     * @param source 视图源。
     * @param menuManager 菜单管理器。
     * @param isAutoEnabledRenderer 标识是否自动管理渲染器组件激活与否。
     */
    public TableBody(JTable source, ITableMenuManager menuManager, boolean isAutoEnabledRenderer) {
        super(source, new ColumnView(source.getColumnModel()), menuManager, isAutoEnabledRenderer);

        setAutoResizeMode(m_source.getAutoResizeMode());
        updateViewportSize();
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /* Add Public Method */

    /*------------------------------------- JTable Public Method -------------------------------------*/

    /**
     * @see javax.swing.JTable#addNotify()
     */
    public void addNotify() {
        super.addNotify();
        getParent().getParent().addMouseListener(this);
    }

    /**
     * @see javax.swing.JTable#removeNotify()
     */
    public void removeNotify() {
        getParent().getParent().removeMouseListener(this);
        super.removeNotify();
    }

    /*------------------------------------- TTableView Public Method -------------------------------------*/

    /**
     * @see com.apollo.swing.component.table.TableView#propertyChange(java.beans.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent evt) {
        if ("autoResizeMode".equals(evt.getPropertyName())) {
            setAutoResizeMode(((Integer) evt.getNewValue()).intValue());
        }

        super.propertyChange(evt);
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
