package com.coderscampus.a4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class EnrollmentProcessor {
	Student student = new Student();

	public static void main(String[] args) {
		Student[] course1 = new Student[100];
		Student[] course2 = new Student[100];
		Student[] course3 = new Student[100];

		int count1 = 0, count2 = 0, count3 = 0;

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/student-master-list.csv"))) {
			String line;
			boolean firstLine = true;
			while ((line = bufferedReader.readLine()) != null) {
				if (firstLine) {
					firstLine = false;
					continue;
				}
				String[] data = line.split(",");
				int studentId = Integer.parseInt(data[0].trim());
				String studentName = data[1].trim();
				String course = data[2].trim();
				int grade = Integer.parseInt(data[3].trim());

				Student student = new Student(studentId, studentName, course, grade);
				if (course.startsWith("COMPSCI")) {
					course1[count1++] = student;
				} else if (course.startsWith("APMTH")) {
					course2[count2++] = student;
				} else if (course.startsWith("STAT")) {
					course3[count3++] = student;
				}
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

		sortStudentsByCourse(course1);
		sortStudentsByCourse(course2);
		sortStudentsByCourse(course3);

		writeToFile("course1.csv", course1, count1);
		writeToFile("course2.csv", course2, count2);
		writeToFile("course3.csv", course3, count3);
	}

	private static void sortStudentsByCourse(Student[] course1) {
		Arrays.sort(course1);
	}

	private static void sortStudentsByGrade(List<Student> students, int i) {
		Student newStudent = new Student();
		int n = students.size();
		for (int i1 = 0; i1 < n - 1; i1++) {
			for (int j = 0; j < n - i1 - 1; j++) {
				if (students.get(j).getGrade() < students.get(j + 1).getGrade()) {
					Student temp = students.get(j);
					students.set(j, students.get(j + 1));
					students.set(j + 1, temp);
				}

			}
		}

		while (i < students.size() && students.get(i).grade >= newStudent.grade) {
			i++;
		}
		students.add(i, newStudent);
	}

	private static void writeToFile(String fileName, Student[] students, int count) {
		try (FileWriter Writer = new FileWriter(fileName)) {
			Writer.write("Student ID, Student Name, Course, Grade\n");
			for (int i = 0; i < count; i++) {
				Writer.write(students[i].toString() + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
