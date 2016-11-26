package com.tj.tema5.controller

import com.tj.tema5.dto.Node
import com.tj.tema5.services.DefaultRepartitionService
import com.tj.tema5.services.RepartitionService

import javax.faces.bean.CustomScoped
import javax.faces.bean.ManagedBean

@ManagedBean
@CustomScoped(value = "#{window}")
class RepartitionController extends AbstractController {
    RepartitionService repartitionService

    RepartitionController() {
        repartitionService = new DefaultRepartitionService()
    }

    List<Node> repartitionResult() {
        repartitionService.getNodes(connection.connection)
    }

    String startRepartition() {
        repartitionService.repartition(connection.connection)
        "final_result?faces-redirect=true"
    }
}
