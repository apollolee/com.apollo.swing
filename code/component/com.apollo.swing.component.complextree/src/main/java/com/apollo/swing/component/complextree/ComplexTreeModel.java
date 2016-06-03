/*
 * 此代码创建于 2007-9-12 下午05:38:21
 */
package com.apollo.swing.component.complextree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingWorker;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * <p>文件名称：ComplexTreeModel.java</p>
 * <p>文件描述：综合树模型类，综合树的核心逻辑都是在此类实现的。</p>
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
public class ComplexTreeModel extends DefaultTreeModel implements IConstants {

    /*------------------------------------- Public (Static) Inner Class -------------------------------------*/

    /**
     * 最大选择数越界异常类，用于封装选择状态出错时的一种特殊异常。
     * @since T3 V1.1
     */
    public static class TOutOfMaxChosenCountException extends RuntimeException {

        /**
         * 当前选中个数。
         */
        private volatile long m_currentChosenCount;

        /**
         * 最大选中个数。
         */
        private volatile long m_maxChosenCount;

        /**
         * 构造方法。
         * @param currentChosenCount 当前选中个数。
         * @param maxChosenCount 最大选中个数。
         * @since T3 V1.1
         */
        public TOutOfMaxChosenCountException(long currentChosenCount, long maxChosenCount) {
            super("CurrentChosenCount = " + currentChosenCount + " , MaxChosenCount = " + maxChosenCount);
            m_currentChosenCount = currentChosenCount;
            m_maxChosenCount = maxChosenCount;
        }

        /**
         * 获取字段 currentChosenCount 的值，当前选中个数。
         * @return 字段 currentChosenCount 的值。
         * @since T3 V1.1
         */
        public long getCurrentChosenCount() {
            return m_currentChosenCount;
        }

        /**
         * 获取字段 maxchosenCount 的值，最大选中个数。
         * @return 字段 maxchosenCount 的值。
         * @since T3 V1.1
         */
        public long getMaxchosenCount() {
            return m_maxChosenCount;
        }

    }

    /*------------------------------------- Protected (Static) Inner Class -------------------------------------*/

    /**
     * 载入工作线程类。
     */
    protected class TLoadWorker extends SwingWorker<Object, Object> {

        /**
         * 父缓存节点。
         */
        protected ComplexTreeCacheNode m_parent;

        /**
         * 构造方法。
         * @param parent 父缓存节点。
         */
        public TLoadWorker(ComplexTreeCacheNode parent) {
            m_parent = parent;
        }

        /**
         * @see javax.swing.SwingWorker#doInBackground()
         */
        @Override
        protected Object doInBackground() throws Exception {
            try {
                return m_dataSource.load(m_parent);

            } catch (ComplexTreeException ex) {
                return ex;
            }
        }

        /**
         * @see javax.swing.SwingWorker#done()
         */
        @Override
        protected void done() {
            Object value = null;

            try {
                value = get();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (value instanceof ComplexTreeException) {
                releaseCache(m_parent);
                fireNodeLoadFailed(m_parent, (ComplexTreeException) value);

            } else {
                m_parent.loaded(value == null ? new ComplexTreeNode[0] : (ComplexTreeNode[]) value);
                update(m_parent);
                reload(m_parent);
                fireNodeLoaded(m_parent);
            }
        }

    }

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
     * 根据子选择节点选择状态判断父选择节点是否为已选择状态。
     * @param choiceNode 父选择节点。
     * @return 标识父选择节点是否为已选择状态。
     */
    protected static boolean isSelected(ComplexTreeChoiceNode choiceNode) {
        if (choiceNode.isLeaf() || (choiceNode instanceof ComplexTreeCacheNode && !((ComplexTreeCacheNode) choiceNode).isLoaded())) {
            return choiceNode.isSelected();
        }

        for (int i = 0, size = choiceNode.getChildCount(); i < size; i++) {
            TreeNode child = choiceNode.getChildAt(i);
            if (child instanceof ComplexTreeChoiceNode && !((ComplexTreeChoiceNode) child).isSelected()) {
                return false;
            }
        }

        return true;
    }

