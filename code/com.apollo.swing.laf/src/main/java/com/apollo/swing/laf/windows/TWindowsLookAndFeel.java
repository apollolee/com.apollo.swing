/*
 * 此代码创建于 2012-11-9 上午11:18:06。
 */
package com.apollo.swing.laf.windows;

import java.awt.Font;
import java.util.Locale;

import javax.swing.UIDefaults;

import com.apollo.swing.laf.windows.ui.TWindowsButtonUI;
import com.apollo.swing.laf.windows.ui.TWindowsComboBoxUI;
import com.apollo.swing.laf.windows.ui.TWindowsMenuItemUI;
import com.apollo.swing.laf.windows.ui.TWindowsTextFieldUI;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

/**
 * <p>文件名称：TWindowsLookAndFeel.java</p>
 * <p>类型描述：Windows观感类。</p>
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
@SuppressWarnings("all")
public class TWindowsLookAndFeel extends WindowsLookAndFeel {

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

    /* Add Public Constructor */

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /* Add Public Method */

    /*------------------------------------- BasicLookAndFeel Public Method -------------------------------------*/

    /**
     * @see javax.swing.plaf.basic.BasicLookAndFeel#getDefaults()
     */
    @Override
    public UIDefaults getDefaults() {
        UIDefaults defaults = super.getDefaults();

        if (Locale.getDefault().getLanguage().equals("zh")) {
            defaults.put("FileChooser.cancelButtonText", "\u53D6\u6D88");
            defaults.put("FileChooser.saveInLabelText", "\u4FDD\u5B58\u4F4D\u7F6E:");
            defaults.put("FileChooser.lookInLabelText", "\u67E5\u770B\uFF1A");
            defaults.put("FileChooser.fileNameLabelText", "\u6587\u4EF6\u540D:");
            defaults.put("FileChooser.filesOfTypeLabelText", "\u6587\u4EF6\u7C7B\u578B:");
            defaults.put("FileChooser.upFolderToolTipText", "\u5411\u4E0A\u4E00\u5C42");
            defaults.put("FileChooser.upFolderAccessibleName", "\u5411\u4E0A");
            defaults.put("FileChooser.homeFolderToolTipText", "\u8D77\u59CB\u76EE\u5F55");
            defaults.put("FileChooser.homeFolderAccessibleName", "\u8D77\u59CB\u76EE\u5F55");
            defaults.put("FileChooser.newFolderToolTipText", "\u521B\u5EFA\u65B0\u7684\u6587\u4EF6\u5939");
            defaults.put("FileChooser.newFolderAccessibleName", "\u65B0\u6587\u4EF6\u5939");
            defaults.put("FileChooser.listViewButtonToolTipText", "\u5217\u8868\u663E\u793A");
            defaults.put("FileChooser.listViewButtonAccessibleName", "\u5217\u8868\u663E\u793A");
            defaults.put("FileChooser.detailsViewButtonToolTipText", "\u663E\u793A\u8BE6\u7EC6\u4FE1\u606F");
            defaults.put("FileChooser.detailsViewButtonAccessibleName", "\u663E\u793A\u8BE6\u7EC6\u4FE1\u606F");
            defaults.put("FileChooser.fileNameHeaderText", "\u540D\u79F0");
            defaults.put("FileChooser.fileSizeHeaderText", "\u5927\u5C0F");
            defaults.put("FileChooser.fileTypeHeaderText", "\u7C7B\u578B");
            defaults.put("FileChooser.fileDateHeaderText", "\u4FEE\u6539\u65F6\u95F4");
            defaults.put("FileChooser.fileAttrHeaderText", "\u5C5E\u6027");
            defaults.put("OptionPane.cancelButtonText", "\u53D6\u6D88");
        }

        Font defaultFont = new Font("Dialog", 0, 12);
        Object[] keys = defaults.keySet().toArray();
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] instanceof String) {
                String key = (String) keys[i];

                if (key.endsWith(".font") || key.endsWith("Font")) {
                    defaults.put(key, defaultFont);
                }
            }
        }

        defaults.put("MenuItemUI", TWindowsMenuItemUI.class.getName());
        defaults.put("ButtonUI", TWindowsButtonUI.class.getName());
        defaults.put("TextFieldUI", TWindowsTextFieldUI.class.getName());
        defaults.put("ComboBoxUI", TWindowsComboBoxUI.class.getName());

        return defaults;
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
