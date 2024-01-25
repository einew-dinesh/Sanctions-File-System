import { Component, OnInit } from '@angular/core';
import {ActivatedRoute,Router} from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-checker',
  templateUrl: './checker.component.html',
  styleUrls: ['./checker.component.css']
})
export class CheckerComponent implements OnInit{
  dash : any;
  user : any;
  tableHeader:any;
  makerData:any;

  constructor(private route: ActivatedRoute,
    private router:Router,
    private http :HttpClient){}

  ngOnInit(){
    
    this.route.queryParamMap.subscribe(params => {
      const userId = params.get('userId');

      // Now you have the userId, you can use it in this component
      console.log("from checker"+userId);
      const url1 = "http://localhost:8080/file/"+userId+"/checker-dashboard";
      const url2 = "http://localhost:8080/user/userId/"+userId;
      const url3 = "http://localhost:8080/file/"+userId+"/maker-table";
         
      this.http.get<any>(url1).subscribe({
        next:(res:any)=>{
          console.log(res);
          this.dash = res;
        },
        error:(err:any)=>{
          console.log(err);
        }
      })

      this.http.get<any>(url2).subscribe({
        next:(res:any)=>{
          console.log(res);
          this.user = res;
        },
        error:(err:any)=>{
          console.log(err);
        }
      })

      this.http.get<any>(url3).subscribe({
        next:(res:any)=>{
          console.log(res);
          this.makerData = res;
        },
        error:(err:any)=>{
          console.log(err);
        }
      })


      this.tableHeader=[{
        "header":"Maker Name",
        "field":"makerName"
      },{
        "header":"Total Uploaded Records",
        "field":"totalUploadedRecords"
      },{
        "header":"Total Approved Records",
        "field":"totalApprovedRecords"
      },{
        "header":"Total Pending Records",
        "field":"totalPendingRecords"
      }]



    })
  }

}
