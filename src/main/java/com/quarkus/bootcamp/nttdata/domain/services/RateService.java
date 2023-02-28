package com.quarkus.bootcamp.nttdata.domain.services;

import com.quarkus.bootcamp.nttdata.domain.entity.Rate;
import com.quarkus.bootcamp.nttdata.domain.respository.RateRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class RateService {

    @Inject
    RateRepository rateRepository;

    public Uni<Rate> add(Rate rate) {
        return rateRepository.persist(rate);
    }

    public Uni<List<Rate>> getAll() {
        return rateRepository.listAll();
    }

    public Uni<Rate> getById(String id) {
        return rateRepository.findById(new ObjectId(id));
    }

  /*public Uni<BootCoin> add(UserRequest users) {
    Uni<NaturalPersonD> naturalPerson = naturalPersonApi.getById(Long.parseLong(users.getCustomerId().toString()));
    return naturalPerson.flatMap(np -> {
      if (np == null || np.getId() == null) {
        throw new NotFoundException("customer not found");
      }
      Uni<CardD> card = callCardsCustomer(users);
      return card.flatMap(c -> {
        if (c == null || c.getId() == null) {
          throw new NotFoundException("card not found");
        }
        Uni<Users> gottenUser = findByCustomerId(users.getCustomerId().toString());
        return gottenUser.flatMap(user -> {
          if (user != null) {
            throw new NotFoundException();
          }
          return saveUserAccount(users);
        });
      });
    });
  }

  /*public Uni<CardD> callCardsCustomer(UserRequest usersa) {
    Uni<List<CardD>> usersCards = iCardApi.getAll(usersa.getCustomerId(), 2L);
    return usersCards.onItem().<CardD>disjoint()
          .filter(uc -> (uc.getSerial().equals(usersa.getCard().getSerial())
                && uc.getMonth().equals(usersa.getCard().getMonth())
                && uc.getYear().equals(usersa.getCard().getYear())
                && uc.getCvv().equals(usersa.getCard().getCvv())))
          .collect().first();
  }

  public Uni<BootCoin> saveUserAccount(UserRequest users) {
    Users userPersist = new Users();
    userPersist.setCustomerId(users.getCustomerId());
    userPersist.setPassword(users.getPassword());
    return usersRepository.persist(userPersist).flatMap(up -> {
      BootCoin cards = new BootCoin();
      cards.setUserId(up.getId());
      cards.setSerial(users.getCard().getSerial());
      return cardsRepository.persist(cards);
    });
  }

  public Uni<Users> findByCustomerId(String customerId) {
    return usersRepository.find("{'customerId': ?1}", customerId).firstResult();
  }

  public Uni<BootCoin> findCardsBySerial(String serial) {
    return cardsRepository.find("{'serial': ?1}", serial).firstResult();
  }

  public Uni<Users> login(UserRequest users) {
    Uni<BootCoin> card = findCardsBySerial(users.getCard().getSerial());
    return card.onItem().transformToUni(c -> {
      if (c == null || c.getId() == null) {
        throw new NotFoundException("cards not found");
      }
      return usersRepository.findById(c.getUserId());
    });
  }*/
}