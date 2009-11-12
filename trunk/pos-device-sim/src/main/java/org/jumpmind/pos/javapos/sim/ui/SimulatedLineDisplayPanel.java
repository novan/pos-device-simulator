package org.jumpmind.pos.javapos.sim.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import jpos.LineDisplay;
import jpos.events.DirectIOEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jumpmind.pos.javapos.sim.SimulatedLineDisplayService;

public class SimulatedLineDisplayPanel extends BaseSimulatedPanel {
    static final Log logger = LogFactory
            .getLog(SimulatedLineDisplayPanel.class);
    private static final long serialVersionUID = 1L;
    private static SimulatedLineDisplayPanel me;
    private JTextPane textArea;

    private SimulatedLineDisplayService deviceCallback;

    private SimulatedLineDisplayPanel() {
    }

    public static SimulatedLineDisplayPanel getInstance() {
        if (me == null) {
            me = new SimulatedLineDisplayPanel();
        }
        return me;
    }

    public void init() {
        setInitialized(true);

        this.setFocusable(false);
        textArea = new JTextPane();
        textArea.setEditable(false);

        StyledDocument doc = textArea.getStyledDocument();
        Style def = StyleContext.getDefaultStyleContext().getStyle(
                StyleContext.DEFAULT_STYLE);
        Style s = doc.addStyle("text", def);
        StyleConstants.setFontFamily(s, "Monospaced");

        JScrollPane scrollPane = new JScrollPane(textArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        JPanel formPanel = new JPanel();
        JButton btnYes = new JButton();
        btnYes.setText("Yes");

        JButton btnNo = new JButton();
        btnNo.setText("No");

        JLabel lblCreditDebitConfirm = new JLabel();
        lblCreditDebitConfirm.setText("Confirm credit/debit");

        btnYes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (getCallbacks() != null) {
                    String confirmMessage = "XEVT<FS>2<FS>1<FS>0<FS>CONFIRM";

                    DirectIOEvent evt = new DirectIOEvent(new LineDisplay(), 1,
                            1, confirmMessage.getBytes());

                    getCallbacks().fireDirectIOEvent(evt);
                }
            }
        });

        btnNo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (getCallbacks() != null) {
                    String confirmMessage = "XEVT<FS>2<FS>0<FS>0<FS>";

                    DirectIOEvent evt = new DirectIOEvent(new LineDisplay(), 1,
                            1, confirmMessage.getBytes());

                    getCallbacks().fireDirectIOEvent(evt);
                }
            }
        });

        formPanel.add(lblCreditDebitConfirm);
        formPanel.add(btnYes);
        formPanel.add(btnNo);

        setLayout(new BorderLayout());
        this.add(formPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public JTextPane getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextPane textArea) {
        this.textArea = textArea;
    }

    public SimulatedLineDisplayService getDeviceCallback() {
        return deviceCallback;
    }

    public void setDeviceCallback(SimulatedLineDisplayService deviceCallback) {
        this.deviceCallback = deviceCallback;
    }
}
