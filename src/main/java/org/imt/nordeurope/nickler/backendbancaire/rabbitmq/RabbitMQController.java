package org.imt.nordeurope.nickler.backendbancaire.rabbitmq;

import com.google.gson.Gson;
import org.imt.nordeurope.nickler.backendbancaire.Model.Account;
import org.imt.nordeurope.nickler.backendbancaire.Model.Enums.AccountType;
import org.imt.nordeurope.nickler.backendbancaire.Model.Enums.Currency;
import org.imt.nordeurope.nickler.backendbancaire.Model.Transaction;
import org.imt.nordeurope.nickler.backendbancaire.Repositories.AccountRepository;
import org.imt.nordeurope.nickler.backendbancaire.Repositories.TransactionRepository;
import org.imt.nordeurope.nickler.backendbancaire.Service.IAccountService;
import org.imt.nordeurope.nickler.backendbancaire.Service.ITransactionService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMQController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    IAccountService accountService;

    @Autowired
    ITransactionService transactionService;

    @GetMapping("/publish")
    public String publishMessage() {
        Double value = 100.0;
        Date date = new Date();
        Account account1, account2;

        if(accountService.getByIban("FR7317569000708788759959H70")==null){
            account1 = new Account("dupont","jean","courant",1450.0, AccountType.CURRENT, Currency.EURO,"FR7317569000708788759959H70");
            accountService.saveAccount(account1);
        }else {
             account1= accountService.getByIban("FR7317569000708788759959H70");
        }

        if(accountService.getByIban("FR5512739000308198735599N46")==null){
            account2 = new Account("dupont","marie","courant",1800.0, AccountType.CURRENT, Currency.EURO,"FR5512739000308198735599N46");
            accountService.saveAccount(account2);
        }else {
             account2= accountService.getByIban("FR5512739000308198735599N46");
        }

        Transaction transaction = new Transaction(account1,account2,value,date);
        transactionService.saveTransaction(transaction);

        Gson gson = new Gson();
        String json = gson.toJson(transaction);

        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE,
                MessagingConfig.ROUTING_KEY, json);
        return "OK";
    }
}
