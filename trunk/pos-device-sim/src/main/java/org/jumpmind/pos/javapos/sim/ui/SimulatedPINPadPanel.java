package org.jumpmind.pos.javapos.sim.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import jpos.PINPad;
import jpos.events.DataEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jumpmind.pos.javapos.sim.SimulatedPINPadService;

public class SimulatedPINPadPanel extends BaseSimulatedPanel {
    static final Log logger = LogFactory.getLog(SimulatedPINPadPanel.class);
    private static final long serialVersionUID = 1L;
    private static SimulatedPINPadPanel me;
    private SimulatedPINPadService deviceCallback;
    protected BorderLayout defaultLayout;
	private JTextField textField;

    private SimulatedPINPadPanel() {
    }

    public static SimulatedPINPadPanel getInstance() {
        if (me == null) {
            me = new SimulatedPINPadPanel();
        }
        return me;
    }

    public void init() {
    	this.setInitialized(true);
        this.setFocusable(false);
        this.setBackground(Color.LIGHT_GRAY);
        this.setName("SimulatedPINPad");

        final JButton button = new JButton("Enter PIN");
        button.setName("EnterPin");
        textField = new JTextField();
        textField.setName("PinValue");
        textField.setSize(200, 20);
        textField.setPreferredSize(new Dimension(200, 20));

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

// *** Doesn't appear to be any straightforward "testing" way to input
// *** a PIN (Encrypted or otherwise), so it's hardcoded in SimulatedPINPadService
//                textField.select(0, textField.getText().length());
//                final byte[] textFieldValue = textField.getText().getBytes();

                    if (getCallbacks() != null) {
                    	PINPad pinpad = new PINPad();
                        DataEvent evt = new DataEvent(pinpad, 1);

                        getCallbacks().fireDataEvent(evt);
                    }
                }
        });
        
        GridBagConstraints c = new GridBagConstraints();

        addToGridBag(0, 0, 0, textField, c, this);
        addToGridBag(0, 1, 0, button, c, this);
    }    
    
    public SimulatedPINPadService getDeviceCallback() {
        return deviceCallback;
    }

    public void setDeviceCallback(SimulatedPINPadService deviceCallback) {
        this.deviceCallback = deviceCallback;
    }

	public void clear() {
		textField.setText("");
	}
}
