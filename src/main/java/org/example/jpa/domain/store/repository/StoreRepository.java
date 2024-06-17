package org.example.jpa.domain.store.repository;

import org.example.jpa.domain.store.repository.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
