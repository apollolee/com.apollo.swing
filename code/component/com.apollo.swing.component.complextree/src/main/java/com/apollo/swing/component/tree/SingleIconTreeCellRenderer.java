/*
 * �˴��봴���� 2008-8-18 ����10:12:20��
 */
package com.apollo.swing.component.tree;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * <p>�ļ����ƣ�SingleIconTreeCellRenderer.java</p>
 * <p>������������ͼ������Ⱦ���ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-8-18</p>
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
public class SingleIconTreeCellRenderer extends DefaultTreeCellRenderer {

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
     * ��ͼ�ꡣ
     */
    protected Icon m_singleIcon;

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
    public SingleIconTreeCellRenderer() {
        this((Icon) null);
    }

    /**
     * ���췽����
     * @param imageLocation ��ͼ��ͼ���λ�ã��ȰѴ��ַ����ȵ���һ��ϵͳ��Դ����ȥ���ң��Ҳ����͵���һ���ļ���ȥ���ҡ�
     */
    public SingleIconTreeCellRenderer(String imageLocation) {
        /* �����п� */
        if (imageLocation == null) {
            setSingleIcon(null);

        } else {
            /* ��imageLocationָ����λ�û�ȡϵͳ��Դ��URL */
            URL imageUrl = ClassLoader.getSystemResource(imageLocation);

            /* ���ݻ�ȡ��ϵͳ��Դ��URL���в�ͬ�Ĵ��� */
            if (imageUrl == null) { //��ȡ����ϵͳ��Դ
                setSingleIcon(new ImageIcon(imageLocation)); //��imageLocation����һ��filenameȥ����

            } else { //��ȡ����ϵͳ��Դ
                setSingleIcon(new ImageIcon(imageUrl));
            }
        }
    }

    /**
     * ���췽����
     * @param imageUrl ��ͼ��ͼ��ĵ�URL��ַ��
     */
    public SingleIconTreeCellRenderer(URL imageUrl) {
        this(new ImageIcon(imageUrl));
    }

    /**
     * ���췽����
     * @param singleIcon ��ͼ�ꡣ
     * @see SingleIconTreeCellRenderer#setSingleIcon(Icon)
     */
    public SingleIconTreeCellRenderer(Icon singleIcon) {
        setSingleIcon(singleIcon);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * ��ȡ��ͼ�ꡣ
     * @return ��ͼ�ꡣ
     */
    public Icon getSingleIcon() {
        return m_singleIcon;
    }

    /**
     * ���õ�ͼ�ꡣ
     * @param singleIcon ��ͼ�ꡣ
     */
    public void setSingleIcon(Icon singleIcon) {
        m_singleIcon = singleIcon;
    }

    /*------------------------------------- DefaultTreeCellRenderer Public Method -------------------------------------*/

    /**
     * @see javax.swing.tree.DefaultTreeCellRenderer#getOpenIcon()
     */
    public Icon getOpenIcon() {
        return m_singleIcon;
    }

    /**
     * @see javax.swing.tree.DefaultTreeCellRenderer#getClosedIcon()
     */
    public Icon getClosedIcon() {
        return m_singleIcon;
    }

    /**
     * @see javax.swing.tree.DefaultTreeCellRenderer#getLeafIcon()
     */
    public Icon getLeafIcon() {
        return m_singleIcon;
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
