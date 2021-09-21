import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { IplService } from '../services/ipl.service';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.css']
})

export class SideMenuComponent implements OnInit {

  @Output()
  onMenuChange: EventEmitter<any> = new EventEmitter();
  menuItems: any[] = [
    { name: 'Scored Runs', value: 'scoredRuns' },
    { name: 'Matches Played', value: 'matchesPlayed' },
    { name: 'Top Runs', value: 'topRuns' },
    { name: 'Centuries Scored', value: 'centuryScored' },
    { name: 'Fifties Scored', value: 'fiftyScored' },
    { name: 'Fastest Fifties', value: 'fastestFiftyScored' },
    { name: 'Batting Strikerate', value: 'battingStrikeRate' },
    { name: 'Batting Average', value: 'battingAverage' },
    { name: 'Maidens Bowled', value: 'maidensBowled' },
    { name: 'Dotballs Bowled', value: 'dotBallsBowled' },
    { name: 'Bowling Economy', value: 'bowlingEconomy' },
    { name: 'Runs Conceded', value: 'runsConceded' }
  ]

  constructor() { }

  ngOnInit(): void {
  }

  onSidemenuChange(event) {
    console.log("sidemenu change", event);
    this.onMenuChange.emit(event);
  }

}
