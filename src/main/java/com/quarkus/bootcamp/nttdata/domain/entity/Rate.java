package com.quarkus.bootcamp.nttdata.domain.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@MongoEntity(collection = "rate")
public class Rate {
    private ObjectId id;
    private double rateBuys;
    private double rateSale;
    private boolean state;
}
