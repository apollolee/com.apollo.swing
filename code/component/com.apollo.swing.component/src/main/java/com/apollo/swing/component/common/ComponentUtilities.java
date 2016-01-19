/*
 * �˴��봴���� 2007-2-26 ����03:31:54
 */
package com.apollo.swing.component.common;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.KeyboardFocusManager;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * <p>�ļ����ƣ�TComponentUtilities.java</p>
 * <p>�ļ���������������ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004</p>
 * <p>������˾��������</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2007-6-15</p>
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
public final class ComponentUtilities {

    /*------------------------------------- Public Static Field -------------------------------------*/

    /**
     * ���������------���Ƴߴ硣
     */
    public static final ComponentListener CPL_LIMITSIZE = new ComponentAdapter() {

        /**
         * @see java.awt.event.ComponentAdapter#componentResized(java.awt.event.ComponentEvent)
         */
        public void componentResized(ComponentEvent evt) {
            /* ��ȡĿ����� */
            Component component = evt.getComponent();

            /* ��ȡĿ��������õĸ��ֳߴ� */
            Dimension newSize = component.getSize();
            Dimension minSize = component.getMinimumSize();
            Dimension maxSize = component.getMaximumSize();

            /* ����Ŀ��������õ����ճߴ� */
            Dimension finalSize = new Dimension();

            /* ȷ��Ŀ����������տ�� */
            if (newSize.width < minSize.width) {
                finalSize.width = minSize.width;

            } else if (newSize.width > maxSize.width) {
                finalSize.width = maxSize.width;

            } else {
                finalSize.width = newSize.width;
            }

            /* ȷ��Ŀ����������ո߶� */
            if (newSize.height < minSize.height) {
                finalSize.height = minSize.height;

            } else if (newSize.height > maxSize.height) {
                finalSize.height = maxSize.height;

            } else {
                finalSize.height = newSize.height;
            }

            /* ����Ŀ��������õ����ճߴ� */
            component.setSize(finalSize);
        }

    };

    /*------------------------------------- Public Field -------------------------------------*/

    /* Add Public Field */

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
    private ComponentUtilities() {
        /* ��ֹ���ⲿʵ�������� */
    }

    /*------------------------------------- Public Static Method -------------------------------------*/

    /**
     * �����ǰ�̱߳�������awt�¼��߳̾�ֱ��ִ��code.run()����������SwingUtilities.invokeLater()��code�����¼�����ִ�С�
     * @param code ��Ҫ���еĴ��롣
     */
    public static final void safelyInvokeLater(Runnable code) {
        if (SwingUtilities.isEventDispatchThread()) {
            code.run();

        } else {
            SwingUtilities.invokeLater(code);
        }
    }

    /**
     * �ж�ָ�������Ƿ�������䴹ֱ������������������ʾ����
     * @param container ָ����������
     * @return Ϊtrue��ʾ������䴹ֱ����Ϊfalse��ʾ��������䴹ֱ����
     */
    public static final boolean isFillVertical(Container container) {
        /* ���˲���Ϊnull������ */
        if (container == null) {
            return false;
        }

        /* ��ȡ�����Ĳ��ֹ����� */
        LayoutManager layout = container.getLayout();

        /* ���������Ĳ��ֹ���������GridBagLayout������ */
        if (!(layout instanceof GridBagLayout)) {
            return true;
        }

        /* ��ȡGridBagLayout��������ȫ����� */
        GridBagLayout gridBagLayout = (GridBagLayout) layout;
        Component[] components = container.getComponents();

        /* �ж�ָ�������Ƿ�������䴹ֱ������������������ʾ���� */
        for (int i = 0; i < components.length; i++) {
            /* ��ȡGridBagConstraints */
            GridBagConstraints gbc = gridBagLayout.getConstraints(components[i]);

            /* �ж�����Ƿ�������䴹ֱ������������������ʾ���� */
            if (gbc.fill == GridBagConstraints.VERTICAL || gbc.fill == GridBagConstraints.BOTH) {
                return true;
            }
        }

        /* ��������䴹ֱ���� */
        return false;
    }

