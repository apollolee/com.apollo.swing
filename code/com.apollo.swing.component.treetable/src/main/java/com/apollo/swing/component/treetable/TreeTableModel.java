/*
 * 此代码创建于 2009-9-14 下午02:36:20。
 */
package com.apollo.swing.component.treetable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

import com.apollo.base.util.IBaseConstants;
import com.apollo.swing.component.complextree.ComplexTree;
import com.apollo.swing.component.complextree.ComplexTreeCacheNode;
import com.apollo.swing.component.complextree.ComplexTreeChoiceNode;
import com.apollo.swing.component.complextree.ComplexTreeModel;
import com.apollo.swing.component.complextree.ComplexTreeNode;

/**
 * <p>文件名称：TreeTableModel.java</p>
 * <p>类型描述：树表模型类，树表的核心逻辑大都在这里实现的。</p>
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
public class TreeTableModel extends AbstractTableModel implements TreeModelListener, TreeExpansionListener {

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

    /**
     * 获取树表对象。
     * @param node 节点。
     * @return 树表对象。
     */
    private static TreeTableObject getObject(ComplexTreeNode node) {
        return (TreeTableObject) node.getUserObject();
    }

    /**
     * 获取节点的下一个兄弟节点。
     * @param node 节点。
     * @return 节点的下一个兄弟节点。
     */
    private static DefaultMutableTreeNode getNextSibling(DefaultMutableTreeNode node) {
        DefaultMutableTreeNode nextNode = node.getNextSibling();
        return nextNode == null && !node.isRoot() ? getNextSibling((DefaultMutableTreeNode) node.getParent()) : nextNode;
    }

    /*------------------------------------- Public Field -------------------------------------*/

    /* Add Public Field */

    /*------------------------------------- Protected Field -------------------------------------*/

    /* Add Protected Field */

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /**
     * 综合树。
     */
    private ComplexTree m_tree;

    /**
     * 节点集。
     */
    private List m_nodes = new ArrayList();

    /**
     * 列名集。
     */
    private List m_columnNames = new ArrayList();

    /**
     * 列类型集。
     */
    private Map m_columnClasses = new HashMap();

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 构造方法。
     * @param model 综合树模型。
     * @param columnNames 列名集。
     * @since T3 V1.1
     */
    public TreeTableModel(ComplexTreeModel model, String[] columnNames) {
        m_tree = new TreeWrapper(model, this);
        m_columnNames.addAll(Arrays.asList(columnNames));
        refresh();
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * 获取综合树，树表的数据结构其实是一个综合树模型，只是在每个节点的userObject上再定义了一层带单元格列表的结构。
     * @return 综合树。
     * @since T3 V1.1
     */
    public ComplexTree getTree() {
        return m_tree;
    }

    /**
     * 获取节点，得到节点就能得到节点对于的相关数据，比如节点带的单元格数据等。
     * @param rowIndex 行索引。
     * @return 节点。
     * @since T3 V1.1
     */
    public ComplexTreeNode getNode(int rowIndex) {
        return (ComplexTreeNode) m_nodes.get(rowIndex);
    }

    /**
     * 刷新，把树模型数据同步到表模型数据中，这个方法非常重要，因为树表的正确显示依赖与树模型和表模型数据的同步，而很多数据的改动是在树模型上进行的，对树模型有节点增删时，最好要调用一下这个方法。
     * @since T3 V1.1
     */
    public void refresh() {
        m_nodes.clear();
        m_nodes.addAll(getVisibleNodes((ComplexTreeNode) m_tree.getModel().getRoot()));
        fireTableDataChanged();
    }

    /**
     * 设置列类型，这个功能和表模型的对于功能一样，可参考JDK文档。
     * @param columnIndex 列索引。
     * @param cls 列类型。
     * @since T3 V1.1
     */
    public void setColumnClass(int columnIndex, Class cls) {
        m_columnClasses.put(new Integer(columnIndex), cls);
    }

    /*------------------------------------- TableModel Public Method -------------------------------------*/

    /**
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    public int getColumnCount() {
        return m_columnNames.size();
    }

    /**
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public int getRowCount() {
        return m_nodes.size();
    }

    /**
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (isLoading(rowIndex)) {
            return ComplexTreeCacheNode.I18N_INFO_LOADING;
        }

        TreeTableObject object = getObject((ComplexTreeNode) m_nodes.get(rowIndex));

        if (columnIndex == 0) {
            return object;

        } else {
            List cells = object.getCells();
            int cellIndex = columnIndex - 1;

            if (cells != null && cellIndex < cells.size()) {
                return ((TreeTableCell) cells.get(cellIndex)).getValue();

            } else {
                return "";
            }
        }
    }

    /*------------------------------------- AbstractTableModel Public Method -------------------------------------*/

    /**
     * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
     */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0 || isLoading(rowIndex)) {
            return false;

        } else {
            TreeTableObject object = getObject((ComplexTreeNode) m_nodes.get(rowIndex));
            List cells = object.getCells();
            int cellIndex = columnIndex - 1;

            if (object.isEditable() && cells != null && cellIndex < cells.size()) {
                return ((TreeTableCell) cells.get(cellIndex)).isEditable();

            } else {
                return false;
            }
        }
    }

    /**
     * @see javax.swing.table.AbstractTableModel#getColumnName(int)
     */
    public String getColumnName(int column) {
        return (String) m_columnNames.get(column);
    }

    /**
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    public Class getColumnClass(int columnIndex) {
        Class columnClass = (Class) m_columnClasses.get(new Integer(columnIndex));
        return columnClass == null ? super.getColumnClass(columnIndex) : columnClass;
    }

    /**
     * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
     */
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        ((TreeTableCell) getObject((ComplexTreeNode) m_nodes.get(rowIndex)).getCells().get(columnIndex - 1)).setValue(value);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    /*------------------------------------- TreeModelListener Public Method -------------------------------------*/

    /**
     * @see javax.swing.event.TreeModelListener#treeNodesChanged(javax.swing.event.TreeModelEvent)
     */
    public void treeNodesChanged(TreeModelEvent evt) {
        refresh();
    }

    /**
     * @see javax.swing.event.TreeModelListener#treeNodesInserted(javax.swing.event.TreeModelEvent)
     */
    public void treeNodesInserted(TreeModelEvent evt) {
        refresh();
    }

    /**
     * @see javax.swing.event.TreeModelListener#treeNodesRemoved(javax.swing.event.TreeModelEvent)
     */
    public void treeNodesRemoved(TreeModelEvent evt) {
        refresh();
    }

    /**
     * @see javax.swing.event.TreeModelListener#treeStructureChanged(javax.swing.event.TreeModelEvent)
     */
    public void treeStructureChanged(TreeModelEvent evt) {
        refresh();
    }

    /*------------------------------------- TreeExpansionListener Public Method -------------------------------------*/

    /**
     * @see javax.swing.event.TreeExpansionListener#treeCollapsed(javax.swing.event.TreeExpansionEvent)
     */
    public void treeCollapsed(TreeExpansionEvent evt) {
        ComplexTreeNode node = (ComplexTreeNode) evt.getPath().getLastPathComponent();
        ComplexTreeNode nextNode = (ComplexTreeNode) getNextSibling(node);

        int firstRow = m_nodes.indexOf(node) + 1;
        int lastRow = nextNode == null ? m_nodes.size() - 1 : m_nodes.indexOf(nextNode) - 1;

        for (int i = lastRow; i >= firstRow; i--) {
            m_nodes.remove(i);
        }

        fireTableRowsDeleted(firstRow, lastRow);
    }

    /**
     * @see javax.swing.event.TreeExpansionListener#treeExpanded(javax.swing.event.TreeExpansionEvent)
     */
    public void treeExpanded(TreeExpansionEvent evt) {
        ComplexTreeNode node = (ComplexTreeNode) evt.getPath().getLastPathComponent();

        List nodes = getVisibleNodes(node);
        nodes.remove(node);

        int firstRow = m_nodes.indexOf(node) + 1;
        int lastRow = firstRow + nodes.size() - 1;

        m_nodes.addAll(firstRow, nodes);

        fireTableRowsInserted(firstRow, lastRow);
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /**
     * 判断是否是载入中。
     * @param rowIndex 行索引。
     * @return 是否是载入中。
     */
    boolean isLoading(int rowIndex) {
        return ComplexTreeCacheNode.I18N_INFO_LOADING.equals(((ComplexTreeNode) m_nodes.get(rowIndex)).getUserObject());
    }

    /*------------------------------------- Private Method -------------------------------------*/

    /**
     * 获取可见的节点集。
     * @param node 节点。
     * @return 可见的节点集。
     */
    private List getVisibleNodes(ComplexTreeNode node) {
        List nodes = new ArrayList();

        if (!node.isRoot() || m_tree.isRootVisible()) {
            nodes.add(node);
        }

        if (m_tree.isExpanded(new TreePath(node.getPath()))) {
            for (int i = 0, size = node.getChildCount(); i < size; i++) {
                nodes.addAll(getVisibleNodes((ComplexTreeNode) node.getChildAt(i)));
            }
        }

        return nodes;
    }

}

