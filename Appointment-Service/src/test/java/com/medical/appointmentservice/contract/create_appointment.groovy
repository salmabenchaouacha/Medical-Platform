org.springframework.cloud.contract.spec.Contract.make {
    description "Créer un rendez-vous"

    request {
        method POST()
        url("/api/appointments")
        headers { contentType(applicationJson()) }
        body(
                doctorId: 1,
                patientId: 1,
                dateHeure: "2025-12-07T15:30:00",
                motif: "Consultation",
                statut: "CONFIRME"
        )
    }

    response {
        status 201
        headers { contentType(applicationJson()) }
        body(
                id: anyNumber(),
                doctorId: 1,
                patientId: 1,
                dateHeure: "2025-12-07T15:30:00",
                motif: "Consultation",
                statut: "CONFIRME"
        )
    }
}
