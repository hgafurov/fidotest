package uz.hg.fidotest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "docs")
public class Doc extends BaseEntity {
	
	@Column(name = "reg_no")
	private String regNo;
	
	@Column(name = "reg_date")
	private Date regDate;
	
	@Column(name = "out_doc_no")
	private String outDocNo;
	
	@Column(name = "out_doc_date")
	private Date outDocDate;
	
	@Column(name = "forma_dostav")
	private String formaDostav;
	
	@Column(name = "correspondent")
	private String correspondent;
	
	@Column(name = "tema")
	private String tema;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "srok_ispol")
	private Date srokIspol;
	
	@Column(name = "access")
	private Boolean access;
	
	@Column(name = "control")
	private Boolean control;
	
	@Column(name = "doc_url")
	private String docUrl;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Doc() {		
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getOutDocNo() {
		return outDocNo;
	}

	public void setOutDocNo(String outDocNo) {
		this.outDocNo = outDocNo;
	}

	public Date getOutDocDate() {
		return outDocDate;
	}

	public void setOutDocDate(Date outDocDate) {
		this.outDocDate = outDocDate;
	}

	public String getFormaDostav() {
		return formaDostav;
	}

	public void setFormaDostav(String formaDostav) {
		this.formaDostav = formaDostav;
	}

	public String getCorrespondent() {
		return correspondent;
	}

	public void setCorrespondent(String correspondent) {
		this.correspondent = correspondent;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getSrokIspol() {
		return srokIspol;
	}

	public void setSrokIspol(Date srokIspol) {
		this.srokIspol = srokIspol;
	}

	public Boolean getAccess() {
		return access;
	}

	public void setAccess(Boolean access) {
		this.access = access;
	}

	public Boolean getControl() {
		return control;
	}

	public void setControl(Boolean control) {
		this.control = control;
	}

	public String getDocUrl() {
		return docUrl;
	}

	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
