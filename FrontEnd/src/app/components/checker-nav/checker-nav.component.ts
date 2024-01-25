import { Component, OnInit } from '@angular/core';
import {ActivatedRoute,Router} from '@angular/router';

@Component({
  selector: 'app-checker-nav',
  templateUrl: './checker-nav.component.html',
  styleUrls: ['./checker-nav.component.css']
})
export class CheckerNavComponent {
  userId:any;
  constructor(private route: ActivatedRoute,
    private router:Router
    ){

  }
  ngOnInit() {
    this.route.queryParamMap.subscribe(params =>{
      this.userId = params.get('userId');

      // Now you have the userId, you can use it in this component
      console.log("Checker navbar "+this.userId);
    })
  }

  logout(){
    this.router.navigate(["/login"]);
  }

}
