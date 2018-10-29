package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;

public interface ProvinsiService {
	
	List<InstansiModel> findInstansiById(long idProvinsi);
	
	List<ProvinsiModel> findAllProvinsi();
	
	Optional<ProvinsiModel> getProvinsiById(long id);
	
}
