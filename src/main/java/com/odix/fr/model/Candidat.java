package com.odix.fr.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;
	
@Data
@Entity
@DiscriminatorValue(value="ROLE_CANDIDAT")
public class Candidat extends Utilisateur implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1004341819482868284L;

	/**Les champs du candidat, hérités de la classe utilisateur :
	 * 
	 * id
	 * identite
	 * telephone
	 * email
	 * poste_occupe
	 * description_detaillee
	 * urlPhoto
	 * entreprise : @ManyToOne
	 * 
	 ***************
	 */


	// Pour regrouper les candidats par IdUtilisateur : qui a inséré ce candidat (pour le moment c'est l'Administrateur)
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Utilisateur utilisateur;
	
	@Column
	private String idLinkedin;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Etat etatCandidat;
	
	@ManyToMany
	@JoinTable(name = "candidat_technologie",
	joinColumns = { @JoinColumn(name = "id_candidat") },
	inverseJoinColumns = { @JoinColumn(name = "id_technologie") })
	private List<Technologie> listeTechnologies;
	
	@ManyToMany
	@JoinTable(name = "candidat_certification",
	joinColumns = { @JoinColumn(name = "id_candidat") },
	inverseJoinColumns = { @JoinColumn(name = "id_certification") })
	private List<Certification> listeCertifications;

	
	public Candidat() {
		super();
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public String getIdLinkedin() {
		return idLinkedin;
	}

	public void setIdLinkedin(String idLinkedin) {
		this.idLinkedin = idLinkedin;
	}
	
	public Etat getEtatCandidat() {
		return etatCandidat;
	}

	public void setEtatCandidat(Etat etatCandidat) {
		this.etatCandidat = etatCandidat;
	}

	public List<Technologie> getListeTechnologies() {
		return listeTechnologies;
	}

	public void setListeTechnologies(List<Technologie> listeTechnologies) {
		this.listeTechnologies = listeTechnologies;
	}

	public List<Certification> getListeCertifications() {
		return listeCertifications;
	}

	public void setListeCertifications(List<Certification> listeCertifications) {
		this.listeCertifications = listeCertifications;
	}
}
