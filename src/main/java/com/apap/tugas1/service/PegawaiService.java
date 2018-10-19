package com.apap.tugas1.service;
import java.util.List;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;


public interface PegawaiService {
	PegawaiModel findByNip(String NIP);
	
	PegawaiModel generateNip(PegawaiModel pegawai);
	
	PegawaiModel addPegawai(PegawaiModel pegawai);
	
	List<PegawaiModel> findByInstansiOrderByTanggalLahirAsc(InstansiModel instansi);
	
}
	