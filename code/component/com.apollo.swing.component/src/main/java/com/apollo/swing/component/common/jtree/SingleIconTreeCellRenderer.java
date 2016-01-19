/*
 * �˴��봴���� 2007-9-20 ����04:10:17
 */
package com.apollo.swing.component.common.jtree;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * <p>�ļ����ƣ�TSingleIconTreeCellRenderer.java</p>
 * <p>�ļ���������ͼ������Ⱦ����</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004</p>
 * <p>������˾��������</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2007-9-20</p>
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

    /*------------------------------------- Public Static Field -------------------------------------*/

    /* Add Public Static Field */

    /*------------------------------------- Public Field -------------------------------------*/

    /* Add Public Field */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     * @param imageLocation ָ��ͼ���λ�ã��ȰѴ��ַ����ȵ���һ��ϵͳ��Դ����ȥ���ң��Ҳ����͵���һ���ļ���ȥ���ҡ�
     * @exception IllegalArgumentException ָ��ͼ���λ��Ϊ��ʱ�׳����쳣��
     */
    public SingleIconTreeCellRenderer(String imageLocation) {
        /* �����п� */
        if (imageLocation == null) {
            throw new IllegalArgumentException("Image location is null !");
        }

        /* ��imageLocationָ����λ�û�ȡϵͳ��Դ��URL */
        URL imageUrl = ClassLoader.getSystemResource(imageLocation);

        /* ���ݻ�ȡ��ϵͳ��Դ��URL���в�ͬ�Ĵ��� */
        if (imageUrl == null) { //��ȡ����ϵͳ��Դ
            setSingleIcon(new ImageIcon(imageLocation)); //��imageLocation����һ��filenameȥ����

        } else { //��ȡ����ϵͳ��Դ
            setSingleIcon(new ImageIcon(imageUrl));
        }
    }

    /**
     * ���췽����
     * @param imageUrl ָ��ͼ��ĵ�URL��ַ��
     */
    public SingleIconTreeCellRenderer(URL imageUrl) {
        this(new ImageIcon(imageUrl));
    }

    /**
     * ���췽����
     * @param singleIcon ָ��ͼ�ꡣ
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

    /*------------------------------------- Public Static Method -------------------------------------*/

    /* Add Public Static Method */

    /*------------------------------------- Public Method -------------------------------------*/

    /**
     * ���õ�һͼ�ꡣ
     * @param singleIcon ָ��ͼ�ꡣ
     * @exception IllegalArgumentException ָ��ͼ��Ϊ��ʱ�׳����쳣��
     */
    public void setSingleIcon(Icon singleIcon) {
        /* �����п� */
        if (singleIcon == null) {
            throw new IllegalArgumentException("Single icon is null !");
        }

        /* ��ȱʡ����Ⱦ��������ͼ������Ϊָ����ͼ�� */
        setOpenIcon(singleIcon);
        setClosedIcon(singleIcon);
        setLeafIcon(singleIcon);
    }

    /*------------------------------------- Protected Static Method -------------------------------------*/

    /* Add Protected Static Method */

    /*------------------------------------- Protected Method -------------------------------------*/

    /* Add Protected Method */

    /*------------------------------------- Friendly Static Method -------------------------------------*/

    /* Add Friendly Static Method */

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Static Method -------------------------------------*/

    /* Add Private Static Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

    /*------------------------------------- Public Static Inner Class -------------------------------------*/

    /* Add Public Static Inner Class */

    /*------------------------------------- Public Inner Class -------------------------------------*/

    /* Add Public Inner Class */

    /*------------------------------------- Protected Static Inner Class -------------------------------------*/

    /* Add Protected Static Inner Class */

    /*------------------------------------- Protected Inner Class -------------------------------------*/

    /* Add Protected Inner Class */

    /*------------------------------------- Friendly Static Inner Class -------------------------------------*/

    /* Add Friendly Static Inner Class */

    /*------------------------------------- Friendly Inner Class -------------------------------------*/

    /* Add Friendly Inner Class */

    /*------------------------------------- Private Static Inner Class -------------------------------------*/

    /* Add Private Static Inner Class */

    /*------------------------------------- Private Inner Class -------------------------------------*/

    /* Add Private Inner Class */

    /*------------------------------------- Protected Static Field -------------------------------------*/

    /* Add Protected Static Field */

    /*------------------------------------- Protected Field -------------------------------------*/

    /* Add Protected Field */

    /*------------------------------------- Friendly Static Field -------------------------------------*/

    /* Add Friendly Static Field */

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Static Field -------------------------------------*/

    /* Add Private Static Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Static Initial Block -------------------------------------*/

    /* Add Static Initial Block */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

}
