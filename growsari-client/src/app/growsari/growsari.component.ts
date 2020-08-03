import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from "@angular/material";
import { RoutingConstants } from '../constants/routing.constants';
import { UserCreateComponent } from './user/user-create/user-create.component';

@Component({
  selector: 'app-growsari',
  templateUrl: './growsari.component.html',
  styleUrls: ['./growsari.component.css']
})
export class GrowsariComponent implements OnInit {

  constructor(
    private dialogAddNewUser: MatDialog,
    private router: Router) { }

  ngOnInit(): void {
  }

  navigateToUsers() {
    this.router.navigate([RoutingConstants.ROUTE_USERS]);
  }

  navigateToTopics() {

  }

}
