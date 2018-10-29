package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;

public interface IntansiService {
	
	InstansiModel findById(long id);
	
	List<InstansiModel> getAllInstansi();
	
	List<InstansiModel> getInstansiFromProvinsi(ProvinsiModel provinsi);
	
	Optional<InstansiModel> getInstansiById(long id);
}
