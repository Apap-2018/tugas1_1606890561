package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import com.apap.tugas1.model.JabatanModel;

public interface JabatanService {
	
	JabatanModel addJabatan(JabatanModel jabatan);

	List<JabatanModel> getAllJabatan();

	JabatanModel findById(long id);
	
	void deleteById(long id);
	
	
}
