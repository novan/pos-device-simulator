package org.jumpmind.pos.javapos.sim.ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import jpos.MSR;
import jpos.events.DataEvent;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jumpmind.pos.javapos.sim.SimulatedMSRService;
import org.jumpmind.pos.javapos.sim.beans.MSRCardBean;

public class SimulatedMSRPanel extends BaseSimulatedPanel {
    
    private static final long serialVersionUID = 1L;
    private static SimulatedMSRPanel me;
    private Map<String, MSRCardBean> cards = new HashMap<String, MSRCardBean>();
    private MSRCardBean selectedCard;
    private SimulatedMSRService deviceCallback;   

    private SimulatedMSRPanel() {
    }
    
    public static SimulatedMSRPanel getInstance() {
        if (me == null) {
            me = new SimulatedMSRPanel();
        }
        return me;
    }
    
    public void init() {
        setInitialized(true);
        
        this.selectedCard = new MSRCardBean();
        this.setFocusable(false);
        this.setBackground(Color.LIGHT_GRAY);
        
        JButton button1 = new JButton("Swipe Card");
        button1.setSize(200, 20);

        loadCards();
        
        final JLabel lblTrack1Data = new JLabel("Track1 Data : ");
        final JTextField txtTrack1Data = new JTextField("");
        final JLabel lblTrack2Data = new JLabel("Track2 Data : ");
        final JTextField txtTrack2Data = new JTextField("900556062940");
        final JLabel lblTrack3Data = new JLabel("Track3 Data : ");
        final JTextField txtTrack3Data = new JTextField("");
        final JLabel lblTrack4Data = new JLabel("Track4 Data : ");
        final JTextField txtTrack4Data = new JTextField("");
        final JLabel lblAccountNumber = new JLabel("Account Number : ");
        final JTextField txtAccountNumber = new JTextField("");
        final JLabel lblExpirationDate = new JLabel("Expiration Date : ");
        final JTextField txtExpirationDate = new JTextField("");
        final JLabel lblTitle = new JLabel("Title : ");
        final JTextField txtTitle = new JTextField("");
        final JLabel lblFirstName = new JLabel("FirstName : ");
        final JTextField txtFirstName = new JTextField("");
        final JLabel lblMiddleInitial = new JLabel("MiddleInitial : ");
        final JTextField txtMiddleInitial = new JTextField("");
        final JLabel lblSurname = new JLabel("Surname : ");
        final JTextField txtSurname = new JTextField("");
        final JLabel lblSuffix = new JLabel("Suffix : ");
        final JTextField txtSuffix = new JTextField("");
        final JLabel lblServiceCode = new JLabel("Service Code : ");
        final JTextField txtServiceCode = new JTextField("");
        final JLabel lblTrack1DiscData = new JLabel("Track1 Disc. Data : ");
        final JTextField txtTrack1DiscData = new JTextField("");
        final JLabel lblTrack2DiscData = new JLabel("Track2 Disc. Data : ");
        final JTextField txtTrack2DiscData = new JTextField("");
        final JLabel lblDataCount = new JLabel("Data Count : ");
        final JTextField txtDataCount = new JTextField("");

        JComboBox cbCards = new JComboBox(loadMSRCardBeans());
        cbCards.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        JComboBox cb = (JComboBox) e.getSource();
                        String label = (String) cb.getSelectedItem();
                        MSRCardBean card = cards.get(label);
                        
                        txtTrack1Data.setText(card.getTrack1Data());
                        txtTrack2Data.setText(card.getTrack2Data());
                        txtTrack3Data.setText(card.getTrack3Data());
                        txtTrack4Data.setText(card.getTrack4Data());
                        txtAccountNumber.setText(card.getAccountNumber());
                        txtExpirationDate.setText(card.getExpirationDate());
                        txtTitle.setText(card.getTitle());
                        txtFirstName.setText(card.getFirstName());
                        txtMiddleInitial.setText(card.getMiddleInitial());
                        txtSurname.setText(card.getSurName());
                        txtSuffix.setText(card.getSuffix());
                        txtServiceCode.setText(card.getServiceCode());
                        txtTrack1DiscData.setText(card.getTrack1DiscretionaryData());
                        txtTrack2DiscData.setText(card.getTrack2DiscretionaryData());
                        try {
                            txtDataCount.setText(new Integer(card.getDataCount()).toString());
                        }
                        catch (Exception ex) {
                            logger.warn("Unable to set data count, not a valid integer.");
                        }
                    }
                });

            }
        });

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if (getCallbacks() != null) {
                            MSR msr = new MSR();
                            DataEvent evt = new DataEvent(msr, 1);
                            evt.getSource();
        
                            
                            selectedCard.setTrack1Data(txtTrack1Data.getText());
                            selectedCard.setTrack2Data(txtTrack2Data.getText());
                            selectedCard.setTrack3Data(txtTrack3Data.getText());
                            selectedCard.setTrack4Data(txtTrack4Data.getText());
        
                            selectedCard.setAccountNumber(txtAccountNumber.getText());
                            selectedCard.setExpirationDate(txtExpirationDate.getText());
                            selectedCard.setTitle(txtTitle.getText());
                            selectedCard.setFirstName(txtFirstName.getText());
                            selectedCard.setMiddleInitial(txtMiddleInitial.getText());
                            selectedCard.setSurName(txtSurname.getText());
                            selectedCard.setSuffix(txtSuffix.getText());
                            selectedCard.setServiceCode(txtServiceCode.getText());
                            selectedCard.setTrack1DiscretionaryData(txtTrack1DiscData.getText());
                            selectedCard.setTrack2DiscretionaryData(txtTrack2DiscData.getText());
                            try {
                                selectedCard.setDataCount(new Integer(txtDataCount.getText()).intValue());
                            } catch (Exception ex) {
                            }
        
                            getDeviceCallback().setSelectedCard(selectedCard);
                            getCallbacks().fireDataEvent(new DataEvent(evt, 1));
                        }
                    }
                });
            }
        });

        JLabel header = new JLabel("<html>Select a card from the drop down or enter values manually.</html>");

        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        addToGridBag(0, 0, 2, header, c, this);
        addToGridBag(0, 1, 2, cbCards, c, this);
        addToGridBag(0, 2, 1, lblTrack1Data, c, this);
        addToGridBag(1, 2, 1, txtTrack1Data, c, this);
        addToGridBag(0, 3, 1, lblTrack2Data, c, this);
        addToGridBag(1, 3, 1, txtTrack2Data, c, this);
        addToGridBag(0, 4, 1, lblTrack3Data, c, this);
        addToGridBag(1, 4, 1, txtTrack3Data, c, this);
        addToGridBag(0, 5, 1, lblTrack4Data, c, this);
        addToGridBag(1, 5, 1, txtTrack4Data, c, this);
        addToGridBag(0, 6, 1, lblAccountNumber, c, this);
        addToGridBag(1, 6, 1, txtAccountNumber, c, this);
        addToGridBag(0, 7, 1, lblExpirationDate, c, this);
        addToGridBag(1, 7, 1, txtExpirationDate, c, this);
        addToGridBag(0, 8, 1, lblTitle, c, this);
        addToGridBag(1, 8, 1, txtTitle, c, this);
        addToGridBag(0, 9, 1, lblFirstName, c, this);
        addToGridBag(1, 9, 1, txtFirstName, c, this);
        addToGridBag(0, 10, 1, lblMiddleInitial, c, this);
        addToGridBag(1, 10, 1, txtMiddleInitial, c, this);
        addToGridBag(0, 11, 1, lblSurname, c, this);
        addToGridBag(1, 11, 1, txtSurname, c, this);
        addToGridBag(0, 12, 1, lblSuffix, c, this);
        addToGridBag(1, 12, 1, txtSuffix, c, this);
        addToGridBag(0, 13, 1, lblServiceCode, c, this);
        addToGridBag(1, 13, 1, txtServiceCode, c, this);
        addToGridBag(0, 14, 1, lblTrack1DiscData, c, this);
        addToGridBag(1, 14, 1, txtTrack1DiscData, c, this);
        addToGridBag(0, 15, 1, lblTrack2DiscData, c, this);
        addToGridBag(1, 15, 1, txtTrack2DiscData, c, this);
        addToGridBag(0, 16, 1, lblDataCount, c, this);
        addToGridBag(1, 16, 1, txtDataCount, c, this);
        addToGridBag(0, 17, 2, button1, c, this);
    }
    
    public void loadCards() {
        SAXBuilder builder = new SAXBuilder();
        Document doc = null;
        String xmlFile = "/org/jumpmind/pos/javapos/sim/SimulatedMSRService.xml";

        try {
            doc = builder.build(new InputStreamReader(SimulatedMSRService.class.getResourceAsStream(xmlFile)));
            Element msr = doc.getRootElement();
            for (Object cardObj : msr.getChildren()) {
                Element cardXML = (Element) cardObj;
                MSRCardBean card = new MSRCardBean();

                card.setLabel(readElement(cardXML, "label"));
                card.setTrack1Data(readElement(cardXML, "track1Data"));
                card.setTrack2Data(readElement(cardXML, "track2Data"));
                card.setTrack3Data(readElement(cardXML, "track3Data"));
                card.setTrack4Data(readElement(cardXML, "track4Data"));
                card.setAccountNumber(readElement(cardXML, "accountNumber"));
                card.setExpirationDate(readElement(cardXML, "expirationDate"));
                card.setTitle(readElement(cardXML, "title"));
                card.setFirstName(readElement(cardXML, "firstName"));
                card.setMiddleInitial(readElement(cardXML, "middleInitial"));
                card.setSurName(readElement(cardXML, "surName"));
                card.setSuffix(readElement(cardXML, "suffix"));
                card.setServiceCode(readElement(cardXML, "serviceCode"));
                card.setTrack1DiscretionaryData(readElement(cardXML, "track1DiscretionaryData"));
                card.setTrack2DiscretionaryData(readElement(cardXML, "track2DiscretionaryData"));
                card.setDataCount(readElementInt(cardXML, "dataCount"));

                cards.put(card.getLabel(), card);
            }
        } catch (Exception e) {
            logger.error("Unable to preload cards from " + xmlFile);
            e.printStackTrace();
        }

    }
    
    public Object[] loadMSRCardBeans() {
        Object[] val = null;
        if (cards != null) {
            val = new TreeSet<String>(cards.keySet()).toArray();
        }
        return val;
    }   

    public SimulatedMSRService getDeviceCallback() {
        return deviceCallback;
    }

    public void setDeviceCallback(SimulatedMSRService deviceCallback) {
        this.deviceCallback = deviceCallback;
    }
}
