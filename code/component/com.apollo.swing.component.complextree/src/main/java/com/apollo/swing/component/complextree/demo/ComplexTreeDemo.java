/*
 * 此代码创建于 2008-10-21 下午04:04:44。
 */
package com.apollo.swing.component.complextree.demo;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.AbstractCellEditor;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

import com.apollo.base.util.BaseUtilities;
import com.apollo.base.util.IBaseConstants;
import com.apollo.swing.component.common.jtree.SingleIconTreeCellRenderer;
import com.apollo.swing.component.complextree.ComplexTree;
import com.apollo.swing.component.complextree.ComplexTreeCacheNode;
import com.apollo.swing.component.complextree.ComplexTreeChoiceNode;
import com.apollo.swing.component.complextree.ComplexTreeException;
import com.apollo.swing.component.complextree.ComplexTreeModel;
import com.apollo.swing.component.complextree.ComplexTreeModelEvent;
import com.apollo.swing.component.complextree.ComplexTreeNode;
import com.apollo.swing.component.complextree.IComplexTreeDataSource;
import com.apollo.swing.component.complextree.IComplexTreeModelListener;
import com.apollo.swing.laf.TLookAndFeelManager;

/**
 * <p>文件名称：ComplexTreeDemo.java</p>
 * <p>类型描述：综合树演示类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-10-21</p>
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
public class ComplexTreeDemo extends JPanel implements IBaseConstants, IComplexTreeModelListener {

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

    /**
     * 网络资源渲染器。
     */
    protected static TreeCellRenderer s_netResRenderer = new SingleIconTreeCellRenderer("com/apollo/swing/component/complextree/demo/net-res.gif");

    /**
     * 网元组渲染器。
     */
    protected static TreeCellRenderer s_neGroupRenderer = new SingleIconTreeCellRenderer("com/apollo/swing/component/complextree/demo/ne-group.gif");

    /**
     * 网元渲染器。
     */
    protected static TreeCellRenderer s_neRenderer = new SingleIconTreeCellRenderer("com/apollo/swing/component/complextree/demo/ne.gif");

    /**
     * 单板渲染器。
     */
    protected static TreeCellRenderer s_boardRenderer = new SingleIconTreeCellRenderer("com/apollo/swing/component/complextree/demo/board.gif");

    /**
     * 端口渲染器。
     */
    protected static TreeCellRenderer s_portRenderer = new SingleIconTreeCellRenderer("com/apollo/swing/component/complextree/demo/port.gif");

    /**
     * 非选择节点渲染器。
     */
    protected static TreeCellRenderer s_noSelectionRenderer = new SingleIconTreeCellRenderer("com/apollo/swing/component/complextree/demo/no-selection.gif");

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
     * @param tree 综合树。
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
    private static List<DefaultMutableTreeNode> getNodes(DefaultMutableTreeNode node) {
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
     * @param tree 综合树。
     * @param nodes 综合树节点集。
     * @return 标识是否搜索到指定的文本。
     */
    private static boolean searchText(String text, JTree tree, List<DefaultMutableTreeNode> nodes) {
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

    /**
     * 主方法。
     * @param args 参数。
     */
    public static void main(String[] args) {
        TLookAndFeelManager.loadAlloy(false);

        JFrame f = new JFrame();
        f.setTitle("TComplexTreeDemo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addComponentListener(BaseUtilities.createSizeLimiter());
        f.setContentPane(new ComplexTreeDemo());
        f.pack();
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        BaseUtilities.center(f);
        f.setVisible(true);
    }

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
     * 已选中的路径。
     */
    protected TreePath[] m_chosenPaths = null;

    /**
     * 模型。
     */
    protected ComplexTreeModel m_model = new ComplexTreeModel(new ComplexTreeNode("root"));

    /**
     * 树。
     */
    protected ComplexTree m_tree = new ComplexTree(m_model);

    /**
     * 消息。
     */
    protected JTextArea m_msg = new JTextArea();

    /**
     * 动作------综合树。
     */
    protected Action m_complex = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "综合树", 'C');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            /* 构造根节点 */
            ComplexTreeNode netRes = new ComplexTreeNode("网络资源");
            netRes.setRenderer(s_netResRenderer);

            /* 构造第一层节点 */
            /* for netRes */
            ComplexTreeNode neGroup1 = new ComplexTreeNode("网元组 1");
            neGroup1.setRenderer(s_neGroupRenderer);
            neGroup1.setToolTipText("测试工具提示文本！--- 网元组 1");

            ComplexTreeNode neGroup2 = new ComplexTreeNode("网元组 2");
            neGroup2.setRenderer(s_neGroupRenderer);

            ComplexTreeNode neGroup3 = new ComplexTreeNode("网元组 3");
            neGroup3.setRenderer(s_neGroupRenderer);

            ComplexTreeNode neGroup4 = new ComplexTreeNode("网元组 4（可编辑）");
            neGroup4.setRenderer(s_neGroupRenderer);
            neGroup4.setEditable(true);
            neGroup4.setToolTipText("测试工具提示文本！--- 网元组 4（可编辑）");

            ComplexTreeNode ctn1 = new ComplexTreeNode("普通综合树节点");
            ctn1.setRenderer(s_noSelectionRenderer);

            /* 添加第一层节点 */
            /* for netRes */
            netRes.add(neGroup1);
            netRes.add(neGroup2);
            netRes.add(neGroup3);
            netRes.add(neGroup4);
            netRes.add(ctn1);

            /* 构造第二层节点 */
            /* for neGroup1 */
            ComplexTreeNode ne11 = new ComplexTreeNode("网元 1-1（可编辑）");
            ne11.setRenderer(s_neRenderer);
            ne11.setEditable(true);

            ComplexTreeNode ne12 = new ComplexTreeNode("网元 1-2");
            ne12.setRenderer(s_neRenderer);

            ComplexTreeNode ne13 = new ComplexTreeNode("网元 1-3");
            ne13.setRenderer(s_neRenderer);

            ComplexTreeNode ctn2 = new ComplexTreeNode("普通综合树节点（可编辑）");
            ctn2.setRenderer(s_noSelectionRenderer);
            ctn2.setEditable(true);
            ctn2.setToolTipText("测试工具提示文本！--- 普通综合树节点（可编辑）");

            /* for neGroup2 */
            ComplexTreeNode ne21 = new ComplexTreeNode("网元 2-1");
            ne21.setRenderer(s_neRenderer);

            /* for neGroup3 */
            ComplexTreeNode ne31 = new ComplexTreeNode("网元 3-1");
            ne31.setRenderer(s_neRenderer);

            ComplexTreeNode ne32 = new ComplexTreeNode("网元 3-2");
            ne32.setRenderer(s_neRenderer);

            /* 添加第二层节点 */
            /* for neGroup1 */
            neGroup1.add(ne11);
            neGroup1.add(ne12);
            neGroup1.add(ne13);
            neGroup1.add(ctn2);

            /* for neGroup2 */
            neGroup2.add(ne21);

            /* for neGroup3 */
            neGroup3.add(ne31);
            neGroup3.add(ne32);

            /* 构造第三层节点 */
            /* for ne12 */
            ComplexTreeNode board121 = new ComplexTreeNode("单板 1-2-1");
            board121.setRenderer(s_boardRenderer);

            ComplexTreeNode board122 = new ComplexTreeNode("单板 1-2-2");
            board122.setRenderer(s_boardRenderer);

            ComplexTreeNode board123 = new ComplexTreeNode("单板 1-2-3");
            board123.setRenderer(s_boardRenderer);

            /* 添加第三层节点 */
            /* for ne12 */
            ne12.add(board121);
            ne12.add(board122);
            ne12.add(board123);

            /* 构造第四层节点 */
            /* for board122 */
            ComplexTreeNode port1221 = new ComplexTreeNode("端口 1-2-2-1");
            port1221.setRenderer(s_portRenderer);

            ComplexTreeNode port1222 = new ComplexTreeNode("端口 1-2-2-2");
            port1222.setRenderer(s_portRenderer);

            /* custom */
            ComplexTreeNode customStn = new ComplexTreeNode(new Integer(2));
            customStn.setRenderer(new CustomCellRenderer());
            customStn.setEditor(new CustomCellEditor());
            customStn.setEditable(true);

            /* 添加第四层节点 */
            /* for board122 */
            board122.add(port1221);
            board122.add(port1222);
            board122.add(customStn);

            m_model.setRoot(netRes);
        }

    };

    /**
     * 动作------选择树。
     */
    protected Action m_choice = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "选择树", 'O');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            /* 构造根选择节点 */
            ComplexTreeChoiceNode netRes = new ComplexTreeChoiceNode("网络资源");
            netRes.setRenderer(s_netResRenderer);

            /* 构造第一层节点 */
            /* for netRes */
            ComplexTreeChoiceNode neGroup1 = new ComplexTreeChoiceNode("网元组 1");
            neGroup1.setRenderer(s_neGroupRenderer);
            neGroup1.setToolTipText("测试工具提示文本！--- 网元组 1");

            ComplexTreeChoiceNode neGroup2 = new ComplexTreeChoiceNode("网元组 2");
            neGroup2.setRenderer(s_neGroupRenderer);

            ComplexTreeChoiceNode neGroup3 = new ComplexTreeChoiceNode("网元组 3");
            neGroup3.setRenderer(s_neGroupRenderer);

            ComplexTreeChoiceNode neGroup4 = new ComplexTreeChoiceNode("网元组 4（可编辑）");
            neGroup4.setRenderer(s_neGroupRenderer);
            neGroup4.setEditable(true);
            neGroup4.setToolTipText("测试工具提示文本！--- 网元组 4（可编辑）");

            ComplexTreeNode ctn1 = new ComplexTreeNode("普通综合树节点");
            ctn1.setRenderer(s_noSelectionRenderer);

            /* 添加第一层节点 */
            /* for netRes */
            netRes.add(neGroup1);
            netRes.add(neGroup2);
            netRes.add(neGroup3);
            netRes.add(neGroup4);
            netRes.add(ctn1);

            /* 构造第二层节点 */
            /* for neGroup1 */
            ComplexTreeChoiceNode ne11 = new ComplexTreeChoiceNode("网元 1-1（可编辑）");
            ne11.setRenderer(s_neRenderer);
            ne11.setEditable(true);

            ComplexTreeChoiceNode ne12 = new ComplexTreeChoiceNode("网元 1-2");
            ne12.setRenderer(s_neRenderer);

            ComplexTreeChoiceNode ne13 = new ComplexTreeChoiceNode("网元 1-3");
            ne13.setRenderer(s_neRenderer);

            ComplexTreeNode ctn2 = new ComplexTreeNode("普通综合树节点（可编辑）");
            ctn2.setRenderer(s_noSelectionRenderer);
            ctn2.setEditable(true);
            ctn2.setToolTipText("测试工具提示文本！--- 普通综合树节点（可编辑）");

            /* for neGroup2 */
            ComplexTreeChoiceNode ne21 = new ComplexTreeChoiceNode("网元 2-1");
            ne21.setRenderer(s_neRenderer);

            /* for neGroup3 */
            ComplexTreeChoiceNode ne31 = new ComplexTreeChoiceNode("网元 3-1");
            ne31.setRenderer(s_neRenderer);

            ComplexTreeChoiceNode ne32 = new ComplexTreeChoiceNode("网元 3-2");
            ne32.setRenderer(s_neRenderer);

            /* 添加第二层节点 */
            /* for neGroup1 */
            neGroup1.add(ne11);
            neGroup1.add(ne12);
            neGroup1.add(ne13);
            neGroup1.add(ctn2);

            /* for neGroup2 */
            neGroup2.add(ne21);

            /* for neGroup3 */
            neGroup3.add(ne31);
            neGroup3.add(ne32);

            /* 构造第三层节点 */
            /* for ne12 */
            ComplexTreeChoiceNode board121 = new ComplexTreeChoiceNode("单板 1-2-1");
            board121.setRenderer(s_boardRenderer);

            ComplexTreeChoiceNode board122 = new ComplexTreeChoiceNode("单板 1-2-2");
            board122.setRenderer(s_boardRenderer);

            ComplexTreeChoiceNode board123 = new ComplexTreeChoiceNode("单板 1-2-3");
            board123.setRenderer(s_boardRenderer);

            /* 添加第三层节点 */
            /* for ne12 */
            ne12.add(board121);
            ne12.add(board122);
            ne12.add(board123);

            /* 构造第四层节点 */
            /* for board122 */
            ComplexTreeChoiceNode port1221 = new ComplexTreeChoiceNode("端口 1-2-2-1");
            port1221.setRenderer(s_portRenderer);

            ComplexTreeChoiceNode port1222 = new ComplexTreeChoiceNode("端口 1-2-2-2");
            port1222.setRenderer(s_portRenderer);

            /* custom */
            ComplexTreeChoiceNode customStn = new ComplexTreeChoiceNode(new Integer(2));
            customStn.setRenderer(new CustomCellRenderer());
            customStn.setEditor(new CustomCellEditor());
            customStn.setEditable(true);

            /* 添加第四层节点 */
            /* for board122 */
            board122.add(port1221);
            board122.add(port1222);
            board122.add(customStn);

            m_model.setRoot(netRes);
        }

    };

    /**
     * 动作------缓存树。
     */
    protected Action m_cache = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "缓存树", 'A');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_model.setDataSource(new MockDataSource(100, 5, 1000, 1));
            m_model.setCacheSize(2);
            m_model.setRoot(new ComplexTreeCacheNode("Node"));
        }

    };

    /**
     * 动作------获取已选中路径。
     */
    protected Action m_getChosenPaths = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "获取已选中路径", 'G');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_msg.append("---------------------------------已选中路径（开始）---------------------------------\n");
            m_chosenPaths = m_model.getChosenPaths();
            for (int i = 0; i < m_chosenPaths.length; i++) {
                m_msg.append(m_chosenPaths[i].toString() + "\n");
            }
            m_msg.append("---------------------------------已选中路径（结束）---------------------------------\n");
        }

    };

    /**
     * 动作------设置已选中路径。
     */
    protected Action m_setChosenPaths = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "设置已选中路径", 'S');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_model.setChosenPaths(m_chosenPaths);
            m_tree.expand();
        }

    };

    /**
     * 动作------获取已载入的缓存节点。
     */
    protected Action m_getCaches = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "获取已载入的缓存节点", 'G');
            setEnabled(false);
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            /*m_msg.append("---------------------------------已载入的缓存节点（开始）---------------------------------\n");
            for (int i = 0, size = m_model.m_caches.size(); i < size; i++) {
            	m_msg.append(m_model.m_caches.get(i).toString() + "\n");
            }
            m_msg.append("---------------------------------已载入的缓存节点（结束）---------------------------------\n");*/
        }

    };

    /**
     * 动作------清除消息。
     */
    protected Action m_clearMsg = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "清除消息", 'R');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_msg.setText("");
        }

    };

    /**
     * 动作------同步选择。
     */
    protected JCheckBox m_syncChoice = new JCheckBox(new AbstractAction() {

        {
            BaseUtilities.setAction(this, "同步选择", 'S');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_tree.setEditable(!m_syncChoice.isSelected());
            m_tree.setSyncChoice(m_syncChoice.isSelected());
        }

    });

    /**
     * 动作------允许选择。
     */
    protected JCheckBox m_enabledChoice = new JCheckBox(new AbstractAction() {

        {
            BaseUtilities.setAction(this, "允许选择", 'E');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_tree.setEnabledChoice(m_enabledChoice.isSelected());
        }

    });

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
    public ComplexTreeDemo() {
        super(new GridBagLayout());

        m_msg.setEditable(false);
        m_model.addComplexTreeModelListener(this);
        m_enabledChoice.setSelected(true);

        JScrollPane treeView = new JScrollPane(m_tree);
        //m_tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        treeView.setBorder(BorderFactory.createTitledBorder("综合树"));

        JScrollPane msgView = new JScrollPane(m_msg);
        msgView.setBorder(BorderFactory.createTitledBorder("消息"));

        JSplitPane treeAndMsgView = new JSplitPane(JSplitPane.VERTICAL_SPLIT, treeView, msgView);
        treeAndMsgView.setDividerLocation(300);

        JPanel btnView = new JPanel(new GridBagLayout());
        btnView.setBorder(BorderFactory.createTitledBorder("功能"));
        btnView.add(new JButton(m_complex), new GridBagConstraints(0,
                                                                   0,
                                                                   1,
                                                                   1,
                                                                   0.0,
                                                                   0.0,
                                                                   GridBagConstraints.NORTHWEST,
                                                                   GridBagConstraints.HORIZONTAL,
                                                                   INSETS_DEFAULT,
                                                                   IPADX_DEFAULT,
                                                                   IPADY_DEFAULT));
        btnView.add(new JButton(m_choice), new GridBagConstraints(0,
                                                                  1,
                                                                  1,
                                                                  1,
                                                                  0.0,
                                                                  0.0,
                                                                  GridBagConstraints.NORTHWEST,
                                                                  GridBagConstraints.HORIZONTAL,
                                                                  INSETS_DEFAULT,
                                                                  IPADX_DEFAULT,
                                                                  IPADY_DEFAULT));
        btnView.add(new JButton(m_cache), new GridBagConstraints(0,
                                                                 2,
                                                                 1,
                                                                 1,
                                                                 0.0,
                                                                 0.0,
                                                                 GridBagConstraints.NORTHWEST,
                                                                 GridBagConstraints.HORIZONTAL,
                                                                 INSETS_DEFAULT,
                                                                 IPADX_DEFAULT,
                                                                 IPADY_DEFAULT));
        btnView.add(new JButton(m_getChosenPaths), new GridBagConstraints(0,
                                                                          3,
                                                                          1,
                                                                          1,
                                                                          0.0,
                                                                          0.0,
                                                                          GridBagConstraints.NORTHWEST,
                                                                          GridBagConstraints.HORIZONTAL,
                                                                          INSETS_DEFAULT,
                                                                          IPADX_DEFAULT,
                                                                          IPADY_DEFAULT));
        btnView.add(new JButton(m_setChosenPaths), new GridBagConstraints(0,
                                                                          4,
                                                                          1,
                                                                          1,
                                                                          0.0,
                                                                          0.0,
                                                                          GridBagConstraints.NORTHWEST,
                                                                          GridBagConstraints.HORIZONTAL,
                                                                          INSETS_DEFAULT,
                                                                          IPADX_DEFAULT,
                                                                          IPADY_DEFAULT));
        btnView.add(new JButton(m_getCaches), new GridBagConstraints(0,
                                                                     5,
                                                                     1,
                                                                     1,
                                                                     0.0,
                                                                     0.0,
                                                                     GridBagConstraints.NORTHWEST,
                                                                     GridBagConstraints.HORIZONTAL,
                                                                     INSETS_DEFAULT,
                                                                     IPADX_DEFAULT,
                                                                     IPADY_DEFAULT));
        btnView.add(new JButton(m_clearMsg), new GridBagConstraints(0,
                                                                    6,
                                                                    1,
                                                                    1,
                                                                    0.0,
                                                                    0.0,
                                                                    GridBagConstraints.NORTHWEST,
                                                                    GridBagConstraints.HORIZONTAL,
                                                                    INSETS_DEFAULT,
                                                                    IPADX_DEFAULT,
                                                                    IPADY_DEFAULT));
        btnView.add(m_syncChoice, new GridBagConstraints(0,
                                                         7,
                                                         1,
                                                         1,
                                                         0.0,
                                                         0.0,
                                                         GridBagConstraints.NORTHWEST,
                                                         GridBagConstraints.HORIZONTAL,
                                                         INSETS_DEFAULT,
                                                         IPADX_DEFAULT,
                                                         IPADY_DEFAULT));
        btnView.add(m_enabledChoice, new GridBagConstraints(0,
                                                            8,
                                                            1,
                                                            1,
                                                            0.0,
                                                            0.0,
                                                            GridBagConstraints.NORTHWEST,
                                                            GridBagConstraints.NONE,
                                                            INSETS_DEFAULT,
                                                            IPADX_DEFAULT,
                                                            IPADY_DEFAULT));

        final JTextField tf = new JTextField();
        JButton bt = new JButton("查询");
        bt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                searchText(tf.getText().trim(), m_tree, m_tree);
            }

        });

        btnView.add(tf, new GridBagConstraints(0,
                                               9,
                                               1,
                                               1,
                                               1.0,
                                               0.0,
                                               GridBagConstraints.NORTHWEST,
                                               GridBagConstraints.HORIZONTAL,
                                               INSETS_DEFAULT,
                                               IPADX_DEFAULT,
                                               IPADY_DEFAULT));
        btnView.add(bt, new GridBagConstraints(0,
                                               10,
                                               1,
                                               1,
                                               0.0,
                                               1.0,
                                               GridBagConstraints.NORTHWEST,
                                               GridBagConstraints.NONE,
                                               INSETS_DEFAULT,
                                               IPADX_DEFAULT,
                                               IPADY_DEFAULT));

        add(treeAndMsgView, new GridBagConstraints(0,
                                                   0,
                                                   1,
                                                   1,
                                                   1.0,
                                                   1.0,
                                                   GridBagConstraints.WEST,
                                                   GridBagConstraints.BOTH,
                                                   INSETS_DEFAULT,
                                                   IPADX_DEFAULT,
                                                   IPADY_DEFAULT));
        add(btnView, new GridBagConstraints(1,
                                            0,
                                            1,
                                            1,
                                            0.0,
                                            1.0,
                                            GridBagConstraints.NORTHWEST,
                                            GridBagConstraints.VERTICAL,
                                            INSETS_DEFAULT,
                                            IPADX_DEFAULT,
                                            IPADY_DEFAULT));

        setPreferredSize(new Dimension(500, 400));
        setMinimumSize(getPreferredSize());
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /* Add Public Method */

    /*------------------------------------- IComplexTreeModelListener Public Method -------------------------------------*/

    /**
     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#nodeChoiceChanged(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
     */
    public void nodeChoiceChanged(ComplexTreeModelEvent evt) {
        m_msg.append("节点选择已改变（" + (evt.isSelected() ? "已选择" : "去选择") + "）：" + Arrays.asList(evt.getPahts()) + "\n");
    }

    /**
     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#nodeWillLoad(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
     */
    public void nodeWillLoad(ComplexTreeModelEvent evt) {
        m_msg.append("节点即将载入：" + evt.getSource() + "\n");
    }

    /**
     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#nodeLoaded(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
     */
    public void nodeLoaded(ComplexTreeModelEvent evt) {
        m_msg.append("节点已载入：" + evt.getSource() + "\n");
    }

    /**
     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#nodeLoadFailed(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
     */
    public void nodeLoadFailed(ComplexTreeModelEvent evt) {
        m_msg.append("节点载入已失败：" + evt.getException() + "\n");
    }

    /**
     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#releaseCache(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
     */
    public void releaseCache(ComplexTreeModelEvent evt) {
        m_msg.append("释放缓存：" + evt.getCaches() + "\n");
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}

