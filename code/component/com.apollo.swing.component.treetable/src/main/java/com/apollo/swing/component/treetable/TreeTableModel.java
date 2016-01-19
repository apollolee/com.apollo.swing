/*
 * �˴��봴���� 2009-9-14 ����02:36:20��
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
 * <p>�ļ����ƣ�TreeTableModel.java</p>
 * <p>��������������ģ���࣬����ĺ����߼���������ʵ�ֵġ�</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2009-9-14</p>
 * <p>�޸ļ�¼��</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * @version 1.0
 * @author ����
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
     * ��ȡ�������
     * @param node �ڵ㡣
     * @return �������
     */
    private static TreeTableObject getObject(ComplexTreeNode node) {
        return (TreeTableObject) node.getUserObject();
    }

    /**
     * ��ȡ�ڵ����һ���ֵܽڵ㡣
     * @param node �ڵ㡣
     * @return �ڵ����һ���ֵܽڵ㡣
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
     * �ۺ�����
     */
    private ComplexTree m_tree;

    /**
     * �ڵ㼯��
     */
    private List m_nodes = new ArrayList();

    /**
     * ��������
     */
    private List m_columnNames = new ArrayList();

    /**
     * �����ͼ���
     */
    private Map m_columnClasses = new HashMap();

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     * @param model �ۺ���ģ�͡�
     * @param columnNames ��������
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
     * ��ȡ�ۺ�������������ݽṹ��ʵ��һ���ۺ���ģ�ͣ�ֻ����ÿ���ڵ��userObject���ٶ�����һ�����Ԫ���б�Ľṹ��
     * @return �ۺ�����
     * @since T3 V1.1
     */
    public ComplexTree getTree() {
        return m_tree;
    }

    /**
     * ��ȡ�ڵ㣬�õ��ڵ���ܵõ��ڵ���ڵ�������ݣ�����ڵ���ĵ�Ԫ�����ݵȡ�
     * @param rowIndex ��������
     * @return �ڵ㡣
     * @since T3 V1.1
     */
    public ComplexTreeNode getNode(int rowIndex) {
        return (ComplexTreeNode) m_nodes.get(rowIndex);
    }

    /**
     * ˢ�£�����ģ������ͬ������ģ�������У���������ǳ���Ҫ����Ϊ�������ȷ��ʾ��������ģ�ͺͱ�ģ�����ݵ�ͬ�������ܶ����ݵĸĶ�������ģ���Ͻ��еģ�����ģ���нڵ���ɾʱ�����Ҫ����һ�����������
     * @since T3 V1.1
     */
    public void refresh() {
        m_nodes.clear();
        m_nodes.addAll(getVisibleNodes((ComplexTreeNode) m_tree.getModel().getRoot()));
        fireTableDataChanged();
    }

    /**
     * ���������ͣ�������ܺͱ�ģ�͵Ķ��ڹ���һ�����ɲο�JDK�ĵ���
     * @param columnIndex ��������
     * @param cls �����͡�
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
     * �ж��Ƿ��������С�
     * @param rowIndex ��������
     * @return �Ƿ��������С�
     */
    boolean isLoading(int rowIndex) {
        return ComplexTreeCacheNode.I18N_INFO_LOADING.equals(((ComplexTreeNode) m_nodes.get(rowIndex)).getUserObject());
    }

    /*------------------------------------- Private Method -------------------------------------*/

    /**
     * ��ȡ�ɼ��Ľڵ㼯��
     * @param node �ڵ㡣
     * @return �ɼ��Ľڵ㼯��
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
 * <p>�ļ����ƣ�TreeTableModel.java</p>
 * <p>�����������ۺ�����װ�ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2009-9-19</p>
 * <p>�޸ļ�¼��</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * @version 1.0
 * @author ����
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
     * ����Ⱦ����
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
     * û����ı߿�
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
     * ��
     */
    private JTable m_table;

    /**
     * ����Ⱦ����
     */
    private TreeCellRenderer m_treeRenderer;

    /**
     * �С�
     */
    private int m_row;

    /**
     * ��ͼ��
     */
    private JPanel m_view = new JPanel(new BorderLayout());

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     * @param treeModel �ۺ���ģ�͡�
     * @param tableModel ����ģ�͡�
     */
    public TreeWrapper(ComplexTreeModel treeModel, TreeTableModel tableModel) {
        super(treeModel);

        addTreeExpansionListener(tableModel);
        treeModel.addTreeModelListener(tableModel);

        updateUI(); //���ü�����λ�ã���֤TTreeTableModel�ص���������󱻵���
        setModel(treeModel); //���ü�����λ�ã���֤TTreeTableModel�ص���������󱻵���

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
