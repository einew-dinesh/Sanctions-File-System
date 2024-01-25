import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(
    private router:Router,
    private http :HttpClient,
    //private userService: UserService
    ){

  }

  signup(){
    this.router.navigate(["/signup"]);
  }

  loginUser(f: NgForm){
    console.log(f.form.value);
    const data = f.form.value;

    this.http.post<any>("http://localhost:8080/user/login",data).subscribe({
      next:(res:any)=>{
        console.log(res);
        if(res.message=='Login Success'){
          
          if(res.userRole=='maker'){
            //this.userService.setUserId(res.userId);
            this.router.navigate(["/maker"],{ queryParams: { userId: res.userId } });
          }else if(res.userRole=='checker'){
            this.router.navigate(["/checker"],{ queryParams: { userId: res.userId } });
          }else{
            console.log("Role is not assigned to you")
          }
        }
      },
      error:(err:any)=>{
        console.log(err);
      }
    })
  }

}
