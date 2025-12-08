org.springframework.cloud.contract.spec.Contract.make {
    description "Supprimer un rendez-vous existant"

    request {
        method DELETE()
        url("/api/appointments/1")
    }

    response {
        status 204
    }
}
