import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { MakerComponent } from './pages/maker/maker.component';
import { MakerNavComponent } from './components/maker-nav/maker-nav.component';
import { CheckerNavComponent } from './components/checker-nav/checker-nav.component';
import { MakerUploadComponent } from './pages/maker-upload/maker-upload.component';
import { MakerHistoryComponent } from './pages/maker-history/maker-history.component';
import { MakerHistoryFileComponent } from './pages/maker-history/maker-history-file/maker-history-file.component';
import { CheckerComponent } from './pages/checker/checker.component';
import { CheckerActionComponent } from './pages/checker-action/checker-action.component';
import { CheckerActionFileComponent } from './pages/checker-action-file/checker-action-file.component';
import { SignupComponent } from './components/signup/signup.component';
import { EditRecordComponent } from './components/edit-record/edit-record.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MakerComponent,
    MakerNavComponent,
    CheckerNavComponent,
    MakerUploadComponent,
    MakerHistoryComponent,
    MakerHistoryFileComponent,
    CheckerComponent,
    CheckerActionComponent,
    CheckerActionFileComponent,
    SignupComponent,
    EditRecordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
