import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-edit-record',
  templateUrl: './edit-record.component.html',
  styleUrls: ['./edit-record.component.css']
})
export class EditRecordComponent {
  fileRecordId:any;
  fileId:any;
  userId:any;

  constructor(private router:Router,
    private http :HttpClient,
    private route: ActivatedRoute){

  }

  ngOnInit(){

    this.route.queryParamMap.subscribe(params =>{
      this.fileRecordId = params.get('fileRecordId');
      this.fileId = params.get('fileId');
      this.userId= params.get('userId');

      // Now you have the userId, you can use it in this component
      console.log("file "+this.fileRecordId);
    })
  }

  editRecord(f:NgForm){
    console.log(f.form.value);
    const data = f.form.value;

    const url = "http://localhost:8080/file-record/update-record/"+this.fileRecordId;

    this.http.post<any>(url,data).subscribe({
      next:(res:any)=>{
        console.log(res);
      
            this.router.navigate(["maker/history/file"],{ queryParams: { userId: this.userId, fileId:this.fileId } });
          
        
      },
      error:(err:any)=>{
        console.log(err);
      }
    })
  }

}
