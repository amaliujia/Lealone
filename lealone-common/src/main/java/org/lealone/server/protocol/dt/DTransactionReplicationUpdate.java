/*
 * Copyright Lealone Database Group.
 * Licensed under the Server Side Public License, v 1.
 * Initial Developer: zhh
 */
package org.lealone.server.protocol.dt;

import java.io.IOException;

import org.lealone.net.NetInputStream;
import org.lealone.server.protocol.PacketDecoder;
import org.lealone.server.protocol.PacketType;
import org.lealone.server.protocol.replication.ReplicationUpdate;

public class DTransactionReplicationUpdate extends ReplicationUpdate {

    public DTransactionReplicationUpdate(String sql, String replicationName) {
        super(sql, replicationName);
    }

    public DTransactionReplicationUpdate(NetInputStream in, int version) throws IOException {
        super(in, version);
    }

    @Override
    public PacketType getType() {
        return PacketType.DISTRIBUTED_TRANSACTION_REPLICATION_UPDATE;
    }

    @Override
    public PacketType getAckType() {
        return PacketType.DISTRIBUTED_TRANSACTION_REPLICATION_UPDATE_ACK;
    }

    public static final Decoder decoder = new Decoder();

    private static class Decoder implements PacketDecoder<DTransactionReplicationUpdate> {
        @Override
        public DTransactionReplicationUpdate decode(NetInputStream in, int version) throws IOException {
            return new DTransactionReplicationUpdate(in, version);
        }
    }
}
