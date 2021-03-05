import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Exam } from 'src/app/model/exam';
import { AuthService } from 'src/app/services/auth.service';
import { Students } from '../../model/students';

@Component({
  selector: 'app-student-exam',
  templateUrl: './student-exam.component.html',
  styleUrls: ['./student-exam.component.scss']
})
export class StudentExamComponent implements OnInit {

  students: Students[];
  exams: Exam [] = []; 
  constructor(private router: Router, private aS: AuthService) { }

  ngOnInit(): void {
    this.getExam();
  }

  getExam(){
    this.aS.getExam().subscribe((response) =>{ 
      this.exams = response;
   })
  }
}
