/*
 * 此代码创建于 2012-11-9 上午10:54:58。
 */
package com.apollo.swing.laf.motif.ui;

import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ComponentUI;

import com.sun.java.swing.plaf.motif.MotifMenuItemUI;

/**
 * <p>文件名称：TMotifMenuItemUI.java</p>
 * <p>类型描述：TMotifMenuItemUI。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2012-11-9</p>
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
public class TMotifMenuItemUI extends MotifMenuItemUI {

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

    public static ComponentUI createUI(JComponent c) {
        return new TMotifMenuItemUI();
    }

    protected MouseInputListener createMouseInputListener(JComponent c) {
        return new ModifiedMouseInputHandler();
    }

    private class ModifiedMouseInputHandler extends MotifMenuItemUI.MouseInputHandler {

        private ModifiedMouseInputHandler() {
            super();
        }

        public void mouseExited(MouseEvent mouseEvent) {
            MenuSelectionManager menuSelectionManager = MenuSelectionManager.defaultManager();

            if ((mouseEvent.getModifiers() & 0x1C) != 0) {
                MenuSelectionManager.defaultManager().processMouseEvent(mouseEvent);
            } else {
                MenuElement[] path = menuSelectionManager.getSelectedPath();

                if ((path.length > 1) && (!(path[(path.length - 1)] instanceof JPopupMenu))) {
                    menuSelectionManager.setSelectedPath(removeLastPathElement(path));
                }
            }
        }

        private MenuElement[] removeLastPathElement(MenuElement[] path) {
            MenuElement[] newPath = new MenuElement[path.length - 1];

            for (int i = 0; i < path.length - 1; i++) {
                newPath[i] = path[i];
            }
            return newPath;
        }

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
