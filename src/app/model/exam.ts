export class Exam {
    grade: number;
    name: string;
    subject_name: string;
    surname: string;

    constructor(grade, name, subject_name, surname) {
        this.grade = grade;
        this.name = name;
        this.subject_name = subject_name;
        this.surname = surname;
    }
}