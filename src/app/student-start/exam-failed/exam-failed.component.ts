import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Exam } from 'src/app/model/exam';
import { Students } from 'src/app/model/students';
import { Subject } from 'src/app/model/subject';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-exam-failed',
  templateUrl: './exam-failed.component.html',
  styleUrls: ['./exam-failed.component.scss']
})
export class ExamFailedComponent implements OnInit {

  subjects: Subject[] = [];
  students: Students[] = [];
  exams: Exam[] = [];

  constructor(private router: Router, private aS: AuthService) { }

  ngOnInit(): void {
    this.aS.getSubject().subscribe((response) =>{ 
      this.subjects = response;
   })
   this.aS.getStudentsWhoFailed().subscribe((response) => {
    this.exams = response;
})
}

}
