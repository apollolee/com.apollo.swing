/*
 * 此代码创建于 2007-7-1 下午12:28:10
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
 * <p>文件名称：TSortIcon.java</p>
 * <p>类型描述：排序图标类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-5-9</p>
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
     * 排序状态------升序。
     */
    public static final int STATE_ASCENDING = 1;

    /**
     * 排序状态------降序。
     */
    public static final int STATE_DESCENDING = -1;

    /**
     * 排序状态------无序（普通状态）。
     */
    public static final int STATE_NORMAL = 0;

    /*------------------------------------- Protected Static Field -------------------------------------*/

    /**
     * 写图标所代表的排序列的序号时所用的字体。
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
     * 背光面边界颜色。
     */
    protected Color m_edgeColor1;

    /**
     * 迎光面边界颜色。
     */
    protected Color m_edgeColor2;

    /**
     * 图标填充颜色。
     */
    protected Color m_fillColor;

    /**
     * 图标的大小（因为图标是正方形，这个大小就是边长）。
     */
    protected int m_size;

    /**
     * 当前所渲染的列在所有排序列中的序号，序号越靠前的列在排序中所起的作用越大。
     */
    protected int m_index;

    /**
     * 排序状态。
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
     * 构造方法。
     */
    public SortIcon() {
        this(STATE_NORMAL, false, false);
    }

    /**
     * 构造方法。
     * @param state 排序状态。
     * @param isRaisedView 图标是否浮凸显示。
     * @param isPressedView 装载图标的容器是否被按下。
     */
    public SortIcon(int state, boolean isRaisedView, boolean isPressedView) {
        this(1, state, isRaisedView, isPressedView);
    }

    /**
     * 构造方法。
     * @param index 显示在图标上的数字。
     * @param state 排序状态。
     * @param isRaisedView 图标是否浮凸显示。
     * @param isPressedView 装载图标的容器是否被按下。
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
            /* 考虑到图标和数字均较小，为美观和便于用户辨认，不再区分isPressedView，统一采用深色背景 -- 李伟 */
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
     * 获取此渲染器所服务的排序列的序号。
     * @return 此渲染器所服务的排序列的序号。
     */
    public int getIndex() {
        return m_index;
    }

    /**
     * 设置此渲染器所服务的排序列的序号。
     * @param index 此渲染器所服务的排序列的序号。
     */
    public void setIndex(int index) {
        m_index = index;
    }

    /**
     * 获取设置排序状态。
     * @return 排序状态。
     */
    public int getState() {
        return m_state;
    }

    /**
     * 设置排序状态。
     * @param state 排序状态。
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
     * 初始化图标。
     * @param edgeColor1 背光面边界颜色。
     * @param edgeColor2 迎光面边界颜色。
     * @param fillColor 填充颜色。
     * @param size 图标尺寸。
     * @param state 排序状态。
     */
    private void initialize(Color edgeColor1, Color edgeColor2, Color fillColor, int size, int state) {
        m_edgeColor1 = edgeColor1;
        m_edgeColor2 = edgeColor2;
        m_fillColor = fillColor;
        m_size = size;
        m_state = state;
    }

    /**
     * 画形状为正三角的图标。
     * @param g 图形设备句柄。
     * @param x 图标绘画起始原点的x坐标。
     * @param y 图标绘画起始原点的y坐标。
     */
    private void drawUpArrow(Graphics g, int x, int y) {
        /* 画正三角形顶点 */
        g.setColor(m_edgeColor1);
        int offsetX = x + m_size / 2;
        g.drawLine(offsetX, y, offsetX, y);

        /* 调整坐标到顶点下偏左一个象素位置 */
        offsetX--;
        int offsetY = y + 1;
        for (int dx = 0; y + m_size > offsetY + 3; dx += 2) {
            g.setColor(m_edgeColor1);
            g.drawLine(offsetX, offsetY, offsetX + 1, offsetY); //画正三角形左弦阴影
            g.drawLine(offsetX, offsetY + 1, offsetX + 1, offsetY + 1); //画正三角形左弦阴影，只是y坐标向下调整了一个象素

            if (dx > 0) {
                g.setColor(m_fillColor);
                g.drawLine(offsetX + 2, offsetY, offsetX + 1 + dx, offsetY); //使用填充色进行填充
                g.drawLine(offsetX + 2, offsetY + 1, offsetX + 1 + dx, offsetY + 1); //使用填充色进行填充
            }

            g.setColor(m_edgeColor2);
            g.drawLine(offsetX + dx + 2, offsetY, offsetX + dx + 3, offsetY); //画正三角形右弦反光
            g.drawLine(offsetX + dx + 2, offsetY + 1, offsetX + dx + 3, offsetY + 1); //画正三角形右弦反光，只是y坐标向下调整了一个象素
            offsetX--;
            offsetY += 2; // 每次循环，y轴向下方向步进的跨幅为2象素
        }

        /* 画左弦上最后一个阴影 */
        g.setColor(m_edgeColor1);
        g.drawLine(x, (y + m_size) - 3, x + 1, (y + m_size) - 3);
        g.setColor(m_edgeColor2);
        g.drawLine(x + 2, (y + m_size) - 2, (x + m_size) - 1, (y + m_size) - 2); //画紧挨着正三角形下弦的边，模拟反光效果
        g.drawLine(x, (y + m_size) - 1, x + m_size, (y + m_size) - 1); //画正三角形下弦，模拟反光效果

        /* 画倒三角形内的排序列序号 */
        Font font = g.getFont();
        g.setColor(Color.black);
        g.setFont(SMALL_FONT);
        //g.drawString(m_sortNumber, (x + m_size / 2) - 2, y + m_size / 2 + 4);
        g.drawString(String.valueOf(m_index), (x + m_size / 2) - 3, y + m_size / 2 + 4); //考虑到索引为两位数时，三角形内显示不全，适当将drawString的左起点向左多移动1个象素 -- 李伟
        g.setFont(font);
    }

    /**
     * 画带形状为倒三角的图标。
     * @param g 图形设备句柄。
     * @param x 图标绘画起始原点的x坐标。
     * @param y 图标绘画起始原点的y坐标。
     */
    private void drawDownArrow(Graphics g, int x, int y) {
        g.setColor(m_edgeColor1);
        g.drawLine(x, y, (x + m_size) - 1, y); //画倒三角的上边界
        g.drawLine(x, y + 1, (x + m_size) - 3, y + 1); //画上边界的阴影

        g.setColor(m_edgeColor2);
        g.drawLine((x + m_size) - 2, y + 1, (x + m_size) - 1, y + 1); //画上边界的阴影的残余部分，模拟出该部分给光源照到的效果

        /* x,y的位置指向上边界阴影线下左边位置 */
        int offsetX = x + 1;
        int offsetY = y + 2;
        for (int dx = m_size - 6; y + m_size > offsetY + 1; dx -= 2) {
            g.setColor(m_edgeColor1);
            g.drawLine(offsetX, offsetY, offsetX + 1, offsetY); //画倒三角形左弦阴影
            g.drawLine(offsetX, offsetY + 1, offsetX + 1, offsetY + 1); //画倒三角形左弦阴影，只是y坐标向下调整了一个象素

            if (dx > 0) {
                g.setColor(m_fillColor);
                g.drawLine(offsetX + 2, offsetY, offsetX + 1 + dx, offsetY); //使用填充色进行填充
                g.drawLine(offsetX + 2, offsetY + 1, offsetX + 1 + dx, offsetY + 1); //使用填充色进行填充
            }

            g.setColor(m_edgeColor2);
            g.drawLine(offsetX + dx + 2, offsetY, offsetX + dx + 3, offsetY); //画倒三角形右弦反光
            g.drawLine(offsetX + dx + 2, offsetY + 1, offsetX + dx + 3, offsetY + 1); //画倒三角形右弦反光，只是y坐标向下调整了一个象素
            offsetX++;
            offsetY += 2; //每次循环，y轴向下方向步进的跨幅为2象素
        }

        /* 画倒三角形下顶点 */
        g.setColor(m_edgeColor1);
        g.drawLine(x + m_size / 2, (y + m_size) - 1, x + m_size / 2, (y + m_size) - 1);

        /* 画倒三角形内的排序列序号 */
        Font font = g.getFont();
        g.setColor(Color.black);
        g.setFont(SMALL_FONT);
        //g.drawString(m_sortNumber, (x + m_size / 2) - 2, y + m_size / 2 + 2);
        g.drawString(String.valueOf(m_index), (x + m_size / 2) - 3, y + m_size / 2 + 2); //考虑到索引为两位数时，三角形内显示不全，适当将drawString的左起点向左多移动1个象素 -- 李伟
        g.setFont(font);
    }

    /**
     * 画没有箭头的图标。
     * @param g 图形设备句柄。
     * @param x 图标绘画起始原点的x坐标。
     * @param y 图标绘画起始原点的y坐标。
     */
    private void drawBlank(Graphics g, int x, int y) {
        //g.drawRect(x, y, m_size - 1, m_size - 1);
    }

}
