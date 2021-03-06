package com.apap.tugas1.service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.PegawaiDB;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {

	@Autowired	
	private PegawaiDB pegawaiDb; 
	
	@Override
	public PegawaiModel findByNip(String NIP) {
		// TODO Auto-generated method stub
		return pegawaiDb.findByNip(NIP);
	}

	public PegawaiModel generateNip(PegawaiModel pegawai) {
		// TODO Auto-generated method stub
		long idInstansi = pegawai.getInstansi().getId();
		
		Date date = pegawai.getTanggalLahir();
		DateFormat df = new SimpleDateFormat("dd/MM/yy");  
		System.out.println(df);
		String dateText = df.format(date);  
		dateText.replaceAll("/", "");
		String yearStart = pegawai.getTahunMasuk(); 
		List<PegawaiModel> yangSama = pegawaiDb.findByInstansiAndTanggalLahirAndTahunMasuk(idInstansi, date, yearStart);
		int lastTwoDigit = yangSama.size()+1;
		String angkaAkhir="";
		if (lastTwoDigit <10) {
			angkaAkhir = "0" + lastTwoDigit; 
		} else {
			angkaAkhir = ""+ lastTwoDigit;
		}
		pegawai.setNip(idInstansi + dateText+yearStart+ angkaAkhir);
		return pegawai;
		
	}

	@Override
	public PegawaiModel addPegawai(PegawaiModel pegawai) {
		// TODO Auto-generated method stub
		return pegawaiDb.save(generateNip(pegawai));
	}

	@Override
	public List<PegawaiModel> findByInstansiOrderByTanggalLahirAsc(InstansiModel instansi) {
		// TODO Auto-generated method stub
		return pegawaiDb.findByInstansiOrderByTanggalLahirAsc(instansi);
	}
	
	@Override
	public List<PegawaiModel> getPegawaiByInstansi(InstansiModel instansi) {
		return pegawaiDb.findById(instansi);
	}
	
	@Override 
	public List<PegawaiModel> getPegawaiByInstansiAndJabatan(InstansiModel instansi, JabatanModel jabatan) {
		List<PegawaiModel> hasil = new ArrayList<>();
		
		for(PegawaiModel pegawai : pegawaiDb.findById(instansi)) {
			if (pegawai.getJabatan().contains(jabatan)) {
				hasil.add(pegawai);
			}
		}
		
		return hasil;
	}
	
	@Override
	public List<PegawaiModel> getPegawaiByJabatan(JabatanModel jabatan){
		return pegawaiDb.findByJabatan(jabatan);
	}

	@Override
	public List<PegawaiModel> getPegawaiByProvinsiAndJabatan(long idProvinsi, JabatanModel jabatan) {
		// TODO Auto-generated method stub
		List<PegawaiModel> hasil = new ArrayList<>();
		
		for(PegawaiModel pegawai : this.getPegawaiByProvinsi(idProvinsi)) {
			if (pegawai.getJabatan().contains(jabatan)) {
				hasil.add(pegawai);
			}
		}
		
		return hasil;
	}

	@Override
	public List<PegawaiModel> getPegawaiByProvinsi(long idProvinsi) {
		// TODO Auto-generated method stub
		List<PegawaiModel> hasil = new ArrayList<>();
		
		for(PegawaiModel pegawai : pegawaiDb.findAll()) {
			if (pegawai.getInstansi().getProvinsi().getId() == idProvinsi) {
				hasil.add(pegawai);
			}
		}
		
		return hasil;
	}

	@Override
	public PegawaiModel update(String nip, PegawaiModel pegawai) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
