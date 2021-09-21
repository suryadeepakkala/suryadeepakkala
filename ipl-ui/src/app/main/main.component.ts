import { Component, OnInit } from '@angular/core';
import { IplService } from '../services/ipl.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})

export class MainComponent implements OnInit {
  selectedMenu: String = 'scoredRuns';
  selectedSeason: String = "2020";

  data: any;

  constructor(public iplService: IplService) {
  }

  ngOnInit(): void {
  }

  onSeasonChange(event) {
    console.log("onSeasonChange ", event);
    this.selectedSeason = event.value;
    this.getData(this.selectedMenu, this.selectedSeason);
  }

  getData(endPointPath, season) {
    this.iplService.getData(endPointPath, season).subscribe(res => {
    console.log(endPointPath, season, res);
    this.data = this.toTableData(res);
    })
  }

  toTableData(data: any) {
    return Object.keys(data).map(d => {
    return { key: d, value: data[d] };
    })
  }

  onMenuChange(event) {
    this.data = null;
    this.selectedMenu = event.source._value[0];
    console.log("onMenuChange", event.source._value[0]);
    this.getData(this.selectedMenu, this.selectedSeason);
  }

}
