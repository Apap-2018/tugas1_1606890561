package com.apap.tugas1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas1.model.JabatanModel;

@Repository
public interface JabatanDB extends JpaRepository<JabatanModel, Long> {
	JabatanModel findById(long id);
	
	Optional<JabatanModel> getJabatanById(long id);
	
}
