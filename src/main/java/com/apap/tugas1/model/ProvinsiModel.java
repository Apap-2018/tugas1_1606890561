package com.apap.tugas1.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author ruhur
 * 
 * table kelas model provinsi
 * id(bigint 20) = auto incerement, not null, PK
 * nama(varchar 255) = not null
 * presentasi_tunjangan(double) = not null  
 *
 */

@Entity
@Table(name = "provinsi")
public class ProvinsiModel implements Serializable{
	
//id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
//nama_provinsi
	@NotNull
	@Size(max = 255)
	@Column(name = "nama", nullable = false)
	private String nama;

//presentase_tunjangan
	@NotNull
	@Size(max = 255)
	@Column(name = "presentase_tunjangan", nullable = false)
	private double presentaseTunjangan;
	
//onetomany
	@JsonIgnore
	@OneToMany(mappedBy = "provinsi", cascade = CascadeType.PERSIST)
	private List<InstansiModel> instansi;

	
//setter getter
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public double getPresentaseTunjangan() {
		return presentaseTunjangan;
	}

	public void setPresentaseTunjangan(double presentaseTunjangan) {
		this.presentaseTunjangan = presentaseTunjangan;
	}

	public List<InstansiModel> getInstansi() {
		return instansi;
	}

	public void setInstansi(List<InstansiModel> instansi) {
		this.instansi = instansi;
	}
	
	

}
