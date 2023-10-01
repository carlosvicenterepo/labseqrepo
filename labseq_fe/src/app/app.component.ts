import { Component } from '@angular/core';
import {AppService} from "./app.service";
import {Subject, takeUntil} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'labseq_fe';

  responseValue = '';
  constructor(private appService: AppService) {}

  destroy$: Subject<boolean> = new Subject<boolean>();

  onSubmit(event: any) {
    this.appService.labseq(event.target.value.value).pipe(takeUntil(this.destroy$)).subscribe(data => {
      // this.responseValue = data;
      this.responseValue = JSON.parse(JSON.stringify(data));
      console.log('message::::', data);
    });
  }
}
