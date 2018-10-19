package com.apap.tugas1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author ruhur
 *
 * table kelas model jabatan
 * id(long 20) = auto increment, not null, PK
 * nama (varchar 255) = not null
 * deskripsi (varchar 255) = not null
 * gaji_pokok (double) 	= not null
 * 
 *
 */

@Entity
@Table(name = "jabatan")
public class JabatanModel implements Serializable {
	
// id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

// nama
	@NotNull
	@Size(max = 255)
	@Column(name = "nama", nullable = false)
	private String nama;
	
// deskripsi
	@NotNull
	@Size(max = 255)
	@Column(name = "deskripsi", nullable = false)
	private String deskripsi;
	
// gaji_pokok
	@NotNull
	@Column(name = "gaji_pokok", nullable = false)
	private double gajiPokok;
	
//many to many
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "jabatan_pegawai", joinColumns ={@JoinColumn(name = "idJabatan")}, 
	inverseJoinColumns= {@JoinColumn(name="idPegawai")})
	private List<PegawaiModel> jabatan = new ArrayList<PegawaiModel>();

//setter getter

	public List<PegawaiModel> getJabatan() {
		return jabatan;
	}

	public void setJabatan(List<PegawaiModel> jabatan) {
		this.jabatan = jabatan;
	}

	// setter getter
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String namaJabatan) {
		this.nama = namaJabatan;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsiJabatan) {
		this.deskripsi = deskripsiJabatan;
	}

	public double getGajiPokok() {
		return gajiPokok;
	}

	public void setGajiPokok(double gajiPokok) {
		this.gajiPokok = gajiPokok;
	}
	
	
	
}
