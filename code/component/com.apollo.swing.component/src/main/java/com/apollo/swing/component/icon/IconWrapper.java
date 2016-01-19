/*
 * �˴��봴���� 2008-9-19 ����09:41:38��
 */
package com.apollo.swing.component.icon;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;
import javax.swing.UIManager;

/**
 * <p>�ļ����ƣ�TIconWrapper.java</p>
 * <p>����������ͼ���װ���ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-9-19</p>
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
public class IconWrapper implements Icon {

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
     * ����ѡ���ͼ���װ����
     * @return ѡ���ͼ���װ����
     */
    public static IconWrapper createCheckBoxIconWrapper() {
        return new IconWrapper(UIManager.getIcon("CheckBox.icon"));
    }

    /**
     * ������ѡ��ťͼ���װ����
     * @return ��ѡ��ťͼ���װ����
     */
    public static IconWrapper createRadioButtonIconWrapper() {
        return new IconWrapper(UIManager.getIcon("RadioButton.icon"));
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
     * ����װ��ͼ�ꡣ
     */
    protected Icon m_icon;

    /**
     * ��ʶ�Ƿ��ڲ�ȷ��״̬��
     */
    protected volatile boolean m_isIndetermined;

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     * @param icon ����װ��ͼ�ꡣ
     */
    public IconWrapper(Icon icon) {
        this(icon, false);
    }

    /**
     * ���췽����
     * @param icon ����װ��ͼ�ꡣ
     * @param isIndetermined ��ʶ�Ƿ��ڲ�ȷ��״̬��
     */
    public IconWrapper(Icon icon, boolean isIndetermined) {
        m_icon = icon;
        m_isIndetermined = isIndetermined;
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * ��ȡ�ֶ� icon ��ֵ��
     * @return �ֶ� icon ��ֵ��
     */
    public Icon getIcon() {
        return m_icon;
    }

    /**
     * �����ֶ� icon ��ֵ��
     * @param icon �ֶ� icon ��ֵ��
     */
    public void setIcon(Icon icon) {
        m_icon = icon;
    }

    /**
     * ��ȡ�ֶ� isIndetermined ��ֵ��
     * @return �ֶ� isIndetermined ��ֵ��
     */
    public boolean isIndetermined() {
        return m_isIndetermined;
    }

    /**
     * �����ֶ� isIndetermined ��ֵ��
     * @param isIndetermined �ֶ� isIndetermined ��ֵ��
     */
    public void setIndetermined(boolean isIndetermined) {
        m_isIndetermined = isIndetermined;
    }

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * @see javax.swing.Icon#getIconHeight()
     */
    public int getIconHeight() {
        return m_icon.getIconHeight();
    }

    /**
     * @see javax.swing.Icon#getIconWidth()
     */
    public int getIconWidth() {
        return m_icon.getIconWidth();
    }

    /**
     * @see javax.swing.Icon#paintIcon(java.awt.Component, java.awt.Graphics, int, int)
     */
    public void paintIcon(Component c, Graphics g, int x, int y) {
        /* ��ȡ2D��ͼ��� */
        Graphics2D g2d = (Graphics2D) g.create();

        /* ���û�ͼ���Ϊ��͸��״̬ */
        if (m_isIndetermined) { // ��ǰѡ��״̬���ڲ�ȷ��״̬
            /* TODO ע�⣺����û���ҵ�������Ⱦ��ȷ��״̬ѡ���ķ����������ṩ�������ַ���������ά����ѡ��Ͳο�����ѡһ�� */

            /* ���ñ���ɫ����Ⱦ��ȷ��״̬ѡ���ע�⣺TODO �����ú͹۸���أ���ͬ�۸�����Ҫ��������ֵ����ʱû�ҵ����õĽ������ */
            g2d.setBackground(Color.GRAY);
            g2d.clearRect(6, (c.getHeight() - 10) / 2, 10, 10);
            /* ����͸��������Ⱦ��ȷ��״̬ѡ��� */
            //g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)); // ��ȾΪ��͸��Ч��
        }

        /* ����ͼ�� */
        m_icon.paintIcon(c, g2d, x, y);
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
