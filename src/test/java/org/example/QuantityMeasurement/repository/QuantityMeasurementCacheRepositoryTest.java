
/*
 * Class Name  : QuantityMeasurementCacheRepositoryTest
 * Description : Tests repository storage and file persistence.
 * Author      : Manthan Hanchate
 * Date        : 19/11/2026
 */
package org.example.QuantityMeasurement.repository;

import org.example.QuantityMeasurement.entity.QuantityMeasurementEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementCacheRepositoryTest {

    @TempDir
    Path tempDir;

    private QuantityMeasurementCacheRepository repository;
    private String storageFile;

    @BeforeEach
    void setUp() {
        storageFile = tempDir.resolve("test-measurements.dat").toString();
        repository =
                QuantityMeasurementCacheRepository.createForTesting(storageFile);
    }

    // Tests saving and retrieving a measurement.
    @Test
    void testSaveAndFindAll() {
        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(1.0, "FEET");

        repository.save(entity);

        assertEquals(1, repository.findAll().size());
        assertEquals(entity.getId(), repository.findAll().get(0).getId());
    }

    // Tests retrieving a measurement by ID.
    @Test
    void testFindById() {
        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(1.0, "FEET");

        repository.save(entity);

        assertEquals(entity.getId(),
                repository.findById(entity.getId()).getId());
    }

    // Tests handling of an unknown ID.
    @Test
    void testFindByUnknownId() {
        assertNull(repository.findById("unknown-id"));
    }

    // Tests rejection of null record.
    @Test
    void testSaveNullEntity() {
        assertThrows(
                IllegalArgumentException.class,
                () -> repository.save(null)
        );
    }

    // Tests that returned lists cannot modify internal storage.
    @Test
    void testFindAllReturnsDefensiveCopy() {
        repository.save(new QuantityMeasurementEntity(1.0, "FEET"));

        List<QuantityMeasurementEntity> records = repository.findAll();
        records.clear();

        assertEquals(1, repository.findAll().size());
    }

    // Tests clearing repository records.
    @Test
    void testClear() {
        repository.save(new QuantityMeasurementEntity(1.0, "FEET"));

        repository.clear();

        assertTrue(repository.findAll().isEmpty());
    }

    // Tests persistence across repository instances.
    @Test
    void testDiskPersistence() {
        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(1.0, "FEET");

        repository.save(entity);

        QuantityMeasurementCacheRepository reloaded =
                QuantityMeasurementCacheRepository.createForTesting(storageFile);

        assertEquals(1, reloaded.findAll().size());
        assertEquals(entity.getId(), reloaded.findAll().get(0).getId());
    }

    // Tests singleton instance consistency.
    @Test
    void testSingletonInstance() {
        assertSame(
                QuantityMeasurementCacheRepository.getInstance(),
                QuantityMeasurementCacheRepository.getInstance()
        );
    }
}