    /**
     * ����JTable�������ѡ��ȣ��������еĸ��У��Լ����������е���ѡ��ȣ����̰߳�ȫ����
     * ע�⣺1.������targetTable����ģ�ͣ�������ģ�ͣ�������Ⱦ����������ͷ��Ⱦ����û��׼���ã��˷����п����׳������������쳣��
     * ������2.������targetTableѡ���AutoResizeMode�Ƿ�AUTO_RESIZE_OFF����ô�����ò����ڽ����в�������Ҫ��Ч����
     * @param targetTable Ŀ����
     */
    public static final void adjustJTablePreferredWidths(JTable targetTable) {
        if (targetTable == null) {
            return; //Ŀ����Ϊ�ղ������´���
        }

        TableColumnModel cm = targetTable.getColumnModel(); //��ȡ��ģ��
        JTableHeader th = targetTable.getTableHeader(); //��ȡ��ͷ

        final int columnMargin = cm.getColumnMargin(); //�����м��
        int targetTablePreferredWidths = 0; //Ŀ������ѡ����ռ�����

        for (int i = 0, columnCount = cm.getColumnCount(); i < columnCount; i++) { //������
            TableColumn column = cm.getColumn(i);

            TableCellRenderer headerRenderer = column.getHeaderRenderer();
            if (headerRenderer == null) { //�û�û���ñ�ͷ��Ⱦ����ʹ��Ĭ�ϵ�
                headerRenderer = th.getDefaultRenderer();
            }

            Object headerValue = column.getHeaderValue();

            /* ��ȡ��ͷ����ѡ��� */
            Component headerComponent = headerRenderer.getTableCellRendererComponent(targetTable, headerValue, false, false, 0, i);
            int columnMaxWidth = headerComponent.getPreferredSize().width; //��ǰ�е�����ȣ��������µ��滻�Ƚ�

            for (int j = 0, rowCount = targetTable.getRowCount(); j < rowCount; j++) { //������
                TableCellRenderer cellRenderer = targetTable.getCellRenderer(j, i);
                Object cellValue = targetTable.getValueAt(j, i);

                /* ��ȡ���еĵ�ǰ�е���ѡ��� */
                Component cellComponent = cellRenderer.getTableCellRendererComponent(targetTable, cellValue, false, false, j, i);
                columnMaxWidth = Math.max(columnMaxWidth, cellComponent.getPreferredSize().width); //�Ƚ��滻
            }

            columnMaxWidth += columnMargin; //����п���Ҫ���ǵ��м��
            column.setPreferredWidth(columnMaxWidth); //�����е���ѡ���
            targetTablePreferredWidths += column.getPreferredWidth(); //�ռ������е���ѡ���
        }

        /* ���ù��������ӿڵ����ߴ磨JTable�������ѡ��Ȼ���ݸ����м�����������Բ���Ҫ�ر��趨�� */
        Dimension psvs = targetTable.getPreferredScrollableViewportSize();
        psvs.width = targetTablePreferredWidths;
        /* ע�⣺���ﲻ��ֱ������ΪJTable����ѡ��С���������ƻ��û����趨�Ĺ����������ѡ"�߶�"�� */
        targetTable.setPreferredScrollableViewportSize(psvs);
    }

    /**
     * ָֹͣ�����ĵ�Ԫ��༭����������б�������ڱ༭�ĵĵ�Ԫ��Ļ�����
     * @param targetTable Ŀ����
     */
    public static final void stopJTableCellEditing(JTable targetTable) {
        if (targetTable == null) {
            return; //Ŀ����Ϊ�ղ������´���
        }

        if (targetTable.isEditing()) {
            int row = targetTable.getEditingRow();
            int column = targetTable.getEditingColumn();
            TableCellEditor editor = targetTable.getCellEditor(row, column);
            editor.stopCellEditing();
        }
    }

    /**
     * ��ȡָ������ĵĶ����ܡ�
     * @param component ָ���������
     * @return ָ������ĵĶ����ܡ�
     */
    public static final JFrame getFrame(Component component) {
        /* ��ȡָ������ĵĶ��㴰�� */
        Window window = getWindow(component);

        /* ����ָ������ĵĶ����� */
        if (window instanceof JFrame) { //ָ������ĵĶ��㴰���ǿ��
            return (JFrame) window;

        } else { //ָ������ĵĶ��㴰�ڲ��ǿ��
            return null;
        }
    }

    /**
     * ��ȡָ������ĵĶ���Ի���
     * @param component ָ���������
     * @return ָ������ĵĶ���Ի���
     */
    public static final JDialog getDialog(Component component) {
        /* ��ȡָ������ĵĶ��㴰�� */
        Window window = getWindow(component);

        /* ����ָ������ĵĶ���Ի��� */
        if (window instanceof JDialog) { //ָ������ĵĶ��㴰���ǶԻ���
            return (JDialog) window;

        } else { //ָ������ĵĶ��㴰�ڲ��ǶԻ���
            return null;
        }
    }

