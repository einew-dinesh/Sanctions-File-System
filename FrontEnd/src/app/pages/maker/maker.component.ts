import { Component, OnInit } from '@angular/core';
import {ActivatedRoute,Router} from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../../user.service';


@Component({
  selector: 'app-maker',
  templateUrl: './maker.component.html',
  styleUrls: ['./maker.component.css']
})
export class MakerComponent implements OnInit{
  userIdService: any;
  result: any;
  user:any;
  constructor(private route: ActivatedRoute,
    private router:Router,
    private http :HttpClient,
    private userService: UserService
    ) {
      this.userIdService = this.userService.getUserId();
    }

  ngOnInit() {
    this.route.queryParamMap.subscribe(params => {
      const userId = params.get('userId');

      // Now you have the userId, you can use it in this component
      console.log("from make"+this.userIdService);
      const url1 = "http://localhost:8080/file/"+userId+"/maker-dashboard";
      const url2 = "http://localhost:8080/user/userId/"+userId;
      
      this.http.get<any>(url1).subscribe({
        next:(res:any)=>{
          console.log(res);
          this.result = res;
          console.log(this.result);

        },
        error:(err:any)=>{
          console.log(err);
        }
      })

      this.http.get<any>(url2).subscribe({
        next:(res:any)=>{
          console.log(res);
          this.user = res;
          console.log(this.user);

        },
        error:(err:any)=>{
          console.log(err);
        }
      })

    });
  }

}
