package com.library.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "library")
public class Library {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int issue_id;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "book_id")
	private int bookId;
	
	@Column(name = "issue_date")
	private Date issue_date;
	
	@Column(name = "return_date")
	private Date return_date;
	
	@Column(name = "fine")
	private int fine;

	public int getIssue_id() {
		return issue_id;
	}

	public void setIssue_id(int issue_id) {
		this.issue_id = issue_id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public Date getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}

	public Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}

	public int getFine() {
		return fine;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}
}
