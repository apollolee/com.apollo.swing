/*
 * �˴��봴���� 2007-9-12 ����05:16:58
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
 * <p>�ļ����ƣ�ComplexTree.java</p>
 * <p>�ļ��������ۺ����࣬�������Ƕ��������һ��ǿ�����ܿ����ֲ�ͬ���͵Ľڵ�������֧����̬ѡ��ͻ��湦�ܡ�</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004</p>
 * <p>������˾��������</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2007-9-12</p>
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
     * ���ۺ��������ı�������λ��
     * @param text �ı���
     * @param tree ����
     * @param parent �������
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
     * ���ۺ��������ı�������λ��
     * @param text �ı���
     * @param tree ����
     * @return ��ʶ�Ƿ�������ָ�����ı���
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
     * ��ȡ�ڵ㼯��
     * @param node �ڵ㡣
     * @return �ڵ㼯��
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
     * ���ۺ����ڵ�����ı�������λ��
     * @param text �ı���
     * @param tree ����
     * @param nodes �ڵ㼯��
     * @return ��ʶ�Ƿ�������ָ�����ı���
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
     * �жϻ����Ƿ��Ѿ������ͷ��б��ˡ�
     * @param cache ���档
     * @param willReleaseCaches �ͷ��б�
     * @return ��ʶ�Ƿ��Ѿ������ͷ��б��ˡ�
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
     * ��ʶ�Ƿ�ѡ��ڵ�ʱҲͬʱ���ѡ���
     */
    protected volatile boolean m_isSyncChoice = false;

    /**
     * ��ʶ��ѡ��ڵ㴦�ڱ༭״̬ʱ������ڵ���ǰ��ѡ�����Ƿ�����ֹͣ�༭��
     */
    protected volatile boolean m_isStopEditingWhenClickChoice = true;

    /**
     * �������༭�Ľڵ㣬��ʱ���ԡ�
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
     * ���췽����
     * @param model �ۺ�������ģ�͡�
     * @since T3 V1.1
     */
    public ComplexTree(ComplexTreeModel model) {
        /* ���ø���Ĺ��췽�� */
        super(model);

        /* ��װ��Ⱦ���ͱ༭�������Ҳ������û��ı�������� */
        super.setCellRenderer(new ComplexTreeCellRenderer());
        super.setCellEditor(new ComplexTreeCellEditor(this));

        /* ���ڴ��ڸ�ϸ���ȵĿ��ƣ����������ۺ�������ΪĬ������༭ */
        setEditable(true);
        setShowsRootHandles(true);
        setToggleClickCount(0);

        /* ��Ը��ڵ�ToolTip��֧�֣�TODO ע�⣺������ܴ�����ʾЧ�ʺ��ڴ氲ȫ������ */
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
     * ��ȡ�ۺ���ģ�͡�
     * @return �ۺ���ģ�͡�
     * @since T3 V1.1
     */
    public ComplexTreeModel getComplexTreeModel() {
        return (ComplexTreeModel) treeModel;
    }

    /**
     * ��ȡ�ֶ� isSyncChoice ��ֵ����ʶ�Ƿ�����ͬ��ѡ��������������ڵ�ķ�ѡ�������Ҳ���ѡ����ϣ��Ƚ��ʺϷǱ༭���ۺ�����ͬ��ѡ��ȱʡ������
     * @return �ֶ� isSyncChoice ��ֵ��
     * @since T3 V1.1
     */
    public boolean isSyncChoice() {
        return m_isSyncChoice;
    }

    /**
     * �����ֶ� isSyncChoice ��ֵ����ʶ�Ƿ�����ͬ��ѡ��������������ڵ�ķ�ѡ�������Ҳ���ѡ����ϣ��Ƚ��ʺϷǱ༭���ۺ�����ͬ��ѡ��ȱʡ������
     * @param isSyncChoice �ֶ� isSyncChoice ��ֵ��
     * @since T3 V1.1
     */
    public void setSyncChoice(boolean isSyncChoice) {
        m_isSyncChoice = isSyncChoice;
    }

    /**
     * �жϵ�ѡ��ڵ㴦�ڱ༭״̬ʱ������ڵ���ǰ��ѡ�����Ƿ�����ֹͣ�༭��
     * @return Ϊtrue��ʾ����ֹͣ�༭��Ϊfalse��ʾ������ֹͣ�༭��
     * @since T3 V1.1
     */
    public boolean isStopEditingWhenClickChoice() {
        return m_isStopEditingWhenClickChoice;
    }

    /**
     * ���õ�ѡ��ڵ㴦�ڱ༭״̬ʱ������ڵ���ǰ��ѡ�����Ƿ�����ֹͣ�༭��
     * @param isStopEditingWhenClickChoice Ϊtrue��ʾ����ֹͣ�༭��Ϊfalse��ʾ������ֹͣ�༭��
     * @since T3 V1.1
     */
    public void setStopEditingWhenClickChoice(boolean isStopEditingWhenClickChoice) {
        m_isStopEditingWhenClickChoice = isStopEditingWhenClickChoice;
    }

    /**
     * �����ֶ� isEnabledChoice ��ֵ����ʶ�Ƿ�����ѡ�����������������ϵĽڵ�ǰ�治�����ѡ���ȱʡΪ����
     * @param isEnabledChoice �ֶ� isEnabledChoice ��ֵ��
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
     * չ����ǰ���б���ѡ�Ľڵ����ڵ�·����
     * @since T3 V1.1
     */
    public void expand() {
        expand(getComplexTreeModel().getChosenPaths());
    }

    /**
     * չ��ָ��·������ע�⣺·���������ÿ��ֵ���ǽڵ���󣬶��ǽڵ����������userObject����
     * @param paths ·������
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
     * չ��ָ��·����ע�⣺·���������ÿ��ֵ���ǽڵ���󣬶��ǽڵ����������userObject����
     * @param path ·����
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
     * ϸ���ȵĿ��ƽڵ㹤����ʾ��
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
     * ϸ���ȿ��ƽڵ��Ƿ�ɱ༭��
     * @see javax.swing.JTree#isPathEditable(javax.swing.tree.TreePath)
     */
    public boolean isPathEditable(TreePath path) {
        if (super.isPathEditable(path)) {
            /* ���漴�����༭�Ľڵ� */
            m_willEditNode = (ComplexTreeNode) path.getLastPathComponent();
            return m_willEditNode.isEditable();

        } else {
            return false;
        }
    }

    /**
     * @see javax.swing.JTree#setModel(javax.swing.tree.TreeModel)
     * @throws IllegalArgumentException ���õ���ģ�Ͳ���һ��ComplexTreeModel����׳����쳣��
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
     * ��ֹ�û����ñ��ĵ�Ԫ��༭����
     * @see javax.swing.JTree#setCellEditor(javax.swing.tree.TreeCellEditor)
     * @throws IllegalArgumentException �û����ô�API���׳����쳣��
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
        /* �ݲ���ʵ�� */
    }

    /**
     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#nodeLoaded(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
     */
    public void nodeLoaded(ComplexTreeModelEvent evt) {
        /* �ݲ���ʵ�� */
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
     * �ͷŻ����б�
     * @param caches �����б�
     * @param parentCacheNode ������ڵ㡣
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
     * ���ѡ�еĶ���������ʵ�֡�
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
     * ��ȡѡ���ķ�Χ��
     * @param row �С�
     * @return ѡ���ķ�Χ��
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
 * <p>�ļ����ƣ�ComplexTree.java</p>
 * <p>�����������ۺ��������ӿڡ�</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-9-23</p>
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
interface IConstants extends IBaseConstants {

    /*------------------------------------- Public Static Final Field -------------------------------------*/

    /**
     * ״̬------׼����
     */
    public static final int STATE_READY = 0;

    /**
     * ״̬------�����С�
     */
    public static final int STATE_LOADING = 1;

    /**
     * ״̬------�����롣
     */
    public static final int STATE_LOADED = 2;

    /*------------------------------------- Public Abstract Method -------------------------------------*/

    /* Add Public Abstract Method */

}

/**
 * <p>�ļ����ƣ�ComplexTree.java</p>
 * <p>�����������ۺ��������ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-9-23</p>
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
     * ͨ��key����ȡ�б��ƵĹ��ʻ���Ϣ��
     * @param i18nKey ���ʻ���Ϣ�ؼ��֡�
     * @return ���ʻ���Ϣ��
     */
    protected static String getI18nMessage(String i18nKey) {
        return BaseUtilities.getI18nMessage("com.apollo.swing.component.complextree.resource.ComplexTree", i18nKey);
    }

    /**
     * ��ȡͼ�ꡣ
     * @param iconFilename ͼ���ļ�����
     * @return ͼ�ꡣ
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
     * ���췽����
     */
    private Utilities() {
        /* ��ֹ���ⲿʵ�������� */
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
 * <p>�ļ����ƣ�ComplexTree.java</p>
 * <p>����������ѡ���ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2009-11-27</p>
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
     * ѡ��ͼ�꣬��������Ҫ�����ʶѡ���Ƿ��ڲ�ȷ������ѡ��״̬��
     */
    IconWrapper m_choiceIcon = IconWrapper.createCheckBoxIconWrapper();

    /**
     * ѡ��ť��
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
     * ���췽����
     */
    Choice() {
        m_choiceBtn.setOpaque(false);
    }

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * �ж��Ƿ�Ϊ��ѡ��״̬��
     * @return ��ʶ�Ƿ�Ϊ��ѡ��״̬��
     */
    public boolean isSelected() {
        return m_choiceBtn.isSelected() && !m_choiceIcon.isIndetermined();
    }

    /**
     * ����Ϊ��ѡ��״̬��
     */
    public void setSelected() {
        m_choiceBtn.setSelected(true);
        m_choiceIcon.setIndetermined(false);
    }

    /**
     * �ж��Ƿ�Ϊδѡ��״̬��
     * @return ��ʶ�Ƿ�Ϊδѡ��״̬��
     */
    public boolean isDeselected() {
        return !m_choiceBtn.isSelected() && !m_choiceIcon.isIndetermined();
    }

    /**
     * ����Ϊδѡ��״̬��
     */
    public void setDeselected() {
        m_choiceBtn.setSelected(false);
        m_choiceIcon.setIndetermined(false);
    }

    /**
     * �ж��Ƿ�Ϊ��ȷ��״̬��
     * @return ��ʶ�Ƿ�Ϊ��ȷ��״̬��
     */
    public boolean isIndetermined() {
        return m_choiceIcon.isIndetermined();
    }

    /**
     * ����Ϊ��ȷ��״̬��
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
 * <p>�ļ����ƣ�ComplexTree.java</p>
 * <p>�ļ��������ۺ�����Ⱦ���ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004</p>
 * <p>������˾��������</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2007-11-16</p>
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
     * ѡ��
     */
    protected Choice m_choice = new Choice();

    /**
     * ѡ��ڵ���塣
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
     * ���췽����
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

        /* ����ѡ��ڵ㣨ѡ��ڵ���Ҫ�����е���Ⱦ���ǰ�����һ��ѡ��� */
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
 * <p>�ļ����ƣ�ComplexTree.java</p>
 * <p>�ļ��������ۺ����༭���ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004</p>
 * <p>������˾��������</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2007-11-16</p>
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
     * �༭�ڵ㵥Ԫ��༭��������
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
     * ����������------����������ʱ���ѡ������»�����������ж��Ƿ���Ҫ��ֹ�༭������
     * ע�⣺ˢ�º������ֹͣ�༭��ͬ�����ﲻ�����Ƴ����˼������������޷�����ˢ�£���Ϊ����ĵ���������̵����˳��༭״̬��
     * TODO ����Ҳ�����һ��С��BUG�����������ˢ�¶����ڽ��У�������Խ������Ӱ�졣 
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
     * �ۺ����������á�
     */
    protected ComplexTree m_complexTree;

    /**
     * ѡ��
     */
    protected Choice m_choice = new Choice();

    /**
     * ѡ��ڵ���塣
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
     * ���췽����
     * @param complexTree �ۺ�����
     */
    public ComplexTreeCellEditor(ComplexTree complexTree) {
        /* ���ۺ�����������Ⱦ������ʼ���༭��������Ҫ�����ۺ������췽������������Ⱦ���������ñ༭�� */
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

        if (m_complexTree.m_willEditNode.isEditable()) { //�������༭�Ľڵ�����༭
            /* ��ȡ����Ҫ�༭�ڵ�ı༭�� */
            TreeCellEditor editor = m_complexTree.m_willEditNode.getEditor();

            /* ���ж�ί�и��༭�� */
            if (editor == null) { //�������༭�Ľڵ�û�����ñ༭��������ȱʡ�༭���ж��Ƿ�����༭
                return super.isCellEditable(evt);

            } else { //�������༭�Ľڵ������˱༭��
                return editor.isCellEditable(evt);
            }

        } else { //�������༭�Ľڵ㲻����༭
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
        /* ����༭��� */
        Component ec = null;

        /* ��ȡ�༭�ڵ� */
        ComplexTreeNode node = (ComplexTreeNode) value;

        /* ��װ�༭�ڵ� */
        installEditingNode(node);

        /* ��ȡ�༭��� */
        TreeCellEditor nodeEditor = node.getEditor();
        if (nodeEditor == null) { //�༭�ڵ�û�����ýڵ�༭��
            ec = super.getTreeCellEditorComponent(tree, value, isSelected, isExpanded, isLeaf, rowIndex);

        } else { //�༭�ڵ������˽ڵ�༭��
            ec = nodeEditor.getTreeCellEditorComponent(tree, value, isSelected, isExpanded, isLeaf, rowIndex);
        }

        /* ���ѡ��ڵ���� */
        m_choiceNodeView.removeAll();

        /* ����ѡ��ڵ㣨ѡ��ڵ���Ҫ�����еı༭���ǰ�����һ����ѡ�� */
        if (m_complexTree.getComplexTreeModel().m_isEnabledChoice && node instanceof ComplexTreeChoiceNode) { //��ѡ��ڵ�
            /* ��ȡѡ��ڵ� */
            ComplexTreeChoiceNode choiceNode = (ComplexTreeChoiceNode) node;

            if (choiceNode.isSelected()) {
                m_choice.setSelected();

            } else if (choiceNode.isDeselected()) {
                m_choice.setDeselected();

            } else {
                m_choice.setIndetermined();
            }

            /* ��Ӹ�ѡ��ѡ��ڵ���� */
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

        /* �����Ⱦ�����ѡ��ڵ���� */
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

        /* ������Ⱦ��� */
        return m_choiceNodeView;
    }

    /**
     * ���ص�Ԫ��༭ֵʱ���ÿ����û����õı༭���Ƿ�Ӧ���ˣ�ֻҪ�û������˱༭������ί�и�����
     * @see javax.swing.tree.DefaultTreeCellEditor#getCellEditorValue()
     */
    public Object getCellEditorValue() {
        /* ��ȡ����Ҫ�༭�ڵ�ı༭�� */
        TreeCellEditor editor = m_complexTree.m_willEditNode.getEditor();

        /* ���ж�ί�и��༭�� */
        if (editor == null) { //�������༭�Ľڵ�û�����ñ༭��������ȱʡ�༭���ж��Ƿ�����༭
            return super.getCellEditorValue();

        } else { //�������༭�Ľڵ������˱༭��
            return editor.getCellEditorValue();
        }
    }

    /**
     * ֹͣ��Ԫ��༭ʱ��Ҫ�����û����õı༭���Ƿ�Ӧ���ˣ�û�б�Ӧ�þͲ��þ�ί�и����ˡ�
     * @see javax.swing.tree.DefaultTreeCellEditor#stopCellEditing()
     */
    public boolean stopCellEditing() {
        /* ��ȡ����Ҫ�༭�ڵ�ı༭�� */
        TreeCellEditor editor = m_complexTree.m_willEditNode.getEditor();

        /* �����жϽ�� */
        boolean isStopCellEditing;

        /* ���ж�ί�и��༭�� */
        if (editor == null) { //�������༭�Ľڵ�û�����ñ༭��
            isStopCellEditing = super.stopCellEditing(); //����ȱʡ�༭���ж��Ƿ�����༭

        } else { //�������༭�Ľڵ������˱༭��
            isStopCellEditing = editor.stopCellEditing();
        }

        /* ж�ر༭�ڵ� */
        uninstallEditingNode(m_complexTree.m_willEditNode);

        /* �����жϽ�� */
        return isStopCellEditing;
    }

    /**
     * ȡ����Ԫ��༭ʱ��Ҫ�����û����õı༭���Ƿ�Ӧ���ˣ�û�б�Ӧ�þͲ��þ�ί�и����ˡ�
     * @see javax.swing.tree.DefaultTreeCellEditor#cancelCellEditing()
     */
    public void cancelCellEditing() {
        /* ��ȡ����Ҫ�༭�ڵ�ı༭�� */
        TreeCellEditor editor = m_complexTree.m_willEditNode.getEditor();

        /* ���ж�ί�и��༭�� */
        if (editor == null) { //�������༭�Ľڵ�û�����ñ༭��
            super.cancelCellEditing(); //����ȱʡ�༭���ж��Ƿ�����༭

        } else { //�������༭�Ľڵ������˱༭��
            editor.cancelCellEditing();
        }

        /* ж�ر༭�ڵ� */
        uninstallEditingNode(m_complexTree.m_willEditNode);
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * ��װ�༭�ڵ㡣
     * @param node �༭�ڵ㡣
     */
    protected void installEditingNode(ComplexTreeNode node) {
        /* ��װ�༭�ڵ����õ�ȱʡ��Ⱦ�� */
        TreeCellRenderer nodeRenderer = node.getRenderer();
        if (nodeRenderer instanceof DefaultTreeCellRenderer) { //�༭�ڵ����õ���Ⱦ����һ��ȱʡ������Ԫ����Ⱦ��
            renderer = (DefaultTreeCellRenderer) nodeRenderer;
        }

        /* ��װ�༭�ڵ�ĵ�Ԫ��༭������ */
        TreeCellEditor nodeEditor = node.getEditor(); //��ȡ�ڵ�༭��
        if (nodeEditor != null) { //�༭�ڵ������˽ڵ�༭��
            nodeEditor.addCellEditorListener(m_cellEditorListener); //��ӱ༭�ڵ�ĵ�Ԫ��༭������
        }
    }

    /**
     * ж�ر༭�ڵ㡣
     * @param node �༭�ڵ㡣
     */
    protected void uninstallEditingNode(ComplexTreeNode node) {
        /* ж�ر༭�ڵ����õ�ȱʡ��Ⱦ�� */
        renderer = (DefaultTreeCellRenderer) m_complexTree.getCellRenderer();
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
