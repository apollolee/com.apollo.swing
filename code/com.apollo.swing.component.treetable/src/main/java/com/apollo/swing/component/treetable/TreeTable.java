/*
 * 此代码创建于 2009-9-14 下午02:37:12。
 */
package com.apollo.swing.component.treetable;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.apollo.base.util.IBaseConstants;
import com.apollo.swing.component.common.jtable.TableHeaderController;
import com.apollo.swing.component.complextree.ComplexTree;
import com.apollo.swing.component.complextree.ComplexTreeChoiceNode;
import com.apollo.swing.component.complextree.ComplexTreeModel;
import com.apollo.swing.component.complextree.ComplexTreeNode;

/**
 * <p>文件名称：TreeTable.java</p>
 * <p>类型描述：树表类，注意：树表组件依赖于综合树组件，树表的树就是综合树，因此，这个树表也支持三态选择和缓存。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2009-9-14</p>
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
public class TreeTable extends JTable implements IBaseConstants, TableCellRenderer {

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
     * 标识是否是阅读模式。
     */
    protected volatile boolean m_isReadMode = false;

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 构造方法。
     * @param model 树表模型。
     * @since T3 V1.1
     */
    public TreeTable(TreeTableModel model) {
        super(model);
        setRowHeight(RowHeight_Table_Default);
        new TableHeaderController(getTableHeader()).addDisabledReorderingColumnViewIndex(0);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * 获取字段 isReadMode 的值。
     * @return 字段 isReadMode 的值。
     */
    public boolean isReadMode() {
        return m_isReadMode;
    }

    /**
     * 设置字段 isReadMode 的值。
     * @param isReadMode 字段 isReadMode 的值。
     */
    public void setReadMode(boolean isReadMode) {
        m_isReadMode = isReadMode;
    }

    /**
     * 获取树表模型。
     * @return 树表模型。
     * @since T3 V1.1
     */
    public TreeTableModel getTreeTableModel() {
        return (TreeTableModel) getModel();
    }

    /*------------------------------------- JTable Public Method -------------------------------------*/

    /**
     * @see javax.swing.JTable#setRowHeight(int)
     */
    public void setRowHeight(int rowHeight) {
        super.setRowHeight(rowHeight);
        getTreeTableModel().getTree().setRowHeight(rowHeight);
    }

    /**
     * @see javax.swing.JTable#valueChanged(javax.swing.event.ListSelectionEvent)
     */
    public void valueChanged(ListSelectionEvent evt) {
        super.valueChanged(evt);
        getTreeTableModel().getTree().setSelectionRows(getSelectedRows());
    }

    /**
     * @see javax.swing.JTable#getCellRenderer(int, int)
     */
    public TableCellRenderer getCellRenderer(int row, int column) {
        return this;
    }

    /**
     * @see javax.swing.JTable#getCellEditor(int, int)
     */
    public TableCellEditor getCellEditor(int row, int column) {
        TableCellEditor editor = null;

        TreeTableObject obj = (TreeTableObject) getTreeTableModel().getNode(row).getUserObject();
        List cells = obj.getCells();
        int index = convertColumnIndexToModel(column) - 1;

        if (cells != null && index < cells.size()) {
            editor = ((TreeTableCell) cells.get(index)).getEditor();
        }

        if (editor == null) {
            editor = super.getCellEditor(row, column);
        }

        return editor;
    }

    /*------------------------------------- TableCellRenderer Public Method -------------------------------------*/

    /**
     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component rc = getFinalCellRenderer(row, column).getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Color foreground = null;
        Color background = null;

        TreeTableModel model = getTreeTableModel();
        if (!isSelected) {
            if (model.isLoading(row)) {
                if (column > 0) {
                    rc.setBackground(null);
                }

            } else {
                TreeTableObject obj = (TreeTableObject) model.getNode(row).getUserObject();
                TreeTableCell cell = null;
                if (column > 0) {
                    List cells = obj.getCells();
                    int index = convertColumnIndexToModel(column) - 1;
                    if (cells != null && index < cells.size()) {
                        cell = (TreeTableCell) cells.get(index);
                    }
                }

                if (cell != null && cell.getRenderer() != null) {
                    return rc;
                }

                foreground = obj.getForeground();
                if (foreground == null) {
                    if (column == 0) {
                        foreground = obj.getNodeForeground();

                    } else if (cell != null) {
                        foreground = cell.getForeground();
                    }
                }

                if (m_isReadMode) {
                    rc.setBackground(row % 2 == 1 ? Color_Bg_Row_Odd : Color_Bg_Row_Even);

                } else {
                    background = obj.getBackground();
                    if (background == null) {
                        if (column == 0) {
                            background = obj.getNodeBackground();

                        } else if (cell != null) {
                            background = cell.getBackground();
                        }
                    }

                    rc.setForeground(foreground);
                    if (column == 0) {
                        boolean isNodeEditable = model.getTree().getComplexTreeModel().isEnabledChoice() && model.getNode(row) instanceof ComplexTreeChoiceNode;
                        rc.setBackground(background == null && !isNodeEditable ? Color_Bg_Editable_False : background);

                    } else {
                        rc.setBackground(background == null && !table.isCellEditable(row, column) ? Color_Bg_Editable_False : background);
                    }
                }
            }
        }

        return rc;
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Component Protected Method -------------------------------------*/

    /**
     * @see java.awt.Component#processMouseEvent(java.awt.event.MouseEvent)
     */
    protected void processMouseEvent(MouseEvent evt) {
        if (evt.getID() == MouseEvent.MOUSE_CLICKED && evt.getButton() == MouseEvent.BUTTON1) {
            Point point = evt.getPoint();
            int row = rowAtPoint(point);
            int column = columnAtPoint(point);

            if (row != -1 && column == 0) {
                TreeTableModel model = getTreeTableModel();
                ComplexTreeNode node = model.getNode(row);
                ComplexTree tree = model.getTree();
                ComplexTreeModel treeModel = tree.getComplexTreeModel();

                if (treeModel.isEnabledChoice() && node instanceof ComplexTreeChoiceNode && getChoiceBounds(row).contains(point)) {
                    treeModel.doClick((ComplexTreeChoiceNode) node);
                    repaint();

                } else if (!node.isLeaf() && (getToggleBounds(row).contains(point) || evt.getClickCount() == 2)) {
                    if (tree.isExpanded(row)) {
                        tree.collapseRow(row);

                    } else {
                        tree.expandRow(row);
                    }
                }
            }
        }

        super.processMouseEvent(evt);
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /**
     * 获取最终的渲染器。
     * @param row 行。
     * @param column 列。
     * @return 渲染器。
     */
    private TableCellRenderer getFinalCellRenderer(int row, int column) {
        TreeTableModel model = getTreeTableModel();

        if (column == 0) {
            return (TableCellRenderer) model.getTree();

        } else {
            TableCellRenderer renderer = null;

            if (model.isLoading(row)) {
                return getDefaultRenderer(String.class);
            }

            TreeTableObject obj = (TreeTableObject) model.getNode(row).getUserObject();
            List cells = obj.getCells();
            int index = convertColumnIndexToModel(column) - 1;

            if (cells != null && index < cells.size()) {
                renderer = ((TreeTableCell) cells.get(index)).getRenderer();
            }

            if (renderer == null) {
                renderer = super.getCellRenderer(row, column);
            }

            return renderer;
        }
    }

    /**
     * 获取选中的矩形。
     * @param row 行。
     * @return 选中的矩形。
     */
    private Rectangle getChoiceBounds(int row) {
        Rectangle tableCellRect = getCellRect(row, 0, true);
        Rectangle treeCellRect = getTreeTableModel().getTree().getRowBounds(row);
        Icon icon = UIManager.getIcon("CheckBox.icon");

        int x = treeCellRect.x + 5;
        int y = tableCellRect.y + tableCellRect.height / 2 - icon.getIconHeight() / 2;
        int width = icon.getIconWidth() + 1;
        int height = icon.getIconHeight() + 1;

        return new Rectangle(x, y, width, height);
    }

    /**
     * 获取把手的矩形。
     * @param row 行。
     * @return 把手的矩形。
     */
    private Rectangle getToggleBounds(int row) {
        Rectangle tableCellRect = getCellRect(row, 0, true);
        Rectangle treeCellRect = getTreeTableModel().getTree().getRowBounds(row);
        Icon icon = UIManager.getIcon("Tree.expandedIcon");

        int x = treeCellRect.x - 20;
        int y = tableCellRect.y + tableCellRect.height / 2 - icon.getIconHeight() / 2;
        int width = icon.getIconWidth() + 1;
        int height = icon.getIconHeight() + 1;

        return new Rectangle(x, y, width, height);
    }

}
