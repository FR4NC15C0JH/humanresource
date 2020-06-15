import { Departament } from './departament';
import { Job } from './job';

export class Employees {
    constructor(
        public id: number,
        public name: string,
        public email: string,
        public password: string,    
        public image: string,
        public departament: Departament,
        public job: Job,
        public profiles: string
    ){}        
}