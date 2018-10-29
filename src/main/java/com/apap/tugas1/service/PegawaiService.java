package com.apap.tugas1.service;
import java.util.List;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;


public interface PegawaiService {
	PegawaiModel findByNip(String NIP);
	
	PegawaiModel generateNip(PegawaiModel pegawai);
	
	PegawaiModel addPegawai(PegawaiModel pegawai);
	
	List<PegawaiModel> findByInstansiOrderByTanggalLahirAsc(InstansiModel instansi);
	
	List<PegawaiModel> getPegawaiByInstansiAndJabatan(InstansiModel instansi, JabatanModel jabatan);
	
	List<PegawaiModel> getPegawaiByInstansi(InstansiModel instansi);
	
	List<PegawaiModel> getPegawaiByProvinsiAndJabatan(long idProvinsi, JabatanModel jabatan);
	
	List<PegawaiModel> getPegawaiByProvinsi (long idProvinsi);
	
	List<PegawaiModel> getPegawaiByJabatan (JabatanModel jabatan);
	
}
	