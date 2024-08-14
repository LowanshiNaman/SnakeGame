package com.example.demoProject.Tasks;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultithreadAtomicCounter {


    private final MongoCollection<Document> counterCollection;

    public MultithreadAtomicCounter(MongoDatabase database) {
        this.counterCollection = database.getCollection("counters");
    }

    public int getNextSequence(String key) {
        Document query = new Document("_id", key);
        Document update = new Document("$inc", new Document("sequence_value", 1));
        Document options = new Document("new", true);

        Document result = counterCollection.findOneAndUpdate(query, update);
        return result.getInteger("sequence_value");
    }

    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("mydatabase");
            MultithreadAtomicCounter multithreadAtomicCounter = new MultithreadAtomicCounter(database);
            runDemo(multithreadAtomicCounter);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Running in MultiThread Environment
    private static final int NUM_THREADS = 10;
    private static final int NUM_INCREMENTS = 100;

    public static void runDemo(MultithreadAtomicCounter multithreadAtomicCounter) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        for (int i = 0; i < NUM_THREADS; i++) {
            executor.submit(() -> {
                for (int j = 0; j < NUM_INCREMENTS; j++) {
                    int newValue = multithreadAtomicCounter.getNextSequence("myCounter");
                    System.out.println("Thread " + Thread.currentThread().getId() + " incremented to: " + newValue);
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Final counter value: " + multithreadAtomicCounter.getNextSequence("myCounter"));
    }
}
