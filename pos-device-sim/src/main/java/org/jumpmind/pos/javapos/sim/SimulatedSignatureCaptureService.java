package org.jumpmind.pos.javapos.sim;

import java.awt.Point;

import org.jumpmind.pos.javapos.sim.beans.SignatureCaptureBean;
import org.jumpmind.pos.javapos.sim.ui.SimulatedSignatureCapturePanel;

import jpos.JposException;
import jpos.services.SignatureCaptureService111;

public class SimulatedSignatureCaptureService extends AbstractSimulatedService implements SignatureCaptureService111 {
    
    private SignatureCaptureBean signature;
    
    @Override
    public void reset() {
    }

    public void clearInputProperties() throws JposException {
    }

    public void compareFirmwareVersion(String s, int[] ai) throws JposException {
    }

    public boolean getCapCompareFirmwareVersion() throws JposException {
        return false;
    }

    public boolean getCapUpdateFirmware() throws JposException {
        return false;
    }

    public void updateFirmware(String s) throws JposException {
    }

    public boolean getCapStatisticsReporting() throws JposException {
        return false;
    }

    public boolean getCapUpdateStatistics() throws JposException {
        return false;
    }

    public void resetStatistics(String s) throws JposException {
    }

    public void retrieveStatistics(String[] as) throws JposException {
    }

    public void updateStatistics(String s) throws JposException {
    }

    public int getCapPowerReporting() throws JposException {
        return 0;
    }

    public int getPowerState() throws JposException {
        return 0;
    }

    public void beginCapture(String s) throws JposException {
        this.signature = new SignatureCaptureBean();
        SimulatedSignatureCapturePanel.getInstance();
		SimulatedSignatureCapturePanel.clear();
    }

    public void clearInput() throws JposException {

    }

    public void endCapture() throws JposException {
    }

    public boolean getAutoDisable() throws JposException {
        return false;
    }

    public boolean getCapDisplay() throws JposException {
        return false;
    }

    public boolean getCapRealTimeData() throws JposException {
        return false;
    }

    public boolean getCapUserTerminated() throws JposException {
        return false;
    }

    public int getDataCount() throws JposException {
        return 0;
    }

    public boolean getDataEventEnabled() throws JposException {
        return false;
    }

    public int getMaximumX() throws JposException {
        return 0;
    }

    public int getMaximumY() throws JposException {
        return 0;
    }

    public Point[] getPointArray() throws JposException {
        return getSignature() != null ? getSignature().getPointArray() : null;
    }

    public byte[] getRawData() throws JposException {
        return getSignature() != null ? getSignature().getRawData() : null;
    }

    public boolean getRealTimeDataEnabled() throws JposException {
        return false;
    }

    public void setAutoDisable(boolean flag) throws JposException {
    }

    public void setDataEventEnabled(boolean flag) throws JposException {
    }

    public void setRealTimeDataEnabled(boolean flag) throws JposException {
    }

    public void checkHealth(int i) throws JposException {
    }

    public void directIO(int i, int[] ai, Object obj) throws JposException {
    }

    public String getCheckHealthText() throws JposException {
        return null;
    }

    public String getDeviceServiceDescription() throws JposException {
        return null;
    }

    public String getPhysicalDeviceDescription() throws JposException {
        return "SimulatedSignatureCaptureService";
    }

    public String getPhysicalDeviceName() throws JposException {
        return "SimulatedSignatureCaptureService";
    }
    
    public SignatureCaptureBean getSignature() {
        return signature;
    }

    public void setSignature(SignatureCaptureBean signature) {
        this.signature = signature;
    }

    public boolean getCapServiceAllowManagement() throws JposException {
        return false;
    }
}
