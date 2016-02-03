Getting started:
  1. Download latest jar file from [here](http://pos-device-simulator.googlecode.com/files/pos-device-sim-1.0.jar)
  1. Add the jar file to the Point of Sale classpath
  1. Update or create a new jpos.xml for the device simulator.
    * **Example of Simuluated MSR** 

&lt;JposEntry logicalName="MSR"&gt;



&lt;creation serviceClass="org.jumpmind.pos.javapos.sim.SimulatedMSRService" /&gt;



&lt;/JposEntry&gt;


  1. Run Point of Sale application