/*
 * 此代码创建于 2012-11-8 下午07:28:41。
 */
package com.apollo.swing.laf.alloy.ui;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JToolBar;
import javax.swing.plaf.ComponentUI;

import com.incors.plaf.alloy.AlloyButtonUI;

/**
 * <p>文件名称：TAlloyButtonUI.java</p>
 * <p>类型描述：TAlloyButtonUI。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2012-11-8</p>
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
public class TAlloyButtonUI extends AlloyButtonUI {

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

    private static ComponentUI sharedInstance = new TAlloyButtonUI();

    private static final AlphaComposite ICON_COMPOSITE = AlphaComposite.getInstance(3, 0.6F);

    public static ComponentUI createUI(JComponent c) {
        return sharedInstance;
    }

    public Dimension getPreferredSize(JComponent c) {
        Dimension d = super.getPreferredSize(c);
        d.height = 23;
        return d;
    }

    public Dimension getMaximumSize(JComponent c) {
        Dimension d = super.getMaximumSize(c);
        d.height = 23;
        return d;
    }

    public Dimension getMinimumSize(JComponent c) {
        Dimension d = super.getMinimumSize(c);
        d.height = 23;
        return d;
    }

    public void paint(Graphics g, JComponent c) {
        AbstractButton button = (AbstractButton) c;
        if ((button.getParent() instanceof JToolBar)) {
            paintToolbarButton((Graphics2D) g, button);
        } else
            super.paint(g, c);
    }

    private void paintToolbarButton(Graphics2D g2d, AbstractButton button) {
        ButtonModel model = button.getModel();
        int width = button.getWidth() - 2;
        int height = button.getHeight() - 2;
        if (!model.isEnabled()) {
            Icon disableIcon = button.getDisabledIcon();
            if (disableIcon != null) {
                g2d.setComposite(ICON_COMPOSITE);
                int iconWidth = disableIcon.getIconWidth();
                int iconHeight = disableIcon.getIconHeight();
                disableIcon.paintIcon(button, g2d, (width - iconWidth) / 2 + 1, (height - iconHeight) / 2 + 1);
            }
        } else if (model.isRollover()) {
            Icon icon = button.getIcon();
            if (icon != null) {
                int iconWidth = icon.getIconWidth();
                int iconHeight = icon.getIconHeight();
                if (model.isPressed()) {
                    g2d.fillRect(1, 1, button.getWidth() - 1, button.getHeight() - 1);
                    icon.paintIcon(button, g2d, (width - iconWidth) / 2 + 2, (height - iconHeight) / 2 + 2);
                } else {
                    g2d.fillRect(1, 1, button.getWidth() - 1, button.getHeight() - 1);
                    icon.paintIcon(button, g2d, (width - iconWidth) / 2 + 1, (height - iconHeight) / 2 + 1);
                }
            }
        } else {
            Icon icon = button.getIcon();
            if (icon != null) {
                int iconWidth = icon.getIconWidth();
                int iconHeight = icon.getIconHeight();
                icon.paintIcon(button, g2d, (width - iconWidth) / 2 + 1, (height - iconHeight) / 2 + 1);
            }
        }
    }

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

    /* Add Public Constructor */

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /* Add Public Method */

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
