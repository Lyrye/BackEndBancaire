package org.imt.nordeurope.nickler.backendbancaire.Service;

import org.imt.nordeurope.nickler.backendbancaire.Model.Transaction;

public interface IManageService {
    void doTransaction(Transaction transaction);
}
