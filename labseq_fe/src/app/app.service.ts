import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient) { }

  rootURL = '/labseq';

  labseq(value: any) {

    return this.http.get(this.rootURL + '/' + value);
  }

}
