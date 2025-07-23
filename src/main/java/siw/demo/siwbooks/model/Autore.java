package siw.demo.siwbooks.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"nome", "cognome"}))
public class Autore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String cognome;
	@Column(name = "data_nascita")
	private LocalDate dataNascita;
	@Column(nullable = true, name = "data_morte")
	private LocalDate dataMorte;
	private String nazionalita;
	@Column(name = "portrait")
	private String urlImage;
	
	
	@ManyToMany(mappedBy = "autori")
	private List<Libro> libri;
	
	public Autore() {}
	
	public Long getId() {
		return this.id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	public LocalDate getDataMorte() {
		return dataMorte;
	}

	public void setDataMorte(LocalDate dataMorte) {
		this.dataMorte = dataMorte;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cognome, dataMorte, dataNascita, id, getLibri(), nazionalita, nome, urlImage);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autore other = (Autore) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(dataMorte, other.dataMorte)
				&& Objects.equals(dataNascita, other.dataNascita) && Objects.equals(id, other.id)
				&& Objects.equals(getLibri(), other.getLibri()) && Objects.equals(nazionalita, other.nazionalita)
				&& Objects.equals(nome, other.nome) && Objects.equals(urlImage, other.urlImage);
	}

	public List<Libro> getLibri() {
		return libri;
	}

	public void setLibri(List<Libro> libri) {
		this.libri = libri;
	}
	
	
	
	
}
