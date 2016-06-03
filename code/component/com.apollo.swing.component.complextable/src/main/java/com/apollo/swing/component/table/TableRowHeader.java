/*
 * 此代码创建于 2008-4-26 下午12:30:27。
 */
package com.apollo.swing.component.table;

import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableCellRenderer;

import com.apollo.base.util.BaseUtilities;

/**
 * <p>文件名称：TableRowHeader.java</p>
 * <p>类型描述：表格行头类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-4-26</p>
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
public class TableRowHeader extends TableView implements ComponentListener {

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
     * 表体。
     */
    protected TableBody m_body;

    /**
     * 列表定制关键字。
     */
    protected String m_customKey;

    /**
     * 列表定制对话框。
     */
    protected TableCustomDialog m_customDialog;

    /**
     * 列表定制动作。
     */
    public Action m_custom = new AbstractAction() {

        {
            BaseUtilities.setAction(this, I18N_INFO_TABLECUSTOM, 'C', ICON_TABLECUSTOM);
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            if (m_customDialog == null) {
                m_customDialog = TableCustomDialog.create(m_body, TableRowHeader.this, m_body, m_customKey);
            }

            m_customDialog.show();
        }

    };

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
     * @param body 表体。
     * @param customKey 列表定制关键字。
     */
    public TableRowHeader(JTable source, TableBody body, String customKey) {
        this(source, body, true, customKey);
    }

    /**
     * 构造方法。
     * @param source 视图源。
     * @param body 表体。
     * @param isRowIdVisible 标识是否显示行号。
     * @param customKey 列表定制关键字。
     */
    public TableRowHeader(JTable source, TableBody body, boolean isRowIdVisible, String customKey) {
        this(source, body, isRowIdVisible, null, customKey);
    }

    /**
     * 构造方法。
     * @param source 视图源。
     * @param body 表体。
     * @param isRowIdVisible 标识是否显示行号。
     * @param rowIdRenderer 行号渲染器。
     * @param customKey 列表定制关键字。
     */
    public TableRowHeader(JTable source, TableBody body, boolean isRowIdVisible, TableCellRenderer rowIdRenderer, String customKey) {
        this(source, body, isRowIdVisible, rowIdRenderer, null, true, customKey);
    }

    /**
     * 构造方法。
     * @param source 视图源。
     * @param body 表体。
     * @param isRowIdVisible 标识是否显示行号。
     * @param rowIdRenderer 行号渲染器。
     * @param menuManager 菜单管理器。
     * @param isAutoEnabledRenderer 标识是否自动管理渲染器组件激活与否。
     * @param customKey 列表定制关键字。
     */
    public TableRowHeader(JTable source,
                          TableBody body,
                          boolean isRowIdVisible,
                          TableCellRenderer rowIdRenderer,
                          ITableMenuManager menuManager,
                          boolean isAutoEnabledRenderer,
                          String customKey) {
        super(source, new ColumnView(source.getColumnModel(), isRowIdVisible, rowIdRenderer), menuManager, isAutoEnabledRenderer);

        m_body = body;
        m_customKey = customKey;

        //m_defaultActions.add(m_custom);
        //m_body.m_defaultActions.add(m_custom);

        columnModel.getSelectionModel().addListSelectionListener(this);
        m_body.getColumnModel().getSelectionModel().addListSelectionListener(this);

        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(false);
        updateViewportSize();
        addComponentListener(this);

        TableUtilities.disableColumnLeftMove(m_body, 0);
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
     * @see javax.swing.JTable#tableChanged(javax.swing.event.TableModelEvent)
     */
    public void tableChanged(TableModelEvent evt) {
        repaint();
        super.tableChanged(evt);
    }

    /**
     * @see javax.swing.JTable#valueChanged(javax.swing.event.ListSelectionEvent)
     */
    public void valueChanged(ListSelectionEvent evt) {
        Object source = evt.getSource();
        ListSelectionModel cmSm = columnModel.getSelectionModel();
        ListSelectionModel bodyCmSm = m_body.getColumnModel().getSelectionModel();

        if (source == cmSm) {
            if (!cmSm.isSelectionEmpty()) {
                bodyCmSm.clearSelection();
            }
            return;
        }

        if (source == bodyCmSm) {
            if (!bodyCmSm.isSelectionEmpty()) {
                cmSm.clearSelection();
            }
            return;
        }

        //以前的老代码，看样子应该是处理了列选择的模式，但综合表目前仅支持行选择，列选择太麻烦了，故先注释掉，后续改成代码做参考。
        /*Object source = evt.getSource();
        boolean isCellSelectionEnabled = getCellSelectionEnabled();
        ListSelectionModel cmSm = columnModel.getSelectionModel();
        ListSelectionModel bodyCmSm = m_body.getColumnModel().getSelectionModel();

        if (source == cmSm) {
            if (isCellSelectionEnabled && !cmSm.isSelectionEmpty()) {
                if (isSelectedRowId()) {
                    cmSm.removeListSelectionListener(this);
                    bodyCmSm.removeListSelectionListener(this);

                    TTableUtilities.selectAllColumn(this);
                    TTableUtilities.selectAllColumn(m_body);

                    cmSm.addListSelectionListener(this);
                    bodyCmSm.addListSelectionListener(this);

                } else {
                    bodyCmSm.clearSelection();
                }
            }

            return;
        }

        if (source == bodyCmSm) {
            if (isCellSelectionEnabled && !bodyCmSm.isSelectionEmpty()) {
                cmSm.clearSelection();
            }

            return;
        }*/

        super.valueChanged(evt);
    }

    /*------------------------------------- ComponentListener Public Method -------------------------------------*/

    /**
     * @see java.awt.event.ComponentListener#componentMoved(java.awt.event.ComponentEvent)
     */
    public void componentMoved(ComponentEvent evt) {
        Container parent = m_body.getParent();

        if (parent instanceof JViewport) { //目标表是放在滚动窗格里
            JViewport viewport = (JViewport) parent;

            int offsetY = getVisibleRect().y;
            Point viewPosition = viewport.getViewPosition();

            if (viewPosition.y != offsetY) { //目标表位置还未移动过
                viewPosition.y = offsetY;
                viewport.setViewPosition(viewPosition);
            }
        }
    }

    /**
     * @see java.awt.event.ComponentListener#componentResized(java.awt.event.ComponentEvent)
     */
    public void componentResized(ComponentEvent evt) {
        /* 暂不必实现 */
    }

    /**
     * @see java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent)
     */
    public void componentShown(ComponentEvent evt) {
        /* 暂不必实现 */
    }

    /**
     * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.ComponentEvent)
     */
    public void componentHidden(ComponentEvent evt) {
        /* 暂不必实现 */
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * 判断行号列是否选中了。
     * @return 为true表示行号列是选中的，为false表示行号列不是选中的。
     */
    protected boolean isSelectedRowId() {
        ColumnView cv = getColumnView();

        if (!cv.m_isRowIdVisible) {
            return false;
        }

        int[] columns = cv.getSelectedColumns();

        for (int i = 0; i < columns.length; i++) {
            if (cv.isRowIdColumn(columns[i])) {
                return true;
            }
        }

        return false;
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
