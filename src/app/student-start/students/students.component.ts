import { Component, OnInit } from '@angular/core';
import { Students } from '../../model/students';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import jwtDecode from 'jwt-decode';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.scss']
})
export class StudentsComponent implements OnInit {
  students: Students[] = [];
  searchValue: string;
  closeResult: string;
  formObj: any;
  selected: boolean;
  submited = false;


  constructor(public http: HttpClient, public auth: AuthService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getAllStudents();
}


getAllStudents(){
  this.auth.getAllStudents().subscribe((response) =>{ 
    this.students = response;
 })
}
deleteStudent(student) {

     if (confirm('Are you sure?')) {
  this.auth.deleteStudent(student).subscribe( 
    response =>{
      this.submited = true;
      this.getAllStudents();
      console.log(response)
    });

}
}
onchange(){
console.log(this.students)
}

insertStudent(formObj){
  console.log(formObj)
  this.auth.insertStudent(formObj).subscribe((response) => {
    this.getAllStudents();
  })
}
open(content) {
  this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
    this.closeResult = `Closed with: ${result}`;
  }, (reason) => {
    this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
  });
}

private getDismissReason(reason: any): string {
  if (reason === ModalDismissReasons.ESC) {
    return 'by pressing ESC';
  } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
    return 'by clicking on a backdrop';
  } else {
    return `with: ${reason}`;
  }
}

}