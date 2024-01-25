import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router ,ActivatedRoute} from '@angular/router';



@Component({
  selector: 'app-maker-upload',
  templateUrl: './maker-upload.component.html',
  styleUrls: ['./maker-upload.component.css']
})
export class MakerUploadComponent implements OnInit{
  userId: any;
  selectedFile!: File;

  constructor(private router:Router,private http :HttpClient,private route: ActivatedRoute){}

  ngOnInit(){

    this.route.queryParamMap.subscribe(params =>{
      this.userId = params.get('userId');

      // Now you have the userId, you can use it in this component
      console.log("uplaoad "+this.userId);
    })
  }

  onFileSelected(event:any) {
    console.log(event);
    //debugger;
    this.selectedFile = event.target.files[0];
    console.log(this.selectedFile);
  }

  uploadFile(form: NgForm){
    console.log(form.form.value);
    //const data = new FormData();
    //const data = f.form.value;
    const formData = new FormData();
    formData.append('fileName', form.value.FileName);
    formData.append('fileType', form.value.FileType);
    formData.append('csv', this.selectedFile);
    formData.append('comment',form.value.comment);

    const url = "http://localhost:8080/file/upload-file/"+this.userId;

    this.http.post<any>(url,formData).subscribe({
      next:(res:any)=>{
        console.log(res);
      },
      error:(err:any)=>{
        console.log(err);
      }
    })
  }

}
