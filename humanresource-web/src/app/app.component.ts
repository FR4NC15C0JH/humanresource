import { Component } from '@angular/core';
import { SharedService } from './service/shared.service';
import { EmployeesService } from './service/employees.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  //title = 'humanresource-web';
  showTemplate: boolean = false;
  public shared: SharedService;

  constructor(private employeesService: EmployeesService){
    this.shared = SharedService.getInstance();
  }

  ngOnInit(){
    this.shared.showTemplate.subscribe(
      show => this.showTemplate = show
    );
  }

  showContentWrapper(){
    return{
      'content-wrapper' : this.shared.isLoggedIn()
    }
  }
}
