import {Employees} from "./employees";

export class CurrentUserAuthenticationDto {
    public token: string;
    public employees: Employees;
}