    /**
     * 根据子选择节点选择状态判断父选择节点是否为取消选择状态。
     * @param choiceNode 父选择节点。
     * @return 标识父选择节点是否为取消选择状态。
     */
    protected static boolean isDeselected(ComplexTreeChoiceNode choiceNode) {
        if (choiceNode.isLeaf() || (choiceNode instanceof ComplexTreeCacheNode && !((ComplexTreeCacheNode) choiceNode).isLoaded())) {
            return choiceNode.isDeselected();
        }

        for (int i = 0, size = choiceNode.getChildCount(); i < size; i++) {
            TreeNode child = choiceNode.getChildAt(i);
            if (child instanceof ComplexTreeChoiceNode && !((ComplexTreeChoiceNode) child).isDeselected()) {
                return false;
            }
        }

        return true;
    }

    /*------------------------------------- Friendly Static Method -------------------------------------*/

    /* Add Friendly Static Method */

    /*------------------------------------- Private Static Method -------------------------------------*/

    /* Add Private Static Method */

    /*------------------------------------- Public Field -------------------------------------*/

    /* Add Public Field */

    /*------------------------------------- Protected Field -------------------------------------*/

    /**
     * 数据源。
     */
    protected IComplexTreeDataSource m_dataSource;

    /**
     * 缓存大小。
     */
    protected volatile int m_cacheSize;

    /**
     * 标识是否允许选择。
     */
    protected volatile boolean m_isEnabledChoice;

    /**
     * 最大选中节点的个数。
     */
    protected volatile long m_maxChosenCount = Long.MAX_VALUE;

    /**
     * 已选中的路径集。
     */
    protected List m_chosenPaths = new ArrayList();

    /**
     * 缓存集。
     */
    protected List m_caches = new ArrayList();

    /**
     * 标识是否允许载入。
     */
    protected volatile boolean m_isEnabledLoad = true;

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 构造方法。
     * @param root 根节点，可以是选择节点或缓存节点。
     * @since T3 V1.1
     */
    public ComplexTreeModel(ComplexTreeNode root) {
        this(root, null);
    }

    /**
     * 构造方法。
     * @param root 根节点，可以是选择节点或缓存节点。
     * @param dataSource 数据源，如果模型层中不带有任何缓存节点，则数据源可以设置为null。
     * @since T3 V1.1
     */
    public ComplexTreeModel(ComplexTreeNode root, IComplexTreeDataSource dataSource) {
        this(root, dataSource, 100);
    }

    /**
     * 构造方法。
     * @param root 根节点，可以是选择节点或缓存节点。
     * @param dataSource 数据源，如果模型层中不带有任何缓存节点，则数据源可以设置为null。
     * @param cacheSize 缓存大小，即同时处于已载入状态的缓存节点的个数，注意：模型会尽量去满足这个限制值，但不保证一定能满足，因此有些情况下此值会失效。
     * @since T3 V1.1
     */
    public ComplexTreeModel(ComplexTreeNode root, IComplexTreeDataSource dataSource, int cacheSize) {
        this(root, dataSource, cacheSize, true);
    }

