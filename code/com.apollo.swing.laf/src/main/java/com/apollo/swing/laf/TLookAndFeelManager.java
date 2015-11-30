/*
 * �˴��봴���� 2008-6-2 ����03:36:19��
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
 * <p>�ļ����ƣ�TLookAndFeelManager.java</p>
 * <p>�����������۸й������ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-6-3</p>
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
     * ����Alloy�۸С�
     * @param isDecoratedWindow Ϊtrue��ʾ�ù۸�װ�δ��ڣ�Ϊfalse��ʾ���ù۸�װ�δ��ڡ�
     * @return ����true��ʾ����ɹ�������false��ʾ����ʧ�ܡ�
     */
    public static boolean loadAlloy(boolean isDecoratedWindow) {
        return loadAlloy(TAlloyLookAndFeel.class, isDecoratedWindow);
    }

    /**
     * ����Glass����Alloy�۸С�
     * @param isDecoratedWindow Ϊtrue��ʾ�ù۸�װ�δ��ڣ�Ϊfalse��ʾ���ù۸�װ�δ��ڡ�
     * @return ����true��ʾ����ɹ�������false��ʾ����ʧ�ܡ�
     */
    public static boolean loadGlassThemeAlloy(boolean isDecoratedWindow) {
        return loadAlloy(TGlassThemeAlloyLookAndFeel.class, isDecoratedWindow);
    }

    /**
     * ����Acid����Alloy�۸С�
     * @param isDecoratedWindow Ϊtrue��ʾ�ù۸�װ�δ��ڣ�Ϊfalse��ʾ���ù۸�װ�δ��ڡ�
     * @return ����true��ʾ����ɹ�������false��ʾ����ʧ�ܡ�
     */
    public static boolean loadAcidThemeAlloy(boolean isDecoratedWindow) {
        return loadAlloy(TAcidThemeAlloyLookAndFeel.class, isDecoratedWindow);
    }

    /**
     * ����Bedouin����Alloy�۸С�
     * @param isDecoratedWindow Ϊtrue��ʾ�ù۸�װ�δ��ڣ�Ϊfalse��ʾ���ù۸�װ�δ��ڡ�
     * @return ����true��ʾ����ɹ�������false��ʾ����ʧ�ܡ�
     */
    public static boolean loadBedouinThemeAlloy(boolean isDecoratedWindow) {
        return loadAlloy(TBedouinThemeAlloyLookAndFeel.class, isDecoratedWindow);
    }

    /**
     * ����Metal�۸С�
     * @return ����true��ʾ����ɹ�������false��ʾ����ʧ�ܡ�
     */
    public static boolean loadMetal() {
        return load(new TMetalLookAndFeel());
    }

    /**
     * ����Motif�۸С�
     * @return ����true��ʾ����ɹ�������false��ʾ����ʧ�ܡ�
     */
    public static boolean loadMotif() {
        return load(new TMotifLookAndFeel());
    }

    /**
     * ����Windows�۸С�
     * @return ����true��ʾ����ɹ�������false��ʾ����ʧ�ܡ�
     */
    public static boolean loadWindows() {
        return load(new TWindowsLookAndFeel());
    }

    /*------------------------------------- Protected Static Method -------------------------------------*/

    /**
     * ����Alloy�۸С�
     * @param lafType �۸����͡�
     * @param isDecoratedWindow Ϊtrue��ʾ�ù۸�װ�δ��ڣ�Ϊfalse��ʾ���ù۸�װ�δ��ڡ�
     * @return ����true��ʾ����ɹ�������false��ʾ����ʧ�ܡ�
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
     * ����۸С�
     * @param laf �۸С�
     * @return ����true��ʾ����ɹ�������false��ʾ����ʧ�ܡ�
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
     * ���췽����
     */
    private TLookAndFeelManager() {
        /* ��ֹ���ⲿʵ�������� */
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
