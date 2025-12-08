org.springframework.cloud.contract.spec.Contract.make {
    description "Lister les rendez-vous pour un patient"

    request {
        method GET()
        urlPath("/api/appointments") {
            queryParameters { parameter "patientId": "1" }
        }
    }

    response {
        status 200
        headers { contentType(applicationJson()) }
        body([
                [
                        id: 1,
                        doctorId: 2,
                        patientId: 1,
                        dateHeure: "2025-12-08T10:00:00",
                        motif: "Consultation suivie",
                        statut: "CONFIRME"
                ]
        ])
    }
}
