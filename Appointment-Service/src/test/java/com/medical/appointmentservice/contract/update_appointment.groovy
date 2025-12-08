org.springframework.cloud.contract.spec.Contract.make {
    description "Modifier un rendez-vous existant"

    request {
        method PUT()
        url("/api/appointments/1")
        headers { contentType(applicationJson()) }
        body(
                doctorId: 2,
                patientId: 1,
                dateHeure: "2025-12-08T10:00:00",
                motif: "Consultation suivie",
                statut: "CONFIRME"
        )
    }

    response {
        status 200
        headers { contentType(applicationJson()) }
        body(
                id: 1,
                doctorId: 2,
                patientId: 1,
                dateHeure: "2025-12-08T10:00:00",
                motif: "Consultation suivie",
                statut: "CONFIRME"
        )
    }
}
