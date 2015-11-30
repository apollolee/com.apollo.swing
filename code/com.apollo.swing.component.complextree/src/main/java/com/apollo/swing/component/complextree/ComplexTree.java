/*
 * 此代码创建于 2007-9-12 下午05:16:58
 */
package com.apollo.swing.component.complextree;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.CellEditor;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.apollo.base.util.BaseUtilities;
import com.apollo.base.util.IBaseConstants;
import com.apollo.swing.component.icon.IconWrapper;

/**
 * <p>文件名称：ComplexTree.java</p>
 * <p>文件描述：综合树类，这个组件是对树组件的一个强化，能靠三种不同类型的节点的组合来支持三态选择和缓存功能。</p>
 * <p>版权所有：版权所有(C)2001-2004</p>
 * <p>公　　司：菠萝梨</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2007-9-12</p>
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
public class ComplexTree extends JTree implements IBaseConstants, IComplexTreeModelListener {

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

    /**
     * 对综合树进行文本搜索定位。
     * @param text 文本。
     * @param tree 树。
     * @param parent 父组件。
     */
    public static void searchText(String text, JTree tree, Component parent) {
        String oldText = text;
        text = text == null ? null : text.trim().toLowerCase();

        if (text == null || "".equals(text)) {
            JOptionPane.showMessageDialog(parent, I18N_SearchTextIsNull, I18N_INFO_ERROR, JOptionPane.ERROR_MESSAGE);

        } else {
            if (!searchText(text, tree)) {
                String desc = "<html><font color=\"#ff0000\">" + oldText + "</font>" + I18N_IsNotFound;
                JOptionPane.showMessageDialog(parent, desc, I18N_Info, JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * 对综合树进行文本搜索定位。
     * @param text 文本。
     * @param tree 树。
     * @return 标识是否搜索到指定的文本。
     */
    public static boolean searchText(String text, JTree tree) {
        text = text == null ? null : text.trim().toLowerCase();

        if (text == null || tree == null) {
            return false;
        }

        List<DefaultMutableTreeNode> nodes = getNodes((DefaultMutableTreeNode) tree.getModel().getRoot());
        TreePath[] paths = tree.getSelectionPaths();

        if (paths == null || paths.length == 0) {
            return searchText(text, tree, nodes);

        } else {
            Object node = paths[paths.length - 1].getLastPathComponent();
            int index = nodes.indexOf(node);

            if (searchText(text, tree, nodes.subList(index + 1, nodes.size()))) {
                return true;
            }

            if (searchText(text, tree, nodes.subList(0, index + 1))) {
                return true;
            }
        }

        return false;
    }

    /**
     * 获取节点集。
     * @param node 节点。
     * @return 节点集。
     */
    public static List<DefaultMutableTreeNode> getNodes(DefaultMutableTreeNode node) {
        List<DefaultMutableTreeNode> nodes = new ArrayList<DefaultMutableTreeNode>();
        nodes.add(node);

        if (!node.isLeaf() && (!(node instanceof ComplexTreeCacheNode) || ((ComplexTreeCacheNode) node).isLoaded())) {
            for (int i = 0, size = node.getChildCount(); i < size; i++) {
                nodes.addAll(getNodes((DefaultMutableTreeNode) node.getChildAt(i)));
            }
        }

        return nodes;
    }

    /**
     * 对综合树节点进行文本搜索定位。
     * @param text 文本。
     * @param tree 树。
     * @param nodes 节点集。
     * @return 标识是否搜索到指定的文本。
     */
    public static boolean searchText(String text, JTree tree, List<DefaultMutableTreeNode> nodes) {
        for (DefaultMutableTreeNode node : nodes) {
            Object value = node.getUserObject();

            if (value != null && value.toString().trim().toLowerCase().indexOf(text) > -1) {
                TreePath path = new TreePath(node.getPath());

                tree.setSelectionPath(path);
                tree.scrollPathToVisible(path);
                tree.requestFocus();

                return true;
            }
        }

        return false;
    }

    /*------------------------------------- Protected Static Method -------------------------------------*/

    /**
     * 判断缓存是否已经加入释放列表了。
     * @param cache 缓存。
     * @param willReleaseCaches 释放列表。
     * @return 标识是否已经加入释放列表了。
     */
    protected static boolean isReleased(ComplexTreeCacheNode cache, List willReleaseCaches) {
        for (int i = 0, size = willReleaseCaches.size(); i < size; i++) {
            if (cache.isNodeAncestor((TreeNode) willReleaseCaches.get(i))) {
                willReleaseCaches.remove(i);
                willReleaseCaches.add(i, cache);
                return true;
            }
        }

        return false;
    }

    /*------------------------------------- Friendly Static Method -------------------------------------*/

    /* Add Friendly Static Method */

    /*------------------------------------- Private Static Method -------------------------------------*/

    /* Add Private Static Method */

    /*------------------------------------- Public Field -------------------------------------*/

    /* Add Public Field */

    /*------------------------------------- Protected Field -------------------------------------*/

    /**
     * 标识是否选择节点时也同时点击选择框。
     */
    protected volatile boolean m_isSyncChoice = false;

    /**
     * 标识当选择节点处于编辑状态时，点击节点最前面选择框后是否立即停止编辑。
     */
    protected volatile boolean m_isStopEditingWhenClickChoice = true;

    /**
     * 即将被编辑的节点，临时属性。
     */
    protected ComplexTreeNode m_willEditNode = null;

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 构造方法。
     * @param model 综合树数据模型。
     * @since T3 V1.1
     */
    public ComplexTree(ComplexTreeModel model) {
        /* 调用父类的构造方法 */
        super(model);

        /* 安装渲染器和编辑器，并且不允许用户改变这个设置 */
        super.setCellRenderer(new ComplexTreeCellRenderer());
        super.setCellEditor(new ComplexTreeCellEditor(this));

        /* 由于存在更细粒度的控制，这里整个综合树设置为默认允许编辑 */
        setEditable(true);
        setShowsRootHandles(true);
        setToggleClickCount(0);

        /* 针对各节点ToolTip的支持；TODO 注意：这里可能存在显示效率和内存安全等问题 */
        ToolTipManager.sharedInstance().registerComponent(this);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * 获取综合树模型。
     * @return 综合树模型。
     * @since T3 V1.1
     */
    public ComplexTreeModel getComplexTreeModel() {
        return (ComplexTreeModel) treeModel;
    }

    /**
     * 获取字段 isSyncChoice 的值，标识是否允许同步选择，如果允许则点击节点的非选择框区域也会把选择框勾上，比较适合非编辑的综合树，同步选择缺省不允许。
     * @return 字段 isSyncChoice 的值。
     * @since T3 V1.1
     */
    public boolean isSyncChoice() {
        return m_isSyncChoice;
    }

    /**
     * 设置字段 isSyncChoice 的值，标识是否允许同步选择，如果允许则点击节点的非选择框区域也会把选择框勾上，比较适合非编辑的综合树，同步选择缺省不允许。
     * @param isSyncChoice 字段 isSyncChoice 的值。
     * @since T3 V1.1
     */
    public void setSyncChoice(boolean isSyncChoice) {
        m_isSyncChoice = isSyncChoice;
    }

    /**
     * 判断当选择节点处于编辑状态时，点击节点最前面选择框后是否立即停止编辑。
     * @return 为true表示立即停止编辑，为false表示不立即停止编辑。
     * @since T3 V1.1
     */
    public boolean isStopEditingWhenClickChoice() {
        return m_isStopEditingWhenClickChoice;
    }

    /**
     * 设置当选择节点处于编辑状态时，点击节点最前面选择框后是否立即停止编辑。
     * @param isStopEditingWhenClickChoice 为true表示立即停止编辑，为false表示不立即停止编辑。
     * @since T3 V1.1
     */
    public void setStopEditingWhenClickChoice(boolean isStopEditingWhenClickChoice) {
        m_isStopEditingWhenClickChoice = isStopEditingWhenClickChoice;
    }

    /**
     * 设置字段 isEnabledChoice 的值，标识是否允许选择，如果不允许则界面上的节点前面不会带有选择框，缺省为允许。
     * @param isEnabledChoice 字段 isEnabledChoice 的值。
     * @since T3 V1.1
     */
    public void setEnabledChoice(boolean isEnabledChoice) {
        ComplexTreeModel model = getComplexTreeModel();

        if (model.m_isEnabledChoice == isEnabledChoice) {
            return;
        }

        model.clearChosenPaths();
        model.m_isEnabledChoice = isEnabledChoice;

        int currentRowHeight = getRowHeight();
        updateUI();
        setRowHeight(currentRowHeight);
    }

    /**
     * 展开当前所有被勾选的节点所在的路径。
     * @since T3 V1.1
     */
    public void expand() {
        expand(getComplexTreeModel().getChosenPaths());
    }

    /**
     * 展开指定路径集，注意：路径里包含的每个值不是节点对象，而是节点对象所带的userObject对象。
     * @param paths 路径集。
     * @since T3 V1.1
     */
    public void expand(TreePath[] paths) {
        if (paths == null || paths.length == 0) {
            return;
        }

        for (int i = 0; i < paths.length; i++) {
            expand(paths[i]);
        }
    }

    /**
     * 展开指定路径，注意：路径里包含的每个值不是节点对象，而是节点对象所带的userObject对象。
     * @param path 路径。
     * @since T3 V1.1
     */
    public void expand(final TreePath path) {
        if (path == null) {
            return;
        }

        final ComplexTreeModel model = getComplexTreeModel();
        final ComplexTreeNode lastNode = model.getLastNode(path);

        if (lastNode == null) {
            return;
        }

        TreePath lastNodePath = new TreePath(lastNode.getPath());
        if (path.getLastPathComponent().equals(lastNode.getUserObject())) {
            expandPath(lastNode.isRoot() ? lastNodePath : new TreePath(((ComplexTreeNode) lastNode.getParent()).getPath()));
            addSelectionPath(lastNodePath);
            scrollPathToVisible(lastNodePath);

        } else {
            if (lastNode instanceof ComplexTreeCacheNode && !((ComplexTreeCacheNode) lastNode).isLoaded()) {
                model.addComplexTreeModelListener(new IComplexTreeModelListener() {

                    /**
                     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#nodeChoiceChanged(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
                     */
                    public void nodeChoiceChanged(ComplexTreeModelEvent evt) {
                    }

                    /**
                     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#nodeWillLoad(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
                     */
                    public void nodeWillLoad(ComplexTreeModelEvent evt) {
                    }

                    /**
                     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#nodeLoaded(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
                     */
                    public void nodeLoaded(ComplexTreeModelEvent evt) {
                        if (evt.getSource() == lastNode) {
                            expand(path);
                            model.removeComplexTreeModelListener(this);
                        }
                    }

                    /**
                     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#nodeLoadFailed(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
                     */
                    public void nodeLoadFailed(ComplexTreeModelEvent evt) {
                        if (evt.getSource() == lastNode) {
                            model.removeComplexTreeModelListener(this);
                        }
                    }

                    /**
                     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#releaseCache(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
                     */
                    public void releaseCache(ComplexTreeModelEvent evt) {
                    }

                });
                expandPath(lastNodePath);
            }
        }
    }

    /*------------------------------------- JComponent Public Method -------------------------------------*/

    /**
     * @see javax.swing.JComponent#setEnabled(boolean)
     */
    public void setEnabled(boolean enabled) {
        ((ComplexTreeCellRenderer) getCellRenderer()).m_choice.m_choiceBtn.setEnabled(enabled);
        super.setEnabled(enabled);
    }

    /*------------------------------------- JTree Public Method -------------------------------------*/

    /**
     * 细粒度的控制节点工具提示。
     * @see javax.swing.JTree#getToolTipText(java.awt.event.MouseEvent)
     */
    public String getToolTipText(MouseEvent evt) {
        if (evt == null) {
            return null;
        }

        TreePath path = getPathForLocation(evt.getX(), evt.getY());

        if (path == null) {
            return null;
        }

        return ((ComplexTreeNode) path.getLastPathComponent()).getToolTipText();
    }

    /**
     * 细粒度控制节点是否可编辑。
     * @see javax.swing.JTree#isPathEditable(javax.swing.tree.TreePath)
     */
    public boolean isPathEditable(TreePath path) {
        if (super.isPathEditable(path)) {
            /* 保存即将被编辑的节点 */
            m_willEditNode = (ComplexTreeNode) path.getLastPathComponent();
            return m_willEditNode.isEditable();

        } else {
            return false;
        }
    }

    /**
     * @see javax.swing.JTree#setModel(javax.swing.tree.TreeModel)
     * @throws IllegalArgumentException 设置的新模型不是一个ComplexTreeModel则会抛出此异常。
     */
    public void setModel(TreeModel newModel) {
        if (!(newModel instanceof ComplexTreeModel)) {
            throw new IllegalArgumentException("New model is not a ComplexTreeModel !");
        }

        ComplexTreeModel oldModel = getComplexTreeModel();
        if (oldModel != null) {
            oldModel.removeComplexTreeModelListener(this);
        }
        ((ComplexTreeModel) newModel).addComplexTreeModelListener(this);

        super.setModel(newModel);
    }

    /**
     * 阻止用户设置表级的单元格编辑器。
     * @see javax.swing.JTree#setCellEditor(javax.swing.tree.TreeCellEditor)
     * @throws IllegalArgumentException 用户调用此API会抛出此异常。
     */
    public void setCellEditor(TreeCellEditor newEditor) {
        throw new IllegalArgumentException("Can not set new tree cell editor !");
    }

    /*------------------------------------- IComplexTreeModelListener Public Method -------------------------------------*/

    /**
     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#nodeChoiceChanged(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
     */
    public void nodeChoiceChanged(ComplexTreeModelEvent evt) {
        repaint();
    }

    /**
     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#nodeWillLoad(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
     */
    public void nodeWillLoad(ComplexTreeModelEvent evt) {
        /* 暂不必实现 */
    }

    /**
     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#nodeLoaded(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
     */
    public void nodeLoaded(ComplexTreeModelEvent evt) {
        /* 暂不必实现 */
    }

    /**
     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#nodeLoadFailed(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
     */
    public void nodeLoadFailed(ComplexTreeModelEvent evt) {
        ComplexTreeCacheNode cacheNode = (ComplexTreeCacheNode) evt.getSource();
        collapsePath(new TreePath(cacheNode.getPath()));
        if (isShowing()) {
            JOptionPane.showMessageDialog(this,
                                          evt.getException().getMessage(),
                                          cacheNode.getUserObject().toString(),
                                          JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#releaseCache(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
     */
    public void releaseCache(ComplexTreeModelEvent evt) {
        List caches = evt.getCaches();
        List willReleaseCaches = new ArrayList();
        ComplexTreeCacheNode parentCacheNode = (ComplexTreeCacheNode) evt.getSource();

        for (int i = caches.size() - 1; i >= 0; i--) {
            ComplexTreeCacheNode cacheNode = (ComplexTreeCacheNode) caches.get(i);
            if (!parentCacheNode.isNodeAncestor(cacheNode)) {
                if (isCollapsed(new TreePath(cacheNode.getPath()))) {
                    if (!isReleased(cacheNode, willReleaseCaches)) {
                        willReleaseCaches.add(cacheNode);
                    }
                    caches.remove(i);
                }
            }
        }

        if (!willReleaseCaches.isEmpty()) {
            releaseCaches(willReleaseCaches, parentCacheNode);
            return;
        }

        for (int i = caches.size() - 1; i >= 0; i--) {
            ComplexTreeCacheNode cache = (ComplexTreeCacheNode) caches.get(i);
            if (!parentCacheNode.isNodeAncestor(cache)) {
                if (!isReleased(cache, willReleaseCaches)) {
                    willReleaseCaches.add(cache);
                }
                caches.remove(i);
            }
        }

        if (!willReleaseCaches.isEmpty()) {
            releaseCaches(willReleaseCaches, parentCacheNode);
        }
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * 释放缓存列表。
     * @param caches 缓存列表。
     * @param parentCacheNode 父缓存节点。
     */
    protected void releaseCaches(final List caches, final ComplexTreeCacheNode parentCacheNode) {
        SwingUtilities.invokeLater(new Runnable() {

            /**
             * @see java.lang.Runnable#run()
             */
            public void run() {
                for (int i = 0, size = caches.size(); i < size; i++) {
                    ComplexTreeCacheNode cache = (ComplexTreeCacheNode) caches.get(i);
                    if (isExpanded(new TreePath(cache.getPath()))) {
                        collapsePath(new TreePath(cache.getPath()));
                    }
                    getComplexTreeModel().releaseCache(cache);
                }

                SwingUtilities.invokeLater(new Runnable() {

                    /**
                     * @see java.lang.Runnable#run()
                     */
                    public void run() {
                        scrollPathToVisible(new TreePath(parentCacheNode.getPath()));
                    }

                });
            }

        });
    }

    /*------------------------------------- JComponent Protected Method -------------------------------------*/

    /**
     * 点击选中的动作在这里实现。
     * @see javax.swing.JComponent#processMouseEvent(java.awt.event.MouseEvent)
     */
    protected void processMouseEvent(MouseEvent evt) {
        if (isEnabled() && isEditable() && getComplexTreeModel().m_isEnabledChoice && evt.getID() == MouseEvent.MOUSE_CLICKED && evt.getButton() == MouseEvent.BUTTON1) {
            Point point = evt.getPoint();
            int row = getRowForLocation(point.x, point.y);

            if (row != -1) {
                ComplexTreeNode node = (ComplexTreeNode) getPathForRow(row).getLastPathComponent();
                if (node instanceof ComplexTreeChoiceNode && (m_isSyncChoice || getChoiceBounds(row).contains(point))) {
                    getComplexTreeModel().doClick((ComplexTreeChoiceNode) node);
                    repaint();
                }
            }
        }

        super.processMouseEvent(evt);
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /**
     * 获取选择框的范围。
     * @param row 行。
     * @return 选择框的范围。
     */
    Rectangle getChoiceBounds(int row) {
        Rectangle rowBounds = getRowBounds(row);
        Icon icon = UIManager.getIcon("CheckBox.icon");

        int x = rowBounds.x + 5;
        int y = rowBounds.y + rowBounds.height / 2 - icon.getIconHeight() / 2;
        int width = icon.getIconWidth() - 1;
        int height = icon.getIconHeight() - 1;

        return new Rectangle(x, y, width, height);
    }

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}

/**
 * <p>文件名称：ComplexTree.java</p>
 * <p>类型描述：综合树常量接口。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-9-23</p>
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
interface IConstants extends IBaseConstants {

    /*------------------------------------- Public Static Final Field -------------------------------------*/

    /**
     * 状态------准备。
     */
    public static final int STATE_READY = 0;

    /**
     * 状态------载入中。
     */
    public static final int STATE_LOADING = 1;

    /**
     * 状态------已载入。
     */
    public static final int STATE_LOADED = 2;

    /*------------------------------------- Public Abstract Method -------------------------------------*/

    /* Add Public Abstract Method */

}

/**
 * <p>文件名称：ComplexTree.java</p>
 * <p>类型描述：综合树工具类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-9-23</p>
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
class Utilities {

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

    /**
     * 通过key来获取列表定制的国际化信息。
     * @param i18nKey 国际化信息关键字。
     * @return 国际化信息。
     */
    protected static String getI18nMessage(String i18nKey) {
        return BaseUtilities.getI18nMessage("com.apollo.swing.component.complextree.resource.ComplexTree", i18nKey);
    }

    /**
     * 获取图标。
     * @param iconFilename 图标文件名。
     * @return 图标。
     */
    protected static Icon getIcon(String iconFilename) {
        return BaseUtilities.getIcon(Utilities.class, "com/apollo/swing/component/complextree/resource/icon/", iconFilename);
    }

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

    /* Add Public Constructor */

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /**
     * 构造方法。
     */
    private Utilities() {
        /* 禁止从外部实例化此类 */
    }

    /*------------------------------------- Public Method -------------------------------------*/

    /* Add Public Method */

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}

/**
 * <p>文件名称：ComplexTree.java</p>
 * <p>类型描述：选择类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2009-11-27</p>
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
class Choice {

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

    /**
     * 选择图标，此属性主要负责标识选择是否处于不确定（半选）状态。
     */
    IconWrapper m_choiceIcon = IconWrapper.createCheckBoxIconWrapper();

    /**
     * 选择按钮。
     */
    JToggleButton m_choiceBtn = new JCheckBox(m_choiceIcon);

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /* Add Public Constructor */

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /**
     * 构造方法。
     */
    Choice() {
        m_choiceBtn.setOpaque(false);
    }

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * 判断是否为已选择状态。
     * @return 标识是否为已选择状态。
     */
    public boolean isSelected() {
        return m_choiceBtn.isSelected() && !m_choiceIcon.isIndetermined();
    }

    /**
     * 设置为已选择状态。
     */
    public void setSelected() {
        m_choiceBtn.setSelected(true);
        m_choiceIcon.setIndetermined(false);
    }

    /**
     * 判断是否为未选择状态。
     * @return 标识是否为未选择状态。
     */
    public boolean isDeselected() {
        return !m_choiceBtn.isSelected() && !m_choiceIcon.isIndetermined();
    }

    /**
     * 设置为未选择状态。
     */
    public void setDeselected() {
        m_choiceBtn.setSelected(false);
        m_choiceIcon.setIndetermined(false);
    }

    /**
     * 判断是否为不确定状态。
     * @return 标识是否为不确定状态。
     */
    public boolean isIndetermined() {
        return m_choiceIcon.isIndetermined();
    }

    /**
     * 设置为不确定状态。
     */
    public void setIndetermined() {
        m_choiceBtn.setSelected(true);
        m_choiceIcon.setIndetermined(true);
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}

/**
 * <p>文件名称：ComplexTree.java</p>
 * <p>文件描述：综合树渲染器类。</p>
 * <p>版权所有：版权所有(C)2001-2004</p>
 * <p>公　　司：菠萝梨</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2007-11-16</p>
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
class ComplexTreeCellRenderer extends DefaultTreeCellRenderer {

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
     * 选择。
     */
    protected Choice m_choice = new Choice();

    /**
     * 选择节点面板。
     */
    protected JPanel m_choiceNodeView = new JPanel(new GridBagLayout());

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 构造方法。
     */
    public ComplexTreeCellRenderer() {
        m_choiceNodeView.setOpaque(false);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /* Add Public Method */

    /*------------------------------------- DefaultTreeCellRenderer Public Method -------------------------------------*/

    /**
     * @see javax.swing.tree.DefaultTreeCellRenderer#getTreeCellRendererComponent(javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int, boolean)
     */
    public Component getTreeCellRendererComponent(JTree tree,
                                                  Object value,
                                                  boolean isSelected,
                                                  boolean isExpanded,
                                                  boolean isLeaf,
                                                  int rowIndex,
                                                  boolean hasFocus) {
        ComplexTreeNode node = (ComplexTreeNode) value;

        Component rc = null;
        TreeCellRenderer renderer = node.getRenderer();
        if (renderer == null) {
            rc = super.getTreeCellRendererComponent(tree, value, isSelected, isExpanded, isLeaf, rowIndex, hasFocus);

        } else {
            rc = renderer.getTreeCellRendererComponent(tree, value, isSelected, isExpanded, isLeaf, rowIndex, hasFocus);
        }

        m_choiceNodeView.removeAll();

        /* 处理选择节点（选择节点需要在已有的渲染组件前面加上一个选择框） */
        if (((ComplexTree) tree).getComplexTreeModel().m_isEnabledChoice && node instanceof ComplexTreeChoiceNode) {
            ComplexTreeChoiceNode choiceNode = (ComplexTreeChoiceNode) node;

            if (choiceNode.isSelected()) {
                m_choice.setSelected();

            } else if (choiceNode.isDeselected()) {
                m_choice.setDeselected();

            } else {
                m_choice.setIndetermined();
            }

            m_choiceNodeView.add(m_choice.m_choiceBtn, new GridBagConstraints(GridBagConstraints.RELATIVE,
                                                                              0,
                                                                              1,
                                                                              1,
                                                                              0.0,
                                                                              0.0,
                                                                              GridBagConstraints.WEST,
                                                                              GridBagConstraints.NONE,
                                                                              new Insets(0, 0, 0, 0),
                                                                              0,
                                                                              0));
        }

        m_choiceNodeView.add(rc, new GridBagConstraints(GridBagConstraints.RELATIVE,
                                                        0,
                                                        1,
                                                        1,
                                                        1.0,
                                                        1.0,
                                                        GridBagConstraints.WEST,
                                                        GridBagConstraints.BOTH,
                                                        new Insets(0, 0, 0, 0),
                                                        0,
                                                        0));

        return m_choiceNodeView;
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}

/**
 * <p>文件名称：ComplexTree.java</p>
 * <p>文件描述：综合树编辑器类。</p>
 * <p>版权所有：版权所有(C)2001-2004</p>
 * <p>公　　司：菠萝梨</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2007-11-16</p>
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
class ComplexTreeCellEditor extends DefaultTreeCellEditor {

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
     * 编辑节点单元格编辑监听器。
     */
    protected CellEditorListener m_cellEditorListener = new CellEditorListener() {

        /**
         * @see javax.swing.event.CellEditorListener#editingCanceled(javax.swing.event.ChangeEvent)
         */
        public void editingCanceled(ChangeEvent evt) {
            ((CellEditor) realEditor).cancelCellEditing();
            ((CellEditor) evt.getSource()).removeCellEditorListener(this);
        }

        /**
         * @see javax.swing.event.CellEditorListener#editingStopped(javax.swing.event.ChangeEvent)
         */
        public void editingStopped(ChangeEvent evt) {
            ((CellEditor) realEditor).stopCellEditing();
            ((CellEditor) evt.getSource()).removeCellEditorListener(this);
        }

    };

    /**
     * 动作监听器------当动作发生时点击选择框并重新绘制树，最后判断是否需要终止编辑动作。
     * 注意：刷新和下面的停止编辑不同，这里不可以移除掉此监听器，否则无法持续刷新，因为这里的点击不会立刻导致退出编辑状态。
     * TODO 这里也会带来一个小的BUG，会有冗余的刷新动作在进行，但不会对界面造成影响。 
     */
    protected ActionListener m_choiceAction = new ActionListener() {

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_complexTree.getComplexTreeModel().doClick((ComplexTreeChoiceNode) m_complexTree.m_willEditNode);
            m_complexTree.repaint();

            if (m_complexTree.m_isStopEditingWhenClickChoice) {
                stopCellEditing();
            }
        }

    };

    /**
     * 综合树对象引用。
     */
    protected ComplexTree m_complexTree;

    /**
     * 选择。
     */
    protected Choice m_choice = new Choice();

    /**
     * 选择节点面板。
     */
    protected JPanel m_choiceNodeView = new JPanel(new GridBagLayout());

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 构造方法。
     * @param complexTree 综合树。
     */
    public ComplexTreeCellEditor(ComplexTree complexTree) {
        /* 用综合树对象及其渲染器来初始化编辑器，这里要求在综合树构造方法中先设置渲染器，后设置编辑器 */
        super(complexTree, (DefaultTreeCellRenderer) complexTree.getCellRenderer());
        m_complexTree = complexTree;
        m_choice.m_choiceBtn.addActionListener(m_choiceAction);
        m_choiceNodeView.setOpaque(false);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /* Add Public Method */

    /*------------------------------------- DefaultTreeCellEditor Public Method -------------------------------------*/

    /**
     * @see javax.swing.tree.DefaultTreeCellEditor#isCellEditable(java.util.EventObject)
     */
    public boolean isCellEditable(EventObject evt) {
        if (m_complexTree.getComplexTreeModel().m_isEnabledChoice && evt instanceof MouseEvent && m_complexTree.m_willEditNode instanceof ComplexTreeChoiceNode) {
            Point point = ((MouseEvent) evt).getPoint();
            int row = m_complexTree.getRowForLocation(point.x, point.y);
            if (m_complexTree.getChoiceBounds(row).contains(point)) {
                return false;
            }
        }

        if (m_complexTree.m_willEditNode.isEditable()) { //即将被编辑的节点允许编辑
            /* 获取即将要编辑节点的编辑器 */
            TreeCellEditor editor = m_complexTree.m_willEditNode.getEditor();

            /* 把判断委托给编辑器 */
            if (editor == null) { //即将被编辑的节点没有设置编辑器，交由缺省编辑器判断是否允许编辑
                return super.isCellEditable(evt);

            } else { //即将被编辑的节点设置了编辑器
                return editor.isCellEditable(evt);
            }

        } else { //即将被编辑的节点不允许编辑
            return false;
        }
    }

    /**
     * @see javax.swing.tree.DefaultTreeCellEditor#getTreeCellEditorComponent(javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int)
     */
    public Component getTreeCellEditorComponent(JTree tree,
                                                Object value,
                                                boolean isSelected,
                                                boolean isExpanded,
                                                boolean isLeaf,
                                                int rowIndex) {
        /* 定义编辑组件 */
        Component ec = null;

        /* 获取编辑节点 */
        ComplexTreeNode node = (ComplexTreeNode) value;

        /* 安装编辑节点 */
        installEditingNode(node);

        /* 获取编辑组件 */
        TreeCellEditor nodeEditor = node.getEditor();
        if (nodeEditor == null) { //编辑节点没有设置节点编辑器
            ec = super.getTreeCellEditorComponent(tree, value, isSelected, isExpanded, isLeaf, rowIndex);

        } else { //编辑节点设置了节点编辑器
            ec = nodeEditor.getTreeCellEditorComponent(tree, value, isSelected, isExpanded, isLeaf, rowIndex);
        }

        /* 清空选择节点面板 */
        m_choiceNodeView.removeAll();

        /* 处理选择节点（选择节点需要在已有的编辑组件前面加上一个复选框） */
        if (m_complexTree.getComplexTreeModel().m_isEnabledChoice && node instanceof ComplexTreeChoiceNode) { //是选择节点
            /* 获取选择节点 */
            ComplexTreeChoiceNode choiceNode = (ComplexTreeChoiceNode) node;

            if (choiceNode.isSelected()) {
                m_choice.setSelected();

            } else if (choiceNode.isDeselected()) {
                m_choice.setDeselected();

            } else {
                m_choice.setIndetermined();
            }

            /* 添加复选框到选择节点面板 */
            m_choiceNodeView.add(m_choice.m_choiceBtn, new GridBagConstraints(GridBagConstraints.RELATIVE,
                                                                              0,
                                                                              1,
                                                                              1,
                                                                              0.0,
                                                                              0.0,
                                                                              GridBagConstraints.WEST,
                                                                              GridBagConstraints.NONE,
                                                                              new Insets(0, 0, 0, 0),
                                                                              0,
                                                                              0));
        }

        /* 添加渲染组件到选择节点面板 */
        m_choiceNodeView.add(ec, new GridBagConstraints(GridBagConstraints.RELATIVE,
                                                        0,
                                                        1,
                                                        1,
                                                        1.0,
                                                        1.0,
                                                        GridBagConstraints.WEST,
                                                        GridBagConstraints.BOTH,
                                                        new Insets(0, 0, 0, 0),
                                                        0,
                                                        0));

        /* 返回渲染组件 */
        return m_choiceNodeView;
    }

    /**
     * 返回单元格编辑值时不用考虑用户设置的编辑器是否被应用了，只要用户设置了编辑器，就委托给它。
     * @see javax.swing.tree.DefaultTreeCellEditor#getCellEditorValue()
     */
    public Object getCellEditorValue() {
        /* 获取即将要编辑节点的编辑器 */
        TreeCellEditor editor = m_complexTree.m_willEditNode.getEditor();

        /* 把判断委托给编辑器 */
        if (editor == null) { //即将被编辑的节点没有设置编辑器，交由缺省编辑器判断是否允许编辑
            return super.getCellEditorValue();

        } else { //即将被编辑的节点设置了编辑器
            return editor.getCellEditorValue();
        }
    }

    /**
     * 停止单元格编辑时需要考虑用户设置的编辑器是否被应用了，没有被应用就不用就委托给它了。
     * @see javax.swing.tree.DefaultTreeCellEditor#stopCellEditing()
     */
    public boolean stopCellEditing() {
        /* 获取即将要编辑节点的编辑器 */
        TreeCellEditor editor = m_complexTree.m_willEditNode.getEditor();

        /* 定义判断结果 */
        boolean isStopCellEditing;

        /* 把判断委托给编辑器 */
        if (editor == null) { //即将被编辑的节点没有设置编辑器
            isStopCellEditing = super.stopCellEditing(); //交由缺省编辑器判断是否允许编辑

        } else { //即将被编辑的节点设置了编辑器
            isStopCellEditing = editor.stopCellEditing();
        }

        /* 卸载编辑节点 */
        uninstallEditingNode(m_complexTree.m_willEditNode);

        /* 返回判断结果 */
        return isStopCellEditing;
    }

    /**
     * 取消单元格编辑时需要考虑用户设置的编辑器是否被应用了，没有被应用就不用就委托给它了。
     * @see javax.swing.tree.DefaultTreeCellEditor#cancelCellEditing()
     */
    public void cancelCellEditing() {
        /* 获取即将要编辑节点的编辑器 */
        TreeCellEditor editor = m_complexTree.m_willEditNode.getEditor();

        /* 把判断委托给编辑器 */
        if (editor == null) { //即将被编辑的节点没有设置编辑器
            super.cancelCellEditing(); //交由缺省编辑器判断是否允许编辑

        } else { //即将被编辑的节点设置了编辑器
            editor.cancelCellEditing();
        }

        /* 卸载编辑节点 */
        uninstallEditingNode(m_complexTree.m_willEditNode);
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * 安装编辑节点。
     * @param node 编辑节点。
     */
    protected void installEditingNode(ComplexTreeNode node) {
        /* 安装编辑节点设置的缺省渲染器 */
        TreeCellRenderer nodeRenderer = node.getRenderer();
        if (nodeRenderer instanceof DefaultTreeCellRenderer) { //编辑节点设置的渲染器是一个缺省的树单元格渲染器
            renderer = (DefaultTreeCellRenderer) nodeRenderer;
        }

        /* 安装编辑节点的单元格编辑监听器 */
        TreeCellEditor nodeEditor = node.getEditor(); //获取节点编辑器
        if (nodeEditor != null) { //编辑节点设置了节点编辑器
            nodeEditor.addCellEditorListener(m_cellEditorListener); //添加编辑节点的单元格编辑监听器
        }
    }

    /**
     * 卸载编辑节点。
     * @param node 编辑节点。
     */
    protected void uninstallEditingNode(ComplexTreeNode node) {
        /* 卸载编辑节点设置的缺省渲染器 */
        renderer = (DefaultTreeCellRenderer) m_complexTree.getCellRenderer();
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
