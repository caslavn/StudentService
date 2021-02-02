import { Component, OnInit } from '@angular/core';
import { Students } from './students';
import { StudentsService } from './students.service';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.scss']
})
export class StudentsComponent implements OnInit {
  Students: Students[];

  constructor(public ss: StudentsService) { }

  ngOnInit(): void {
    this.ss.getStudents().subscribe((response) => {
      this.Students = response;
    })
  }

  getStudents(){
    let resp = this.ss.getStudents();
    resp.subscribe(data=>this.Students=data);
  }

}
