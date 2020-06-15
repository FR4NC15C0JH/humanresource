import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule} from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EmployeesNewComponent } from './components/employees-new/employees-new.component';
import { EmployeesListComponent } from './components/employees-list/employees-list.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { MenuComponent } from './components/menu/menu.component';
import { EmployeesEditComponent } from './components/employees-edit/employees-edit.component';
import { EmployeesProfileComponent } from './components/employees-profile/employees-profile.component';
import { DepartamentListComponent } from './components/departament-list/departament-list.component';
import { DepartamentNewComponent } from './components/departament-new/departament-new.component';
import { JobListComponent } from './components/job-list/job-list.component';
import { JobNewComponent } from './components/job-new/job-new.component';
import { LoginComponent } from './components/security/login/login.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { EmployeesService } from './service/employees.service';
import { DepartamentService } from './service/departament.service';
import { JobService } from './service/job.service';
import { SharedService } from './service/shared.service';
import { MessageService } from './service/message.service';
import { AuthGuard } from './components/security/auth.guard';
import { AuthInterceptor } from './components/security/auth.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    EmployeesNewComponent,
    EmployeesListComponent,
    FooterComponent,
    HeaderComponent,
    HomeComponent,
    MenuComponent,
    EmployeesEditComponent,
    EmployeesProfileComponent,
    DepartamentListComponent,
    DepartamentNewComponent,
    JobListComponent,
    JobNewComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    EmployeesService,
    DepartamentService,
    JobService,
    SharedService,
    MessageService,
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
