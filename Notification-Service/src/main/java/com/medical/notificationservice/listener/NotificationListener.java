package com.medical.notificationservice.listener;



import com.medical.notificationservice.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    /**
     * TP5: La méthode écoute la queue et reçoit le message en JSON brut (String).
     * Si vous aviez besoin des champs, vous devriez utiliser une librairie comme Jackson
     * pour parser le JSON manuellement. Ici, on se contente de l'afficher.
     */
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handleAppointmentCreation(String message) {
        // Le message est une chaîne de caractères (le JSON brut)
        System.out.println("----------------------------------------------");
        System.out.println("✅ NOTIFICATION REÇUE (via RabbitMQ)!");
        System.out.println("Message JSON brut reçu:");
        System.out.println(message);
        System.out.println("----------------------------------------------");
    }
}