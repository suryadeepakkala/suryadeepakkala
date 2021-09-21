import { Component, Input, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { element } from 'protractor';


export interface TableElement {
  playerName: string;
  runs: string;
}

const ELEMENT_DATA: TableElement[] = [

];

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})

export class TableComponent implements OnInit {

  dataSource: MatTableDataSource<any>;

  displayedColumns: string[];
  @Input()
  set data(data: any) {
    console.log("data", data);
    if (!data || data.length <= 0) return;
    this.displayedColumns = Object.keys(data[0]);
    this.dataSource = new MatTableDataSource<any>(data)
  }

  constructor() { }

  ngOnInit(): void {

  }

}
