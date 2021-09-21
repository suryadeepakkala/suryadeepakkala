import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

interface Chart {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {
  displayedColumns: string[];
  @Input() 
  set data(data:any){
    console.log("data", data);
    this.displayedColumns = data;
  }
 
  charts: Chart[] = [
    {value: 'bar', viewValue: 'BarChart'},
    {value: 'pie', viewValue: 'PieChart'}   
  ];

  chartType:string;

  constructor() {
   }

  ngOnInit(): void {
  }

  validateChart(obj){
    console.log(obj);
    return obj = 'bar' ? true:false ;
  }

  validatePieChart(objp){
    console.log(objp);
    return objp = 'pie' ? true:false;
  }

}
