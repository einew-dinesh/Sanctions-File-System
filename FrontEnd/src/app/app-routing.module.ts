import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MakerComponent } from './pages/maker/maker.component';
import { LoginComponent } from './components/login/login.component';
import { MakerUploadComponent } from './pages/maker-upload/maker-upload.component';
import { MakerHistoryComponent } from './pages/maker-history/maker-history.component';
import { MakerNavComponent } from './components/maker-nav/maker-nav.component';
import { CheckerNavComponent } from './components/checker-nav/checker-nav.component';
import { MakerHistoryFileComponent } from './pages/maker-history/maker-history-file/maker-history-file.component';
import { CheckerComponent } from './pages/checker/checker.component';
import { CheckerActionComponent } from './pages/checker-action/checker-action.component';
import { SignupComponent } from './components/signup/signup.component';
import { CheckerActionFileComponent } from './pages/checker-action-file/checker-action-file.component';
import { EditRecordComponent } from './components/edit-record/edit-record.component';

// const routes: Routes = [

//   {path:'',children:[{
//     path:'',component:LoginComponent
//   },{
//     path:'maker',component:CheckerNavComponent,children:[{
//       path:'',component:MakerComponent
//     },{
//       path:'history',children:[{
//         path:'',component:MakerHistoryComponent
//       },{
//         path:'file',component:MakerHistoryFileComponent
//       }]
//     },{
//       path:'upload',component:MakerUploadComponent
//     }]
//   },{
//     path:'checker',component:CheckerNavComponent
//   }]}
// ];

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  {
    path: 'maker',
    component: MakerNavComponent,
    children: [
      { path: '', component: MakerComponent }, // Default route for /maker
      {
        path: 'history',
        children: [
          { path: '', component: MakerHistoryComponent },
          { path: 'file', 
            children: [
              { path: '', component: MakerHistoryFileComponent  },
              { path: 'edit', component: EditRecordComponent },
            ]
        },
          
        ]
      },
      { path: 'upload', component: MakerUploadComponent }
    ]
  },
  { 
    path: 'checker', 
    component: CheckerNavComponent,
    children:[
      { path: '', component: CheckerComponent },
      { path: 'action', 
        children: [
          { path: '', component: CheckerActionComponent },
          { path: 'file', component: CheckerActionFileComponent }
        ]
    }
    ] 
  },
  
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
