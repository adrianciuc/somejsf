package com.tj.tema5.services

import com.tj.tema5.dto.Node

import java.sql.Connection


interface RepartitionService {

    List<Node> getNodes(Connection connection)

    void repartition(Connection connection)
}