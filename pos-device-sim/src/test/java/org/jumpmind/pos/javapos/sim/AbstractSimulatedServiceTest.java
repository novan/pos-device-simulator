package org.jumpmind.pos.javapos.sim;

import jpos.BaseControl;
import jpos.JposConst;
import jpos.JposException;
import jpos.events.DataEvent;
import jpos.events.DirectIOEvent;
import jpos.events.ErrorEvent;
import jpos.events.OutputCompleteEvent;
import jpos.events.StatusUpdateEvent;
import jpos.services.EventCallbacks;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class AbstractSimulatedServiceTest {
    MockAbstractSimulatedService service;

    @Before
    public void before() {
        service = new MockAbstractSimulatedService();
    }

    @Test
    public void testGetServiceVersion() throws Exception {
        Assert.assertEquals(1011000, service.getDeviceServiceVersion());
    }

    @Test
    public void testCheckIfOpenJposException() throws Exception {
        try {
            service.checkIfOpen();
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof JposException);
        }
    }

    @Test
    public void testCheckIfOpen() throws Exception {
        service.open("testDevice", null);
        service.checkIfOpen();
    }

    @Test
    public void testCheckIfClaimedJposException() throws Exception {
        try {
            service.open("testDevice", null);
            service.checkIfClaimed();
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof JposException);
        }
    }

    @Test
    public void testCheckIfClaimed() throws Exception {
        service.open("testDevice", null);
        service.claim(10000);
        service.checkIfClaimed();
    }

    @Test
    public void testOpened() throws Exception {
        service.open("testDevice", new MockEventCallbacks());

        service.checkIfOpen();
        Assert.assertEquals(JposConst.JPOS_S_IDLE, service.getState());
        Assert.assertNotNull(service.getCallbacks());
    }

    @Test
    public void testClosed() throws Exception {
        service.open("testDevice", new MockEventCallbacks());
        service.checkIfOpen();
        service.close();

        Assert.assertEquals(JposConst.JPOS_S_CLOSED, service.getState());
        Assert.assertNull(service.getCallbacks());
        Assert.assertFalse(service.getDeviceEnabled());
        Assert.assertFalse(service.getFreezeEvents());
        Assert.assertFalse(service.getClaimed());
        Assert.assertFalse(service.getFlag());
    }

    @Test
    public void testClaim() throws Exception {
        service.open("testDevice", new MockEventCallbacks());
        service.claim(10000);

        Assert.assertTrue(service.getClaimed());
    }

    @Test
    public void testEnable() throws Exception {
        service.open("testDevice", new MockEventCallbacks());
        service.claim(10000);
        service.setDeviceEnabled(true);

        Assert.assertTrue(service.getDeviceEnabled());
    }

    @Test
    public void testRelease() throws Exception {
        service.open("testDevice", new MockEventCallbacks());
        service.claim(10000);
        service.release();

        Assert.assertEquals(JposConst.JPOS_S_IDLE, service.getState());
        Assert.assertFalse(service.getDeviceEnabled());
        Assert.assertFalse(service.getClaimed());
    }

    public class MockAbstractSimulatedService extends AbstractSimulatedService {
        private boolean flag = true;

        @Override
        public void reset() {
            flag = false;
        }

        public boolean getFlag() {
            return this.flag;
        }
    }

    public class MockEventCallbacks implements EventCallbacks {
        public void fireDataEvent(DataEvent paramDataEvent) {
        }

        public void fireDirectIOEvent(DirectIOEvent paramDirectIOEvent) {
        }

        public void fireErrorEvent(ErrorEvent paramErrorEvent) {
        }

        public void fireOutputCompleteEvent(
                OutputCompleteEvent paramOutputCompleteEvent) {
        }

        public void fireStatusUpdateEvent(
                StatusUpdateEvent paramStatusUpdateEvent) {
        }

        public BaseControl getEventSource() {
            return null;
        }
    }
}
