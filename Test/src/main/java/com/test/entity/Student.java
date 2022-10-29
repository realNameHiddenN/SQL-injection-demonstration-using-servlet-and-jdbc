package com.test.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stdtab")
public class Student {
	@Id
	private int stdId;
	private String stdName;
	private Double stdMarks;
	
	@ManyToMany
	@JoinTable(name = "stdcrstab",
			joinColumns = @JoinColumn(name="sidFk"),
			inverseJoinColumns = @JoinColumn(name="cidFk")
	)
	private Set<Course> cobs = new HashSet<>();
}
