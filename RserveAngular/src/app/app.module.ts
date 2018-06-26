import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { GetDataFrameComponent } from './get-data-frame/get-data-frame.component';

@NgModule({
  declarations: [
    AppComponent,
    GetDataFrameComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
