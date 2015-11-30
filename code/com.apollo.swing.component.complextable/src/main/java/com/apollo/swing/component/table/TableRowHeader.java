/*
 * �˴��봴���� 2008-4-26 ����12:30:27��
 */
package com.apollo.swing.component.table;

import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableCellRenderer;

import com.apollo.base.util.BaseUtilities;

/**
 * <p>�ļ����ƣ�TableRowHeader.java</p>
 * <p>���������������ͷ�ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-4-26</p>
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
public class TableRowHeader extends TableView implements ComponentListener {

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

    /**
     * ���塣
     */
    protected TableBody m_body;

    /**
     * �б��ƹؼ��֡�
     */
    protected String m_customKey;

    /**
     * �б��ƶԻ���
     */
    protected TableCustomDialog m_customDialog;

    /**
     * �б��ƶ�����
     */
    public Action m_custom = new AbstractAction() {

        {
            BaseUtilities.setAction(this, I18N_INFO_TABLECUSTOM, 'C', ICON_TABLECUSTOM);
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent evt) {
            if (m_customDialog == null) {
                m_customDialog = TableCustomDialog.create(m_body, TableRowHeader.this, m_body, m_customKey);
            }

            m_customDialog.show();
        }

    };

    /*------------------------------------- Friendly Field -------------------------------------*/

    /* Add Friendly Field */

    /*------------------------------------- Private Field -------------------------------------*/

    /* Add Private Field */

    /*------------------------------------- Initial Block -------------------------------------*/

    /* Add Initial Block */

    /*------------------------------------- Public Constructor -------------------------------------*/

    /**
     * ���췽����
     * @param source ��ͼԴ��
     * @param body ���塣
     * @param customKey �б��ƹؼ��֡�
     */
    public TableRowHeader(JTable source, TableBody body, String customKey) {
        this(source, body, true, customKey);
    }

    /**
     * ���췽����
     * @param source ��ͼԴ��
     * @param body ���塣
     * @param isRowIdVisible ��ʶ�Ƿ���ʾ�кš�
     * @param customKey �б��ƹؼ��֡�
     */
    public TableRowHeader(JTable source, TableBody body, boolean isRowIdVisible, String customKey) {
        this(source, body, isRowIdVisible, null, customKey);
    }

    /**
     * ���췽����
     * @param source ��ͼԴ��
     * @param body ���塣
     * @param isRowIdVisible ��ʶ�Ƿ���ʾ�кš�
     * @param rowIdRenderer �к���Ⱦ����
     * @param customKey �б��ƹؼ��֡�
     */
    public TableRowHeader(JTable source, TableBody body, boolean isRowIdVisible, TableCellRenderer rowIdRenderer, String customKey) {
        this(source, body, isRowIdVisible, rowIdRenderer, null, true, customKey);
    }

    /**
     * ���췽����
     * @param source ��ͼԴ��
     * @param body ���塣
     * @param isRowIdVisible ��ʶ�Ƿ���ʾ�кš�
     * @param rowIdRenderer �к���Ⱦ����
     * @param menuManager �˵���������
     * @param isAutoEnabledRenderer ��ʶ�Ƿ��Զ�������Ⱦ������������
     * @param customKey �б��ƹؼ��֡�
     */
    public TableRowHeader(JTable source,
                          TableBody body,
                          boolean isRowIdVisible,
                          TableCellRenderer rowIdRenderer,
                          ITableMenuManager menuManager,
                          boolean isAutoEnabledRenderer,
                          String customKey) {
        super(source, new ColumnView(source.getColumnModel(), isRowIdVisible, rowIdRenderer), menuManager, isAutoEnabledRenderer);

        m_body = body;
        m_customKey = customKey;

        //m_defaultActions.add(m_custom);
        //m_body.m_defaultActions.add(m_custom);

        columnModel.getSelectionModel().addListSelectionListener(this);
        m_body.getColumnModel().getSelectionModel().addListSelectionListener(this);

        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(false);
        updateViewportSize();
        addComponentListener(this);

        TableUtilities.disableColumnLeftMove(m_body, 0);
    }

    /*------------------------------------- Protected Constructor -------------------------------------*/

    /* Add Protected Constructor */

    /*------------------------------------- Friendly Constructor -------------------------------------*/

    /* Add Friendly Constructor */

    /*------------------------------------- Private Constructor -------------------------------------*/

    /* Add Private Constructor */

    /*------------------------------------- Public Method -------------------------------------*/

    /* Add Public Method */

    /*------------------------------------- JTable Public Method -------------------------------------*/

    /**
     * @see javax.swing.JTable#tableChanged(javax.swing.event.TableModelEvent)
     */
    public void tableChanged(TableModelEvent evt) {
        repaint();
        super.tableChanged(evt);
    }

    /**
     * @see javax.swing.JTable#valueChanged(javax.swing.event.ListSelectionEvent)
     */
    public void valueChanged(ListSelectionEvent evt) {
        Object source = evt.getSource();
        ListSelectionModel cmSm = columnModel.getSelectionModel();
        ListSelectionModel bodyCmSm = m_body.getColumnModel().getSelectionModel();

        if (source == cmSm) {
            if (!cmSm.isSelectionEmpty()) {
                bodyCmSm.clearSelection();
            }
            return;
        }

        if (source == bodyCmSm) {
            if (!bodyCmSm.isSelectionEmpty()) {
                cmSm.clearSelection();
            }
            return;
        }

        //��ǰ���ϴ��룬������Ӧ���Ǵ�������ѡ���ģʽ�����ۺϱ�Ŀǰ��֧����ѡ����ѡ��̫�鷳�ˣ�����ע�͵��������ĳɴ������ο���
        /*Object source = evt.getSource();
        boolean isCellSelectionEnabled = getCellSelectionEnabled();
        ListSelectionModel cmSm = columnModel.getSelectionModel();
        ListSelectionModel bodyCmSm = m_body.getColumnModel().getSelectionModel();

        if (source == cmSm) {
            if (isCellSelectionEnabled && !cmSm.isSelectionEmpty()) {
                if (isSelectedRowId()) {
                    cmSm.removeListSelectionListener(this);
                    bodyCmSm.removeListSelectionListener(this);

                    TTableUtilities.selectAllColumn(this);
                    TTableUtilities.selectAllColumn(m_body);

                    cmSm.addListSelectionListener(this);
                    bodyCmSm.addListSelectionListener(this);

                } else {
                    bodyCmSm.clearSelection();
                }
            }

            return;
        }

        if (source == bodyCmSm) {
            if (isCellSelectionEnabled && !bodyCmSm.isSelectionEmpty()) {
                cmSm.clearSelection();
            }

            return;
        }*/

        super.valueChanged(evt);
    }

    /*------------------------------------- ComponentListener Public Method -------------------------------------*/

    /**
     * @see java.awt.event.ComponentListener#componentMoved(java.awt.event.ComponentEvent)
     */
    public void componentMoved(ComponentEvent evt) {
        Container parent = m_body.getParent();

        if (parent instanceof JViewport) { //Ŀ����Ƿ��ڹ���������
            JViewport viewport = (JViewport) parent;

            int offsetY = getVisibleRect().y;
            Point viewPosition = viewport.getViewPosition();

            if (viewPosition.y != offsetY) { //Ŀ���λ�û�δ�ƶ���
                viewPosition.y = offsetY;
                viewport.setViewPosition(viewPosition);
            }
        }
    }

    /**
     * @see java.awt.event.ComponentListener#componentResized(java.awt.event.ComponentEvent)
     */
    public void componentResized(ComponentEvent evt) {
        /* �ݲ���ʵ�� */
    }

    /**
     * @see java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent)
     */
    public void componentShown(ComponentEvent evt) {
        /* �ݲ���ʵ�� */
    }

    /**
     * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.ComponentEvent)
     */
    public void componentHidden(ComponentEvent evt) {
        /* �ݲ���ʵ�� */
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * �ж��к����Ƿ�ѡ���ˡ�
     * @return Ϊtrue��ʾ�к�����ѡ�еģ�Ϊfalse��ʾ�к��в���ѡ�еġ�
     */
    protected boolean isSelectedRowId() {
        ColumnView cv = getColumnView();

        if (!cv.m_isRowIdVisible) {
            return false;
        }

        int[] columns = cv.getSelectedColumns();

        for (int i = 0; i < columns.length; i++) {
            if (cv.isRowIdColumn(columns[i])) {
                return true;
            }
        }

        return false;
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
