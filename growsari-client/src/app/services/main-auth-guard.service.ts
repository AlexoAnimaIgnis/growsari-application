import { Injectable }    from '@angular/core';
import {
  CanActivate, Router,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
}                        from '@angular/router';
import { GrowsariUserService } from './user/growsariuser.service';

/**
 * @author alexander.ballester
 * main authentication service
 */
@Injectable()
export class MainAuthGuardService implements CanActivate {
  grantedAuthorities: Array<string>;

  constructor(
    private userService: GrowsariUserService,
    private router: Router) {

    }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (!this.userService.isAuthenticated()) {
      this.router.navigate(['/user/login', {url: state.url}] );
      return false;
    }
    return true; 
  }
}