    /**
     * ��ȡָ������ĵĶ��㴰�ڡ�
     * @param component ָ���������
     * @return ָ������ĵĶ��㴰�ڡ�
     */
    public static final Window getWindow(Component component) {
        /* ����ָ������ĵĶ��㴰�� */
        if (component != null) { //ָ���������Ϊ��
            if (component instanceof JDialog || component instanceof JFrame) { //ָ��������ǿ�ܻ�Ի���
                return (Window) component;

            } else { //ָ��������ǲ��ǿ�ܺͶԻ���
                /* ��ȡָ������ĵĶ��㴰�� */
                Window window = SwingUtilities.windowForComponent(component);

                /* ��ȡ��ָ������ĵĶ��㴰��Ϊnull���� */
                if (window == null) {
                    window = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
                }

                /* ����ָ������ĵĶ��㴰�� */
                return window;
            }

        } else { //ָ�������Ϊ��
            return null;
        }
    }

    /**
     * ��ȡָ�����ڷ�����Ļ����ʱ������Ͻ�����㡣
     * @param window ָ���Ĵ��ڡ�
     * @return ָ�����ڷ�����Ļ����ʱ������Ͻ�����㡣
     */
    public static final Point getLocationOnCenterInScreen(Window window) {
        /* ����ָ�����ڷ�����Ļ����ʱ������Ͻ������ */
        Point location = new Point(0, 0);

        /* ��ȡ��Ļ�ĳߴ��ָ�����ڵĳߴ� */
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = window.getSize();

        /* ����ָ�����ڷ�����Ļ����ʱ������Ͻ������ */
        location.x = screenSize.width / 2 - windowSize.width / 2;
        location.y = screenSize.height / 2 - windowSize.height / 2;

        /* ����ָ�����ڷ�����Ļ����ʱ������Ͻ������ */
        return location;
    }

    /**
     * ��ȡһ���������Ļ�ڵ���ѡ��ʾλ�á�
     * @param targetComponent Ŀ�������
     * @param expectantLocation Ŀ������ڴ����ֵ�λ�ã�������Ͻǵ�����㣩��
     * @return ���Ŀ�������Ŀ������ڴ����ֵ�λ��Ϊ���򷵻�null��
     */
    public static final Point getPreferredLocationInScreen(JComponent targetComponent, Point expectantLocation) {
        if (targetComponent == null || expectantLocation == null) {
            return null;
        }

        Point preferredLocation = new Point(); //��ѡ��ʾλ��

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension targetComponentPreferredSize = targetComponent.getPreferredSize();

        /* ����x�������ֵ */
        if (expectantLocation.x < 0) { //�ڴ���x��������Ļ���棨��ࣩ
            preferredLocation.x = expectantLocation.x + targetComponentPreferredSize.width;

        } else if (expectantLocation.x + targetComponentPreferredSize.width > screenSize.width) { //�ڴ���x���꿿�Ҳ�����ʾ��������������а������ڴ���x��������Ļ���棨�Ҳࣩ�����
            preferredLocation.x = expectantLocation.x - targetComponentPreferredSize.width;

        } else { //�������
            preferredLocation.x = expectantLocation.x;
        }

        /* ����y�������ֵ */
        if (expectantLocation.y < 0) { //�ڴ���y��������Ļ���棨�ϲࣩ
            preferredLocation.y = expectantLocation.y + targetComponentPreferredSize.height;

        } else if (expectantLocation.y + targetComponentPreferredSize.height > screenSize.height) { //�ڴ���y���꿿�²�����ʾ��������������а������ڴ���y��������Ļ���棨�²ࣩ�����
            preferredLocation.y = expectantLocation.y - targetComponentPreferredSize.height;

        } else { //�������
            preferredLocation.y = expectantLocation.y;
        }

        /* �����ڴ�������С����Ļ�ܶ�ʱ������ */
        if (preferredLocation.x < 0) { //�ڴ���x�����Ǹ��������ҹ���С�����ڼ��������Ⱥ���Ȼ�Ǹ���������Ļ����
            preferredLocation.x = 0;
        }

        if (preferredLocation.y < 0) { //�ڴ���y�����Ǹ��������ҹ���С�����ڼ�������߶Ⱥ���Ȼ�Ǹ���������Ļ����
            preferredLocation.y = 0;
        }

        return preferredLocation;
    }

