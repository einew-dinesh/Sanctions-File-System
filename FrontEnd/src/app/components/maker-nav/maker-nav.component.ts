import { Component, OnInit } from '@angular/core';
import {ActivatedRoute,Router} from '@angular/router';

@Component({
  selector: 'app-maker-nav',
  templateUrl: './maker-nav.component.html',
  styleUrls: ['./maker-nav.component.css']
})
export class MakerNavComponent implements OnInit{
  userId:any;
  constructor(private route: ActivatedRoute,
    private router:Router
    ){

  }
  ngOnInit() {
    this.route.queryParamMap.subscribe(params =>{
      this.userId = params.get('userId');

      // Now you have the userId, you can use it in this component
      console.log("navbar "+this.userId);
    })
  }

  logout(){
    this.router.navigate(["/login"]);
  }

}
