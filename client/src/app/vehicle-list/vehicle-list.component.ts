import { Component } from '@angular/core';
import {VehicleService} from '../vehicle-service/vehicle.service';


@Component({
  selector: 'app-vehicle-list',
  templateUrl: './vehicle-list.component.html',
  styleUrls: ['./vehicle-list.component.css']
})
export class VehicleListComponent  {
  vehicles;

  constructor(private vehicleService: VehicleService) {
    vehicleService.getVehicles()
      .subscribe(
        vehicles => this.vehicles = vehicles,
        error => console.log(error)
      );
     }

}