    /**
     * 构造方法。
     * @param root 根节点，可以是选择节点或缓存节点。
     * @param dataSource 数据源，如果模型层中不带有任何缓存节点，则数据源可以设置为null。
     * @param cacheSize 缓存大小，即同时处于已载入状态的缓存节点的个数，注意：模型会尽量去满足这个限制值，但不保证一定能满足，因此有些情况下此值会失效。
     * @param isEnabledChoice 标识是否允许选择，如果不允许则界面上的节点前面不会带有选择框，缺省为允许。
     * @since T3 V1.1
     */
    public ComplexTreeModel(ComplexTreeNode root, IComplexTreeDataSource dataSource, int cacheSize, boolean isEnabledChoice) {
        super(root);

        m_dataSource = dataSource;
        m_cacheSize = cacheSize;
        m_isEnabledChoice = isEnabledChoice;
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * 获取字段 dataSource 的值，数据源，如果模型层中不带有任何缓存节点，则数据源可以设置为null。
     * @return 字段 dataSource 的值。
     * @since T3 V1.1
     */
    public IComplexTreeDataSource getDataSource() {
        return m_dataSource;
    }

    /**
     * 设置字段 dataSource 的值，数据源，如果模型层中不带有任何缓存节点，则数据源可以设置为null。
     * @param dataSource 字段 dataSource 的值。
     * @since T3 V1.1
     */
    public void setDataSource(IComplexTreeDataSource dataSource) {
        m_dataSource = dataSource;
    }

    /**
     * 获取字段 cacheSize 的值，缓存大小，即同时处于已载入状态的缓存节点的个数，注意：模型会尽量去满足这个限制值，但不保证一定能满足，因此有些情况下此值会失效。
     * @return 字段 cacheSize 的值。
     * @since T3 V1.1
     */
    public int getCacheSize() {
        return m_cacheSize;
    }

    /**
     * 设置字段 cacheSize 的值，缓存大小，即同时处于已载入状态的缓存节点的个数，注意：模型会尽量去满足这个限制值，但不保证一定能满足，因此有些情况下此值会失效。
     * @param cacheSize 字段 cacheSize 的值。
     * @since T3 V1.1
     */
    public void setCacheSize(int cacheSize) {
        m_cacheSize = cacheSize;
    }

    /**
     * 获取字段 isEnabledChoice 的值，标识是否允许选择，如果不允许则界面上的节点前面不会带有选择框，缺省为允许。
     * @return 字段 isEnabledChoice 的值。
     * @since T3 V1.1
     */
    public boolean isEnabledChoice() {
        return m_isEnabledChoice;
    }

    /**
     * 获取字段 maxChosenCount 的值，标识是否允许选择，如果不允许则界面上的节点前面不会带有选择框，缺省为允许。
     * @return 字段 maxChosenCount 的值。
     * @since T3 V1.1
     */
    public long getMaxChosenCount() {
        return m_maxChosenCount;
    }

    /**
     * 设置字段 maxChosenCount 的值，最大选中数上限。
     * @param maxChosenCount 字段 maxChosenCount 的值。
     * @since T3 V1.1
     */
    public void setMaxChosenCount(long maxChosenCount) {
        m_maxChosenCount = maxChosenCount;
    }

    /**
     * 添加综合树模型监听器。
     * @param listener 综合树模型监听器。
     * @since T3 V1.1
     */
    public void addComplexTreeModelListener(IComplexTreeModelListener listener) {
        listenerList.add(IComplexTreeModelListener.class, listener);
    }

    /**
     * 移除综合树模型监听器。
     * @param listener 综合树模型监听器。
     * @since T3 V1.1
     */
    public void removeComplexTreeModelListener(IComplexTreeModelListener listener) {
        listenerList.remove(IComplexTreeModelListener.class, listener);
    }

    /**
     * 获取所有综合树模型监听器。
     * @return 所有综合树模型监听器。
     * @since T3 V1.1
     */
    public IComplexTreeModelListener[] getComplexTreeModelListener() {
        return (IComplexTreeModelListener[]) listenerList.getListeners(IComplexTreeModelListener.class);
    }

    /**
     * 释放所有缓存，这个是手动释放缓存的方法，一般情况下不需要应用自行去调用。
     * @since T3 V1.1
     */
    public void releaseCache() {
        releaseCache((ComplexTreeNode) root);
    }

    /**
     * 释放指定节点下的所有缓存，这个是手动释放缓存的方法，一般情况下不需要应用自行去调用。
     * @param node 指定节点。
     * @since T3 V1.1
     */
    public void releaseCache(ComplexTreeNode node) {
        if (node instanceof ComplexTreeCacheNode) {
            ComplexTreeCacheNode cacheNode = (ComplexTreeCacheNode) node;
            m_caches.remove(cacheNode);
            if (cacheNode.ready()) {
                reloadForDisabledLoad(node);
            }

        } else {
            for (int i = 0, size = node.getChildCount(); i < size; i++) {
                releaseCache((ComplexTreeNode) node.getChildAt(i));
            }
        }
    }

    /**
     * 获取已选中的路径集，注意：路径里包含的每个值不是节点对象，而是节点对象所带的userObject对象。
     * @return 已选中的路径集。
     * @since T3 V1.1
     */
    public TreePath[] getChosenPaths() {
        int currentChosenCount = m_chosenPaths.size();
        if (currentChosenCount > m_maxChosenCount) {
            throw new TOutOfMaxChosenCountException(currentChosenCount, m_maxChosenCount);
        }

        validate();
        syncChosenPaths();
        return m_isEnabledChoice ? (TreePath[]) m_chosenPaths.toArray(new TreePath[m_chosenPaths.size()]) : new TreePath[0];
    }

    /**
     * 设置已选中的路径集，注意：路径里包含的每个值不是节点对象，而是节点对象所带的userObject对象。
     * @param chosenPaths 已选中的路径集。
     * @since T3 V1.1
     */
    public void setChosenPaths(TreePath[] chosenPaths) {
        if (!m_isEnabledChoice) {
            return;
        }

        int chosenCount = chosenPaths == null ? 0 : chosenPaths.length;
        if (chosenCount > m_maxChosenCount) {
            throw new TOutOfMaxChosenCountException(chosenCount, m_maxChosenCount);
        }

        clearChosenPaths();

        if (chosenPaths == null || chosenPaths.length == 0) {
            return;
        }

        m_chosenPaths.addAll(Arrays.asList(chosenPaths));
        refresh();

        chosenPaths = getChosenPaths();
        if (chosenPaths.length != 0) {
            fireNodeChoiceChanged(chosenPaths, true);
        }
    }

    /**
     * 清除所有已选中的路径。
     * @since T3 V1.1
     */
    public void clearChosenPaths() {
        if (!m_isEnabledChoice) {
            return;
        }

        if (!m_chosenPaths.isEmpty()) {
            TreePath[] chosenPaths = (TreePath[]) m_chosenPaths.toArray(new TreePath[m_chosenPaths.size()]);
            deselect((ComplexTreeChoiceNode) root, true, true, false);
            fireNodeChoiceChanged(chosenPaths, false);
        }
    }

    /**
     * 点击指定节点，节点的选中状态会随之切换。
     * @param choiceNode 选择节点。
     * @since T3 V1.1
     */
    public void doClick(ComplexTreeChoiceNode choiceNode) {
        if (!m_isEnabledChoice) {
            return;
        }

        if (choiceNode.isDeselected()) {
            select(choiceNode);

        } else {
            deselect(choiceNode);
        }
    }

    /**
     * 选择指定的节点。
     * @param choiceNode 选择节点。
     * @since T3 V1.1
     */
    public void select(ComplexTreeChoiceNode choiceNode) {
        select(choiceNode, true, true, true);
    }

    /**
     * 取消选择指定的节点。
     * @param choiceNode 选择节点。
     * @since T3 V1.1
     */
    public void deselect(ComplexTreeChoiceNode choiceNode) {
        deselect(choiceNode, true, true, true);
    }

    /**
     * 更新选中节点，主要是为了保持选中状态的一致性，此方法相对来说比较耗时，所以最好少用，而应该多靠应用来保证节点选中状态的一致性。
     * @param parent 父选择节点。
     * @since T3 V1.1
     */
    public void update(ComplexTreeChoiceNode parent) {
        if (parent.isSelected()) {
            for (int i = 0, size = parent.getChildCount(); i < size; i++) {
                ComplexTreeNode child = (ComplexTreeNode) parent.getChildAt(i);
                if (child instanceof ComplexTreeChoiceNode) {
                    select((ComplexTreeChoiceNode) child, false, false, false);
                }
            }

        } else if (parent.isDeselected()) {
            for (int i = 0, size = parent.getChildCount(); i < size; i++) {
                ComplexTreeNode child = (ComplexTreeNode) parent.getChildAt(i);
                if (child instanceof ComplexTreeChoiceNode) {
                    deselect((ComplexTreeChoiceNode) child, false, false, false);
                }
            }

        } else {
            refresh(parent);
            syncChosenPaths(parent);
        }
    }

    /*------------------------------------- DefaultTreeModel Public Method -------------------------------------*/

    /**
     * @see javax.swing.tree.DefaultTreeModel#setRoot(javax.swing.tree.TreeNode)
     */
    public void setRoot(TreeNode root) {
        clearChosenPaths();
        super.setRoot(root);
    }

    /**
     * @see javax.swing.tree.DefaultTreeModel#getChild(java.lang.Object, int)
     */
    public Object getChild(Object parent, int index) {
        if (parent instanceof ComplexTreeCacheNode) {
            ComplexTreeCacheNode cacheNode = (ComplexTreeCacheNode) parent;

            synchronized (cacheNode) {
                if (cacheNode.isReady()) {
                    cacheNode.loading();
                    fireNodeWillLoad(cacheNode);
                    new TLoadWorker(cacheNode).execute();
                    addCache(cacheNode);
                }
            }
        }

        return super.getChild(parent, index);
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * 同步已选中的路径集。
     */
    protected void syncChosenPaths() {
        syncChosenPaths((ComplexTreeChoiceNode) root);
    }

    /**
     * 同步已选中的路径集。
     * @param choiceNode 选择节点。
     */
    protected void syncChosenPaths(ComplexTreeChoiceNode choiceNode) {
        TreePath chosenPath = choiceNode.getChosenPath();
        if (m_chosenPaths.contains(chosenPath)) {
            m_chosenPaths.set(m_chosenPaths.indexOf(chosenPath), chosenPath);
        }

        if (m_chosenPaths.isEmpty() || choiceNode.isLeaf() || (choiceNode instanceof ComplexTreeCacheNode && !((ComplexTreeCacheNode) choiceNode).isLoaded())) {
            return;
        }

        for (int i = 0, size = choiceNode.getChildCount(); i < size; i++) {
            TreeNode child = choiceNode.getChildAt(i);
            if (child instanceof ComplexTreeChoiceNode) {
                syncChosenPaths((ComplexTreeChoiceNode) child);
            }
        }
    }

    /**
     * 验证已选中的路径集是否合法。
     */
    protected void validate() {
        for (int i = m_chosenPaths.size() - 1; i >= 0; i--) {
            if (!validate((TreePath) m_chosenPaths.get(i))) {
                m_chosenPaths.remove(i);
            }
        }
    }

    /**
     * 验证已选中的路径是否合法。
     * @param chosenPath 已选中的路径。
     * @return 标识已选中的路径是否合法。
     */
    protected boolean validate(TreePath chosenPath) {
        ComplexTreeChoiceNode node = (ComplexTreeChoiceNode) root;
        return chosenPath.getPathComponent(0).equals(node.getUserObject()) ? validate(chosenPath, node) : false;
    }

    /**
     * 验证已选中的路径是否合法。
     * @param chosenPath 已选中的路径。
     * @param node 节点。
     * @return 标识已选中的路径是否合法。
     */
    protected boolean validate(TreePath chosenPath, ComplexTreeChoiceNode node) {
        int level = node.getLevel();

        if (level + 1 == chosenPath.getPathCount() || (node instanceof ComplexTreeCacheNode && !((ComplexTreeCacheNode) node).isLoaded())) {
            return true;
        }

        Object childPathComponent = chosenPath.getPathComponent(level + 1);
        for (int i = 0, size = node.getChildCount(); i < size; i++) {
            ComplexTreeChoiceNode child = (ComplexTreeChoiceNode) node.getChildAt(i);
            if (childPathComponent.equals(child.getUserObject())) {
                return validate(chosenPath, child);
            }
        }

        return false;
    }

    /**
     * 发送节点已改变通知（适用于禁止载入时）。
     * @param node 节点。
     */
    protected void reloadForDisabledLoad(TreeNode node) {
        m_isEnabledLoad = false;
        reload(node);
        m_isEnabledLoad = true;
    }

    /**
     * 获取最近的节点。
     * @param path 路径。
     * @return 最近的节点。
     */
    protected ComplexTreeNode getLastNode(TreePath path) {
        Object[] userObjects = path.getPath();
        if (userObjects.length == 0) {
            return null;
        }

        ComplexTreeNode lastNode = (ComplexTreeNode) root;
        if (!lastNode.getUserObject().equals(userObjects[0])) {
            return null;
        }

        for (int i = 1; i < userObjects.length; i++) {
            for (int j = 0, size = lastNode.getChildCount(); j < size; j++) {
                if (lastNode instanceof ComplexTreeCacheNode && !((ComplexTreeCacheNode) lastNode).isLoaded()) {
                    return lastNode;
                }

                ComplexTreeNode child = (ComplexTreeNode) lastNode.getChildAt(j);
                if (userObjects[i].equals(child.getUserObject())) {
                    lastNode = child;
                    break;
                }
            }
        }

        return lastNode;
    }

    /**
     * 根据指定的已选中路径获取选中节点。
     * @param chosenPath 指定的已选中路径。
     * @return 选中节点。
     */
    protected ComplexTreeChoiceNode getChosenNode(TreePath chosenPath) {
        if (!m_isEnabledChoice) {
            return null;
        }

        Object[] userObjects = chosenPath.getPath();
        if (userObjects.length == 0) {
            return null;
        }

        ComplexTreeChoiceNode choiceNode = (ComplexTreeChoiceNode) root;
        if (!choiceNode.getUserObject().equals(userObjects[0])) {
            return null;
        }

        for (int i = 1; i < userObjects.length; i++) {
            for (int j = 0, size = choiceNode.getChildCount(); j < size; j++) {
                if (choiceNode instanceof ComplexTreeCacheNode && !((ComplexTreeCacheNode) choiceNode).isLoaded()) {
                    choiceNode = null;
                    break;
                }

                TreeNode child = choiceNode.getChildAt(j);

                if (child instanceof ComplexTreeChoiceNode && userObjects[i].equals(((ComplexTreeChoiceNode) child).getUserObject())) {
                    choiceNode = (ComplexTreeChoiceNode) child;
                    break;
                }

                if (j + 1 == size) {
                    choiceNode = null;
                }
            }

            if (choiceNode == null) {
                return null;
            }
        }

        return choiceNode;
    }

    /**
     * 判断指定路径是否是已选中的路径。
     * @param path 指定路径。
     * @return 标识指定路径是否是已选中的路径。
     */
    protected boolean isChosenPath(TreePath path) {
        if (!m_isEnabledChoice) {
            return false;
        }

        for (int i = 0, size = m_chosenPaths.size(); i < size; i++) {
            if (path.isDescendant((TreePath) m_chosenPaths.get(i))) {
                return true;
            }
        }

        return false;
    }

    /**
     * 添加已选中的路径。
     * @param newChosenPath 已选中的路径。
     */
    protected void addChosenPath(TreePath newChosenPath) {
        if (!m_isEnabledChoice) {
            return;
        }

        for (int i = m_chosenPaths.size() - 1; i >= 0; i--) {
            TreePath chosenPath = (TreePath) m_chosenPaths.get(i);

            if (chosenPath.isDescendant(newChosenPath)) {
                return;
            }

            if (newChosenPath.isDescendant(chosenPath)) {
                m_chosenPaths.remove(i);
            }
        }

        m_chosenPaths.add(newChosenPath);
    }

    /**
     * 移除已选中的路径。
     * @param oldChosenPath 已选中的路径。
     */
    protected void removeChosenPath(TreePath oldChosenPath) {
        if (!m_isEnabledChoice) {
            return;
        }

        for (int i = m_chosenPaths.size() - 1; i >= 0; i--) {
            TreePath chosenPath = (TreePath) m_chosenPaths.get(i);

            if (oldChosenPath.isDescendant(chosenPath)) {
                m_chosenPaths.remove(i);
            }
        }

        TreePath lastChosenPath = getLastChosenPath(oldChosenPath);
        if (lastChosenPath != null) {
            m_chosenPaths.remove(lastChosenPath);

            ComplexTreeChoiceNode lastChosenNode = getChosenNode(lastChosenPath);
            ComplexTreeChoiceNode chosenPath = getChosenNode(oldChosenPath);

            while (chosenPath != lastChosenNode) {
                ComplexTreeChoiceNode parentChoiceNode = (ComplexTreeChoiceNode) chosenPath.getParent();

                for (int i = 0, size = parentChoiceNode.getChildCount(); i < size; i++) {
                    TreeNode childChoiceNode = parentChoiceNode.getChildAt(i);

                    if (childChoiceNode != chosenPath && childChoiceNode instanceof ComplexTreeChoiceNode) {
                        m_chosenPaths.add(((ComplexTreeChoiceNode) childChoiceNode).getChosenPath());
                    }
                }

                chosenPath = parentChoiceNode;
            }
        }
    }

    /**
     * 获取最近的选中路径。
     * @param path 路径。
     * @return 最近的选中路径。
     */
    protected TreePath getLastChosenPath(TreePath path) {
        if (!m_isEnabledChoice) {
            return null;
        }

        TreePath lastChosenPath = null;

        for (int i = 0, size = m_chosenPaths.size(); i < size; i++) {
            TreePath chosenPath = (TreePath) m_chosenPaths.get(i);

            if (chosenPath.isDescendant(path) && (lastChosenPath == null || chosenPath.getPathCount() > lastChosenPath.getPathCount())) {
                lastChosenPath = chosenPath;
            }
        }

        return lastChosenPath;
    }

    /**
     * 选择。
     * @param choiceNode 选择节点
     * @param hasRefreshChosenPath 标识是否需要刷新选中路径。
     * @param hasCheckParent 标识是否需要检查父节点。
     * @param hasNotify 标识是否需要发通知。
     */
    protected void select(ComplexTreeChoiceNode choiceNode, boolean hasRefreshChosenPath, boolean hasCheckParent, boolean hasNotify) {
        if (!m_isEnabledChoice || choiceNode == null || choiceNode.isSelected()) {
            return;
        }

        choiceNode.setSelected();
        TreePath chosenPath = choiceNode.getChosenPath();

        if (hasRefreshChosenPath) {
            addChosenPath(chosenPath);
        }

        if (hasCheckParent) {
            ComplexTreeChoiceNode parentChoiceNode = (ComplexTreeChoiceNode) choiceNode.getParent();
            if (parentChoiceNode != null) {
                if (isSelected(parentChoiceNode)) {
                    select(parentChoiceNode, true, true, false);

                } else {
                    indetermine(parentChoiceNode);
                }
            }
        }

        if (hasNotify) {
            fireNodeChoiceChanged(chosenPath, true);
        }

        if (choiceNode instanceof ComplexTreeCacheNode && !((ComplexTreeCacheNode) choiceNode).isLoaded()) {
            return;
        }

        for (int i = 0, size = choiceNode.getChildCount(); i < size; i++) {
            TreeNode child = choiceNode.getChildAt(i);

            if (child instanceof ComplexTreeChoiceNode) {
                select((ComplexTreeChoiceNode) child, false, false, false);
            }
        }
    }

    /**
     * 取消选择。
     * @param choiceNode 选择节点。
     * @param hasRefreshChosenPath 标识是否需要刷新选中路径。
     * @param hasCheckParent 标识是否需要检查父节点。
     * @param hasNotify 标识是否需要发通知。
     */
    protected void deselect(ComplexTreeChoiceNode choiceNode, boolean hasRefreshChosenPath, boolean hasCheckParent, boolean hasNotify) {
        if (!m_isEnabledChoice || choiceNode == null || choiceNode.isDeselected()) {
            return;
        }

        choiceNode.setDeselected();
        TreePath chosenPath = choiceNode.getChosenPath();

        if (hasRefreshChosenPath) {
            removeChosenPath(chosenPath);
        }

        if (hasCheckParent) {
            ComplexTreeChoiceNode parentChoiceNode = (ComplexTreeChoiceNode) choiceNode.getParent();
            if (parentChoiceNode != null) {
                if (isDeselected(parentChoiceNode)) {
                    deselect(parentChoiceNode, true, true, false);

                } else {
                    indetermine(parentChoiceNode);
                }
            }
        }

        if (hasNotify) {
            fireNodeChoiceChanged(chosenPath, false);
        }

        if (choiceNode instanceof ComplexTreeCacheNode && !((ComplexTreeCacheNode) choiceNode).isLoaded()) {
            return;
        }

        for (int i = 0, size = choiceNode.getChildCount(); i < size; i++) {
            TreeNode child = choiceNode.getChildAt(i);

            if (child instanceof ComplexTreeChoiceNode) {
                deselect((ComplexTreeChoiceNode) child, false, false, false);
            }
        }
    }

    /**
     * 不确定。
     * @param choiceNode 选择节点。
     */
    protected void indetermine(ComplexTreeChoiceNode choiceNode) {
        if (!m_isEnabledChoice || choiceNode == null || choiceNode.isIndetermined()) {
            return;
        }

        choiceNode.setIndetermined();
        indetermine((ComplexTreeChoiceNode) choiceNode.getParent());
    }

    /**
     * 刷新。
     */
    protected void refresh() {
        refresh((ComplexTreeChoiceNode) root);
    }

    /**
     * 刷新。
     * @param choiceNode 选择节点。
     */
    protected void refresh(ComplexTreeChoiceNode choiceNode) {
        if (!m_isEnabledChoice || choiceNode == null) {
            return;
        }

        if (m_chosenPaths.contains(choiceNode.getChosenPath())) {
            select(choiceNode, true, true, false);
            return;
        }

        if (m_chosenPaths.isEmpty() || choiceNode.isLeaf()) {
            deselect(choiceNode, true, true, false);
            return;
        }

        if (choiceNode instanceof ComplexTreeCacheNode && !((ComplexTreeCacheNode) choiceNode).isLoaded()) {
            if (isChosenPath(choiceNode.getChosenPath())) {
                indetermine(choiceNode);

            } else {
                deselect(choiceNode, true, true, false);
            }

            return;
        }

        boolean isDeselected = true;
        for (int i = 0, size = choiceNode.getChildCount(); i < size; i++) {
            TreeNode child = choiceNode.getChildAt(i);

            if (child instanceof ComplexTreeChoiceNode) {
                refresh((ComplexTreeChoiceNode) child);
                isDeselected = false;
            }
        }

        if (isDeselected) {
            deselect(choiceNode, true, true, false);
        }
    }

    /**
     * 发送节点选择已改变通知。
     * @param chosenPath 选中的路径。
     * @param isSelected 标识是选择还是取消选择。
     */
    protected void fireNodeChoiceChanged(TreePath chosenPath, boolean isSelected) {
        fireNodeChoiceChanged(new TreePath[] { chosenPath }, isSelected);
    }

    /**
     * 发送节点选择已改变通知。
     * @param chosenPaths 选中的路径集。
     * @param isSelected 标识是选择还是取消选择。
     */
    protected void fireNodeChoiceChanged(TreePath[] chosenPaths, boolean isSelected) {
        ComplexTreeModelEvent evt = new ComplexTreeModelEvent(this, chosenPaths, isSelected);
        Object[] listeners = listenerList.getListenerList();

        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == IComplexTreeModelListener.class) {
                ((IComplexTreeModelListener) listeners[i + 1]).nodeChoiceChanged(evt);
            }
        }
    }

