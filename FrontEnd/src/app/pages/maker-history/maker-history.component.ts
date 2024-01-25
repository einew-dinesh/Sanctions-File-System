import { Component, OnInit } from '@angular/core';
import { Router ,ActivatedRoute} from '@angular/router';
import { UserService } from '../../user.service';
import { HttpClient } from '@angular/common/http';

class MakerHistory{
  fileName !: string;
  fileType !: string;
  comment ?: string;
  createdOn!: string;
  updatedOn?:string;
  fileStatus!:string;
}

@Component({
  selector: 'app-maker-history',
  templateUrl: './maker-history.component.html',
  styleUrls: ['./maker-history.component.css']
})
export class MakerHistoryComponent implements OnInit{
  userId: any;
  tableHeader:any;
  tableData:any;
  constructor(private router:Router,
    private userService:UserService,
    private http :HttpClient,
    private route: ActivatedRoute
    ){
      //this.userId = this.userService.getUserId();
  }

  ngOnInit(){

    this.route.queryParamMap.subscribe(params =>{
      this.userId = params.get('userId');

      // Now you have the userId, you can use it in this component
      console.log("history "+this.userId);
    })

    this.tableHeader=[{
      "header":"File Name",
      "field":"fileName"
    },{
      "header":"File Type",
      "field":"fileType"
    },{
      "header":"Comment",
      "field":"comment"
    },{
      "header":"Uploaded On",
      "field":"uploadedOn"
    },{
      "header":"Updated On",
      "field":"updatedOn"
    },{
      "header":"Overall Status",
      "field":"fileStatus"
    }]
    const url ="http://localhost:8080/file/files-of-user/"+this.userId;
    this.http.get<any>(url).subscribe({
      next:(res:any)=>{
        console.log(res);
        this.tableData=res;
      },
      error:(err:any)=>{
        console.log(err);
      }
    })

  }


  

  viewFile(row:any){
    console.log(row)
    this.router.navigate(["file"],{relativeTo: this.route, queryParams: {fileId: row.fileId , userId:this.userId} })
  }

}
