import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

interface Season {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent implements OnInit {
  @Input()
  defaultValue: string;
  @Output()
  onSeasonChange: EventEmitter<any> = new EventEmitter();
  seasons: any[] = [
    { value: '2020', viewValue: '2020 ' },
    { value: '2019', viewValue: '2019 ' },
    { value: '2018', viewValue: '2018 ' },
    { value: '2017', viewValue: '2017 Season' },
    { value: '2016', viewValue: '2016 Season' },
    { value: '2015', viewValue: '2015 Season' },
    { value: '2014', viewValue: '2014 Season' },
    { value: '2013', viewValue: '2013 Season' },
    { value: '2012', viewValue: '2012 Season' },
    { value: '2011', viewValue: '2011 Season' },
    { value: '2010', viewValue: '2010 Season' },
    { value: '2009', viewValue: '2009 Season' },
    { value: '2008', viewValue: '2008 Season' }
  ];

  constructor() {
  }

  ngOnInit(): void {
  }

  onSelectionChange(event) {
    console.log("onSelectionChange", event);
    this.onSeasonChange.emit(event);
  }

}
