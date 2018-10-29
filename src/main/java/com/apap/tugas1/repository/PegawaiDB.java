package com.apap.tugas1.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;

@Repository
public interface PegawaiDB extends JpaRepository<PegawaiModel, Long>{
	PegawaiModel findByNip(String NIP);

	List<PegawaiModel> findByInstansiAndTanggalLahirAndTahunMasuk(long id, Date tanggalLahir, String tahunMasuk);	
	
	List<PegawaiModel> findByInstansiOrderByTanggalLahirAsc(InstansiModel instansi);
	
	List<PegawaiModel> findById(InstansiModel instansi);
	
	List<PegawaiModel> findByJabatan(JabatanModel jabatan);
	
}
