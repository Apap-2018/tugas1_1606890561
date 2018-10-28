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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author ruhur
 *
 * tabel kelas model instansi
 * id (big int)= auto increment, primary key, not null
 * nama (varchar255) = not null
 * deskripsi (varchar255) = not null
 * id_provinsi(long 20) = FK id provinsi, ManytoOne
 *
 */

@Entity
@Table(name = "Instansi")
public class InstansiModel implements Serializable {
//id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

//nama_instansi
	@NotNull
	@Size(max = 255)
	@Column(name = "nama", nullable = false)
	private String namaInstansi;
	
//deskripsi_instansi
	@NotNull
	@Size(max = 255)
	@Column(name = "deskripsi", nullable = false)
	private String deskripsiInstansi;
	
//id_provinsi
	@ManyToOne()
	@JoinColumn(name = "id_provinsi", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private ProvinsiModel provinsi;
	
//onetomany
	@OneToMany(mappedBy = "instansi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<PegawaiModel> pegawai;

//setter getter
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNama() {
		return namaInstansi;
	}

	public void setNama(String namaInstansi) {
		this.namaInstansi = namaInstansi;
	}

	public String getDeskripsiInstansi() {
		return deskripsiInstansi;
	}

	public void setDeskripsiInstansi(String deskripsiInstansi) {
		this.deskripsiInstansi = deskripsiInstansi;
	}

	public ProvinsiModel getProvinsi() {
		return provinsi;
	}

	public void setProvinsi(ProvinsiModel provinsi) {
		this.provinsi = provinsi;
	}

	public List<PegawaiModel> getPegawai() {
		return pegawai;
	}

	public void setPegawai(List<PegawaiModel> pegawai) {
		this.pegawai = pegawai;
	}


	
	
}