/**
 * <p>文件名称：TreeTableModel.java</p>
 * <p>类型描述：综合树包装类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2009-9-19</p>
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
class TreeWrapper extends ComplexTree implements TableCellRenderer {

    /*------------------------------------- Public (Static) Inner Class -------------------------------------*/

    /* Add Public (Static) Inner Class */

    /*------------------------------------- Protected (Static) Inner Class -------------------------------------*/

    /* Add Protected (Static) Inner Class */

    /*------------------------------------- Friendly (Static) Inner Class -------------------------------------*/

    /* Add Friendly (Static) Inner Class */

    /*------------------------------------- Private (Static) Inner Class -------------------------------------*/

    /**
     * 树渲染器。
     */
    private class TTreeCellRenderer implements IBaseConstants, TreeCellRenderer {

        /**
         * @see javax.swing.tree.TreeCellRenderer#getTreeCellRendererComponent(javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int, boolean)
         */
        public Component getTreeCellRendererComponent(JTree tree,
                                                      Object value,
                                                      boolean selected,
                                                      boolean expanded,
                                                      boolean leaf,
                                                      int row,
                                                      boolean hasFocus) {
            Component rc = m_treeRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

            Object obj = ((ComplexTreeNode) value).getUserObject();
            if (obj instanceof TreeTableObject) {
                TreeTableObject tto = (TreeTableObject) obj;

                Color foreground = tto.getForeground();
                if (foreground == null) {
                    foreground = tto.getNodeForeground();
                }

                Color background;
                if (m_table != null && ((TreeTable) m_table).m_isReadMode) {
                    background = row % 2 == 1 ? Color_Bg_Row_Odd : Color_Bg_Row_Even;

                } else {
                    background = tto.getBackground();

                    if (background == null) {
                        background = tto.getNodeBackground();
                    }

                    if (background == null) {
                        boolean isNodeEditable = getComplexTreeModel().isEnabledChoice() && value instanceof ComplexTreeChoiceNode;
                        if (!isNodeEditable) {
                            background = Color_Bg_Editable_False;
                        }
                    }
                }

                foreground = selected ? m_table.getSelectionForeground() : (foreground == null ? tree.getForeground() : foreground);
                background = selected ? m_table.getSelectionBackground() : (background == null ? tree.getBackground() : background);

                JComponent rjc = (JComponent) rc;
                rjc.setForeground(foreground);
                rjc.setBackground(background);

                for (int i = 0, size = rjc.getComponentCount(); i < size; i++) {
                    JComponent c = (JComponent) rjc.getComponent(i);
                    c.setOpaque(true);
                    c.setForeground(foreground);
                    c.setBackground(background);
                }
            }

            return rc;
        }

    }

    /*------------------------------------- Public Static Field -------------------------------------*/

    /* Add Public Static Field */

    /*------------------------------------- Protected Static Field -------------------------------------*/

    /* Add Protected Static Field */

    /*------------------------------------- Friendly Static Field -------------------------------------*/

    /* Add Friendly Static Field */

    /*------------------------------------- Private Static Field -------------------------------------*/

    /**
     * 没焦点的边框。
     */
    private static Border NO_FOCUS_BORDER = new EmptyBorder(0, 0, 0, 0);

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

    /**
     * 表。
     */
    private JTable m_table;

    /**
     * 树渲染器。
     */
    private TreeCellRenderer m_treeRenderer;

    /**
     * 行。
     */
    private int m_row;

    /**
     * 视图。
     */
    private JPanel m_view = new JPanel(new BorderLayout());

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 构造方法。
     * @param treeModel 综合树模型。
     * @param tableModel 树表模型。
     */
    public TreeWrapper(ComplexTreeModel treeModel, TreeTableModel tableModel) {
        super(treeModel);

        addTreeExpansionListener(tableModel);
        treeModel.addTreeModelListener(tableModel);

        updateUI(); //重置监听器位置，保证TTreeTableModel回调方法会最后被调用
        setModel(treeModel); //重置监听器位置，保证TTreeTableModel回调方法会最后被调用

        m_treeRenderer = getCellRenderer();
        setCellRenderer(new TTreeCellRenderer());

        setOpaque(false);
        m_view.add(this, BorderLayout.CENTER);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /* Add Public Method */

    /*------------------------------------- JComponent Public Method -------------------------------------*/

    /**
     * @see javax.swing.JComponent#scrollRectToVisible(java.awt.Rectangle)
     */
    public void scrollRectToVisible(Rectangle rect) {
        m_table.scrollRectToVisible(rect);
    }

    /**
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    public void paint(Graphics g) {
        Rectangle rowBounds = getRowBounds(m_row);

        if (rowBounds == null) {
            throw new NullPointerException("UI = " + ui + " , CurrentRowIndex = " + m_row + " , TreeRowCount = " + getRowCount() + " , TableRowCount = " + m_table.getRowCount());
        }

        g.translate(0, -rowBounds.y);
        super.paint(g);
    }

    /*------------------------------------- JTree Public Method -------------------------------------*/

    /**
     * @see javax.swing.JTree#addSelectionPath(javax.swing.tree.TreePath)
     */
    public void addSelectionPath(TreePath path) {
        super.addSelectionPath(path);
        int row = getRowForPath(path);
        m_table.getSelectionModel().addSelectionInterval(row, row);
    }

    /*------------------------------------- TableCellRenderer Public Method -------------------------------------*/

    /**
     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        m_table = table;
        m_row = row;

        if (isSelected) {
            m_view.setForeground(table.getSelectionForeground());
            m_view.setBackground(table.getSelectionBackground());

        } else {
            m_view.setForeground(table.getForeground());
            m_view.setBackground(table.getBackground());
        }

        m_view.setFont(table.getFont());

        if (hasFocus) {
            m_view.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));

            if (table.isCellEditable(row, column)) {
                m_view.setForeground(UIManager.getColor("Table.focusCellForeground"));
                m_view.setBackground(UIManager.getColor("Table.focusCellBackground"));
            }

        } else {
            m_view.setBorder(NO_FOCUS_BORDER);
        }

        return m_view;
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
