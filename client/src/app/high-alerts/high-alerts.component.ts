import { Component } from '@angular/core';
import { VehicleService } from '../vehicle-service/vehicle.service';


@Component({
  selector: 'app-high-alerts',
  templateUrl: './high-alerts.component.html',
  styleUrls: ['./high-alerts.component.css']
})
export class HighAlertsComponent  {
  alerts;

  constructor(private vehicleService: VehicleService) {
    vehicleService.getReadings()
      .subscribe(
        alerts => this.alerts = alerts,
        error => console.log(error)
      );
  }

}