/**
 * <p>文件名称：ComplexTreeDemo.java</p>
 * <p>类型描述：定制节点渲染器类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-10-22</p>
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
class CustomCellRenderer extends JComboBox implements TreeCellRenderer {

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

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * 构造方法。
     */
    public CustomCellRenderer() {
        addItem("Demo Item 1");
        addItem("Demo Item 2");
        addItem("Demo Item 3");
        addItem("Demo Item 4");
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /* Add Public Method */

    /*------------------------------------- TreeCellRenderer Public Method -------------------------------------*/

    /**
     * @see javax.swing.tree.TreeCellRenderer#getTreeCellRendererComponent(javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int, boolean)
     */
    public Component getTreeCellRendererComponent(JTree tree,
                                                  Object value,
                                                  boolean isSelected,
                                                  boolean isExpanded,
                                                  boolean isLeaf,
                                                  int rowIndex,
                                                  boolean hasFocus) {
        ComplexTreeNode node = (ComplexTreeNode) value;
        Integer index = (Integer) node.getUserObject();

        setSelectedIndex(index.intValue());

        return this;
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}

/**
 * <p>文件名称：ComplexTreeDemo.java</p>
 * <p>类型描述：定制节点编辑器类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-10-22</p>
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
class CustomCellEditor extends AbstractCellEditor implements TreeCellEditor {

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
     * 编辑组件。
     */
    protected JComboBox m_comboBox = new JComboBox();

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
    public CustomCellEditor() {
        m_comboBox.addItem("Demo Item 1");
        m_comboBox.addItem("Demo Item 2");
        m_comboBox.addItem("Demo Item 3");
        m_comboBox.addItem("Demo Item 4");

        m_comboBox.addPopupMenuListener(new PopupMenuListener() {

            /**
             * @see javax.swing.event.PopupMenuListener#popupMenuCanceled(javax.swing.event.PopupMenuEvent)
             */
            public void popupMenuCanceled(PopupMenuEvent evt) {
                /* 无需实现 */
            }

            /**
             * @see javax.swing.event.PopupMenuListener#popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent)
             */
            public void popupMenuWillBecomeInvisible(PopupMenuEvent evt) {
                stopCellEditing();
            }

            /**
             * @see javax.swing.event.PopupMenuListener#popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent)
             */
            public void popupMenuWillBecomeVisible(PopupMenuEvent evt) {
                /* 无需实现 */
            }

        });
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /* Add Public Method */

    /*------------------------------------- TreeCellEditor Public Method -------------------------------------*/

    /**
     * @see javax.swing.tree.TreeCellEditor#getTreeCellEditorComponent(javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int)
     */
    public Component getTreeCellEditorComponent(JTree tree,
                                                Object value,
                                                boolean isSelected,
                                                boolean isExpanded,
                                                boolean isLeaf,
                                                int rowIndex) {
        ComplexTreeNode node = (ComplexTreeNode) value;
        Integer index = (Integer) node.getUserObject();

        m_comboBox.setSelectedIndex(index.intValue());

        return m_comboBox;
    }

    /*------------------------------------- CellEditor Public Method -------------------------------------*/

    /**
     * @see javax.swing.CellEditor#getCellEditorValue()
     */
    public Object getCellEditorValue() {
        return new Integer(m_comboBox.getSelectedIndex());
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}

/**
 * <p>文件名称：MockDataSource.java</p>
 * <p>类型描述：打桩用的综合树模型数据源。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-3-27</p>
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
class MockDataSource implements IComplexTreeDataSource {

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
     * 尺寸。
     */
    protected int m_size;

    /**
     * 深度。
     */
    protected int m_depth;

    /**
     * 延迟。
     */
    protected long m_delay;

    /**
     * 模。
     */
    protected int m_mod;

    /**
     * 异常。
     */
    protected ComplexTreeException m_dsEx = null;

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
    public MockDataSource() {
        this(10, 5, 1000, 3);
    }

    /**
     * 构造方法。
     * @param size 尺寸。
     * @param depth 深度。
     * @param delay 延迟。
     * @param mod 模。
     */
    public MockDataSource(int size, int depth, long delay, int mod) {
        m_size = size;
        m_depth = depth;
        m_delay = delay;
        m_mod = mod;
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * 获取字段 dsEx 的值。
     * @return 字段 dsEx 的值。
     */
    public ComplexTreeException getDataSourceException() {
        return m_dsEx;
    }

    /**
     * 设置字段 dsEx 的值。
     * @param dsEx 字段 dsEx 的值。
     */
    public void setDataSourceException(ComplexTreeException dsEx) {
        m_dsEx = dsEx;
    }

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * @see com.zte.ican.gui.component.complextree.IDataSource#load(com.zte.ican.gui.component.complextree.TCacheTreeNode)
     */
    public ComplexTreeNode[] load(ComplexTreeCacheNode parent) throws ComplexTreeException {
        try {
            Thread.sleep(m_delay);

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        if (m_dsEx != null) {
            throw m_dsEx;
        }

        if (parent.getPath().length >= m_depth) {
            return null;
        }

        String base = parent.getUserObject().toString();

        ComplexTreeNode[] children = new ComplexTreeNode[m_size];
        for (int i = 0; i < children.length; i++) {
            if (i % m_mod == 0) {
                children[i] = new ComplexTreeCacheNode(base + "-" + i);

            } else if (i % m_mod == 1) {
                children[i] = new ComplexTreeChoiceNode(base + "-" + i);

            } else {
                children[i] = new ComplexTreeNode(base + "-" + i);
            }
        }

        return children;
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
