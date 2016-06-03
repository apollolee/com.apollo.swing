/*
 * 此代码创建于 2008-9-19 上午09:41:38。
 */
package com.apollo.swing.component.icon;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;
import javax.swing.UIManager;

/**
 * <p>文件名称：TIconWrapper.java</p>
 * <p>类型描述：图标包装器类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-9-19</p>
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
     * 创建选择框图标包装器。
     * @return 选择框图标包装器。
     */
    public static IconWrapper createCheckBoxIconWrapper() {
        return new IconWrapper(UIManager.getIcon("CheckBox.icon"));
    }

    /**
     * 创建单选按钮图标包装器。
     * @return 单选按钮图标包装器。
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
     * 被包装的图标。
     */
    protected Icon m_icon;

    /**
     * 标识是否处于不确定状态。
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
     * 构造方法。
     * @param icon 被包装的图标。
     */
    public IconWrapper(Icon icon) {
        this(icon, false);
    }

    /**
     * 构造方法。
     * @param icon 被包装的图标。
     * @param isIndetermined 标识是否处于不确定状态。
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
     * 获取字段 icon 的值。
     * @return 字段 icon 的值。
     */
    public Icon getIcon() {
        return m_icon;
    }

    /**
     * 设置字段 icon 的值。
     * @param icon 字段 icon 的值。
     */
    public void setIcon(Icon icon) {
        m_icon = icon;
    }

    /**
     * 获取字段 isIndetermined 的值。
     * @return 字段 isIndetermined 的值。
     */
    public boolean isIndetermined() {
        return m_isIndetermined;
    }

    /**
     * 设置字段 isIndetermined 的值。
     * @param isIndetermined 字段 isIndetermined 的值。
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
        /* 获取2D绘图句柄 */
        Graphics2D g2d = (Graphics2D) g.create();

        /* 设置绘图句柄为半透明状态 */
        if (m_isIndetermined) { // 当前选择状态处于不确定状态
            /* TODO 注意：由于没有找到完美渲染不确定状态选择框的方法，所以提供以下两种方法给代码维护者选择和参考（二选一） */

            /* 设置背景色来渲染不确定状态选择框，注意：TODO 此设置和观感相关，不同观感下需要调整参数值，暂时没找到更好的解决方法 */
            g2d.setBackground(Color.GRAY);
            g2d.clearRect(6, (c.getHeight() - 10) / 2, 10, 10);
            /* 设置透明度来渲染不确定状态选择框 */
            //g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)); // 渲染为半透明效果
        }

        /* 绘制图标 */
        m_icon.paintIcon(c, g2d, x, y);
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
