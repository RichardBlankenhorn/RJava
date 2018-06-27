import { DataService } from './../data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-get-data-frame',
  templateUrl: './get-data-frame.component.html',
  styleUrls: ['./get-data-frame.component.css']
})
export class GetDataFrameComponent implements OnInit {

  datasets = ['Auto', 'OJ', 'Caravan', 'Carseats', 'College', 'Credit', 'Default', 'Hitters', 'Portfolio', 'Smarket', 'Wage', 'Weekly'];

  data = null;

  datasetLength = 0;

  selected = 'Auto';

  index = 0;

  start = 0;

  end = 10;

  arr = new Array(10);

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
      },
      err => console.error('Get Dataset Got an Error: ' + err)
    );
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
