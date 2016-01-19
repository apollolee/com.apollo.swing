/*
 * 此代码创建于 2008-7-24 上午09:26:15。
 */
package com.apollo.swing.component.tree;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.apollo.base.util.IBaseConstants;
import com.apollo.swing.component.complextree.ComplexTree;
import com.apollo.swing.component.complextree.ComplexTreeCacheNode;
import com.apollo.swing.component.complextree.ComplexTreeNode;

/**
 * <p>文件名称：TreeUtilities.java</p>
 * <p>类型描述：树工具类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-7-24</p>
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
public class TreeUtilities implements IBaseConstants {

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
     * @param tree 综合树。
     * @param parent 父组件。
     */
    public static void searchText(String text, ComplexTree tree, Component parent) {
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
     * @param tree 综合树。
     * @return 标识是否搜索到指定的文本。
     */
    public static boolean searchText(String text, ComplexTree tree) {
        text = text == null ? null : text.trim().toLowerCase();

        if (text == null || tree == null) {
            return false;
        }

        List nodes = getNodes((ComplexTreeNode) tree.getModel().getRoot());
        TreePath[] paths = tree.getSelectionPaths();

        if (paths == null || paths.length == 0) {
            return searchText(text, tree, nodes);

        } else {
            ComplexTreeNode node = (ComplexTreeNode) paths[paths.length - 1].getLastPathComponent();
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
     * 获取树的根路径。
     * @param tree 树。
     * @return 树的根路径。
     */
    public static TreePath getTreeRootPath(JTree tree) {
        return new TreePath(((DefaultMutableTreeNode) tree.getModel().getRoot()).getPath());
    }

    /**
     * 选择树的根。
     * @param tree 树。
     */
    public static void selectRoot(JTree tree) {
        tree.setSelectionPath(getTreeRootPath(tree));
    }

    /**
     * 展开树。
     * @param tree 树。
     */
    public static void expand(JTree tree) {
        expand(tree, (DefaultMutableTreeNode) tree.getModel().getRoot());
    }

    /**
     * 展开树。
     * @param tree 树。
     * @param node 节点。
     */
    public static void expand(JTree tree, DefaultMutableTreeNode node) {
        tree.expandPath(new TreePath(node.getPath()));

        for (int i = 0, size = node.getChildCount(); i < size; i++) {
            expand(tree, (DefaultMutableTreeNode) node.getChildAt(i));
        }
    }

    /**
     * 折叠树。
     * @param tree 树。
     */
    public static void collapse(JTree tree) {
        collapse(tree, (DefaultMutableTreeNode) tree.getModel().getRoot());
    }

    /**
     * 折叠树。
     * @param tree 树。
     * @param node 节点。
     */
    public static void collapse(JTree tree, DefaultMutableTreeNode node) {
        for (int i = 0, size = node.getChildCount(); i < size; i++) {
            collapse(tree, (DefaultMutableTreeNode) node.getChildAt(i));
        }

        tree.collapsePath(new TreePath(node.getPath()));
    }

    /**
     * 停止编辑。
     * @param tree 树。
     */
    public static void stopEditing(JTree tree) {
        if (tree.isEditing()) {
            tree.stopEditing();
        }
    }

    /**
     * 取消编辑。
     * @param tree 树。
     */
    public static void cancelEditing(JTree tree) {
        if (tree.isEditing()) {
            tree.cancelEditing();
        }
    }

    /*------------------------------------- Protected Static Method -------------------------------------*/

    /* Add Protected Static Method */

    /*------------------------------------- Friendly Static Method -------------------------------------*/

    /* Add Friendly Static Method */

    /*------------------------------------- Private Static Method -------------------------------------*/

    /**
     * 获取节点集。
     * @param node 节点。
     * @return 节点集。
     */
    private static List getNodes(ComplexTreeNode node) {
        List nodes = new ArrayList();
        nodes.add(node);

        if (!node.isLeaf() && (!(node instanceof ComplexTreeCacheNode) || ((ComplexTreeCacheNode) node).isLoaded())) {
            for (int i = 0, size = node.getChildCount(); i < size; i++) {
                nodes.addAll(getNodes((ComplexTreeNode) node.getChildAt(i)));
            }
        }

        return nodes;
    }

    /**
     * 对综合树节点进行文本搜索定位。
     * @param text 文本。
     * @param tree 综合树。
     * @param nodes 综合树节点集。
     * @return 标识是否搜索到指定的文本。
     */
    private static boolean searchText(String text, ComplexTree tree, List nodes) {
        for (int i = 0, size = nodes.size(); i < size; i++) {
            ComplexTreeNode node = (ComplexTreeNode) nodes.get(i);
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

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /* Add Public Method */

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
