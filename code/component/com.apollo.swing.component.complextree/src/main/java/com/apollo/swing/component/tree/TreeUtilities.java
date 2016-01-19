/*
 * �˴��봴���� 2008-7-24 ����09:26:15��
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
 * <p>�ļ����ƣ�TreeUtilities.java</p>
 * <p>�����������������ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-7-24</p>
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
     * ���ۺ��������ı�������λ��
     * @param text �ı���
     * @param tree �ۺ�����
     * @param parent �������
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
     * ���ۺ��������ı�������λ��
     * @param text �ı���
     * @param tree �ۺ�����
     * @return ��ʶ�Ƿ�������ָ�����ı���
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
     * ��ȡ���ĸ�·����
     * @param tree ����
     * @return ���ĸ�·����
     */
    public static TreePath getTreeRootPath(JTree tree) {
        return new TreePath(((DefaultMutableTreeNode) tree.getModel().getRoot()).getPath());
    }

    /**
     * ѡ�����ĸ���
     * @param tree ����
     */
    public static void selectRoot(JTree tree) {
        tree.setSelectionPath(getTreeRootPath(tree));
    }

    /**
     * չ������
     * @param tree ����
     */
    public static void expand(JTree tree) {
        expand(tree, (DefaultMutableTreeNode) tree.getModel().getRoot());
    }

    /**
     * չ������
     * @param tree ����
     * @param node �ڵ㡣
     */
    public static void expand(JTree tree, DefaultMutableTreeNode node) {
        tree.expandPath(new TreePath(node.getPath()));

        for (int i = 0, size = node.getChildCount(); i < size; i++) {
            expand(tree, (DefaultMutableTreeNode) node.getChildAt(i));
        }
    }

    /**
     * �۵�����
     * @param tree ����
     */
    public static void collapse(JTree tree) {
        collapse(tree, (DefaultMutableTreeNode) tree.getModel().getRoot());
    }

    /**
     * �۵�����
     * @param tree ����
     * @param node �ڵ㡣
     */
    public static void collapse(JTree tree, DefaultMutableTreeNode node) {
        for (int i = 0, size = node.getChildCount(); i < size; i++) {
            collapse(tree, (DefaultMutableTreeNode) node.getChildAt(i));
        }

        tree.collapsePath(new TreePath(node.getPath()));
    }

    /**
     * ֹͣ�༭��
     * @param tree ����
     */
    public static void stopEditing(JTree tree) {
        if (tree.isEditing()) {
            tree.stopEditing();
        }
    }

    /**
     * ȡ���༭��
     * @param tree ����
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
     * ��ȡ�ڵ㼯��
     * @param node �ڵ㡣
     * @return �ڵ㼯��
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
     * ���ۺ����ڵ�����ı�������λ��
     * @param text �ı���
     * @param tree �ۺ�����
     * @param nodes �ۺ����ڵ㼯��
     * @return ��ʶ�Ƿ�������ָ�����ı���
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
