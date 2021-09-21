import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';

@Injectable({
    providedIn: 'root'
})

export class IplService {
    mostrun: any;

    constructor(private http: HttpClient) { }

    ngOnInit(): void {
        let resmostruns = this.http.get("http://localhost:7777/matches/topScorers/2020");
        resmostruns.subscribe((data) => this.mostrun = data);
    }

    getData(endPointPath, season) {
        return this.http.get("http://localhost:7777/matches/" + endPointPath + "/" + season);
    }
}



