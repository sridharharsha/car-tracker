import { Component } from '@angular/core';
import { VehicleService } from '../vehicle-service/vehicle.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-alerts-list',
  templateUrl: './alerts-list.component.html',
  styleUrls: ['./alerts-list.component.css']
})
export class AlertsListComponent {

  alerts;
  constructor(private route: ActivatedRoute, private vehicleService: VehicleService) {
    this.route.params.subscribe(params => {
      vehicleService.getReadingsByVin(params.vin)
        .subscribe(alerts => this.alerts = alerts);
      });
  }


}
