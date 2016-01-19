/*
 * �˴��봴���� 2007-9-13 ����09:27:26
 */
package com.apollo.swing.component.complextree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;

/**
 * <p>�ļ����ƣ�ComplexTreeNode.java</p>
 * <p>�ļ��������ۺ����ڵ��࣬��װ���ۺ����ڵ�Ĳ��������ݡ�</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004</p>
 * <p>������˾��������</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2007-9-13</p>
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
public class ComplexTreeNode extends DefaultMutableTreeNode implements IConstants {

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
     * ��ʶ�ڵ��Ƿ�ɱ༭��
     */
    protected volatile boolean m_isEditable = false;

    /**
     * �ڵ����Ⱦ����
     */
    protected TreeCellRenderer m_renderer = null;

    /**
     * �ڵ�༭�������ڵ��ѡ��ڵ㸴ѡ������Ƶ�����shouldSelectCell()�������ᱻ�������õ�������m_editor����ʵ�ִ˷�����
     */
    protected TreeCellEditor m_editor = null;

    /**
     * ��ʶ�ڵ��Ƿ��Զ������ã�������ʾ��
     */
    protected boolean m_isAutoToolTip = false;

    /**
     * ������ʾ�ı���
     */
    protected String m_toolTipText = null;

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     * @since T3 V1.1
     */
    public ComplexTreeNode() {
        this(null);
    }

    /**
     * ���췽����
     * @param userObject �û�����
     * @since T3 V1.1
     */
    public ComplexTreeNode(Object userObject) {
        this(userObject, true);
    }

    /**
     * ���췽����
     * @param userObject �û�����
     * @param allowsChildren ��ʶ�Ƿ�����ýڵ�ӵ���ӽڵ㡣
     * @since T3 V1.1
     */
    public ComplexTreeNode(Object userObject, boolean allowsChildren) {
        super(userObject, allowsChildren);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * �жϽڵ��Ƿ�ɱ༭��
     * @return ��ʶ�ڵ��Ƿ�ɱ༭��
     * @since T3 V1.1
     */
    public boolean isEditable() {
        return m_isEditable;
    }

    /**
     * ���ýڵ��Ƿ�ɱ༭��
     * @param isEditable ��ʶ�ڵ��Ƿ�ɱ༭��
     * @since T3 V1.1
     */
    public void setEditable(boolean isEditable) {
        m_isEditable = isEditable;
    }

    /**
     * ��ȡ�ڵ���Ⱦ����
     * @return �ڵ���Ⱦ����
     * @since T3 V1.1
     */
    public TreeCellRenderer getRenderer() {
        return m_renderer;
    }

    /**
     * ���ýڵ���Ⱦ����
     * @param renderer �ڵ���Ⱦ����
     * @since T3 V1.1
     */
    public void setRenderer(TreeCellRenderer renderer) {
        m_renderer = renderer;
    }

    /**
     * ��ȡ�ڵ�༭����
     * @return �ڵ�༭����
     * @since T3 V1.1
     */
    public TreeCellEditor getEditor() {
        return m_editor;
    }

    /**
     * ���ýڵ�༭����
     * @param editor �ڵ�༭����
     * @since T3 V1.1
     */
    public void setEditor(TreeCellEditor editor) {
        m_editor = editor;
    }

    /**
     * �жϽڵ��Ƿ��Զ������ã�������ʾ��������Ƶ��ڵ㣬�Զ�������ʾ��Ϣ����Ϣ����Ϊ�ڵ��userObject��toString�����ڳ��ڵ㱻���������ס������º����á�
     * @return ��ʶ�ڵ��Ƿ��Զ������ã�������ʾ��
     * @since T3 V1.1
     */
    public boolean isAutoToolTip() {
        return m_isAutoToolTip;
    }

    /**
     * ���ýڵ��Ƿ��Զ������ã�������ʾ��������Ƶ��ڵ㣬�Զ�������ʾ��Ϣ����Ϣ����Ϊ�ڵ��userObject��toString�����ڳ��ڵ㱻���������ס������º����á�
     * @param isAutoToolTip ��ʶ�ڵ��Ƿ��Զ������ã�������ʾ��
     * @since T3 V1.1
     */
    public void setAutoToolTip(boolean isAutoToolTip) {
        m_isAutoToolTip = isAutoToolTip;
    }

    /**
     * ��ȡ������ʾ�ı���
     * @return ������ʾ�ı���
     * @since T3 V1.1
     */
    public String getToolTipText() {
        if (m_toolTipText == null && m_isAutoToolTip) {
            return userObject == null ? null : userObject.toString();

        } else {
            return m_toolTipText;
        }
    }

    /**
     * ���ù�����ʾ�ı��������������ʾ�ı�����autoToolTip��Ч�������õ���ʾ�ı�Ϊ׼��
     * @param toolTipText ������ʾ�ı���
     * @since T3 V1.1
     */
    public void setToolTipText(String toolTipText) {
        m_toolTipText = toolTipText;
    }

    /*------------------------------------- DefaultMutableTreeNode Public Method -------------------------------------*/

    /**
     * @see javax.swing.tree.DefaultMutableTreeNode#insert(javax.swing.tree.MutableTreeNode, int)
     * @throws IllegalArgumentException ���õ��½ڵ㲻��һ��TComplexTreeNode�����õ��½ڵ���һ��TChoiceTreeNode�������ڵ㣨���ڵ㣩����һ��TChoiceTreeNode����׳����쳣��
     */
    public void insert(MutableTreeNode newChild, int childIndex) {
        if (!(newChild instanceof ComplexTreeNode)) {
            throw new IllegalArgumentException("New child is not a TComplexTreeNode !");
        }

        if (newChild instanceof ComplexTreeChoiceNode && !(this instanceof ComplexTreeChoiceNode)) {
            throw new IllegalArgumentException("New child is a TChoiceTreeNode, but parent is not a TChoiceTreeNode !");
        }

        super.insert(newChild, childIndex);
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
