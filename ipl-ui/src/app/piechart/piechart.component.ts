import { Component, Input, OnInit } from '@angular/core';

import * as d3 from 'd3-selection';
import * as d3Scale from 'd3-scale';
import * as d3Shape from 'd3-shape';

export interface Pchart {
  playerName: string;
  runs: string;
}

@Component({
  selector: 'app-piechart',
  templateUrl: './piechart.component.html',
  styleUrls: ['./piechart.component.css']
})

export class PiechartComponent implements OnInit {
  displayedColumns: string[];
  dataSource: any;
  StatsPieChart: { playerName: any; runs: any; }[];
  @Input()
  set data(data: any) {
    this.StatsPieChart = []
    console.log("data", data);
    data.map((objp) => {
      return this.StatsPieChart.push({ playerName: objp.key, runs: objp.value })
    })
    console.log(this.StatsPieChart);
  }

  title = 'D3 Pie Chart in Angular 10';

  margin = { top: 20, right: 20, bottom: 30, left: 50 };
  width: number;
  height: number;
  radius: number;

  arc: any;
  labelArc: any;
  labelPer: any;
  pie: any;
  color: any;
  svg: any;

  constructor() {
    this.width = 200 - this.margin.left - this.margin.right;
    this.height = 100 - this.margin.top - this.margin.bottom;
    this.radius = Math.min(this.width, this.height) / 2;
  }

  ngOnInit(): void {
    this.initSvg();
    this.drawPie();
  }

  initSvg() {
    this.color = d3Scale.scaleOrdinal()
      .range(['#FFA500', '#00FF00', '#FF0000', '#6b486b', '#FF00FF', '#d0743c', '#00FA9A']);
    this.arc = d3Shape.arc()
      .outerRadius(this.radius - 10)
      .innerRadius(0);
    this.labelArc = d3Shape.arc()
      .outerRadius(this.radius - 20)
      .innerRadius(this.radius - 20);

    this.labelPer = d3Shape.arc()
      .outerRadius(this.radius - 30)
      .innerRadius(this.radius - 30);

    this.pie = d3Shape.pie()
      .sort(null)
      .value((d: any) => d.runs);

    this.svg = d3.select('#pieChart')
      .append('svg')
      .attr('width', '100%')
      .attr('height', '100%')
      .attr('viewBox', '0 0 ' + Math.min(this.width, this.height) + ' ' + Math.min(this.width, this.height))
      .append('g')
      .attr('transform', 'translate(' + Math.min(this.width, this.height) / 2 + ',' + Math.min(this.width, this.height) / 2 + ')');
  }

  drawPie() {
    const g = this.svg.selectAll('.arc')
      .data(this.pie(this.StatsPieChart))
      .enter().append('g')
      .attr('class', 'arc');
    g.append('path').attr('d', this.arc)
      .style('fill', (d: any) => this.color(d.data.playerName));
    g.append("text")
      .attr("transform", function (d) {
        return "translate(" + ((this.radius - 12) * Math.sin(((d.endAngle - d.startAngle) / 2) + d.startAngle)) + "," + (-1 * (this.radius - 12) * Math.cos(((d.endAngle - d.startAngle) / 2) + d.startAngle)) + ")";
      }.bind(this))
      .attr("dy", ".35em")
      .style("font", ".2em sans-serif")
      .style("text-anchor", function (d) {
        var rads = ((d.endAngle - d.startAngle) / 2) + d.startAngle;
        if ((rads > 7 * Math.PI / 4 && rads < Math.PI / 4) || (rads > 3 * Math.PI / 4 && rads < 5 * Math.PI / 4)) {
          return "middle";
        } else if (rads >= Math.PI / 4 && rads <= 3 * Math.PI / 4) {
          return "start";
        } else if (rads >= 5 * Math.PI / 4 && rads <= 7 * Math.PI / 4) {
          return "end";
        } else {
          return "middle";
        }
      })
      .text(function (d) {
        return d.data.playerName + " / " + d.data.runs
      });
  }

}
