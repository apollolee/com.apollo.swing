/*
 * �˴��봴���� 2007-9-12 ����05:38:21
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
 * <p>�ļ����ƣ�ComplexTreeModel.java</p>
 * <p>�ļ��������ۺ���ģ���࣬�ۺ����ĺ����߼������ڴ���ʵ�ֵġ�</p>
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
public class ComplexTreeModel extends DefaultTreeModel implements IConstants {

    /*------------------------------------- Public (Static) Inner Class -------------------------------------*/

    /**
     * ���ѡ����Խ���쳣�࣬���ڷ�װѡ��״̬����ʱ��һ�������쳣��
     * @since T3 V1.1
     */
    public static class TOutOfMaxChosenCountException extends RuntimeException {

        /**
         * ��ǰѡ�и�����
         */
        private volatile long m_currentChosenCount;

        /**
         * ���ѡ�и�����
         */
        private volatile long m_maxChosenCount;

        /**
         * ���췽����
         * @param currentChosenCount ��ǰѡ�и�����
         * @param maxChosenCount ���ѡ�и�����
         * @since T3 V1.1
         */
        public TOutOfMaxChosenCountException(long currentChosenCount, long maxChosenCount) {
            super("CurrentChosenCount = " + currentChosenCount + " , MaxChosenCount = " + maxChosenCount);
            m_currentChosenCount = currentChosenCount;
            m_maxChosenCount = maxChosenCount;
        }

        /**
         * ��ȡ�ֶ� currentChosenCount ��ֵ����ǰѡ�и�����
         * @return �ֶ� currentChosenCount ��ֵ��
         * @since T3 V1.1
         */
        public long getCurrentChosenCount() {
            return m_currentChosenCount;
        }

        /**
         * ��ȡ�ֶ� maxchosenCount ��ֵ�����ѡ�и�����
         * @return �ֶ� maxchosenCount ��ֵ��
         * @since T3 V1.1
         */
        public long getMaxchosenCount() {
            return m_maxChosenCount;
        }

    }

    /*------------------------------------- Protected (Static) Inner Class -------------------------------------*/

    /**
     * ���빤���߳��ࡣ
     */
    protected class TLoadWorker extends SwingWorker<Object, Object> {

        /**
         * ������ڵ㡣
         */
        protected ComplexTreeCacheNode m_parent;

        /**
         * ���췽����
         * @param parent ������ڵ㡣
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
     * ������ѡ��ڵ�ѡ��״̬�жϸ�ѡ��ڵ��Ƿ�Ϊ��ѡ��״̬��
     * @param choiceNode ��ѡ��ڵ㡣
     * @return ��ʶ��ѡ��ڵ��Ƿ�Ϊ��ѡ��״̬��
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
     * ������ѡ��ڵ�ѡ��״̬�жϸ�ѡ��ڵ��Ƿ�Ϊȡ��ѡ��״̬��
     * @param choiceNode ��ѡ��ڵ㡣
     * @return ��ʶ��ѡ��ڵ��Ƿ�Ϊȡ��ѡ��״̬��
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
     * ����Դ��
     */
    protected IComplexTreeDataSource m_dataSource;

    /**
     * �����С��
     */
    protected volatile int m_cacheSize;

    /**
     * ��ʶ�Ƿ�����ѡ��
     */
    protected volatile boolean m_isEnabledChoice;

    /**
     * ���ѡ�нڵ�ĸ�����
     */
    protected volatile long m_maxChosenCount = Long.MAX_VALUE;

    /**
     * ��ѡ�е�·������
     */
    protected List m_chosenPaths = new ArrayList();

    /**
     * ���漯��
     */
    protected List m_caches = new ArrayList();

    /**
     * ��ʶ�Ƿ��������롣
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
     * ���췽����
     * @param root ���ڵ㣬������ѡ��ڵ�򻺴�ڵ㡣
     * @since T3 V1.1
     */
    public ComplexTreeModel(ComplexTreeNode root) {
        this(root, null);
    }

    /**
     * ���췽����
     * @param root ���ڵ㣬������ѡ��ڵ�򻺴�ڵ㡣
     * @param dataSource ����Դ�����ģ�Ͳ��в������κλ���ڵ㣬������Դ��������Ϊnull��
     * @since T3 V1.1
     */
    public ComplexTreeModel(ComplexTreeNode root, IComplexTreeDataSource dataSource) {
        this(root, dataSource, 100);
    }

    /**
     * ���췽����
     * @param root ���ڵ㣬������ѡ��ڵ�򻺴�ڵ㡣
     * @param dataSource ����Դ�����ģ�Ͳ��в������κλ���ڵ㣬������Դ��������Ϊnull��
     * @param cacheSize �����С����ͬʱ����������״̬�Ļ���ڵ�ĸ�����ע�⣺ģ�ͻᾡ��ȥ�����������ֵ��������֤һ�������㣬�����Щ����´�ֵ��ʧЧ��
     * @since T3 V1.1
     */
    public ComplexTreeModel(ComplexTreeNode root, IComplexTreeDataSource dataSource, int cacheSize) {
        this(root, dataSource, cacheSize, true);
    }

    /**
     * ���췽����
     * @param root ���ڵ㣬������ѡ��ڵ�򻺴�ڵ㡣
     * @param dataSource ����Դ�����ģ�Ͳ��в������κλ���ڵ㣬������Դ��������Ϊnull��
     * @param cacheSize �����С����ͬʱ����������״̬�Ļ���ڵ�ĸ�����ע�⣺ģ�ͻᾡ��ȥ�����������ֵ��������֤һ�������㣬�����Щ����´�ֵ��ʧЧ��
     * @param isEnabledChoice ��ʶ�Ƿ�����ѡ�����������������ϵĽڵ�ǰ�治�����ѡ���ȱʡΪ����
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
     * ��ȡ�ֶ� dataSource ��ֵ������Դ�����ģ�Ͳ��в������κλ���ڵ㣬������Դ��������Ϊnull��
     * @return �ֶ� dataSource ��ֵ��
     * @since T3 V1.1
     */
    public IComplexTreeDataSource getDataSource() {
        return m_dataSource;
    }

    /**
     * �����ֶ� dataSource ��ֵ������Դ�����ģ�Ͳ��в������κλ���ڵ㣬������Դ��������Ϊnull��
     * @param dataSource �ֶ� dataSource ��ֵ��
     * @since T3 V1.1
     */
    public void setDataSource(IComplexTreeDataSource dataSource) {
        m_dataSource = dataSource;
    }

    /**
     * ��ȡ�ֶ� cacheSize ��ֵ�������С����ͬʱ����������״̬�Ļ���ڵ�ĸ�����ע�⣺ģ�ͻᾡ��ȥ�����������ֵ��������֤һ�������㣬�����Щ����´�ֵ��ʧЧ��
     * @return �ֶ� cacheSize ��ֵ��
     * @since T3 V1.1
     */
    public int getCacheSize() {
        return m_cacheSize;
    }

    /**
     * �����ֶ� cacheSize ��ֵ�������С����ͬʱ����������״̬�Ļ���ڵ�ĸ�����ע�⣺ģ�ͻᾡ��ȥ�����������ֵ��������֤һ�������㣬�����Щ����´�ֵ��ʧЧ��
     * @param cacheSize �ֶ� cacheSize ��ֵ��
     * @since T3 V1.1
     */
    public void setCacheSize(int cacheSize) {
        m_cacheSize = cacheSize;
    }

    /**
     * ��ȡ�ֶ� isEnabledChoice ��ֵ����ʶ�Ƿ�����ѡ�����������������ϵĽڵ�ǰ�治�����ѡ���ȱʡΪ����
     * @return �ֶ� isEnabledChoice ��ֵ��
     * @since T3 V1.1
     */
    public boolean isEnabledChoice() {
        return m_isEnabledChoice;
    }

    /**
     * ��ȡ�ֶ� maxChosenCount ��ֵ����ʶ�Ƿ�����ѡ�����������������ϵĽڵ�ǰ�治�����ѡ���ȱʡΪ����
     * @return �ֶ� maxChosenCount ��ֵ��
     * @since T3 V1.1
     */
    public long getMaxChosenCount() {
        return m_maxChosenCount;
    }

    /**
     * �����ֶ� maxChosenCount ��ֵ�����ѡ�������ޡ�
     * @param maxChosenCount �ֶ� maxChosenCount ��ֵ��
     * @since T3 V1.1
     */
    public void setMaxChosenCount(long maxChosenCount) {
        m_maxChosenCount = maxChosenCount;
    }

    /**
     * ����ۺ���ģ�ͼ�������
     * @param listener �ۺ���ģ�ͼ�������
     * @since T3 V1.1
     */
    public void addComplexTreeModelListener(IComplexTreeModelListener listener) {
        listenerList.add(IComplexTreeModelListener.class, listener);
    }

    /**
     * �Ƴ��ۺ���ģ�ͼ�������
     * @param listener �ۺ���ģ�ͼ�������
     * @since T3 V1.1
     */
    public void removeComplexTreeModelListener(IComplexTreeModelListener listener) {
        listenerList.remove(IComplexTreeModelListener.class, listener);
    }

    /**
     * ��ȡ�����ۺ���ģ�ͼ�������
     * @return �����ۺ���ģ�ͼ�������
     * @since T3 V1.1
     */
    public IComplexTreeModelListener[] getComplexTreeModelListener() {
        return (IComplexTreeModelListener[]) listenerList.getListeners(IComplexTreeModelListener.class);
    }

    /**
     * �ͷ����л��棬������ֶ��ͷŻ���ķ�����һ������²���ҪӦ������ȥ���á�
     * @since T3 V1.1
     */
    public void releaseCache() {
        releaseCache((ComplexTreeNode) root);
    }

    /**
     * �ͷ�ָ���ڵ��µ����л��棬������ֶ��ͷŻ���ķ�����һ������²���ҪӦ������ȥ���á�
     * @param node ָ���ڵ㡣
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
     * ��ȡ��ѡ�е�·������ע�⣺·���������ÿ��ֵ���ǽڵ���󣬶��ǽڵ����������userObject����
     * @return ��ѡ�е�·������
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
     * ������ѡ�е�·������ע�⣺·���������ÿ��ֵ���ǽڵ���󣬶��ǽڵ����������userObject����
     * @param chosenPaths ��ѡ�е�·������
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
     * ���������ѡ�е�·����
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
     * ���ָ���ڵ㣬�ڵ��ѡ��״̬����֮�л���
     * @param choiceNode ѡ��ڵ㡣
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
     * ѡ��ָ���Ľڵ㡣
     * @param choiceNode ѡ��ڵ㡣
     * @since T3 V1.1
     */
    public void select(ComplexTreeChoiceNode choiceNode) {
        select(choiceNode, true, true, true);
    }

    /**
     * ȡ��ѡ��ָ���Ľڵ㡣
     * @param choiceNode ѡ��ڵ㡣
     * @since T3 V1.1
     */
    public void deselect(ComplexTreeChoiceNode choiceNode) {
        deselect(choiceNode, true, true, true);
    }

    /**
     * ����ѡ�нڵ㣬��Ҫ��Ϊ�˱���ѡ��״̬��һ���ԣ��˷��������˵�ȽϺ�ʱ������������ã���Ӧ�ö࿿Ӧ������֤�ڵ�ѡ��״̬��һ���ԡ�
     * @param parent ��ѡ��ڵ㡣
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
     * ͬ����ѡ�е�·������
     */
    protected void syncChosenPaths() {
        syncChosenPaths((ComplexTreeChoiceNode) root);
    }

    /**
     * ͬ����ѡ�е�·������
     * @param choiceNode ѡ��ڵ㡣
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
     * ��֤��ѡ�е�·�����Ƿ�Ϸ���
     */
    protected void validate() {
        for (int i = m_chosenPaths.size() - 1; i >= 0; i--) {
            if (!validate((TreePath) m_chosenPaths.get(i))) {
                m_chosenPaths.remove(i);
            }
        }
    }

    /**
     * ��֤��ѡ�е�·���Ƿ�Ϸ���
     * @param chosenPath ��ѡ�е�·����
     * @return ��ʶ��ѡ�е�·���Ƿ�Ϸ���
     */
    protected boolean validate(TreePath chosenPath) {
        ComplexTreeChoiceNode node = (ComplexTreeChoiceNode) root;
        return chosenPath.getPathComponent(0).equals(node.getUserObject()) ? validate(chosenPath, node) : false;
    }

    /**
     * ��֤��ѡ�е�·���Ƿ�Ϸ���
     * @param chosenPath ��ѡ�е�·����
     * @param node �ڵ㡣
     * @return ��ʶ��ѡ�е�·���Ƿ�Ϸ���
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
     * ���ͽڵ��Ѹı�֪ͨ�������ڽ�ֹ����ʱ����
     * @param node �ڵ㡣
     */
    protected void reloadForDisabledLoad(TreeNode node) {
        m_isEnabledLoad = false;
        reload(node);
        m_isEnabledLoad = true;
    }

    /**
     * ��ȡ����Ľڵ㡣
     * @param path ·����
     * @return ����Ľڵ㡣
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
     * ����ָ������ѡ��·����ȡѡ�нڵ㡣
     * @param chosenPath ָ������ѡ��·����
     * @return ѡ�нڵ㡣
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
     * �ж�ָ��·���Ƿ�����ѡ�е�·����
     * @param path ָ��·����
     * @return ��ʶָ��·���Ƿ�����ѡ�е�·����
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
     * �����ѡ�е�·����
     * @param newChosenPath ��ѡ�е�·����
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
     * �Ƴ���ѡ�е�·����
     * @param oldChosenPath ��ѡ�е�·����
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
     * ��ȡ�����ѡ��·����
     * @param path ·����
     * @return �����ѡ��·����
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
     * ѡ��
     * @param choiceNode ѡ��ڵ�
     * @param hasRefreshChosenPath ��ʶ�Ƿ���Ҫˢ��ѡ��·����
     * @param hasCheckParent ��ʶ�Ƿ���Ҫ��鸸�ڵ㡣
     * @param hasNotify ��ʶ�Ƿ���Ҫ��֪ͨ��
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
     * ȡ��ѡ��
     * @param choiceNode ѡ��ڵ㡣
     * @param hasRefreshChosenPath ��ʶ�Ƿ���Ҫˢ��ѡ��·����
     * @param hasCheckParent ��ʶ�Ƿ���Ҫ��鸸�ڵ㡣
     * @param hasNotify ��ʶ�Ƿ���Ҫ��֪ͨ��
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
     * ��ȷ����
     * @param choiceNode ѡ��ڵ㡣
     */
    protected void indetermine(ComplexTreeChoiceNode choiceNode) {
        if (!m_isEnabledChoice || choiceNode == null || choiceNode.isIndetermined()) {
            return;
        }

        choiceNode.setIndetermined();
        indetermine((ComplexTreeChoiceNode) choiceNode.getParent());
    }

    /**
     * ˢ�¡�
     */
    protected void refresh() {
        refresh((ComplexTreeChoiceNode) root);
    }

    /**
     * ˢ�¡�
     * @param choiceNode ѡ��ڵ㡣
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
     * ���ͽڵ�ѡ���Ѹı�֪ͨ��
     * @param chosenPath ѡ�е�·����
     * @param isSelected ��ʶ��ѡ����ȡ��ѡ��
     */
    protected void fireNodeChoiceChanged(TreePath chosenPath, boolean isSelected) {
        fireNodeChoiceChanged(new TreePath[] { chosenPath }, isSelected);
    }

    /**
     * ���ͽڵ�ѡ���Ѹı�֪ͨ��
     * @param chosenPaths ѡ�е�·������
     * @param isSelected ��ʶ��ѡ����ȡ��ѡ��
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
     * ���ͽڵ㼴������֪ͨ��
     * @param parent ������ڵ㡣
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
     * ���ͽڵ�������֪ͨ��
     * @param parent ������ڵ㡣
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
     * ���ͽڵ�������ʧ��֪ͨ��
     * @param parent ������ڵ㡣
     * @param dsEx ����Դ�쳣��
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
     * �����ͷŻ���֪ͨ��
     * @param parent ������ڵ㡣
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
     * ��ӻ��档
     * @param cache ���档
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
