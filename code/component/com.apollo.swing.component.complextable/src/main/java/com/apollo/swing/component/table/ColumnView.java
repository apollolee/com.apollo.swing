/*
 * �˴��봴���� 2008-5-4 ����01:59:15��
 */
package com.apollo.swing.component.table;

import java.awt.Component;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * <p>�ļ����ƣ�ColumnView.java</p>
 * <p>��������������ͼ�ࡣ</p>
 * <p>��Ȩ���У���Ȩ����(C)2001-2004��</p>
 * <p>������˾�������档</p>
 * <p>����ժҪ��</p>
 * <p>����˵����</p>
 * <p>������ڣ�2008-5-4</p>
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
public class ColumnView extends DefaultTableColumnModel implements SwingConstants, ITableConstants, TableColumnModelListener {

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
     * �ж�ָ�����Ƿ�Ϊ�к��С�
     * @param column ָ���С�
     * @return Ϊtrue��ʾָ�������к��У�Ϊfalse��ʾָ���в����к��С�
     */
    public static boolean isRowIdColumn(TableColumn column) {
        return I18N_INFO_ROWID.equals(column.getIdentifier());
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
     * ���
     */
    protected JTable m_table;

    /**
     * ��ͼԴ��
     */
    protected TableColumnModel m_source;

    /**
     * ��ʶ�Ƿ�Ϊ��ͷ�С�
     */
    protected volatile boolean m_isRowHeader;

    /**
     * ��ʶ�Ƿ���ʾ�к��С�
     */
    protected volatile boolean m_isRowIdVisible;

    /**
     * �к�����Ⱦ����
     */
    protected TableCellRenderer m_rowIdRenderer;

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
     */
    public ColumnView(TableColumnModel source) {
        this(source, false, false, null);
    }

    /**
     * ���췽����
     * @param source ��ͼԴ��
     * @param isRowIdVisible ��ʶ�Ƿ���ʾ�к��С�
     * @param rowIdRenderer �к�����Ⱦ����
     */
    public ColumnView(TableColumnModel source, boolean isRowIdVisible, TableCellRenderer rowIdRenderer) {
        this(source, true, isRowIdVisible, rowIdRenderer);
    }

    /**
     * ���췽����
     * @param source ��ͼԴ��
     * @param isRowHeader ��ʶ�Ƿ�Ϊ��ͷ�С�
     * @param isRowIdVisible ��ʶ�Ƿ���ʾ�к��С�
     * @param rowIdRenderer �к�����Ⱦ����
     */
    public ColumnView(TableColumnModel source, boolean isRowHeader, boolean isRowIdVisible, TableCellRenderer rowIdRenderer) {
        m_isRowHeader = isRowHeader;
        m_isRowIdVisible = isRowIdVisible;
        m_rowIdRenderer = rowIdRenderer == null ? new RowIdRenderer() : rowIdRenderer;

        setSource(source);

        if (!m_isRowHeader) {
            for (int i = 0, size = m_source.getColumnCount(); i < size; i++) {
                addColumn(m_source.getColumn(i));
            }
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
     * ����ͼ����ת��Ϊ��ͼԴ��ͼ������
     * @param viewColumnIndex ��ͼ������
     * @return ��ͼԴ��ͼ������
     */
    public int convertIndexToSource(int viewColumnIndex) {
        return m_source.getColumnIndex(getColumn(viewColumnIndex).getIdentifier());
    }

    /**
     * ����ͼԴ��ͼ����ת��Ϊ��ͼ������
     * @param sourceColumnIndex ��ͼԴ��ͼ������
     * @return ��ͼ������
     */
    public int convertIndexToView(int sourceColumnIndex) {
        return getColumnIndex(m_source.getColumn(sourceColumnIndex).getIdentifier());
    }

    /**
     * �ж�ָ�����Ƿ�Ϊ�к��С�
     * @param column ָ����������
     * @return Ϊtrue��ʾָ�������к��У�Ϊfalse��ʾָ���в����к��С�
     */
    public boolean isRowIdColumn(int column) {
        return isRowIdColumn(getColumn(column));
    }

    /*------------------------------------- TableColumnModelListener Public Method -------------------------------------*/

    /**
     * @see javax.swing.event.TableColumnModelListener#columnAdded(javax.swing.event.TableColumnModelEvent)
     */
    public void columnAdded(TableColumnModelEvent evt) {
        if (!m_isRowHeader) {
            TableColumn column = m_source.getColumn(evt.getToIndex());
            addColumn(column);

            if (m_table != null) {
                List customs = TableUtilities.getTableCustoms(m_table);

                if (customs != null) {
                    customs.add(new TableCustom(column, true));
                }
            }
        }
    }

    /**
     * @see javax.swing.event.TableColumnModelListener#columnRemoved(javax.swing.event.TableColumnModelEvent)
     */
    public void columnRemoved(TableColumnModelEvent evt) {
        for (int i = 0, size = getColumnCount(); i < size; i++) {
            TableColumn column = getColumn(i);

            if (!isRowIdColumn(column) && !isInSource(column)) {
                if (m_table != null) {
                    List customs = TableUtilities.getTableCustoms(m_table);

                    if (customs != null) {
                        for (int j = 0, sizes = customs.size(); j < sizes; j++) {
                            TableCustom custom = (TableCustom) customs.get(j);

                            if (custom.m_column == column) {
                                customs.remove(j);
                                break;
                            }
                        }
                    }
                }

                removeColumn(column);
                fireColumnRemoved(new TableColumnModelEvent(this, i, 0));
                return;
            }
        }
    }

    /**
     * @see javax.swing.event.TableColumnModelListener#columnMoved(javax.swing.event.TableColumnModelEvent)
     */
    public void columnMoved(TableColumnModelEvent evt) {
        /* �ݲ���ʵ�� */
    }

    /**
     * @see javax.swing.event.TableColumnModelListener#columnMarginChanged(javax.swing.event.ChangeEvent)
     */
    public void columnMarginChanged(ChangeEvent evt) {
        setColumnMargin(m_source.getColumnMargin());
    }

    /**
     * @see javax.swing.event.TableColumnModelListener#columnSelectionChanged(javax.swing.event.ListSelectionEvent)
     */
    public void columnSelectionChanged(ListSelectionEvent evt) {
        /* �ݲ���ʵ�� */
    }

    /*------------------------------------- Protected Method -------------------------------------*/

    /**
     * ������ͼԴ��
     * @param source ��ͼԴ��
     */
    protected void setSource(TableColumnModel source) {
        if (m_source != null) {
            m_source.removeColumnModelListener(this);
        }

        m_source = source;
        m_source.addColumnModelListener(this);

        List identifiers = TableUtilities.getColumnIdentifiers(this);
        TableUtilities.clear(this);

        if (m_isRowIdVisible) {
            identifiers.remove(I18N_INFO_ROWID);

            TableColumn rowIdColumn = new TableColumn(0, DEFAULT_ROWIDWIDTH, m_rowIdRenderer, null);
            rowIdColumn.setHeaderValue(I18N_INFO_ROWID);
            rowIdColumn.setMinWidth(15);
            rowIdColumn.setMaxWidth(999);
            rowIdColumn.setPreferredWidth(DEFAULT_ROWIDWIDTH);
            rowIdColumn.setHeaderRenderer(new TableCellRenderer() {

                /**
                 * ��Ⱦ�������
                 */
                protected JLabel m_rc = new JLabel();

                {
                    m_rc.setHorizontalAlignment(CENTER);
                    m_rc.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
                }

                /**
                 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
                 */
                public Component getTableCellRendererComponent(JTable table,
                                                               Object value,
                                                               boolean isSelected,
                                                               boolean hasFocus,
                                                               int row,
                                                               int column) {
                    m_rc.setText(value.toString());

                    if (table != null) {
                        JTableHeader header = table.getTableHeader();

                        if (header != null) {
                            m_rc.setForeground(header.getForeground());
                            m_rc.setBackground(header.getBackground());
                            m_rc.setFont(header.getFont());
                        }
                    }

                    if (m_table != null) {
                        m_rc.setEnabled(m_table.isEnabled());
                    }

                    return m_rc;
                }

            });

            addColumn(rowIdColumn);
        }

        for (int i = 0, size = identifiers.size(); i < size; i++) {
            addColumn(m_source.getColumn(m_source.getColumnIndex(identifiers.get(i))));
        }
    }

    /**
     * ��ȡ��ͼԴͬ���С�
     * @param modelIndex ģ��������
     * @return ��ͼԴͬ���С�
     */
    protected TableColumn getSyncColumn(int modelIndex) {
        for (int i = m_isRowIdVisible ? 1 : 0, size = getColumnCount(); i < size; i++) {
            TableColumn column = getColumn(i);
            if (column.getModelIndex() == modelIndex) {
                return column;
            }
        }

        TableColumn column = TableUtilities.getColumn(m_source, modelIndex);
        addColumn(column);

        return column;
    }

    /**
     * �ж����Ƿ�����ͼԴ�С�
     * @param column �С�
     * @return Ϊtrue��ʾ�л�����ͼԴ�У�Ϊfalse��ʾ�в�����ͼԴ�С�
     */
    protected boolean isInSource(TableColumn column) {
        return TableUtilities.contains(m_source, column);
    }

    /*------------------------------------- Friendly Method -------------------------------------*/

    /* Add Friendly Method */

    /*------------------------------------- Private Method -------------------------------------*/

    /* Add Private Method */

}
