import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HttpModule } from '@angular/http';
import { VehicleListComponent } from './vehicle-list/vehicle-list.component';
import {VehicleService} from './vehicle-service/vehicle.service';
import { RouterModule, Routes } from '@angular/router';
import { AlertsListComponent } from './alerts-list/alerts-list.component';
import { HighAlertsComponent } from './high-alerts/high-alerts.component';


const appRoutes: Routes = [
  {path: 'vehicles', component: VehicleListComponent},
  {path: 'vehicles/:vin', component: AlertsListComponent},
  {path: 'high-alerts', component: HighAlertsComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    VehicleListComponent,
    AlertsListComponent,
    HighAlertsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)

  ],
  providers: [VehicleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
