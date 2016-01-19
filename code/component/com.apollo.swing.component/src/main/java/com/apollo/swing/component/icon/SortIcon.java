/*
 * �˴��봴���� 2007-7-1 ����12:28:10
 */
package com.apollo.swing.component.icon;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.UIManager;

import com.apollo.base.util.IBaseConstants;

/**
 * <p>�ļ����ƣ�TSortIcon.java</p>
 * <p>��������������ͼ���ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-5-9</p>
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
public class SortIcon implements Icon, IBaseConstants {

    /*------------------------------------- Public (Static) Inner Class -------------------------------------*/

    /* Add Public (Static) Inner Class */

    /*------------------------------------- Protected (Static) Inner Class -------------------------------------*/

    /* Add Protected (Static) Inner Class */

    /*------------------------------------- Friendly (Static) Inner Class -------------------------------------*/

    /* Add Friendly (Static) Inner Class */

    /*------------------------------------- Private (Static) Inner Class -------------------------------------*/

    /* Add Private (Static) Inner Class */

    /*------------------------------------- Public Static Field -------------------------------------*/

    /**
     * ����״̬------����
     */
    public static final int STATE_ASCENDING = 1;

    /**
     * ����״̬------����
     */
    public static final int STATE_DESCENDING = -1;

    /**
     * ����״̬------������ͨ״̬����
     */
    public static final int STATE_NORMAL = 0;

    /*------------------------------------- Protected Static Field -------------------------------------*/

    /**
     * дͼ��������������е����ʱ���õ����塣
     */
    protected static final Font SMALL_FONT = new Font("Serif", Font.TRUETYPE_FONT, 10);

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
     * ������߽���ɫ��
     */
    protected Color m_edgeColor1;

    /**
     * ӭ����߽���ɫ��
     */
    protected Color m_edgeColor2;

    /**
     * ͼ�������ɫ��
     */
    protected Color m_fillColor;

    /**
     * ͼ��Ĵ�С����Ϊͼ���������Σ������С���Ǳ߳�����
     */
    protected int m_size;

    /**
     * ��ǰ����Ⱦ�����������������е���ţ����Խ��ǰ���������������������Խ��
     */
    protected int m_index;

    /**
     * ����״̬��
     */
    protected int m_state;

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
    public SortIcon() {
        this(STATE_NORMAL, false, false);
    }

    /**
     * ���췽����
     * @param state ����״̬��
     * @param isRaisedView ͼ���Ƿ�͹��ʾ��
     * @param isPressedView װ��ͼ��������Ƿ񱻰��¡�
     */
    public SortIcon(int state, boolean isRaisedView, boolean isPressedView) {
        this(1, state, isRaisedView, isPressedView);
    }

    /**
     * ���췽����
     * @param index ��ʾ��ͼ���ϵ����֡�
     * @param state ����״̬��
     * @param isRaisedView ͼ���Ƿ�͹��ʾ��
     * @param isPressedView װ��ͼ��������Ƿ񱻰��¡�
     */
    public SortIcon(int index, int state, boolean isRaisedView, boolean isPressedView) {
        m_index = index;

        if (isRaisedView) {
            if (isPressedView) {
                initialize(UIManager.getColor("controlLtHighlight"),
                           UIManager.getColor("controlDkShadow"),
                           UIManager.getColor("controlShadow"),
                           18,
                           state);

            } else {
                initialize(UIManager.getColor("controlHighlight"),
                           UIManager.getColor("controlShadow"),
                           UIManager.getColor("control"),
                           18,
                           state);
            }

        } else {
            /* ���ǵ�ͼ������־���С��Ϊ���ۺͱ����û����ϣ���������isPressedView��ͳһ������ɫ���� -- ��ΰ */
            initialize(UIManager.getColor("controlDkShadow"),
                       UIManager.getColor("controlLtHighlight"),
                       UIManager.getColor("controlShadow"),
                       18,
                       state);
        }
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * ��ȡ����Ⱦ��������������е���š�
     * @return ����Ⱦ��������������е���š�
     */
    public int getIndex() {
        return m_index;
    }

    /**
     * ���ô���Ⱦ��������������е���š�
     * @param index ����Ⱦ��������������е���š�
     */
    public void setIndex(int index) {
        m_index = index;
    }

    /**
     * ��ȡ��������״̬��
     * @return ����״̬��
     */
    public int getState() {
        return m_state;
    }

    /**
     * ��������״̬��
     * @param state ����״̬��
     */
    public void setState(int state) {
        m_state = state;
    }

    /*------------------------------------- Object Public Method -------------------------------------*/

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append(I18N_INFO_SORT).append(": ");

        if (STATE_NORMAL == m_state) {
            sb.append(I18N_INFO_NONE);

        } else {
            if (STATE_ASCENDING == m_state) {
                sb.append(I18N_INFO_ASCENDING);

            } else {
                sb.append(I18N_INFO_DESCENDING);
            }

            sb.append(" , ").append(I18N_INFO_INDEX).append(": ");
            sb.append(m_index);
        }

        return sb.toString();
    }

    /*------------------------------------- Icon Public Method -------------------------------------*/

    /**
     * @see javax.swing.Icon#getIconHeight()
     */
    public int getIconHeight() {
        return m_state == STATE_NORMAL ? 0 : m_size;
    }

    /**
     * @see javax.swing.Icon#getIconWidth()
     */
    public int getIconWidth() {
        return m_state == STATE_NORMAL ? 0 : m_size;
    }

    /**
     * @see javax.swing.Icon#paintIcon(java.awt.Component, java.awt.Graphics, int, int)
     */
    public void paintIcon(Component c, Graphics g, int x, int y) {
        switch (m_state) {
        case STATE_ASCENDING:
            drawUpArrow(g, x, y);
            break;

        case STATE_DESCENDING:
            drawDownArrow(g, x, y);
            break;

        case STATE_NORMAL:
            drawBlank(g, x, y);
            break;

        default:
            break;
        }
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /**
     * ��ʼ��ͼ�ꡣ
     * @param edgeColor1 ������߽���ɫ��
     * @param edgeColor2 ӭ����߽���ɫ��
     * @param fillColor �����ɫ��
     * @param size ͼ��ߴ硣
     * @param state ����״̬��
     */
    private void initialize(Color edgeColor1, Color edgeColor2, Color fillColor, int size, int state) {
        m_edgeColor1 = edgeColor1;
        m_edgeColor2 = edgeColor2;
        m_fillColor = fillColor;
        m_size = size;
        m_state = state;
    }

    /**
     * ����״Ϊ�����ǵ�ͼ�ꡣ
     * @param g ͼ���豸�����
     * @param x ͼ��滭��ʼԭ���x���ꡣ
     * @param y ͼ��滭��ʼԭ���y���ꡣ
     */
    private void drawUpArrow(Graphics g, int x, int y) {
        /* ���������ζ��� */
        g.setColor(m_edgeColor1);
        int offsetX = x + m_size / 2;
        g.drawLine(offsetX, y, offsetX, y);

        /* �������굽������ƫ��һ������λ�� */
        offsetX--;
        int offsetY = y + 1;
        for (int dx = 0; y + m_size > offsetY + 3; dx += 2) {
            g.setColor(m_edgeColor1);
            g.drawLine(offsetX, offsetY, offsetX + 1, offsetY); //����������������Ӱ
            g.drawLine(offsetX, offsetY + 1, offsetX + 1, offsetY + 1); //����������������Ӱ��ֻ��y�������µ�����һ������

            if (dx > 0) {
                g.setColor(m_fillColor);
                g.drawLine(offsetX + 2, offsetY, offsetX + 1 + dx, offsetY); //ʹ�����ɫ�������
                g.drawLine(offsetX + 2, offsetY + 1, offsetX + 1 + dx, offsetY + 1); //ʹ�����ɫ�������
            }

            g.setColor(m_edgeColor2);
            g.drawLine(offsetX + dx + 2, offsetY, offsetX + dx + 3, offsetY); //�������������ҷ���
            g.drawLine(offsetX + dx + 2, offsetY + 1, offsetX + dx + 3, offsetY + 1); //�������������ҷ��⣬ֻ��y�������µ�����һ������
            offsetX--;
            offsetY += 2; // ÿ��ѭ����y�����·��򲽽��Ŀ��Ϊ2����
        }

        /* �����������һ����Ӱ */
        g.setColor(m_edgeColor1);
        g.drawLine(x, (y + m_size) - 3, x + 1, (y + m_size) - 3);
        g.setColor(m_edgeColor2);
        g.drawLine(x + 2, (y + m_size) - 2, (x + m_size) - 1, (y + m_size) - 2); //�������������������ҵıߣ�ģ�ⷴ��Ч��
        g.drawLine(x, (y + m_size) - 1, x + m_size, (y + m_size) - 1); //�������������ң�ģ�ⷴ��Ч��

        /* �����������ڵ���������� */
        Font font = g.getFont();
        g.setColor(Color.black);
        g.setFont(SMALL_FONT);
        //g.drawString(m_sortNumber, (x + m_size / 2) - 2, y + m_size / 2 + 4);
        g.drawString(String.valueOf(m_index), (x + m_size / 2) - 3, y + m_size / 2 + 4); //���ǵ�����Ϊ��λ��ʱ������������ʾ��ȫ���ʵ���drawString�������������ƶ�1������ -- ��ΰ
        g.setFont(font);
    }

    /**
     * ������״Ϊ�����ǵ�ͼ�ꡣ
     * @param g ͼ���豸�����
     * @param x ͼ��滭��ʼԭ���x���ꡣ
     * @param y ͼ��滭��ʼԭ���y���ꡣ
     */
    private void drawDownArrow(Graphics g, int x, int y) {
        g.setColor(m_edgeColor1);
        g.drawLine(x, y, (x + m_size) - 1, y); //�������ǵ��ϱ߽�
        g.drawLine(x, y + 1, (x + m_size) - 3, y + 1); //���ϱ߽����Ӱ

        g.setColor(m_edgeColor2);
        g.drawLine((x + m_size) - 2, y + 1, (x + m_size) - 1, y + 1); //���ϱ߽����Ӱ�Ĳ��ಿ�֣�ģ����ò��ָ���Դ�յ���Ч��

        /* x,y��λ��ָ���ϱ߽���Ӱ�������λ�� */
        int offsetX = x + 1;
        int offsetY = y + 2;
        for (int dx = m_size - 6; y + m_size > offsetY + 1; dx -= 2) {
            g.setColor(m_edgeColor1);
            g.drawLine(offsetX, offsetY, offsetX + 1, offsetY); //����������������Ӱ
            g.drawLine(offsetX, offsetY + 1, offsetX + 1, offsetY + 1); //����������������Ӱ��ֻ��y�������µ�����һ������

            if (dx > 0) {
                g.setColor(m_fillColor);
                g.drawLine(offsetX + 2, offsetY, offsetX + 1 + dx, offsetY); //ʹ�����ɫ�������
                g.drawLine(offsetX + 2, offsetY + 1, offsetX + 1 + dx, offsetY + 1); //ʹ�����ɫ�������
            }

            g.setColor(m_edgeColor2);
            g.drawLine(offsetX + dx + 2, offsetY, offsetX + dx + 3, offsetY); //�������������ҷ���
            g.drawLine(offsetX + dx + 2, offsetY + 1, offsetX + dx + 3, offsetY + 1); //�������������ҷ��⣬ֻ��y�������µ�����һ������
            offsetX++;
            offsetY += 2; //ÿ��ѭ����y�����·��򲽽��Ŀ��Ϊ2����
        }

        /* �����������¶��� */
        g.setColor(m_edgeColor1);
        g.drawLine(x + m_size / 2, (y + m_size) - 1, x + m_size / 2, (y + m_size) - 1);

        /* �����������ڵ���������� */
        Font font = g.getFont();
        g.setColor(Color.black);
        g.setFont(SMALL_FONT);
        //g.drawString(m_sortNumber, (x + m_size / 2) - 2, y + m_size / 2 + 2);
        g.drawString(String.valueOf(m_index), (x + m_size / 2) - 3, y + m_size / 2 + 2); //���ǵ�����Ϊ��λ��ʱ������������ʾ��ȫ���ʵ���drawString�������������ƶ�1������ -- ��ΰ
        g.setFont(font);
    }

    /**
     * ��û�м�ͷ��ͼ�ꡣ
     * @param g ͼ���豸�����
     * @param x ͼ��滭��ʼԭ���x���ꡣ
     * @param y ͼ��滭��ʼԭ���y���ꡣ
     */
    private void drawBlank(Graphics g, int x, int y) {
        //g.drawRect(x, y, m_size - 1, m_size - 1);
    }

}
