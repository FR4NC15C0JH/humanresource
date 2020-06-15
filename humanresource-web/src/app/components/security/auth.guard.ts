import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from "@angular/router";
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { EmployeesService } from 'src/app/service/employees.service';
import { SharedService } from 'src/app/service/shared.service';

@Injectable()
export class AuthGuard implements CanActivate {

    public shared: SharedService;

    constructor(private useSerice: EmployeesService,
                private router: Router){
        this.shared = SharedService.getInstance();
    }

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean> | boolean {
            if(this.shared.isLoggedIn()){
                return true;
            }
            this.router.navigate(["/login"]);
            return false;
        }
}