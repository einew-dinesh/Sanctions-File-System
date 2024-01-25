import { Component, OnInit } from '@angular/core';
import { Router ,ActivatedRoute} from '@angular/router';
import { UserService } from '../../user.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-checker-action-file',
  templateUrl: './checker-action-file.component.html',
  styleUrls: ['./checker-action-file.component.css']
})
export class CheckerActionFileComponent implements OnInit{

  tableHeader:any;
  tableData:any;
  fileId:any;
  constructor(private router:Router,
    private http :HttpClient,
    private route: ActivatedRoute){}

  ngOnInit(){

    this.route.queryParamMap.subscribe(params =>{
      this.fileId = params.get('fileId');

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
        console.log(res[0].file.fileName)
      },
      error:(err:any)=>{
        console.log(err);
      }
    })
  }

  viewFile(row:any, status:String){
    console.log(row.fileRecordId)
    const url ="http://localhost:8080/file-record/update-status/"+row.fileRecordId+"/"+status;
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
}
