/*
 * 2022 - NDTP v6 Protocol for Granit.
 */
package org.traccar.protocol;

import org.traccar.BaseProtocol;
import org.traccar.PipelineBuilder;
import org.traccar.TrackerServer;

public class NDTPv6Protocol extends BaseProtocol {

  public NDTPv6Protocol() {
    addServer(
      new TrackerServer(false, getName()) {

        @Override
        protected void addProtocolHandlers(PipelineBuilder pipeline) {
          pipeline.addLast(new NDTPv6ProtocolDecoder(NDTPv6Protocol.this));
        }
      }
    );
  }
}
