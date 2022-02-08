package edu.school21.cinema.models;

import java.sql.Date;
import java.sql.Time;

public class Log {
	private Long id;
	private String ip;
	private String email;
	private Date date;
	private Time time;

	public Log(){}

	public Log(Long log_id, String ip, String email, Date date, Time time) {
		this.id = log_id;
		this.ip = ip;
		this.email = email;
		this.date = date;
		this.time = time;
	}

	@Override
	public String toString() {
		return "Log{" +
				"id=" + id +
				", ip='" + ip + '\'' +
				", email='" + email + '\'' +
				", date='" + date + '\'' +
				", time='" + time + '\'' +
				'}';
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Long getId() {
		return id;
	}

	public String getIp() {
		return ip;
	}

	public String getEmail() {
		return email;
	}

	public Date getDate() {
		return date;
	}
	public Time getTime() {
		return time;
	}
}
