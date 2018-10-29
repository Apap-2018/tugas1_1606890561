package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.InstansiDB;

@Service
@Transactional
public class InstansiServiceImpl implements IntansiService{
	@Autowired
	private InstansiDB instansiDb;
	
	@Override
	public InstansiModel findById(long id) {
		// TODO Auto-generated method stub
		return instansiDb.findById(id);
	}

	@Override
	public List<InstansiModel> getAllInstansi() {
		// TODO Auto-generated method stub
		return instansiDb.findAll();
	}

	@Override
	public List<InstansiModel> getInstansiFromProvinsi(ProvinsiModel provinsi) {
		// TODO Auto-generated method stub
		return instansiDb.findByProvinsi(provinsi);
	}

	@Override
	public Optional<InstansiModel> getInstansiById(long id) {
		// TODO Auto-generated method stub
		return instansiDb.getInstansiById(id);
	}

}
