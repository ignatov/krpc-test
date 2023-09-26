package org.example

import org.jetbrains.krpc.RPCTransport
import org.jetbrains.krpc.test.KRPCTransportTestBase

class TransportTest : KRPCTransportTestBase() {
    private val stringTransport = StringTransport()

    override val clientTransport: RPCTransport get() = stringTransport.client
    override val serverTransport: RPCTransport get() = stringTransport.server
}