    /**
     * 发送节点即将载入通知。
     * @param parent 父缓存节点。
     */
    protected void fireNodeWillLoad(ComplexTreeCacheNode parent) {
        ComplexTreeModelEvent evt = new ComplexTreeModelEvent(parent);
        Object[] listeners = listenerList.getListenerList();

        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == IComplexTreeModelListener.class) {
                ((IComplexTreeModelListener) listeners[i + 1]).nodeWillLoad(evt);
            }
        }
    }

    /**
     * 发送节点已载入通知。
     * @param parent 父缓存节点。
     */
    protected void fireNodeLoaded(ComplexTreeCacheNode parent) {
        ComplexTreeModelEvent evt = new ComplexTreeModelEvent(parent);
        Object[] listeners = listenerList.getListenerList();

        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == IComplexTreeModelListener.class) {
                ((IComplexTreeModelListener) listeners[i + 1]).nodeLoaded(evt);
            }
        }
    }

    /**
     * 发送节点载入已失败通知。
     * @param parent 父缓存节点。
     * @param dsEx 数据源异常。
     */
    protected void fireNodeLoadFailed(ComplexTreeCacheNode parent, ComplexTreeException dsEx) {
        ComplexTreeModelEvent evt = new ComplexTreeModelEvent(parent, dsEx);
        Object[] listeners = listenerList.getListenerList();

        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == IComplexTreeModelListener.class) {
                ((IComplexTreeModelListener) listeners[i + 1]).nodeLoadFailed(evt);
            }
        }
    }

    /**
     * 发送释放缓存通知。
     * @param parent 父缓存节点。
     */
    protected void fireReleaseCache(ComplexTreeCacheNode parent) {
        ComplexTreeModelEvent evt = new ComplexTreeModelEvent(parent, m_caches);
        Object[] listeners = listenerList.getListenerList();

        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == IComplexTreeModelListener.class) {
                ((IComplexTreeModelListener) listeners[i + 1]).releaseCache(evt);
            }
        }
    }

    /**
     * 添加缓存。
     * @param cache 缓存。
     */
    protected void addCache(ComplexTreeCacheNode cache) {
        if (m_caches.size() >= m_cacheSize) {
            fireReleaseCache(cache);
        }

        m_caches.add(cache);
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
