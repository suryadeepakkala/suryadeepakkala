import { Component, Input, OnInit } from '@angular/core';

import * as d3 from 'd3-selection';
import * as d3Scale from 'd3-scale';
import * as d3Array from 'd3-array';
import * as d3Axis from 'd3-axis';

export interface Bchart {
  playerName: string;
  runs: string;
}

@Component({
  selector: 'app-barchart',
  templateUrl: './barchart.component.html',
  styleUrls: ['./barchart.component.css']
})

export class BarchartComponent implements OnInit {
  displayedColumns: string[];
  dataSource: any;
  StatsBarChart;
  @Input()
  set data(data: any) {
    this.StatsBarChart = []
    console.log("data", data);
    data.map((obj) => {
      return this.StatsBarChart.push({ playerName: obj.key, runs: obj.value })
    })
    console.log(this.StatsBarChart);
    this.rerender();
  }

  currentRate = 8;
  title = 'D3 Barchart with Angular 10';
  width: number;
  height: number;
  margin = { top: 20, right: 20, bottom: 30, left: 40 };
  x: any;
  y: any;
  svg: any;
  g: any;

  constructor() {
    this.width = 900 - this.margin.left - this.margin.right;
    this.height = 500 - this.margin.top - this.margin.bottom;
  }

  ngOnInit(): void {
    this.initSvg();
    this.initAxis();
    this.drawAxis();
    this.drawBars();
  }

  rerender() {
    this.initSvg();
    this.initAxis();
    this.drawAxis();
    this.drawBars();
  }

  initSvg() {
    d3.select('#barChart').selectAll('*').remove()
    this.svg = d3.select('#barChart')
      .append('svg')
      .attr('width', '100%')
      .attr('height', '100%')
      .attr('viewBox', '0 0 900 500');
    this.g = this.svg.append('g')
      .attr('transform', 'translate(' + this.margin.left + ',' + this.margin.top + ')');
  }

  initAxis() {
    this.x = d3Scale.scaleBand().rangeRound([0, this.width]).padding(0.1);
    this.y = d3Scale.scaleLinear().rangeRound([this.height, 0]);
    this.x.domain(this.StatsBarChart.map((d) => d.playerName));
    this.y.domain([0, d3Array.max(this.StatsBarChart, (d: { runs: any; }) => d.runs)]);
  }

  drawAxis() {
    this.g.append('g')
      .attr('class', 'axis axis--x')
      .attr('transform', 'translate(0,' + this.height + ')')
      .call(d3Axis.axisBottom(this.x));
    this.g.append('g')
      .attr('class', 'axis axis--y')
      .call(d3Axis.axisLeft(this.y))
      .append('text')
      .attr('class', 'axis-title')
      .attr('transform', 'rotate(-90)')
      .attr('y', 6)
      .attr('dy', '0.71em')
      .attr('text-anchor', 'end')
      .text('Frequency');
  }

  drawBars() {
    this.g.selectAll('.bar')
      .data(this.StatsBarChart)
      .enter().append('rect')
      .attr('class', 'bar')
      .attr('x', (d: { playerName: any; }) => this.x(d.playerName))
      .attr('y', (d: { runs: any; }) => this.y(d.runs))
      .attr('width', this.x.bandwidth())
      .attr('fill', '#498bfc')
      .attr('height', (d: { runs: any; }) => this.height - this.y(d.runs));
  }

}


