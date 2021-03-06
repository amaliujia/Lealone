/*
 * Copyright Lealone Database Group.
 * Licensed under the Server Side Public License, v 1.
 * Initial Developer: zhh
 */
package org.lealone.server.protocol.dt;

import java.io.IOException;

import org.lealone.db.result.Result;
import org.lealone.net.NetInputStream;
import org.lealone.net.NetOutputStream;
import org.lealone.server.protocol.PacketDecoder;
import org.lealone.server.protocol.PacketType;
import org.lealone.server.protocol.statement.StatementQueryAck;

public class DTransactionQueryAck extends StatementQueryAck {

    public final String localTransactionNames;

    public DTransactionQueryAck(Result result, int rowCount, int fetchSize, String localTransactionNames) {
        super(result, rowCount, fetchSize);
        this.localTransactionNames = localTransactionNames;
    }

    public DTransactionQueryAck(NetInputStream in, int version) throws IOException {
        super(in, version);
        localTransactionNames = in.readString();
    }

    @Override
    public PacketType getType() {
        return PacketType.DISTRIBUTED_TRANSACTION_QUERY_ACK;
    }

    @Override
    public void encodeExt(NetOutputStream out, int version) throws IOException {
        out.writeString(localTransactionNames);
    }

    public static final Decoder decoder = new Decoder();

    private static class Decoder implements PacketDecoder<DTransactionQueryAck> {
        @Override
        public DTransactionQueryAck decode(NetInputStream in, int version) throws IOException {
            return new DTransactionQueryAck(in, version);
        }
    }
}
