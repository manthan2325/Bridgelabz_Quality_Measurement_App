
/*
 * Class Name  : QuantityMeasurementCacheRepository
 * Description : Provides singleton in-memory storage with file persistence.
 * Author      : Manthan Hanchate
 * Date        : 19/11/2026
 */
package org.example.QuantityMeasurement.repository;

import org.example.QuantityMeasurement.entity.QuantityMeasurementEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementCacheRepository
        implements IQuantityMeasurementRepository {

    private static final String FILE_NAME = "quantity-measurements.dat";

    private static final QuantityMeasurementCacheRepository INSTANCE =
            new QuantityMeasurementCacheRepository(FILE_NAME);

    private final List<QuantityMeasurementEntity> cache;
    private final String fileName;

    // Initializes repository storage from the configured file.
    private QuantityMeasurementCacheRepository(String fileName) {
        this.fileName = fileName;
        this.cache = loadFromDisk();
    }

    // Returns the singleton repository instance.
    public static QuantityMeasurementCacheRepository getInstance() {
        return INSTANCE;
    }

    // Creates an isolated repository for testing.
    static QuantityMeasurementCacheRepository createForTesting(String fileName) {
        return new QuantityMeasurementCacheRepository(fileName);
    }

    @Override
    public synchronized void save(QuantityMeasurementEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }

        cache.add(entity);
        persistToDisk();
    }

    @Override
    public synchronized List<QuantityMeasurementEntity> findAll() {
        return new ArrayList<>(cache);
    }

    @Override
    public synchronized QuantityMeasurementEntity findById(String id) {
        if (id == null) {
            return null;
        }

        return cache.stream()
                .filter(entity -> entity.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public synchronized void clear() {
        cache.clear();
        persistToDisk();
    }

    // Reads serialized measurement records from disk.
    @SuppressWarnings("unchecked")
    private List<QuantityMeasurementEntity> loadFromDisk() {
        File file = new File(fileName);

        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try (ObjectInputStream input =
                     new ObjectInputStream(new FileInputStream(file))) {
            return (List<QuantityMeasurementEntity>) input.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            throw new IllegalStateException(
                    "Unable to load measurement records", exception);
        }
    }

    // Writes the complete cache to disk.
    private void persistToDisk() {
        try (ObjectOutputStream output =
                     new ObjectOutputStream(new FileOutputStream(fileName))) {
            output.writeObject(cache);
            output.flush();
        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Unable to persist measurement records", exception);
        }
    }
}