    /**
     * ������¼����������ĵ�ת��Ϊ��Ļ�ϵ�����㡣
     * @param evt ����¼���
     * @return �������¼�Ϊ���򷵻�Ϊnull��
     */
    public static final Point convertLocationToScreen(MouseEvent evt) {
        if (evt == null) {
            return null;
        }

        return convertLocationToScreen(evt.getComponent(), evt.getPoint());
    }

    /**
     * ��һ���������������ת��Ϊ��Ļ�ϵľ�������㡣
     * @param source Դ�����
     * @param relativeLocation
     * @return ������ԴΪ���򷵻�null��
     */
    public static final Point convertLocationToScreen(Component source, Point relativeLocation) {
        if (source == null) {
            return null;
        }

        Point sourceLocation = source.getLocationOnScreen();

        Point absoluteLocation = new Point();
        absoluteLocation.x = sourceLocation.x + relativeLocation.x;
        absoluteLocation.y = sourceLocation.y + relativeLocation.y;

        return absoluteLocation;
    }

    /**
     * ����ResourceBundle���ƻ�ȡi18n��Ϣ�����ָ������Դ�Ҳ�����ֱ�ӷ���i18nKey��
     * @param i18nBaseName ��Դ���Ļ������ƣ���һ����ȫ�޶�������
     * @param i18nKey ���ʻ���Դ�Ĺ��ʻ��ؼ��֡�
     * @return ���ʻ���Ϣ��
     */
    public static final String getI18nMessage(String i18nBaseName, String i18nKey) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle(i18nBaseName);
            return rb.getString(i18nKey);

        } catch (MissingResourceException ex) { //�Ҳ���ָ������Դ
            return i18nKey;
        }
    }

    /**
     * ��ȡָ�����ȵĸ��������ַ���������ʽ��
     * @param floatValue ����ֵ��
     * @param precision ���ȡ�
     * @return �ַ���������
     */
    public static final String getFloatDescriptionByPrecision(double floatValue, int precision) {
        BigDecimal floatValueBd = new BigDecimal(floatValue);

        BigDecimal result = floatValueBd.setScale(precision, BigDecimal.ROUND_HALF_UP);

        return result.toString();
    }

    /**
     * ��ȡָ�����class�ļ����ڵ�λ�á�
     * @param targetClass Ŀ���ࡣ
     * @param isClasspathParent Ϊtrue���ص���class�ļ�������·���ĸ�Ŀ¼��Ϊfalse��ֱ�ӷ�����·����ע�⣺���class�ļ���jar��zip�н����ص���jar�ļ���zip�ļ���·������
     * @param isRealSpace Ϊtrue��ʹ����ʵ�Ŀո����滻URL�����ת��ո�Ϊfalse��ֱ��ʹ��URL����涨��ת��ո�ע�⣺���URL���ڹ���File���˲�����Ҫ����Ϊtrue����
     * @return ��ʶλ�ã�Ϊnull��ʾtargetClassΪnull����URLʧ���ˡ�
     */
    public static final URL getClassCodeLocation(Class targetClass, boolean isClasspathParent, boolean isRealSpace) {
        /* Ŀ�����п� */
        if (targetClass == null) {
            return null;
        }

        /*
         * ע�⣺�������ֱ�ӵ���Class.getProtectionDomain().getCodeSource().getLocation()�������õ�������Ҫ��Location����
         * �����������������2�����Ʋ�̫���㣺
         * ������1.�˷�����Ҫ��Ӧ�İ�ȫȨ�ޣ����û�У�����Java��ȫ�쳣�ġ�
         * ������2.�˷�����getCodeSource()�ڵ�ClassΪ���������������ʱ�᷵��null����Ȼ���ٻ��õ������⻹�����ǲ�ϣ���ġ�
         * ������������������ȱ�㣬���ǲ�ʹ�ô˷�������ȡLocation��
         */

        /* ��ȡĿ����class�ļ���URL�����õ������Ϣ */
        String clsName = targetClass.getName();
        String clsRes = "/" + clsName.replace('.', '/') + ".class";
        URL url = targetClass.getResource(clsRes);
        String protocol = url.getProtocol();
        String spec = url.toString();

        /* TODO �滻�ո�URL�������ո���"%20"����������URL������File�ཫ�޷��ҵ��ļ��������滻���������ܻ����ƽ̨�����ԡ� */
        if (isRealSpace) {
            spec = spec.replaceAll("%20", " ");
        }

        /* ��ȡ��·�� */
        if (protocol.equals("jar") || protocol.equals("zip")) { //class�ļ��Ƿ���jar��zip�е�
            spec = spec.substring(spec.indexOf(":") + 1, spec.lastIndexOf("!"));

        } else { //����ͨ��Ŀ¼
            spec = spec.substring(0, spec.lastIndexOf(clsRes));
        }

        /* ��ȡ��·���ĸ�Ŀ¼ */
        if (isClasspathParent) {
            spec = spec.substring(0, spec.lastIndexOf("/"));
        }

        /* ����URL������ */
        try {
            return new URL(spec);

        } catch (MalformedURLException ex) {
            return null;
        }
    }

    /**
     * ����ָ�����ڵ��ȡ����·����Ϣ��
     * @param node Ŀ��ڵ㡣
     * @return ·����Ϣ�����Ŀ��ڵ�Ϊ�ս�����null��
     */
    public static final TreePath getTreePath(TreeNode node) {
        /* �����п� */
        if (node == null) {
            return null;
        }

        /* ��ȡĿ��ڵ㵽���ڵ���б� */
        List nodeList = new ArrayList();
        nodeList.add(node);
        TreeNode parent = node.getParent();
        while (parent != null) {
            nodeList.add(parent);
            parent = parent.getParent();
        }

        /* ��ȡ���ڵ㵽Ŀ��ڵ������ */
        Object[] path = new Object[nodeList.size()];
        for (int i = 0; i < path.length; i++) {
            path[i] = nodeList.get(path.length - 1 - i);
        }

        /* ���첢����·����Ϣ */
        return new TreePath(path);
    }

    /**
     * �ж����ϵ�һ���ڵ��Ƿ������Ŀ��������ϡ�
     * @param tree Ŀ������
     * @param node Ŀ��ڵ㡣
     * @param isContainFull Ϊtrue��ʾҪ����Ŀ��ڵ��ȫ�����ز��ж�Ϊ�ڿ��������ڣ�Ϊfalse��ʾֻҪ����Ŀ��ڵ��һ�����ؾ��ж�Ϊ�ڿ��������С�
     * @return �ڿ��������ﷵ��true��������ʾ���������������򷵻�false��
     */
    public static final boolean isInTreeVisibleRect(JTree tree, TreeNode node, boolean isContainFull) {
        /* �����п� */
        if (tree == null || node == null) {
            return false;
        }

        /* �������Ŀ�������ͽڵ����ʾ���� */
        Rectangle vr = tree.getVisibleRect();
        Rectangle pb = tree.getPathBounds(getTreePath(node));

        /* �����Ŀ�����������1�������Դ˰ѽڵ�ı߿�������� */
        vr.x--;
        vr.y--;
        vr.width += 2;
        vr.height += 2;

        /* �������п� */
        if (vr == null || pb == null) {
            return false;
        }

        /* ����������� */
        if (isContainFull) { //��������
            return vr.contains(pb);

        } else { //���ְ���
            if (vr.contains(pb.x, pb.y)) { //���Ͻ�
                return true;

            } else if (vr.contains(pb.x, pb.y + pb.height)) { //���½�
                return true;

            } else if (vr.contains(pb.x + pb.width, pb.y)) { //���Ͻ�
                return true;

            } else if (vr.contains(pb.x + pb.width, pb.y + pb.height)) { //���½�
                return true;

            } else { //�ڵ�һ�����ض��������Ŀ���������
                return false;
            }
        }
    }

    /**
     * �ж�ָ���Ķ��������Ƿ������ָ�������жϱ�׼�Ƕ�����ͬ��������ȣ���
     * @param objectArray Ŀ��������顣
     * @param object Ŀ�����
     * @return ��������򷵻�true�������������Ŀ���������Ϊ�ջ�Ŀ�����Ϊ���򷵻�false��
     */
    public static final boolean containsObject(Object[] objectArray, Object object) {
        /* �����п� */
        if (objectArray == null || objectArray.length == 0 || object == null) {
            return false;
        }

        /* �����Ƚ� */
        for (int i = 0; i < objectArray.length; i++) {
            if (objectArray[i] == object) { //��ǰ�����Ŀ�������ͬ
                return true;
            }
        }

        /* û�ҵ���ͬ�Ķ��󣬷���false */
        return false;
    }

    /*------------------------------------- Public Method -------------------------------------*/

    /* Add Public Method */

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
