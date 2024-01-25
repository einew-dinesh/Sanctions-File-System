import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})

export class SignupComponent {

  constructor(
    private router:Router,
    private http :HttpClient
    ){}

  login(){
    this.router.navigate(["/login"]);
  }

  signupUser(f:NgForm){
    //debugger;
    // const data1 = {
    //   "userName":"Dinesh",
    //   "userEmail":"einew",
    //   "userRole":"maker",
    //   "password":"1803",
    //   "cpassword":"1803"
    // }
    const data = f.form.value;
    
    //console.log(data);
    const url="http://localhost:8080/user/signup"

    this.http.post<any>(url,data).subscribe({
      next:(res:any)=>{
        console.log(res);
        this.router.navigate(["/login"]);
      },
      error:(err:any)=>{
        console.log(err);
      }
    })
  }

}
