package com.quarkus.bootcamp.nttdata.domain.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@MongoEntity(collection = "bootcoin")
public class BootCoin {

  private ObjectId id;
  private ObjectId rateId;
  private ObjectId salesManId;//Vendedor&Comprador
  private double balance;
}
