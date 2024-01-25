import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-maker-history-file',
  templateUrl: './maker-history-file.component.html',
  styleUrls: ['./maker-history-file.component.css']
})
export class MakerHistoryFileComponent implements OnInit{
  tableHeader:any;
  tableData:any;
  fileId:any;
  userId:any;
  constructor(private router:Router,
    private http :HttpClient,
    private route: ActivatedRoute){

  }

  ngOnInit(){

    this.route.queryParamMap.subscribe(params =>{
      this.fileId = params.get('fileId');
      this.userId= params.get('userId');

      // Now you have the userId, you can use it in this component
      console.log("file "+this.fileId);
    })

    this.tableHeader=[{
      "header":"Person Name",
      "field":"name"
    },{
      "header":"PAN",
      "field":"pan"
    },{
      "header":"Email",
      "field":"email"
    },{
      "header":"Updated On",
      "field":"updatedOn"
    },{
      "header":"Status",
      "field":"status"
    }]

    
    const url ="http://localhost:8080/file-record/"+this.fileId;
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

  deleteRecord(row:any){
    const url="http://localhost:8080/file-record/delete-record/"+row.fileRecordId;
    this.http.get<any>(url).subscribe({
      next:(res:any)=>{
        console.log(res);
      },
      error:(err:any)=>{
        console.log(err);
      }
    })

    this.reloadPage();


  }

  reloadPage(){
    window.location.reload()
  }

  editFileRecord(fileRecordId:any){
    console.log(fileRecordId)
    this.router.navigate(["edit"],{relativeTo: this.route, queryParams: {fileRecordId: fileRecordId, fileId:this.fileId, userId:this.userId} })
  }
  

}
