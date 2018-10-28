package com.apap.tugas1.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
 * tabel kelas model pegawai
 * id (big int)= auto increment, primary key, not null
 * nip (Varchar255) = notnull, unique
 * tempat_lahir(varchar255) = notnull
 * tempat_lahir(date) = notnull
 * tahun_masuk(varchar255) = notnull
 * id_instansi(long20) = FK ke INSTANSI id, ManytoOne
 *
 */

@Entity
@Table(name = "pegawai")
public class PegawaiModel implements Serializable {
//id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
//nip
	@NotNull
	@Size(max=255)
	@Column(name = "nip", nullable = false, unique = true)
	private String nip;
	
//name
	@NotNull
	@Size(max=255)
	@Column(name="nama", nullable=false)
	private String nama;
	
//tempat_lahir
	@NotNull
	@Size(max = 255)
	@Column(name = "tempat_lahir", nullable = false)
	private String tempatLahir;
	
//tanggal_lahir
	@NotNull
	@Column(name = "tanggal_lahir", nullable = false)
	private Date tanggalLahir;
	
//tahun_masuk
	@NotNull
	@Size(max = 255)
	@Column(name = "tahun_masuk", nullable = false)
	private String tahunMasuk;
	
//id_instansi
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_instansi", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private InstansiModel instansi;

//many to many
	@ManyToMany
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnore
	@JoinTable(name = "jabatan_pegawai", joinColumns ={@JoinColumn(name = "idPegawai")}, 
	inverseJoinColumns= {@JoinColumn(name="idJabatan")})
	private List<JabatanModel> jabatan;
	
//buat gaji
	@Column(name = "gaji", nullable = false)
	private double gaji;
	
//setter getter
	public long getGaji() {
		double gaji = 0;
		double tunjangan = instansi.getProvinsi().getPresentaseTunjangan() * 0.01;
		for(int i =0; i< jabatan.size() ; i++) {
			if(jabatan.get(i).getGajiPokok() > gaji) {
				gaji = jabatan.get(i).getGajiPokok();
			}
		}
		
		return (long)(gaji + (tunjangan * gaji));
		
	}
	
	public String getNama() {
		return nama;
	}

	public void setName(String nama) {
		this.nama = nama;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getTempatLahir() {
		return tempatLahir;
	}

	public void setTempatLahir(String tempatLahir) {
		this.tempatLahir = tempatLahir;
	}

	public Date getTanggalLahir() {
		return tanggalLahir;
	}

	public void setTanggalLahir(Date tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}

	public String getTahunMasuk() {
		return tahunMasuk;
	}

	public void setTahunMasuk(String tahunMasuk) {
		this.tahunMasuk = tahunMasuk;
	}

	public InstansiModel getInstansi() {
		return instansi;
	}

	public void setInstansi(InstansiModel instansi) {
		this.instansi = instansi;
	}

	public long getId() {
		return id;
	}

	public void setId(long idPegawai) {
		this.id = idPegawai;
	}

	public List<JabatanModel> getJabatan() {
		return jabatan;
	}

	public void setJabatan(List<JabatanModel> jabatan) {
		this.jabatan = jabatan;
	}
	
	

	
}
