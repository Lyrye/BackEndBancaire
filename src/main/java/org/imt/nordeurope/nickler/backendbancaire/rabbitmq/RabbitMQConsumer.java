package org.imt.nordeurope.nickler.backendbancaire.rabbitmq;

//import org.mines.spring.config.MessagingConfig;
import com.google.gson.Gson;
import org.imt.nordeurope.nickler.backendbancaire.Model.Transaction;
import org.imt.nordeurope.nickler.backendbancaire.Service.IManageService;
import org.imt.nordeurope.nickler.backendbancaire.Service.ITransactionService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class RabbitMQConsumer {
    @Autowired
    ITransactionService transactionService;

    @Autowired
    IManageService manageService;

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(String json) {
        System.out.println("Message receive from the queue : " + json );
        Gson gson = new Gson();
        Transaction transaction = gson.fromJson(json,Transaction.class);
        transactionService.saveTransaction(transaction);
        manageService.doTransaction(transaction);
    }
}