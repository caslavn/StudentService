import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from "./auth/auth.guard";
import { AdminGuard } from "./auth/admin.guard";
import { LoginComponent } from "./login/login.component";
import { AdminComponent } from "./student-start/admin/admin.component";
import { ExamFailedComponent } from "./student-start/exam-failed/exam-failed.component";
import { StudentExamComponent } from "./student-start/student-exam/student-exam.component";
import { StudentsComponent } from "./student-start/students/students.component";

const routes: Routes= [

  { path: '', redirectTo: 'login', pathMatch: 'full', canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'api/students', component: StudentsComponent },
  { path: 'api/exam', component: StudentExamComponent },
  { path: 'api/exam/students-who-failed', component: ExamFailedComponent },
  { path: 'api/student/admin', component: AdminComponent,  canActivate: [AdminGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]

})
export class AppRoutingModule { }