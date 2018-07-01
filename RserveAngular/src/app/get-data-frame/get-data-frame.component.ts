import { DataService } from './../data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-get-data-frame',
  templateUrl: './get-data-frame.component.html',
  styleUrls: ['./get-data-frame.component.css']
})
export class GetDataFrameComponent implements OnInit {

  datasets = ['Auto', 'OJ', 'Caravan', 'Carseats', 'College', 'Credit', 'Default', 'Hitters', 'Portfolio', 'Smarket', 'Wage', 'Weekly'];

  choices = ['Stats', 'Data', 'Regression'];

  choice = 'Choose Analysis';

  data = null;

  varNames = null;

  respVar = null;

  selectedDataSet = 'Auto';

  selected = 'Auto';

  linModel = null;

  // BREAK
  sumStats = null;

  datasetLength = 0;

  sumStatsLength = 0;

  sumStatSelected = 'Auto';

  linModelSelected = 'Auto';

  dataModelNames = 'Auto';

  index = 0;

  start = 0;

  end = 10;

  arr = new Array(10);

  statArr = new Array(15);

  linArr = new Array(8);

  changeChoice = function() {
    this.varNames = null;
  };

  getDataSet = function() {
    this.arr = new Array();
    this.start = 0;
    this.end = 10;
    for (let i = this.start; i < this.end; i++) {
      this.arr.push(i);
    }
    this.dataService.getDataFrame(this.selected).subscribe(
      data => {
        this.data = data;
        this.datasetLength = data.objValues[0].length;
        console.log(data);
      },
      err => console.error('Get Dataset Got an Error: ' + err)
    );
  };

  getSumStats = function() {
    this.statArr = new Array();
    // this.start = 0;
    // this.end = 10;
    for (let i = 0; i < 14; i++) {
      this.statArr.push(i);
    }
    this.dataService.getSumStats(this.sumStatSelected).subscribe(
      data => {
        this.sumStats = data;
        console.log(data);
      },
      err => console.error('Get Sum Stats Got an Error: ' + err)
    );
  };

  getLinModel = function() {
    // this.linArr = new Array();
    // for (let i = 0; i < 8; i++) {
    //   this.linArr.push(i);
    // }
    this.dataService.getLinModel(this.selectedDataSet, this.respVar).subscribe(
      data => {
        this.linArr = new Array();
        for (let i = 0; i < data.objValues[1].length; i++) {
        this.linArr.push(i);
        }
        this.linModel = data;
        console.log(data);
      },
      err => console.error('Get Lin Model Got an Error: ' + err)
    );
  };

  getVarNames = function() {
    console.log(this.selectedDataSet);
    this.dataService.getVarNames(this.selectedDataSet).subscribe(
      data => {
        this.varNames = data;
        console.log(data);
      },
      err => console.error('Get Var Names Got an Error: ' + err)
    );
  };

  setLinModel = function(model) {
    this.linModelSelected = model;
  };

  nextTen = function() {
    if (this.end < this.datasetLength) {
      this.start = this.start + 10;
      this.end = this.end + 10;
      this.arr = new Array();
      for (let i = this.start; i < this.end; i++) {
        this.arr.push(i);
      }
    }
  };

  prevTen = function() {
    if (this.start > 0) {
      this.start = this.start - 10;
      this.end = this.end - 10;
      this.arr = new Array();
      for (let i = this.start; i < this.end; i++) {
        this.arr.push(i);
      }
    }
  };

  constructor(private dataService: DataService) { }

  ngOnInit() {
  }

}
