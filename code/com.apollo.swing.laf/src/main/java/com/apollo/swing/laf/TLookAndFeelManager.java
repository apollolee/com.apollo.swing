/*
 * 此代码创建于 2008-6-2 下午03:36:19。
 */
package com.apollo.swing.laf;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.apollo.swing.laf.alloy.TAcidThemeAlloyLookAndFeel;
import com.apollo.swing.laf.alloy.TAlloyLookAndFeel;
import com.apollo.swing.laf.alloy.TBedouinThemeAlloyLookAndFeel;
import com.apollo.swing.laf.alloy.TGlassThemeAlloyLookAndFeel;
import com.apollo.swing.laf.metal.TMetalLookAndFeel;
import com.apollo.swing.laf.motif.TMotifLookAndFeel;
import com.apollo.swing.laf.windows.TWindowsLookAndFeel;
import com.incors.plaf.alloy.AlloyLookAndFeel;

/**
 * <p>文件名称：TLookAndFeelManager.java</p>
 * <p>类型描述：观感管理器类。</p>
 * <p>版权所有：版权所有(C)2001-2004。</p>
 * <p>公　　司：菠萝梨。</p>
 * <p>内容摘要：</p>
 * <p>其他说明：</p>
 * <p>完成日期：2008-6-3</p>
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
public class TLookAndFeelManager {

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

    static {
        loadAlloy(false);
    }

    /*------------------------------------- Public Static Method -------------------------------------*/

    /**
     * 载入Alloy观感。
     * @param isDecoratedWindow 为true表示用观感装饰窗口，为false表示不用观感装饰窗口。
     * @return 返回true表示载入成功，返回false表示载入失败。
     */
    public static boolean loadAlloy(boolean isDecoratedWindow) {
        return loadAlloy(TAlloyLookAndFeel.class, isDecoratedWindow);
    }

    /**
     * 载入Glass主题Alloy观感。
     * @param isDecoratedWindow 为true表示用观感装饰窗口，为false表示不用观感装饰窗口。
     * @return 返回true表示载入成功，返回false表示载入失败。
     */
    public static boolean loadGlassThemeAlloy(boolean isDecoratedWindow) {
        return loadAlloy(TGlassThemeAlloyLookAndFeel.class, isDecoratedWindow);
    }

    /**
     * 载入Acid主题Alloy观感。
     * @param isDecoratedWindow 为true表示用观感装饰窗口，为false表示不用观感装饰窗口。
     * @return 返回true表示载入成功，返回false表示载入失败。
     */
    public static boolean loadAcidThemeAlloy(boolean isDecoratedWindow) {
        return loadAlloy(TAcidThemeAlloyLookAndFeel.class, isDecoratedWindow);
    }

    /**
     * 载入Bedouin主题Alloy观感。
     * @param isDecoratedWindow 为true表示用观感装饰窗口，为false表示不用观感装饰窗口。
     * @return 返回true表示载入成功，返回false表示载入失败。
     */
    public static boolean loadBedouinThemeAlloy(boolean isDecoratedWindow) {
        return loadAlloy(TBedouinThemeAlloyLookAndFeel.class, isDecoratedWindow);
    }

    /**
     * 载入Metal观感。
     * @return 返回true表示载入成功，返回false表示载入失败。
     */
    public static boolean loadMetal() {
        return load(new TMetalLookAndFeel());
    }

    /**
     * 载入Motif观感。
     * @return 返回true表示载入成功，返回false表示载入失败。
     */
    public static boolean loadMotif() {
        return load(new TMotifLookAndFeel());
    }

    /**
     * 载入Windows观感。
     * @return 返回true表示载入成功，返回false表示载入失败。
     */
    public static boolean loadWindows() {
        return load(new TWindowsLookAndFeel());
    }

    /*------------------------------------- Protected Static Method -------------------------------------*/

    /**
     * 载入Alloy观感。
     * @param lafType 观感类型。
     * @param isDecoratedWindow 为true表示用观感装饰窗口，为false表示不用观感装饰窗口。
     * @return 返回true表示载入成功，返回false表示载入失败。
     */
    protected static boolean loadAlloy(Class lafType, boolean isDecoratedWindow) {
        AlloyLookAndFeel.setProperty("alloy.isLookAndFeelFrameDecoration", String.valueOf(isDecoratedWindow));

        try {
            return load((LookAndFeel) lafType.newInstance());

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 载入观感。
     * @param laf 观感。
     * @return 返回true表示载入成功，返回false表示载入失败。
     */
    protected static boolean load(LookAndFeel laf) {
        try {
            UIManager.setLookAndFeel(laf);
            return true;

        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
            return false;
        }
    }

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

    /**
     * 构造方法。
     */
    private TLookAndFeelManager() {
        /* 禁止从外部实例化此类 */
    }

    /*------------------------------------- Public Method -------------------------------------*/

    /* Add Public Method */

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
