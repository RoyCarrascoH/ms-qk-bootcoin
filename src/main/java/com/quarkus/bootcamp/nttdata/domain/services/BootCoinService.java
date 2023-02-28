package com.quarkus.bootcamp.nttdata.domain.services;

import com.quarkus.bootcamp.nttdata.domain.entity.BootCoin;
import com.quarkus.bootcamp.nttdata.domain.respository.BootCoinRepository;
import com.quarkus.bootcamp.nttdata.infraestructure.response.entity.Balance;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class BootCoinService {

    @Inject
    BootCoinRepository repository;

    public Uni<BootCoin> add(BootCoin bootCoin) {
        return repository.persist(bootCoin);
    }

    public Uni<List<BootCoin>> getAll() {
        return repository.listAll();
    }

    public Uni<BootCoin> getById(String id) {
        return repository.findById(new ObjectId(id));
    }

    public Uni<BootCoin> updateCardId(String id, Balance balance) {
        Uni<BootCoin> bootcoin = repository.findById(new ObjectId(id));
        return bootcoin
                .onItem().transform(au -> {
                    au.setBalance(balance.getBalance());
                    return au;
                }).call(au -> repository.persistOrUpdate(au));
    }

}