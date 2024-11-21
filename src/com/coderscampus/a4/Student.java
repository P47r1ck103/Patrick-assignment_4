package com.coderscampus.a4;

public class Student implements Comparable<Student> {
	int studentId;
	String studentName;
	String course;
	int grade;

	public Student() {

	}

	public Student(int studentId, String studentName, String course, int grade) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.course = course;
		this.grade = grade;
	}

	public String sortStudentByCourse() {
		return "";
	}

	@Override
	public String toString() {
		return studentId + "," + studentName + "," + course + "," + grade;

	}

	public Integer getStudentId() {

		return studentId;
	}

	public int getGrade() {

		return grade;
	}

	public String getStudentName() {

		return studentName;
	}

	public String getCourse() {

		return course;
	}

	@Override
	public int compareTo(Student that) {
		if (this.grade > that.grade) {
			return -1;
		} else if (this.course.equals(that.course)) {
			return this.course.compareTo(that.course);
		} else {
			return 1;
		}
	}
}
