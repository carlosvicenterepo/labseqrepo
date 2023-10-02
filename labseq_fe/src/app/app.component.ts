import { Component } from '@angular/core';
import {AppService} from "./app.service";
import {Subject, takeUntil} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  get responseValue(): String {
    return this._responseValue;
  }

  title = 'labseq_fe';

  _responseValue = "";
  constructor(private appService: AppService) {}

  destroy$: Subject<boolean> = new Subject<boolean>();

  onSubmit(event: any) {
    this.appService.labseq(event.target.value.value).pipe(takeUntil(this.destroy$)).subscribe(data => {
      // this.responseValue = data;
      this._responseValue = JSON.stringify(data, undefined, 2);
      console.log('message::::', this._responseValue);
    });
  }

  clean() {
    this._responseValue = "";
  }
}
