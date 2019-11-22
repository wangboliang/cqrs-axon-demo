package com.demo.query.config;

/**
 * <p>
 *
 * </p>
 *
 * @author wangliang
 * @since 2019/11/21
 */
//@Configuration
public class AxonConfig {

//    @Value("${mongodb.url}")
//    private String mongoUrl;
//
//    @Value("${mongodb.dbname}")
//    private String mongoDbName;
//
//    @Value("${mongodb.events.collection.name}")
//    private String eventsCollectionName;
//
//    @Value("${mongodb.events.snapshot.collection.name}")
//    private String snapshotCollectionName;
//
//    /*@Bean
//    public Serializer axonJsonSerializer() {
//        return new JacksonSerializer();
//    }*/
//
//    @Bean
//    public EventStorageEngine eventStorageEngine(Serializer serializer) {
//        return new MongoEventStorageEngine(
//                serializer, null, axonMongoTemplate(), new DocumentPerEventStorageStrategy());
//    }
//
//    @Bean(name = "axonMongoTemplate")
//    public MongoTemplate axonMongoTemplate() {
//        MongoTemplate template = new DefaultMongoTemplate(mongoClient(), mongoDbName, eventsCollectionName, snapshotCollectionName);
//        return template;
//    }
//
//    @Bean
//    public MongoClient mongoClient() {
//        MongoFactory mongoFactory = new MongoFactory();
//        mongoFactory.setMongoAddresses(Arrays.asList(new ServerAddress(mongoUrl)));
//        return mongoFactory.createMongo();
//    }

    /*@Bean
    public SagaStore sagaStore(){
        org.axonframework.mongo.eventhandling.saga.repository.MongoTemplate mongoTemplate =
                new org.axonframework.mongo.eventhandling.saga.repository.DefaultMongoTemplate(mongoClient());
        return new MongoSagaStore(mongoTemplate, axonJsonSerializer());
    }*/

}
