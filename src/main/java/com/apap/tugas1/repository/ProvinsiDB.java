package com.apap.tugas1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;

@Repository
public interface ProvinsiDB extends JpaRepository<ProvinsiModel, Long> {
	List<InstansiModel> findInstansiById(long provinsi);
	
	List<ProvinsiModel> findAll();
	
	Optional<ProvinsiModel> getProvinsiById(long id);

}
