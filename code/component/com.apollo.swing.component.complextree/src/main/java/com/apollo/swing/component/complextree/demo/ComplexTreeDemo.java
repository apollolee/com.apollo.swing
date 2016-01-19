/*
 * �˴��봴���� 2008-10-21 ����04:04:44��
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
 * <p>�ļ����ƣ�ComplexTreeDemo.java</p>
 * <p>�����������ۺ�����ʾ�ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-10-21</p>
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
     * ������Դ��Ⱦ����
     */
    protected static TreeCellRenderer s_netResRenderer = new SingleIconTreeCellRenderer("com/apollo/swing/component/complextree/demo/net-res.gif");

    /**
     * ��Ԫ����Ⱦ����
     */
    protected static TreeCellRenderer s_neGroupRenderer = new SingleIconTreeCellRenderer("com/apollo/swing/component/complextree/demo/ne-group.gif");

    /**
     * ��Ԫ��Ⱦ����
     */
    protected static TreeCellRenderer s_neRenderer = new SingleIconTreeCellRenderer("com/apollo/swing/component/complextree/demo/ne.gif");

    /**
     * ������Ⱦ����
     */
    protected static TreeCellRenderer s_boardRenderer = new SingleIconTreeCellRenderer("com/apollo/swing/component/complextree/demo/board.gif");

    /**
     * �˿���Ⱦ����
     */
    protected static TreeCellRenderer s_portRenderer = new SingleIconTreeCellRenderer("com/apollo/swing/component/complextree/demo/port.gif");

    /**
     * ��ѡ��ڵ���Ⱦ����
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
     * ���ۺ��������ı�������λ��
     * @param text �ı���
     * @param tree �ۺ�����
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
     * @param tree �ۺ�����
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
     * ���ۺ����ڵ�����ı�������λ��
     * @param text �ı���
     * @param tree �ۺ�����
     * @param nodes �ۺ����ڵ㼯��
     * @return ��ʶ�Ƿ�������ָ�����ı���
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
     * ��������
     * @param args ������
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
     * ��ѡ�е�·����
     */
    protected TreePath[] m_chosenPaths = null;

    /**
     * ģ�͡�
     */
    protected ComplexTreeModel m_model = new ComplexTreeModel(new ComplexTreeNode("root"));

    /**
     * ����
     */
    protected ComplexTree m_tree = new ComplexTree(m_model);

    /**
     * ��Ϣ��
     */
    protected JTextArea m_msg = new JTextArea();

    /**
     * ����------�ۺ�����
     */
    protected Action m_complex = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "�ۺ���", 'C');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            /* ������ڵ� */
            ComplexTreeNode netRes = new ComplexTreeNode("������Դ");
            netRes.setRenderer(s_netResRenderer);

            /* �����һ��ڵ� */
            /* for netRes */
            ComplexTreeNode neGroup1 = new ComplexTreeNode("��Ԫ�� 1");
            neGroup1.setRenderer(s_neGroupRenderer);
            neGroup1.setToolTipText("���Թ�����ʾ�ı���--- ��Ԫ�� 1");

            ComplexTreeNode neGroup2 = new ComplexTreeNode("��Ԫ�� 2");
            neGroup2.setRenderer(s_neGroupRenderer);

            ComplexTreeNode neGroup3 = new ComplexTreeNode("��Ԫ�� 3");
            neGroup3.setRenderer(s_neGroupRenderer);

            ComplexTreeNode neGroup4 = new ComplexTreeNode("��Ԫ�� 4���ɱ༭��");
            neGroup4.setRenderer(s_neGroupRenderer);
            neGroup4.setEditable(true);
            neGroup4.setToolTipText("���Թ�����ʾ�ı���--- ��Ԫ�� 4���ɱ༭��");

            ComplexTreeNode ctn1 = new ComplexTreeNode("��ͨ�ۺ����ڵ�");
            ctn1.setRenderer(s_noSelectionRenderer);

            /* ��ӵ�һ��ڵ� */
            /* for netRes */
            netRes.add(neGroup1);
            netRes.add(neGroup2);
            netRes.add(neGroup3);
            netRes.add(neGroup4);
            netRes.add(ctn1);

            /* ����ڶ���ڵ� */
            /* for neGroup1 */
            ComplexTreeNode ne11 = new ComplexTreeNode("��Ԫ 1-1���ɱ༭��");
            ne11.setRenderer(s_neRenderer);
            ne11.setEditable(true);

            ComplexTreeNode ne12 = new ComplexTreeNode("��Ԫ 1-2");
            ne12.setRenderer(s_neRenderer);

            ComplexTreeNode ne13 = new ComplexTreeNode("��Ԫ 1-3");
            ne13.setRenderer(s_neRenderer);

            ComplexTreeNode ctn2 = new ComplexTreeNode("��ͨ�ۺ����ڵ㣨�ɱ༭��");
            ctn2.setRenderer(s_noSelectionRenderer);
            ctn2.setEditable(true);
            ctn2.setToolTipText("���Թ�����ʾ�ı���--- ��ͨ�ۺ����ڵ㣨�ɱ༭��");

            /* for neGroup2 */
            ComplexTreeNode ne21 = new ComplexTreeNode("��Ԫ 2-1");
            ne21.setRenderer(s_neRenderer);

            /* for neGroup3 */
            ComplexTreeNode ne31 = new ComplexTreeNode("��Ԫ 3-1");
            ne31.setRenderer(s_neRenderer);

            ComplexTreeNode ne32 = new ComplexTreeNode("��Ԫ 3-2");
            ne32.setRenderer(s_neRenderer);

            /* ��ӵڶ���ڵ� */
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

            /* ���������ڵ� */
            /* for ne12 */
            ComplexTreeNode board121 = new ComplexTreeNode("���� 1-2-1");
            board121.setRenderer(s_boardRenderer);

            ComplexTreeNode board122 = new ComplexTreeNode("���� 1-2-2");
            board122.setRenderer(s_boardRenderer);

            ComplexTreeNode board123 = new ComplexTreeNode("���� 1-2-3");
            board123.setRenderer(s_boardRenderer);

            /* ��ӵ�����ڵ� */
            /* for ne12 */
            ne12.add(board121);
            ne12.add(board122);
            ne12.add(board123);

            /* ������Ĳ�ڵ� */
            /* for board122 */
            ComplexTreeNode port1221 = new ComplexTreeNode("�˿� 1-2-2-1");
            port1221.setRenderer(s_portRenderer);

            ComplexTreeNode port1222 = new ComplexTreeNode("�˿� 1-2-2-2");
            port1222.setRenderer(s_portRenderer);

            /* custom */
            ComplexTreeNode customStn = new ComplexTreeNode(new Integer(2));
            customStn.setRenderer(new CustomCellRenderer());
            customStn.setEditor(new CustomCellEditor());
            customStn.setEditable(true);

            /* ��ӵ��Ĳ�ڵ� */
            /* for board122 */
            board122.add(port1221);
            board122.add(port1222);
            board122.add(customStn);

            m_model.setRoot(netRes);
        }

    };

    /**
     * ����------ѡ������
     */
    protected Action m_choice = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "ѡ����", 'O');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            /* �����ѡ��ڵ� */
            ComplexTreeChoiceNode netRes = new ComplexTreeChoiceNode("������Դ");
            netRes.setRenderer(s_netResRenderer);

            /* �����һ��ڵ� */
            /* for netRes */
            ComplexTreeChoiceNode neGroup1 = new ComplexTreeChoiceNode("��Ԫ�� 1");
            neGroup1.setRenderer(s_neGroupRenderer);
            neGroup1.setToolTipText("���Թ�����ʾ�ı���--- ��Ԫ�� 1");

            ComplexTreeChoiceNode neGroup2 = new ComplexTreeChoiceNode("��Ԫ�� 2");
            neGroup2.setRenderer(s_neGroupRenderer);

            ComplexTreeChoiceNode neGroup3 = new ComplexTreeChoiceNode("��Ԫ�� 3");
            neGroup3.setRenderer(s_neGroupRenderer);

            ComplexTreeChoiceNode neGroup4 = new ComplexTreeChoiceNode("��Ԫ�� 4���ɱ༭��");
            neGroup4.setRenderer(s_neGroupRenderer);
            neGroup4.setEditable(true);
            neGroup4.setToolTipText("���Թ�����ʾ�ı���--- ��Ԫ�� 4���ɱ༭��");

            ComplexTreeNode ctn1 = new ComplexTreeNode("��ͨ�ۺ����ڵ�");
            ctn1.setRenderer(s_noSelectionRenderer);

            /* ��ӵ�һ��ڵ� */
            /* for netRes */
            netRes.add(neGroup1);
            netRes.add(neGroup2);
            netRes.add(neGroup3);
            netRes.add(neGroup4);
            netRes.add(ctn1);

            /* ����ڶ���ڵ� */
            /* for neGroup1 */
            ComplexTreeChoiceNode ne11 = new ComplexTreeChoiceNode("��Ԫ 1-1���ɱ༭��");
            ne11.setRenderer(s_neRenderer);
            ne11.setEditable(true);

            ComplexTreeChoiceNode ne12 = new ComplexTreeChoiceNode("��Ԫ 1-2");
            ne12.setRenderer(s_neRenderer);

            ComplexTreeChoiceNode ne13 = new ComplexTreeChoiceNode("��Ԫ 1-3");
            ne13.setRenderer(s_neRenderer);

            ComplexTreeNode ctn2 = new ComplexTreeNode("��ͨ�ۺ����ڵ㣨�ɱ༭��");
            ctn2.setRenderer(s_noSelectionRenderer);
            ctn2.setEditable(true);
            ctn2.setToolTipText("���Թ�����ʾ�ı���--- ��ͨ�ۺ����ڵ㣨�ɱ༭��");

            /* for neGroup2 */
            ComplexTreeChoiceNode ne21 = new ComplexTreeChoiceNode("��Ԫ 2-1");
            ne21.setRenderer(s_neRenderer);

            /* for neGroup3 */
            ComplexTreeChoiceNode ne31 = new ComplexTreeChoiceNode("��Ԫ 3-1");
            ne31.setRenderer(s_neRenderer);

            ComplexTreeChoiceNode ne32 = new ComplexTreeChoiceNode("��Ԫ 3-2");
            ne32.setRenderer(s_neRenderer);

            /* ��ӵڶ���ڵ� */
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

            /* ���������ڵ� */
            /* for ne12 */
            ComplexTreeChoiceNode board121 = new ComplexTreeChoiceNode("���� 1-2-1");
            board121.setRenderer(s_boardRenderer);

            ComplexTreeChoiceNode board122 = new ComplexTreeChoiceNode("���� 1-2-2");
            board122.setRenderer(s_boardRenderer);

            ComplexTreeChoiceNode board123 = new ComplexTreeChoiceNode("���� 1-2-3");
            board123.setRenderer(s_boardRenderer);

            /* ��ӵ�����ڵ� */
            /* for ne12 */
            ne12.add(board121);
            ne12.add(board122);
            ne12.add(board123);

            /* ������Ĳ�ڵ� */
            /* for board122 */
            ComplexTreeChoiceNode port1221 = new ComplexTreeChoiceNode("�˿� 1-2-2-1");
            port1221.setRenderer(s_portRenderer);

            ComplexTreeChoiceNode port1222 = new ComplexTreeChoiceNode("�˿� 1-2-2-2");
            port1222.setRenderer(s_portRenderer);

            /* custom */
            ComplexTreeChoiceNode customStn = new ComplexTreeChoiceNode(new Integer(2));
            customStn.setRenderer(new CustomCellRenderer());
            customStn.setEditor(new CustomCellEditor());
            customStn.setEditable(true);

            /* ��ӵ��Ĳ�ڵ� */
            /* for board122 */
            board122.add(port1221);
            board122.add(port1222);
            board122.add(customStn);

            m_model.setRoot(netRes);
        }

    };

    /**
     * ����------��������
     */
    protected Action m_cache = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "������", 'A');
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
     * ����------��ȡ��ѡ��·����
     */
    protected Action m_getChosenPaths = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "��ȡ��ѡ��·��", 'G');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_msg.append("---------------------------------��ѡ��·������ʼ��---------------------------------\n");
            m_chosenPaths = m_model.getChosenPaths();
            for (int i = 0; i < m_chosenPaths.length; i++) {
                m_msg.append(m_chosenPaths[i].toString() + "\n");
            }
            m_msg.append("---------------------------------��ѡ��·����������---------------------------------\n");
        }

    };

    /**
     * ����------������ѡ��·����
     */
    protected Action m_setChosenPaths = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "������ѡ��·��", 'S');
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
     * ����------��ȡ������Ļ���ڵ㡣
     */
    protected Action m_getCaches = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "��ȡ������Ļ���ڵ�", 'G');
            setEnabled(false);
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            /*m_msg.append("---------------------------------������Ļ���ڵ㣨��ʼ��---------------------------------\n");
            for (int i = 0, size = m_model.m_caches.size(); i < size; i++) {
            	m_msg.append(m_model.m_caches.get(i).toString() + "\n");
            }
            m_msg.append("---------------------------------������Ļ���ڵ㣨������---------------------------------\n");*/
        }

    };

    /**
     * ����------�����Ϣ��
     */
    protected Action m_clearMsg = new AbstractAction() {

        {
            BaseUtilities.setAction(this, "�����Ϣ", 'R');
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            m_msg.setText("");
        }

    };

    /**
     * ����------ͬ��ѡ��
     */
    protected JCheckBox m_syncChoice = new JCheckBox(new AbstractAction() {

        {
            BaseUtilities.setAction(this, "ͬ��ѡ��", 'S');
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
     * ����------����ѡ��
     */
    protected JCheckBox m_enabledChoice = new JCheckBox(new AbstractAction() {

        {
            BaseUtilities.setAction(this, "����ѡ��", 'E');
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
     * ���췽����
     */
    public ComplexTreeDemo() {
        super(new GridBagLayout());

        m_msg.setEditable(false);
        m_model.addComplexTreeModelListener(this);
        m_enabledChoice.setSelected(true);

        JScrollPane treeView = new JScrollPane(m_tree);
        //m_tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        treeView.setBorder(BorderFactory.createTitledBorder("�ۺ���"));

        JScrollPane msgView = new JScrollPane(m_msg);
        msgView.setBorder(BorderFactory.createTitledBorder("��Ϣ"));

        JSplitPane treeAndMsgView = new JSplitPane(JSplitPane.VERTICAL_SPLIT, treeView, msgView);
        treeAndMsgView.setDividerLocation(300);

        JPanel btnView = new JPanel(new GridBagLayout());
        btnView.setBorder(BorderFactory.createTitledBorder("����"));
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
        JButton bt = new JButton("��ѯ");
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
        m_msg.append("�ڵ�ѡ���Ѹı䣨" + (evt.isSelected() ? "��ѡ��" : "ȥѡ��") + "����" + Arrays.asList(evt.getPahts()) + "\n");
    }

    /**
     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#nodeWillLoad(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
     */
    public void nodeWillLoad(ComplexTreeModelEvent evt) {
        m_msg.append("�ڵ㼴�����룺" + evt.getSource() + "\n");
    }

    /**
     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#nodeLoaded(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
     */
    public void nodeLoaded(ComplexTreeModelEvent evt) {
        m_msg.append("�ڵ������룺" + evt.getSource() + "\n");
    }

    /**
     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#nodeLoadFailed(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
     */
    public void nodeLoadFailed(ComplexTreeModelEvent evt) {
        m_msg.append("�ڵ�������ʧ�ܣ�" + evt.getException() + "\n");
    }

    /**
     * @see com.apollo.swing.component.complextree.IComplexTreeModelListener#releaseCache(com.apollo.swing.component.complextree.ComplexTreeModelEvent)
     */
    public void releaseCache(ComplexTreeModelEvent evt) {
        m_msg.append("�ͷŻ��棺" + evt.getCaches() + "\n");
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}

/**
 * <p>�ļ����ƣ�ComplexTreeDemo.java</p>
 * <p>�������������ƽڵ���Ⱦ���ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-10-22</p>
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
     * ���췽����
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
 * <p>�ļ����ƣ�ComplexTreeDemo.java</p>
 * <p>�������������ƽڵ�༭���ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-10-22</p>
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
     * �༭�����
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
     * ���췽����
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
                /* ����ʵ�� */
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
                /* ����ʵ�� */
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
 * <p>�ļ����ƣ�MockDataSource.java</p>
 * <p>������������׮�õ��ۺ���ģ������Դ��</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-3-27</p>
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
     * �ߴ硣
     */
    protected int m_size;

    /**
     * ��ȡ�
     */
    protected int m_depth;

    /**
     * �ӳ١�
     */
    protected long m_delay;

    /**
     * ģ��
     */
    protected int m_mod;

    /**
     * �쳣��
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
     * ���췽����
     */
    public MockDataSource() {
        this(10, 5, 1000, 3);
    }

    /**
     * ���췽����
     * @param size �ߴ硣
     * @param depth ��ȡ�
     * @param delay �ӳ١�
     * @param mod ģ��
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
     * ��ȡ�ֶ� dsEx ��ֵ��
     * @return �ֶ� dsEx ��ֵ��
     */
    public ComplexTreeException getDataSourceException() {
        return m_dsEx;
    }

    /**
     * �����ֶ� dsEx ��ֵ��
     * @param dsEx �ֶ� dsEx ��ֵ��
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
