package com.zheltoukhov.groupstatistic.storage;

import com.mongodb.MongoClient;
import com.zheltoukhov.groupstatistic.ConfigProperties;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class MorphiaService {
    private static Morphia morphia;
    private static Datastore datastore;
    private static MorphiaService instance;

    private MorphiaService(){

        String host = ConfigProperties.getProperty("mongo.host");
        MongoClient mongoClient = new MongoClient(host);

        this.morphia = new Morphia();
        String databaseName = ConfigProperties.getProperty("mongo.db-name");
        this.datastore = morphia.createDatastore(mongoClient, databaseName);
    }

    public static MorphiaService getInstance() {
        if (instance == null) {
            instance = new MorphiaService();
        }
        return instance;
    }


    public Morphia getMorphia() {
        return morphia;
    }

    public Datastore getDatastore() {
        return datastore;
    }
}
